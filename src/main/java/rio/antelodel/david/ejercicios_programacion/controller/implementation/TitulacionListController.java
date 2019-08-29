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
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRTitulacionNombre;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Controller
@RequestMapping("/titulaciones")
@Secured(Role.ADMIN)
public class TitulacionListController extends AAdminElementListController < IRTitulacion > {

	// Constants

	private static final String NOMBRE = "titulaciones";
	private static final String LINK_NOMBRE = "titulaciones";
	
	// DAOs
	
	@Autowired
	private IRTitulacionDAO iRTitulacionDAO;
	public void setIRTitulacionDAO (IRTitulacionDAO iRTitulacionDAO) { this.iRTitulacionDAO = iRTitulacionDAO; }
	
	// Filters
	
	@Autowired
	private IFilterIRTitulacionNombre iFilterIRTitulacionNombre;
	public void setiFilterIRTitulacionNombre(IFilterIRTitulacionNombre iFilterIRTitulacionNombre) { this.iFilterIRTitulacionNombre = iFilterIRTitulacionNombre; }
	
	// Functions
	
	// Mappings
	
	// Get Titulacion
	
	@GetMapping
	public ModelAndView getTitulacionListForm (	@RequestParam(value="filter", required=false) String viewFilter,
												@RequestParam(value="page", required=false) String viewPage,
												@RequestParam(value="page_size_id", required=false) String viewPageSize) {
	
		List<IRTitulacion> iRTitulacionList = iRTitulacionDAO.getAll();
		
		iRTitulacionList = iFilterIRTitulacionNombre.cleanAndApply(iRTitulacionList, viewFilter, Operation.OR);
		
		return getListForm(NOMBRE, LINK_NOMBRE, viewFilter, viewPage, viewPageSize, iRTitulacionList);
		
	}

}
