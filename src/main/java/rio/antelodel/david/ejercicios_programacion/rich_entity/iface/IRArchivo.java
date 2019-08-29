package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.model.iface.IArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IRArchivo extends IRichEntity <IArchivo>, IParseable, Comparable<IRArchivo> {

	public int getId ();
	
	public String getRuta ();
	public void setRuta (String ruta);
	
	public String getNombre ();
	
	public boolean isVisible ();
	
}
