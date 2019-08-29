package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Repository
public class RTitulacionDAO extends ARHibernateDAO <IRTitulacion> implements IRTitulacionDAO {

	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	@Override
	public IRTitulacion find (int key) {
	
		return IRFactory.newIRTitulacion(iTitulacionDAO.find(key));
		
	}
	
	@Override
	public List<IRTitulacion> getAll() {
		
		return IRFactory.getIRTitulacionList(iTitulacionDAO.getAll());
		
	}

}
