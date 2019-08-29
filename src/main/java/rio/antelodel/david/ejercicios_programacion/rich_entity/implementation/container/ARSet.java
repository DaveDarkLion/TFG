package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetEntity;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRSet;

public abstract class ARSet < T extends ISetEntity<U>, U extends IEjercicioSet, V extends IREjercicioSet<U, V> & Comparable<V>, W extends IRSet<T, U, V, W>> extends ARichEntity < T > implements IRSet<T, U, V, W> {

	// Constructors
	
	public ARSet (T entity) {
		
		super(entity);
		
	}
	
	// Entity Functions
	
	@Override
	public List<IREjercicio> getIREjercicios () {
		
		List<IREjercicio> iREjercicioList = new ArrayList<>();
		for (V ejercicioSet : getIREjerciciosSet()) iREjercicioList.add(ejercicioSet.getIREjercicio());
		
		return iREjercicioList;
		
	}
	
	@Override
	public List<IRProfesor> getIRProfesores () {
		
		ArrayList<IRProfesor> iRProfesoresList = new ArrayList<>();
		for (IREjercicio iRE : getIREjercicios()) iRProfesoresList.add(iRE.getIRProfesor());
		
		return iRProfesoresList;
		
	}
	
	@Override
	public List<IRCategoria> getIRCategorias () {
		
		ArrayList<IRCategoria> iRCategoriasList = new ArrayList<>();
		for (IREjercicio iRE : getIREjercicios()) iRCategoriasList.addAll(iRE.getIRCategorias());
		
		return iRCategoriasList;
		
	}
	
	// Get sorted Ejercicio List
	
	@Override
	public List<IREjercicio> getIREjerciciosSortedList () {
		
		List<V> ejerciciosSetList = getIREjerciciosSet();
		Collections.sort(ejerciciosSetList);
		
		List<IREjercicio> iREjerciciosList = new ArrayList<>();
		for (V eS : ejerciciosSetList) iREjerciciosList.add(eS.getIREjercicio());
		
		return iREjerciciosList;
		
	}
	
	// Get last position
	
	@Override
	public < X extends IREjercicioSet<?, X> > int getLastPosition (List<X> iREjerciciosSetList) {
		
		if (iREjerciciosSetList == null || iREjerciciosSetList.isEmpty()) return 0;
		else {
		
			int positionMax = -1;
			
			for (X rEjercicioSet : iREjerciciosSetList) 
				if (rEjercicioSet.getIRSet().equals(this) && rEjercicioSet.getPosition() > positionMax) positionMax = rEjercicioSet.getPosition();
			
			return positionMax + 1;
			
		}
		
	}
	
	// Filters
	
	// Profesor

	@Override
	public List<IRProfesor> getFilterIRProfesor () {
		
		return getIRProfesores();
		
	}

}
