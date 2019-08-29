package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRPracticaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;

public interface IRPractica extends IRComplexSet <IPractica, IEjercicioPractica, IREjercicioPractica, IRPractica>, IFilterIRPracticaGetter {

}
