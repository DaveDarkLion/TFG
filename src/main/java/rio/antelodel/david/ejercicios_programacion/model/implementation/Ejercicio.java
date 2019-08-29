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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import rio.antelodel.david.ejercicios_programacion.model.iface.IArchivo;
import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;

@Entity
@Table(name="ejercicio")
@Transactional
public class Ejercicio implements IEjercicio {

	private static final long serialVersionUID = -5363793923487690990L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="enunciado")
	private String enunciado;
	
	@ManyToOne(targetEntity=Profesor.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="profesorEmail", referencedColumnName="email")
	private IProfesor iProfesor;
	
	@ManyToMany(targetEntity=Categoria.class, cascade=CascadeType.PERSIST)
    @JoinTable(name="categoriaEjercicio", joinColumns = { @JoinColumn(name="ejercicioId") }, inverseJoinColumns = { @JoinColumn(name="categoriaId") } )
	private Set<ICategoria> iCategorias = new HashSet<>();
	
	@ManyToOne(targetEntity=Dificultad.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="dificultadId", referencedColumnName="id")
	private IDificultad iDificultad;
	
	@Column(name="visib")
	private boolean visible;

	@ManyToMany(targetEntity=Archivo.class, cascade=CascadeType.REMOVE)
    @JoinTable(name="archivoEjercicioEntrada", joinColumns = { @JoinColumn(name="ejercicioId") }, inverseJoinColumns = { @JoinColumn(name="archivoId") } )
	private Set<IArchivo> iArchivosEntrada = new HashSet<>();
	
	@ManyToMany(targetEntity=Archivo.class, cascade=CascadeType.REMOVE)
    @JoinTable(name="archivoEjercicioValidacion", joinColumns = { @JoinColumn(name="ejercicioId") }, inverseJoinColumns = { @JoinColumn(name="archivoId") } )
	private Set<IArchivo> iArchivosValidacion = new HashSet<>();
	
	@ManyToMany(targetEntity=Archivo.class, cascade=CascadeType.REMOVE)
    @JoinTable(name="archivoEjercicioSolucion", joinColumns = { @JoinColumn(name="ejercicioId") }, inverseJoinColumns = { @JoinColumn(name="archivoId") } )
	private Set<IArchivo> iArchivosSolucion = new HashSet<>();
	
	@OneToMany(targetEntity=EjercicioExamen.class, mappedBy="iEjercicio", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IEjercicioExamen> iEjercicioExamenes = new HashSet<>();
	
	@OneToMany(targetEntity=EjercicioPractica.class, mappedBy="iEjercicio", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IEjercicioPractica> iEjercicioPracticas = new HashSet<>();
	
	@OneToMany(targetEntity=EjercicioPracticaEvaluacion.class, mappedBy="iEjercicio", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IEjercicioPracticaEvaluacion> iEjercicioPracticasEvaluacion = new HashSet<>();
	
	@OneToMany(targetEntity=DificultadAlumnoEjercicio.class, mappedBy="iEjercicio", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private Set<IDificultadAlumnoEjercicio> iDificultadAlumnoEjercicio = new HashSet<>();
	
	@ManyToMany(targetEntity=Persona.class, cascade=CascadeType.PERSIST)
	@JoinTable(name="ejercicioPersona", joinColumns = { @JoinColumn(name="ejercicioId") }, inverseJoinColumns = { @JoinColumn(name="email") })
	private Set<IPersona> iPersonas = new HashSet<>();
	
	public Ejercicio () { }
	
	public Ejercicio (String titulo, String enunciado, IProfesor iProfesor, IDificultad iDificultad, boolean visible) {

		super();
		
		this.titulo = titulo;
		this.enunciado = enunciado;
		this.iProfesor = iProfesor;
		this.iDificultad = iDificultad;
		this.visible = visible;
		
	}

	@Override
	public int getId () { return id; }
	@Override
	public void setId (int id) { this.id = id; }

	@Override
	public String getEnunciado () { return enunciado; }
	@Override
	public void setEnunciado (String enunciado) { this.enunciado = enunciado; }
	
	@Override
	public String getTitulo () { return titulo; }
	@Override
	public void setTitulo (String titulo) { this.titulo = titulo; }
	
	@Override
	public IProfesor getIProfesor () { return iProfesor; }
	@Override
	public void setIProfesor (IProfesor iProfesor) { this.iProfesor = iProfesor; }

	@Override
	public Set<ICategoria> getICategorias () { return iCategorias; }
	@Override
	public void setICategorias (Set<ICategoria> iCategorias) { this.iCategorias = iCategorias; }

	@Override
	public IDificultad getIDificultad () { return iDificultad; }
	@Override
	public void setIDificultad (IDificultad iDificultad) { this.iDificultad = iDificultad; }

	@Override
	public boolean isVisible () { return visible; }
	@Override
	public void setVisible (boolean visible) { this.visible = visible; }
	
	@Override
	public Set<IArchivo> getIArchivosEntrada () { return iArchivosEntrada; }
	@Override
	public void setIArchivosEntrada (Set<IArchivo> iArchivosEntrada) { this.iArchivosEntrada = iArchivosEntrada; }

	@Override
	public Set<IArchivo> getIArchivosValidacion () { return iArchivosValidacion; }
	@Override
	public void setIArchivosValidacion (Set<IArchivo> iArchivosValidacion) { this.iArchivosValidacion = iArchivosValidacion; }

	@Override
	public Set<IArchivo> getIArchivosSolucion () { return iArchivosSolucion; }
	@Override
	public void setIArchivosSolucion (Set<IArchivo> iArchivosSolucion) { this.iArchivosSolucion = iArchivosSolucion; }

	@Override
	public Set<IEjercicioExamen> getIEjercicioExamenes () { return iEjercicioExamenes; }
	@Override
	public void setIEjercicioExamenes (Set<IEjercicioExamen> iEjercicioExamenes) { this.iEjercicioExamenes = iEjercicioExamenes; }
	
	@Override
	public Set<IEjercicioPractica> getIEjercicioPracticas () { return iEjercicioPracticas; }
	@Override
	public void setIEjercicioPracticas (Set<IEjercicioPractica> iEjercicioPracticas) { this.iEjercicioPracticas = iEjercicioPracticas; }
	
	@Override
	public Set<IEjercicioPracticaEvaluacion> getIEjercicioPracticasEvaluacion () { return iEjercicioPracticasEvaluacion; }
	@Override
	public void setIEjercicioPracticasEvaluacion (Set<IEjercicioPracticaEvaluacion> iEjercicioPracticasEvaluacion) { this.iEjercicioPracticasEvaluacion = iEjercicioPracticasEvaluacion; }

	@Override
	public Set<IDificultadAlumnoEjercicio> getIDificultadAlumnoEjercicio () { return iDificultadAlumnoEjercicio; }
	@Override
	public void setIDificultadAlumnoEjercicio (Set<IDificultadAlumnoEjercicio> iDificultadAlumnoEjercicio) { this.iDificultadAlumnoEjercicio = iDificultadAlumnoEjercicio; }

	@Override
	public Set<IPersona> getIPersonas () { return iPersonas; }
	@Override
	public void setIPersonas (Set<IPersona> iPersonas) { this.iPersonas = iPersonas; }

	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Ejercicio) {
			
			Ejercicio ejercicio = (Ejercicio)o;
			return getId() == ejercicio.getId();
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getId());
		
	}

}
