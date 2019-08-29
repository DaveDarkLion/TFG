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
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class FilterRProfesorTest extends ATest {

	private final String filter1 = "profesor1";
	private final String filter2 = "profesor2";
	private final String filter3 = "profesor3";
	
	private List<String> filters;
	
	private FilterIRProfesor filterIRProfesorEmail;
	
	@Mock private IRProfesor iRProfesor1;
	@Mock private IRProfesor iRProfesor2;
	@Mock private IRProfesor iRProfesor3;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIRProfesorEmail = new FilterIRProfesor();
		
		when(iRProfesor1.getEmail()).thenReturn("profesor1");
		when(iRProfesor2.getEmail()).thenReturn("profesor2");
		when(iRProfesor3.getEmail()).thenReturn("profesor3");
		
		when(iREjercicio1.getFilterIRProfesor()).thenReturn(Arrays.asList(iRProfesor1, iRProfesor2, iRProfesor3));
		when(iREjercicio2.getFilterIRProfesor()).thenReturn(Arrays.asList(iRProfesor1));
		when(iREjercicio3.getFilterIRProfesor()).thenReturn(Arrays.asList(iRProfesor1, iRProfesor3));
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIRProfesorEmail.matches(iRProfesor1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIRProfesorEmail.matches(iRProfesor1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(true).when(filterIRProfesorSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRProfesorSpy.matchesObject(iREjercicio1, filter1));
		verify(filterIRProfesorSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(false).when(filterIRProfesorSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRProfesorSpy).matches(Matchers.eq(iRProfesor1), Matchers.anyString());
		doReturn(true).when(filterIRProfesorSpy).matches(Matchers.eq(iRProfesor2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRProfesorSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRProfesorSpy).matches(iRProfesor1, filter2);
		verify(filterIRProfesorSpy).matches(iRProfesor2, filter2);
		verify(filterIRProfesorSpy, never()).matches(Matchers.eq(iRProfesor3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(false).when(filterIRProfesorSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRProfesorSpy).matches(Matchers.eq(iRProfesor1), Matchers.anyString());
		doReturn(false).when(filterIRProfesorSpy).matches(Matchers.eq(iRProfesor2), Matchers.anyString());
		doReturn(false).when(filterIRProfesorSpy).matches(Matchers.eq(iRProfesor3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRProfesorSpy.matchesObject(iREjercicio1, filter2));
		verify(filterIRProfesorSpy).matches(iRProfesor1, filter2);
		verify(filterIRProfesorSpy).matches(iRProfesor2, filter2);
		verify(filterIRProfesorSpy).matches(iRProfesor3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(false).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter2);
		
		// Testing
		
		assertTrue(filterIRProfesorSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRProfesorSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(false).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter2);
		doReturn(false).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRProfesorSpy.matchesObject(iREjercicio1, filters, Operation.OR));
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(true).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		doReturn(true).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertTrue(filterIRProfesorSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(true).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		doReturn(false).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter2);
		doReturn(true).when(filterIRProfesorSpy).matchesObject(iREjercicio1, filter3);
		
		// Testing
		
		assertFalse(filterIRProfesorSpy.matchesObject(iREjercicio1, filters, Operation.AND));
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter2);
		verify(filterIRProfesorSpy, never()).matchesObject(iREjercicio1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(false).when(filterIRProfesorSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyString());
		doReturn(true).when(filterIRProfesorSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyString());
		doReturn(false).when(filterIRProfesorSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIRProfesorSpy.apply(iREjercicioList, filter1).size());
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filter1);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio2, filter1);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(true).when(filterIRProfesorSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRProfesorSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRProfesorSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(false).when(filterIRProfesorSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIRProfesorSpy).matchesObject(Matchers.eq(iREjercicio1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIRProfesorSpy).matchesObject(Matchers.eq(iREjercicio2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIRProfesorSpy).matchesObject(Matchers.eq(iREjercicio3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRProfesorSpy.apply(iREjercicioList, filters, Operation.AND));
		verify(filterIRProfesorSpy).matchesObject(iREjercicio1, filters, Operation.AND);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio2, filters, Operation.AND);
		verify(filterIRProfesorSpy).matchesObject(iREjercicio3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(true).when(filterIRProfesorSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iREjercicioList, filterIRProfesorSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND));
		verify(filterIRProfesorSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIRProfesor filterIRProfesorSpy = spy(filterIRProfesorEmail);
		
		doReturn(false).when(filterIRProfesorSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIRProfesorSpy.cleanAndApply(iREjercicioList, filter1, Operation.AND);
		verify(filterIRProfesorSpy).apply(Matchers.eq(iREjercicioList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}