package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRTitulacionGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IRTitulacion extends IRichEntity <ITitulacion>, IParseable, Comparable<IRTitulacion>, IFilterIRTitulacionGetter {

	public int getId ();
	
	public String getNombre ();
	public void setNombre (String ruta);
	
	public List <IRExamen> getIRExamenes ();
	
	public List <IRPractica> getIRPracticas ();
	
	public List <IRPracticaEvaluacion> getIRPracticasEvaluacion ();
	
	public boolean hasIRSets ();
	
}
