package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface ITitulacion extends IEntity, Serializable {

	public int getId ();
	public void setId (int id);

	public String getNombre ();
	public void setNombre (String nombre);
	
	public Set<IExamen> getIExamenes ();
	public void setIExamenes (Set<IExamen> iExamenes);
	
	public Set<IPractica> getIPracticas ();
	public void setIPracticas (Set<IPractica> iPracticas);
	
	public Set<IPracticaEvaluacion> getIPracticasEvaluacion();
	public void setIPracticasEvaluacion (Set<IPracticaEvaluacion> iPracticasEvaluacion);
	
}
