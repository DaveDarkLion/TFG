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

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRPracticaAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;

public class FilterRPracticaAnoRangeTest {

	private FilterIRPracticaAnoRange filterIRPracticaAnoRange;
	
	@Mock private IRPractica iRPractica1;
	@Mock private IRPractica iRPractica2;
	@Mock private IRPractica iRPractica3;
	
	private List<IRPractica> iRPracticaList;
	
	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filterIRPracticaAnoRange = new FilterIRPracticaAnoRange();
		
		when(iRPractica1.getAno()).thenReturn(2005);
		when(iRPractica2.getAno()).thenReturn(2010);
		when(iRPractica3.getAno()).thenReturn(2015);
		
		iRPracticaList = Arrays.asList(iRPractica1, iRPractica2, iRPractica3);
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testMatchesRange_Normal () {
		
		assertTrue(filterIRPracticaAnoRange.matchesRange(iRPractica2, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRange_TooHigh () {
		
		when(iRPractica1.getAno()).thenReturn(2025);
		
		assertFalse(filterIRPracticaAnoRange.matchesRange(iRPractica3, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRange_TooLow () {
		
		when(iRPractica1.getAno()).thenReturn(2005);
		
		assertFalse(filterIRPracticaAnoRange.matchesRange(iRPractica1, Integer.toString(2008), Integer.toString(2012)));
		
	}
	
	@Test
	public void testMatchesRangeList_True () {
		
		// Setup
		
		FilterIRPracticaAnoRange filterIRPracticaAnoRangeSpy = spy(filterIRPracticaAnoRange);
		
		doReturn(false).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica1), Matchers.anyString(), Matchers.anyString());
		doReturn(true).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica3), Matchers.anyString(), Matchers.anyString());
		
		assertTrue(filterIRPracticaAnoRangeSpy.matchesRange(iRPracticaList, "", ""));
		verify(filterIRPracticaAnoRangeSpy).matchesRange(iRPractica1, "", "");
		verify(filterIRPracticaAnoRangeSpy).matchesRange(iRPractica2, "", "");
		verify(filterIRPracticaAnoRangeSpy, never()).matchesRange(iRPractica3, "", "");
		
	}
	
	@Test
	public void testMatchesRangeList_False () {
		
		// Setup
		
		FilterIRPracticaAnoRange filterIRPracticaAnoRangeSpy = spy(filterIRPracticaAnoRange);
		
		doReturn(false).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica1), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica3), Matchers.anyString(), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRPracticaAnoRangeSpy.matchesRange(iRPracticaList, "", ""));
		verify(filterIRPracticaAnoRangeSpy).matchesRange(iRPractica1, "", "");
		verify(filterIRPracticaAnoRangeSpy).matchesRange(iRPractica2, "", "");
		verify(filterIRPracticaAnoRangeSpy).matchesRange(iRPractica3, "", "");
		
	}
	
	@Test
	public void testMatchesRangeGeneric () {
		
		// Setup
		
		FilterIRPracticaAnoRange filterIRPracticaAnoRangeSpy = spy(filterIRPracticaAnoRange);
		
		doReturn(Arrays.asList(iRPractica1)).when(filterIRPracticaAnoRangeSpy).getTargetList(iREjercicio1);
		doReturn(Arrays.asList(iRPractica2)).when(filterIRPracticaAnoRangeSpy).getTargetList(iREjercicio2);
		doReturn(Arrays.asList(iRPractica3)).when(filterIRPracticaAnoRangeSpy).getTargetList(iREjercicio3);
		
		doReturn(false).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica1), Matchers.anyString(), Matchers.anyString());
		doReturn(true).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica2), Matchers.anyString(), Matchers.anyString());
		doReturn(false).when(filterIRPracticaAnoRangeSpy).matchesRange(Matchers.eq(iRPractica3), Matchers.anyString(), Matchers.anyString());
		
		// Testing
		
		assertEquals(Arrays.asList(iREjercicio2), filterIRPracticaAnoRangeSpy.matchesRangeGeneric(iREjercicioList, "", ""));
		verify(filterIRPracticaAnoRangeSpy).matchesRange(iRPractica1, "", "");
		verify(filterIRPracticaAnoRangeSpy).matchesRange(iRPractica2, "", "");
		verify(filterIRPracticaAnoRangeSpy).matchesRange(iRPractica3, "", "");
		
	}
	
}
