package rio.antelodel.david.ejercicios_programacion.model.implementation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.IAdministrador;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;

@Entity
@Table(name="persona")
public class Persona implements IPersona {

	private static final long serialVersionUID = -313217908333930715L;

	@Id
	@Column(name="email")
	private String email;

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido1")
	private String apellido1;
	
	@Column(name="apellido2")
	private String apellido2;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(targetEntity=Alumno.class, mappedBy="iPersona", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private IAlumno iAlumno;
	
	@OneToOne(targetEntity=Profesor.class, mappedBy="iPersona", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private IProfesor iProfesor;
	
	@OneToOne(targetEntity=Administrador.class, mappedBy="iPersona", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private IAdministrador iAdministrador;
	
	@OneToMany(targetEntity=EjercicioPersona.class, mappedBy="iPersona", cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Set<IEjercicioPersona> iEjerciciosPersona = new HashSet<>();
	
	public Persona () { }
	
	public Persona (String email, String nombre, String apellido1, String apellido2, String password) {
		
		super();
		
		this.email = email;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.password = password;
		
	}
	
	@Override
	public String getEmail () { return email; }
	@Override
	public void setEmail (String email) { this.email = email; }

	@Override
	public String getNombre () { return nombre; }
	@Override
	public void setNombre (String nombre) { this.nombre = nombre; }

	@Override
	public String getApellido1 () { return apellido1; }
	@Override
	public void setApellido1 (String apellido1) { this.apellido1 = apellido1; }

	@Override
	public String getApellido2 () { return apellido2; }
	@Override
	public void setApellido2 (String apellido2) { this.apellido2 = apellido2; }
	
	@Override
	public String getPassword () { return password; }
	@Override
	public void setPassword (String password) { this.password = password; }

	@Override
	public IAlumno getIAlumno () { return iAlumno; }
	@Override
	public void setIAlumno (IAlumno iAlumno) { this.iAlumno = iAlumno; }

	@Override
	public IProfesor getIProfesor () { return iProfesor; }
	@Override
	public void setIProfesor (IProfesor iProfesor) { this.iProfesor = iProfesor; }

	@Override
	public IAdministrador getIAdministrador () { return iAdministrador; }
	@Override
	public void setIAdministrador (IAdministrador iAdministrador) { this.iAdministrador = iAdministrador; }
	
	@Override
	public Set<IEjercicioPersona> getIEjerciciosPersona () { return iEjerciciosPersona; }
	@Override
	public void setIEjerciciosPersona (Set<IEjercicioPersona> iEjerciciosPersona) { this.iEjerciciosPersona = iEjerciciosPersona; }
	
	@Override
	public Set<IEjercicioPersona> getIEjerciciosSet() {
		
		return getIEjerciciosPersona();
		
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof Persona) {
		
			Persona persona = (Persona)o;
			return getEmail().equals(persona.getEmail());
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getEmail());
		
	}
	
}
