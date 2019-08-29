package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;

public interface ITitulacionDAO extends IDAO <ITitulacion> {

	public ITitulacion find (int key);
	
}
