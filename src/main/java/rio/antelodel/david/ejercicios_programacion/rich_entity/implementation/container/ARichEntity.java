package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container;

import java.util.Objects;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public abstract class ARichEntity < T extends IEntity > implements IRichEntity <T> {

	// Entity
	
	protected T entity;
	
	// Constructors
	
	public ARichEntity () { }
	
	public ARichEntity (T entity) {
		
		this.entity = entity;
		
	}
	
	// Getters & Setters
	
	@Override
	public T getEntity () { return entity; }
	
	// Check null
	
	@Override
	public boolean isNull () { return getEntity() == null; }
	
	// Equals
	
	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals (Object o) {
	
		if (o instanceof ARichEntity) {
		
			ARichEntity aRichEntity = (ARichEntity)o;
			return getEntity().equals(aRichEntity.getEntity());
			
		}
		
		return false;
		
	}
	
	// Hash
	
	@Override
	public int hashCode () {

		return Objects.hash(getEntity());
		
	}
	
}
