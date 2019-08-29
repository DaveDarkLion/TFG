package rio.antelodel.david.ejercicios_programacion.controller.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.set_type.SetType;

public class ControllerInfo {

	// Private Constructor
	
	private ControllerInfo () { }
	
	// Links
	
	public static final String LINK_ADD = "/add";
	public static final String LINK_DELETE = "/delete";
	public static final String LINK_GENERIC_ID = "/{id:[0-9]+}";
	public static final String LINK_GENERIC_EMAIL = "/{email:.*}";
	public static final String EXTENSION = ".html";
	
	// Calendar
	
	private static final int ANO_MIN = 2000;
	public static int getAnoMin () { return ANO_MIN; } 
	
	public static int getAnoMax () { return Calendar.getInstance().get(Calendar.YEAR) + 1; }
	
	// Page
	
	public static final int PAGE_SIZE_DEFAULT = 10;
	
	private static final List<Pair<Integer, Integer>> PAGE_SIZE_LIST = new ArrayList<>(Arrays.asList(	new Pair<>(5), new Pair<>(10), new Pair<>(20), new Pair<>(50), new Pair<>(100)));
	public static List<Pair<Integer, Integer>> getPageSizeList () { return PAGE_SIZE_LIST; }
	
	// Role
	
	public static final Pair<String, String> PAIR_ROLE_ANY = new Pair<>("any", "Cualquiera");
	public static final Pair<String, String> PAIR_ROLE_ALUMNO = new Pair<>(Role.ALUMNO, "Alumno");
	public static final Pair<String, String> PAIR_ROLE_PROFESOR = new Pair<>(Role.PROFESOR, "Profesor");
	public static final Pair<String, String> PAIR_ROLE_ADMIN = new Pair<>(Role.ADMIN, "Administrador");
	
	private static final List<Pair<String, String>> ROLE_LIST = new ArrayList<>(Arrays.asList(PAIR_ROLE_ALUMNO, PAIR_ROLE_PROFESOR, PAIR_ROLE_ADMIN));
	public static final List<Pair<String, String>> getRoleList () { return ROLE_LIST; }
	
	// SetType

	public static final SetType SET_TYPE_ALL = new SetType(SetType.Type.ALL, 0, "Ejercicios", "ejercicios", false, false);
	public static final SetType SET_TYPE_CART = new SetType(SetType.Type.CART, 1, "Mi selección", "ejercicios", true, true);
	public static final SetType SET_TYPE_EXAMEN = new SetType(SetType.Type.EXAMEN, 2, "Examen", "examenes", true, false);
	public static final SetType SET_TYPE_PRACTICA = new SetType(SetType.Type.PRACTICA, 3, "Práctica", "practicas",  true, false);
	public static final SetType SET_TYPE_PRACTICA_EVALUACION = new SetType(SetType.Type.PRACTICA_EVALUABLE, 4, "Práctica evaluable", "practicas-evaluacion", true, false);
	
	private static final List<SetType> SET_TYPE_LIST = new ArrayList<>(Arrays.asList(	SET_TYPE_ALL,
																						SET_TYPE_CART,
																						SET_TYPE_EXAMEN,
																						SET_TYPE_PRACTICA,
																						SET_TYPE_PRACTICA_EVALUACION));
	public static List<SetType> getSetTypeList () { return  SET_TYPE_LIST; }
	
	private static final List<SetType> SET_TYPE_REDUCED_LIST = new ArrayList<>(Arrays.asList(SET_TYPE_EXAMEN, SET_TYPE_PRACTICA, SET_TYPE_PRACTICA_EVALUACION));
	public static List<SetType> getSetTypeReducedList () { return  SET_TYPE_REDUCED_LIST; }
	
}
