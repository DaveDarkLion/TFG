package rio.antelodel.david.ejercicios_programacion.controller.view_boolean;

public class ViewBoolean {
	
	public static final String TRUE = Integer.toString(1);
	public static final String FALSE = Integer.toString(0);
	
	private ViewBoolean() { }
	
	public static String toViewBoolean (boolean b) {
	
		if (b) return TRUE;
		else return FALSE;
		
	}
	
}
