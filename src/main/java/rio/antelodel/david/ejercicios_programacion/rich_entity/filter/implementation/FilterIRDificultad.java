package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRDificultadGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

@Component
public class FilterIRDificultad extends AFilter <IRDificultad, IFilterIRDificultadGetter> implements IFilterIRDificultad {

	// Constants
	
	public static final int ANY = -1;
	
	// Functions
	
	@Override
	protected <V extends IFilterIRDificultadGetter> List<IRDificultad> getFilterTarget (V object) {
		
		return object.getFilterIRDificultad();
		
	}
	
	@Override
	public boolean matches (IRDificultad target, String filter) {
	
		return (filter == null || Integer.parseInt(filter) == ANY || target.getId() == Integer.parseInt(filter));

	}
	
	@Override
	public boolean filterIsAny (String filter) {
	
		return super.filterIsAny(filter) || (IFilterHelper.isInt(filter) && Integer.parseInt(filter) == ANY);
		
	}
	
}
