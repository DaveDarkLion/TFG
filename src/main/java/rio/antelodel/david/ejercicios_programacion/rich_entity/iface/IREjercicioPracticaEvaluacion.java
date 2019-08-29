package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioComplexSet;

public interface IREjercicioPracticaEvaluacion extends IREjercicioComplexSet <IEjercicioPracticaEvaluacion, IREjercicioPracticaEvaluacion> {

	public IRPracticaEvaluacion getIRPracticaEvaluacion ();
	public void setIRPracticaEvaluacion (IRPracticaEvaluacion iRPracticaEvaluacion);
	
}
