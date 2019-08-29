package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;

public interface IRIdeaDAO extends IRDAO <IRIdea> {

	public IRIdea find (int key);
	
}
