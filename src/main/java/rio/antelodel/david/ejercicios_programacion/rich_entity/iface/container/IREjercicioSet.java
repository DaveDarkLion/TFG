package rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;

public interface IREjercicioSet <T extends IEjercicioSet, U extends IREjercicioSet<T, U>> extends IRichEntity <T>, Comparable<U> {

	public IREjercicio getIREjercicio ();
	
	public int getPosition ();
	public void setPosition (int position);
	
	public IRSet<?, ?, ?, ?> getIRSet ();
	
	public U getPrevious (List<U> iREjerciciosSetList);
	
	public U getNext (List<U> iREjerciciosSetList);
	
	public void exchange (U iREjercicioSet);
	
}
