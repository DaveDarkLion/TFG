package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
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
import org.springframework.web.servlet.view.RedirectView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.ejercicio.AEjercicioController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler.IArchivoHandlerConfig;
import rio.antelodel.david.ejercicios_programacion.controller.utility.archivo_handler.IArchivoHandler;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRArchivoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadAlumnoEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@Controller
@RequestMapping("/ejercicios")
@Secured ({ Role.ALUMNO, Role.PROFESOR, Role.ADMIN })
public class EjercicioController extends AEjercicioController {
	
	// Ejercicio Data Structure
	
	protected class SEjercicioData {
	
		protected boolean visible = false;
		protected String titulo;
		protected String enunciado;
		protected IRProfesor iRProfesor;
		protected IRDificultad iRDificultad;
		protected Set<IRCategoria> iRCategorias = new HashSet<>();
		protected Set<IRArchivo> iRArchivosEntrada = new HashSet<>();
		protected List<File> archivoEntradaFiles = new ArrayList<>();
		protected Set<IRArchivo> iRArchivosValidacion = new HashSet<>();
		protected List<File> archivoValidacionFiles = new ArrayList<>();
		protected Set<IRArchivo> iRArchivosSolucion = new HashSet<>();
		protected List<File> archivoSolucionFiles = new ArrayList<>();

	}
	
	protected static final String CODIFICATION = "UTF-8";
	
	// Element Names
	
	protected static final String ENUNCIADO_NAME = "enunciado";
	protected static final String DIFICULTAD_ALUMNO_VOTES_NAME = "dificultadAlumnoVotes";
	protected static final String IN_CART_NAME = "inCart";
	
	// Data names
	
	protected static final String EJERCICIO_DATA_NAME = "ejercicioData";
	protected static final String DIFICULTAD_ALUMNO_DATA_NAME = "dificultadAlumnoData";
	protected static final String DIFICULTAD_ALUMNO_AVERAGE_DATA_NAME = "dificultadAlumnoAverageData";
	
	// DAOs
	
	@Autowired
	protected IRAlumnoDAO iRAlumnoDAO;
	public void setIRAlumnoDAO (IRAlumnoDAO iRAlumnoDAO) { this.iRAlumnoDAO = iRAlumnoDAO; }
	
	@Autowired
	protected IRArchivoDAO iRArchivoDAO;
	public void setIRArchivoDAO (IRArchivoDAO iRArchivoDAO) { this.iRArchivoDAO = iRArchivoDAO; }
	
	@Autowired
	protected IRDificultadAlumnoEjercicioDAO iRDaeDAO;
	public void setIRDificultadAlumnoEjercicioDAO (IRDificultadAlumnoEjercicioDAO iRDaeDAO) { this.iRDaeDAO = iRDaeDAO; }
	
	@Autowired
	protected IREjercicioPersonaDAO iREjercicioPersonaDAO;
	public void setIREjercicioPersonaDAO (IREjercicioPersonaDAO iREjercicioPersonaDAO) { this.iREjercicioPersonaDAO = iREjercicioPersonaDAO; }
	
	@Autowired
	private IRIdeaDAO iRIdeaDAO;
	public void setIRIdeaDAO (IRIdeaDAO iRIdeaDAO) { this.iRIdeaDAO = iRIdeaDAO; }
	
	// Handlers
	
	@Autowired
	public IArchivoHandler iArchivoHandler;
	public void setiArchivoHandler(IArchivoHandler customFileHandler) { this.iArchivoHandler = customFileHandler; }
	
	// Views
	
	protected static final String VIEW_EJERCICIO_VIEW = VIEW_MAIN_PATH + "ejercicio-edit/ejercicio-view";
	protected static final String VIEW_EJERCICIO_EDIT = VIEW_MAIN_PATH + "ejercicio-edit/ejercicio-edit";
	
	// Functions
	
	// Mappings
	
	// Get Ejercicio Edit
	
