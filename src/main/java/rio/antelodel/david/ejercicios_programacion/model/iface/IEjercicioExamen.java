package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;

public interface IEjercicioExamen extends IEjercicioSet, Serializable {

	public IExamen getIExamen ();
	public void setIExamen (IExamen iExamen);

	public int getPosition ();
	public void setPosition (int position);
	
}
