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

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRPracticaEvaluacionAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;

public class FilterRPracticaEvaluacionAnoRangeTest {

	private FilterIRPracticaEvaluacionAnoRange filterIRPracticaEvaluacionAnoRange;
	
	@Mock private IRPracticaEvaluacion iRPracticaEvaluacion1;
	@Mock private IRPracticaEvaluacion iRPracticaEvaluacion2;
	@Mock private IRPracticaEvaluacion iRPracticaEvaluacion3;
	
	private List<IRPracticaEvaluacion> iRPracticaEvaluacionList;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filterIRPracticaEvaluacionAnoRange = new FilterIRPracticaEvaluacionAnoRange();
		
		when(iRPracticaEvaluacion1.getAno()).thenReturn(2005);
		when(iRPracticaEvaluacion2.getAno()).thenReturn(2010);
		when(iRPracticaEvaluacion3.getAno()).thenReturn(2015);
		
		iRPracticaEvaluacionList = Arrays.asList(iRPracticaEvaluacion1, iRPracticaEvaluacion2, iRPracticaEvaluacion3);
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatchesRange_Normal () {
		
		assertTrue(filterIRPracticaEvaluacionAnoRange.matchesRange(iRPracticaEvaluacion2, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRange_TooHigh () {
		
		when(iRPracticaEvaluacion1.getAno()).thenReturn(2025);
		
		assertFalse(filterIRPracticaEvaluacionAnoRange.matchesRange(iRPracticaEvaluacion3, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRange_TooLow () {
		
		when(iRPracticaEvaluacion1.getAno()).thenReturn(2005);
		
		assertFalse(filterIRPracticaEvaluacionAnoRange.matchesRange(iRPracticaEvaluacion1, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRangeList_True () {
		
		// Setup
		
		FilterIRPracticaEvaluacionAnoRange filterIRPracticaEvaluacionAnoRangeSpy = spy(filterIRPracticaEvaluacionAnoRange);
		
		doReturn(false).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion1), Matchers.anyString(), Matchers.anyString());
		doReturn(true).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion3), Matchers.anyString(), Matchers.anyString());
		
		assertTrue(filterIRPracticaEvaluacionAnoRangeSpy.matchesRange(iRPracticaEvaluacionList, "", ""));
		verify(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(iRPracticaEvaluacion1, "", "");
		verify(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(iRPracticaEvaluacion2, "", "");
		verify(filterIRPracticaEvaluacionAnoRangeSpy, never()).matchesRange(iRPracticaEvaluacion3, "", "");
		
	}
	
	@Test
	public void testMatchesRangeList_False () {
		
		// Setup
		
		FilterIRPracticaEvaluacionAnoRange filterIRPracticaEvaluacionAnoRangeSpy = spy(filterIRPracticaEvaluacionAnoRange);
		
		doReturn(false).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion1), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion3), Matchers.anyString(), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRPracticaEvaluacionAnoRangeSpy.matchesRange(iRPracticaEvaluacionList, "", ""));
		verify(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(iRPracticaEvaluacion1, "", "");
		verify(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(iRPracticaEvaluacion2, "", "");
		verify(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(iRPracticaEvaluacion3, "", "");
		
	}
	
	@Test
	public void testMatchesRangeGeneric () {
		
		// Setup
		
		FilterIRPracticaEvaluacionAnoRange filterIRPracticaEvaluacionAnoRangeSpy = spy(filterIRPracticaEvaluacionAnoRange);
		
		doReturn(Arrays.asList(iRPracticaEvaluacion1)).when(filterIRPracticaEvaluacionAnoRangeSpy).getTargetList(iREjercicio1);
		doReturn(Arrays.asList(iRPracticaEvaluacion2)).when(filterIRPracticaEvaluacionAnoRangeSpy).getTargetList(iREjercicio2);
		doReturn(Arrays.asList(iRPracticaEvaluacion3)).when(filterIRPracticaEvaluacionAnoRangeSpy).getTargetList(iREjercicio3);
		
		doReturn(false).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion1), Matchers.anyString(), Matchers.anyString());
		doReturn(true).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(Matchers.eq(iRPracticaEvaluacion3), Matchers.anyString(), Matchers.anyString());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRPracticaEvaluacionAnoRangeSpy.matchesRangeGeneric(iREjercicioList, "", ""));
		verify(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(iRPracticaEvaluacion1, "", "");
		verify(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(iRPracticaEvaluacion2, "", "");
		verify(filterIRPracticaEvaluacionAnoRangeSpy).matchesRange(iRPracticaEvaluacion3, "", "");
		
	}
	
}
