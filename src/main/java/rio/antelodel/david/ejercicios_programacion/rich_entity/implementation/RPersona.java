package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.helper.IREjercicioSetHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARSet;

public class RPersona extends ARSet < IPersona, IEjercicioPersona, IREjercicioPersona, IRPersona > implements IRPersona {

	// Constructors
	
	public RPersona (IPersona entity) {
		
		super(entity);
		
	}
	
	public RPersona (String email, String nombre, String apellido1, String apellido2, String password) {
		
		super(MFactory.newIPersona(email, nombre, apellido1, apellido2, password));
		
	}
	
	// Entity Functions

	@Override
	public String getEmail () { return getEntity().getEmail(); }
	@Override
	public void setEmail (String email) { getEntity().setEmail(email); }

	@Override
	public String getNombre () { return getEntity().getNombre(); }
	@Override
	public void setNombre (String nombre) { getEntity().setNombre(nombre); }

	@Override
	public String getApellido1 () { return getEntity().getApellido1(); }
	@Override
	public void setApellido1 (String apellido1) { getEntity().setApellido1(apellido1); }

	@Override
	public String getApellido2 () { return getEntity().getApellido2(); }
	@Override
	public void setApellido2 (String apellido2) { getEntity().setApellido2(apellido2); }
	
	@Override
	public String getPassword() { return getEntity().getPassword(); }
	@Override
	public void setPassword (String password) { getEntity().setPassword(password); }
	
	@Override
	public List<IREjercicio> getIREjerciciosPersona () { return IREjercicioSetHelper.getIREjercicios(IRFactory.getIREjercicioPersonaList(new ArrayList<>(getEntity().getIEjerciciosSet()))); }
	
	@Override
	public List<IREjercicioPersona> getIREjerciciosSet () {
		
		return IRFactory.getIREjercicioPersonaList(new ArrayList<>(getEntity().getIEjerciciosSet()));
		
	}
	
	// Role
	
	@Override
	public boolean isAlumno () { return getEntity().getIAlumno() != null; }
	@Override
	public boolean isProfesor () { return getEntity().getIProfesor() != null; }
	@Override
	public boolean isAdministrador () { return getEntity().getIAdministrador() != null; }
	
	// Data

	@Override
	public JSONObject getData () {

		JSONObject data = new JSONObject();
		
		data.put("email", org.json.simple.JSONObject.escape(getEmail()));
		data.put("nombre", org.json.simple.JSONObject.escape(getNombre()));
		data.put("apellido1", org.json.simple.JSONObject.escape(getApellido1()));
		data.put("apellido2", org.json.simple.JSONObject.escape(getApellido2()));
		
		return data;
	
	}

	@Override
	public JSONObject getFullData () {
		
		return getData();
		
	}
	
	// Comparable
	
	@Override
	public int compareTo (IRUser iRUser) {
		
		return (getApellido1() + getApellido2() + getNombre()).compareToIgnoreCase(iRUser.getApellido1() + iRUser.getApellido2() + iRUser.getNombre());
		
	}
	
	// User
	
	@Override
	public List<String> getRoles () {
		
		List<String> roles = new ArrayList<>();
		
		if (isAlumno()) roles.add(Role.ALUMNO);
		if (isProfesor()) roles.add(Role.PROFESOR);
		if (isAdministrador()) roles.add(Role.ADMIN);
		
		return roles;
		
	}
	
	@Override
	public IRPersona getIRPersona() {

		return this;
		
	}
	
	// Filters
	
	// IRUser
	
	@Override
	public List<IRUser> getFilterIRUser () {
		
		return Arrays.asList(this);
		
	}
	
}
