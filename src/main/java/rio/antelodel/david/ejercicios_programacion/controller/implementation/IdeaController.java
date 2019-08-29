package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;

@Controller
@RequestMapping("/ideas")
@Secured(Role.PROFESOR)
public class IdeaController extends AController {
	
	// Constants
	
	protected static final String TITLE = "Idea";
	
	// Element names
	
	protected static final String ID_NAME = "id";
	
	// Data names
	
	protected static final String PROFESOR_DATA = "profesorData";
	protected static final String DATA_NAME = "data";
	
	// Views
	
	protected static final String VIEW_IDEA_EDIT = "idea/idea-edit";
	
	// DAOs
	
	@Autowired
	private IRIdeaDAO iRIdeaDAO;
	public void setIRIdeaDAO (IRIdeaDAO iRIdeaDAO) { this.iRIdeaDAO = iRIdeaDAO; }
	
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	public void setIRProfesorDAO (IRProfesorDAO iRProfesorDAO) { this.iRProfesorDAO = iRProfesorDAO; }
	
	// Functions
	
	// Mapping
	
	// Get Idea Edit Form
	
	@GetMapping(LINK_GENERIC_ID)
	@Transactional
	public ModelAndView getIdeaEditForm (@PathVariable("id") int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRIdea iRIdea = iRIdeaDAO.find(id);
		
		if (!iRIdea.isNull()) {
		
			model.put(ID_NAME, Integer.toString(id));
			model.put(TITLE_NAME, TITLE);
			model.put(IS_NEW_NAME, ViewBoolean.FALSE);
			model.put(DATA_NAME, iRIdea.getFullData().toString());
			
			return getDefaultMAV(VIEW_IDEA_EDIT, model);
			
		}
		
		else {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non existent Idea with id: " + id);
			return getGenericErrorView(model);
			
		}
		
	}

	// Get Idea New Form
	
	@GetMapping(LINK_ADD)
	public ModelAndView getIdeaNewForm () {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		model.put(TITLE_NAME,  TITLE);
		model.put(IS_NEW_NAME,  ViewBoolean.TRUE);
		model.put(PROFESOR_DATA,  iRProfesorDAO.find(getUser().getEmail()).getData().toString());
		model.put(DATA_NAME, new JSONObject().toString());
		
		// Return MAV
		
		return getDefaultMAV(VIEW_IDEA_EDIT, model);
		
	}
	
	// Create Idea
	
	@PostMapping(LINK_ADD)
	@Transactional
	public ModelAndView createIdea (@RequestParam(value="nombre") String viewNombre, @RequestParam(value="idea_text") String viewIdea) {
	
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
	
			IRIdea iRIdea = IRFactory.newIRIdea(Jsoup.parse(viewNombre).text(),
												Jsoup.clean(viewIdea, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)),
												iRProfesorDAO.find(getUser().getEmail()));
			
			iRIdeaDAO.save(iRIdea);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created a new Idea with id: " + iRIdea.getId());
			return getMessageView("Idea añadida con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to create Idea and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Update Idea
	
	@PostMapping(LINK_GENERIC_ID)
	@Transactional
	public ModelAndView updateIdea (@PathVariable("id") int id, @RequestParam(value="nombre") String viewNombre, @RequestParam(value="idea_text") String viewIdeaText) {
	
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRIdea iRIdea = iRIdeaDAO.find(id);
		
		try {
		
			iRIdea.setNombre(Jsoup.parse(viewNombre).text());
			iRIdea.setIdeaText(Jsoup.clean(viewIdeaText, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false)));
			iRIdeaDAO.update(iRIdea);
			
			
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated Idea with id: " + iRIdea.getId());
			return getMessageView("Idea editada con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update Idea with id: " + id + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Delete Idea
	
	@PostMapping(LINK_GENERIC_ID + LINK_DELETE)
	public ModelAndView deleteIdea (@PathVariable("id") int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IRIdea iRIdea = iRIdeaDAO.find(id);
		
		try {
		
			iRIdeaDAO.delete(iRIdea);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted Idea with id: " + id);
			return getMessageView("Idea eliminada con éxito", model);
			
		}
	
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete Idea with id: " + id + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
}