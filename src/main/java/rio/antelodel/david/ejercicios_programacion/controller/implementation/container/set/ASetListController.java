package rio.antelodel.david.ejercicios_programacion.controller.implementation.container.set;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerData.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.set_type.SetType;
import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.comparator.IRComplexSetComparator;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IRDificultadHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;

public abstract class ASetListController < T extends IRComplexSet<?, ?, ?, T> > extends AController {
	
	// Element names
	
	protected static final String TABLE_NAME_NAME = "tableName";
	protected static final String FILTER_SET_NAME = "filterSet";
		
	// Data names
		
	protected static final String SET_DIFICULTADES_DATA_NAME = "setDificultadesData"; 
	protected static final String SETS_DATA_NAME = "setsData";
	protected static final String SET_TYPE_DATA_NAME = "setTypeData";
	
	// DAOs
	
	@Autowired
	protected IRProfesorDAO iRProfesorDAO;
	public void setIRProfesorDAO (IRProfesorDAO iRProfesorDAO) { this.iRProfesorDAO = iRProfesorDAO; }
	
	@Autowired
	protected IRTitulacionDAO iRTitulacionDAO;
	public void setIRTitulacionDAO (IRTitulacionDAO iRTitulacionDAO) { this.iRTitulacionDAO = iRTitulacionDAO; }
	
	@Autowired
	protected IRCategoriaDAO iRCategoriaDAO;
	public void setIRCategoriaDAO (IRCategoriaDAO iRCategoriaDAO) { this.iRCategoriaDAO = iRCategoriaDAO; }
	
	// Filters
	
	@Autowired
	protected IFilterIRProfesor iFilterIRProfesor;
	public void setiFilterIRProfesor (IFilterIRProfesor iFilterIRProfesor) { this.iFilterIRProfesor = iFilterIRProfesor; }
	
	@Autowired
	protected IFilterIRTitulacion iFilterIRTitulacion;
	public void setiFilterIRTitulacion (IFilterIRTitulacion iFilterIRTitulacion) { this.iFilterIRTitulacion = iFilterIRTitulacion; }
	
	@Autowired
	protected IFilterIRCategoria iFilterIRCategoria;
	public void setIFilterIRCategoria (IFilterIRCategoria iFilterIRCategoria) { this.iFilterIRCategoria = iFilterIRCategoria; }
	
	// Views
	
	protected static final String VIEW_SET_LIST = "set/set-list";
		
	// Functions
	
	@SuppressWarnings("rawtypes")
	protected ModelAndView getListForm (SetType setType,
										String tableName,
										String criteria,
										String reverse,
										String viewFilterSet,
										String viewFilterProfesor,
										String viewFilterTitulacion,
										String [] viewFilterCategoria,
										String viewFilterSetValueInitial,
										String viewFilterSetValueFinal,
										String viewPage,
										String viewPageSize) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Table name
		
		model.put(TABLE_NAME_NAME, tableName);
		
		// Criteria info
		
		model.put(CRITERIA_NAME, Integer.toString(toInt(criteria, 3)));
		model.put(REVERSE_NAME, Integer.toString(toInt(reverse, 1)));
		
		// Filter Set name
		
		model.put(FILTER_SET_NAME, IFilterHelper.getCleanFilters(viewFilterSet));
		
		// Profesores Data
		
		model.put(PROFESOR_ANY_NAME, ViewBoolean.TRUE);
		
		JSONObject profesorCurrentData = getNullableData(iRProfesorDAO.find(toStr(viewFilterProfesor, "any")));
		model.put(PROFESOR_CURRENT_DATA_NAME, profesorCurrentData.toString());
		
		JSONArray profesoresData = getGenericListSortedData(iRProfesorDAO.getAll());
		model.put(PROFESORES_DATA_NAME, profesoresData.toString());
		
		// Titulaciones Data
		
		model.put(TITULACION_ANY_NAME, ViewBoolean.TRUE);
		
		JSONObject titulacionCurrentData = getNullableData(iRTitulacionDAO.find(toInt(viewFilterTitulacion, -1)));
		model.put(TITULACION_CURRENT_DATA_NAME, titulacionCurrentData.toString());
		
