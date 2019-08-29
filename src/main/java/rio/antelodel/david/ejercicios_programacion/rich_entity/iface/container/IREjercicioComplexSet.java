package rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;

public interface IREjercicioComplexSet <T extends IEjercicioSet, U extends IREjercicioComplexSet<T, U>> extends IREjercicioSet<T, U> {

	public IRComplexSet<?, ?, ?, ?> getIRComplexSet ();
	
}
