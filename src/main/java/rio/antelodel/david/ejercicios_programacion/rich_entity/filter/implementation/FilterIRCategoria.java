package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRCategoriaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;

@Component
public class FilterIRCategoria extends AFilter <IRCategoria, IFilterIRCategoriaGetter> implements IFilterIRCategoria {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRCategoriaGetter> List<IRCategoria> getFilterTarget (V object) {
		
		return object.getFilterIRCategoria();
		
	}
	
	@Override
	public boolean matches (IRCategoria target, String filter) {
	
		return target.getId() == Integer.parseInt(filter);

	}
	
}
