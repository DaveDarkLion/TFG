package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IDificultadAlumnoEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IDificultadAlumnoEjercicioPK;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadAlumnoEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

@Repository
public class RDificultadAlumnoEjercicioDAO extends ARHibernateDAO <IRDificultadAlumnoEjercicio> implements IRDificultadAlumnoEjercicioDAO {

	@Autowired
	private IDificultadAlumnoEjercicioDAO iDificultadAlumnoEjercicioDAO;
	
	@Override
	public IRDificultadAlumnoEjercicio find (IRAlumno iRAlumno, IREjercicio iREjercicio) {
	
		return IRFactory.newIRDificultadAlumnoEjercicio(iDificultadAlumnoEjercicioDAO.find(new IDificultadAlumnoEjercicioPK(iRAlumno.getEntity(), iREjercicio.getEntity())));
		
	}
	
	@Override
	public List<IRDificultadAlumnoEjercicio> getAll() {
		
		return IRFactory.getIRDificultadAlumnoEjercicioList(iDificultadAlumnoEjercicioDAO.getAll());
		
	}

}
