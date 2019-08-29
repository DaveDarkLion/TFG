package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@Repository
public class RPersonaDAO extends ARHibernateDAO <IRPersona> implements IRPersonaDAO {

	@Autowired
	private IPersonaDAO iPersonaDAO;
	
	@Override
	public IRPersona find (String key) {
	
		return IRFactory.newIRPersona(iPersonaDAO.find(key));
		
	}
	
	@Override
	public List<IRPersona> getAll() {
		
		return IRFactory.getIRPersonaList(iPersonaDAO.getAll());
		
	}

}
