package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

public interface IRProfesorDAO extends IRDAO <IRProfesor> {

	public IRProfesor find (String key);
	
}
