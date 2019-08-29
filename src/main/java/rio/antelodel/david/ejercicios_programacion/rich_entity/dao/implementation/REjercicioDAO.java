package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

@Repository
public class REjercicioDAO extends ARHibernateDAO <IREjercicio> implements IREjercicioDAO {

	@Autowired
	private IEjercicioDAO iEjercicioDAO;
	
	@Override
	public IREjercicio find(int key) {
		
		return IRFactory.newIREjercicio(iEjercicioDAO.find(key));
		
	}
	
	@Override
	public List<IREjercicio> getAll() {
		
		return IRFactory.getIREjercicioList(iEjercicioDAO.getAll());
		
	}

}
