package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRPracticaEvaluacionGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaEvaluacionAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilterIRSetAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Component
public class FilterIRPracticaEvaluacionAnoRange extends AFilterIRSetAnoRange <IRPracticaEvaluacion, IFilterIRPracticaEvaluacionGetter> implements IFilterIRPracticaEvaluacionAnoRange {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRPracticaEvaluacionGetter> List<IRPracticaEvaluacion> getFilterTarget (V object) {
		
		return object.getFilterIRPracticaEvaluacion();
		
	}
	
	@Override
	public boolean matches (IRPracticaEvaluacion target, String filter) {
	
		return target.getAno() == Integer.parseInt(filter);

	}

	// Parent
	
	@Override
	public int getAno(IRPracticaEvaluacion target) {
		
		return target.getAno();
		
	}
	
	@Override
	protected IRTitulacion getIRTitulacion(IRPracticaEvaluacion target) {

		return target.getIRTitulacion();
		
	}

	@Override
	public <V extends IFilterIRPracticaEvaluacionGetter> List<IRPracticaEvaluacion> getTargetList(V object) {
		
		return getFilterTarget(object);
		
	}
	
}
