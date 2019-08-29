package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IIdea;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Idea;

@Repository
public class IdeaDAO extends AHibernateDAO <IIdea, Idea> implements IIdeaDAO {
	
	public IdeaDAO () {
	
		super(Idea.class);
		
	}
	
	@Override
	public IIdea find (int key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Idea getEntity (IIdea iface) {
		
		return (Idea)iface;
		
	}
	
}