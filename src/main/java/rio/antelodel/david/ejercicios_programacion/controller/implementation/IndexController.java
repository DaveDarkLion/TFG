package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;

@Controller
public class IndexController extends AController {

	// Views
	
	protected static final String VIEW_INDEX = "index";
	
	// Functions
	
	// Mapping
	
	@GetMapping({ "/", "/index" })
	public ModelAndView getIndexForm () {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Return MAV
		
		return getDefaultMAV(VIEW_INDEX, model);
		
	}
	
}
