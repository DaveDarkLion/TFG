package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaPK;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.AREjercicioSetDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;

@Repository
public class REjercicioPracticaDAO extends AREjercicioSetDAO <IREjercicioPractica, IEjercicioPractica, IRPractica> implements IREjercicioPracticaDAO {

	@Autowired
	private IEjercicioPracticaDAO iEjercicioPracticaDAO;
	
	@Override
	public IREjercicioPractica find (IREjercicio iREjercicio, IRPractica iRPractica) {

		return IRFactory.newIREjercicioPractica(iEjercicioPracticaDAO.find(new IEjercicioPracticaPK(iREjercicio.getEntity(), iRPractica.getEntity())));
		
	}
	
	@Override
	public List<IREjercicioPractica> getAll() {
		
		return IRFactory.getIREjercicioPracticaList(iEjercicioPracticaDAO.getAll());
		
	}

}
