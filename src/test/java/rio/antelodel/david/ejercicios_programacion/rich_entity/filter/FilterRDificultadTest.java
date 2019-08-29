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
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class FilterRDificultadTest extends ATest {

	private final String filter1 = Integer.toString(0);
	private final String filter2 = Integer.toString(1);
	private final String filter3 = Integer.toString(2);
	
	private List<String> filters;
	
	private FilterIRDificultad filterIRDificultad;
	
	@Mock private IRDificultad iRDificultad1;
	@Mock private IRDificultad iRDificultad2;
	@Mock private IRDificultad iRDificultad3;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIRDificultad = new FilterIRDificultad();
		
		when(iRDificultad1.getId()).thenReturn(0);
		when(iRDificultad2.getId()).thenReturn(1);
		when(iRDificultad3.getId()).thenReturn(2);
		
		when(iREjercicio1.getFilterIRDificultad()).thenReturn(Arrays.asList(iRDificultad1, iRDificultad2, iRDificultad3));
		when(iREjercicio2.getFilterIRDificultad()).thenReturn(Arrays.asList(iRDificultad1));
		when(iREjercicio3.getFilterIRDificultad()).thenReturn(Arrays.asList(iRDificultad1, iRDificultad3));
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIRDificultad.matches(iRDificultad1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIRDificultad.matches(iRDificultad1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(true).when(filterIRDificultadSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRDificultadSpy.matchesObject(iREjercicio1, filter1));
		verify(filterIRDificultadSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(false).when(filterIRDificultadSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRDificultadSpy).matches(Matchers.eq(iRDificultad1), Matchers.anyString());
		doReturn(true).when(filterIRDificultadSpy).matches(Matchers.eq(iRDificultad2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRDificultadSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRDificultadSpy).matches(iRDificultad1, filter2);
		verify(filterIRDificultadSpy).matches(iRDificultad2, filter2);
		verify(filterIRDificultadSpy, never()).matches(Matchers.eq(iRDificultad3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(false).when(filterIRDificultadSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRDificultadSpy).matches(Matchers.eq(iRDificultad1), Matchers.anyString());
		doReturn(false).when(filterIRDificultadSpy).matches(Matchers.eq(iRDificultad2), Matchers.anyString());
		doReturn(false).when(filterIRDificultadSpy).matches(Matchers.eq(iRDificultad3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRDificultadSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRDificultadSpy).matches(iRDificultad1, filter2);
		verify(filterIRDificultadSpy).matches(iRDificultad2, filter2);
		verify(filterIRDificultadSpy).matches(iRDificultad3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(false).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter2);
		
		// Testing
		
		assertTrue(filterIRDificultadSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRDificultadSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(false).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter2);
		doReturn(false).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRDificultadSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(true).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertTrue(filterIRDificultadSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(true).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRDificultadSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRDificultadSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRDificultadSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(false).when(filterIRDificultadSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(true).when(filterIRDificultadSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyString());
		doReturn(false).when(filterIRDificultadSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIRDificultadSpy.apply(iREjercicioList, filter1).size());
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio2, filter1);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(true).when(filterIRDificultadSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRDificultadSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRDificultadSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(false).when(filterIRDificultadSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIRDificultadSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIRDificultadSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIRDificultadSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRDificultadSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRDificultadSpy).matchesObject(iREjercicio1, filters, Operation.AND);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio2, filters, Operation.AND);
		verify(filterIRDificultadSpy).matchesObject(iREjercicio3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(true).when(filterIRDificultadSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRDificultadSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND));
		verify(filterIRDificultadSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIRDificultad filterIRDificultadSpy = spy(filterIRDificultad);
		
		doReturn(false).when(filterIRDificultadSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIRDificultadSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND);
		verify(filterIRDificultadSpy).apply(Matchers.eq(iREjercicioList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}