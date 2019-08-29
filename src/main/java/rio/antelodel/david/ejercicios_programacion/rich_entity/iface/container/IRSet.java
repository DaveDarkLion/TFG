package rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetEntity;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRProfesorGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

public interface IRSet <T extends ISetEntity<U>, U extends IEjercicioSet, V extends IREjercicioSet<U, V> & Comparable<V>, W extends IRSet<T, U, V, W>> extends IRichEntity<T>, IFilterIRProfesorGetter {

	public List<IREjercicio> getIREjercicios ();
	
	public List<IRProfesor> getIRProfesores ();
	
	public List<IRCategoria> getIRCategorias ();
	
	public List<V> getIREjerciciosSet ();
	
	public List<IREjercicio> getIREjerciciosSortedList ();
	
	public < X extends IREjercicioSet<?, X> > int getLastPosition (List<X> iREjerciciosSetList);
	
}
