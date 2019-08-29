package rio.antelodel.david.ejercicios_programacion.rich_entity.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetComplexEntity;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioComplexSet;

public class IRComplexSetHelper {

	// Private Constructor
	
	private IRComplexSetHelper () { }
	
	public static < W extends IRComplexSet <T, U, V, W>,  T extends ISetComplexEntity<U>, U extends IEjercicioSet, V extends IREjercicioComplexSet<U, V> & Comparable<V> > List<W> getVisibleList (List<W> rSetsList) {
		
		List<W> rSetsVisibleList = new ArrayList<>();
		
		for (W set : rSetsList) if (set.isVisible()) rSetsVisibleList.add(set);
		Collections.sort(rSetsVisibleList);
		
		return rSetsVisibleList;
		
	}

}
