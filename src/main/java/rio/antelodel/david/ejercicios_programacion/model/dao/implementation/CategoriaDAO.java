package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ICategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Categoria;

@Repository
public class CategoriaDAO extends AHibernateDAO <ICategoria, Categoria> implements ICategoriaDAO {
	
	public CategoriaDAO () {
	
		super(Categoria.class);
		
	}
	
	@Override
	public ICategoria find (int key) {
		
		return getCurrentSession().get(getCurrentClass(), key);
		
	}
	
	@Override
	public Categoria getEntity (ICategoria iface) {
		
		return (Categoria)iface;
		
	}
	
}