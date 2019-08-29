package rio.antelodel.david.ejercicios_programacion.controller.utility.user_parser;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

public interface IUserParser {

	public List<Pair<IRPersona, String []>> parsePersonaList (String unparsedText) throws Exception;
	
}
