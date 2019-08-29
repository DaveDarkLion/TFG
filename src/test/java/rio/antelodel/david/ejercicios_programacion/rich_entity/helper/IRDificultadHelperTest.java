package rio.antelodel.david.ejercicios_programacion.rich_entity.helper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IRDificultadHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

@RunWith(PowerMockRunner.class)
public class IRDificultadHelperTest {

	@Mock private IRDificultad iRDificultad1;
	@Mock private IRDificultad iRDificultad2;
	@Mock private IRDificultad iRDificultad3;
	
	private List<IRDificultad> iRDificultadList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		when(iRDificultad1.getValor()).thenReturn(2.5f);
		when(iRDificultad2.getValor()).thenReturn(5f);
		when(iRDificultad3.getValor()).thenReturn(7.5f);
		
		iRDificultadList = Arrays.asList(iRDificultad1, iRDificultad2, iRDificultad3);
		
	}
	
	@Test
	public void testGetIRDificultadClosest () {
		
		assertEquals(iRDificultad2, IRDificultadHelper.getIRDificultadClosest(4.5f, iRDificultadList));
		
	}
	
	@Test
	@PrepareForTest(IRDificultadHelper.class)
	public void testGetIRDificultadAverage_Normal () {
		
		PowerMockito.spy(IRDificultadHelper.class);
		
		IRDificultadHelper.getIRDificultadAverage(Arrays.asList(iRDificultad1, iRDificultad3), iRDificultadList);
		PowerMockito.verifyStatic();
		IRDificultadHelper.getIRDificultadClosest(5f, iRDificultadList);
		
	}
	
}
