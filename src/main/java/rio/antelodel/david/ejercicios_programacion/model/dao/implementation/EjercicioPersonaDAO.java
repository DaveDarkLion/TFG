package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.implementation.EjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPersonaPK;

@Repository
public class EjercicioPersonaDAO extends AHibernateDAO <IEjercicioPersona, EjercicioPersona> implements IEjercicioPersonaDAO {

	public EjercicioPersonaDAO () {
	
		super(EjercicioPersona.class);
		
	}
	
	@Override
	public IEjercicioPersona find (IEjercicioPersonaPK key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public EjercicioPersona getEntity (IEjercicioPersona iface) {
		
		return (EjercicioPersona)iface;
		
	}
	
}