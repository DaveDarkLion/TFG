package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetComplexEntity;

public interface IExamen extends ISetComplexEntity <IEjercicioExamen>, Serializable {

	public void setId (int id);
	
	public Set<IEjercicioExamen> getIEjerciciosExamen ();
	public void setIEjerciciosExamen (Set<IEjercicioExamen> iEjerciciosExamen);
	
}
