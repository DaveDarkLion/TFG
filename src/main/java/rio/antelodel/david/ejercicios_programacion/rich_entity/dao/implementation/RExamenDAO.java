package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARSetDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;

@Repository
public class RExamenDAO extends ARSetDAO <IRExamen> implements IRExamenDAO {

	@Autowired
	private IExamenDAO iExamenDAO;
	
	@Override
	public IRExamen find (int key) {
	
		return IRFactory.newIRExamen(iExamenDAO.find(key));
		
	}
	
	@Override
	public List<IRExamen> getAll() {
		
		return IRFactory.getIRExamenList(iExamenDAO.getAll());
		
	}

}
