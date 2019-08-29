package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRPracticaEvaluacionGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;

public interface IRPracticaEvaluacion extends IRComplexSet <IPracticaEvaluacion, IEjercicioPracticaEvaluacion, IREjercicioPracticaEvaluacion, IRPracticaEvaluacion>, IFilterIRPracticaEvaluacionGetter {

}
