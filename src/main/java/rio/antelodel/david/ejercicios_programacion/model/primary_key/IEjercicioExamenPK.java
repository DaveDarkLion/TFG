package rio.antelodel.david.ejercicios_programacion.model.primary_key;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;

public class IEjercicioExamenPK implements Serializable {

	private static final long serialVersionUID = -3448172189188047175L;

	private IEjercicio iEjercicio;
	private IExamen iExamen;

	public IEjercicioExamenPK () { }
	
	public IEjercicioExamenPK (IEjercicio iEjercicio, IExamen iExamen) {
		
		super();
		
		this.iEjercicio = iEjercicio;
		this.iExamen = iExamen;
		
	}

	public IEjercicio getIEjercicio() { return iEjercicio; }

	public IExamen getIExamen () { return iExamen; }
	
}
