package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.AREjercicioComplexSet;

public class REjercicioExamen extends AREjercicioComplexSet < IEjercicioExamen, IREjercicioExamen > implements IREjercicioExamen {

	// Constructors
	
	public REjercicioExamen () { }
	
	public REjercicioExamen (IEjercicioExamen entity) {
		
		super(entity);
		
	}
	
	public REjercicioExamen (IREjercicio rEjercicio, IRExamen rExamen, int position) {
	
		super(MFactory.newIEjercicioExamen(rEjercicio.getEntity(), rExamen.getEntity(), position));
		
	}
	
	// Entity Functions
	
	@Override
	public IRExamen getIRExamen () { return IRFactory.newIRExamen(getEntity().getIExamen()); }
	@Override
	public void setIRExamen (IRExamen iRExamen) { getEntity().setIExamen(iRExamen.getEntity()); }
	
	// Get IRComplexSet
	
	@Override
	public IRComplexSet<?, ?, ?, ?> getIRComplexSet () { return getIRExamen(); }
	
}
