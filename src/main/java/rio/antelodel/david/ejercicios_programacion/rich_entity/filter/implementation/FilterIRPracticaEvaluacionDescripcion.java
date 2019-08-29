package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation;

import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRPracticaEvaluacionGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.IFilterIRPracticaEvaluacionDescripcion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container.AFilter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;

@Component
public class FilterIRPracticaEvaluacionDescripcion extends AFilter <IRPracticaEvaluacion, IFilterIRPracticaEvaluacionGetter> implements IFilterIRPracticaEvaluacionDescripcion {
	
	// Functions
	
	@Override
	protected <V extends IFilterIRPracticaEvaluacionGetter> List<IRPracticaEvaluacion> getFilterTarget (V object) {
		
		return object.getFilterIRPracticaEvaluacion();
		
	}
	
	@Override
	public boolean matches (IRPracticaEvaluacion target, String filter) {
	
		return 	filterIsAny(filter)
				|| target.getDescripcion().toLowerCase().contains(filter.toLowerCase())
				|| target.getDescripcion().equalsIgnoreCase(filter);

	}
	
}
