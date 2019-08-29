package rio.antelodel.david.ejercicios_programacion.controller.implementation.container.admin_element;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerData.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;

public abstract class AAdminElementListController <T extends Comparable<T> & IParseable> extends AController {
		
	// Element names
	
	protected static final String NOMBRE_NAME = "nombre";
	protected static final String FILTER_NAME = "filter";
	protected static final String LINK_NOMBRE_NAME = "linkName";
	
	// Data names
	
	protected static final String DATA_NAME = "data";
	
	// Views
	
	protected static final String VIEW_LIST = "admin/admin-element-list";
		
	// Functions
	
	protected ModelAndView getListForm (String nombre, String linkNombre, String viewFilter, String viewPage, String viewPageSize, List<T> elementList) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// General values
		
		model.put(NOMBRE_NAME, nombre);
		model.put(FILTER_NAME,  IFilterHelper.getCleanFilters(viewFilter));
		model.put(LINK_NOMBRE_NAME,  linkNombre);
		
		// Sort
		
		Collections.sort(elementList);
		
		// Page data
				
		int page = getPageInfo(viewPage, 1);
		model.put(PAGE_NAME, Integer.toString(page));
		
		// Page size data
		
		int pageSize = getPageInfo(viewPageSize, PAGE_SIZE_DEFAULT);
		
		JSONObject pageSizeData = getPageSizeData(pageSize);
		model.put(PAGE_SIZES_DATA_NAME, pageSizeData.toString());
		
		// Page last data
		
		int pageLast = getPageLast(elementList, pageSize);
		model.put(PAGE_LAST_NAME, Integer.toString(pageLast));
		
		// Paged list
		
		elementList = getPagedList(elementList, page, pageSize);
		
		// List Data
		
		JSONArray data = getGenericListSortedData(elementList);
		model.put(DATA_NAME, data.toString());
		
		// Return MAV
		
		return getDefaultMAV(VIEW_LIST, model);
	
	}
	
}
