package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;

public interface IREjercicioPracticaDAO extends IRDAO <IREjercicioPractica> {

	public IREjercicioPractica find (IREjercicio iREjercicio, IRPractica iRPractica);
	
}
