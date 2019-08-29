package rio.antelodel.david.ejercicios_programacion.rich_entity.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;

public class IREjercicioSetHelper {

	// Private Constructor
	
	private IREjercicioSetHelper () { }
	
	// Get Visible List
	
	// Get Ejercicio List
	
	public static List<IREjercicio> getIREjercicios (List<? extends IREjercicioSet<?, ?>> iREjercicioSetList) {
		
		List<IREjercicio> rEjerciciosList = new ArrayList<>();
		
		for (IREjercicioSet<?, ?> iRES : iREjercicioSetList) rEjerciciosList.add(iRES.getIREjercicio());
		
		return rEjerciciosList;
		
	}
	
	// Get IREjercicio-Position List
	
	public static <T extends IEjercicioSet, U extends IREjercicioSet<T, U>> List<Pair<IREjercicio, Integer>> getIREjercicioPositionSortedList (List<? extends U> iREjercicioSetList) {
		
		Collections.sort(iREjercicioSetList);
		
		List<Pair<IREjercicio, Integer>> iREjercicioPositionList = new ArrayList<>();
		
		for (IREjercicioSet<?, ?> iRES : iREjercicioSetList) iREjercicioPositionList.add(new Pair<>(iRES.getIREjercicio(), iRES.getPosition()));
		
		return iREjercicioPositionList;
		
	}

}
