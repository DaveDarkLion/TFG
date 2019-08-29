package rio.antelodel.david.ejercicios_programacion.model.primary_key;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;

public class IEjercicioPracticaPK implements Serializable {

	private static final long serialVersionUID = -3448172189188047175L;

	private IEjercicio iEjercicio;
	private IPractica iPractica;

	public IEjercicioPracticaPK () { }
	
	public IEjercicioPracticaPK (IEjercicio iEjercicio, IPractica iPractica) {
		
		super();
		
		this.iEjercicio = iEjercicio;
		this.iPractica = iPractica;
		
	}

	public IEjercicio getIEjercicio() { return iEjercicio; }

	public IPractica getIPractica () { return iPractica; }
	
}
