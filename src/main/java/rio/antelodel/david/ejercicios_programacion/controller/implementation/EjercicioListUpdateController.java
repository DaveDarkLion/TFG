package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.ejercicio.AEjercicioController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.set_type.SetType;
import rio.antelodel.david.ejercicios_programacion.controller.set_type.SetType.Type;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;

@Controller
@RequestMapping("/ejercicios")
@Secured ({ Role.ALUMNO, Role.PROFESOR, Role.ADMIN })
public class EjercicioListUpdateController extends AEjercicioController {
	
	// Constants
	
	protected static final String URL_REDIRECT_DEFAULT = "ejercicios.html";
	
	// DAOs
	
	@Autowired
	private IREjercicioPersonaDAO iREjercicioPersonaDAO;
	public void setIREjercicioPersonaDAO (IREjercicioPersonaDAO iREjercicioPersonaDAO) { this.iREjercicioPersonaDAO = iREjercicioPersonaDAO; }
	
	@Autowired
	private IREjercicioExamenDAO iREjercicioExamenDAO;
	public void setIREjercicioExamenDAO (IREjercicioExamenDAO iREjercicioExamenDAO) { this.iREjercicioExamenDAO = iREjercicioExamenDAO; }
	
	@Autowired
	private IREjercicioPracticaDAO iREjercicioPracticaDAO;
	public void setIREjercicioPracticaDAO (IREjercicioPracticaDAO iREjercicioPracticaDAO) { this.iREjercicioPracticaDAO = iREjercicioPracticaDAO; }
	
	@Autowired
	private IREjercicioPracticaEvaluacionDAO iREjercicioPracticaEvaluacionDAO;
	public void setIREjercicioPracticaEvaluacionDAO (IREjercicioPracticaEvaluacionDAO iREjercicioPracticaEvaluacionDAO) { this.iREjercicioPracticaEvaluacionDAO = iREjercicioPracticaEvaluacionDAO; }
	
	// Functions
	
	// Mapping
	
	// Update set
	
	@PostMapping
	@Transactional
	public ModelAndView updateSet (	HttpServletRequest request,
									@RequestParam (value="criteria", required=false) String criteria,
									@RequestParam (value="reverse", required=false) String reverse,
									@RequestParam (value="cart_ejercicio_id", required=false) String viewCartEjercicio,
									@RequestParam (value="move_id", required=false) String viewMoveId,
									@RequestParam (value="move_up", required=false) String viewMoveUp,
									@RequestParam (value="move_down", required=false) String viewMoveDown,
									@RequestParam (value="filter_ejercicio", required=false) String viewFilterEjercicio,
									@RequestParam (value="profesor_email", required=false) String viewFilterProfesor,
									@RequestParam (value="dificultad_id", required=false) String viewFilterDificultad,
									@RequestParam (value="filter_set_checkbox", required=false) String viewFilterSetEnabled,
									@RequestParam (value="filter_set_not_checkbox", required=false) String viewFilterSetNotEnabled,
									@RequestParam (value="set_type_id", required=false) String viewFilterSetType,
									@RequestParam (value="titulacion_id", required=false) String viewFilterSetTitulacion,
									@RequestParam (value="filter_set_value_initial", required=false) String viewFilterSetValueInitial,
									@RequestParam (value="filter_set_value_final", required=false) String viewFilterSetValueFinal,
									@RequestParam (value="filter_set_not_current_checkbox", required=false) String viewFilterSetNotCurrentEnabled,
									@RequestParam (value="edition_mode_checkbox", required=false) String viewEditionMode,
									@RequestParam (value="page", required=false) String page,
									@RequestParam (value="page_size_id", required=false) String pageSize,
									@RequestParam (value="categorias_id[]", required=false) String [] viewFilterCategoria,
									@RequestParam (value="set_type", required = false) String viewSetType,
									@RequestParam (value="set_value", required = false) String viewSetValue) {

		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
			
			// Get data
			
			int iViewSetType = toInt(viewSetType, 1);
			int iViewSetValue = toInt(viewSetValue, -1);
			SetType setType = getSetType(iViewSetType);
			
			// Update Set Elements
			
			updateSetElements(viewCartEjercicio, isValid(viewEditionMode), setType.getType(), iViewSetValue);
			
			// Move Set Elements
			
			moveSetElements(viewMoveId, isValid(viewMoveUp), isValid(viewMoveDown), setType.getType(), iViewSetValue, isValid(viewEditionMode));
			
			// Return MAV
			
			return new ModelAndView(new RedirectView(getURLRedirectDefault(request, viewFilterCategoria)));
			
		} catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	protected void updateSetElements (String cartEjercicio, boolean editionMode, Type type, int setValue) {
		
		if (isValid(cartEjercicio)) {
			
			IREjercicio iREjercicio = iREjercicioDAO.find(Integer.parseInt(cartEjercicio));
				
			if (isEditionMode(type, editionMode) && isAbierto(type, setValue)) updateSetElements(type, setValue, iREjercicio);
			else updateSetElements(SetType.Type.CART, -1, iREjercicio);
			
		}
		
	}
	
