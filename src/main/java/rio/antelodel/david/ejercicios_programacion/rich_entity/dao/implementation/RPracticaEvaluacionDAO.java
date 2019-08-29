package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARSetDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;

@Repository
public class RPracticaEvaluacionDAO extends ARSetDAO <IRPracticaEvaluacion> implements IRPracticaEvaluacionDAO {

	@Autowired
	private IPracticaEvaluacionDAO iPracticaEvaluacionDAO;
	
	@Override
	public IRPracticaEvaluacion find (int key) {
	
		return IRFactory.newIRPracticaEvaluacion(iPracticaEvaluacionDAO.find(key));
		
	}
	
	@Override
	public List<IRPracticaEvaluacion> getAll() {
		
		return IRFactory.getIRPracticaEvaluacionList(iPracticaEvaluacionDAO.getAll());
		
	}

}