	@GetMapping(LINK_GENERIC_ID + "/edit")
	@Transactional
	public ModelAndView getEjercicioEditForm (@PathVariable("id") int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
			
			if (userIsPrivileged()) {
			
				// Ejercicio Data
				
				IREjercicio iREjercicio = iREjercicioDAO.find(id);
				model.put(EJERCICIO_DATA_NAME, iREjercicio.getFullData().toString());
				
				// Not new
				
				model.put(IS_NEW_NAME, ViewBoolean.FALSE);
				
				// Dificultades
				
				JSONArray dificultadesData = getGenericListSortedData(iRDificultadDAO.getAll());
				model.put(DIFICULTADES_DATA_NAME, dificultadesData.toString());
				model.put(DIFICULTAD_CURRENT_DATA_NAME, iREjercicio.getIRDificultad().getFullData().toString());
				model.put(DIFICULTAD_ANY_NAME, ViewBoolean.FALSE);
				
				// RDificultad average data
				
				IRDificultad iRDificultadAverage = getDificultadAverageEjercicio(iREjercicio);
				model.put(DIFICULTAD_ALUMNO_AVERAGE_DATA_NAME, iRDificultadAverage.getFullData().toString());
				model.put(DIFICULTAD_ALUMNO_VOTES_NAME, Integer.toString(getAverageDificultadVotes(iREjercicio)));
				
				// Categorias Current
				
				JSONArray categoriasCurrentData = getGenericListSortedData(iREjercicio.getIRCategorias());
				model.put(CATEGORIAS_CURRENT_DATA_NAME, categoriasCurrentData.toString());
					
				// Profesores
					
				JSONArray profesoresData = getGenericListSortedData(iRProfesorDAO.getAll());
				model.put(PROFESORES_DATA_NAME, profesoresData.toString());
				model.put(PROFESOR_CURRENT_DATA_NAME, iREjercicio.getIRProfesor().getFullData().toString());
				model.put(PROFESOR_ANY_NAME, ViewBoolean.FALSE);
				
				// Categorias
				
				JSONArray categoriasData = getGenericListSortedData(iRCategoriaDAO.getAll());
				model.put(CATEGORIAS_DATA_NAME, categoriasData.toString());
				
				// Final view
				
				return getDefaultMAV(VIEW_EJERCICIO_EDIT, model);
				
			}
			
			else {
				
				CustomLogger.LOGGER.info(CustomLogger.USER + getUser().getEmail() + " tried to edit information of an Ejercicio as a non privileged user");
				return getAccessDeniedView();
				
			}
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to edit information of Ejercicio with id: " + id + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
				
	}
	
