package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;

public interface IREjercicioPersona extends IREjercicioSet <IEjercicioPersona, IREjercicioPersona> {

	public IRPersona getIRPersona ();
	public void setIRPersona (IRPersona iRPersona);
	
}
