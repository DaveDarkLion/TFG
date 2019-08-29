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
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRCategoriaNombre;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;

@Controller
@RequestMapping("/categorias")
@Secured(Role.ADMIN)
public class CategoriaListController extends AAdminElementListController < IRCategoria > {

	// Constants

	private static final String NOMBRE = "categorías";
	private static final String LINK_NOMBRE = "categorias";
	
	// DAOs
	
	@Autowired
	private IRCategoriaDAO iRCategoriaDAO;
	public void setIRCategoriaDAO (IRCategoriaDAO iRCategoriaDAO) { this.iRCategoriaDAO = iRCategoriaDAO; }
	
	// Filters
	
	@Autowired
	private IFilterIRCategoriaNombre iFilterIRCategoriaNombre;
	public void setiFilterIRCategoriaNombre(IFilterIRCategoriaNombre iFilterIRCategoriaNombre) { this.iFilterIRCategoriaNombre = iFilterIRCategoriaNombre; }
	
	// Mappings
	
	// Get Categoria List
	
	@GetMapping
	public ModelAndView getCategoriaListForm (	@RequestParam(value="filter", required=false) String viewFilter,
												@RequestParam(value="page", required=false) String viewPage,
												@RequestParam(value="page_size_id", required=false) String viewPageSize) {
	
		List<IRCategoria> iRCategoriaList = iRCategoriaDAO.getAll();
		
		iRCategoriaList = iFilterIRCategoriaNombre.cleanAndApply(iRCategoriaList, viewFilter, Operation.OR);
		
		return getListForm(NOMBRE, LINK_NOMBRE, viewFilter, viewPage, viewPageSize, iRCategoriaList);
		
	}

}
