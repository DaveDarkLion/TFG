package rio.antelodel.david.ejercicios_programacion.model.iface.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;

public interface ISetComplexEntity <T extends IEjercicioSet> extends ISetEntity <T> {
	
	public abstract int getId ();
	
	public abstract int getMes ();
	public abstract void setMes (int mes);
	
	public abstract int getAno ();
	public abstract void setAno (int ano);
	
	public abstract String getDescripcion ();
	public abstract void setDescripcion(String descripcion);
	
	public abstract ITitulacion getITitulacion ();
	public abstract void setITitulacion (ITitulacion iTitulacion);
	
	public abstract boolean isVisible ();
	public abstract void setVisible (boolean visible);
	
	public abstract boolean isAbierto ();
	public abstract void setAbierto (boolean abierto);
	
	public Set<IEjercicio> getIEjercicios ();
	
	public static < V extends ISetComplexEntity<?> > List<V> getVisibleList (List<V> list) {
	
		List<V> visibleList = new ArrayList<>();
		
		for (V v : list) if (v.isVisible()) visibleList.add(v);
		
		return visibleList;
		
	}
	
}
