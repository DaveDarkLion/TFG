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
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPersona;

public class REjercicioPersonaTest {
	
	private REjercicioPersona rEjercicioPersona1;
	private REjercicioPersona rEjercicioPersona2;
	private REjercicioPersona rEjercicioPersona3;
	
	private IEjercicioPersona iEjercicioPersona1;
	private IEjercicioPersona iEjercicioPersona2;
	private IEjercicioPersona iEjercicioPersona3;
	
	@Mock private IEjercicio iEjercicio1;
	@Mock private IEjercicio iEjercicio2;
	@Mock private IEjercicio iEjercicio3;
	
	@Mock private IPersona iPersona1;
	
	private List<IREjercicioPersona> iREjercicioPersonaList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		iEjercicioPersona1 = MFactory.newIEjercicioPersona(iEjercicio1, iPersona1, 1);
		iEjercicioPersona2 = MFactory.newIEjercicioPersona(iEjercicio2, iPersona1, 2);
		iEjercicioPersona3 = MFactory.newIEjercicioPersona(iEjercicio3, iPersona1, 3);
		
		rEjercicioPersona1 = new REjercicioPersona(iEjercicioPersona1);
		rEjercicioPersona2 = new REjercicioPersona(iEjercicioPersona2);
		rEjercicioPersona3 = new REjercicioPersona(iEjercicioPersona3);
		
		iREjercicioPersonaList = Arrays.asList(rEjercicioPersona1, rEjercicioPersona2, rEjercicioPersona3);
		
	}
	
	@Test
	public void testGetPrevious_Normal () throws Exception {
		
		IREjercicioPersona iREjercicioPersonaResult = rEjercicioPersona2.getPrevious(iREjercicioPersonaList);
		assertEquals(rEjercicioPersona1, iREjercicioPersonaResult);
		
	}
	
	@Test
	public void testGetPrevious_First () throws Exception {
		
		IREjercicioPersona iREjercicioPersonaResult = rEjercicioPersona1.getPrevious(iREjercicioPersonaList);
		assertNull(iREjercicioPersonaResult);
		
	}
	
	@Test
	public void testGetNext_Normal () throws Exception {
		
		IREjercicioPersona iREjercicioPersonaResult = rEjercicioPersona2.getNext(iREjercicioPersonaList);
		assertEquals(rEjercicioPersona3, iREjercicioPersonaResult);
		
	}
	
	@Test
	public void testGetNext_Last () throws Exception {
		
		IREjercicioPersona iREjercicioPersonaResult = rEjercicioPersona3.getNext(iREjercicioPersonaList);
		assertNull(iREjercicioPersonaResult);
		
	}
	
	@Test
	public void testExchange () {
		
		rEjercicioPersona1.exchange(rEjercicioPersona3);
		
		assertEquals(3, rEjercicioPersona1.getPosition());
		assertEquals(1, rEjercicioPersona3.getPosition());
		
	}
	
}
