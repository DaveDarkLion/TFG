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

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IIdea;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;

@Entity
@Table(name="profesor")
public class Profesor implements IProfesor {

	private static final long serialVersionUID = -2526820136631399875L;

	@Id
	private String email;
	
	@MapsId("email")
	@OneToOne(targetEntity=Persona.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="email")
	private IPersona iPersona;

	@OneToMany(targetEntity=Ejercicio.class, mappedBy="iProfesor", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IEjercicio> iEjercicios;
	
	@OneToMany(targetEntity=Idea.class, mappedBy="iProfesor", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IIdea> iIdeas = new HashSet<>();
	
	public Profesor () { }
	
	public Profesor (IPersona iPersona) {
		
		super();
		
		this.iPersona = iPersona;
		
		this.email = iPersona.getEmail();
		
	}
	
	@Override
	public IPersona getIPersona() { return iPersona; }
	@Override
	public void setIPersona (IPersona iPersona) { this.iPersona = iPersona; }

	@Override
	public Set<IEjercicio> getIEjercicios () { return iEjercicios; }
	@Override
	public void setIEjercicios (Set<IEjercicio> iEjercicios) { this.iEjercicios = iEjercicios; }
	
	@Override
	public Set<IIdea> getIIdeas () { return iIdeas; }
	@Override
	public void setIIdeas (Set<IIdea> iIdeas) { this.iIdeas = iIdeas; }

	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Profesor) {
		
			Profesor profesor = (Profesor)o;
			return getIPersona().equals(profesor.getIPersona());
			
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getIPersona());
		
	}
	
}
