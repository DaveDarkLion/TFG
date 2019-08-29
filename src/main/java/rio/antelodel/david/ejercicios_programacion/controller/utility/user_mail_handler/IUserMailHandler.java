package rio.antelodel.david.ejercicios_programacion.controller.utility.user_mail_handler;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;

public interface IUserMailHandler {
	
	public boolean sendConfirmationMail (IRUser iRUser);

	public boolean sendProfileUpdateMail (IRUser iRUser);
	
	public boolean sendProfileDeletionMail (IRUser iRUser);
	
	public boolean sendPasswordRestorationMail (IRUser iRUser);
	
}
