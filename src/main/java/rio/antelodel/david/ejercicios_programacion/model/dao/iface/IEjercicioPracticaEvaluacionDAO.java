package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaEvaluacionPK;

public interface IEjercicioPracticaEvaluacionDAO extends IDAO <IEjercicioPracticaEvaluacion> {

	public IEjercicioPracticaEvaluacion find (IEjercicioPracticaEvaluacionPK key);
	
}
