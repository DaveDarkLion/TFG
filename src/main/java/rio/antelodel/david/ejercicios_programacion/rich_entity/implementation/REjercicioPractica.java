package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.AREjercicioComplexSet;

public class REjercicioPractica extends AREjercicioComplexSet < IEjercicioPractica, IREjercicioPractica > implements IREjercicioPractica {

	// Constructors
	
	public REjercicioPractica () { }
	
	public REjercicioPractica (IEjercicioPractica entity) {
		
		super(entity);
		
	}
	
	public REjercicioPractica (IREjercicio rEjercicio, IRPractica rPractica, int position) {
	
		super(MFactory.newIEjercicioPractica(rEjercicio.getEntity(), rPractica.getEntity(), position));
		
	}
	
	// Entity Functions
	
	@Override
	public IRPractica getIRPractica () { return IRFactory.newIRPractica(getEntity().getIPractica()); }
	public void setIRPractica (IRPractica iRPractica) { getEntity().setIPractica(iRPractica.getEntity()); }
	
	// Get IRComplexSet
	
	@Override
	public IRComplexSet<?, ?, ?, ?> getIRComplexSet () { return getIRPractica(); }

}
