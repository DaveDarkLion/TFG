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
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaEvaluacionPK;

@Entity
@Table(name="ejercicioPracticaEvaluacion")
@IdClass(IEjercicioPracticaEvaluacionPK.class)
public class EjercicioPracticaEvaluacion implements IEjercicioPracticaEvaluacion {

	private static final long serialVersionUID = 7270057281402069383L;

	@Id
	@ManyToOne(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ejercicioId", referencedColumnName="id")
	private IEjercicio iEjercicio;
	
	@Id
	@ManyToOne(targetEntity=PracticaEvaluacion.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="practicaEvaluacionId", referencedColumnName="id")
	private IPracticaEvaluacion iPracticaEvaluacion;
	
	@Column(name="posit")
	private int position;

	public EjercicioPracticaEvaluacion () { }

	public EjercicioPracticaEvaluacion(IEjercicio iEjercicio, IPracticaEvaluacion iPracticaEvaluacion, int position) {
		
		super();
		
		this.iEjercicio = iEjercicio;
		this.iPracticaEvaluacion = iPracticaEvaluacion;
		this.position = position;
		
	}

	@Override
	public IEjercicio getIEjercicio () { return iEjercicio; }
	@Override
	public void setIEjercicio (IEjercicio iEjercicio) { this.iEjercicio = iEjercicio; }

	@Override
	public IPracticaEvaluacion getIPracticaEvaluacion () { return iPracticaEvaluacion; }
	@Override
	public void setIPracticaEvaluacion (IPracticaEvaluacion iPracticaEvaluacion) { this.iPracticaEvaluacion = iPracticaEvaluacion; }

	@Override
	public int getPosition () { return position; }
	@Override
	public void setPosition (int position) { this.position = position; }

	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof EjercicioPracticaEvaluacion) {
		
			EjercicioPracticaEvaluacion ejercicioPracticaEvaluacion = (EjercicioPracticaEvaluacion)o;
			
			return getIPracticaEvaluacion().equals(ejercicioPracticaEvaluacion.getIPracticaEvaluacion()) && getIEjercicio().equals(ejercicioPracticaEvaluacion.getIEjercicio());
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getIEjercicio(), getIPracticaEvaluacion());
		
	}
	
}
