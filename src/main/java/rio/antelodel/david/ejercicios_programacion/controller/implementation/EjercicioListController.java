package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerData.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.latex_document.ILaTexDocumentHelper;
import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.ejercicio.AEjercicioController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.set_type.SetType;
import rio.antelodel.david.ejercicios_programacion.controller.set_type.SetType.Type;
import rio.antelodel.david.ejercicios_programacion.controller.utility.latex_document_handler.IUserLaTexDocumentHandler;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.comparator.IREjercicioComparator;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIREjercicioEnunciado;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IREjercicioHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;

@Controller
@RequestMapping("/ejercicios")
@Secured ({ Role.ALUMNO, Role.PROFESOR, Role.ADMIN })
public class EjercicioListController extends AEjercicioController {
	
	// Element names
	
	protected static final String FILTER_EJERCICIO_NAME = "filterEjercicio";
	protected static final String FILTER_SET_EXISTS_NAME = "filterSetExists";
	protected static final String FILTER_SET_ENABLED_NAME = "filterSetEnabled";
	protected static final String FILTER_SET_NOT_ENABLED_NAME = "filterSetNotEnabled";
	protected static final String FILTER_SET_VALUE_MIN_NAME = "filterSetValueMin";
	protected static final String FILTER_SET_VALUE_MAX_NAME = "filterSetValueMax";
	protected static final String FILTER_SET_VALUE_INITIAL_NAME = "filterSetValueInitial";
	protected static final String FILTER_SET_VALUE_FINAL_NAME = "filterSetValueFinal";
	protected static final String FILTER_SET_NOT_CURRENT_ENABLED = "filterSetNotCurrentEnabled";
	protected static final String SET_NAME_NAME = "setName";
	protected static final String SET_NAME = "set";
	protected static final String EDITABLE_NAME = "editable";
	protected static final String MOVABLE_NAME = "movable";
	protected static final String ABIERTO_NAME = "abierto";
	protected static final String EDITION_MODE_NAME = "editionMode";
	protected static final String SET_TYPE_NAME = "setType";
	protected static final String SET_VALUE_NAME = "setValue";
	protected static final String SET_NAME_LINK_NAME = "setNameLink";
	protected static final String TABLE_TITLE_NAME = "table_name";
	protected static final String CAN_GENERATE_DOCUMENT_NAME = "can_generate_document";
	protected static final String CAN_GENERATE_SET = "can_generate_set";
	
	// Data names
	
	protected static final String EJERCICIOS_DATA_NAME = "ejerciciosData";
	protected static final String EJERCICIOS_SET_DATA_NAME = "ejerciciosSetData";
	protected static final String EJERCICIOS_CHECKED_DATA_NAME = "ejerciciosCheckedData";
	
	// Filters
	
	@Autowired
	protected IFilterIREjercicioEnunciado iFilterIREjercicioEnunciado;
	public void setIFilterIREjercicioEnunciado (IFilterIREjercicioEnunciado iFilterIREjercicioEnunciado) { this.iFilterIREjercicioEnunciado = iFilterIREjercicioEnunciado; }
	
	@Autowired
	protected IFilterIRProfesor iFilterIRProfesor;
	public void setIFilterIRProfesor (IFilterIRProfesor iFilterIRProfesor) { this.iFilterIRProfesor = iFilterIRProfesor; }
	
	@Autowired
	protected IFilterIRDificultad iFilterIRDificultad;
	public void setIFilterIRDificultad (IFilterIRDificultad iFilterIRDificultad) { this.iFilterIRDificultad = iFilterIRDificultad; }
	
	@Autowired
	protected IFilterIRCategoria iFilterIRCategoria;
	public void setIFilterIRCategoria (IFilterIRCategoria iFilterIRCategoria) { this.iFilterIRCategoria = iFilterIRCategoria; }
	
	// Handlers
	
	@Autowired
	private IUserLaTexDocumentHandler iUserLaTexDocumentHandler;
	public void setILaTexDocumentHandler (IUserLaTexDocumentHandler iUserLatexDocumentHandler) { this.iUserLaTexDocumentHandler = iUserLatexDocumentHandler; }
	
