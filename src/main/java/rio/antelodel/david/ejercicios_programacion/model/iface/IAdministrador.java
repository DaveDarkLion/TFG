package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IAdministrador extends IEntity, Serializable {

	public IPersona getIPersona ();
	public void setIPersona (IPersona iPersona);
	
}
