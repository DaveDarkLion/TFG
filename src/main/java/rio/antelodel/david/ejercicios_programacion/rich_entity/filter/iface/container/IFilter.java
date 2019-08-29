package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.container;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;

public interface IFilter <T, U> {
	
	public boolean matches (T target, String filter);
	
	public < V extends U > boolean matchesObject (V object, String filter);
	
	public < V extends U > boolean matchesObject (V object, List<String> filters, Operation operation);
	
	public < V extends U > List<V> apply (List<V> objects, String filter);
	
	public < V extends U > List<V> apply (List<V> objects, List<String> filters, Operation operation);
	
	public < V extends U > List <V> cleanAndApply (List<V> objects, String filter, Operation operation);
	
}
