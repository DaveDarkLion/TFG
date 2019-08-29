package rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioExamenDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioExamenPK;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.implementation.container.AREjercicioSetDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;

@Repository
public class REjercicioExamenDAO extends AREjercicioSetDAO <IREjercicioExamen, IEjercicioExamen, IRExamen> implements IREjercicioExamenDAO {

	@Autowired
	private IEjercicioExamenDAO iEjercicioExamenDAO;
	
	@Override
	public IREjercicioExamen find (IREjercicio iREjercicio, IRExamen iRExamen) {

		return IRFactory.newIREjercicioExamen(iEjercicioExamenDAO.find(new IEjercicioExamenPK(iREjercicio.getEntity(), iRExamen.getEntity())));
		
	}
	
	@Override
	public List<IREjercicioExamen> getAll () {
		
		return IRFactory.getIREjercicioExamenList(iEjercicioExamenDAO.getAll());
		
	}

}
