package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Profesor;

@Repository
public class ProfesorDAO extends AHibernateDAO <IProfesor, Profesor> implements IProfesorDAO {
	
	public ProfesorDAO () {
	
		super(Profesor.class);
		
	}
	
	@Override
	public IProfesor find (String key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Profesor getEntity (IProfesor iface) {
		
		return (Profesor)iface;
		
	}
	
}