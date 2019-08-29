package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IArchivo extends IEntity, Serializable {
	
	public int getId ();
	public void setId (int id);
	
	public String getRuta ();
	public void setRuta (String ruta);

	public Set<IEjercicio> getIEjerciciosEntrada ();
	public void setIEjerciciosEntrada (Set<IEjercicio> iEjerciciosEntrada);

	public Set<IEjercicio> getIEjerciciosValidacion ();
	public void setIEjerciciosValidacion (Set<IEjercicio> iEjerciciosValidacion);

	public Set<IEjercicio> getIEjerciciosSolucion ();
	public void setIEjerciciosSolucion (Set<IEjercicio> iEjerciciosSolucion);

}
