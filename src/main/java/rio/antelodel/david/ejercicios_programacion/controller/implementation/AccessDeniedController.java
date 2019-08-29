package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;

@Controller
@RequestMapping("/access-denied")
public class AccessDeniedController extends AController {
	
	// Functions
	
	// Mapping
	
	@GetMapping
	public ModelAndView getAccessDeniedForm () {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		if (!getUser().isNull()) setup(model);
		
		return getErrorView("No tiene permiso para acceder a este recurso", model);
		
		
	}
	
}
