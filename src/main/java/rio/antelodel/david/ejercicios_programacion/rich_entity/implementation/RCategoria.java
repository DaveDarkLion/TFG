package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARichEntity;

public class RCategoria extends ARichEntity < ICategoria > implements IRCategoria {
	
	// Constructors
	
	public RCategoria (ICategoria entity) {
		
		super(entity);
		
	}
	
	public RCategoria (String nombre) {
	
		super(MFactory.newICategoria(nombre));
		
	}
	
	// Entity Functions
	
	@Override
	public int getId () { return getEntity().getId(); }
	
	@Override
	public String getNombre () { return getEntity().getNombre(); }
	@Override
	public void setNombre (String nombre) { getEntity().setNombre(nombre); }
	
	@Override
	public boolean hasIREjercicios () { return getEntity().getIEjercicios().isEmpty(); }

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
	public int compareTo (IRCategoria iRCategoria) {
		
		return getNombre().toLowerCase().compareTo(iRCategoria.getNombre().toLowerCase());
		
	}

	// Filters
	
	// RCategoria
	
	@Override
	public List<IRCategoria> getFilterIRCategoria() {

		return Arrays.asList(this);
		
	}
	
}
