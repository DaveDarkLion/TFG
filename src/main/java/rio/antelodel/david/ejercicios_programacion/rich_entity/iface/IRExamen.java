package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRExamenGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;

public interface IRExamen extends IRComplexSet <IExamen, IEjercicioExamen, IREjercicioExamen, IRExamen>, IFilterIRExamenGetter {

}
