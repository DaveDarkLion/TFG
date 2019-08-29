package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;

public interface IRCategoriaDAO extends IRDAO <IRCategoria> {

	public IRCategoria find (int key);
	
}
