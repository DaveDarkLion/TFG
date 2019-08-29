package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRIdeaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRIdeaNombreIdeaText;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;

@Component
public class FilterIRIdeaNombreIdeaText extends AFilter <IRIdea, IFilterIRIdeaGetter> implements IFilterIRIdeaNombreIdeaText {

	// Constants
	
	public static final int ANY = -1;
	
	// Functions
	
	@Override
	protected <V extends IFilterIRIdeaGetter> List<IRIdea> getFilterTarget (V object) {
		
		return object.getFilterIRIdea();
		
	}
	
	@Override
	public boolean matches (IRIdea target, String filter) {
	
		return (filter == null
				|| target.getNombre().toLowerCase().contains(filter.toLowerCase())
				|| target.getIdeaText().toLowerCase().contains(filter.toLowerCase()))
				|| target.getNombre().equalsIgnoreCase(filter)
				|| target.getIdeaText().contentEquals(filter);

	}
	
	@Override
	public boolean filterIsAny (String filter) {
	
		return (IFilterHelper.isInt(filter) && Integer.parseInt(filter) == ANY);
		
	}
	
}