	// Views
	
	protected static final String VIEW_EJERCICIO_LIST = VIEW_MAIN_PATH + "ejercicio-list/ejercicio-list";
	
	// Functions
	
	// Mapping
	
	// Get Ejercicio List
	
	@GetMapping
	@Transactional
	public ModelAndView getEjercicioListForm (	@RequestParam (value="criteria", required=false) String criteria,
												@RequestParam (value="reverse", required=false) String reverse,
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
												@RequestParam (value="page", required=false) String viewPage,
												@RequestParam (value="page_size_id", required=false) String viewPageSize,
												@RequestParam (value="categorias_id[]", required=false) String [] viewFilterCategoria,
												@RequestParam (value="set_type", required = false) String viewSetType,
												@RequestParam (value="set_value", required = false) String viewSetValue) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
			
			// Set
			
			int iSetType = toInt(viewSetType, 0);
			model.put(SET_TYPE_NAME, Integer.toString(iSetType));
			
			int setValue = toInt(viewSetValue, -1);
			model.put(SET_VALUE_NAME, Integer.toString(setValue));
			
			SetType setType = getSetType(iSetType);
			model.put(SET_NAME_LINK_NAME, setType.getNameLink());
			
			model.put(SET_TYPE_CURRENT_DATA_NAME, setType.getFullData().toString());
			model.put(SET_TYPES_DATA_NAME, getGenericListSortedData(getSetTypeReducedList()).toString());
			
			if (setIsVisible(setType.getType(), setValue)) {
			
				// Ejercicio Lists
				
				List<IREjercicio> iREjercicioList = getIREjercicioList(criteria, reverse);
				List<IREjercicio> iREjercicioSetList = getIREjercicioSetList(setType.getType(), setValue);
				List<IREjercicio> iREjercicioCheckedList = getIREjercicioCheckedList(setType.getType(), setValue, isValid(viewEditionMode));
				
				iREjercicioList = cleanIREjercicioList(setType.getType(), isValid(viewEditionMode), isValid(viewFilterSetNotCurrentEnabled), iREjercicioList, iREjercicioCheckedList);
				iREjercicioSetList = cleanIREjercicioSetList(iREjercicioSetList);
				
				// Apply filters
				
				iREjercicioList = applyFilters(	iREjercicioList,
												model,
												viewFilterEjercicio,
												viewFilterProfesor,
												viewFilterDificultad,
												viewFilterCategoria,
												viewFilterSetEnabled,
												viewFilterSetNotEnabled,
												viewFilterSetType,
												viewFilterSetTitulacion,
												viewFilterSetValueInitial,
												viewFilterSetValueFinal);
				
				// Set info
				
				model.put(SET_NAME, ViewBoolean.toViewBoolean(isSet(setType.getType())));
				model.put(EDITABLE_NAME, ViewBoolean.toViewBoolean(isEditable(setType.getType())));
				model.put(ABIERTO_NAME, ViewBoolean.toViewBoolean(isAbierto(setType.getType(), setValue)));
				model.put(EDITION_MODE_NAME, ViewBoolean.toViewBoolean(isEditionMode(setType.getType(), isValid(viewEditionMode))));
				model.put(MOVABLE_NAME, ViewBoolean.toViewBoolean(isMovable(setType.getType(), toInt(viewSetValue, -1))));
				
				model.put(SET_NAME_NAME, setType.getName());
				
				// Other info
				
				model.put(TABLE_TITLE_NAME, setType.getName());
				model.put(CAN_GENERATE_DOCUMENT_NAME, ViewBoolean.toViewBoolean(setType.canGenerateDocument()));
				model.put(CAN_GENERATE_SET, ViewBoolean.toViewBoolean(setType.canGenerateSet()));
				model.put(FILTER_SET_NOT_CURRENT_ENABLED, ViewBoolean.toViewBoolean(isValid(viewFilterSetNotCurrentEnabled)));
				model.put(CRITERIA_NAME, Integer.toString(toInt(criteria)));
				model.put(REVERSE_NAME, Integer.toString(toInt(reverse)));
				
				// Page data
				
				int page = getPageInfo(viewPage, 1);
				model.put(PAGE_NAME, Integer.toString(page));
				
				// Page size data
				
				int pageSize = getPageInfo(viewPageSize, PAGE_SIZE_DEFAULT);
				
				JSONObject pageSizeData = getPageSizeData(pageSize);
				model.put(PAGE_SIZES_DATA_NAME, pageSizeData.toString());
				
				// Page last data
				
				int pageLast = getPageLast(iREjercicioList, pageSize);
				model.put(PAGE_LAST_NAME, Integer.toString(pageLast));
				
				// Get Ejercicios paged list
		
				iREjercicioList = getPagedList(iREjercicioList, page, pageSize);
				
				// Tables
				
				JSONArray ejerciciosData = getGenericListData(iREjercicioList);
				model.put(EJERCICIOS_DATA_NAME, ejerciciosData.toString());
				
				JSONArray ejerciciosSetData = getGenericListData(iREjercicioSetList);
				model.put(EJERCICIOS_SET_DATA_NAME, ejerciciosSetData.toString());
				
				JSONArray ejerciciosCheckedData = getGenericListData(iREjercicioCheckedList);
				model.put(EJERCICIOS_CHECKED_DATA_NAME, ejerciciosCheckedData.toString());
				
				// Return MAV
				
				return getDefaultMAV(VIEW_EJERCICIO_LIST, model);
				
			}
			
