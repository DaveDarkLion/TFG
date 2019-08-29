package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@Repository
public class RProfesorDAO extends ARHibernateDAO <IRProfesor> implements IRProfesorDAO {

	@Autowired
	private IProfesorDAO iProfesorDAO;
	
	@Override
	public IRProfesor find (String key) {
	
		return IRFactory.newIRProfesor(iProfesorDAO.find(key));
		
	}
	
	@Override
	public List<IRProfesor> getAll() {
		
		return IRFactory.getIRProfesorList(iProfesorDAO.getAll());
		
	}

}
