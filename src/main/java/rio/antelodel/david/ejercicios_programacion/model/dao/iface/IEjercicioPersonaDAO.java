package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPersonaPK;

public interface IEjercicioPersonaDAO extends IDAO <IEjercicioPersona> {

	public IEjercicioPersona find (IEjercicioPersonaPK key);
	
}
