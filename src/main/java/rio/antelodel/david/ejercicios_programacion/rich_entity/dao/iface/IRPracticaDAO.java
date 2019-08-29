package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;

public interface IRPracticaDAO extends IRDAO <IRPractica> {

	public IRPractica find (int key);
	
}
