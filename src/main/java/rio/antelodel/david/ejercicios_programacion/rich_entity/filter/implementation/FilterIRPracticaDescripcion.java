package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRPracticaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;

@Component
public class FilterIRPracticaDescripcion extends AFilter <IRPractica, IFilterIRPracticaGetter> implements IFilterIRPracticaDescripcion {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRPracticaGetter> List<IRPractica> getFilterTarget (V object) {
		
		return object.getFilterIRPractica();
		
	}
	
	@Override
	public boolean matches (IRPractica target, String filter) {
	
		return 	filterIsAny(filter)
				|| target.getDescripcion().toLowerCase().contains(filter.toLowerCase())
				|| target.getDescripcion().equalsIgnoreCase(filter);

	}
	
}
