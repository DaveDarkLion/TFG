package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

@Repository
public class RDificultadDAO extends ARHibernateDAO <IRDificultad> implements IRDificultadDAO {

	@Autowired
	private IDificultadDAO iDificultadDAO;
	
	@Override
	public IRDificultad find (int key) {
	
		return IRFactory.newIRDificultad(iDificultadDAO.find(key));
		
	}
	
	@Override
	public List<IRDificultad> getAll() {
		
		return IRFactory.getIRDificultadList(iDificultadDAO.getAll());
		
	}

}
