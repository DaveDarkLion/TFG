package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

public interface IRTitulacionDAO extends IRDAO <IRTitulacion> {

	public IRTitulacion find (int key);
	
}
