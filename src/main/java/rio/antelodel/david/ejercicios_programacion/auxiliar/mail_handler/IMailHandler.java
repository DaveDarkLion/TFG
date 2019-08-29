package rio.antelodel.david.ejercicios_programacion.auxiliar.mail_handler;

public interface IMailHandler {

	public boolean send (String addressTo, String subject, String text);
	
}
