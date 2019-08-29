package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;

public interface IREjercicioPracticaEvaluacionDAO extends IRDAO <IREjercicioPracticaEvaluacion> {

	public IREjercicioPracticaEvaluacion find (IREjercicio iREjercicio, IRPracticaEvaluacion iRPracticaEvaluacion);
	
}
