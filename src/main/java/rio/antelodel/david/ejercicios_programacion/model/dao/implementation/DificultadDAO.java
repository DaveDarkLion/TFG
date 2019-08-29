package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Dificultad;

@Repository
public class DificultadDAO extends AHibernateDAO <IDificultad, Dificultad> implements IDificultadDAO {
	
	public DificultadDAO () {
	
		super(Dificultad.class);
		
	}
	
	@Override
	public IDificultad find (int key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Dificultad getEntity (IDificultad iface) {
		
		return (Dificultad)iface;
		
	}
	
}