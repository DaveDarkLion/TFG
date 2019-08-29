package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;

public interface IEjercicioPractica extends IEjercicioSet, Serializable {

	public IPractica getIPractica ();
	public void setIPractica (IPractica iPractica);

	public int getPosition ();
	public void setPosition (int position);
	
}
