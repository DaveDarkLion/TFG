package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.implementation.PracticaEvaluacion;

@Repository
public class PracticaEvaluacionDAO extends AHibernateDAO <IPracticaEvaluacion, PracticaEvaluacion> implements IPracticaEvaluacionDAO {
	
	public PracticaEvaluacionDAO () {
	
		super(PracticaEvaluacion.class);
		
	}
	
	@Override
	public IPracticaEvaluacion find (int key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public PracticaEvaluacion getEntity (IPracticaEvaluacion iface) {
		
		return (PracticaEvaluacion)iface;
		
	}
	
}