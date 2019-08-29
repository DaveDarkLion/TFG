package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
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

import rio.antelodel.david.ejercicios_programacion.auxiliar.password_service.IPasswordService;
import rio.antelodel.david.ejercicios_programacion.auxiliar.password_service.restoration.IPasswordRestorationService;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.AController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.utility.user_mail_handler.IUserMailHandler;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@Controller
@RequestMapping("/login")
public class LoginController extends AController {
	
	// Element names
	
	protected static final String FAILED_NAME = "failed";
	
	protected static final String EMAIL_NOT_IN_DB = "emailNotInDB";
	protected static final String NOT_ENOUGH_TIME = "notEnoughTime";
	
	// In Memory User Manager
	
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	public void setInMemoryUserDetailsManager (InMemoryUserDetailsManager inMemoryUserDetailsManager) { this.inMemoryUserDetailsManager = inMemoryUserDetailsManager; }
	
	// User Mail Handler
	
	@Autowired
	protected IUserMailHandler iUserMailHandler;
	public void setIUserMailHandler (IUserMailHandler iUserMailHandler) { this.iUserMailHandler = iUserMailHandler; }
	
	// Password Service
	
	@Autowired
	protected IPasswordService iPasswordService;
	public void setIPasswordService (IPasswordService iPasswordService) { this.iPasswordService = iPasswordService; }
	
	// Password Service
	
	@Autowired
	protected IPasswordRestorationService iPasswordRestorationService;
	public void setIPasswordRestorationService (IPasswordRestorationService iPasswordRestorationService) { this.iPasswordRestorationService = iPasswordRestorationService; }
	
	// Views
	
	protected static final String VIEW_LOGIN = "login/login";
	protected static final String VIEW_PASSWORD_RESTORATION = "login/password-restoration";
	
	// Functions
	
	// Mapping
	
	@GetMapping
	@Secured(Role.ANONYMOUS)
	public ModelAndView getLoginForm (@RequestParam(value="failed", required=false) String viewFailed) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Failed previous login
		
		model.put(FAILED_NAME, ViewBoolean.toViewBoolean(isValid(viewFailed)));
		
		// Return MAV
		
		return getDefaultMAV(VIEW_LOGIN, model);
		
	}
	
	@GetMapping("/restore-password")
	@Secured(Role.ANONYMOUS)
	public ModelAndView getPasswordRestorationForm () {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Set failure flags
		
		model.put(EMAIL_NOT_IN_DB, ViewBoolean.FALSE);
		model.put(NOT_ENOUGH_TIME, ViewBoolean.FALSE);
		
		// Return MAV
		
		return getDefaultMAV(VIEW_PASSWORD_RESTORATION, model);
		
	}
	
	@PostMapping("/restore-password")
	@Secured(Role.ANONYMOUS)
	@Transactional
	public ModelAndView restorePassword (@RequestParam("email") String email) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Get Persona
		
		IRPersona iRPersona = iRPersonaDAO.find(email);
		
		// Check success
		
		boolean success = true;
		
		if (iRPersona.isNull()) {
			
			model.put(EMAIL_NOT_IN_DB, ViewBoolean.TRUE);
			success = false;
			
		}
		
		else if (!iPasswordRestorationService.canRestore(iRPersona)) {
			
			model.put(NOT_ENOUGH_TIME, ViewBoolean.TRUE);
			success = false;

		}
		
		if (success) {
			
			String password = iPasswordService.randomizePassword();
			
			inMemoryUserDetailsManager.updatePassword(inMemoryUserDetailsManager.loadUserByUsername(iRPersona.getEmail()), "{noop}" + password);
			
			iRPersona.setPassword(password);
			
			iRPersonaDAO.update(iRPersona);
			
			iUserMailHandler.sendPasswordRestorationMail(iRPersona);
			
			iPasswordRestorationService.registerUser(iRPersona);
			
			// Return MAV
			
			return getMessageView("Le ha sido enviado un correo con su nuevo password", model);
			
		}
		
		else return getDefaultMAV(VIEW_PASSWORD_RESTORATION, model);
		
	}
	
}
