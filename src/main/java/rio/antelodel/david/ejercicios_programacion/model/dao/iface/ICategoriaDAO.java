package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;

public interface ICategoriaDAO extends IDAO <ICategoria> {

	public ICategoria find (int key);
	
}
