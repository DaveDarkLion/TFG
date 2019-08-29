package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;

public interface IRAlumnoDAO extends IRDAO <IRAlumno> {

	public IRAlumno find (String key);
	
}
