package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRSet;

public abstract class AREjercicioComplexSet < T extends IEjercicioSet, U extends IREjercicioComplexSet<T, U> > extends AREjercicioSet<T, U> implements IREjercicioComplexSet<T, U> {

	// Constructors
	
	// Constructors
	
	public AREjercicioComplexSet () { }
		
	public AREjercicioComplexSet (T entity) {
			
		super(entity);
			
	}
	
	public IRSet<?, ?, ?, ?> getIRSet () { return getIRComplexSet(); }
	
}
