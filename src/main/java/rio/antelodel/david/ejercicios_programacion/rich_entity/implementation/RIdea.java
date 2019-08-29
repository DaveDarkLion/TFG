package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARichEntity;

public class RIdea extends ARichEntity <IIdea> implements IRIdea {

	// Constructors
	
	public RIdea (IIdea entity) {
		
		super(entity);
		
	}
	
	public RIdea (String nombre, String ideaText, IRProfesor iRProfesor) {
	
		super(MFactory.newIIdea(nombre, ideaText, iRProfesor.getEntity()));
		
	}
	
	// Entity Functions
	
	@Override
	public int getId () { return getEntity().getId(); }
	
	@Override
	public String getNombre () { return getEntity().getNombre(); }
	@Override
	public void setNombre (String nombre) { getEntity().setNombre(nombre); }
	
	@Override
	public String getIdeaText () { return getEntity().getIdeaText(); }
	@Override
	public void setIdeaText (String ideaText) { getEntity().setIdeaText(ideaText); }
	
	@Override
	public IRProfesor getIRProfesor () { return IRFactory.newIRProfesor(getEntity().getIProfesor()); }

	// Data
	
	@Override
	public JSONObject getData () {
		
		JSONObject data = new JSONObject();
		
		data.put("id", getId());
		data.put("nombre", org.json.simple.JSONObject.escape(getNombre()));
		data.put("ideaText", org.json.simple.JSONObject.escape(getIdeaText()));
		
		return data;
		
	}
	
	@Override
	public JSONObject getFullData () {

		JSONObject data = getData();
		
		data.put("profesor", getIRProfesor().getFullData());
		
		return data;
		
	}
		
	// Comparable
	
	@Override
	public int compareTo (IRIdea rIdea) {
		
		return getNombre().compareTo(rIdea.getNombre());
		
	}
	
	// Filters
	
	// RIdea
	
	@Override
	public List<IRIdea> getFilterIRIdea() {
		
		return Arrays.asList(this);
		
	}
	
	// RProfesor

	@Override
	public List<IRProfesor> getFilterIRProfesor() {
		
		return Arrays.asList(getIRProfesor());
		
	}
	
}
