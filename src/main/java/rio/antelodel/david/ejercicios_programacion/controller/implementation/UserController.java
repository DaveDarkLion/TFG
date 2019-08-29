package rio.antelodel.david.ejercicios_programacion.controller.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerData.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.container.user.AUserEditionController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@Controller
@RequestMapping("/usuarios")
@Secured(Role.ADMIN)
public class UserController extends AUserEditionController {

	// Element names
	
	protected static final String CAN_DELETE_NAME = "canDelete";
	
	// Data names
	
	protected static final String DATA_NAME = "data";
	
	// Views
	
	protected static final String VIEW_USER_EDIT = "admin/user/user-edit";
	
	// Functions
	
	// Mapping
	
	// Get User Edit Form
	
	@GetMapping(LINK_GENERIC_EMAIL + EXTENSION)
	@Transactional
	public ModelAndView getUserEditForm (@PathVariable String email) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// Not new
		
		model.put(IS_NEW_NAME, ViewBoolean.FALSE);
		
		try {
			
			IRPersona iRPersona = iRPersonaDAO.find(email);
		
			// Not profile
			
			model.put(IS_PROFILE, ViewBoolean.FALSE);
			
			// Not self
			
			model.put(CAN_DELETE_NAME, ViewBoolean.toViewBoolean(!getUser().equals(iRPersona)));
			
			// User
			
			model.put(DATA_NAME, iRPersona.getFullData().toString());
			
			// Roles
			
			model.put(ROLES_DATA_NAME, getGenericData(getRoleList()).toString());
			model.put(ROLES_CURRENT_DATA_NAME, getGenericData(getUserRoles(iRPersona)).toString());
			
			// Return MAV
			
			return getDefaultMAV(VIEW_USER_EDIT, model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to retrieve information from user: " + email + ", and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	@GetMapping(LINK_ADD)
	@Transactional
	public ModelAndView getUserNewForm () {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		// New
		
		model.put(IS_NEW_NAME, ViewBoolean.TRUE);
		
		// Not profile
		
		model.put(IS_PROFILE, ViewBoolean.FALSE);
		
		// User
		
		model.put(DATA_NAME, new JSONObject().toString());
		
		// Roles
		
		model.put(ROLES_DATA_NAME, getGenericData(getRoleList()).toString());
		model.put(ROLES_CURRENT_DATA_NAME, new JSONObject().toString());
	
		// Return MAV
		
		return getDefaultMAV(VIEW_USER_EDIT, model);
			
	}
	
	@PostMapping(LINK_ADD)
	@Transactional
	public ModelAndView createUser (@RequestParam("email") String viewEmail,
									@RequestParam("nombre") String viewNombre,
									@RequestParam("apellido1") String viewApellido1,
									@RequestParam("apellido2") String viewApellido2,
									@RequestParam("roles_id[]") String [] viewRolesId) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
				
			if (viewEmail.length() > 0) {
				
				// Create Password
				
				String password = iPasswordService.randomizePassword();
				
				// Create Persona
				
				IRPersona iRPersona = IRFactory.newIRPersona(viewEmail, Jsoup.parse(viewNombre).text(), Jsoup.parse(viewApellido1).text(), Jsoup.parse(viewApellido2).text(), password);
				
				// Save Persona
				
				saveNewUser(iRPersona, viewRolesId);
				
				// Return MAV
				
				CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " created the user with email: " + iRPersona.getEmail());
			
				return getMessageView("Usuario creado con éxito", model);
				
			}
			
			else {
				
				CustomLogger.LOGGER.info(CustomLogger.USER + getUser().getEmail() + " tried to add a new Persona with an empty email");
				return getGenericErrorView(model);
				
			}
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.info(CustomLogger.USER + getUser().getEmail() + " tried to add a new Persona and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	@PostMapping(LINK_GENERIC_EMAIL + EXTENSION)
	@Transactional
	public ModelAndView updateUser (@PathVariable String email,
									@RequestParam("nombre") String viewNombre,
									@RequestParam("apellido1") String viewApellido1,
									@RequestParam("apellido2") String viewApellido2,
									@RequestParam("roles_id[]") String [] viewRolesId) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
		
		try {
			
			// Update Persona
			
			IRPersona iRPersona = iRPersonaDAO.find(email);
			
			iRPersona.setNombre(Jsoup.parse(viewNombre).text());
			iRPersona.setApellido1(Jsoup.parse(viewApellido1).text());
			iRPersona.setApellido2(Jsoup.parse(viewApellido2).text());
			
			// Update Roles
			
			updateRoles(iRPersona, viewRolesId);
			
			// Update memory
			
			updateUserInMemory(iRPersona, viewRolesId);
			
			// Update Persona
			
			iRPersonaDAO.update(iRPersona);
			
			// Send update mail
			
			iUserMailHandler.sendProfileUpdateMail(iRPersona);
			
			// Return MAV
			
			CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " updated the user with email: " + iRPersona.getEmail());
		
			return getMessageView("Usuario actualizado con éxito", model);
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to update user " + email + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
	@PostMapping(LINK_GENERIC_EMAIL + LINK_DELETE + EXTENSION)
	@Transactional
	public ModelAndView deleteUser (@PathVariable String email) {
		
		// Setup
		
		Map<String, String> model = new HashMap<>();
		setup(model);
				
		try {
			
			// Get Persona
			
			IRPersona iRPersona = iRPersonaDAO.find(email);
			
			if (!getUser().equals(iRPersona)) {
			
			// Update memory
			
				deleteUserInMemory(iRPersona);
				
				// Send deletion mail
				
				iUserMailHandler.sendProfileDeletionMail(iRPersona);
				
				// Delete
				
				iRPersonaDAO.delete(iRPersona);
				
				// Return MAV
				
				CustomLogger.LOGGER.fine(CustomLogger.USER + getUser().getEmail() + " deleted the user with email: " + email);
				
				return getMessageView("Usuario eliminado con éxito", model);
				
			}
			
			else {
				
				CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete user self");
				return getGenericErrorView(model);
				
			}
			
		}
		
		catch (Exception e) {
			
			CustomLogger.LOGGER.severe(CustomLogger.USER + getUser().getEmail() + " tried to delete user: " + email + " and triggered the exception: " + e.toString());
			return getGenericErrorView(model);
			
		}
		
	}
	
}
