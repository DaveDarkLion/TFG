package rio.antelodel.david.ejercicios_programacion.auxiliar.password_service;

public interface IPasswordServiceConfig {

	public static final String CHARSET_DEFAULT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
	
	public static final int LENGTH_MIN = 6;
	public static final int LENGTH_MAX = 10;
	public static final int LENGTH_DEFAULT = 8; 
	
}