	protected void updateSetElements (Type type, int setValue, IREjercicio iREjercicio) {
		
		switch (type) {
		
			case CART :
				
				IRPersona iRPersona = getUser();
				
				IREjercicioPersona iREjercicioPersona = iREjercicioPersonaDAO.find(iREjercicio, iRPersona);
				
				if (iREjercicioPersona.isNull()) iREjercicioPersonaDAO.save(IRFactory.newIREjercicioPersona(iREjercicio, iRPersona, iRPersona.getLastPosition(iREjercicioPersonaDAO.getAll())));
				else iREjercicioPersonaDAO.delete(iREjercicioPersona);
				
				break;
				
			case EXAMEN :
				
				IRExamen iRExamen = iRExamenDAO.find(setValue);
				
				IREjercicioExamen iREjercicioExamen = iREjercicioExamenDAO.find(iREjercicio, iRExamen);
				
				if (iREjercicioExamen.isNull()) iREjercicioExamenDAO.save(IRFactory.newIREjercicioExamen(iREjercicio, iRExamen, iRExamen.getLastPosition(iREjercicioExamenDAO.getAll())));
				else iREjercicioExamenDAO.delete(iREjercicioExamen);
				
				break;
				
			case PRACTICA :
				
				IRPractica iRPractica = iRPracticaDAO.find(setValue);
				
				IREjercicioPractica iREjercicioPractica = iREjercicioPracticaDAO.find(iREjercicio, iRPractica);
				
				if (iREjercicioPractica.isNull()) iREjercicioPracticaDAO.save(IRFactory.newIREjercicioPractica(iREjercicio, iRPractica, iRPractica.getLastPosition(iREjercicioPracticaDAO.getAll())));
				else iREjercicioPracticaDAO.delete(iREjercicioPractica);
				
				break;
		
			case PRACTICA_EVALUABLE :
				
				IRPracticaEvaluacion iRPracticaEvaluacion = iRPracticaEvaluacionDAO.find(setValue);
				
				IREjercicioPracticaEvaluacion iREjercicioPracticaEvaluacion = iREjercicioPracticaEvaluacionDAO.find(iREjercicio, iRPracticaEvaluacion);
				
				if (iREjercicioPracticaEvaluacion.isNull()) iREjercicioPracticaEvaluacionDAO.save(IRFactory.newIREjercicioPracticaEvaluacion(iREjercicio, iRPracticaEvaluacion, iRPracticaEvaluacion.getLastPosition(iREjercicioPracticaEvaluacionDAO.getAll())));
				else iREjercicioPracticaEvaluacionDAO.delete(iREjercicioPracticaEvaluacion);
				
				break;
				
			default : break;
				
		}
		
	}
	
	protected void moveSetElements (String moveId, boolean moveUp, boolean moveDown, Type type, int setValue, boolean editionMode) {
		
		if (((userIsPrivileged() && isAbierto(type, setValue) && isEditionMode(type, editionMode)) || type == Type.CART) && isInt(moveId)) {
		
			IREjercicio iREjercicio = iREjercicioDAO.find(Integer.parseInt(moveId));
			
			if (moveDown) moveSetElements(type, setValue, iREjercicio, true);
			else if (moveUp) moveSetElements(type, setValue, iREjercicio, false);

		}
		
	}
	
