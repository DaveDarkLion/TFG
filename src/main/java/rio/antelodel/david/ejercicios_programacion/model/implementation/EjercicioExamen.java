package rio.antelodel.david.ejercicios_programacion.model.implementation;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioExamenPK;

@Entity
@Table(name="ejercicioExamen")
@IdClass(IEjercicioExamenPK.class)
public class EjercicioExamen implements IEjercicioExamen {
	
	private static final long serialVersionUID = -8869224056469341387L;

	@Id
	@ManyToOne(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ejercicioId", referencedColumnName="id")
	private IEjercicio iEjercicio;
	
	@Id
	@ManyToOne(targetEntity=Examen.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="examenId", referencedColumnName="id")
	private IExamen iExamen;
	
	@Column(name="posit")
	private int position;

	public EjercicioExamen () { }

	public EjercicioExamen(IEjercicio iEjercicio, IExamen iExamen, int position) {
		
		super();
		
		this.iEjercicio = iEjercicio;
		this.iExamen = iExamen;
		this.position = position;
		
	}

	@Override
	public IEjercicio getIEjercicio () { return iEjercicio; }
	@Override
	public void setIEjercicio (IEjercicio iEjercicio) { this.iEjercicio = iEjercicio; }

	@Override
	public IExamen getIExamen () { return iExamen; }
	@Override
	public void setIExamen (IExamen iExamen) { this.iExamen = iExamen; }

	@Override
	public int getPosition () { return position; }
	@Override
	public void setPosition (int position) { this.position = position; }

	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof EjercicioExamen) {
		
			EjercicioExamen ejercicioExamen = (EjercicioExamen)o;
			return getIExamen().equals(ejercicioExamen.getIExamen()) && getIEjercicio().equals(ejercicioExamen.getIEjercicio());
			
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getIEjercicio(), getIExamen());
		
	}
	
}
