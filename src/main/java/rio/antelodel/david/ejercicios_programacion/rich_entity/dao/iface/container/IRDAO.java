package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IRDAO < T extends IRichEntity<?> > {

	public void save (T object);
	public void update (T object);
	public void delete (T object);
	public void refresh (T object);
	public List<T> getAll ();
	public T getDefault ();
	
}
