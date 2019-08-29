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
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioExamen;

public class REjercicioExamenTest {
	
	private REjercicioExamen rEjercicioExamen1;
	private REjercicioExamen rEjercicioExamen2;
	private REjercicioExamen rEjercicioExamen3;
	
	private IEjercicioExamen iEjercicioExamen1;
	private IEjercicioExamen iEjercicioExamen2;
	private IEjercicioExamen iEjercicioExamen3;
	
	@Mock private IEjercicio iEjercicio1;
	@Mock private IEjercicio iEjercicio2;
	@Mock private IEjercicio iEjercicio3;
	
	@Mock private IExamen iExamen1;
	
	private List<IREjercicioExamen> iREjercicioExamenList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		iEjercicioExamen1 = MFactory.newIEjercicioExamen(iEjercicio1, iExamen1, 1);
		iEjercicioExamen2 = MFactory.newIEjercicioExamen(iEjercicio2, iExamen1, 2);
		iEjercicioExamen3 = MFactory.newIEjercicioExamen(iEjercicio3, iExamen1, 3);
		
		rEjercicioExamen1 = new REjercicioExamen(iEjercicioExamen1);
		rEjercicioExamen2 = new REjercicioExamen(iEjercicioExamen2);
		rEjercicioExamen3 = new REjercicioExamen(iEjercicioExamen3);
		
		iREjercicioExamenList = Arrays.asList(rEjercicioExamen1, rEjercicioExamen2, rEjercicioExamen3);
		
	}
	
	@Test
	public void testGetPrevious_Normal () throws Exception {
		
		IREjercicioExamen iREjercicioExamenResult = rEjercicioExamen2.getPrevious(iREjercicioExamenList);
		assertEquals(rEjercicioExamen1, iREjercicioExamenResult);
		
	}
	
	@Test
	public void testGetPrevious_First () throws Exception {
		
		IREjercicioExamen iREjercicioExamenResult = rEjercicioExamen1.getPrevious(iREjercicioExamenList);
		assertNull(iREjercicioExamenResult);
		
	}
	
	@Test
	public void testGetNext_Normal () throws Exception {
		
		IREjercicioExamen iREjercicioExamenResult = rEjercicioExamen2.getNext(iREjercicioExamenList);
		assertEquals(rEjercicioExamen3, iREjercicioExamenResult);
		
	}
	
	@Test
	public void testGetNext_Last () throws Exception {
		
		IREjercicioExamen iREjercicioExamenResult = rEjercicioExamen3.getNext(iREjercicioExamenList);
		assertNull(iREjercicioExamenResult);
		
	}
	
	@Test
	public void testExchange () {
		
		rEjercicioExamen1.exchange(rEjercicioExamen3);
		
		assertEquals(3, rEjercicioExamen1.getPosition());
		assertEquals(1, rEjercicioExamen3.getPosition());
		
	}
	
}
