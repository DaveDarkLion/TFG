package rio.antelodel.david.ejercicios_programacion.auxiliar.password_service;

import static rio.antelodel.david.ejercicios_programacion.auxiliar.password_service.IPasswordServiceConfig.*;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class PasswordService implements IPasswordService {

	// Randomize a new Password
	
	protected String randomizePassword (int length, String charset) {
		
		StringBuilder password = new StringBuilder();
		
		Random random = new Random();
		
		for (int i = 0; i < length; i++) password.append(charset.charAt(random.nextInt(charset.length())));
		
		return password.toString();
		
	}
	
	@Override
	public String randomizePassword () {
		
		return randomizePassword(LENGTH_DEFAULT, CHARSET_DEFAULT);
		
	}
	
	// Check Password format
	
	protected boolean checkPasswordFormat (String password, int lengthMin, int lengthMax, String charset) {
		
		int length = password.length();
		
		if (length < lengthMin || length > lengthMax) return false;
		
		for (int i = 0; i < length; i++) {
			
			char current = password.charAt(i);
			
			if (charset.indexOf(current) < 0) return false;
			
		}
		
		return true;
		
	}
	
	@Override
	public boolean checkPasswordFormat (String password) {
		
		return checkPasswordFormat(password, LENGTH_MIN, LENGTH_MAX, CHARSET_DEFAULT);
		
	}
	
}
