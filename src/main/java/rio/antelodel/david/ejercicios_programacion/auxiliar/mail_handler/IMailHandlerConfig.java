package rio.antelodel.david.ejercicios_programacion.auxiliar.mail_handler;

import java.util.Properties;

public interface IMailHandlerConfig {

	// Constants
	
	public static final String ADDRESS = "adm.udc.ep@gmail.com";
	public static final String PASSWORD = "adminpass";
	
	public static final String HOST = "smtp.gmail.com";
	public static final String PORT = "587";
	public static final String AUTH = "true";
	public static final String START_TLS_ENABLE = "true";
	
	// Functions
	
	public static Properties getProperties () {
		
		// New properties
		
		Properties properties = System.getProperties();
		
		// Set properties
		
		properties.setProperty("mail.smtp.host", HOST);
		properties.put("mail.smtp.port", PORT);
		properties.put("mail.smtp.auth", AUTH);
		properties.put("mail.smtp.starttls.enable", START_TLS_ENABLE);
		
		// Return properties
		
		return properties;
		
	}
	
}
