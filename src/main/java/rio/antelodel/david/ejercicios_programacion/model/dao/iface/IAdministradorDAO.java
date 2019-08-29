package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAdministrador;

public interface IAdministradorDAO extends IDAO <IAdministrador> {

	public IAdministrador find (String key);
	
}
