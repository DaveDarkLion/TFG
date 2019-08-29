package rio.antelodel.david.ejercicios_programacion.model.iface;

import java.io.Serializable;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.model.iface.container.IEntity;

public interface IEjercicio extends IEntity, Serializable {

	public int getId ();
	public void setId (int id);

	public String getTitulo ();
	public void setTitulo (String titulo);
	
	public String getEnunciado ();
	public void setEnunciado (String enunciado);
	
	public IProfesor getIProfesor ();
	public void setIProfesor (IProfesor iProfesor);

	public Set<ICategoria> getICategorias ();
	public void setICategorias (Set<ICategoria> iCategorias);

	public IDificultad getIDificultad ();
	public void setIDificultad (IDificultad iDificultad);

	public boolean isVisible ();
	public void setVisible (boolean visible);
	
	public Set<IArchivo> getIArchivosEntrada ();
	public void setIArchivosEntrada (Set<IArchivo> iArchivosEntrada);

	public Set<IArchivo> getIArchivosValidacion ();
	public void setIArchivosValidacion (Set<IArchivo> iArchivosValidacion);

	public Set<IArchivo> getIArchivosSolucion ();
	public void setIArchivosSolucion (Set<IArchivo> iArchivosSolucion);
	
	public Set<IEjercicioExamen> getIEjercicioExamenes ();
	public void setIEjercicioExamenes (Set<IEjercicioExamen> iEjercicioExamenes);
	
	public Set<IEjercicioPractica> getIEjercicioPracticas ();
	public void setIEjercicioPracticas (Set<IEjercicioPractica> iEjercicioPracticas);
	
	public Set<IEjercicioPracticaEvaluacion> getIEjercicioPracticasEvaluacion ();
	public void setIEjercicioPracticasEvaluacion (Set<IEjercicioPracticaEvaluacion> iEjercicioPracticasEvaluacion);

	public Set<IDificultadAlumnoEjercicio> getIDificultadAlumnoEjercicio ();
	public void setIDificultadAlumnoEjercicio (Set<IDificultadAlumnoEjercicio> iDificultadAlumnoEjercicio);

	public Set<IPersona> getIPersonas ();
	public void setIPersonas (Set<IPersona> iPersonas);
	
}
