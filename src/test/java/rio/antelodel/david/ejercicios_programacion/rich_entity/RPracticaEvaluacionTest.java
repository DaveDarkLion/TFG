package rio.antelodel.david.ejercicios_programacion.rich_entity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RExamen;

public class RPracticaEvaluacionTest {

	@Mock private IREjercicioExamen iREjercicioExamen1;
	@Mock private IREjercicioExamen iREjercicioExamen2;
	@Mock private IREjercicioExamen iREjercicioExamen3;
	@Mock private IREjercicioExamen iREjercicioExamen4;
	
	List<IREjercicioExamen> iREjercicioExamenList;
	
	@Mock private IRTitulacion iRTitulacion1;
	
	private IRExamen iRExamen1;
	private IRExamen iRExamen2;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		iRExamen1 = new RExamen(0, 0, "testExamen1", iRTitulacion1, false, false);
		iRExamen1.getEntity().setId(0);
		iRExamen2 = new RExamen(0, 0, "testExamen2", iRTitulacion1, false, false);
		iRExamen2.getEntity().setId(1);
		
		doReturn(iRExamen1).when(iREjercicioExamen1).getIRSet();
		when(iREjercicioExamen1.getPosition()).thenReturn(4);
		
		doReturn(iRExamen1).when(iREjercicioExamen2).getIRSet();
		when(iREjercicioExamen2.getPosition()).thenReturn(7);
		
		doReturn(iRExamen2).when(iREjercicioExamen3).getIRSet();
		when(iREjercicioExamen3.getPosition()).thenReturn(15);
		
		doReturn(iRExamen1).when(iREjercicioExamen4).getIRSet();
		when(iREjercicioExamen4.getPosition()).thenReturn(1);
		
		iREjercicioExamenList = Arrays.asList(iREjercicioExamen1, iREjercicioExamen2, iREjercicioExamen3, iREjercicioExamen4);
		
	}
	
	@Test
	public void testGetLastPosition_Normal () {
		
		assertEquals(8, iRExamen1.getLastPosition(iREjercicioExamenList));
		
	}
	
	@Test
	public void testGetLastPosition_NullOrEmpty () {
		
		assertEquals(0, iRExamen1.getLastPosition(null));
		assertEquals(0, iRExamen1.getLastPosition(new ArrayList<>()));
		
	}
	
}
