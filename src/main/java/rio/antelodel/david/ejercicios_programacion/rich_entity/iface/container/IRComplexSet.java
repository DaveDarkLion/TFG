package rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetComplexEntity;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRCategoriaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRTitulacionGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

public interface IRComplexSet <T extends ISetComplexEntity<U>, U extends IEjercicioSet, V extends IREjercicioComplexSet<U, V>, W extends IRComplexSet<T, U, V, W>> extends IRSet<T, U, V, W>, IParseable, Comparable<W>, IFilterIRCategoriaGetter, IFilterIRTitulacionGetter {

	public int getId ();
	
	public boolean isVisible ();
	public void setVisible (boolean visible);
	
	public boolean isAbierto ();
	public void setAbierto (boolean abierto);
	
	public int getMes ();
	public void setMes (int mes);
	
	public int getAno ();
	public void setAno (int ano);
	
	public String getDescripcion ();
	public void setDescripcion (String descripcion);
	
	public IRTitulacion getIRTitulacion ();
	public void setIRTitulacion (IRTitulacion iRTitulacion);
	
}
