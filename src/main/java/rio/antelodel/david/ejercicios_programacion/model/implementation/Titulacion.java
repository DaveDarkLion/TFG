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

import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;

@Entity
@Table(name="titulacion")
public class Titulacion implements ITitulacion {

	private static final long serialVersionUID = 4048356457875289020L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;

	@OneToMany(targetEntity=Examen.class, mappedBy="iTitulacion", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IExamen> iExamenes = new HashSet<>();
	
	@OneToMany(targetEntity=Practica.class, mappedBy="iTitulacion", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IPractica> iPracticas = new HashSet<>();
	
	@OneToMany(targetEntity=PracticaEvaluacion.class, mappedBy="iTitulacion", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IPracticaEvaluacion> iPracticasEvaluacion = new HashSet<>();
	
	public Titulacion () { }
	
	public Titulacion (String nombre) {

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
	public Set<IExamen> getIExamenes () { return iExamenes; }
	@Override
	public void setIExamenes (Set<IExamen> iExamenes) { this.iExamenes = iExamenes; }
	
	@Override
	public Set<IPractica> getIPracticas () { return iPracticas; }
	@Override
	public void setIPracticas (Set<IPractica> iPracticas) { this.iPracticas = iPracticas; }
	
	@Override
	public Set<IPracticaEvaluacion> getIPracticasEvaluacion () { return iPracticasEvaluacion; }
	@Override
	public void setIPracticasEvaluacion (Set<IPracticaEvaluacion> iPracticasEvaluacion) { this.iPracticasEvaluacion = iPracticasEvaluacion; }

	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Titulacion) {
		
			Titulacion titulacion = (Titulacion)o;
			return getId() == titulacion.getId();
			
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getId());
		
	}
	
}
