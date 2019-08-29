package rio.antelodel.david.ejercicios_programacion.rich_entity.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRExamenAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;

public class FilterRExamenAnoRangeTest {

	private FilterIRExamenAnoRange filterIRExamenAnoRange;
	
	@Mock private IRExamen iRExamen1;
	@Mock private IRExamen iRExamen2;
	@Mock private IRExamen iRExamen3;
	
	private List<IRExamen> iRExamenList;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filterIRExamenAnoRange = new FilterIRExamenAnoRange();
		
		when(iRExamen1.getAno()).thenReturn(2005);
		when(iRExamen2.getAno()).thenReturn(2010);
		when(iRExamen3.getAno()).thenReturn(2015);
		
		iRExamenList = Arrays.asList(iRExamen1, iRExamen2, iRExamen3);
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatchesRange_Normal () {
		
		assertTrue(filterIRExamenAnoRange.matchesRange(iRExamen2, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRange_TooHigh () {
		
		when(iRExamen1.getAno()).thenReturn(2025);
		
		assertFalse(filterIRExamenAnoRange.matchesRange(iRExamen3, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRange_TooLow () {
		
		when(iRExamen1.getAno()).thenReturn(2005);
		
		assertFalse(filterIRExamenAnoRange.matchesRange(iRExamen1, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRangeList_True () {
		
		// Setup
		
		FilterIRExamenAnoRange filterIRExamenAnoRangeSpy = spy(filterIRExamenAnoRange);
		
		doReturn(false).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen1), Matchers.anyString(), Matchers.anyString());
		doReturn(true).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen3), Matchers.anyString(), Matchers.anyString());
		
		assertTrue(filterIRExamenAnoRangeSpy.matchesRange(iRExamenList, "", ""));
		verify(filterIRExamenAnoRangeSpy).matchesRange(iRExamen1, "", "");
		verify(filterIRExamenAnoRangeSpy).matchesRange(iRExamen2, "", "");
		verify(filterIRExamenAnoRangeSpy, never()).matchesRange(iRExamen3, "", "");
		
	}
	
	@Test
	public void testMatchesRangeList_False () {
		
		// Setup
		
		FilterIRExamenAnoRange filterIRExamenAnoRangeSpy = spy(filterIRExamenAnoRange);
		
		doReturn(false).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen1), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen3), Matchers.anyString(), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRExamenAnoRangeSpy.matchesRange(iRExamenList, "", ""));
		verify(filterIRExamenAnoRangeSpy).matchesRange(iRExamen1, "", "");
		verify(filterIRExamenAnoRangeSpy).matchesRange(iRExamen2, "", "");
		verify(filterIRExamenAnoRangeSpy).matchesRange(iRExamen3, "", "");
		
	}
	
	@Test
	public void testMatchesRangeGeneric () {
		
		// Setup
		
		FilterIRExamenAnoRange filterIRExamenAnoRangeSpy = spy(filterIRExamenAnoRange);
		
		doReturn(Arrays.asList(iRExamen1)).when(filterIRExamenAnoRangeSpy).getTargetList(iREjercicio1);
		doReturn(Arrays.asList(iRExamen2)).when(filterIRExamenAnoRangeSpy).getTargetList(iREjercicio2);
		doReturn(Arrays.asList(iRExamen3)).when(filterIRExamenAnoRangeSpy).getTargetList(iREjercicio3);
		
		doReturn(false).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen1), Matchers.anyString(), Matchers.anyString());
		doReturn(true).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRExamenAnoRangeSpy).matchesRange(Matchers.eq(iRExamen3), Matchers.anyString(), Matchers.anyString());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRExamenAnoRangeSpy.matchesRangeGeneric(iREjercicioList, "", ""));
		verify(filterIRExamenAnoRangeSpy).matchesRange(iRExamen1, "", "");
		verify(filterIRExamenAnoRangeSpy).matchesRange(iRExamen2, "", "");
		verify(filterIRExamenAnoRangeSpy).matchesRange(iRExamen3, "", "");
		
	}
	
}
