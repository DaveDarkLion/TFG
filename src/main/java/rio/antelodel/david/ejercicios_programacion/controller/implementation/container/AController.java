package rio.antelodel.david.ejercicios_programacion.controller.implementation.container;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility;
import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IRDificultadHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

public abstract class AController {
	
	// Names
	
	// Element Names
	
	protected static final String IS_ALUMNO_NAME = "isAlumno";
	protected static final String IS_PROFESOR_NAME = "isProfesor";
	protected static final String IS_ADMIN_NAME = "isAdmin";
	
	// Data Names
	
	public static final String PROFILE_DATA_NAME = "profileData";
	
	// DAOs
	
	@Autowired
	protected IRPersonaDAO iRPersonaDAO;
	public void setIRPersonaDAO (IRPersonaDAO iRPersonaDAO) { this.iRPersonaDAO = iRPersonaDAO; }
	
	@Autowired
	protected IRDificultadDAO iRDificultadDAO;
	public void setIRDificultadDAO (IRDificultadDAO iRDificultadDAO) { this.iRDificultadDAO = iRDificultadDAO; }
	
	// Functions
	
	// Get SecurityContext
	
	private SecurityContext getSecurityContext () { return SecurityContextHolder.getContext(); }
	
	// Get user
	
	protected IRPersona getUser () {
	
		return iRPersonaDAO.find(getSecurityContext().getAuthentication().getName());
		
	}
	
	// View setup
	
	protected void setup (Map<String, String> model) {
		
		setProfileNavBar(model);
		setRoles(model);
		
	}
	
	protected void setProfileNavBar (Map<String, String> model) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		IRPersona iRPersona = iRPersonaDAO.find(email);
		
		if (!iRPersona.isNull()) model.put(PROFILE_DATA_NAME, iRPersona.getFullData().toString());
		
	}
	
	protected void setRoles (Map<String, String> model) {
		
		model.put(IS_ALUMNO_NAME,  ViewBoolean.toViewBoolean(userIsRole(Role.ALUMNO)));
		model.put(IS_PROFESOR_NAME,  ViewBoolean.toViewBoolean(userIsRole(Role.PROFESOR)));
		model.put(IS_ADMIN_NAME,  ViewBoolean.toViewBoolean(userIsRole(Role.ADMIN)));
		
	}
	
	// Check Roles
	
	protected boolean userIsRole (String role) {
		
		return ControllerUtility.userIsRole(role, getSecurityContext());
		
	}
	
	protected boolean userIsPrivileged () {
		
		return userIsRole(Role.PROFESOR) || userIsRole(Role.ADMIN);
		
	}
	
	// Dificultad
	
	protected IRDificultad getRDificultadAverage (List<IRDificultad> iRDificultadesCurrentList) {
		
		return IRDificultadHelper.getIRDificultadAverage(iRDificultadesCurrentList, iRDificultadDAO.getAll());
		
	}
	
}
