package rio.antelodel.david.ejercicios_programacion.rich_entity.helper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IRComplexSetHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;

public class IRComplexSetHelperTest {

	@Mock private IRExamen iRExamen1;
	@Mock private IRExamen iRExamen2;
	@Mock private IRExamen iRExamen3;
	
	private List<IRExamen> iRExamenList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		when(iRExamen1.isVisible()).thenReturn(false);
		when(iRExamen2.isVisible()).thenReturn(true);
		when(iRExamen3.isVisible()).thenReturn(false);
		
		iRExamenList = Arrays.asList(iRExamen1, iRExamen2, iRExamen3);
		
	}
	
	@Test
	public void testGetVisibleList () {
		
		assertEquals(Arrays.asList(iRExamen2), IRComplexSetHelper.getVisibleList(iRExamenList));
		
	}
	
}
