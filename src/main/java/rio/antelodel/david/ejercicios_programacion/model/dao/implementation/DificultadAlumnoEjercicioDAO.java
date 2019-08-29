package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IDificultadAlumnoEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.implementation.DificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IDificultadAlumnoEjercicioPK;

@Repository
public class DificultadAlumnoEjercicioDAO extends AHibernateDAO <IDificultadAlumnoEjercicio, DificultadAlumnoEjercicio> implements IDificultadAlumnoEjercicioDAO {

	public DificultadAlumnoEjercicioDAO () {
	
		super(DificultadAlumnoEjercicio.class);
		
	}
	
	@Override
	public IDificultadAlumnoEjercicio find (IDificultadAlumnoEjercicioPK key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public DificultadAlumnoEjercicio getEntity (IDificultadAlumnoEjercicio iface) {
		
		return (DificultadAlumnoEjercicio)iface;
		
	}
	
}