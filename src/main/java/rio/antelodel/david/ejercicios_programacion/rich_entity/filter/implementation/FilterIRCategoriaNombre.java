package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRCategoriaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRCategoriaNombre;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;

@Component
public class FilterIRCategoriaNombre extends AFilter <IRCategoria, IFilterIRCategoriaGetter> implements IFilterIRCategoriaNombre {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRCategoriaGetter> List<IRCategoria> getFilterTarget (V object) {
		
		return object.getFilterIRCategoria();
		
	}
	
	@Override
	public boolean matches (IRCategoria target, String filter) {
		
		return 	filterIsAny(filter)
				|| target.getNombre().toLowerCase().contains(filter.toLowerCase())
				|| target.getNombre().equalsIgnoreCase(filter);

	}
	
}
