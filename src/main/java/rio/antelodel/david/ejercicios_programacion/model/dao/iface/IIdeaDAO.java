package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IIdea;

public interface IIdeaDAO extends IDAO <IIdea> {

	public IIdea find (int key);
	
}
