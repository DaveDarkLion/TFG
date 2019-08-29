package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;

public interface IRPracticaEvaluacionDAO extends IRDAO <IRPracticaEvaluacion> {

	public IRPracticaEvaluacion find (int key);
	
}
