package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IFilterHelper {

	// Private Constructor
	
	private IFilterHelper () { }
	
	// Operations
	
	public enum Operation { OR, AND }
		
	// Constants
		
	public static final String DEFAULT_SEPARATOR = ",";
	
	// Functions
	
	public static List<String> separateFilters (String filters, String separator) {
		
		if (filters == null) filters = "";
		List<String> resultUntrimmed = getArray(filters.split(separator));
		List<String> result = new ArrayList<>();
		
		
		for (String f : resultUntrimmed) if (!f.matches("\\s*")) result.add(f.trim());
		
		return result;
		
	}
	
	public static List<String> separateFilters (String filters) {
		
		return separateFilters(filters, DEFAULT_SEPARATOR);
		
	}
	
	public static String filterListToString (List<String> filterList) {
		
		StringBuilder result = new StringBuilder();
		
		if (!filterList.isEmpty()) result.append(filterList.get(0));
		
		if (filterList.size() > 1) for (int i = 1; i < filterList.size(); i++) result.append(DEFAULT_SEPARATOR + " " + filterList.get(i));
	
		return result.toString();
		
	}
	
	public static String getCleanFilters (String filters, String separator) {
		
		if (filters == null) return "";
		else return filterListToString(separateFilters(filters, separator));
		
	}
	
	public static String getCleanFilters (String filters) {
		
		return getCleanFilters(filters, DEFAULT_SEPARATOR);
		
	}
	
	// Utility

	public static boolean isInt (String num) {
		
	    try { Integer.parseInt(num); return true; }
	    catch (NumberFormatException | NullPointerException e) { return false; }
	    
	}
	
	public static <V> List<V> getArray (V[] array) {
		
		if (array == null) return new ArrayList<>();
		else return Arrays.asList(array);
		
	}
	
}
