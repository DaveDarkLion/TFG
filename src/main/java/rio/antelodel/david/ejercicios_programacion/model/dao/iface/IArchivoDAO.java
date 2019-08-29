package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IArchivo;

public interface IArchivoDAO extends IDAO <IArchivo> {

	public IArchivo find (int key);
	
}
