package rio.antelodel.david.ejercicios_programacion.controller.utility.user_parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@Service
public class UserParserJSON implements IUserParser {
	
	// Constants
	
	private static final String TEMP_PASSWORD = "";
	
	// Parse Persona List
	
	@Override
	public List<Pair<IRPersona, String []>> parsePersonaList (String unparsedText) throws Exception {
		
		List<Pair<IRPersona, String []>> iRPersonaRolesList = new ArrayList<>();
	
		JSONArray data = new JSONArray(unparsedText);
		
		for (int i = 0; i < data.length(); i++) {
			
			JSONObject current = data.getJSONObject(i);
			
			// Create Persona
			
			IRPersona iRPersona = IRFactory.newIRPersona(	current.getString("email"),
															current.getString("nombre"),
															current.getString("apellido1"),
															current.getString("apellido2"),
															TEMP_PASSWORD);
			
			// Get Roles
			
			String [] rolesId = getRolesId(current.getJSONArray("roles_id"));
			
			iRPersonaRolesList.add(new Pair<>(iRPersona, rolesId));
			
		}
		
		return iRPersonaRolesList;
		
	}
	
	private String [] getRolesId (JSONArray data) {
		
		String [] rolesId = new String [data.length()];
		
		for (int i = 0; i < data.length(); i++) {
			
			rolesId[i] = data.getString(i);
			
		}
		
		return rolesId;
		
	}
	
}
