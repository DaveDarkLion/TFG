package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;

public interface IRPersona extends IRSet<IPersona, IEjercicioPersona, IREjercicioPersona, IRPersona>, IRUser {

	public void setEmail (String email);
	
	public void setNombre (String nombre);
	
	public void setApellido1 (String apellido1);
	
	public void setApellido2 (String apellido2);
	
	public String getPassword ();
	public void setPassword (String password);
	
	public List<IREjercicio> getIREjerciciosPersona ();
	
	public boolean isAlumno ();
	public boolean isProfesor ();
	public boolean isAdministrador ();
	
}
