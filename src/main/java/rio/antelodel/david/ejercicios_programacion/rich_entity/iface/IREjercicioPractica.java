package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioComplexSet;

public interface IREjercicioPractica extends IREjercicioComplexSet <IEjercicioPractica, IREjercicioPractica> {

	public IRPractica getIRPractica ();
	public void setIRPractica (IRPractica iRPractica);
	
}