		JSONArray titulacionesData = getGenericListSortedData(iRTitulacionDAO.getAll());
		model.put(TITULACIONES_DATA_NAME, titulacionesData.toString());
		
		// Categorias Data
		
		List<IRCategoria> iRCategoriaList = new ArrayList<>();
		
		if (isValid(viewFilterCategoria)) {
			
			for (String s : viewFilterCategoria) {
				
				IRCategoria iRCategoria = iRCategoriaDAO.find(toInt(s, -1));
				if (!iRCategoria.isNull()) iRCategoriaList.add(iRCategoria);
				
			}
			
		}
		
		model.put(CATEGORIAS_CURRENT_DATA_NAME, IParseableHelper.getGenericListData(iRCategoriaList).toString());
		model.put(CATEGORIAS_DATA_NAME, getGenericListSortedData(iRCategoriaDAO.getAll()).toString());
		
		// Page data
		
		int page = getPageInfo(viewPage, 1);
		model.put(PAGE_NAME, Integer.toString(page));
		
		// Page size data
		
		int pageSize = getPageInfo(viewPageSize, PAGE_SIZE_DEFAULT);
		
		JSONObject pageSizeData = getPageSizeData(pageSize);
		model.put(PAGE_SIZES_DATA_NAME, pageSizeData.toString());
			
		// Set filters
		
		model.put(FILTER_SET_VALUE_MIN_NAME, Integer.toString(getAnoMin()));
		model.put(FILTER_SET_VALUE_MAX_NAME, Integer.toString(getAnoMax()));
		
		model.put(FILTER_SET_VALUE_INITIAL_NAME, Integer.toString(toInt(viewFilterSetValueInitial, getAnoMin())));
		model.put(FILTER_SET_VALUE_FINAL_NAME, Integer.toString(toInt(viewFilterSetValueFinal, getAnoMax())));
		
		// Set list
		
		List<T> setList = getSetList(viewFilterSet, viewFilterProfesor, viewFilterTitulacion, viewFilterCategoria, Integer.toString(toInt(viewFilterSetValueInitial, getAnoMin())), Integer.toString(toInt(viewFilterSetValueFinal, getAnoMax())), page, pageSize);
		
		// Sort
		
		Comparator<IRComplexSet> comparator;
		
		switch (toInt(criteria, 3)) {
		
			case 0 	: comparator = IRComplexSetComparator.comparatorDescripcion; break;
			case 1 	: comparator = IRComplexSetComparator.comparatorTitulacion; break;
			case 2 	: comparator = IRComplexSetComparator.comparatorMes; break;
			default : comparator = IRComplexSetComparator.comparatorAno; break;
		
		}
		
		Collections.sort(setList, comparator);
		
		if (toInt(reverse, 1) > 0) Collections.reverse(setList);
		
		// Page last data
		
		int pageLast = getPageLast(setList, pageSize);
		model.put(PAGE_LAST_NAME, Integer.toString(pageLast));
		
		// Paged list
		
		setList = getPagedList(setList, page, pageSize);
		
		// Set Dificultades Data
		
		model.put(SET_DIFICULTADES_DATA_NAME, getSetDificultadesData(setList).toString());
		
		// Sets Data
		
		model.put(SET_TYPE_DATA_NAME, setType.getFullData().toString());
		
		JSONArray setsData = getGenericListData(setList);
		model.put(SETS_DATA_NAME, setsData.toString());
		
		// Return MAV
		
		return getDefaultMAV(VIEW_SET_LIST, model);
	
	}
	
	// Get Set Dificultad average
	
	private JSONArray getSetDificultadesData (List<T> list) {
		
		JSONArray setDificultadesData = new JSONArray();
		
		for (T set : list) {
			
			List<IRDificultad> iRDificultadList = new ArrayList<>();
			
			for (IREjercicio iRE : set.getIREjercicios()) iRDificultadList.add(iRE.getIRDificultad());
			
			setDificultadesData.put(IRDificultadHelper.getIRDificultadAverage(iRDificultadList, iRDificultadDAO.getAll()).getFullData());
			
		}
		
		return setDificultadesData;
		
	}
	
	protected abstract List<T> getSetList (String filterSet, String filterProfesor, String filterTitulacion, String [] viewFilterCategoria, String filterSetValueInitial,String filterSetValueFinal, int page, int pageSize);
	
}
