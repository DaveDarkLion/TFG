package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARichEntity;

public class RDificultad extends ARichEntity <IDificultad> implements IRDificultad {
	
	// Constructors
	
	public RDificultad () { }
	
	public RDificultad (IDificultad entity) {
		
		super(entity);
		
	}
	
	public RDificultad (String nombre, float valor) {
	
		super(MFactory.newIDificultad(nombre, valor));
		
	}
	
	// Entity Functions
	
	@Override
	public int getId () { return getEntity().getId(); }
	
	@Override
	public String getNombre () { return getEntity().getNombre(); }
	@Override
	public void setNombre (String nombre) { getEntity().setNombre(nombre); }
	
	@Override
	public float getValor () { return getEntity().getValor(); }
	@Override
	public void setValor (float valor) { getEntity().setValor(valor); }
	
	@Override
	public boolean hasIREjercicios () { return getEntity().getIEjercicios().isEmpty(); }

	// Data

	@Override
	public JSONObject getData () {

		JSONObject data = new JSONObject();
		
		data.put("id", getId());
		data.put("nombre", org.json.simple.JSONObject.escape(getNombre()));
		data.put("valor", getValor());
		
		return data;
	
	}

	@Override
	public JSONObject getFullData () {
		
		return getData();
		
	}
	
	// Comparable
	
	@Override
	public int compareTo (IRDificultad iRDificultad) {
		
		return Float.compare(getValor(), iRDificultad.getValor());
		
	}

	// Filters
	
	// RDificultad
	
	@Override
	public List<IRDificultad> getFilterIRDificultad () {

		return Arrays.asList(this);

	}
		
}
