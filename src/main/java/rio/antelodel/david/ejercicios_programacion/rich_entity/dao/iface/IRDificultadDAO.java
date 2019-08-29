package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

public interface IRDificultadDAO extends IRDAO <IRDificultad> {

	public IRDificultad find (int key);
	
}
