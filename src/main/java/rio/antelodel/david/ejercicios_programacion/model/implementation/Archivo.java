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

import rio.antelodel.david.ejercicios_programacion.model.iface.IArchivo;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;

@Entity
@Table(name="archivo")
public class Archivo implements IArchivo {

	private static final long serialVersionUID = 1600868061627025615L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="ruta")
	private String ruta;

	@ManyToMany(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinTable(name="archivoEjercicioEntrada", joinColumns = { @JoinColumn(name="archivoId") }, inverseJoinColumns = { @JoinColumn(name="ejercicioId") } )
	private Set<IEjercicio> iEjerciciosEntrada = new HashSet<>();
	
	@ManyToMany(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinTable(name="archivoEjercicioValidacion", joinColumns = { @JoinColumn(name="archivoId") }, inverseJoinColumns = { @JoinColumn(name="ejercicioId") } )
	private Set<IEjercicio> iEjerciciosValidacion = new HashSet<>();
	
	@ManyToMany(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinTable(name="archivoEjercicioSolucion", joinColumns = { @JoinColumn(name="archivoId") }, inverseJoinColumns = { @JoinColumn(name="ejercicioId") } )
	private Set<IEjercicio> iEjerciciosSolucion = new HashSet<>();
	
	public Archivo () { }
	
	public Archivo (String ruta) {
		
		super();

		this.ruta = ruta;
		
	}

	@Override
	public int getId () { return id; }
	@Override
	public void setId (int id) { this.id = id; }
	
	@Override
	public String getRuta () { return ruta; }
	@Override
	public void setRuta (String ruta) { this.ruta = ruta; }

	@Override
	public Set<IEjercicio> getIEjerciciosEntrada () { return iEjerciciosEntrada; }
	@Override
	public void setIEjerciciosEntrada (Set<IEjercicio> iEjerciciosEntrada) { this.iEjerciciosEntrada = iEjerciciosEntrada; }

	@Override
	public Set<IEjercicio> getIEjerciciosValidacion () { return iEjerciciosValidacion; }
	@Override
	public void setIEjerciciosValidacion (Set<IEjercicio> iEjerciciosValidacion) { this.iEjerciciosValidacion = iEjerciciosValidacion; }

	@Override
	public Set<IEjercicio> getIEjerciciosSolucion () { return iEjerciciosSolucion; }
	@Override
	public void setIEjerciciosSolucion (Set<IEjercicio> iEjerciciosSolucion) { this.iEjerciciosSolucion = iEjerciciosSolucion; }

	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Archivo) {
			
			Archivo archivo = (Archivo)o;
			return getId() == archivo.getId();
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getId());
		
	}
	
}
