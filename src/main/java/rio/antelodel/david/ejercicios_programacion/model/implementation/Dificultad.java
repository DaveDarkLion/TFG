package rio.antelodel.david.ejercicios_programacion.model.implementation;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;

@Entity
@Table(name="dificultad")
public class Dificultad implements IDificultad {

	private static final long serialVersionUID = -4780943909027155669L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="valor")
	private float valor;
	
	@OneToMany(targetEntity=Ejercicio.class, mappedBy="iDificultad", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IEjercicio> iEjercicios = new HashSet<>();

	public Dificultad () { }
	
	public Dificultad (String nombre, float valor) {

		super();
		
		this.nombre = nombre;
		this.valor = valor;
		
	}

	@Override
	public int getId () { return id; }
	@Override
	public void setId (int id) { this.id = id; }

	@Override
	public String getNombre () { return nombre; }
	@Override
	public void setNombre (String nombre) { this.nombre = nombre; }
	
	@Override
	public float getValor() { return valor; }
	@Override
	public void setValor(float valor) { this.valor = valor; }

	@Override
	public Set<IEjercicio> getIEjercicios() { return iEjercicios; }
	@Override
	public void setIEjercicios(Set<IEjercicio> iEjercicios) { this.iEjercicios = iEjercicios; }
	
	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Dificultad) {
		
			Dificultad dificultad = (Dificultad)o;
			return getId() == dificultad.getId();
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getId());
		
	}
	
}
