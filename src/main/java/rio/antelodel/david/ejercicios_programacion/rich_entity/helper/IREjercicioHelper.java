package rio.antelodel.david.ejercicios_programacion.rich_entity.helper;

import java.util.ArrayList;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public class IREjercicioHelper {

	// Private Constructor
	
	private IREjercicioHelper () { }
	
	// Get Visible List
	
	public static List<IREjercicio> getVisibleForUnprivilegedList (List<IREjercicio> iREjercicioList) {
		
		List<IREjercicio> rEjerciciosVisibleList = new ArrayList<>();
		
		for (IREjercicio rE : iREjercicioList) if (rE.isVisibleForUnprivileged()) rEjerciciosVisibleList.add(rE);
		
		return rEjerciciosVisibleList;
		
	}

}
