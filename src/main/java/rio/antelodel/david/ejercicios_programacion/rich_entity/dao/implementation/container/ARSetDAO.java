package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;

public abstract class ARSetDAO < T extends IRComplexSet <?, ?, ?, T> > extends ARHibernateDAO <T> {

	public abstract T find (int key);
	
}