			else {
				
				CustomLogger.LOGGER.info(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from a non visible Set as a non privileged user");
				return getAccessDeniedView();
				
			}
			
		} 
		
		catch (Exception e) { 
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	// Auxiliar
	
	private List<IREjercicio> applyFilters (List<IREjercicio> iREjercicioList,
											Map<String, String> model,
											String viewFilterEjercicio,
											String viewFilterProfesor,
											String viewFilterDificultad,
											String [] viewFilterCategoria,
											String viewFilterSetEnabled,
											String viewFilterSetNotEnabled,
											String viewFilterSetType,
											String viewFilterSetTitulacion,
											String viewFilterSetValueInitial,
											String viewFilterSetValueFinal) {
		
		// Enunciado
		
		iREjercicioList = iFilterIREjercicioEnunciado.cleanAndApply(iREjercicioList, viewFilterEjercicio, Operation.OR);
		model.put(FILTER_EJERCICIO_NAME, IFilterHelper.getCleanFilters(viewFilterEjercicio));
		
		// Profesor
		
		iREjercicioList = iFilterIRProfesor.cleanAndApply(iREjercicioList, viewFilterProfesor, Operation.OR);
		model.put(PROFESORES_DATA_NAME, getGenericListSortedData(iRProfesorDAO.getAll()).toString());
		model.put(PROFESOR_CURRENT_DATA_NAME, getNullableData(iRProfesorDAO.find(toStr(viewFilterProfesor))).toString());
		model.put(PROFESOR_ANY_NAME, ViewBoolean.TRUE);
		
		// Dificultad
		
		iREjercicioList = iFilterIRDificultad.cleanAndApply(iREjercicioList, viewFilterDificultad, Operation.OR);
		model.put(DIFICULTADES_DATA_NAME, getGenericListSortedData(iRDificultadDAO.getAll()).toString());
		model.put(DIFICULTAD_CURRENT_DATA_NAME, getNullableData(iRDificultadDAO.find(toInt(viewFilterDificultad, -1))).toString());
		model.put(DIFICULTAD_ANY_NAME, ViewBoolean.TRUE);
		
		// Categoria
		
		iREjercicioList = iFilterIRCategoria.apply(iREjercicioList, getList(viewFilterCategoria), Operation.AND);
		model.put(CATEGORIAS_DATA_NAME, getGenericListSortedData(iRCategoriaDAO.getAll()).toString());
		
		List<IRCategoria> iRCategoriaCurrentList = new ArrayList<>();
		if (viewFilterCategoria != null) for (String s : viewFilterCategoria) {
			
			IRCategoria iRCategoria = iRCategoriaDAO.find(toInt(s, -1));
			if (!iRCategoria.isNull()) iRCategoriaCurrentList.add(iRCategoria);
			
		}
		
		model.put(CATEGORIAS_CURRENT_DATA_NAME, getGenericListSortedData(iRCategoriaCurrentList).toString());
		
		// Set
		
		iREjercicioList = applyFiltersSet(model, isValid(viewFilterSetEnabled), isValid(viewFilterSetNotEnabled), iREjercicioList, viewFilterSetType, viewFilterSetTitulacion, viewFilterSetValueInitial, viewFilterSetValueFinal);
		
		// Return Ejercicio List
		
		return iREjercicioList;
		
	}
	
	// Get Document
	
	@GetMapping("/-1/document.html")
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HttpEntity<byte[]> getDocument (@RequestParam("document_id") String viewDocumentType) {
		
		try {
		
			List<IREjercicioSet> iREjercicioSetList = new ArrayList<>();
			
			for (IREjercicioSet iREjercicioSet : getUser().getIREjerciciosSet()) if (userIsPrivileged() || iREjercicioSet.getIREjercicio().isVisibleForUnprivileged()) iREjercicioSetList.add(iREjercicioSet);
			
			return iUserLaTexDocumentHandler.getDocument("Boletin de ejercicios", "Boletín de ejercicios", iREjercicioSetList, ILaTexDocumentHelper.intToDocumentType(toInt(viewDocumentType)));
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to obtain document and triggered the exception: " + e.toString());
			return null;
			
		}
		
	}
	
	protected boolean setIsVisible (SetType.Type type, int setValue) {
		
		return userIsPrivileged() || type == Type.ALL || type == Type.CART || getSetEntity(type, setValue).isVisible(); 
		
	}
	
	protected boolean hasSetFilters () {
		
		return userIsPrivileged();
		
	}
	
	protected boolean isEditable (Type type) {
		
		return isComplexSet(type) && userIsPrivileged();
		
	}
	
	protected boolean isMovable (Type type, int setValue) {
		
		return type == Type.CART || (isAbierto(type, setValue) && userIsPrivileged());
		
	}
	
	protected List<IREjercicio> cleanIREjercicioSetList (List<IREjercicio> iREjercicioList) {
		
		if (!userIsPrivileged()) return IREjercicioHelper.getVisibleForUnprivilegedList(iREjercicioList);
		else return iREjercicioList;
		
	}
	
	protected List<IREjercicio> cleanIREjercicioList (Type type, boolean editionMode, boolean notCurrentEnabled, List<IREjercicio> iREjercicioList, List<IREjercicio> iREjercicioCheckedList) {
		
		if (!userIsPrivileged()) iREjercicioList = IREjercicioHelper.getVisibleForUnprivilegedList(iREjercicioList);
		else if (isEditionMode(type, editionMode) && notCurrentEnabled) iREjercicioList.removeAll(iREjercicioCheckedList);
		
		return iREjercicioList;
		
	}
	
	protected List<IREjercicio> getIREjercicioList (String criteria, String reverse) {
		
		return getIREjercicioList(Type.ALL, -1, criteria, reverse);
		
	}
	
	protected List<IREjercicio> getIREjercicioSetList (Type type, int setValue) {
		
		if (isComplexSet(type)) return getIREjercicioList(type, setValue, null, null);
		else return getIREjercicioList(Type.CART, -1, null, null);
		
	}
	
	protected List<IREjercicio> getIREjercicioCheckedList(Type type, int setValue, boolean editionMode) {
		
		if (isSet(type) && isEditionMode(type, editionMode)) return getIREjercicioList(type, setValue, null, null);
		else return getIREjercicioList(Type.CART, -1, null, null);
		
	}
	
	protected List<IREjercicio> getIREjercicioList (Type type, int setValue, String criteria, String reverse) {
		
		List<IREjercicio> iREjercicioList = new ArrayList<>();
		
		switch (type) {
		
			case ALL :
				
				Comparator<IREjercicio> comparator;
				
				switch(toInt(criteria, -1)) {
				
					case 1 	: comparator = IREjercicioComparator.comparatorProfesor; break;
					case 2 	: comparator = IREjercicioComparator.comparatorDificultad; break;
					default : comparator = IREjercicioComparator.comparatorTitulo; break;
				
				}
				
				iREjercicioList = iREjercicioDAO.getAll();
				Collections.sort(iREjercicioList, comparator);
				
				if (toInt(reverse) > 0) Collections.reverse(iREjercicioList);
				
				break;
	
			case CART :
				
				IRPersona iRPersona = getUser();
				iREjercicioList = iRPersona.getIREjerciciosSortedList();
				
				break;
				
			default :
				
				IRComplexSet<?, ?, ?, ?> iRComplexSet = getSetEntity(type, setValue);
				if (iRComplexSet != null && !iRComplexSet.isNull()) iREjercicioList = iRComplexSet.getIREjerciciosSortedList();
				
				break;
				
		}
		
		return iREjercicioList;
	
	}
	
	protected List<IREjercicio> applyFiltersSet (Map<String, String> model, boolean filterSetEnabled, boolean filterSetNotEnabled, List<IREjercicio> iREjercicioList, String viewFilterSetType, String viewFilterSetTitulacion, String viewFilterSetValueInitial, String viewFilterSetValueFinal) {
	
		model.put(SET_TYPE_FILTER_CURRENT_DATA_NAME, getSetType(toInt(viewFilterSetType, 2)).getFullData().toString());
		
		// Filter type
		
		model.put(FILTER_SET_EXISTS_NAME, ViewBoolean.toViewBoolean(hasSetFilters()));
		model.put(FILTER_SET_ENABLED_NAME, ViewBoolean.toViewBoolean(filterSetEnabled));
		model.put(FILTER_SET_NOT_ENABLED_NAME, ViewBoolean.toViewBoolean(filterSetNotEnabled));
		
		// Titulaciones Data
		
		model.put(TITULACION_ANY_NAME, ViewBoolean.TRUE);
				
		JSONObject titulacionCurrentData = getNullableData(iRTitulacionDAO.find(toInt(viewFilterSetTitulacion, -1)));
		model.put(TITULACION_CURRENT_DATA_NAME, titulacionCurrentData.toString());
				
		JSONArray titulacionesData = getGenericListSortedData(iRTitulacionDAO.getAll());
		model.put(TITULACIONES_DATA_NAME, titulacionesData.toString());
		
		// Values
		
		model.put(FILTER_SET_VALUE_MIN_NAME, Integer.toString(getAnoMin()));
		model.put(FILTER_SET_VALUE_MAX_NAME, Integer.toString(getAnoMax()));
		
		model.put(FILTER_SET_VALUE_INITIAL_NAME, Integer.toString(toInt(viewFilterSetValueInitial, getAnoMin())));
		model.put(FILTER_SET_VALUE_FINAL_NAME, Integer.toString(toInt(viewFilterSetValueFinal, getAnoMax())));
		
		// Titulacion
		
		IRTitulacion iRTitulacion = iRTitulacionDAO.find(toInt(viewFilterSetTitulacion, -1));
		
		if (hasSetFilters() && filterSetEnabled) {
		
			List<IREjercicio> iREjercicioFinalList;
			
			switch (getSetType(toInt(viewFilterSetType)).getType()) {
			
				case EXAMEN : iREjercicioFinalList = iFilterIRExamenAnoRange.matchesRangeGeneric(iREjercicioList, viewFilterSetValueInitial, viewFilterSetValueFinal, iRTitulacion); break;
				case PRACTICA : iREjercicioFinalList = iFilterIRPracticaAnoRange.matchesRangeGeneric(iREjercicioList, viewFilterSetValueInitial, viewFilterSetValueFinal, iRTitulacion); break;
				case PRACTICA_EVALUABLE : iREjercicioFinalList = iFilterIRPracticaEvaluacionAnoRange.matchesRangeGeneric(iREjercicioList, viewFilterSetValueInitial, viewFilterSetValueFinal, iRTitulacion); break;
				default : iREjercicioFinalList = new ArrayList<>();
				
			}
			
			if (filterSetNotEnabled) iREjercicioList.removeAll(iREjercicioFinalList);
			else iREjercicioList = iREjercicioFinalList;
			
			return iREjercicioList;
			
		}
		
		else return iREjercicioList;
		
	}
	
}
