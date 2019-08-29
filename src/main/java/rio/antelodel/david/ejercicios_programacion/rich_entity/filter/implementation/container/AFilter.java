package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container;

import java.util.ArrayList;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.container.IFilter;

public abstract class AFilter <T, U> implements IFilter <T, U> {
	
	// Functions
	
	protected abstract < V extends U > List < T > getFilterTarget (V object);
	
	@Override
	public < V extends U > boolean matchesObject (V object, String filter) {
		
		if (filterIsAny(filter)) return true;
		
		else {
		
			List<T> targets = getFilterTarget(object);
			
			for (T t : targets) if (matches(t, filter)) return true;
			return false;
			
		}
		
	}
	
	@Override
	public < V extends U > boolean matchesObject (V object, List<String> filters, Operation operation) {
		
		if (operation == Operation.OR) {
		
			for (String f : filters) if (matchesObject(object, f)) return true;
			return false;
			
		}
		
		else {
			
			for (String f : filters) if (!matchesObject(object, f)) return false;
			return true;
			
		}
		
	}
	
	@Override
	public < V extends U > List<V> apply (List<V> objects, String filter) {
		
		ArrayList<V> result = new ArrayList<>();
	
		for (V o : objects) if (matchesObject(o, filter)) result.add(o);
		return result;
		
	}
	
	@Override
	public < V extends U > List<V> apply (List<V> objects, List<String> filters, Operation operation) {
		
		if (!filtersAreAny(filters)) {
			
			ArrayList<V> result = new ArrayList<>();
			
			for (V o : objects) if (matchesObject(o, filters, operation)) result.add(o);
			
			return result;
			
		}
		
		else return objects;
		
	}
	
	@Override
	public < V extends U > List <V> cleanAndApply (List<V> objects, String filter, Operation operation) {
		
		if (!filterIsAny(filter)) {
			
			List<String> filtersClean = IFilterHelper.separateFilters(filter, IFilterHelper.DEFAULT_SEPARATOR);
			return apply(objects, filtersClean, operation);
			
		}
		
		else return objects;
		
	}
	
	public boolean filterIsAny (String filter) {
		
		return filter == null || filter.isEmpty();
		
	}
	
	public boolean filtersAreAny (List<String> filters) {
		
		return filters == null || filters.isEmpty();
		
	}

}