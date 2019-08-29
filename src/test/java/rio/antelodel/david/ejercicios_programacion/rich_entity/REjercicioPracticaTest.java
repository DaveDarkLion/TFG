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
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPractica;

public class REjercicioPracticaTest {
	
	private REjercicioPractica rEjercicioPractica1;
	private REjercicioPractica rEjercicioPractica2;
	private REjercicioPractica rEjercicioPractica3;
	
	private IEjercicioPractica iEjercicioPractica1;
	private IEjercicioPractica iEjercicioPractica2;
	private IEjercicioPractica iEjercicioPractica3;
	
	@Mock private IEjercicio iEjercicio1;
	@Mock private IEjercicio iEjercicio2;
	@Mock private IEjercicio iEjercicio3;
	
	@Mock private IPractica iPractica1;
	
	private List<IREjercicioPractica> iREjercicioPracticaList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		iEjercicioPractica1 = MFactory.newIEjercicioPractica(iEjercicio1, iPractica1, 1);
		iEjercicioPractica2 = MFactory.newIEjercicioPractica(iEjercicio2, iPractica1, 2);
		iEjercicioPractica3 = MFactory.newIEjercicioPractica(iEjercicio3, iPractica1, 3);
		
		rEjercicioPractica1 = new REjercicioPractica(iEjercicioPractica1);
		rEjercicioPractica2 = new REjercicioPractica(iEjercicioPractica2);
		rEjercicioPractica3 = new REjercicioPractica(iEjercicioPractica3);
		
		iREjercicioPracticaList = Arrays.asList(rEjercicioPractica1, rEjercicioPractica2, rEjercicioPractica3);
		
	}
	
	@Test
	public void testGetPrevious_Normal () throws Exception {
		
		IREjercicioPractica iREjercicioPracticaResult = rEjercicioPractica2.getPrevious(iREjercicioPracticaList);
		assertEquals(rEjercicioPractica1, iREjercicioPracticaResult);
		
	}
	
	@Test
	public void testGetPrevious_First () throws Exception {
		
		IREjercicioPractica iREjercicioPracticaResult = rEjercicioPractica1.getPrevious(iREjercicioPracticaList);
		assertNull(iREjercicioPracticaResult);
		
	}
	
	@Test
	public void testGetNext_Normal () throws Exception {
		
		IREjercicioPractica iREjercicioPracticaResult = rEjercicioPractica2.getNext(iREjercicioPracticaList);
		assertEquals(rEjercicioPractica3, iREjercicioPracticaResult);
		
	}
	
	@Test
	public void testGetNext_Last () throws Exception {
		
		IREjercicioPractica iREjercicioPracticaResult = rEjercicioPractica3.getNext(iREjercicioPracticaList);
		assertNull(iREjercicioPracticaResult);
		
	}
	
	@Test
	public void testExchange () {
		
		rEjercicioPractica1.exchange(rEjercicioPractica3);
		
		assertEquals(3, rEjercicioPractica1.getPosition());
		assertEquals(1, rEjercicioPractica3.getPosition());
		
	}
	
}
