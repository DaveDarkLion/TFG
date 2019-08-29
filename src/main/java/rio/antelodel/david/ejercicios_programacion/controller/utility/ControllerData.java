package rio.antelodel.david.ejercicios_programacion.controller.utility;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;

public class ControllerData {

	// Private Constructor
	
	private ControllerData () { }
	
	// Names
	
	protected static final String CURRENT_ELEMENT_NAME = "current";
	protected static final String LIST_ELEMENT_NAME =  "list";
	
	protected static final String FIRST_ELEMENT_NAME =  "id";
	protected static final String SECOND_ELEMENT_NAME =  "name";
	
	// Functions
	
	// Generic Data
	
	public static <T, U> JSONArray getGenericData (List<Pair<T, U>> entities) {
		
		JSONArray listData = new JSONArray();
		
		for (Pair<T, U> p : entities) {
			
			JSONObject currentEntityListData = new JSONObject();
			
			currentEntityListData.put(FIRST_ELEMENT_NAME, p.getFirst());
			currentEntityListData.put(SECOND_ELEMENT_NAME, p.getSecond());
			
			listData.put(currentEntityListData);
			
		}
		
		return listData;
		
	}
	
	public static <T, U> JSONObject getGenericData (Pair<T, U> currentEntity, List<Pair<T, U>> entities) {
		
		// Current
		
		JSONObject currentData = new JSONObject();
		currentData.put(FIRST_ELEMENT_NAME, currentEntity.getFirst());
		currentData.put(SECOND_ELEMENT_NAME, currentEntity.getSecond());

		// List
		
		JSONArray listData = getGenericData(entities);
		
		// Final JSONObject
		
		JSONObject genericData = new JSONObject();
		
		genericData.put(CURRENT_ELEMENT_NAME, currentData);
		genericData.put(LIST_ELEMENT_NAME, listData);
		
		return genericData;
		
	}
	
	// Page Size Data
	
	public static JSONObject getPageSizeData (Integer currentPageSize) {
			
		return getGenericData(new Pair<Integer, Integer>(currentPageSize), getPageSizeList());
		
	}
	
}
