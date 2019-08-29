package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Alumno;

@Repository
public class AlumnoDAO extends AHibernateDAO <IAlumno, Alumno> implements IAlumnoDAO {

	public AlumnoDAO () {
	
		super(Alumno.class);
		
	}
	
	@Override
	public IAlumno find (String key) {
	
		return getCurrentSession().find(getCurrentClass(), key);
		
	}
	
	@Override
	public Alumno getEntity (IAlumno iface) {
		
		return (Alumno)iface;
		
	}
	
}