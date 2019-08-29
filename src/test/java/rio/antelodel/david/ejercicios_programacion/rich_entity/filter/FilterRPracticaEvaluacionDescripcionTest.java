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
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRPracticaEvaluacionDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class FilterRPracticaEvaluacionDescripcionTest extends ATest {

	private final String filter1 = "practicaEvaluacion1";
	private final String filter2 = "practicaEvaluacion2";
	private final String filter3 = "practicaEvaluacion3";
	
	private List<String> filters;
	
	private FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionDescripcion;
	
	@Mock private IRPracticaEvaluacion iRPracticaEvaluacion1;
	@Mock private IRPracticaEvaluacion iRPracticaEvaluacion2;
	@Mock private IRPracticaEvaluacion iRPracticaEvaluacion3;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIRPracticaEvaluacionDescripcion = new FilterIRPracticaEvaluacionDescripcion();
		
		when(iRPracticaEvaluacion1.getDescripcion()).thenReturn("practicaEvaluacion1");
		when(iRPracticaEvaluacion2.getDescripcion()).thenReturn("practicaEvaluacion2");
		when(iRPracticaEvaluacion3.getDescripcion()).thenReturn("practicaEvaluacion3");
		
		when(iREjercicio1.getFilterIRPracticaEvaluacion()).thenReturn(Arrays.asList(iRPracticaEvaluacion1, iRPracticaEvaluacion2, iRPracticaEvaluacion3));
		when(iREjercicio2.getFilterIRPracticaEvaluacion()).thenReturn(Arrays.asList(iRPracticaEvaluacion1));
		when(iREjercicio3.getFilterIRPracticaEvaluacion()).thenReturn(Arrays.asList(iRPracticaEvaluacion1, iRPracticaEvaluacion3));
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIRPracticaEvaluacionDescripcion.matches(iRPracticaEvaluacion1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIRPracticaEvaluacionDescripcion.matches(iRPracticaEvaluacion1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(true).when(filterIRPracticaEvaluacionSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRPracticaEvaluacionSpy.matchesObject(iREjercicio1, filter1));
		verify(filterIRPracticaEvaluacionSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matches(Matchers.eq(iRPracticaEvaluacion1), Matchers.anyString());
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matches(Matchers.eq(iRPracticaEvaluacion2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRPracticaEvaluacionSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRPracticaEvaluacionSpy).matches(iRPracticaEvaluacion1, filter2);
		verify(filterIRPracticaEvaluacionSpy).matches(iRPracticaEvaluacion2, filter2);
		verify(filterIRPracticaEvaluacionSpy, never()).matches(Matchers.eq(iRPracticaEvaluacion3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matches(Matchers.eq(iRPracticaEvaluacion1), Matchers.anyString());
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matches(Matchers.eq(iRPracticaEvaluacion2), Matchers.anyString());
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matches(Matchers.eq(iRPracticaEvaluacion3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRPracticaEvaluacionSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRPracticaEvaluacionSpy).matches(iRPracticaEvaluacion1, filter2);
		verify(filterIRPracticaEvaluacionSpy).matches(iRPracticaEvaluacion2, filter2);
		verify(filterIRPracticaEvaluacionSpy).matches(iRPracticaEvaluacion3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter2);
		
		// Testing
		
		assertTrue(filterIRPracticaEvaluacionSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRPracticaEvaluacionSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter2);
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRPracticaEvaluacionSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertTrue(filterIRPracticaEvaluacionSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRPracticaEvaluacionSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRPracticaEvaluacionSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyString());
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIRPracticaEvaluacionSpy.apply(iREjercicioList, filter1).size());
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio2, filter1);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(true).when(filterIRPracticaEvaluacionSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRPracticaEvaluacionSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRPracticaEvaluacionSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIRPracticaEvaluacionSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIRPracticaEvaluacionSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRPracticaEvaluacionSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio1, filters, Operation.AND);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio2, filters, Operation.AND);
		verify(filterIRPracticaEvaluacionSpy).matchesObject(iREjercicio3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(true).when(filterIRPracticaEvaluacionSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRPracticaEvaluacionSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND));
		verify(filterIRPracticaEvaluacionSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIRPracticaEvaluacionDescripcion filterIRPracticaEvaluacionSpy = spy(filterIRPracticaEvaluacionDescripcion);
		
		doReturn(false).when(filterIRPracticaEvaluacionSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIRPracticaEvaluacionSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND);
		verify(filterIRPracticaEvaluacionSpy).apply(Matchers.eq(iREjercicioList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}