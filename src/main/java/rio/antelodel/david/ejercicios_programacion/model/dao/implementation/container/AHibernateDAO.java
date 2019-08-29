package rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.container.IDAO;

@Transactional
public abstract class AHibernateDAO < T, U extends T > implements IDAO <T> {

	private Class<U> clazz;
	
	public Class<U> getCurrentClass () { return clazz; }
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AHibernateDAO (Class<U> clazz) {
	
		this.clazz = clazz;
		
	}
	
	protected Session getCurrentSession () { return sessionFactory.getCurrentSession(); }
	
	@Override
	public void save (T object) {
	
		getCurrentSession().save(getEntity(object));
		
	}
	
	@Override
	public void update (T object) {
	
		getCurrentSession().update(getEntity(object));
		
	}
	
	@Override
	public void delete (T object) {
		
		getCurrentSession().delete(getEntity(object));
		
	}
	
	@Override
	public void refresh (T object) {
		
		getCurrentSession().refresh(object);
		
	}
	
	@Override
	public List<T> getAll () {
		
		CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<U> query =  builder.createQuery(getCurrentClass());
		Root<U> root = query.from(clazz);
	    query.select(root);
		
		return new ArrayList<>(getCurrentSession().createQuery(query).getResultList());
		
	}
	
	public T find () {
		
		CustomLogger.LOGGER.severe("Empty method, use the overloaded version");
		return null;
		
	}
	
	// Other
	
	public abstract U getEntity (T iface);
	
}
