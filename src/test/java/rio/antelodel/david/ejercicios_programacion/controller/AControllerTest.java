package rio.antelodel.david.ejercicios_programacion.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAdministrador;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public abstract class AControllerTest extends ATest {

	// Authentication
	
	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	@Autowired
	private AuthenticationManager authManager;
	
	// Test User
	
	private IRPersona iRPersona;
	
	private IRAlumno iRAlumno;
	private IRProfesor iRProfesor;
	private IRAdministrador iRAdministrador;
	
	// DAOs
	
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	
	@Autowired
	private IRAlumnoDAO iRAlumnoDAO;
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	@Autowired
	private IRAdministradorDAO iRAdministradorDAO;
	
	// Functions
	
	protected String loginAsAlumno () {
		
		return loginAsTestUser(new ArrayList<>(Arrays.asList(Role.ALUMNO)));
		
	}
	
	protected String loginAsProfesor () {
		
		return loginAsTestUser(new ArrayList<>(Arrays.asList(Role.PROFESOR)));
		
	}
	
	protected String loginAsAdministrador () {
		
		return loginAsTestUser(new ArrayList<>(Arrays.asList(Role.ADMIN)));
		
	}
	
	protected String loginAsTestUser (List<String> roles) {
		
		iRPersona = IRFactory.newIRPersona("testuser@test.com", "TestUser", "TestUser", "TestUser", "1234");
		iRPersonaDAO.save(iRPersona);
		
		updateDBUser(roles, iRPersona);
		
		List<GrantedAuthority> a = new ArrayList<GrantedAuthority>();
		for (String role : roles) a.add(new SimpleGrantedAuthority(role));
		
		User user = new User(iRPersona.getEmail(), "{noop}" + iRPersona.getPassword(), a);
		
		inMemoryUserDetailsManager.createUser(user);
		
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(iRPersona.getEmail(), iRPersona.getPassword());
		Authentication auth = authManager.authenticate(authReq);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
		
		return iRPersona.getEmail();
		
	}
	
	public void logout () {
		
		SecurityContext sc = SecurityContextHolder.getContext();
		
		String userName = sc.getAuthentication().getName();

		sc.setAuthentication(null);
		
		inMemoryUserDetailsManager.deleteUser(userName);
		
	}
	
	private void updateDBUser (List<String> roles, IRPersona iRPersona) {
		
		if (roles.contains(Role.ALUMNO)) {
			
			iRAlumno = IRFactory.newIRAlumno(iRPersona);
			iRAlumnoDAO.save(iRAlumno);
			
		}
		
		if (roles.contains(Role.PROFESOR)) {
			
			iRProfesor = IRFactory.newIRProfesor(iRPersona);
			iRProfesorDAO.save(iRProfesor);
			
		}
		
		if (roles.contains(Role.ADMIN)) {
			
			iRAdministrador = IRFactory.newIRAdministrador(iRPersona);
			iRAdministradorDAO.save(iRAdministrador);
			
		}
		
	}
	
	// Parse ModelAndView model
	
	protected Map<String, String> parseModel (String text) {
	
		Map<String, String> model = new HashMap<>();
		
		int i = 1;
		
		char c;
		
		String currentKey = "";
		String currentValue = "";
		boolean value = false;
		
		while ((c = text.charAt(i)) != '}') {
			
			if (c == '=') {
				
				value = true;
				i++;
				
			}
			
			else if (c == ',') {
				
				model.put(currentKey, currentValue);
				
				currentKey = "";
				currentValue = "";
				value = false;
				
				i += 2;
				
			}
			
			else {
				
				if (value) currentValue += c;
				else currentKey += c;
				
				i++;
				
			}
			
		}
		
		model.put(currentKey, currentValue);
		
		return model;
		
	}
	
	protected JSONObject getJSONObject (Map<?, ?> originalMap) {
	
		String jsonString = "";
		
		String original = new JSONObject(originalMap).toString();
		
		int i = 0;
		
		while(i < original.length() - 1) {
			
			char c = original.charAt(i);
			
			if (c == '\"' && (original.charAt(i-1) == ']' || original.charAt(i-1) == '}' || original.charAt(i+1) == '[' || original.charAt(i+1) == '{')) i++;
			
			else {
				
				jsonString += c;
				i++;
				
			}
			
		}
		
		jsonString += '}';
		
		jsonString = jsonString.replace("\\", "");
		
		return new JSONObject(jsonString);
		
	}

}
