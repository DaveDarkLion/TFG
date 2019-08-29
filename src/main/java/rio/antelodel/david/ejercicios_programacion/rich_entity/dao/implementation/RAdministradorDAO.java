package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAdministrador;

@Repository
public class RAdministradorDAO extends ARHibernateDAO <IRAdministrador> implements IRAdministradorDAO {

	@Autowired
	private IAdministradorDAO iAdministradorDAO;
	
	@Override
	public IRAdministrador find (String key) {
	
		return IRFactory.newIRAdministrador(iAdministradorDAO.find(key));
		
	}
	
	@Override
	public List<IRAdministrador> getAll() {
		
		return IRFactory.getIRAdministradorList(iAdministradorDAO.getAll());
		
	}

}
