package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEjercicioSet;
import rio.antelodel.david.ejercicios_programacion.model.iface.container.ISetComplexEntity;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioComplexSet;

public abstract class ARComplexSet < T extends ISetComplexEntity<U>, U extends IEjercicioSet, V extends IREjercicioComplexSet<U, V> & Comparable<V>, W extends IRComplexSet<T, U, V, W>> extends ARSet <T, U, V, W> implements IRComplexSet<T, U, V, W> {
	
	// Constructors
	
	public ARComplexSet (T entity) {
		
		super(entity);
		
	}
	
	// Entity Functions
	
	@Override
	public int getId () { return getEntity().getId(); }
	
	@Override
	public boolean isVisible () { return getEntity().isVisible(); }
	@Override
	public void setVisible (boolean visible) { getEntity().setVisible(visible); }
	
	@Override
	public boolean isAbierto () { return getEntity().isAbierto(); }
	@Override
	public void setAbierto (boolean abierto) { getEntity().setAbierto(abierto); }
	
	@Override
	public int getMes () { return getEntity().getMes(); }
	@Override
	public void setMes (int mes) { getEntity().setMes(mes); }
	
	@Override
	public int getAno () { return getEntity().getAno(); }
	@Override
	public void setAno (int ano) { getEntity().setAno(ano); }
	
	@Override
	public String getDescripcion () { return getEntity().getDescripcion(); }
	@Override
	public void setDescripcion (String descripcion) { getEntity().setDescripcion(descripcion); }
	
	@Override
	public IRTitulacion getIRTitulacion () { return IRFactory.newIRTitulacion(getEntity().getITitulacion()); }
	@Override
	public void setIRTitulacion (IRTitulacion iRTitulacion) { getEntity().setITitulacion(iRTitulacion.getEntity()); }
	
	// Data
	
	@Override
	public JSONObject getData () {
		
		JSONObject data = new JSONObject();
		
		data.put("id", getId());
		data.put("mes", getMes());
		data.put("ano", getAno());
		data.put("descripcion", org.json.simple.JSONObject.escape(getDescripcion()));
		data.put("visible", isVisible());
		data.put("abierto", isAbierto());
		
		return data;
		
	}
	
	@Override
	public JSONObject getFullData () {

		JSONObject data = getData();
		
		data.put("titulacion", getIRTitulacion().getData());
		
		return data;
		
	}
	
	// Comparable
	
	@SuppressWarnings("rawtypes")
	@Override
	public int compareTo (IRComplexSet iRComplexSet) {

		int anoComparison = -1 * Integer.compare(getAno(), iRComplexSet.getAno());
		int mesComparison = -1 * Integer.compare(getMes(), iRComplexSet.getMes());
		
		if (anoComparison != 0) return anoComparison;
		else return mesComparison;
		
	}
	
	// Filters
	
	//  Profesor

	@Override
	public List<IRProfesor> getFilterIRProfesor () {
		
		return getIRProfesores();
		
	}
	
	// Titulacion
	
	@Override
	public List<IRTitulacion> getFilterIRTitulacion () {
		
		return Arrays.asList(getIRTitulacion());
		
	}
	
	// Categoria
	
	@Override
	public List<IRCategoria> getFilterIRCategoria () {
		
		return getIRCategorias();
		
	}

}
