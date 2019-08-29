package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;

public interface IPracticaEvaluacionDAO extends IDAO <IPracticaEvaluacion> {

	public IPracticaEvaluacion find (int key);
	
}
