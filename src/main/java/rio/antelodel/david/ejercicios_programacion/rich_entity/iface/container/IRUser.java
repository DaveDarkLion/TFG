package rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container;

import java.util.List;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRUserGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

public interface IRUser extends IParseable, Comparable<IRUser>, IFilterIRUserGetter {

	public IRPersona getIRPersona ();
	
	public List<String> getRoles();
	
	public String getEmail ();
	
	public String getNombre ();
	
	public String getApellido1 ();
	
	public String getApellido2 ();
	
	public String getPassword ();
	
}
