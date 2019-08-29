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
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaPK;

@Entity
@Table(name="ejercicioPractica")
@IdClass(IEjercicioPracticaPK.class)
public class EjercicioPractica implements IEjercicioPractica {

	private static final long serialVersionUID = 7270057281402069383L;

	@Id
	@ManyToOne(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ejercicioId", referencedColumnName="id")
	private IEjercicio iEjercicio;
	
	@Id
	@ManyToOne(targetEntity=Practica.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="practicaId", referencedColumnName="id")
	private IPractica iPractica;
	
	@Column(name="posit")
	private int position;

	public EjercicioPractica () { }

	public EjercicioPractica (IEjercicio iEjercicio, IPractica iPractica, int position) {
		
		super();
		
		this.iEjercicio = iEjercicio;
		this.iPractica = iPractica;
		this.position = position;
		
	}

	@Override
	public IEjercicio getIEjercicio () { return iEjercicio; }
	@Override
	public void setIEjercicio (IEjercicio iEjercicio) { this.iEjercicio = iEjercicio; }

	@Override
	public IPractica getIPractica () { return iPractica; }
	@Override
	public void setIPractica (IPractica iPractica) { this.iPractica = iPractica; }

	@Override
	public int getPosition () { return position; }
	@Override
	public void setPosition (int position) { this.position = position; }

	// Equals
	
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof EjercicioPractica) {
		
			EjercicioPractica ejercicioPractica = (EjercicioPractica)o;
			return getIPractica().equals(ejercicioPractica.getIPractica()) && getIEjercicio().equals(ejercicioPractica.getIEjercicio());
		
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getIEjercicio(), getIPractica());
		
	}
	
}
