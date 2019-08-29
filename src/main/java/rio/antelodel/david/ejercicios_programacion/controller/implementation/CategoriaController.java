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
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;

@Controller
@RequestMapping("categorias")
@Secured(Role.ADMIN)
public class CategoriaController extends AAdminElementController <IRCategoria> {
	
	// Constants
	
	protected static final String TITLE = "Categoría";
	
	// DAOs
	
	@Autowired
	private IRCategoriaDAO iRCategoriaDAO;
	public void setIRCategoriaDAO (IRCategoriaDAO iRCategoriaDAO) { this.iRCategoriaDAO = iRCategoriaDAO; }
	
	// Functions
	
	// Mapping
	
	// Get Categoria Edit Form
	
	@GetMapping(LINK_GENERIC_ID)
	@Transactional
	public ModelAndView getCategoriaEditForm (@PathVariable int id) {	

		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRCategoria iRCategoria = iRCategoriaDAO.find(id);
		
		if (!iRCategoria.isNull()) return getEditForm(TITLE, iRCategoria, false, true);
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non existent Dificultad with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Get Categoria Edit Form
	
	@GetMapping(LINK_ADD)
	public ModelAndView getCategoriaNewForm () {
		
		// Return MAV
		
		return getNewForm(TITLE, false);
		
	}
	
	// Create Categoria
	
	@PostMapping(LINK_ADD)
	@Transactional
	public ModelAndView createCategoria (@RequestParam("nombre") String viewNombre) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
			
			IRCategoria iRCategoria = IRFactory.newIRCategoria(Jsoup.parse(viewNombre).text());
			
			iRCategoriaDAO.save(iRCategoria);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created Categoria with id: " + iRCategoria.getId());
			return getMessageView("Categoría creada con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to create a new Categoria and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Update Categoria
	
	@PostMapping(LINK_GENERIC_ID)
	@Transactional
	public ModelAndView updateCategoria (@PathVariable int id, @RequestParam("nombre") String viewNombre) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRCategoria iRCategoria = iRCategoriaDAO.find(id);
			
		if (!iRCategoria.isNull()) {	
		
			iRCategoria.setNombre(Jsoup.parse(viewNombre).text());
			iRCategoriaDAO.update(iRCategoria);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated Categoria with id: " + iRCategoria.getId());
			return getMessageView("Categoría actualizada con éxito", model);
			
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update a non existent Categoria with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Delete Categoria
	
	@PostMapping(LINK_GENERIC_ID + LINK_DELETE)
	@Transactional
	public ModelAndView deleteCategoria (@PathVariable int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRCategoria iRCategoria = iRCategoriaDAO.find(id);
			
		if (!iRCategoria.isNull()) {	
			
			iRCategoriaDAO.delete(iRCategoriaDAO.find(id));
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted Categoria with id: " + id);
			return getMessageView("Categoría eliminada con éxito", model);
		
		}
		
		else {
		
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete a non existent Categoria with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}
	
}
