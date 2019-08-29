package rio.antelodel.david.ejercicios_programacion.controller.utility;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class ControllerViews {

	// Private Constructor
	
	private ControllerViews() { }
	
	// Names
	
	public static final String MESSAGE_NAME = "message";
	public static final String ERROR_NAME = "error";
	
	// Messages
	
	public static final String GENERIC_ERROR_MESSAGE = "Su solicitud no ha podido ser procesada debido a un error";
	
	// Views
	
	public static final String VIEW_MESSAGE = "message/message";
	public static final String VIEW_ERROR = "error/error";
	
	// Functions
	
	public static ModelAndView getDefaultMAV (String viewName, Map<String, String> model) {
		
		return new ModelAndView(viewName, MODEL_NAME, model);
		
	}
	
	public static ModelAndView getMessageView (String message, Map<String, String> model) {
		
		model.put(MESSAGE_NAME, message);
	
		return getDefaultMAV(VIEW_MESSAGE, model);
		
	}
	
	public static ModelAndView getErrorView (String error, Map<String, String> model) {
		
		model.put(ERROR_NAME, error);
		
		return getDefaultMAV(VIEW_ERROR, model);
		
	}
	
	public static ModelAndView getGenericErrorView (Map<String, String> model) {
		
		return getErrorView(GENERIC_ERROR_MESSAGE, model);
		
	}
	
	public static ModelAndView getAccessDeniedView () {
		
		return new ModelAndView(new RedirectView("/ejercicios-programacion/access-denied.html"));
		
	}
	
}