	protected void moveSetElements (Type type, int setValue, IREjercicio iREjercicio, boolean move) {
		
		switch (type) {
		
			case CART :
				
				IRPersona iRPersona = getUser();
				List<IREjercicioPersona> iREjercicioPersonaList = iRPersona.getIREjerciciosSet();
				IREjercicioPersona iREjercicioPersona = iREjercicioPersonaDAO.find(iREjercicio, iRPersona);
				
				moveSetElements(iREjercicioPersona, iREjercicioPersonaList, iREjercicioPersonaDAO, move);
				
				break;
		
			case EXAMEN :
				
				IRExamen iRExamen = iRExamenDAO.find(setValue);
				List<IREjercicioExamen> iREjercicioExamenList = iRExamen.getIREjerciciosSet();
				IREjercicioExamen iREjercicioExamen = iREjercicioExamenDAO.find(iREjercicio, iRExamen);
				
				moveSetElements(iREjercicioExamen, iREjercicioExamenList, iREjercicioExamenDAO, move);
				
				break;
				
			case PRACTICA :
				
				IRPractica iRPractica = iRPracticaDAO.find(setValue);
				List<IREjercicioPractica> iREjercicioPracticaList = iRPractica.getIREjerciciosSet();
				IREjercicioPractica iREjercicioPractica = iREjercicioPracticaDAO.find(iREjercicio, iRPractica);
				
				moveSetElements(iREjercicioPractica, iREjercicioPracticaList, iREjercicioPracticaDAO, move);
				
				break;
		
			case PRACTICA_EVALUABLE :
				
				IRPracticaEvaluacion iRPracticaEvaluacion = iRPracticaEvaluacionDAO.find(setValue);
				List<IREjercicioPracticaEvaluacion> iREjercicioPracticaEvaluacionList = iRPracticaEvaluacion.getIREjerciciosSet();
				IREjercicioPracticaEvaluacion iREjercicioPracticaEvaluacion = iREjercicioPracticaEvaluacionDAO.find(iREjercicio, iRPracticaEvaluacion);
				
				moveSetElements(iREjercicioPracticaEvaluacion, iREjercicioPracticaEvaluacionList, iREjercicioPracticaEvaluacionDAO, move);
				
				break;
				
			default : break;
		
		}
		
	}
	
	protected <T extends IEjercicioSet, U extends IREjercicioSet<T, U>> void moveSetElements (U iREjercicioSet, List<U> iREjercicioSetList, IRDAO<U> iRDAO, boolean move) {
		
		U iREjercicioSetOther;
		
		if (move) iREjercicioSetOther = iREjercicioSet.getNext(iREjercicioSetList);
		else iREjercicioSetOther = iREjercicioSet.getPrevious(iREjercicioSetList);
		
		iREjercicioSet.exchange(iREjercicioSetOther);
		
		iRDAO.update(iREjercicioSet);
		iRDAO.update(iREjercicioSetOther);
		
	}
	
	// Get URL Redirect
	
	protected String getURLRedirectDefault (HttpServletRequest request, String [] viewFilterCategoria) throws UnsupportedEncodingException {
		
		String url = URL_REDIRECT_DEFAULT + "?";
		
		url += getRequestParams(request, "criteria", "reverse", "filter_ejercicio", "profesor_email", "dificultad_id",
								"filter_set_checkbox", "filter_set_not_checkbox", "set_type_id", "titulacion_id", "filter_set_value_initial",
								"filter_set_value_final", "filter_set_not_current_checkbox",
								"edition_mode_checkbox", "page", "page_size_id",
								"set_type", "set_value");

		url += getCategoriasURLParam(viewFilterCategoria);
		
		return url;
		
	}
	
	protected String getParamFull (HttpServletRequest request, String paramName) {
		
		final String charset = StandardCharsets.UTF_8.toString();
		
		String param = request.getParameter(paramName);
		
		if (param != null && !param.equals(""))
			
			try {
				return URLEncoder.encode(paramName, charset) + "=" + URLEncoder.encode(param, charset);
			} catch (UnsupportedEncodingException e) { return ""; }
		
		else return "";
		
	}
	
	protected String getRequestParams (HttpServletRequest request, String... paramNames) {
		
		StringBuilder requestParams = new StringBuilder();
		
		for (String paramName : paramNames) {
			
			String param = getParamFull(request, paramName);
			
			if (!param.equals("")) requestParams.append("&" + getParamFull(request, paramName));
			
		}
		
		return requestParams.toString();
		
	}
	
	protected String getCategoriasURLParam (String [] viewFilterCategoria) throws UnsupportedEncodingException {
		
		StringBuilder url = new StringBuilder();
		
		if (viewFilterCategoria != null) for (String s : viewFilterCategoria) url.append("&" + URLEncoder.encode("categorias_id[]", StandardCharsets.UTF_8.toString()) + "=" + s);
		
		return url.toString();
		
	}
	
}
