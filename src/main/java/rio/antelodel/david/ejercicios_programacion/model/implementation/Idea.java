package rio.antelodel.david.ejercicios_programacion.model.implementation;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.IIdea;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;

@Entity
@Table(name="idea")
public class Idea implements IIdea {

	private static final long serialVersionUID = -2526820136631399875L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(targetEntity=Profesor.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="profesorEmail", referencedColumnName="email")
	private IProfesor iProfesor;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="ideaText")
	private String ideaText;

	public Idea () { }
	
	public Idea (String nombre, String idea, IProfesor iProfesor) {

		super();
		
		this.nombre = nombre;
		this.ideaText = idea;
		this.iProfesor = iProfesor;
		
	}
	
	@Override
	public int getId () { return id; }
	@Override
	public void setId (int id) { this.id = id; }
	
	@Override
	public String getNombre() { return nombre; }
	@Override
	public void setNombre(String nombre) { this.nombre = nombre; }

	@Override
	public String getIdeaText () { return ideaText; }
	@Override
	public void setIdeaText (String ideaText) { this.ideaText = ideaText; }
	
	@Override
	public IProfesor getIProfesor () { return iProfesor; }
	@Override
	public void setIProfesor (IProfesor iProfesor) { this.iProfesor = iProfesor; }

	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Idea) {
		
			Idea ejercicioIdea = (Idea)o;
			return getId() == ejercicioIdea.getId();
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getId());
		
	}
	
}
