package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaEvaluacionPK;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.AREjercicioSetDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;

@Repository
public class REjercicioPracticaEvaluacionDAO extends AREjercicioSetDAO <IREjercicioPracticaEvaluacion, IEjercicioPracticaEvaluacion, IRPracticaEvaluacion> implements IREjercicioPracticaEvaluacionDAO {

	@Autowired
	private IEjercicioPracticaEvaluacionDAO iEjercicioPracticaEvaluacionDAO;
	
	@Override
	public IREjercicioPracticaEvaluacion find (IREjercicio iREjercicio, IRPracticaEvaluacion iRPracticaEvaluacion) {

		return IRFactory.newIREjercicioPracticaEvaluacion(iEjercicioPracticaEvaluacionDAO.find(new IEjercicioPracticaEvaluacionPK(iREjercicio.getEntity(), iRPracticaEvaluacion.getEntity())));
		
	}
	
	@Override
	public List<IREjercicioPracticaEvaluacion> getAll() {
		
		return IRFactory.getIREjercicioPracticaEvaluacionList(iEjercicioPracticaEvaluacionDAO.getAll());
		
	}

}
