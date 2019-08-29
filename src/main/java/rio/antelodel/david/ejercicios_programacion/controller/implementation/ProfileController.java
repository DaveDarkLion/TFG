package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerData.getGenericData;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.getRoleList;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.auxiliar.password_service.IPasswordService;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@Controller
@RequestMapping("/profile")
@Secured({ Role.ALUMNO, Role.PROFESOR, Role.ADMIN })
public class ProfileController extends AController {

	// Configuration
	
	protected static final String REDIRECT_PAGE = "/ejercicios-programacion/index.html";
	
	// Element names
	
	protected static final String PASSWORD_WRONG_NAME = "wrongPassword";
	protected static final String PASSWORD_WRONG_FORMAT_NAME = "wrongPasswordFormat";
	protected static final String PASSWORDS_DO_NOT_MATCH_NAME = "passwordsDoNotMatch";
	
	// Data names
	
	protected static final String DATA_NAME = "data";
	
	// In Memory User Manager
	
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	public void setInMemoryUserDetailsManager (InMemoryUserDetailsManager inMemoryUserDetailsManager) { this.inMemoryUserDetailsManager = inMemoryUserDetailsManager; }
	
	// Password Service
	
	@Autowired
	protected IPasswordService iPasswordService;
	public void setIPasswordService (IPasswordService iPasswordService) { this.iPasswordService = iPasswordService; }
	
	// Views
	
	protected static final String VIEW_PROFILE = "/admin/user/user-edit";
	protected static final String VIEW_PASSWORD = "/admin/user/user-password";
	
	// Functions
	
	// Mapping
	
	@GetMapping
	public ModelAndView getProfileForm () {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get data
		
		try {
			
			model.put(IS_PROFILE, ViewBoolean.TRUE);
			model.put(DATA_NAME, getUser().getData().toString());
			
			// Roles
			
			model.put(ROLES_DATA_NAME, getGenericData(getRoleList()).toString());
			model.put(ROLES_CURRENT_DATA_NAME, getGenericData(getUserRoles(getUser())).toString());
			
			// Return MAV
			
			return getDefaultMAV(VIEW_PROFILE, model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe("User (possibly not in database) tried to access their profile, and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	@GetMapping("/password")
	public ModelAndView getPasswordForm () {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Set failure flags
		
		model.put(PASSWORD_WRONG_NAME, ViewBoolean.FALSE);
		model.put(PASSWORD_WRONG_FORMAT_NAME, ViewBoolean.FALSE);
		model.put(PASSWORDS_DO_NOT_MATCH_NAME, ViewBoolean.FALSE);
		
		// Return MAV
		
		return getDefaultMAV(VIEW_PASSWORD, model);
		
	}
	
	@PostMapping("/password")
	@Transactional
	public ModelAndView changePassword (@RequestParam("pass_old") String passOld,
										@RequestParam("pass_new1") String passNew1,
										@RequestParam("pass_new2") String passNew2) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
		
			// Get user
			
			IRPersona iRPersona = getUser();
			
			// Check success
			
			boolean success = true;
			
			if (!passOld.equals(iRPersona.getPassword())) {
				
				model.put(PASSWORD_WRONG_NAME, ViewBoolean.TRUE);
				success = false;
				
			}
			
			if (!passNew1.equals(passNew2)) {
				
				model.put(PASSWORDS_DO_NOT_MATCH_NAME, ViewBoolean.TRUE);
				success = false;
				
			}
			
			if (!iPasswordService.checkPasswordFormat(passNew1)) {
				
				model.put(PASSWORD_WRONG_FORMAT_NAME, ViewBoolean.TRUE);
				success = false;
				
			}
			
			if (success) {
				
				inMemoryUserDetailsManager.changePassword("{noop}" + iRPersona.getPassword(), "{noop}" + passNew1);
				
				iRPersona.setPassword(passNew1);
				iRPersonaDAO.update(iRPersona);
				
				// Return MAV
				
				return new ModelAndView(new RedirectView(REDIRECT_PAGE));
				
			}
			
			else return getDefaultMAV(VIEW_PASSWORD, model);
			
		}
	
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to change their password and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
}
