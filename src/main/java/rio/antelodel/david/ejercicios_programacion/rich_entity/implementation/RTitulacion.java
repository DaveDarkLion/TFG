package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARichEntity;

public class RTitulacion extends ARichEntity < ITitulacion > implements IRTitulacion {

	// Constructors
	
	public RTitulacion (ITitulacion entity) {
		
		super(entity);
		
	}
	
	public RTitulacion (String nombre) {
	
		super(MFactory.newITitulacion(nombre));
		
	}
	
	public boolean hasRSet () { return getEntity().getIExamenes().isEmpty() && getEntity().getIPracticas().isEmpty() && getEntity().getIPracticasEvaluacion().isEmpty(); }
	
	// Entity Functions
	
	@Override
	public int getId () { return getEntity().getId(); }
	
	@Override
	public String getNombre () { return getEntity().getNombre(); }
	@Override
	public void setNombre (String nombre) { getEntity().setNombre(nombre); }
	
	@Override
	public List <IRExamen> getIRExamenes () { return IRFactory.getIRExamenList(new ArrayList<>(getEntity().getIExamenes())); }
	
	@Override
	public List <IRPractica> getIRPracticas () { return IRFactory.getIRPracticaList(new ArrayList<>(getEntity().getIPracticas())); }
	
	@Override
	public List <IRPracticaEvaluacion> getIRPracticasEvaluacion () { return IRFactory.getIRPracticaEvaluacionList(new ArrayList<>(getEntity().getIPracticasEvaluacion())); }

	@Override
	public boolean hasIRSets () { return getIRExamenes().isEmpty() && getIRPracticas().isEmpty() && getIRPracticasEvaluacion().isEmpty(); }
	
	// Data

	@Override
	public JSONObject getData () {

		JSONObject data = new JSONObject();
		
		data.put("id", getId());
		data.put("nombre", org.json.simple.JSONObject.escape(getNombre()));
		
		return data;
	
	}

	@Override
	public JSONObject getFullData () {
		
		return getData();
		
	}
	
	// Comparable
	
	@Override
	public int compareTo (IRTitulacion iRTitulacion) {
		
		return getNombre().compareToIgnoreCase(iRTitulacion.getNombre());
		
	}
	
	// Filters
	
	// RTitulacion

	@Override
	public List<IRTitulacion> getFilterIRTitulacion () {
		
		return Arrays.asList(this);
		
	}
	
}