	@GetMapping(LINK_GENERIC_ID + "/view")
	@Transactional
	public ModelAndView getEjercicioViewForm (@PathVariable("id") int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Ejercicio
		
		IREjercicio iREjercicio = iREjercicioDAO.find(id);
		
		try {
			
			if (ejercicioIsVisible(iREjercicio)) {
			
				// Ejercicio Data
				
				model.put(EJERCICIO_DATA_NAME, iREjercicio.getFullData().toString());
				
				// In Cart
				
				model.put(IN_CART_NAME, ViewBoolean.toViewBoolean(getUser().getIREjercicios().contains(iREjercicio)));
				
				// Dificultades
				
				JSONArray dificultadesData = getGenericListSortedData(iRDificultadDAO.getAll());
				model.put(DIFICULTADES_DATA_NAME, dificultadesData.toString());
				model.put(DIFICULTAD_CURRENT_DATA_NAME, iREjercicio.getIRDificultad().getFullData().toString());
				model.put(DIFICULTAD_ANY_NAME, ViewBoolean.FALSE);
				
				// RDificultad average data
				
				IRDificultad iRDificultadAverage = getDificultadAverageEjercicio(iREjercicio);
				model.put(DIFICULTAD_ALUMNO_AVERAGE_DATA_NAME, iRDificultadAverage.getFullData().toString());
				model.put(DIFICULTAD_ALUMNO_VOTES_NAME, Integer.toString(getAverageDificultadVotes(iREjercicio)));
				
				// Categorias Current
				
				JSONArray categoriasCurrentData = getGenericListSortedData(iREjercicio.getIRCategorias());
				model.put(CATEGORIAS_CURRENT_DATA_NAME, categoriasCurrentData.toString());
				
				// Dificultad Alumno current
				
				if (userIsRole(Role.ALUMNO)) model.put(DIFICULTAD_ALUMNO_DATA_NAME, getDificultadAlumnoData(iREjercicio).toString());
				
				return getDefaultMAV(VIEW_EJERCICIO_VIEW, model);
				
			}
			
			else {
				
				CustomLogger.LOGGER.info(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non visible Ejercicio as a non privileged user");
				return getAccessDeniedView();
				
			}
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from Ejercicio with id: " + id + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	protected boolean ejercicioIsVisible (IREjercicio iREjercicio) {
		
		return userIsPrivileged() || iREjercicio.isVisibleForUnprivileged();
		
	}
	
	// Get Ejercicio add screen
	
	@GetMapping(LINK_ADD)
	@Secured({ Role.PROFESOR })
	public ModelAndView getEjercicioNewForm (@RequestParam(value="idea", required=false) String viewIdea) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// New
		
		model.put(IS_NEW_NAME, ViewBoolean.TRUE);
		
		// Ejercicio data
		
		model.put(EJERCICIO_DATA_NAME, new JSONObject().toString());
		
		// Enunciado
		
		IRIdea iRIdea = iRIdeaDAO.find(toInt(viewIdea, -1));
		if (!iRIdea.isNull()) model.put(ENUNCIADO_NAME, iRIdea.getIdeaText());
		
		// Profesores data
		
		// Profesores
		
		JSONArray profesoresData = getGenericListSortedData(iRProfesorDAO.getAll());
		model.put(PROFESORES_DATA_NAME, profesoresData.toString());
		model.put(PROFESOR_ANY_NAME, ViewBoolean.FALSE);
		if (userIsRole(Role.PROFESOR)) model.put(PROFESOR_CURRENT_DATA_NAME, iRProfesorDAO.find(getUser().getEmail()).getFullData().toString());
		else model.put(PROFESOR_CURRENT_DATA_NAME, new JSONObject().toString());
					
		// RDificultad data
		
		JSONArray dificultadesData = getGenericListSortedData(iRDificultadDAO.getAll());	
		model.put(DIFICULTADES_DATA_NAME, dificultadesData.toString());
		model.put(DIFICULTAD_ANY_NAME, ViewBoolean.FALSE);
		model.put(DIFICULTAD_CURRENT_DATA_NAME, new JSONObject().toString());
					
		// Categoria Checkbox Collection
		
		JSONArray categoriasData = getGenericListSortedData(iRCategoriaDAO.getAll());
		model.put(CATEGORIAS_DATA_NAME,  categoriasData.toString());
		model.put(CATEGORIAS_CURRENT_DATA_NAME, new JSONObject().toString());
		
		// Return MAV
		
		return new ModelAndView(VIEW_EJERCICIO_EDIT, MODEL_NAME, model);
		
	}
	
	// Add Ejercicio
	
	@PostMapping(LINK_ADD)
	@Secured({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView createEjercicio (HttpServletRequest request) throws IOException, ServletException {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
			 
		try {
		
			// Get Ejercicio Data
			
			Iterator<Part> iterator = request.getParts().iterator();
			SEjercicioData ejercicioData = parseData(iterator);
			
			// Create REjercicio and update
			
			IREjercicio iREjercicio = IRFactory.newIREjercicio(ejercicioData.titulo, ejercicioData.enunciado, ejercicioData.iRProfesor, ejercicioData.iRDificultad, true);
			
			iREjercicioDAO.save(iREjercicio);
			iArchivoHandler.makeDirs(iREjercicio);
			
			updateEjercicio(iREjercicio, ejercicioData);
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created Ejercicio with id: " + iREjercicio.getId());
			
			// Return MAV
			
			return getMessageView("Ejercicio creado con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to create a new Ejercicio and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Update Ejercicio
	
	@PostMapping(LINK_GENERIC_ID + "/edit")
	@Transactional
	public ModelAndView updateEjercicioEdit (	@PathVariable("id") int id, HttpServletRequest request,
												@RequestParam (value="dificultad_alumno_id", required = false) String viewDificultadAlumno) throws IOException, ServletException {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Ejercicio Data
		
		IREjercicio iREjercicio = iREjercicioDAO.find(id);
		
		try {
			
			if (userIsPrivileged()) return updateEjercicioPrivileged(iREjercicio, request);
			else return getAccessDeniedView();
			
		}
		
		catch (Exception e) {
		
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update Ejercicio with id: " + id + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
			
	}
	
	@PostMapping(LINK_GENERIC_ID + "/view")
	@Transactional
	public ModelAndView updateEjercicioView (	@PathVariable("id") int id, HttpServletRequest request,
												@RequestParam (value="dificultad_alumno_id", required = false) String viewDificultadAlumno) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Ejercicio Data
		
		IREjercicio iREjercicio = iREjercicioDAO.find(id);
		
		try {
			
			if (userIsRole(Role.ALUMNO) && ejercicioIsVisible(iREjercicio)) return updateEjercicioAlumno(iREjercicio, viewDificultadAlumno);
			else return getAccessDeniedView();
			
		}
		
		catch (Exception e) {
		
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update Ejercicio with id: " + id + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
			
	}
	
	// Update Ejercicio for Alumno
	
	private ModelAndView updateEjercicioAlumno (IREjercicio iREjercicio, String dificultadAlumno) {
			
		// Update vote
		
		updateAlumnoVote(iREjercicio, dificultadAlumno);
		
		CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated Ejercicio with id: " + iREjercicio.getId());
		
		// Return MAV
		
		return new ModelAndView("redirect:/ejercicios/" + iREjercicio.getId() + "/view.html");
		
	}
	
	// Update Alumno vote
	
	private void updateAlumnoVote (IREjercicio iREjercicio, String viewDificultadAlumno) {
	
		// Get DAE Data
		
		IRAlumno iRAlumno = iRAlumnoDAO.find(getUser().getEmail());
		IRDificultadAlumnoEjercicio iRDae = iRDaeDAO.find(iRAlumno, iREjercicio);
		
		if (isValid(viewDificultadAlumno, -1)) {
		
			IRDificultad iRDificultad = iRDificultadDAO.find(Integer.parseInt(viewDificultadAlumno));
			
			if (!iRDae.isNull()) {
				
				iRDae.setIRDificultad(iRDificultad);
				iRDaeDAO.update(iRDae);
				
			}
			
			else {
			
				iRDae = IRFactory.newIRDificultadAlumnoEjercicio(iRAlumno, iREjercicio, iRDificultad);
				iRDaeDAO.save(iRDae);
				
			}
			
		}
		
		else if (!iRDae.isNull()) iRDaeDAO.delete(iRDae);
		
	}
	
	// Update Ejercicio for Privileged
	
	private ModelAndView updateEjercicioPrivileged (IREjercicio iREjercicio, HttpServletRequest request) throws IOException, ServletException {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);

		// Get REjercicio data
		
		Iterator<Part> iterator = request.getParts().iterator();
		SEjercicioData ejercicioData = parseData(iterator);
		
		// Update REjercicio
		
		updateEjercicio(iREjercicio, ejercicioData);
	            
		CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated Ejercicio with id: " + iREjercicio.getId());
		
		// Return MAV
		
		return getMessageView("Ejercicio actualizado con éxito", model);
		
	}
	
	// Update Ejercicio
	
	private void updateEjercicio (IREjercicio iREjercicio, SEjercicioData ejercicioData) {
		
		iREjercicio.setVisible(ejercicioData.visible);
		iREjercicio.setTitulo(ejercicioData.titulo);
		iREjercicio.setEnunciado(ejercicioData.enunciado);
		iREjercicio.setIRProfesor(ejercicioData.iRProfesor);
		iREjercicio.setIRDificultad(ejercicioData.iRDificultad);
		iREjercicio.setIRCategorias(ejercicioData.iRCategorias);
		
		// Paths
		
		final String pathRoot = IArchivoHandlerConfig.PATH_MAIN + File.separator + iREjercicio.getId();
		
		final String archivoEntradaPathRoot = pathRoot + File.separator + IArchivoHandlerConfig.PATH_ENTRADA;
		final String archivoValidacionPathRoot = pathRoot + File.separator + IArchivoHandlerConfig.PATH_VALIDACION;
		final String archivoSolucionPathRoot = pathRoot + File.separator + IArchivoHandlerConfig.PATH_SOLUCION;
		
		//Clear Files
		
		cleanArchivoFiles(new ArrayList<>(ejercicioData.iRArchivosEntrada), iREjercicio.getIRArchivosEntrada());
		cleanArchivoFiles(new ArrayList<>(ejercicioData.iRArchivosValidacion), iREjercicio.getIRArchivosValidacion());
		cleanArchivoFiles(new ArrayList<>(ejercicioData.iRArchivosSolucion), iREjercicio.getIRArchivosSolucion());
		
		// Archivos entrada
		
		for (File file : ejercicioData.archivoEntradaFiles) {
			IRArchivo iRArchivo = iArchivoHandler.getArchivoFromFile(file, archivoEntradaPathRoot);
			if (iRArchivo != null) {
				iRArchivoDAO.save(iRArchivo);
				ejercicioData.iRArchivosEntrada.add(iRArchivo);		
			}
		}
		
		iREjercicio.setIRArchivosEntrada(ejercicioData.iRArchivosEntrada);
					
		// Archivos validación
					
		for (File file : ejercicioData.archivoValidacionFiles) {
			IRArchivo iRArchivo = iArchivoHandler.getArchivoFromFile(file, archivoValidacionPathRoot);
			if (iRArchivo != null) {
				iRArchivoDAO.save(iRArchivo);
				ejercicioData.iRArchivosValidacion.add(iRArchivo);	
			}
		}
		
		iREjercicio.setIRArchivosValidacion(ejercicioData.iRArchivosValidacion);
					
		// Archivos solución
					
		for (File file : ejercicioData.archivoSolucionFiles) {
			IRArchivo iRArchivo = iArchivoHandler.getArchivoFromFile(file, archivoSolucionPathRoot);
			if (iRArchivo != null) {
				iRArchivoDAO.save(iRArchivo);
				ejercicioData.iRArchivosSolucion.add(iRArchivo);
			}
		}
		
		iREjercicio.setIRArchivosSolucion(ejercicioData.iRArchivosSolucion);
		
		// Update
		
		iREjercicioDAO.update(iREjercicio);
		
	}
	
	// Clean Files
	
	protected void cleanArchivoFiles (List<IRArchivo> archivosCurrent, List<IRArchivo> archivosOld) {
		
		for (IRArchivo archivo : archivosOld) if (!archivosCurrent.contains(archivo)) iArchivoHandler.deleteArchivoFile(archivo);
		
	}
	
	// Update Cart
	
	@PostMapping(LINK_GENERIC_ID + "/cart")
	@Transactional
	public ModelAndView updateCart (@PathVariable int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
		
			// Get data
			
			IRPersona iRPersona = getUser();
			
			IREjercicio iREjercicio = iREjercicioDAO.find(id);
			
			// Update cart
			
			updatePersonaCart(iREjercicio, iRPersona);
			
			// Return MAV
			
			return new ModelAndView(new RedirectView("/ejercicios-programacion/ejercicios/" + Integer.toString(id) + "/view.html"));
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete Ejercicio with id: " + id + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	protected void updatePersonaCart (IREjercicio iREjercicio, IRPersona iRPersona) {
		
		IREjercicioPersona iREjercicioPersona = iREjercicioPersonaDAO.find(iREjercicio, iRPersona);
		
		if (iREjercicioPersona.isNull()) iREjercicioPersonaDAO.save(IRFactory.newIREjercicioPersona(iREjercicio, iRPersona, iRPersona.getLastPosition(iRPersona.getIREjerciciosSet())));
		else iREjercicioPersonaDAO.delete(iREjercicioPersona);
		
	}
	
	// Delete Ejercicio
	
	@PostMapping(LINK_GENERIC_ID + LINK_DELETE)
	@Secured({ Role.PROFESOR, Role.ADMIN })
	@Transactional
	public ModelAndView deleteEjercicio (@PathVariable("id") int id) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		IREjercicio iREjercicio = iREjercicioDAO.find(id);
		
		try {
		
			iArchivoHandler.deleteDirs(iREjercicio);
			iREjercicioDAO.delete(iREjercicio);
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted Ejercicio with id: " + id);
			return getMessageView("Ejercicio eliminado con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete Ejercicio with id: " + id + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Parse Data
	
	private SEjercicioData parseData (Iterator<Part> iterator) throws IOException {
	
		// Constants
		
		final String codification = CODIFICATION;
		
		SEjercicioData dataStructure = new SEjercicioData();
		
		String path = IArchivoHandlerConfig.PATH_TEMP;
		Files.createDirectories(Paths.get(path));
		
		// Loop
		
		while (iterator.hasNext()) {
			
			Part item = iterator.next();

			String pathEntrada = path + File.separator + IArchivoHandlerConfig.PATH_ENTRADA + File.separator + item.getSubmittedFileName();
			String pathValidacion = path + File.separator + IArchivoHandlerConfig.PATH_VALIDACION + File.separator + item.getSubmittedFileName();
			String pathSolucion = path + File.separator + IArchivoHandlerConfig.PATH_SOLUCION + File.separator + item.getSubmittedFileName();
			
			switch (item.getName()) {
			
				case "visible" 					: dataStructure.visible = true; break;
				case "titulo" 					: dataStructure.titulo = IOUtils.toString(item.getInputStream(), codification); break;
				case "enunciado" 				: dataStructure.enunciado = IOUtils.toString(item.getInputStream(), codification); break;
				case "profesor_email" 			: dataStructure.iRProfesor = iRProfesorDAO.find(IOUtils.toString(item.getInputStream(), codification)); break;
				case "dificultad_id" 			: dataStructure.iRDificultad = iRDificultadDAO.find(Integer.parseInt(IOUtils.toString(item.getInputStream(), codification))); break;
				case "categorias_id[]" 			: dataStructure.iRCategorias.add(iRCategoriaDAO.find(Integer.parseInt(IOUtils.toString(item.getInputStream(), codification)))); break;
				
				case "archivos_entrada_id"		: dataStructure.iRArchivosEntrada.add(iRArchivoDAO.find(Integer.parseInt(IOUtils.toString(item.getInputStream(), codification)))); break;
				case "archivos_entrada_files"	: 
					
					if (isValid(item.getSubmittedFileName())) {
						
						FileUtils.copyInputStreamToFile(item.getInputStream(), new File(pathEntrada));
						dataStructure.archivoEntradaFiles.add(new File(pathEntrada));
						
					}
						
					break;
				
				case "archivos_validacion_id"	: dataStructure.iRArchivosValidacion.add(iRArchivoDAO.find(Integer.parseInt(IOUtils.toString(item.getInputStream(), codification)))); break;
				case "archivos_validacion_files":
					
					if (isValid(item.getSubmittedFileName())) {
						
						FileUtils.copyInputStreamToFile(item.getInputStream(), new File(pathValidacion));
						dataStructure.archivoValidacionFiles.add(new File(pathValidacion));
						
					}
					
					break;
				
				case "archivos_solucion_id"		: dataStructure.iRArchivosSolucion.add(iRArchivoDAO.find(Integer.parseInt(IOUtils.toString(item.getInputStream(), codification)))); break;
				case "archivos_solucion_files"	: 	
				
					if (isValid(item.getSubmittedFileName())) {
						
						FileUtils.copyInputStreamToFile(item.getInputStream(), new File(pathSolucion));
						dataStructure.archivoSolucionFiles.add(new File(pathSolucion));
						
					}
					
					break;
					
				default : break;
				
			}
			
		}
		
	return dataStructure;
		
	}
	
	// Get Dificultad Alumno for Ejercicio
	
	protected JSONObject getDificultadAlumnoData (IREjercicio iREjercicio) {
		
		final IRAlumno iRAlumno = iRAlumnoDAO.find(getUser().getEmail());
				
		IRDificultadAlumnoEjercicio iRDae = iRDaeDAO.find(iRAlumno, iREjercicio);
		
		if (!iRDae.isNull()) return iRDae.getIRDificultad().getFullData();
		else return new JSONObject();
		
	}
	
	// Get Dificultad Average
	
	private IRDificultad getDificultadAverageEjercicio (IREjercicio iREjercicio) {
		
		List<IRDificultad> iRDificultadList = new ArrayList<>();
		
		for (IRDificultadAlumnoEjercicio iRDae : iREjercicio.getIRDificultadAlumnoEjercicio()) iRDificultadList.add(iRDae.getIRDificultad()); 
		
		return getRDificultadAverage(iRDificultadList);
		
	}
	
	// Get vote number
	
	private int getAverageDificultadVotes (IREjercicio iREjercicio) {
		
		int result = 0;
		
		for (IRDificultadAlumnoEjercicio iRDae : iRDaeDAO.getAll()) {
			
			if (iREjercicio.equals(iRDae.getIREjercicio())) result++;
			
		}
		
		return result;
		
	}
	
}
