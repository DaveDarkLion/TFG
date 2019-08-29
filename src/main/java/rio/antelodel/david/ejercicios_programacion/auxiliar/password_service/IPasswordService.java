package rio.antelodel.david.ejercicios_programacion.auxiliar.password_service;

public interface IPasswordService {
	
	public String randomizePassword ();
	
	public boolean checkPasswordFormat (String password);
	
}
