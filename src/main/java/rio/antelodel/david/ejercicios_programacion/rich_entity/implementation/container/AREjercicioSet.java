package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container;

import java.util.Collections;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioSet;

public abstract class AREjercicioSet < T extends IEjercicioSet, U extends IREjercicioSet<T, U> > extends ARichEntity < T > implements IREjercicioSet<T, U> {

	// Constructors
	
	public AREjercicioSet () { }
	
	public AREjercicioSet (T entity) {
		
		super(entity);
		
	}
	
	// Entity Functions
	
	@Override
	public IREjercicio getIREjercicio () { return IRFactory.newIREjercicio(getEntity().getIEjercicio()); }
	
	@Override
	public int getPosition () { return getEntity().getPosition(); }
	@Override
	public void setPosition (int position) { getEntity().setPosition(position); }
	
	// Get previous
	
	@Override
	public U getPrevious (List<U> iREjerciciosSetList) {
	
		Collections.sort(iREjerciciosSetList);
		
		int index = iREjerciciosSetList.indexOf(this);
		
		if (index > 0) return iREjerciciosSetList.get(index - 1);
		else return null;
		
	}
	
	// Get next
	
	@Override
	public U getNext (List<U> iREjerciciosSetList) {
		
		Collections.sort(iREjerciciosSetList);
		
		int index = iREjerciciosSetList.indexOf(this);
		
		if (index < iREjerciciosSetList.size() - 1) return iREjerciciosSetList.get(index + 1);
		else return null;
		
	}
	
	// Exchange Ejercicio positions
	
	@Override
	public void exchange (U iREjercicioSet) {
	
		int position1 = getPosition();
		
		setPosition(iREjercicioSet.getPosition());
		iREjercicioSet.setPosition(position1);
		
	}
	
	// Comparable

	@Override
	public int compareTo (U iREjercicioSet) {
		
		return Integer.compare(getPosition(), iREjercicioSet.getPosition());
		
	}
	
}
