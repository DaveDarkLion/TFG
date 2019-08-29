package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRExamenGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRExamenAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilterIRSetAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Component
public class FilterIRExamenAnoRange extends AFilterIRSetAnoRange <IRExamen, IFilterIRExamenGetter> implements IFilterIRExamenAnoRange {
	
	// Functions
	
	@Override
	protected < V extends IFilterIRExamenGetter > List<IRExamen> getFilterTarget (V object) {
		
		return object.getFilterIRExamen();
		
	}
	
	@Override
	public boolean matches (IRExamen target, String filter) {
	
		return filter == null || target.getAno() == Integer.parseInt(filter);

	}

	// Parent
	
	@Override
	public int getAno (IRExamen target) {
		
		return target.getAno();
		
	}
	
	@Override
	protected IRTitulacion getIRTitulacion(IRExamen target) {

		return target.getIRTitulacion();
		
	}

	@Override
	public <V extends IFilterIRExamenGetter> List<IRExamen> getTargetList (V object) {
		
		return getFilterTarget(object);
		
	}
	
}
