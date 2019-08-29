package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IDificultadAlumnoEjercicio extends IEntity, Serializable {

	public IAlumno getIAlumno ();
	public void setIAlumno (IAlumno iAlumno);

	public IEjercicio getIEjercicio ();
	public void setIEjercicio (IEjercicio iEjercicio);

	public IDificultad getIDificultad ();
	public void setIDificultad (IDificultad iDificultad);
	
}
