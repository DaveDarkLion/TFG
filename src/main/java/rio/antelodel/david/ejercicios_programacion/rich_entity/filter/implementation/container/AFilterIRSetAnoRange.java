package rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.container;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.iface.container.IFilterIRSetAnoRange;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@Component
public abstract class AFilterIRSetAnoRange <T, U> extends AFilter <T, U> implements IFilterIRSetAnoRange <T, U> {

	protected abstract < V extends U > List<T> getTargetList (V object);
	protected abstract int getAno (T target);
	protected abstract IRTitulacion getIRTitulacion (T target);
	
	public boolean matchesRange (T target, String anoInitial, String anoFinal, IRTitulacion iRTitulacion) {
		
		int ano = getAno(target);
		IRTitulacion iRTitulacionTarget = getIRTitulacion(target);
		
		return (ano >= Integer.parseInt(anoInitial) && ano <= Integer.parseInt(anoFinal) && (iRTitulacion.isNull() || iRTitulacion.equals(iRTitulacionTarget)));
		
	}
	
	public boolean matchesRange (List<T> targetList, String anoInitial, String anoFinal, IRTitulacion iRTitulacion) {
		
		for (T t : targetList) if (matchesRange(t, anoInitial, anoFinal, iRTitulacion)) return true;
		return false;
		
	}
	
	public < V extends U > List <V> matchesRangeGeneric (List<V> objectList, String anoInitial, String anoFinal, IRTitulacion iRTitulacion) {
		
		List<V> targetFinalList = new ArrayList<>();
		
		for (V object : objectList) if (matchesRange(getTargetList(object), anoInitial, anoFinal, iRTitulacion)) targetFinalList.add(object);
		
		return targetFinalList;
		
	}
	
	public boolean matchesRange (T target, String anoInitial, String anoFinal) {
		
		int ano = getAno(target);
		
		return (ano >= Integer.parseInt(anoInitial) && ano <= Integer.parseInt(anoFinal));
		
	}
	
	public boolean matchesRange (List<T> targetList, String anoInitial, String anoFinal) {
		
		for (T t : targetList) if (matchesRange(t, anoInitial, anoFinal)) return true;
		return false;
		
	}
	
	public < V extends U > List <V> matchesRangeGeneric (List<V> objectList, String anoInitial, String anoFinal) {
		
		List<V> targetFinalList = new ArrayList<>();
		
		for (V object : objectList) if (matchesRange(getTargetList(object), anoInitial, anoFinal)) targetFinalList.add(object);
		
		return targetFinalList;
		
	}
	
}
