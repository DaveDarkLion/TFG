package rio.antelodel.david.ejercicios_programacion.model.primary_key;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;

public class IEjercicioPersonaPK implements Serializable {
	
	private static final long serialVersionUID = -3080167166723937608L;
	
	private IEjercicio iEjercicio;
	private IPersona iPersona;

	public IEjercicioPersonaPK () { }
	
	public IEjercicioPersonaPK (IEjercicio iEjercicio, IPersona iPersona) {
		
		super();
		
		this.iEjercicio = iEjercicio;
		this.iPersona = iPersona;
		
	}

	public IEjercicio getIEjercicio() { return iEjercicio; }

	public IPersona getIPersona () { return iPersona; }
	
}
