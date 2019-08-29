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
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IRComplexSetHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;

@Controller
@RequestMapping("/practicas")
@Secured({ Role.ALUMNO, Role.PROFESOR, Role.ADMIN })
public class PracticaListController extends ASetListController <IRPractica> {

	private static final String SET_NAME = "Prácticas";
	
	// DAOs
	
	@Autowired
	private IRPracticaDAO iRPracticaDAO;
	public void setIRPracticaDAO (IRPracticaDAO iRPracticaDAO) { this.iRPracticaDAO = iRPracticaDAO; }
	
	// Filters
	
	@Autowired
	private IFilterIRPracticaDescripcion iFilterIRPracticaDescripcion;
	@Autowired
	private IFilterIRPracticaAnoRange iFilterIRPracticaAnoRange;
	
	// Functions
	
	// Mapping
	
	// Get Practica List
	
	@GetMapping
	@Transactional
	public ModelAndView getPracticaListForm (	@RequestParam (value="criteria", required=false) String criteria,
												@RequestParam (value="reverse", required=false) String reverse,
												@RequestParam (value="page", required=false) String page,
												@RequestParam (value="page_size_id", required=false) String pageSize,
												@RequestParam (value="filter_set", required=false) String viewFilterSet,
												@RequestParam (value="profesor_email", required=false) String viewFilterProfesor,
												@RequestParam (value="titulacion_id", required=false) String viewFilterTitulacion,
												@RequestParam (value="categorias_id[]", required=false) String [] viewFilterCategoria,
												@RequestParam (value="filter_set_value_initial", required=false) String viewFilterSetValueInitial,
												@RequestParam (value="filter_set_value_final", required=false) String viewFilterSetValueFinal) {
		
		return getListForm(getSetTypeReducedList().get(1), SET_NAME, criteria, reverse, viewFilterSet, viewFilterProfesor, viewFilterTitulacion, viewFilterCategoria, viewFilterSetValueInitial, viewFilterSetValueFinal, page, pageSize);
		
	}
	
	@Override
	protected List<IRPractica> getSetList (String filterSet, String filterProfesor, String filterTitulacion, String [] viewFilterCategoria, String filterSetValueInitial, String filterSetValueFinal, int page, int pageSize) {
		
		List<IRPractica> iRPracticaList = iRPracticaDAO.getAll();
		
		if (userIsRole(Role.ALUMNO)) iRPracticaList = IRComplexSetHelper.getVisibleList(iRPracticaList);
		
		// Apply filters
		
		iRPracticaList = iFilterIRPracticaDescripcion.cleanAndApply(iRPracticaList, filterSet, IFilterHelper.Operation.OR);
		iRPracticaList = iFilterIRProfesor.cleanAndApply(iRPracticaList, filterProfesor, IFilterHelper.Operation.OR);
		iRPracticaList = iFilterIRTitulacion.cleanAndApply(iRPracticaList, filterTitulacion, IFilterHelper.Operation.OR);
		iRPracticaList = iFilterIRCategoria.apply(iRPracticaList, getList(viewFilterCategoria), IFilterHelper.Operation.AND);
		iRPracticaList = iFilterIRPracticaAnoRange.matchesRangeGeneric(iRPracticaList, filterSetValueInitial, filterSetValueFinal);
		
		return iRPracticaList;
		
	}
	
}
