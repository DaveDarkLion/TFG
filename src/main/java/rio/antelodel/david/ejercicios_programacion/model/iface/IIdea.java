package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IIdea extends IEntity, Serializable {

	public int getId ();
	public void setId (int id);
	
	public String getNombre();
	public void setNombre(String nombre);

	public String getIdeaText ();
	public void setIdeaText (String ideaText);
	
	public IProfesor getIProfesor ();
	public void setIProfesor (IProfesor iProfesor);
	
}
