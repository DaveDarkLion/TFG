package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;

public interface IEjercicioPracticaEvaluacion extends IEjercicioSet, Serializable {

	public IPracticaEvaluacion getIPracticaEvaluacion ();
	public void setIPracticaEvaluacion (IPracticaEvaluacion iPracticaEvaluacion);

	public int getPosition ();
	public void setPosition (int position);
	
}
