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

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RPersona;

public class RPersonaTest {

	@Mock private IREjercicioPersona iREjercicioPersona1;
	@Mock private IREjercicioPersona iREjercicioPersona2;
	@Mock private IREjercicioPersona iREjercicioPersona3;
	@Mock private IREjercicioPersona iREjercicioPersona4;
	
	List<IREjercicioPersona> iREjercicioPersonaList;
	
	@Mock private IRTitulacion iRTitulacion1;
	
	private IRPersona iRPersona1;
	private IRPersona iRPersona2;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		iRPersona1 = new RPersona("testEmail1", "testNombre1", "testApellido1_1", "testApellido2_1", "testPassword1");
		iRPersona2 = new RPersona("testEmail2", "testNombre2", "testApellido1_2", "testApellido2_2", "testPassword2");
		
		doReturn(iRPersona1).when(iREjercicioPersona1).getIRSet();
		when(iREjercicioPersona1.getPosition()).thenReturn(4);
		
		doReturn(iRPersona1).when(iREjercicioPersona2).getIRSet();
		when(iREjercicioPersona2.getPosition()).thenReturn(7);
		
		doReturn(iRPersona2).when(iREjercicioPersona3).getIRSet();
		when(iREjercicioPersona3.getPosition()).thenReturn(15);
		
		doReturn(iRPersona1).when(iREjercicioPersona4).getIRSet();
		when(iREjercicioPersona4.getPosition()).thenReturn(1);
		
		iREjercicioPersonaList = Arrays.asList(iREjercicioPersona1, iREjercicioPersona2, iREjercicioPersona3, iREjercicioPersona4);
		
	}
	
	@Test
	public void testGetLastPosition_Normal () {
		
		assertEquals(8, iRPersona1.getLastPosition(iREjercicioPersonaList));
		
	}
	
	@Test
	public void testGetLastPosition_NullOrEmpty () {
		
		assertEquals(0, iRPersona1.getLastPosition(null));
		assertEquals(0, iRPersona1.getLastPosition(new ArrayList<>()));
		
	}
	
}
