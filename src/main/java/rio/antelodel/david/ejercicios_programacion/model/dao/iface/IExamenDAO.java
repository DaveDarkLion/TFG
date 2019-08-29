package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;

public interface IExamenDAO extends IDAO <IExamen> {

	public IExamen find (int key);
	
}
