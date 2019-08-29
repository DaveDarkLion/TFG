package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioExamenDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.implementation.EjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioExamenPK;

@Repository
public class EjercicioExamenDAO extends AHibernateDAO <IEjercicioExamen, EjercicioExamen> implements IEjercicioExamenDAO {

	public EjercicioExamenDAO () {
	
		super(EjercicioExamen.class);
		
	}
	
	@Override
	public IEjercicioExamen find (IEjercicioExamenPK key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public EjercicioExamen getEntity (IEjercicioExamen iface) {
		
		return (EjercicioExamen)iface;
		
	}
	
}