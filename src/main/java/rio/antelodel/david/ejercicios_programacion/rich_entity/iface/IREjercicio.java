package rio.antelodel.david.ejercicios_programacion.rich_entity.iface;

import java.util.List;
import java.util.Set;

import rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseable;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRCategoriaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRDificultadGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIREjercicioGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRExamenGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRPracticaEvaluacionGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRPracticaGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.getter.IFilterIRProfesorGetter;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRichEntity;

public interface IREjercicio extends IRichEntity <IEjercicio>, IParseable, Comparable<IREjercicio>, IFilterIRCategoriaGetter, IFilterIRDificultadGetter, IFilterIREjercicioGetter, IFilterIRExamenGetter, IFilterIRPracticaGetter, IFilterIRPracticaEvaluacionGetter, IFilterIRProfesorGetter {

	public int getId ();
	
	public boolean isVisible ();
	public void setVisible (boolean visible);
	
	public boolean isVisibleForUnprivileged ();
	
	public String getTitulo ();
	public void setTitulo (String titulo);
	
	public String getEnunciado ();
	public void setEnunciado (String enunciado);
	
	public List<IRCategoria> getIRCategorias ();
	public void setIRCategorias (Set<IRCategoria> iRCategoriasList);
	
	public IRDificultad getIRDificultad ();
	public void setIRDificultad (IRDificultad iRDificultad);
	
	public IRProfesor getIRProfesor ();
	public void setIRProfesor (IRProfesor iRProfesor);
	
	public List<IRDificultadAlumnoEjercicio> getIRDificultadAlumnoEjercicio ();
	
	public List<IRArchivo> getIRArchivosEntrada ();
	public void setIRArchivosEntrada (Set<IRArchivo> iRArchivosEntrada);
	
	public List<IRArchivo> getIRArchivosValidacion ();
	public void setIRArchivosValidacion (Set<IRArchivo> iRArchivosValidacion);
	
	public List<IRArchivo> getIRArchivosSolucion ();
	public void setIRArchivosSolucion (Set<IRArchivo> iRArchivosSolucion);
	
	public List<IREjercicioExamen> getIREjercicioExamenes ();
	public List<IREjercicioPractica> getIREjercicioPracticas ();
	public List<IREjercicioPracticaEvaluacion> getIREjercicioPracticasEvaluacion ();
	
}
