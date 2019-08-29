package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.container;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

public interface IFilterIRSetAnoRange <T, U> extends IFilter <T, U> {
	
	public boolean matchesRange (T target, String anoInitial, String anoFinal, IRTitulacion iRTitulacion);
	
	public boolean matchesRange (List<T> targetList, String anoInitial, String anoFinal, IRTitulacion iRTitulacion);
	
	public < V extends U > List <V> matchesRangeGeneric (List<V> objectList, String anoInitial, String anoFinal, IRTitulacion iRTitulacion);
	
	public boolean matchesRange (T target, String anoInitial, String anoFinal);
	
	public boolean matchesRange (List<T> targetList, String anoInitial, String anoFinal);
	
	public < V extends U > List <V> matchesRangeGeneric (List<V> objectList, String anoInitial, String anoFinal);
	
}