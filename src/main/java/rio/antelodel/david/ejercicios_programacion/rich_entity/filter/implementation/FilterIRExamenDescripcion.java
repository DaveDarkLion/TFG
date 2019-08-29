package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRExamenGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRExamenDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;

@Component
public class FilterIRExamenDescripcion extends AFilter <IRExamen, IFilterIRExamenGetter> implements IFilterIRExamenDescripcion {
	
	// Constants
	
	public static final String DEFAULT_RANGE_SEPARATOR = ";";
	
	// Functions
	
	@Override
	protected <V extends IFilterIRExamenGetter> List<IRExamen> getFilterTarget (V object) {
		
		return object.getFilterIRExamen();
		
	}
	
	@Override
	public boolean matches (IRExamen target, String filter) {
	
		return 	filterIsAny(filter)
				|| target.getDescripcion().toLowerCase().contains(filter.toLowerCase())
				|| target.getDescripcion().equalsIgnoreCase(filter);

	}
	
}
