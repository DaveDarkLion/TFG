package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IRDificultadAlumnoEjercicio extends IRichEntity <IDificultadAlumnoEjercicio> {

	public IRAlumno getIRAlumno ();
	
	public IREjercicio getIREjercicio ();
	
	public IRDificultad getIRDificultad ();
	public void setIRDificultad (IRDificultad iRDificultad);
	
}
