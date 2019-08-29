package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;

public interface IRExamenDAO extends IRDAO <IRExamen> {

	public IRExamen find (int key);
	
}
