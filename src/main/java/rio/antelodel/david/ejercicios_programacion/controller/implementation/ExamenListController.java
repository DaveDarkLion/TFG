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
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRExamenAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRExamenDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IRComplexSetHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;

@Controller
@RequestMapping("/examenes")
@Secured({ Role.ALUMNO, Role.PROFESOR, Role.ADMIN })
public class ExamenListController extends ASetListController <IRExamen> {

	private static final String SET_NAME = "Exámenes";
	
	// DAOs
	
	@Autowired
	private IRExamenDAO iRExamenDAO;
	public void setIRExamenDAO (IRExamenDAO iRExamenDAO) { this.iRExamenDAO = iRExamenDAO; }
	
	// Filters
	
	@Autowired
	private IFilterIRExamenDescripcion iFilterIRExamenDescripcion;
	@Autowired
	private IFilterIRExamenAnoRange iFilterIRExamenAnoRange;
	
	// Functions
	
	// Mapping
	
	// Get Examen List
	
	@GetMapping
	@Transactional
	public ModelAndView getExamenListForm (	@RequestParam (value="criteria", required=false) String criteria,
											@RequestParam (value="reverse", required=false) String reverse,
											@RequestParam (value="page", required=false) String page,
											@RequestParam (value="page_size_id", required=false) String pageSize,
											@RequestParam (value="filter_set", required=false) String viewFilterSet,
											@RequestParam (value="profesor_email", required=false) String viewFilterProfesor,
											@RequestParam (value="titulacion_id", required=false) String viewFilterTitulacion,
											@RequestParam (value="categorias_id[]", required=false) String [] viewFilterCategoria,
											@RequestParam (value="filter_set_value_initial", required=false) String viewFilterSetValueInitial,
											@RequestParam (value="filter_set_value_final", required=false) String viewFilterSetValueFinal) {
		
		return getListForm(getSetTypeReducedList().get(0), SET_NAME, criteria, reverse, viewFilterSet, viewFilterProfesor, viewFilterTitulacion, viewFilterCategoria, viewFilterSetValueInitial, viewFilterSetValueFinal, page, pageSize);
		
	}
	
	@Override
	protected List<IRExamen> getSetList (String filterSet, String filterProfesor, String filterTitulacion, String [] viewFilterCategoria, String filterSetValueInitial, String filterSetValueFinal, int page, int pageSize) {
		
		List<IRExamen> iRExamenList = iRExamenDAO.getAll();
		
		if (userIsRole(Role.ALUMNO)) iRExamenList = IRComplexSetHelper.getVisibleList(iRExamenList);
		
		// Apply filters
		
		iRExamenList = iFilterIRExamenDescripcion.cleanAndApply(iRExamenList, filterSet, IFilterHelper.Operation.OR);
		iRExamenList = iFilterIRProfesor.cleanAndApply(iRExamenList, filterProfesor, IFilterHelper.Operation.OR);
		iRExamenList = iFilterIRTitulacion.cleanAndApply(iRExamenList, filterTitulacion, IFilterHelper.Operation.OR);
		iRExamenList = iFilterIRCategoria.apply(iRExamenList, getList(viewFilterCategoria), IFilterHelper.Operation.AND);
		iRExamenList = iFilterIRExamenAnoRange.matchesRangeGeneric(iRExamenList, filterSetValueInitial, filterSetValueFinal);
		
		return iRExamenList;
		
	}
	
}
