package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRUserGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRUser;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;

@Component
public class FilterIRUser extends AFilter <IRUser, IFilterIRUserGetter> implements IFilterIRUser {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRUserGetter> List<IRUser> getFilterTarget (V object) {
		
		return object.getFilterIRUser();
		
	}
	
	@Override
	public boolean matches (IRUser target, String filter) {
	
		return 	filter == null
				|| getFilterName(target).toLowerCase().contains(filter.toLowerCase())
				|| getFilterName(target).equalsIgnoreCase(filter)
				|| target.getEmail().equalsIgnoreCase(filter);

	}
	
	public String getFilterName (IRUser iRUser) {
		
		return iRUser.getNombre() + " " + iRUser.getApellido1() + " " + iRUser.getApellido2();
		
	}
	
}
