package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAdministrador;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAdministrador;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARichEntity;

public class RAdministrador extends ARichEntity <IAdministrador> implements IRAdministrador {

	// Constructors
	
	public RAdministrador (IAdministrador entity) {
		
		super(entity);
		
	}
	
	public RAdministrador (IRPersona iRPersona) {
		
		super(MFactory.newIAdministrador(iRPersona.getEntity()));
		
	}
	
	// Entity Functions
	
	@Override
	public IRPersona getIRPersona () { return IRFactory.newIRPersona(getEntity().getIPersona()); }

	@Override
	public List<String> getRoles () { return getIRPersona().getRoles(); }
	@Override
	public String getEmail () { return getIRPersona().getEmail(); }
	@Override
	public String getNombre () { return getIRPersona().getNombre(); }
	@Override
	public String getApellido1 () { return getIRPersona().getApellido1(); }
	@Override
	public String getApellido2 () { return getIRPersona().getApellido2(); }
	@Override
	public String getPassword () { return getIRPersona().getPassword(); }
	
	// Data

	@Override
	public JSONObject getData () {

		JSONObject data = new JSONObject();
		
		data.put("persona", getIRPersona().getFullData());
		
		return data;
	
	}

	@Override
	public JSONObject getFullData () {

		return getData();
		
	}
	
	// Comparable
	
	@Override
	public int compareTo (IRUser iRUser) {
		
		return getIRPersona().compareTo(iRUser.getIRPersona());
		
	}
	
	// Filters
	
	// RUser
	
	@Override
	public List<IRUser> getFilterIRUser () {
		
		return Arrays.asList(this);
		
	}
	
}
