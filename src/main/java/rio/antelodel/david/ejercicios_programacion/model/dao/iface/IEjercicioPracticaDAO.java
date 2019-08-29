package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaPK;

public interface IEjercicioPracticaDAO extends IDAO <IEjercicioPractica> {

	public IEjercicioPractica find (IEjercicioPracticaPK key);
	
}
