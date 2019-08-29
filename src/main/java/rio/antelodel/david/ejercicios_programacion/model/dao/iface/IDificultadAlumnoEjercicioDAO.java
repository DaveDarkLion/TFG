package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IDificultadAlumnoEjercicioPK;

public interface IDificultadAlumnoEjercicioDAO extends IDAO <IDificultadAlumnoEjercicio> {

	public IDificultadAlumnoEjercicio find (IDificultadAlumnoEjercicioPK key);
	
}
