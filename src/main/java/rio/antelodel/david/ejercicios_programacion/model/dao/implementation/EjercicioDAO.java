package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Ejercicio;

@Repository
public class EjercicioDAO extends AHibernateDAO <IEjercicio, Ejercicio> implements IEjercicioDAO {

	public EjercicioDAO () {
	
		super(Ejercicio.class);
		
	}
	
	@Override
	public IEjercicio find (int key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Ejercicio getEntity (IEjercicio iface) {
		
		return (Ejercicio)iface;
		
	}
	
}