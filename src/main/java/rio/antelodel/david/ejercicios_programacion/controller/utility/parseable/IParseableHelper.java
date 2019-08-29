package rio.antelodel.david.ejercicios_programacion.controller.utility.parseable;

import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IParseableHelper {
	
	// Functions
	
	// Data List
	
	public static <T extends IParseable> JSONArray getGenericListData (List<T> list) {
		
		JSONArray data = new JSONArray();
			
		for(T element : list) data.put(element.getFullData());
			
		return data;
			
	}
	
	public static <T extends U, U extends IParseable & Comparable<U>> JSONArray getGenericListSortedData (List<T> list) {
			
		Collections.sort(list);
		return getGenericListData(list);
			
	}
	
	// Get nullable Data
	
	public static <T extends IRichEntity<?> & IParseable> JSONObject getNullableData (T entity) {
			
		if (entity.isNull()) return new JSONObject();
		else return entity.getFullData();
			
	}
	
}
