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
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRExamenDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class FilterRExamenDescripcionTest extends ATest {

	private final String filter1 = "examen1";
	private final String filter2 = "examen2";
	private final String filter3 = "examen3";
	
	private List<String> filters;
	
	private FilterIRExamenDescripcion filterIRExamenDescripcion;
	
	@Mock private IRExamen iRExamen1;
	@Mock private IRExamen iRExamen2;
	@Mock private IRExamen iRExamen3;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIRExamenDescripcion = new FilterIRExamenDescripcion();
		
		when(iRExamen1.getDescripcion()).thenReturn("examen1");
		when(iRExamen2.getDescripcion()).thenReturn("examen2");
		when(iRExamen3.getDescripcion()).thenReturn("examen3");
		
		when(iREjercicio1.getFilterIRExamen()).thenReturn(Arrays.asList(iRExamen1, iRExamen2, iRExamen3));
		when(iREjercicio2.getFilterIRExamen()).thenReturn(Arrays.asList(iRExamen1));
		when(iREjercicio3.getFilterIRExamen()).thenReturn(Arrays.asList(iRExamen1, iRExamen3));
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIRExamenDescripcion.matches(iRExamen1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIRExamenDescripcion.matches(iRExamen1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(true).when(filterIRExamenSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRExamenSpy.matchesObject(iREjercicio1, filter1));
		verify(filterIRExamenSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(false).when(filterIRExamenSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRExamenSpy).matches(Matchers.eq(iRExamen1), Matchers.anyString());
		doReturn(true).when(filterIRExamenSpy).matches(Matchers.eq(iRExamen2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRExamenSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRExamenSpy).matches(iRExamen1, filter2);
		verify(filterIRExamenSpy).matches(iRExamen2, filter2);
		verify(filterIRExamenSpy, never()).matches(Matchers.eq(iRExamen3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(false).when(filterIRExamenSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRExamenSpy).matches(Matchers.eq(iRExamen1), Matchers.anyString());
		doReturn(false).when(filterIRExamenSpy).matches(Matchers.eq(iRExamen2), Matchers.anyString());
		doReturn(false).when(filterIRExamenSpy).matches(Matchers.eq(iRExamen3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRExamenSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRExamenSpy).matches(iRExamen1, filter2);
		verify(filterIRExamenSpy).matches(iRExamen2, filter2);
		verify(filterIRExamenSpy).matches(iRExamen3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(false).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter2);
		
		// Testing
		
		assertTrue(filterIRExamenSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRExamenSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(false).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter2);
		doReturn(false).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRExamenSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(true).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertTrue(filterIRExamenSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(true).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRExamenSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRExamenSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRExamenSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(false).when(filterIRExamenSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(true).when(filterIRExamenSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyString());
		doReturn(false).when(filterIRExamenSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIRExamenSpy.apply(iREjercicioList, filter1).size());
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRExamenSpy).matchesObject(iREjercicio2, filter1);
		verify(filterIRExamenSpy).matchesObject(iREjercicio3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(true).when(filterIRExamenSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRExamenSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRExamenSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(false).when(filterIRExamenSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIRExamenSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIRExamenSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIRExamenSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRExamenSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRExamenSpy).matchesObject(iREjercicio1, filters, Operation.AND);
		verify(filterIRExamenSpy).matchesObject(iREjercicio2, filters, Operation.AND);
		verify(filterIRExamenSpy).matchesObject(iREjercicio3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(true).when(filterIRExamenSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRExamenSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND));
		verify(filterIRExamenSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIRExamenDescripcion filterIRExamenSpy = spy(filterIRExamenDescripcion);
		
		doReturn(false).when(filterIRExamenSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIRExamenSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND);
		verify(filterIRExamenSpy).apply(Matchers.eq(iREjercicioList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}