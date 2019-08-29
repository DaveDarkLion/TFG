package rio.antelodel.david.ejercicios_programacion.model.dao.iface;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioExamenPK;

public interface IEjercicioExamenDAO extends IDAO <IEjercicioExamen> {

	public IEjercicioExamen find (IEjercicioExamenPK key);
	
}
