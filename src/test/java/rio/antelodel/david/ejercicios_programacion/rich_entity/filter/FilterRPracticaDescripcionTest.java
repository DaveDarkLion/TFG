package rio.antelodel.david.ejercicios_programacion.rich_entity.filter;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRPracticaDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class FilterRPracticaDescripcionTest extends ATest {

	private final String filter1 = "practica1";
	private final String filter2 = "practica2";
	private final String filter3 = "practica3";
	
	private List<String> filters;
	
	private FilterIRPracticaDescripcion filterIRPracticaDescripcion;
	
	@Mock private IRPractica iRPractica1;
	@Mock private IRPractica iRPractica2;
	@Mock private IRPractica iRPractica3;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIRPracticaDescripcion = new FilterIRPracticaDescripcion();
		
		when(iRPractica1.getDescripcion()).thenReturn("practica1");
		when(iRPractica2.getDescripcion()).thenReturn("practica2");
		when(iRPractica3.getDescripcion()).thenReturn("practica3");
		
		when(iREjercicio1.getFilterIRPractica()).thenReturn(Arrays.asList(iRPractica1, iRPractica2, iRPractica3));
		when(iREjercicio2.getFilterIRPractica()).thenReturn(Arrays.asList(iRPractica1));
		when(iREjercicio3.getFilterIRPractica()).thenReturn(Arrays.asList(iRPractica1, iRPractica3));
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIRPracticaDescripcion.matches(iRPractica1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIRPracticaDescripcion.matches(iRPractica1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(true).when(filterIRPracticaSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRPracticaSpy.matchesObject(iREjercicio1, filter1));
		verify(filterIRPracticaSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(false).when(filterIRPracticaSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRPracticaSpy).matches(Matchers.eq(iRPractica1), Matchers.anyString());
		doReturn(true).when(filterIRPracticaSpy).matches(Matchers.eq(iRPractica2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRPracticaSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRPracticaSpy).matches(iRPractica1, filter2);
		verify(filterIRPracticaSpy).matches(iRPractica2, filter2);
		verify(filterIRPracticaSpy, never()).matches(Matchers.eq(iRPractica3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(false).when(filterIRPracticaSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRPracticaSpy).matches(Matchers.eq(iRPractica1), Matchers.anyString());
		doReturn(false).when(filterIRPracticaSpy).matches(Matchers.eq(iRPractica2), Matchers.anyString());
		doReturn(false).when(filterIRPracticaSpy).matches(Matchers.eq(iRPractica3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRPracticaSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRPracticaSpy).matches(iRPractica1, filter2);
		verify(filterIRPracticaSpy).matches(iRPractica2, filter2);
		verify(filterIRPracticaSpy).matches(iRPractica3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(false).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter2);
		
		// Testing
		
		assertTrue(filterIRPracticaSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRPracticaSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(false).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter2);
		doReturn(false).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRPracticaSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(true).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertTrue(filterIRPracticaSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(true).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRPracticaSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRPracticaSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRPracticaSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(false).when(filterIRPracticaSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(true).when(filterIRPracticaSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyString());
		doReturn(false).when(filterIRPracticaSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIRPracticaSpy.apply(iREjercicioList, filter1).size());
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio2, filter1);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(true).when(filterIRPracticaSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRPracticaSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRPracticaSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(false).when(filterIRPracticaSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIRPracticaSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIRPracticaSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIRPracticaSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRPracticaSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRPracticaSpy).matchesObject(iREjercicio1, filters, Operation.AND);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio2, filters, Operation.AND);
		verify(filterIRPracticaSpy).matchesObject(iREjercicio3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(true).when(filterIRPracticaSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRPracticaSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND));
		verify(filterIRPracticaSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIRPracticaDescripcion filterIRPracticaSpy = spy(filterIRPracticaDescripcion);
		
		doReturn(false).when(filterIRPracticaSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIRPracticaSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND);
		verify(filterIRPracticaSpy).apply(Matchers.eq(iREjercicioList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}