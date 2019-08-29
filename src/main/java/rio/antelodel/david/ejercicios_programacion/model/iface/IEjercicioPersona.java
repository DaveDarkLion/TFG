package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;

public interface IEjercicioPersona extends IEjercicioSet, Serializable {

	public IPersona getIPersona ();
	public void setIPersona (IPersona iPersona);

	public int getPosition ();
	public void setPosition (int position);
	
}
