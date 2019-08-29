package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetComplexEntity;

public interface IPractica extends ISetComplexEntity <IEjercicioPractica>, Serializable {

	public void setId (int id);
	
	public Set<IEjercicioPractica> getIEjerciciosPractica ();
	public void setIEjerciciosPractica (Set<IEjercicioPractica> iEjerciciosPractica);
	
}
