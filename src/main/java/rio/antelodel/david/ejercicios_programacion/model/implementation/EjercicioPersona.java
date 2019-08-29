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
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPersonaPK;

@Entity
@Table(name="ejercicioPersona")
@IdClass(IEjercicioPersonaPK.class)
public class EjercicioPersona implements IEjercicioPersona {
	
	private static final long serialVersionUID = -8869224056469341387L;

	@Id
	@ManyToOne(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ejercicioId", referencedColumnName="id")
	private IEjercicio iEjercicio;
	
	@Id
	@ManyToOne(targetEntity=Persona.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="personaEmail", referencedColumnName="email")
	private IPersona iPersona;
	
	@Column(name="posit")
	private int position;

	public EjercicioPersona () { }

	public EjercicioPersona(IEjercicio iEjercicio, IPersona iPersona, int position) {
		
		super();
		
		this.iEjercicio = iEjercicio;
		this.iPersona = iPersona;
		this.position = position;
		
	}

	@Override
	public IEjercicio getIEjercicio () { return iEjercicio; }
	@Override
	public void setIEjercicio (IEjercicio iEjercicio) { this.iEjercicio = iEjercicio; }

	@Override
	public IPersona getIPersona () { return iPersona; }
	@Override
	public void setIPersona (IPersona iPersona) { this.iPersona = iPersona; }

	@Override
	public int getPosition () { return position; }
	@Override
	public void setPosition (int position) { this.position = position; }

	// Equals
	
	@Override
	public boolean equals(Object o) {
		
		if (o instanceof EjercicioPersona) {
			
			EjercicioPersona ejercicioPersona = (EjercicioPersona)o;
			return getIPersona().equals(ejercicioPersona.getIPersona()) && getIEjercicio().equals(ejercicioPersona.getIEjercicio());
			
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getIEjercicio(), getIPersona());
		
	}
	
}
