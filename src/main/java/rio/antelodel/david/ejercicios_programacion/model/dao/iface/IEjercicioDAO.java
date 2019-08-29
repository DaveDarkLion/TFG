package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;

public interface IEjercicioDAO extends IDAO <IEjercicio> {

	public IEjercicio find (int key);
	
}
