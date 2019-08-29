package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRTitulacionGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Component
public class FilterIRTitulacion extends AFilter <IRTitulacion, IFilterIRTitulacionGetter> implements IFilterIRTitulacion {

	// Constants

	public static final int ANY = -1;
	
	// Functions
	
	@Override
	protected <V extends IFilterIRTitulacionGetter> List<IRTitulacion> getFilterTarget (V object) {
		
		return object.getFilterIRTitulacion();
		
	}
	
	@Override
	public boolean matches (IRTitulacion target, String filter) {
	
		return (Integer.parseInt(filter) == ANY || target.getId() == Integer.parseInt(filter));

	}
	
	@Override
	public boolean filterIsAny (String filter) {
	
		return super.filterIsAny(filter) || (IFilterHelper.isInt(filter) && Integer.parseInt(filter) == ANY);
		
	}
	
}
