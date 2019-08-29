package rio.antelodel.david.ejercicios_programacion.controller.implementation.container.admin_element;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;

public abstract class AAdminElementController <T extends IParseable> extends AController {

	// Element names
	
	protected static final String IS_DIFICULTAD_NAME = "isDificultad";
	
	// Data names
	
	protected static final String DATA_NAME = "data";
	
	// Views
	
	protected static final String VIEW_EDIT = "admin/admin-element-edit";
	
	// Functions
	
	public ModelAndView getEditForm (String title, T element, Boolean isDificultad, Boolean canDelete) {
	
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Edit Form
		
		fillDefaultInfo(model, title, isDificultad, false, canDelete);
		model.put(DATA_NAME, element.getFullData().toString());
		
		// Return MAV
		
		return getDefaultMAV(VIEW_EDIT, model);
		
	}
	
	public ModelAndView getNewForm (String title, Boolean isDificultad) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get New Form
		
		fillDefaultInfo(model, title, isDificultad, true, false);
		model.put(DATA_NAME, new JSONObject().toString());
		
		// Return MAV
		
		return getDefaultMAV(VIEW_EDIT, model);
		
	}
	
	// Fill default info
	
	protected void fillDefaultInfo (Map<String, String> model, String title, Boolean isDificultad, Boolean isNew, Boolean canDelete) {
		
		model.put(TITLE_NAME, title);
		model.put(IS_DIFICULTAD_NAME, ViewBoolean.toViewBoolean(isDificultad));
		model.put(IS_NEW_NAME, ViewBoolean.toViewBoolean(isNew));
		model.put(CAN_DELETE_NAME, ViewBoolean.toViewBoolean(canDelete));
		
	}
	
}
