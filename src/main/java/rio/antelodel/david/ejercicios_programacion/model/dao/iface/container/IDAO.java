package rio.antelodel.david.ejercicios_programacion.model.dao.iface.container;

import java.util.List;

public interface IDAO < T > {

	public void save (T object);
	public void update (T object);
	public void delete (T object);
	public void refresh (T object);
	public List<T> getAll ();
	
}
