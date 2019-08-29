package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public interface IRDificultadAlumnoEjercicioDAO extends IRDAO <IRDificultadAlumnoEjercicio> {

	public IRDificultadAlumnoEjercicio find (IRAlumno iRAlumno, IREjercicio iREjercicio);
	
}
