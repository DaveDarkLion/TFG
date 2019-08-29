package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRPracticaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilterIRSetAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Component
public class FilterIRPracticaAnoRange extends AFilterIRSetAnoRange <IRPractica, IFilterIRPracticaGetter> implements IFilterIRPracticaAnoRange {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRPracticaGetter> List<IRPractica> getFilterTarget (V object) {
		
		return object.getFilterIRPractica();
		
	}
	
	@Override
	public boolean matches (IRPractica target, String filter) {
	
		return target.getAno() == Integer.parseInt(filter);

	}

	// Parent
	
	@Override
	public int getAno(IRPractica target) {

		return target.getAno();
		
	}
	
	@Override
	protected IRTitulacion getIRTitulacion(IRPractica target) {

		return target.getIRTitulacion();
		
	}

	@Override
	public <V extends IFilterIRPracticaGetter> List<IRPractica> getTargetList(V object) {

		return getFilterTarget(object);

	}
	
}
