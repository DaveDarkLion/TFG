package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;

public interface IProfesorDAO extends IDAO <IProfesor> {

	public IProfesor find (String key);
	
}
