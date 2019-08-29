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
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIREjercicioEnunciado;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class FilterREjercicioEnunciadoTest extends ATest {

	private final String filter1 = "ejercicio1";
	private final String filter2 = "ejercicio2";
	private final String filter3 = "ejercicio3";
	
	private List<String> filters;
	
	private FilterIREjercicioEnunciado filterIREjercicioEnunciado;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIREjercicioEnunciado = new FilterIREjercicioEnunciado();
		
		when(iREjercicio1.getEnunciado()).thenReturn("ejercicio1");
		when(iREjercicio1.getTitulo()).thenReturn("ejercicio1");
		when(iREjercicio2.getEnunciado()).thenReturn("ejercicio2");
		when(iREjercicio2.getTitulo()).thenReturn("ejercicio2");
		when(iREjercicio3.getEnunciado()).thenReturn("ejercicio3");
		when(iREjercicio3.getTitulo()).thenReturn("ejercicio3");
		
		when(iREjercicio1.getFilterIREjercicio()).thenReturn(Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3));
		when(iREjercicio2.getFilterIREjercicio()).thenReturn(Arrays.asList(iREjercicio1));
		when(iREjercicio3.getFilterIREjercicio()).thenReturn(Arrays.asList(iREjercicio1, iREjercicio3));
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIREjercicioEnunciado.matches(iREjercicio1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIREjercicioEnunciado.matches(iREjercicio1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(true).when(filterIREjercicioSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIREjercicioSpy.matchesObject(iREjercicio1, filter1));
		verify(filterIREjercicioSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(false).when(filterIREjercicioSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIREjercicioSpy).matches(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(true).when(filterIREjercicioSpy).matches(Matchers.eq(iREjercicio2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIREjercicioSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIREjercicioSpy).matches(iREjercicio1, filter2);
		verify(filterIREjercicioSpy).matches(iREjercicio2, filter2);
		verify(filterIREjercicioSpy, never()).matches(Matchers.eq(iREjercicio3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(false).when(filterIREjercicioSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIREjercicioSpy).matches(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(false).when(filterIREjercicioSpy).matches(Matchers.eq(iREjercicio2), Matchers.anyString());
		doReturn(false).when(filterIREjercicioSpy).matches(Matchers.eq(iREjercicio3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIREjercicioSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIREjercicioSpy).matches(iREjercicio1, filter2);
		verify(filterIREjercicioSpy).matches(iREjercicio2, filter2);
		verify(filterIREjercicioSpy).matches(iREjercicio3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(false).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter2);
		
		// Testing
		
		assertTrue(filterIREjercicioSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIREjercicioSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(false).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter2);
		doReturn(false).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIREjercicioSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(true).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertTrue(filterIREjercicioSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(true).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIREjercicioSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIREjercicioSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIREjercicioSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(false).when(filterIREjercicioSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(true).when(filterIREjercicioSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyString());
		doReturn(false).when(filterIREjercicioSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIREjercicioSpy.apply(iREjercicioList, filter1).size());
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio2, filter1);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(true).when(filterIREjercicioSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIREjercicioSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIREjercicioSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(false).when(filterIREjercicioSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIREjercicioSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIREjercicioSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIREjercicioSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIREjercicioSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIREjercicioSpy).matchesObject(iREjercicio1, filters, Operation.AND);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio2, filters, Operation.AND);
		verify(filterIREjercicioSpy).matchesObject(iREjercicio3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(true).when(filterIREjercicioSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIREjercicioSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND));
		verify(filterIREjercicioSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIREjercicioEnunciado filterIREjercicioSpy = spy(filterIREjercicioEnunciado);
		
		doReturn(false).when(filterIREjercicioSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIREjercicioSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND);
		verify(filterIREjercicioSpy).apply(Matchers.eq(iREjercicioList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}