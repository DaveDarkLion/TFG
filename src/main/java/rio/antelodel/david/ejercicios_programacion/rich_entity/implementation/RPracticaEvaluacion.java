package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARComplexSet;

public class RPracticaEvaluacion extends ARComplexSet < IPracticaEvaluacion, IEjercicioPracticaEvaluacion, IREjercicioPracticaEvaluacion, IRPracticaEvaluacion > implements IRPracticaEvaluacion {
	
	// Constructors
	
	public RPracticaEvaluacion (IPracticaEvaluacion entity) {
		
		super(entity);
		
	}
	
	public RPracticaEvaluacion (int mes, int ano, String descripcion, IRTitulacion iRTitulacion, boolean visible, boolean abierto) {
		
		super(MFactory.newIPracticaEvaluacion(mes, ano, descripcion, iRTitulacion.getEntity(), visible, abierto));
		
	}
	
	// Get Ejercicios Set
	
	@Override
	public List<IREjercicioPracticaEvaluacion> getIREjerciciosSet() {

		return IRFactory.getIREjercicioPracticaEvaluacionList(new ArrayList<>(getEntity().getIEjerciciosSet()));
		
	}
	
	// Filters
	
	// RPracticaEvaluacion
	
	@Override
	public List<IRPracticaEvaluacion> getFilterIRPracticaEvaluacion () {

		return Arrays.asList(this);
		
	}

}
