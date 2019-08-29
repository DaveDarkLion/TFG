package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public interface IREjercicioDAO extends IRDAO <IREjercicio> {

	public IREjercicio find (int key);
	
}
