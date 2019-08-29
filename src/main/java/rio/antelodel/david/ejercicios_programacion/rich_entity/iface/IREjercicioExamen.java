package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioComplexSet;

public interface IREjercicioExamen extends IREjercicioComplexSet <IEjercicioExamen, IREjercicioExamen> {

	public IRExamen getIRExamen ();
	public void setIRExamen (IRExamen iRExamen);
	
}
