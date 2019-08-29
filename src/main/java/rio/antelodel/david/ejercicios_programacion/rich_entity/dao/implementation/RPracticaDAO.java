package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARSetDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;

@Repository
public class RPracticaDAO extends ARSetDAO <IRPractica> implements IRPracticaDAO {

	@Autowired
	private IPracticaDAO iPracticaDAO;
	
	@Override
	public IRPractica find (int key) {
	
		return IRFactory.newIRPractica(iPracticaDAO.find(key));
		
	}
	
	@Override
	public List<IRPractica> getAll() {
		
		return IRFactory.getIRPracticaList(iPracticaDAO.getAll());
		
	}

}
