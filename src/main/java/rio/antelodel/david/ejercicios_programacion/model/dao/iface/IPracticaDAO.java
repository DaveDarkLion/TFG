package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;

public interface IPracticaDAO extends IDAO <IPractica> {

	public IPractica find (int key);
	
}
