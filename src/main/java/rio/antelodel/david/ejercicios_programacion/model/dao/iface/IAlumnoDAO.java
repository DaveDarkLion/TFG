package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;

public interface IAlumnoDAO extends IDAO <IAlumno> {

	public IAlumno find (String key);
	
}
