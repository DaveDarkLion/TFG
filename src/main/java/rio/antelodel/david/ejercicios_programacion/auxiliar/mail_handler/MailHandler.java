package rio.antelodel.david.ejercicios_programacion.auxiliar.mail_handler;

import static rio.antelodel.david.ejercicios_programacion.auxiliar.mail_handler.IMailHandlerConfig.*;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;

import rio.antelodel.david.ejercicios_programacion.auxiliar.logger.CustomLogger;

@Service
public class MailHandler implements IMailHandler {
	
	@Override
	public boolean send (String addressTo, String subject, String text) {
		
		// Addresses

		String addressFrom = ADDRESS;
		
		// Properties
		
		Properties properties = getProperties();
		
		// Session
		
		Session session = Session.getDefaultInstance(properties,
                
			new javax.mail.Authenticator() {
            	
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(ADDRESS, PASSWORD);
				}
        
			}
		
		);
		
		// Message
		
		try {
			
			// Multipart for HTML
			
			MimeBodyPart htmlPart = new MimeBodyPart();
		    htmlPart.setContent(text, "text/html; charset=utf-8");
			
			Multipart multipart = new MimeMultipart("html");
			multipart.addBodyPart(htmlPart);
			
			// Message properties
			
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(addressFrom));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressTo));
			message.setSubject(subject);
			message.setContent(multipart);
			message.saveChanges();
			
			// Send
			
			Transport.send(message);
			
			return true;
			
		}
		
		catch (MessagingException e) {
			
			CustomLogger.LOGGER.severe("Sending a mail to " + addressTo + " triggered the exception: " + e.toString());
			return false;
			
		}
		
	}
		
}
