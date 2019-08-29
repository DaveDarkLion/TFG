package rio.antelodel.david.ejercicios_programacion.rich_entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPracticaEvaluacion;

public class REjercicioPracticaEvaluacionTest {
	
	private REjercicioPracticaEvaluacion rEjercicioPracticaEvaluacion1;
	private REjercicioPracticaEvaluacion rEjercicioPracticaEvaluacion2;
	private REjercicioPracticaEvaluacion rEjercicioPracticaEvaluacion3;
	
	private IEjercicioPracticaEvaluacion iEjercicioPracticaEvaluacion1;
	private IEjercicioPracticaEvaluacion iEjercicioPracticaEvaluacion2;
	private IEjercicioPracticaEvaluacion iEjercicioPracticaEvaluacion3;
	
	@Mock private IEjercicio iEjercicio1;
	@Mock private IEjercicio iEjercicio2;
	@Mock private IEjercicio iEjercicio3;
	
	@Mock private IPracticaEvaluacion iPracticaEvaluacion1;
	
	private List<IREjercicioPracticaEvaluacion> iREjercicioPracticaEvaluacionList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		iEjercicioPracticaEvaluacion1 = MFactory.newIEjercicioPracticaEvaluacion(iEjercicio1, iPracticaEvaluacion1, 1);
		iEjercicioPracticaEvaluacion2 = MFactory.newIEjercicioPracticaEvaluacion(iEjercicio2, iPracticaEvaluacion1, 2);
		iEjercicioPracticaEvaluacion3 = MFactory.newIEjercicioPracticaEvaluacion(iEjercicio3, iPracticaEvaluacion1, 3);
		
		rEjercicioPracticaEvaluacion1 = new REjercicioPracticaEvaluacion(iEjercicioPracticaEvaluacion1);
		rEjercicioPracticaEvaluacion2 = new REjercicioPracticaEvaluacion(iEjercicioPracticaEvaluacion2);
		rEjercicioPracticaEvaluacion3 = new REjercicioPracticaEvaluacion(iEjercicioPracticaEvaluacion3);
		
		iREjercicioPracticaEvaluacionList = Arrays.asList(rEjercicioPracticaEvaluacion1, rEjercicioPracticaEvaluacion2, rEjercicioPracticaEvaluacion3);
		
	}
	
	@Test
	public void testGetPrevious_Normal () throws Exception {
		
		IREjercicioPracticaEvaluacion iREjercicioPracticaEvaluacionResult = rEjercicioPracticaEvaluacion2.getPrevious(iREjercicioPracticaEvaluacionList);
		assertEquals(rEjercicioPracticaEvaluacion1, iREjercicioPracticaEvaluacionResult);
		
	}
	
	@Test
	public void testGetPrevious_First () throws Exception {
		
		IREjercicioPracticaEvaluacion iREjercicioPracticaEvaluacionResult = rEjercicioPracticaEvaluacion1.getPrevious(iREjercicioPracticaEvaluacionList);
		assertNull(iREjercicioPracticaEvaluacionResult);
		
	}
	
	@Test
	public void testGetNext_Normal () throws Exception {
		
		IREjercicioPracticaEvaluacion iREjercicioPracticaEvaluacionResult = rEjercicioPracticaEvaluacion2.getNext(iREjercicioPracticaEvaluacionList);
		assertEquals(rEjercicioPracticaEvaluacion3, iREjercicioPracticaEvaluacionResult);
		
	}
	
	@Test
	public void testGetNext_Last () throws Exception {
		
		IREjercicioPracticaEvaluacion iREjercicioPracticaEvaluacionResult = rEjercicioPracticaEvaluacion3.getNext(iREjercicioPracticaEvaluacionList);
		assertNull(iREjercicioPracticaEvaluacionResult);
		
	}
	
	@Test
	public void testExchange () {
		
		rEjercicioPracticaEvaluacion1.exchange(rEjercicioPracticaEvaluacion3);
		
		assertEquals(3, rEjercicioPracticaEvaluacion1.getPosition());
		assertEquals(1, rEjercicioPracticaEvaluacion3.getPosition());
		
	}
	
}
