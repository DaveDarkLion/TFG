package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.admin_element.AAdminElementController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

@Controller
@RequestMapping("dificultades")
@Secured(Role.ADMIN)
public class DificultadController extends AAdminElementController <IRDificultad> {
	
	// Constants
	
	protected static final String TITLE = "Dificultad";
	
	// Functions
	
	// Mapping
	
	// Get Dificultad Edit Form
	
	@GetMapping(LINK_GENERIC_ID)
	@Transactional
	public ModelAndView getDificultadEditForm (@PathVariable int id) {	

		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRDificultad iRDificultad = iRDificultadDAO.find(id);
		
		if (!iRDificultad.isNull()) return getEditForm(TITLE, iRDificultad, true, iRDificultad.hasIREjercicios());
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non existent Dificultad with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get Dificultad New Form
	
	@GetMapping(LINK_ADD)
	public ModelAndView getDificultadNewForm () {
		
		// Return MAV
		
		return getNewForm(TITLE, true);
		
	}
	
	// Create Dificultad
	
	@PostMapping(LINK_ADD)
	@Transactional
	public ModelAndView createDificultad (@RequestParam("nombre") String viewNombre, @RequestParam("valor") String viewValor) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
			
			IRDificultad iRDificultad = IRFactory.newIRDificultad(Jsoup.parse(viewNombre).text(), toFloat(viewValor));
			
			iRDificultadDAO.save(iRDificultad);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created Dificultad with id: " + iRDificultad.getId());
			return getMessageView("Dificultad creada con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to create a new Dificultad and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Update Dificultad
	
	@PostMapping(LINK_GENERIC_ID)
	@Transactional
	public ModelAndView updateDificultad (@PathVariable int id, @RequestParam("nombre") String viewNombre, @RequestParam("valor") String viewValor) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRDificultad iRDificultad = iRDificultadDAO.find(id);
			
		if (!iRDificultad.isNull()) {	
		
			iRDificultad.setNombre(Jsoup.parse(viewNombre).text());
			iRDificultad.setValor(toFloat(viewValor));
			iRDificultadDAO.update(iRDificultad);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated Dificultad with id: " + iRDificultad.getId());
			return getMessageView("Dificultad actualizada con éxito", model);
			
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update a non existent Dificultad with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Delete Dificultad
	
	@PostMapping(LINK_GENERIC_ID + LINK_DELETE)
	@Transactional
	public ModelAndView deleteDificultad (@PathVariable int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRDificultad iRDificultad = iRDificultadDAO.find(id);
			
		if (!iRDificultad.isNull()) {	
			
			iRDificultadDAO.delete(iRDificultadDAO.find(id));
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted Dificultad with id: " + id);
			return getMessageView("Dificultad eliminada con éxito", model);
		
		}
		
		else {
		
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete a non existent Dificultad with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
}
