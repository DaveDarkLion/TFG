package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRDificultadGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IRDificultad extends IRichEntity <IDificultad>, IParseable, Comparable<IRDificultad>, IFilterIRDificultadGetter {

	public int getId ();
	
	public String getNombre ();
	public void setNombre (String nombre);
	
	public float getValor ();
	public void setValor (float valor);
	
	public boolean hasIREjercicios ();
	
}
