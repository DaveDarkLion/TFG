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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;

@Entity
@Table(name="categoria")
public class Categoria extends Object implements ICategoria {

	private static final long serialVersionUID = -8869436335473694764L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;

	@ManyToMany(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinTable(name="categoriaEjercicio", joinColumns = { @JoinColumn(name="categoriaId") }, inverseJoinColumns = { @JoinColumn(name="ejercicioId") } )
	private Set<IEjercicio> iEjercicios = new HashSet<>();
	
	public Categoria () { }
	
	public Categoria (String nombre) {

		super();
		
		this.nombre = nombre;
		
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
	public Set<IEjercicio> getIEjercicios () { return iEjercicios; }
	@Override
	public void setIEjercicios (Set<IEjercicio> iEjercicios) { this.iEjercicios = iEjercicios; }
	
	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Categoria) {
		
			Categoria categoria = (Categoria)o;
			return getId() == categoria.getId();
			
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getId());
		
	}
	
}
