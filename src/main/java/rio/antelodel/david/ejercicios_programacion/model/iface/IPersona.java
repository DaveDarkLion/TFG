package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetEntity;

public interface IPersona extends ISetEntity <IEjercicioPersona>, Serializable {

	public String getEmail ();
	public void setEmail (String email);

	public String getNombre ();
	public void setNombre (String nombre);

	public String getApellido1 ();
	public void setApellido1 (String apellido1);

	public String getApellido2 ();
	public void setApellido2 (String apellido2);
	
	public String getPassword ();
	public void setPassword (String password);

	public IAlumno getIAlumno ();
	public void setIAlumno (IAlumno iAlumno);

	public IProfesor getIProfesor ();
	public void setIProfesor (IProfesor iProfesor);

	public IAdministrador getIAdministrador ();
	public void setIAdministrador (IAdministrador iAdministrador);
	
	public Set<IEjercicioPersona> getIEjerciciosPersona ();
	public void setIEjerciciosPersona (Set<IEjercicioPersona> iEjerciciosPersona);
	
	public Set<IEjercicioPersona> getIEjerciciosSet ();
	
}
