package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IExamenDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Examen;

@Repository
public class ExamenDAO extends AHibernateDAO <IExamen, Examen> implements IExamenDAO {
	
	public ExamenDAO () {
	
		super(Examen.class);
		
	}
	
	@Override
	public IExamen find (int key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Examen getEntity (IExamen iface) {
		
		return (Examen)iface;
		
	}
	
}