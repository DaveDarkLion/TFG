package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IAlumno extends IEntity, Serializable {

	public IPersona getIPersona ();
	public void setIPersona (IPersona iPersona);
	
	public Set<IDificultadAlumnoEjercicio> getIDificultadalumnoEjercicio ();
	public void setIDificultadalumnoEjercicio (Set<IDificultadAlumnoEjercicio> iDificultadAlumnoEjercicio);
	
}
