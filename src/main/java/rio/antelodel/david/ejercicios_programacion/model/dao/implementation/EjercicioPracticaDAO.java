package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.implementation.EjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaPK;

@Repository
public class EjercicioPracticaDAO extends AHibernateDAO <IEjercicioPractica, EjercicioPractica> implements IEjercicioPracticaDAO {

	public EjercicioPracticaDAO () {
	
		super(EjercicioPractica.class);
		
	}
	
	@Override
	public IEjercicioPractica find (IEjercicioPracticaPK key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public EjercicioPractica getEntity (IEjercicioPractica iface) {
		
		return (EjercicioPractica)iface;
		
	}
	
}