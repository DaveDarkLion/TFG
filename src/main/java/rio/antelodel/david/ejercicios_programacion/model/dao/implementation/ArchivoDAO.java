package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IArchivoDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IArchivo;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Archivo;

@Repository
public class ArchivoDAO extends AHibernateDAO <IArchivo, Archivo> implements IArchivoDAO {
	
	public ArchivoDAO () {
	
		super(Archivo.class);
		
	}
	
	@Override
	public IArchivo find (int key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Archivo getEntity (IArchivo iface) {
		
		return (Archivo)iface;
		
	}
	
}