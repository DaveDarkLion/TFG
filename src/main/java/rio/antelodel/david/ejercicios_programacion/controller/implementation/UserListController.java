package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerData.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.user.AUserController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRUser;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;

@Controller
@RequestMapping("/usuarios")
@Secured(Role.ADMIN)
public class UserListController extends AUserController {
	
	// Element names
	
	protected static final String FILTER_NAME = "filter";
	
	// Data names
	
	protected static final String USERS_DATA_NAME = "usersData";
	
	// Filters
	
	@Autowired
	private IFilterIRUser iFilterIRUser;
	public void setIFilterIRUser (IFilterIRUser iFilterIRUser) { this.iFilterIRUser = iFilterIRUser; }
	
	// Views
	
	protected static final String VIEW_USER_LIST = "admin/user/user-list";
	
	// Functions
	
	// Mapping
	
	@GetMapping
	@Transactional
	public ModelAndView getUserListForm (@RequestParam(value="filter", required=false) String viewFilter,
											@RequestParam(value="role_id", required=false) String viewRoleId,
											@RequestParam(value="page", required=false) String viewPage,
											@RequestParam(value="page_size_id", required=false) String viewPageSize) {
	
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Roles data
		
		JSONObject rolesData = getRolesData(viewRoleId);
		model.put(ROLES_DATA_NAME, rolesData.toString());
		model.put(ROLE_ANY_NAME, ViewBoolean.TRUE);
		
		// Usuarios
		
		List<? extends IRUser> iRUserList = getUserList(viewRoleId);
		
		// Filter by name / email
		
		iRUserList = iFilterIRUser.cleanAndApply(iRUserList, viewFilter, Operation.OR);
		
		if (isValid(viewFilter)) model.put(FILTER_NAME, viewFilter);
		
		// Page data
		
		int page = getPageInfo(viewPage, 1);
		model.put(PAGE_NAME, Integer.toString(page));
				
		// Page size data
		
		int pageSize = getPageInfo(viewPageSize, PAGE_SIZE_DEFAULT);
		
		JSONObject pageSizeData = getPageSizeData(pageSize);
		model.put(PAGE_SIZES_DATA_NAME, pageSizeData.toString());
		
		// Page last data
		
		int pageLast = getPageLast(iRUserList, pageSize);
		model.put(PAGE_LAST_NAME, Integer.toString(pageLast));
		
		// Get RUsers paged list
		
		iRUserList = getPagedList(iRUserList, page, pageSize);
		model.put(USERS_DATA_NAME, getGenericListSortedData(iRUserList).toString());
		
		// Return MAV
		
		return new ModelAndView(VIEW_USER_LIST, MODEL_NAME, model);
		
	}
	
	// Get User List
	
	protected List<? extends IRUser> getUserList (String roleId) {
		
		List<? extends IRUser> iRUserList;
		
		if (isValid(roleId)) {
			
			switch (roleId) {
			
				case Role.ALUMNO 	: iRUserList = iRAlumnoDAO.getAll(); break;
				case Role.PROFESOR 	: iRUserList = iRProfesorDAO.getAll(); break;
				case Role.ADMIN 	: iRUserList = iRAdministradorDAO.getAll(); break;
				default 			: iRUserList = iRPersonaDAO.getAll(); break;
			
			}
			
		}
		
		else iRUserList = iRPersonaDAO.getAll();
		
		return iRUserList;
		
	}
	
	// Roles Data
	
	protected JSONObject getRolesData (String viewRoleId) {
	
		if (isValid(viewRoleId)) {
			
			Pair<String, String> currentRole;
			
			switch (viewRoleId) {
			
				case Role.ALUMNO 	: currentRole = PAIR_ROLE_ALUMNO; break;
				case Role.PROFESOR 	: currentRole = PAIR_ROLE_PROFESOR; break;
				case Role.ADMIN 	: currentRole = PAIR_ROLE_ADMIN; break;
				default 			: currentRole = PAIR_ROLE_ANY; break;
			
			}
			
			return getGenericData(currentRole, getRoleList());
			
		}
		
		else return getGenericData(PAIR_ROLE_ANY, getRoleList());
		
	}

}
