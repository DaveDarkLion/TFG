package rio.antelodel.david.ejercicios_programacion.auxiliar.password_service.restoration;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

public interface IPasswordRestorationService {

	public void registerUser (IRPersona iRPersona);
	
	public boolean canRestore (IRPersona iRPersona);
	
}
