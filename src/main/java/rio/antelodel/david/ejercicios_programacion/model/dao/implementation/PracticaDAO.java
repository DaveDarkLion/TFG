package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Practica;

@Repository
public class PracticaDAO extends AHibernateDAO <IPractica, Practica> implements IPracticaDAO {
	
	public PracticaDAO () {
	
		super(Practica.class);
		
	}
	
	@Override
	public IPractica find (int key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Practica getEntity (IPractica iface) {
		
		return (Practica)iface;
		
	}
	
}