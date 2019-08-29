package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRTitulacionGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRTitulacionNombre;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Component
public class FilterIRTitulacionNombre extends AFilter <IRTitulacion, IFilterIRTitulacionGetter> implements IFilterIRTitulacionNombre {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRTitulacionGetter> List<IRTitulacion> getFilterTarget (V object) {
		
		return object.getFilterIRTitulacion();
		
	}
	
	@Override
	public boolean matches (IRTitulacion target, String filter) {
	
		return 	filterIsAny(filter)
				|| target.getNombre().toLowerCase().contains(filter)
				|| target.getNombre().equalsIgnoreCase(filter);

	}
	
}
