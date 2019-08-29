package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Titulacion;

@Repository
public class TitulacionDAO extends AHibernateDAO <ITitulacion, Titulacion> implements ITitulacionDAO {
	
	public TitulacionDAO () {
	
		super(Titulacion.class);
		
	}
	
	@Override
	public ITitulacion find (int key) {
			
		return getCurrentSession().get(getCurrentClass(), key);
			
	}
	
	@Override
	public Titulacion getEntity (ITitulacion iface) {
		
		return (Titulacion)iface;
		
	}
	
}