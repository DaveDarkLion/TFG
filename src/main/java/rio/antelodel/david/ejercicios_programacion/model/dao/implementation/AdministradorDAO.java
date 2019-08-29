package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAdministrador;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Administrador;

@Repository
public class AdministradorDAO extends AHibernateDAO <IAdministrador, Administrador> implements IAdministradorDAO {
	
	public AdministradorDAO () {
	
		super(Administrador.class);
		
	}
	
	@Override
	public IAdministrador find (String key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Administrador getEntity (IAdministrador iface) {
		
		return (Administrador)iface;
		
	}
	
}