package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
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
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Controller
@RequestMapping("titulaciones")
@Secured(Role.ADMIN)
public class TitulacionController extends AAdminElementController <IRTitulacion> {
	
	// Constants
	
	protected static final String TITLE = "Titulación";
	
	// DAOs
	
	@Autowired
	private IRTitulacionDAO iRTitulacionDAO;
	public void setIRTitulacionDAO (IRTitulacionDAO iRTitulacionDAO) { this.iRTitulacionDAO = iRTitulacionDAO; }
	
	// Functions
	
	// Mapping
	
	// Get Titulacion Edit Form
	
	@GetMapping(LINK_GENERIC_ID)
	@Transactional
	public ModelAndView getTitulacionEditForm (@PathVariable int id) {	

		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRTitulacion iRTitulacion = iRTitulacionDAO.find(id);
		
		if (!iRTitulacion.isNull()) return getEditForm(TITLE, iRTitulacion, false, iRTitulacion.hasIRSets());
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non existent Dificultad with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get Titulacion New Form
	
	@GetMapping(LINK_ADD)
	public ModelAndView getTitulacionNewForm () {
		
		// Return MAV
		
		return getNewForm(TITLE, false);
		
	}
	
	// Create Titulacion
	
	@PostMapping(LINK_ADD)
	@Transactional
	public ModelAndView createTitulacion (@RequestParam("nombre") String viewNombre) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
			
			IRTitulacion iRTitulacion = IRFactory.newIRTitulacion(Jsoup.parse(viewNombre).text());
			
			iRTitulacionDAO.save(iRTitulacion);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created Titulacion with id: " + iRTitulacion.getId());
			return getMessageView("Titulación creada con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to create a new Titulacion and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Update Titulacion
	
	@PostMapping(LINK_GENERIC_ID)
	@Transactional
	public ModelAndView updateTitulacion (@PathVariable int id, @RequestParam("nombre") String viewNombre) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRTitulacion iRTitulacion = iRTitulacionDAO.find(id);
			
		if (!iRTitulacion.isNull()) {	
		
			iRTitulacion.setNombre(Jsoup.parse(viewNombre).text());
			iRTitulacionDAO.update(iRTitulacion);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated Titulacion with id: " + iRTitulacion.getId());
			return getMessageView("Titulación actualizada con éxito", model);
			
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update a non existent Titulacion with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Delete Titulacion
	
	@PostMapping(LINK_GENERIC_ID + LINK_DELETE)
	@Transactional
	public ModelAndView deleteTitulacion (@PathVariable int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRTitulacion iRTitulacion = iRTitulacionDAO.find(id);
			
		if (!iRTitulacion.isNull()) {	
			
			iRTitulacionDAO.delete(iRTitulacionDAO.find(id));
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted Titulacion with id: " + id);
			return getMessageView("Titulación eliminada con éxito", model);
		
		}
		
		else {
		
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete a non existent Titulacion with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
}
