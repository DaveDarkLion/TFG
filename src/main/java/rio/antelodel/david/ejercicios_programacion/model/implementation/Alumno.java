package rio.antelodel.david.ejercicios_programacion.model.implementation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;

@Entity
@Table(name="alumno")
public class Alumno implements IAlumno {

	private static final long serialVersionUID = 2049889234750863031L;
	
	@Id
	private String email;
	
	@MapsId("email")
	@OneToOne(targetEntity=Persona.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="email")
	private IPersona iPersona;
	
	@OneToMany(targetEntity=DificultadAlumnoEjercicio.class, mappedBy="iAlumno", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IDificultadAlumnoEjercicio> iDificultadAlumnoEjercicio = new HashSet<>();
	
	public Alumno () { }
	
	public Alumno (IPersona iPersona) {
		
		super();
		
		this.iPersona = iPersona;
		this.email = iPersona.getEmail();
		
	}

	@Override
	public IPersona getIPersona () { return iPersona; }
	@Override
	public void setIPersona (IPersona iPersona) { this.iPersona = iPersona; }

	@Override
	public Set<IDificultadAlumnoEjercicio> getIDificultadalumnoEjercicio () { return iDificultadAlumnoEjercicio; }
	@Override
	public void setIDificultadalumnoEjercicio (Set<IDificultadAlumnoEjercicio> iDificultadAlumnoEjercicio) { this.iDificultadAlumnoEjercicio = iDificultadAlumnoEjercicio; }
	
	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Alumno) {
			
			Alumno alumno = (Alumno)o;
			return getIPersona().equals(alumno.getIPersona());
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getIPersona());
		
	}
	
}
