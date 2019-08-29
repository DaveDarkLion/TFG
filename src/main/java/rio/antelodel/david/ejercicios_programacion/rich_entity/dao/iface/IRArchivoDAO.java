package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;

public interface IRArchivoDAO extends IRDAO <IRArchivo> {

	public IRArchivo find (int key);
	
}
