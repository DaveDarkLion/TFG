package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;

@Repository
public class RIdeaDAO extends ARHibernateDAO <IRIdea> implements IRIdeaDAO {

	@Autowired
	private IIdeaDAO iIdeaDAO;
	
	@Override
	public IRIdea find (int key) {
		
		return IRFactory.newIRIdea(iIdeaDAO.find(key));
		
	}
	
	@Override
	public List<IRIdea> getAll() {
		
		return IRFactory.getIRIdeaList(iIdeaDAO.getAll());
		
	}

}
