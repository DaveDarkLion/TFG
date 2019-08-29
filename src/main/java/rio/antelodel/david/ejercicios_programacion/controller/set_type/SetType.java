package rio.antelodel.david.ejercicios_programacion.controller.set_type;

import java.util.Objects;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;

public class SetType implements IParseable, Comparable<SetType> {

	public enum Type { ALL, CART, EXAMEN, PRACTICA, PRACTICA_EVALUABLE }
	
	private final Type type;
	private final int id;
	private final String name;
	private final String nameLink;
	private final boolean generateDocument;
	private final boolean generateSet;
	
	public SetType (Type type, int id, String name, String nameLink, boolean canGenerateDocument, boolean canGenerateSet) {
		
		this.type = type;
		this.id = id;
		this.name = name;
		this.nameLink = nameLink;
		this.generateDocument = canGenerateDocument;
		this.generateSet = canGenerateSet;
		
	}

	public Type getType () { return type; }
	
	public int getId () { return id; }

	public String getName () { return name; }

	public String getNameLink() { return nameLink; }

	public boolean canGenerateDocument () { return generateDocument; }

	public boolean canGenerateSet () { return generateSet; }

	// Data
	
	public JSONObject getData () {
		
		JSONObject data = new JSONObject();
		
		data.put("type", getType().name());
		data.put("id", getId());
		data.put("name", getName());
		data.put("nameLink", getNameLink());
		data.put("generateDocument", canGenerateDocument());
		data.put("generateSet", canGenerateSet());
		
		return data;
		
	}
	
	public JSONObject getFullData () {
		
		return getData();
		
	}
	
	// Equals
	
	@Override
	public boolean equals (Object o) {
		
		if (o instanceof SetType) {
		
			SetType setType = (SetType)o;
			return (getType() == setType.getType());
			
		}
		
		else return false;
		
	}
	
	// HashCode
	
	@Override
	public int hashCode () {
		
		return Objects.hash(getId());
		
	}
	
	// Comparable
	
	public int compareTo (SetType setType) {
		
		return Integer.compare(getId(), setType.getId());
		
	}
	
}
