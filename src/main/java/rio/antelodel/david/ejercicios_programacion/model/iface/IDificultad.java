package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IDificultad extends IEntity, Serializable {

	public int getId ();
	public void setId (int id);

	public String getNombre ();
	public void setNombre (String nombre);
	
	public float getValor ();
	public void setValor (float valor);
	
	public Set<IEjercicio> getIEjercicios ();
	public void setIEjercicios (Set<IEjercicio> iEjercicios);
	
}
