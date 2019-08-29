package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPersonaPK;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.ARHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPersona;

@Repository
public class REjercicioPersonaDAO extends ARHibernateDAO <IREjercicioPersona> implements IREjercicioPersonaDAO {

	@Autowired
	private IEjercicioPersonaDAO iEjercicioPersonaDAO;
	
	@Override
	public IREjercicioPersona find (IREjercicio rEjercicio, IRPersona rPersona) {

		return new REjercicioPersona(iEjercicioPersonaDAO.find(new IEjercicioPersonaPK(rEjercicio.getEntity(), rPersona.getEntity())));
		
	}
	
	@Override
	public List<IREjercicioPersona> getAll () {
		
		return IRFactory.getIREjercicioPersonaList(iEjercicioPersonaDAO.getAll());
		
	}

}
