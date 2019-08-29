package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IRProfesor extends IRichEntity <IProfesor>, IRUser {

	public List<IRIdea> getIRIdeas ();
	
}
