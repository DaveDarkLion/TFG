package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;

@Repository
public class RAlumnoDAO extends ARHibernateDAO <IRAlumno> implements IRAlumnoDAO {

	@Autowired
	private IAlumnoDAO iAlumnoDAO;
	
	@Override
	public IRAlumno find (String key) {
	
		return IRFactory.newIRAlumno(iAlumnoDAO.find(key));
		
	}
	
	@Override
	public List<IRAlumno> getAll() {
		
		return IRFactory.getIRAlumnoList(iAlumnoDAO.getAll());
		
	}

}
