package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetComplexEntity;

public interface IPracticaEvaluacion extends ISetComplexEntity <IEjercicioPracticaEvaluacion>, Serializable {

	public void setId (int id);
	
	public Set<IEjercicioPracticaEvaluacion> getIEjerciciosPracticaEvaluacion ();
	public void setIEjerciciosPracticaEvaluacion (Set<IEjercicioPracticaEvaluacion> iEjerciciosPracticaEvaluacion);
	
}
