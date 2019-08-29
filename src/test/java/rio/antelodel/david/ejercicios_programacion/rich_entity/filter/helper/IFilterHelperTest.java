package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import rio.antelodel.david.ejercicios_programacion.ATest;

public class IFilterHelperTest extends ATest {

	@Test
	public void testFilterListToString_Normal () {
		
		assertEquals("test1" + IFilterHelper.DEFAULT_SEPARATOR + " test2" + IFilterHelper.DEFAULT_SEPARATOR + " test3", IFilterHelper.filterListToString(Arrays.asList("test1", "test2", "test3")));
		
	}
	
	@Test
	public void testFilterListToString_One () {
		
		assertEquals("test1", IFilterHelper.filterListToString(Arrays.asList("test1")));
		
	}
	
	@Test
	public void testFilterListToString_Empty () {
		
		assertEquals("", IFilterHelper.filterListToString(new ArrayList<>()));
		
	}
	
	@Test
	public void testSeparateFilters_Normal () {
		
		assertEquals(Arrays.asList("test1", "test2", "test3"), IFilterHelper.separateFilters("test1   , test2,test3, ,,,,,,,, ,"));
		
	}
	
	@Test
	public void testSeparateFilters_Null () {
		
		assertEquals(new ArrayList<>(), IFilterHelper.separateFilters(null));
		
	}
	
}