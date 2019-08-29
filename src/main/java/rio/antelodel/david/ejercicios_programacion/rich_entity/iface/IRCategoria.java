package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRCategoriaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IRCategoria extends IRichEntity <ICategoria>, IParseable, Comparable<IRCategoria>, IFilterIRCategoriaGetter {

	public int getId ();
	
	public String getNombre ();
	public void setNombre (String ruta);
	
	public boolean hasIREjercicios ();
	
}
