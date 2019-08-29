package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.AREjercicioComplexSet;

public class REjercicioPracticaEvaluacion extends AREjercicioComplexSet < IEjercicioPracticaEvaluacion, IREjercicioPracticaEvaluacion > implements IREjercicioPracticaEvaluacion {

	// Constructors
	
	public REjercicioPracticaEvaluacion () { }
	
	public REjercicioPracticaEvaluacion (IEjercicioPracticaEvaluacion entity) {
		
		super(entity);
		
	}
	
	public REjercicioPracticaEvaluacion (IREjercicio rEjercicio, IRPracticaEvaluacion rPracticaEvaluacion, int position) {
	
		super(MFactory.newIEjercicioPracticaEvaluacion(rEjercicio.getEntity(), rPracticaEvaluacion.getEntity(), position));
		
	}
	
	// Entity Functions
	
	@Override
	public IRPracticaEvaluacion getIRPracticaEvaluacion () { return IRFactory.newIRPracticaEvaluacion(getEntity().getIPracticaEvaluacion()); }
	@Override
	public void setIRPracticaEvaluacion (IRPracticaEvaluacion iRPracticaEvaluacion) { getEntity().setIPracticaEvaluacion(iRPracticaEvaluacion.getEntity()); }
	
	// Get IRComplexSet
	
		@Override
		public IRComplexSet<?, ?, ?, ?> getIRComplexSet () { return getIRPracticaEvaluacion(); }

}
