package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRProfesorGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@Component
public class FilterIRProfesor extends AFilter <IRProfesor, IFilterIRProfesorGetter> implements IFilterIRProfesor {

	// Constants
	
	public static final String ANY = "any";
	
	// Functions
	
	@Override
	protected <V extends IFilterIRProfesorGetter> List<IRProfesor> getFilterTarget (V object) {
		
		return object.getFilterIRProfesor();
		
	}
	
	@Override
	public boolean matches (IRProfesor target, String filter) {
	
		return (filter.equals(ANY) || target.getEmail().equalsIgnoreCase(filter.toLowerCase()));

	}
	
	@Override
	public boolean filterIsAny (String filter) {
	
		return super.filterIsAny(filter) || filter.equals(ANY);
		
	}
	
}
