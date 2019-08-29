package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.container.IRDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

@Transactional
public abstract class ARHibernateDAO < T extends IRichEntity <?> > implements IRDAO <T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession () { return sessionFactory.getCurrentSession(); }
	
	public void save (T object) {
		
		getCurrentSession().save(object.getEntity());
		
	}
	
	public void update (T object) {
		
		getCurrentSession().update(object.getEntity());
		
	}
	
	public void saveOrUpdate (T object) {
		
		getCurrentSession().saveOrUpdate(object.getEntity());
		
	}
	
	public void delete (T object) {
		
		getCurrentSession().delete(object.getEntity());
		
	}
	
	public void refresh (T object) {
		
		getCurrentSession().refresh(object.getEntity());
		
	}
	
	public T getDefault () {
		
		return getAll().get(0);
		
	}
	
}
