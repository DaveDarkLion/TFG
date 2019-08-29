package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

public interface IRPersonaDAO extends IRDAO <IRPersona> {

	public IRPersona find (String key);
	
}
