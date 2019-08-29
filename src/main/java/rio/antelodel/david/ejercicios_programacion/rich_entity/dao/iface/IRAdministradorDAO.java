package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAdministrador;

public interface IRAdministradorDAO extends IRDAO <IRAdministrador> {

	public IRAdministrador find (String key);
	
}
