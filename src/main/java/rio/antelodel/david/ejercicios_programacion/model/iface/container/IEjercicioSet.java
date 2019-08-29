package rio.antelodel.david.ejercicios_programacion.model.iface.container;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;

public interface IEjercicioSet extends IEntity {

	public IEjercicio getIEjercicio ();
	public void setIEjercicio (IEjercicio iEjercicio);
	
	public int getPosition ();
	public void setPosition (int position);
	
}
