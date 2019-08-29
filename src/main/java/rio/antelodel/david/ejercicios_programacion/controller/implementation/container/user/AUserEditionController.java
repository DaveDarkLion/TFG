package rio.antelodel.david.ejercicios_programacion.controller.implementation.container.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import rio.antelodel.david.ejercicios_programacion.auxiliar.password_service.IPasswordService;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.utility.user_mail_handler.IUserMailHandler;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

public class AUserEditionController extends AUserController {

	// User Mail Handler
	
	@Autowired
	protected IUserMailHandler iUserMailHandler;
	public void setIUserMailHandler (IUserMailHandler iUserMailHandler) { this.iUserMailHandler = iUserMailHandler; }
	
	// Password Service
	
	@Autowired
	protected IPasswordService iPasswordService;
	public void setIPasswordService (IPasswordService iPasswordService) { this.iPasswordService = iPasswordService; }
	
	// In Memory User Manager
	
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	public void setInMemoryUserDetailsManager (InMemoryUserDetailsManager inMemoryUserDetailsManager) { this.inMemoryUserDetailsManager = inMemoryUserDetailsManager; }
	
	// Session Registry
	
	@Autowired
    private SessionRegistry sessionRegistry;
	public void setSessionRegistry (SessionRegistry sessionRegistry) { this.sessionRegistry = sessionRegistry; }
	
	// Functions
	
	// Save User
	
	protected boolean saveNewUser (IRPersona iRPersona, String [] roles) {
		
		if (iRPersonaDAO.getAll().contains(iRPersona)) return false;
		
		else {
			
			// Save Persona
			
			iRPersonaDAO.save(iRPersona);
			
			// Update Roles
			
			updateRoles(iRPersona, roles);
			
			// Update memory
			
			createUserInMemory(iRPersona, roles);
			
			// Send confirmation mail
			
			iUserMailHandler.sendConfirmationMail(iRPersona);
			
			return true;
			
		}
			
	}
	
	// Update Roles
	
	protected void updateRoles (IRPersona iRPersona, String [] rolesId) {
		
		// Email
		
		final String email = iRPersona.getEmail();
		
		// Role list
		
		List<String> roleList = Arrays.asList(rolesId);
		
		// Alumno
		
		if (roleList.contains(Role.ALUMNO)) { if (iRAlumnoDAO.find(email).isNull()) iRAlumnoDAO.save(IRFactory.newIRAlumno(iRPersona)); } 
		else if (!iRAlumnoDAO.find(email).isNull()) iRAlumnoDAO.delete(iRAlumnoDAO.find(email));
		
		// Profesor
		
		if (roleList.contains(Role.PROFESOR)) { if (iRProfesorDAO.find(email).isNull()) iRProfesorDAO.save(IRFactory.newIRProfesor(iRPersona)); } 
		else if (!iRProfesorDAO.find(email).isNull()) iRProfesorDAO.delete(iRProfesorDAO.find(email));
		
		// Administrador
		
		if (roleList.contains(Role.ADMIN)) { if (iRAdministradorDAO.find(email).isNull()) iRAdministradorDAO.save(IRFactory.newIRAdministrador(iRPersona)); } 
		else if (!iRAdministradorDAO.find(email).isNull()) iRAdministradorDAO.delete(iRAdministradorDAO.find(email));
		
	}
	
	// Update memory
	
	// Create User
	
	protected void createUserInMemory (IRPersona iRPersona, String [] roles) {

		User user = getUserInMemory(iRPersona, roles);
		inMemoryUserDetailsManager.createUser(user);
		
	}
	
	// Check if Roles are valid
	
	protected boolean checkRoles (String [] roles) {
		
		for (String role : roles)
			if (!role.equals(Role.ALUMNO) && !role.equals(Role.PROFESOR) && !role.equals(Role.ADMIN)) return false;
		
		return true;
		
	}
	
	// Update User
	
	protected void updateUserInMemory (IRPersona iRPersona, String [] rolesId) {
		
		expireUserSessions(iRPersona);
		
		User user = getUserInMemory(iRPersona, rolesId);
		inMemoryUserDetailsManager.updateUser(user);
		
	}
	
	// Delete User
	
	protected void deleteUserInMemory (IRPersona iRPersona) {
		
		expireUserSessions(iRPersona);
		
		inMemoryUserDetailsManager.deleteUser(iRPersona.getEmail());
		
	}
	
	// Get User
	
	protected User getUserInMemory (IRPersona iRPersona, String [] rolesId) {
		
		// Role list
		
		List<String> roleList = Arrays.asList(rolesId);
				
		// Authority List
				
		ArrayList<GrantedAuthority> authorityList = new ArrayList<>();
		for (String role : roleList) authorityList.add(new SimpleGrantedAuthority(role));
				
		// Create User
				
		return new User(iRPersona.getEmail(), "{noop}" + iRPersona.getPassword(), authorityList);
		
	}
	
	// Expire all sessions for Persona
    
    public void expireUserSessions(IRPersona iRPersona) {
    	
        for (Object principal : sessionRegistry.getAllPrincipals()) {
        	
            if (principal instanceof User) {
            	
                UserDetails userDetails = (UserDetails) principal;
                
                if (userDetails.getUsername().equals(iRPersona.getEmail())) {
                	
                    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, false)) {
                        information.expireNow();
                        
                    }
                    
                }
                
            }
            
        }
        
    }

	
}
