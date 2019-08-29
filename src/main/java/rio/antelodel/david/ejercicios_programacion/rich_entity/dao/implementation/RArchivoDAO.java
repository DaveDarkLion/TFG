package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IArchivoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRArchivoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;

@Repository
public class RArchivoDAO extends ARHibernateDAO <IRArchivo> implements IRArchivoDAO {

	@Autowired
	private IArchivoDAO iArchivoDAO;
	
	@Override
	public IRArchivo find (int key) {
	
		return IRFactory.newIRArchivo(iArchivoDAO.find(key));
		
	}
	
	@Override
	public List<IRArchivo> getAll() {
		
		return IRFactory.getIRArchivoList(iArchivoDAO.getAll());
		
	}

}
