package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IProfesor extends IEntity, Serializable {

	public IPersona getIPersona ();
	public void setIPersona (IPersona iPersona);
	
	public Set<IEjercicio> getIEjercicios ();
	public void setIEjercicios (Set<IEjercicio> iEjercicios);
	
	public Set<IIdea> getIIdeas ();
	public void setIIdeas (Set<IIdea> iIdeas);
	
}
