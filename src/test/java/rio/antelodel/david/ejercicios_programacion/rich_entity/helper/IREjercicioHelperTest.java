package rio.antelodel.david.ejercicios_programacion.rich_entity.helper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IREjercicioHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class IREjercicioHelperTest {

	@Mock private IREjercicio iREjercicio1;
	@Mock private IREjercicio iREjercicio2;
	@Mock private IREjercicio iREjercicio3;
	
	private List<IREjercicio> iREjercicioList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		when(iREjercicio1.isVisibleForUnprivileged()).thenReturn(false);
		when(iREjercicio2.isVisibleForUnprivileged()).thenReturn(true);
		when(iREjercicio3.isVisibleForUnprivileged()).thenReturn(false);
		
		iREjercicioList = Arrays.asList(iREjercicio1, iREjercicio2, iREjercicio3);
		
	}
	
	@Test
	public void testGetVisibleList () {
		
		assertEquals(Arrays.asList(iREjercicio2), IREjercicioHelper.getVisibleForUnprivilegedList(iREjercicioList));
		
	}
	
}
