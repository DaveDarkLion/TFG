package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;

public abstract class AREjercicioSetDAO < T extends IREjercicioSet<U, T>, U extends IEjercicioSet, V extends IRComplexSet<?, ?, ?, V> > extends ARHibernateDAO <T> {

	public abstract T find (IREjercicio rEjercicio, V rSet);
	
}
