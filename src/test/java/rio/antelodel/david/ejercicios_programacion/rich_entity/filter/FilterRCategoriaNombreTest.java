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
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRCategoriaNombre;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class FilterRCategoriaNombreTest extends ATest {

	private final String filter1 = "categoria1";
	private final String filter2 = "categoria2";
	private final String filter3 = "categoria3";
	
	private List<String> filters;
	
	private FilterIRCategoriaNombre filterIRCategoriaNombre;
	
	@Mock private IRCategoria iRCategoria1;
	@Mock private IRCategoria iRCategoria2;
	@Mock private IRCategoria iRCategoria3;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIRCategoriaNombre = new FilterIRCategoriaNombre();
		
		when(iRCategoria1.getNombre()).thenReturn("categoria1");
		when(iRCategoria2.getNombre()).thenReturn("categoria2");
		when(iRCategoria3.getNombre()).thenReturn("categoria3");
		
		when(iREjercicio1.getFilterIRCategoria()).thenReturn(Arrays.asList(iRCategoria1, iRCategoria2, iRCategoria3));
		when(iREjercicio2.getFilterIRCategoria()).thenReturn(Arrays.asList(iRCategoria1));
		when(iREjercicio3.getFilterIRCategoria()).thenReturn(Arrays.asList(iRCategoria1, iRCategoria3));
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIRCategoriaNombre.matches(iRCategoria1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIRCategoriaNombre.matches(iRCategoria1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(true).when(filterIRCategoriaSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRCategoriaSpy.matchesObject(iREjercicio1, filter1));
		verify(filterIRCategoriaSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(false).when(filterIRCategoriaSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRCategoriaSpy).matches(Matchers.eq(iRCategoria1), Matchers.anyString());
		doReturn(true).when(filterIRCategoriaSpy).matches(Matchers.eq(iRCategoria2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRCategoriaSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRCategoriaSpy).matches(iRCategoria1, filter2);
		verify(filterIRCategoriaSpy).matches(iRCategoria2, filter2);
		verify(filterIRCategoriaSpy, never()).matches(Matchers.eq(iRCategoria3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(false).when(filterIRCategoriaSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRCategoriaSpy).matches(Matchers.eq(iRCategoria1), Matchers.anyString());
		doReturn(false).when(filterIRCategoriaSpy).matches(Matchers.eq(iRCategoria2), Matchers.anyString());
		doReturn(false).when(filterIRCategoriaSpy).matches(Matchers.eq(iRCategoria3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRCategoriaSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRCategoriaSpy).matches(iRCategoria1, filter2);
		verify(filterIRCategoriaSpy).matches(iRCategoria2, filter2);
		verify(filterIRCategoriaSpy).matches(iRCategoria3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter2);
		
		// Testing
		
		assertTrue(filterIRCategoriaSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRCategoriaSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter2);
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRCategoriaSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(true).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertTrue(filterIRCategoriaSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(true).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRCategoriaSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRCategoriaSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(true).when(filterIRCategoriaSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyString());
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIRCategoriaSpy.apply(iREjercicioList, filter1).size());
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio2, filter1);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(true).when(filterIRCategoriaSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRCategoriaSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRCategoriaSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(false).when(filterIRCategoriaSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIRCategoriaSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIRCategoriaSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRCategoriaSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio1, filters, Operation.AND);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio2, filters, Operation.AND);
		verify(filterIRCategoriaSpy).matchesObject(iREjercicio3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(true).when(filterIRCategoriaSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRCategoriaSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND));
		verify(filterIRCategoriaSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIRCategoriaNombre filterIRCategoriaSpy = spy(filterIRCategoriaNombre);
		
		doReturn(false).when(filterIRCategoriaSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIRCategoriaSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND);
		verify(filterIRCategoriaSpy).apply(Matchers.eq(iREjercicioList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}