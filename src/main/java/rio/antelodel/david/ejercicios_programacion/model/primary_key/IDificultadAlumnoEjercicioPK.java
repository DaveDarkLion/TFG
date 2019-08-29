package rio.antelodel.david.ejercicios_programacion.model.primary_key;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;

public class IDificultadAlumnoEjercicioPK implements Serializable {

	private static final long serialVersionUID = 5559503979252689702L;

	private IAlumno iAlumno;
	private IEjercicio iEjercicio;

	public IDificultadAlumnoEjercicioPK () { }
	
	public IDificultadAlumnoEjercicioPK (IAlumno iAlumno, IEjercicio iEjercicio) {
		
		super();
		
		this.iAlumno = iAlumno;
		this.iEjercicio = iEjercicio;
		
	}

	public IAlumno getIAlumno () { return iAlumno; }

	public IEjercicio getIEjercicio () { return iEjercicio; }
	
}
