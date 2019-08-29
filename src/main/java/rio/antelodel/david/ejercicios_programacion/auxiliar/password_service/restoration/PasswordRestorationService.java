package rio.antelodel.david.ejercicios_programacion.auxiliar.password_service.restoration;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@Service
public class PasswordRestorationService implements IPasswordRestorationService {

	// List of Pair User Calendar
	
	private Map<IRPersona, Calendar> iRPersonaCalendarMap = new HashMap<>();
	
	// Functions
	
	public void registerUser (IRPersona iRPersona) {
		
		iRPersonaCalendarMap.put(iRPersona, Calendar.getInstance());
		
	}
	
	protected boolean canRestore (IRPersona iRPersona, int seconds) {
		
		return 	!iRPersonaCalendarMap.containsKey(iRPersona)
				|| Calendar.getInstance().getTimeInMillis() - iRPersonaCalendarMap.get(iRPersona).getTimeInMillis() >= seconds * 1000;
		
	}
	
	public boolean canRestore (IRPersona iRPersona) {
		
		return canRestore(iRPersona, IPasswordRestorationServiceConfig.DELAY_SECONDS);
		
	}
	
}
