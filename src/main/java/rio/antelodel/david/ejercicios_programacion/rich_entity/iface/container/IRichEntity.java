package rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IRichEntity < T extends IEntity > {
	
	public T getEntity ();
	
	public boolean isNull();
	
}
