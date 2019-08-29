package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;

public interface IPersonaDAO extends IDAO <IPersona> {

	public IPersona find (String key);
	
}
