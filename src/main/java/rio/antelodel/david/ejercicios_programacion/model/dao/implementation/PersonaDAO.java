package rio.antelodel.david.ejercicios_programacion.model.dao.implementation;

import org.springframework.stereotype.Repository;

import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.implementation.container.AHibernateDAO;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Persona;

@Repository
public class PersonaDAO extends AHibernateDAO <IPersona, Persona> implements IPersonaDAO {
	
	public PersonaDAO () {
	
		super(Persona.class);
		
	}
	
	@Override
	public IPersona find (String key) {
			
		return getCurrentSession().get(getCurrentClass(), key);
			
	}
	
	@Override
	public Persona getEntity (IPersona iface) {
		
		return (Persona)iface;
		
	}
	
}