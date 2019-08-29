package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

public interface IREjercicioPersonaDAO extends IRDAO <IREjercicioPersona> {

	public IREjercicioPersona find (IREjercicio iREjercicio, IRPersona iRPersona);
	
}
