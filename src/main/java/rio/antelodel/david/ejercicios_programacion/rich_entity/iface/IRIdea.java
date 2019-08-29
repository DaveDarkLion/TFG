package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.model.iface.IIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRIdeaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRProfesorGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IRIdea extends IRichEntity <IIdea>, IParseable, Comparable<IRIdea>, IFilterIRIdeaGetter, IFilterIRProfesorGetter {

	public int getId ();
	
	public String getNombre ();
	public void setNombre (String nombre);
	
	public String getIdeaText ();
	public void setIdeaText (String ideaText);
	
	public IRProfesor getIRProfesor ();
	
}
