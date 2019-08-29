package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.implementation.EjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaEvaluacionPK;

@Repository
public class EjercicioPracticaEvaluacionDAO extends AHibernateDAO <IEjercicioPracticaEvaluacion, EjercicioPracticaEvaluacion> implements IEjercicioPracticaEvaluacionDAO {

	public EjercicioPracticaEvaluacionDAO () {
	
		super(EjercicioPracticaEvaluacion.class);
		
	}
	
	@Override
	public IEjercicioPracticaEvaluacion find (IEjercicioPracticaEvaluacionPK key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public EjercicioPracticaEvaluacion getEntity (IEjercicioPracticaEvaluacion iface) {
		
		return (EjercicioPracticaEvaluacion)iface;
		
	}
	
}