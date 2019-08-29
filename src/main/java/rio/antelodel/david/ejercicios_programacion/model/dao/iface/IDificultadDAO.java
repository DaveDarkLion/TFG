package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;

public interface IDificultadDAO extends IDAO <IDificultad> {

	public IDificultad find (int key);
	
}
