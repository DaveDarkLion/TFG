package rio.antelodel.david.ejercicios_programacion.model.primary_key;

import java.io.Serializable;

import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;

public class IEjercicioPracticaEvaluacionPK implements Serializable {

	private static final long serialVersionUID = -3448172189188047175L;

	private IEjercicio iEjercicio;
	private IPracticaEvaluacion iPracticaEvaluacion;

	public IEjercicioPracticaEvaluacionPK () { }
	
	public IEjercicioPracticaEvaluacionPK (IEjercicio iEjercicio, IPracticaEvaluacion iPracticaEvaluacion) {
		
		super();
		
		this.iEjercicio = iEjercicio;
		this.iPracticaEvaluacion = iPracticaEvaluacion;
		
	}

	public IEjercicio getIEjercicio() { return iEjercicio; }

	public IPracticaEvaluacion getIPracticaEvaluacion () { return iPracticaEvaluacion; }
	
}
