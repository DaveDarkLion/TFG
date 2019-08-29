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
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;

public class FilterRTitulacionTest extends ATest {

	private final String filter1 = Integer.toString(0);
	private final String filter2 = Integer.toString(1);
	private final String filter3 = Integer.toString(2);
	
	private List<String> filters;
	
	private FilterIRTitulacion filterIRTitulacion;
	
	@Mock private IRTitulacion iRTitulacion1;
	@Mock private IRTitulacion iRTitulacion2;
	@Mock private IRTitulacion iRTitulacion3;
	
	@Mock private IRExamen iRExamen1;
	@Mock private IRExamen iRExamen2;
	@Mock private IRExamen iRExamen3;
	
	private List<IRExamen> iRExamenList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIRTitulacion = new FilterIRTitulacion();
		
		when(iRTitulacion1.getId()).thenReturn(0);
		when(iRTitulacion2.getId()).thenReturn(1);
		when(iRTitulacion3.getId()).thenReturn(2);
		
		when(iRExamen1.getFilterIRTitulacion()).thenReturn(Arrays.asList(iRTitulacion1, iRTitulacion2, iRTitulacion3));
		when(iRExamen2.getFilterIRTitulacion()).thenReturn(Arrays.asList(iRTitulacion1));
		when(iRExamen3.getFilterIRTitulacion()).thenReturn(Arrays.asList(iRTitulacion1, iRTitulacion3));
		
		iRExamenList = Arrays.asList(iRExamen1, iRExamen2, iRExamen3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIRTitulacion.matches(iRTitulacion1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIRTitulacion.matches(iRTitulacion1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(true).when(filterIRTitulacionSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRTitulacionSpy.matchesObject(iRExamen1, filter1));
		verify(filterIRTitulacionSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(false).when(filterIRTitulacionSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRTitulacionSpy).matches(Matchers.eq(iRTitulacion1), Matchers.anyString());
		doReturn(true).when(filterIRTitulacionSpy).matches(Matchers.eq(iRTitulacion2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRTitulacionSpy.matchesObject(iRExamen1, filter2));
		verify(filterIRTitulacionSpy).matches(iRTitulacion1, filter2);
		verify(filterIRTitulacionSpy).matches(iRTitulacion2, filter2);
		verify(filterIRTitulacionSpy, never()).matches(Matchers.eq(iRTitulacion3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(false).when(filterIRTitulacionSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRTitulacionSpy).matches(Matchers.eq(iRTitulacion1), Matchers.anyString());
		doReturn(false).when(filterIRTitulacionSpy).matches(Matchers.eq(iRTitulacion2), Matchers.anyString());
		doReturn(false).when(filterIRTitulacionSpy).matches(Matchers.eq(iRTitulacion3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRTitulacionSpy.matchesObject(iRExamen1, filter2));
		verify(filterIRTitulacionSpy).matches(iRTitulacion1, filter2);
		verify(filterIRTitulacionSpy).matches(iRTitulacion2, filter2);
		verify(filterIRTitulacionSpy).matches(iRTitulacion3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		doReturn(true).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter2);
		
		// Testing
		
		assertTrue(filterIRTitulacionSpy.matchesObject(iRExamen1, filters, Operation.OR));
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter2);
		verify(filterIRTitulacionSpy, never()).matchesObject(iRExamen1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter2);
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter3);
		
		// Testing
		
		assertFalse(filterIRTitulacionSpy.matchesObject(iRExamen1, filters, Operation.OR));
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter2);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(true).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		doReturn(true).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter2);
		doReturn(true).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter3);
		
		// Testing
		
		assertTrue(filterIRTitulacionSpy.matchesObject(iRExamen1, filters, Operation.AND));
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter2);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(true).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter2);
		doReturn(true).when(filterIRTitulacionSpy).matchesObject(iRExamen1, filter3);
		
		// Testing
		
		assertFalse(filterIRTitulacionSpy.matchesObject(iRExamen1, filters, Operation.AND));
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter2);
		verify(filterIRTitulacionSpy, never()).matchesObject(iRExamen1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(Matchers.eq(iRExamen1), Matchers.anyString());
		doReturn(true).when(filterIRTitulacionSpy).matchesObject(Matchers.eq(iRExamen2), Matchers.anyString());
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(Matchers.eq(iRExamen3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIRTitulacionSpy.apply(iRExamenList, filter1).size());
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filter1);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen2, filter1);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(true).when(filterIRTitulacionSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iRExamenList, filterIRTitulacionSpy.apply(iRExamenList, filters, Operation.AND));
		verify(filterIRTitulacionSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(false).when(filterIRTitulacionSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(Matchers.eq(iRExamen1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIRTitulacionSpy).matchesObject(Matchers.eq(iRExamen2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIRTitulacionSpy).matchesObject(Matchers.eq(iRExamen3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iRExamen2), filterIRTitulacionSpy.apply(iRExamenList, filters, Operation.AND));
		verify(filterIRTitulacionSpy).matchesObject(iRExamen1, filters, Operation.AND);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen2, filters, Operation.AND);
		verify(filterIRTitulacionSpy).matchesObject(iRExamen3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(true).when(filterIRTitulacionSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iRExamenList, filterIRTitulacionSpy.cleanAndApply(iRExamenList, filter1, Operation.AND));
		verify(filterIRTitulacionSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIRTitulacion filterIRTitulacionSpy = spy(filterIRTitulacion);
		
		doReturn(false).when(filterIRTitulacionSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIRTitulacionSpy.cleanAndApply(iRExamenList, filter1, Operation.AND);
		verify(filterIRTitulacionSpy).apply(Matchers.eq(iRExamenList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}