package rio.antelodel.david.ejercicios_programacion.controller.utility;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.PAIR_ROLE_ADMIN;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.PAIR_ROLE_ALUMNO;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerInfo.PAIR_ROLE_PROFESOR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;

import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.AREjercicioSet;

public class ControllerUtility {

	// Private Constructor
	
	private ControllerUtility () { }
	
	// Check if User is Role
	
	public static boolean userIsRole (String role, SecurityContext context) {
		
		for (GrantedAuthority gA : context.getAuthentication().getAuthorities())
			if (gA.getAuthority().equals(role)) return true;
		
		return false;
		
	}
	
	// Get List of roles for User
	
	public static List<Pair<String, String>> getUserRoles (IRPersona iRPersona) {
		
		List<Pair<String, String>> roleList = new ArrayList<>();
		
		if (iRPersona.isAlumno()) roleList.add(PAIR_ROLE_ALUMNO);
		if (iRPersona.isProfesor()) roleList.add(PAIR_ROLE_PROFESOR);
		if (iRPersona.isAdministrador()) roleList.add(PAIR_ROLE_ADMIN);
		
		return roleList;
		
	}
	
	// Parameter validation
	
	public static boolean isValid (String param) {
		
		return (param != null && !param.isEmpty());
		
	}
	
	public static boolean isValid (String param, String... any) {
		
		if (!isValid(param)) return false;
		for (String s : any) if (param.equals(s)) return false;
		return true;
		
	}
	
	public static boolean isValid (String param, int... any) {
		
		if (!isValid(param)) return false;
		
		int iParam = Integer.parseInt(param);
		for (int i : any) if (iParam == i) return false;
		
		return true;
		
	}
	
	public static boolean isValid (String [] params) {
		
		return (params != null && params.length > 0);
		
	}
	
	// Page
	
	// Get page info
	
	public static int getPageInfo (String page, int def) {
		
		if (isInt(page) && Integer.parseInt(page) >= 1) return Integer.parseInt(page);
		else return def;
		
	}
	
	// Get page last
	
	public static int getPageLast (List<?> list, int pageSize) {
		
		return ((list.size() - 1) / pageSize) + 1;
		
	}
	
	// Get paged list
	
	public static <T> List<T> getPagedList (List<T> list, int page, int pageSize) {
		
		List<T> setListPaged = new ArrayList<>();
		
		for (int i = pageSize * (page - 1); i < pageSize * page && i < list.size(); i++) setListPaged.add(list.get(i));
		
		return setListPaged;
		
	}
	
	// Pair
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Pair<IREjercicio, Integer>> getPairEjercicioSetList (List<? extends AREjercicioSet> rEjerciciosSetList) {
		
		List<Pair<IREjercicio, Integer>> rEjerciciosPositionList = new ArrayList<>();
		
		Collections.sort(rEjerciciosSetList);
		
		int i = 1;
		for (IREjercicioSet<?, ?> rES : rEjerciciosSetList) { rEjerciciosPositionList.add(new Pair<>(rES.getIREjercicio(), i)); i++; }
		
		return rEjerciciosPositionList;
		
	}
	
	// Array
	
	public static <T> List<T> getList (T[] array) {
		
		if (array == null) return new ArrayList<>();
		else return Arrays.asList(array);
		
	}
	
	// Math
	
	// Int
	
	public static int toInt (String param, int def) {
		
		if (isInt(param)) return Integer.parseInt(param);
		else return def;
		
	}
	
	public static int toInt (String param) {
	
		return toInt(param, 0);
		
	}
	
	public static boolean isInt (String param) {
		
		try { Integer.parseInt(param); return true; }
		catch (Exception e) { return false; }
		
	}
	
	// Float
	
	public static float toFloat (String param, float def) {
		
		if (isFloat(param)) return Float.parseFloat(param);
		else return def;
		
	}
	
	public static float toFloat (String param) {
		
		return toFloat(param, 0f);
		
	}
	
	public static boolean isFloat (String param) {
		
		try { Float.parseFloat(param); return true; }
		catch (Exception e) { return false; }
		
	}
	
	// String
	
	public static String toStr (String param, String def) {
		
		if (param != null) return param;
		else return def;
		
	}
	
	public static String toStr (String param) {
		
		return toStr(param, "");
		
	}
	
}
