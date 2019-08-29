package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.set.ASetListController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaEvaluacionAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaEvaluacionDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IRComplexSetHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;

@Controller
@RequestMapping("/practicas-evaluacion")
@Secured({ Role.ALUMNO, Role.PROFESOR, Role.ADMIN })
public class PracticaEvaluacionListController extends ASetListController <IRPracticaEvaluacion> {

	private static final String SET_NAME = "Prácticas evaluables";
	
	// DAOs
	
	@Autowired
	private IRPracticaEvaluacionDAO iRPracticaEvaluacionDAO;
	public void setIRPracticaEvaluacionDAO (IRPracticaEvaluacionDAO iRPracticaEvaluacionDAO) { this.iRPracticaEvaluacionDAO = iRPracticaEvaluacionDAO; }
	
	// Filters
	
	@Autowired
	private IFilterIRPracticaEvaluacionDescripcion iFilterIRPracticaEvaluacionDescripcion;
	@Autowired
	private IFilterIRPracticaEvaluacionAnoRange iFilterIRPracticaEvaluacionAnoRange;
	
	// Functions
	
	// Mapping
	
	// Get PracticaEvaluacion List
	
	@GetMapping
	@Transactional
	public ModelAndView getPracticaEvaluacionListForm (	@RequestParam (value="criteria", required=false) String criteria,
														@RequestParam (value="reverse", required=false) String reverse,
														@RequestParam (value="page", required=false) String page,
														@RequestParam (value="page_size_id", required=false) String pageSize,
														@RequestParam (value="filter_set", required=false) String viewFilterSet,
														@RequestParam (value="profesor_email", required=false) String viewFilterProfesor,
														@RequestParam (value="titulacion_id", required=false) String viewFilterTitulacion,
														@RequestParam (value="categorias_id[]", required=false) String [] viewFilterCategoria,
														@RequestParam (value="filter_set_value_initial", required=false) String viewFilterSetValueInitial,
														@RequestParam (value="filter_set_value_final", required=false) String viewFilterSetValueFinal) {
		
		return getListForm(getSetTypeReducedList().get(2), SET_NAME, criteria, reverse, viewFilterSet, viewFilterProfesor, viewFilterTitulacion, viewFilterCategoria, viewFilterSetValueInitial, viewFilterSetValueFinal, page, pageSize);
		
	}
	
	@Override
	protected List<IRPracticaEvaluacion> getSetList (String filterSet, String filterProfesor, String filterTitulacion, String [] viewFilterCategoria, String filterSetValueInitial, String filterSetValueFinal, int page, int pageSize) {
		
		List<IRPracticaEvaluacion> iRPracticaEvaluacionList = iRPracticaEvaluacionDAO.getAll();
		
		if (userIsRole(Role.ALUMNO)) iRPracticaEvaluacionList = IRComplexSetHelper.getVisibleList(iRPracticaEvaluacionList);
		
		// Apply filters
		
		iRPracticaEvaluacionList = iFilterIRPracticaEvaluacionDescripcion.cleanAndApply(iRPracticaEvaluacionList, filterSet, IFilterHelper.Operation.OR);
		iRPracticaEvaluacionList = iFilterIRProfesor.cleanAndApply(iRPracticaEvaluacionList, filterProfesor, IFilterHelper.Operation.OR);
		iRPracticaEvaluacionList = iFilterIRTitulacion.cleanAndApply(iRPracticaEvaluacionList, filterTitulacion, IFilterHelper.Operation.OR);
		iRPracticaEvaluacionList = iFilterIRCategoria.apply(iRPracticaEvaluacionList, getList(viewFilterCategoria), IFilterHelper.Operation.AND);
		iRPracticaEvaluacionList = iFilterIRPracticaEvaluacionAnoRange.matchesRangeGeneric(iRPracticaEvaluacionList, filterSetValueInitial, filterSetValueFinal);
		
		return iRPracticaEvaluacionList;
		
	}
	
}
