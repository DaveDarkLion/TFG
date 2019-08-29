package rio.antelodel.david.ejercicios_programacion.model.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;

@Entity
@Table(name="examen")
public class Examen implements IExamen {

	private static final long serialVersionUID = -8002589334899937007L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="mes")
	private int mes;
	
	@Column(name="ano")
	private int ano;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@ManyToOne(targetEntity=Titulacion.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="titulacionId", referencedColumnName="id")
	private ITitulacion iTitulacion;
	
	@Column(name="visib")
	private boolean visible;
	
	@Column(name="abierto")
	private boolean abierto;
	
	@OneToMany(targetEntity=EjercicioExamen.class, mappedBy="iExamen", cascade=CascadeType.PERSIST, orphanRemoval=true)
	private Set<IEjercicioExamen> iEjerciciosExamen = new HashSet<>();

	public Examen () { }
	
	public Examen (int mes, int ano, String descripcion, ITitulacion iTitulacion, boolean visible, boolean abierto) {

		super();
		
		this.mes = mes;
		this.ano = ano;
		this.descripcion = descripcion;
		this.iTitulacion = iTitulacion;
		this.visible = visible;
		this.abierto = abierto;
		
	}

	@Override
	public int getId () { return id; }
	@Override
	public void setId (int id) { this.id = id; }

	@Override
	public int getMes () { return mes; }
	@Override
	public void setMes (int mes) { this.mes = mes; }

	@Override
	public int getAno () { return ano; }
	@Override
	public void setAno (int ano) { this.ano = ano; }

	@Override
	public String getDescripcion () { return descripcion; }
	@Override
	public void setDescripcion (String descripcion) { this.descripcion = descripcion; }

	@Override
	public ITitulacion getITitulacion () { return iTitulacion; }
	@Override
	public void setITitulacion (ITitulacion iTitulacion) { this.iTitulacion = iTitulacion; }
	
	@Override
	public boolean isVisible () { return visible; }
	@Override
	public void setVisible (boolean visible) { this.visible = visible; }
	
	@Override
	public boolean isAbierto () { return abierto; }
	@Override
	public void setAbierto (boolean abierto) { this.abierto = abierto; }

	@Override
	public Set<IEjercicioExamen> getIEjerciciosExamen () { return iEjerciciosExamen; }
	@Override
	public void setIEjerciciosExamen (Set<IEjercicioExamen> iEjerciciosExamen) { this.iEjerciciosExamen = iEjerciciosExamen; }

	@Override
	public Set<IEjercicio> getIEjercicios () {
		
		List<IEjercicio> ejerciciosList = new ArrayList<>();
		
		for (IEjercicioExamen eE : getIEjerciciosExamen())
			ejerciciosList.add(eE.getIEjercicio());
		
		return new HashSet<>(ejerciciosList);
		
	}
	
	@Override
	public Set<IEjercicioExamen> getIEjerciciosSet() {
		
		return getIEjerciciosExamen();
		
	}
	
	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof Examen) {
		
			Examen examen = (Examen)o;
			return getId() == examen.getId();
			
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getId());
		
	}
	
}
