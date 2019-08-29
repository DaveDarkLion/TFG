package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ICategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;

@Repository
public class RCategoriaDAO extends ARHibernateDAO <IRCategoria> implements IRCategoriaDAO {

	@Autowired
	private ICategoriaDAO iCategoriaDAO;
	
	@Override
	public IRCategoria find (int key) {
	
		return IRFactory.newIRCategoria(iCategoriaDAO.find(key));
		
	}
	
	@Override
	public List<IRCategoria> getAll() {
		
		return IRFactory.getIRCategoriaList(iCategoriaDAO.getAll());
		
	}

}
