package rio.antelodel.david.ejercicios_programacion.model.iface.container;

import java.util.Set;

public interface ISetEntity <T extends IEjercicioSet> extends IEntity {
	
	public abstract Set<T> getIEjerciciosSet ();
	
}
