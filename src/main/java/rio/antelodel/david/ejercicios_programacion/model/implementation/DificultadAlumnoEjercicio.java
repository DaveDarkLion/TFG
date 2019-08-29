package rio.antelodel.david.ejercicios_programacion.model.implementation;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IDificultadAlumnoEjercicioPK;

@Entity
@Table(name="dificultadAlumnoEjercicio")
@IdClass(IDificultadAlumnoEjercicioPK.class)
public class DificultadAlumnoEjercicio implements IDificultadAlumnoEjercicio {
	
	private static final long serialVersionUID = -111454640507305257L;
	
	@Id
	@ManyToOne(targetEntity=Alumno.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="alumnoEmail", referencedColumnName="email")
	private IAlumno iAlumno;
	
	@Id
	@ManyToOne(targetEntity=Ejercicio.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="ejercicioId", referencedColumnName="id")
	private IEjercicio iEjercicio;
	
	@ManyToOne(targetEntity=Dificultad.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name="dificultadId", referencedColumnName="id")
	private IDificultad iDificultad;

	public DificultadAlumnoEjercicio () { }

	public DificultadAlumnoEjercicio (IAlumno iAlumno, IEjercicio iEjercicio, IDificultad iDificultad) {
		
		super();
		
		this.iAlumno = iAlumno;
		this.iEjercicio = iEjercicio;
		this.iDificultad = iDificultad;
		
	}

	@Override
	public IAlumno getIAlumno () { return iAlumno; }
	@Override
	public void setIAlumno (IAlumno iAlumno) { this.iAlumno = iAlumno; }

	@Override
	public IEjercicio getIEjercicio () { return iEjercicio; }
	@Override
	public void setIEjercicio (IEjercicio iEjercicio) { this.iEjercicio = iEjercicio; }

	@Override
	public IDificultad getIDificultad () { return iDificultad; }
	@Override
	public void setIDificultad (IDificultad iDificultad) { this.iDificultad = iDificultad; }
	
	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof DificultadAlumnoEjercicio) {
		
			DificultadAlumnoEjercicio dae = (DificultadAlumnoEjercicio)o;
			return getIAlumno().equals(dae.getIAlumno()) && getIEjercicio().equals(dae.getIEjercicio());
			
		}
		
		else return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {
			
		return Objects.hash(getIAlumno(), getIEjercicio());
			
	}
	
}
