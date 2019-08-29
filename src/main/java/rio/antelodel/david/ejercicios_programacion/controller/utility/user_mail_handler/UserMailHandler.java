package rio.antelodel.david.ejercicios_programacion.controller.utility.user_mail_handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rio.antelodel.david.ejercicios_programacion.auxiliar.mail_handler.IMailHandler;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;

@Service
public class UserMailHandler implements IUserMailHandler {

	// Mail Handler

	@Autowired
	private IMailHandler iMailHandler;
	public void setIMailHandler (IMailHandler iMailHandler) { this.iMailHandler = iMailHandler; }
	
	// Send confirmation mail with Password
	
	protected boolean sendConfirmationMail (IRUser iRUser, String password) {
		
		final String subject 	= 	"Confirmación de cuenta";
		final String text 		= 	getGreetingDefault(iRUser) + " Se ha creado una cuenta asociada a este correo electrónico en la aplicación de ejercicios de programación de la UDC. "
									+ "Se le ha asignado la siguiente contraseña temporal: <b>" + password + "</b>. "
									+ "Le recomendamos que cambie esta contraseña en cuanto le sea posible.";
		
		return iMailHandler.send(iRUser.getEmail(), subject, text);
		
	}
	
	@Override
	public boolean sendConfirmationMail (IRUser iRUser) {
		
		return sendConfirmationMail(iRUser, iRUser.getPassword());
		
	}
	
	// Send Profile updated mail
	
	@Override
	public boolean sendProfileUpdateMail (IRUser iRUser) {
		
		final String subject 	= "Actualización de cuenta";
		final String text 		= getGreetingDefault(iRUser) + " Le informamos de que su perfil acaba de ser actualizado. Le rogamos que revise si los cambios son correctos.";
		
		return iMailHandler.send(iRUser.getEmail(), subject, text);
		
	}
	
	// Send Profile deleted main
	
	@Override
	public boolean sendProfileDeletionMail (IRUser iRUser) {
		
		final String subject 	= "Actualización de cuenta";
		final String text 		= getGreetingDefault(iRUser) + " Le informamos de que su perfil acaba de ser eliminado. Si cree que esto es un error, póngase en contacto con el administrador.";
		
		return iMailHandler.send(iRUser.getEmail(), subject, text);
		
	}
	
	// Send Password restoration mail
	
	protected boolean sendPasswordRestorationMail (IRUser iRUser, String password) {
		
		final String subject 	= 	"Restauración de contraseña";
		final String text 		= 	getGreetingDefault(iRUser) + " Le informamos de que su password ha sido restaurado satisfactoriamente. "
									+ "Su nuevo password temporal es: <b>" + password + "</b>. Le recomendamos que lo cambie por otro cuanto antes.";
		
		return iMailHandler.send(iRUser.getEmail(), subject, text);
		
	}
	
	public boolean sendPasswordRestorationMail (IRUser iRUser) {
		
		return sendPasswordRestorationMail(iRUser, iRUser.getPassword());
		
	}
	
	// Get default greeting
	
	protected String getGreetingDefault (IRUser iRUser) {
			
		return "Hola, <i>" + iRUser.getNombre() + "</i>.";
			
	}
	
}
