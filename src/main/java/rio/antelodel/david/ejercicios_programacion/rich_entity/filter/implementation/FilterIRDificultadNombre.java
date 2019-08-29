package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRDificultadGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRDificultadNombre;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

@Component
public class FilterIRDificultadNombre extends AFilter <IRDificultad, IFilterIRDificultadGetter> implements IFilterIRDificultadNombre {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRDificultadGetter> List<IRDificultad> getFilterTarget (V object) {
		
		return object.getFilterIRDificultad();
		
	}
	
	@Override
	public boolean matches (IRDificultad target, String filter) {
	
		return	filterIsAny(filter)
				|| target.getNombre().toLowerCase().contains(filter.toLowerCase())
				|| target.getNombre().equalsIgnoreCase(filter);

	}
	
}
