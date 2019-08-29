package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.admin_element.AAdminElementListController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRDificultadNombre;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

@Controller
@RequestMapping("/dificultades")
@Secured(Role.ADMIN)
public class DificultadListController extends AAdminElementListController < IRDificultad > {

	// Constants

	private static final String NOMBRE = "dificultades";
	private static final String LINK_NOMBRE = "dificultades";
	
	// DAOs
	
	@Autowired
	private IRDificultadDAO iRDificultadDAO;
	public void setIRDificultadDAO (IRDificultadDAO iRDificultadDAO) { this.iRDificultadDAO = iRDificultadDAO; }
	
	// Filters
	
	@Autowired
	private IFilterIRDificultadNombre iFilterIRDificultadNombre;
	public void setiFilterIRDificultadNombre(IFilterIRDificultadNombre iFilterIRDificultadNombre) { this.iFilterIRDificultadNombre = iFilterIRDificultadNombre; }
	
	// Functions
	
	// Mappings
	
	// Get Dificultad List
	
	@GetMapping
	public ModelAndView getDificultadListForm (	@RequestParam(value="filter", required=false) String viewFilter,
												@RequestParam(value="page", required=false) String viewPage,
												@RequestParam(value="page_size_id", required=false) String viewPageSize) {
	
		List<IRDificultad> iRDificultadList = iRDificultadDAO.getAll();
		
		iRDificultadList = iFilterIRDificultadNombre.cleanAndApply(iRDificultadList, viewFilter, Operation.OR);
		
		return getListForm(NOMBRE, LINK_NOMBRE, viewFilter, viewPage, viewPageSize, iRDificultadList);
		
	}

}
