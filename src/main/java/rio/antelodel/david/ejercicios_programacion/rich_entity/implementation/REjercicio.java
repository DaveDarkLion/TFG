package rio.antelodel.david.ejercicios_programacion.rich_entity.implementation;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.parseable.IParseableHelper.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import rio.antelodel.david.ejercicios_programacion.controller.view_boolean.ViewBoolean;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IREjercicioComplexSet;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.container.ARichEntity;

public class REjercicio extends ARichEntity <IEjercicio> implements IREjercicio {

	// Constructors
	
	public REjercicio (IEjercicio entity) {
		
		super(entity);
		
	}
	
	public REjercicio (String titulo, String enunciado, IRProfesor iRProfesor, IRDificultad iRDificultad, boolean visible) {
		
		super(MFactory.newIEjercicio(titulo, enunciado, iRProfesor.getEntity(), iRDificultad.getEntity(), visible));
		
	}
	
	// Entity Functions
	
	@Override
	public int getId () { return getEntity().getId(); }
	
	@Override
	public boolean isVisible () { return getEntity().isVisible(); }
	@Override
	public void setVisible (boolean visible) {  getEntity().setVisible(visible); }
	
	@Override
	public boolean isVisibleForUnprivileged () {
		
		return (isVisible()
				|| isVisibleForUnprivilegedInSet(getIREjercicioExamenes())
				|| isVisibleForUnprivilegedInSet(getIREjercicioPracticas())
				|| isVisibleForUnprivilegedInSet(getIREjercicioPracticasEvaluacion())
				);
		
	}
	
	@Override
	public String getEnunciado () { return getEntity().getEnunciado(); }
	@Override
	public void setEnunciado (String enunciado) { getEntity().setEnunciado(enunciado); }
	
	@Override
	public String getTitulo () { return getEntity().getTitulo(); }
	@Override
	public void setTitulo (String titulo) { getEntity().setTitulo(titulo); }
	
	@Override
	public List<IRCategoria> getIRCategorias () { return IRFactory.getIRCategoriaList(new ArrayList<>(getEntity().getICategorias())); }
	@Override
	public void setIRCategorias (Set<IRCategoria> iRCategoriasList) {
		
		Set <ICategoria> categoriasList = new HashSet<>();
		for (IRCategoria iRC : iRCategoriasList) categoriasList.add(iRC.getEntity());
		
		getEntity().setICategorias(categoriasList);
		
	}
	
	@Override
	public IRDificultad getIRDificultad () { return IRFactory.newIRDificultad(getEntity().getIDificultad()); }
	@Override
	public void setIRDificultad (IRDificultad iRDificultad) { getEntity().setIDificultad(iRDificultad.getEntity()); }
	
	@Override
	public IRProfesor getIRProfesor () { return IRFactory.newIRProfesor(getEntity().getIProfesor()); }
	@Override
	public void setIRProfesor (IRProfesor iRProfesor) { getEntity().setIProfesor(iRProfesor.getEntity()); }
	
	@Override
	public List<IRDificultadAlumnoEjercicio> getIRDificultadAlumnoEjercicio () { return IRFactory.getIRDificultadAlumnoEjercicioList(new ArrayList<>(getEntity().getIDificultadAlumnoEjercicio())); }
	
	@Override
	public List<IRArchivo> getIRArchivosEntrada () { return IRFactory.getIRArchivoList(new ArrayList<>(getEntity().getIArchivosEntrada())); }
	@Override
	public void setIRArchivosEntrada (Set<IRArchivo> iRArchivosEntrada) { getEntity().setIArchivosEntrada(new HashSet<>(MFactory.getIArchivoList(new ArrayList<>(iRArchivosEntrada)))); }
	
	@Override
	public List<IRArchivo> getIRArchivosValidacion () { return IRFactory.getIRArchivoList(new ArrayList<>(getEntity().getIArchivosValidacion())); }
	@Override
	public void setIRArchivosValidacion (Set<IRArchivo> iRArchivosValidacion) { getEntity().setIArchivosValidacion(new HashSet<>(MFactory.getIArchivoList(new ArrayList<>(iRArchivosValidacion)))); }
	
	@Override
	public List<IRArchivo> getIRArchivosSolucion () { return IRFactory.getIRArchivoList(new ArrayList<>(getEntity().getIArchivosSolucion())); }
	@Override
	public void setIRArchivosSolucion (Set<IRArchivo> iRArchivosSolucion) { getEntity().setIArchivosSolucion(new HashSet<>(MFactory.getIArchivoList(new ArrayList<>(iRArchivosSolucion)))); }
	
	@Override
	public List<IREjercicioExamen> getIREjercicioExamenes () { return IRFactory.getIREjercicioExamenList(new ArrayList<>(getEntity().getIEjercicioExamenes())); }	
	@Override
	public List<IREjercicioPractica> getIREjercicioPracticas () { return IRFactory.getIREjercicioPracticaList(new ArrayList<>(getEntity().getIEjercicioPracticas())); }
	@Override
	public List<IREjercicioPracticaEvaluacion> getIREjercicioPracticasEvaluacion () { return IRFactory.getIREjercicioPracticaEvaluacionList(new ArrayList<>(getEntity().getIEjercicioPracticasEvaluacion())); }
	
	// Local functions
	
	protected boolean isVisibleForUnprivilegedInSet (List< ? extends IREjercicioComplexSet<?, ?>> iREjercicioComplexSetList) {
		
		for (IREjercicioComplexSet<?, ?> iREjercicioComplexSet : iREjercicioComplexSetList)
			if (iREjercicioComplexSet.getIRComplexSet().isVisible()) return true;
		
		return false;
		
	}
	
	// Data
	
	@Override
	public JSONObject getData () {
		
		JSONObject data = new JSONObject();
		
		data.put("id", getId());
		data.put("titulo", org.json.simple.JSONObject.escape(getTitulo()));
		data.put("enunciado", org.json.simple.JSONObject.escape(getEnunciado()));
		data.put("visible", isVisible());
		
		return data;
		
	}
	
	@Override
	public JSONObject getFullData () {

		JSONObject data = getData();
		
		data.put("profesor", getIRProfesor().getFullData());
		data.put("dificultad", getIRDificultad().getData());
		data.put("archivosEntrada", getGenericListSortedData(getIRArchivosEntrada()));
		data.put("archivosValidacion", getGenericListSortedData(getIRArchivosValidacion()));
		data.put("archivosSolucion", getGenericListSortedData(getIRArchivosSolucion()));
		data.put("visibleForUnprivileged", ViewBoolean.toViewBoolean(isVisibleForUnprivileged()));
		
		return data;
		
	}
	
	// Comparable
	
	@Override
	public int compareTo (IREjercicio iREjercicio) {
		
		return getEntity().getEnunciado().toLowerCase().compareTo(iREjercicio.getEntity().getEnunciado().toLowerCase());
		
	}
	
	// Filters
	
	// RCategoria

	@Override
	public List<IRCategoria> getFilterIRCategoria() {
		
		return getIRCategorias();
		
	}
	
	// RDificultad
	
	@Override
	public List<IRDificultad> getFilterIRDificultad() {
		
		return Arrays.asList(getIRDificultad());
		
	}
	
	// REjercicio
	
	@Override
	public List<IREjercicio> getFilterIREjercicio() {

		return Arrays.asList(this);
		
	}
	
	// RExamen
	
	@Override
	public List<IRExamen> getFilterIRExamen() {
		
		List<IExamen> iExamenesList = new ArrayList<>();
		
		for (IEjercicioExamen iEE : getEntity().getIEjercicioExamenes())
			iExamenesList.add(iEE.getIExamen());
		
		return IRFactory.getIRExamenList(iExamenesList);
		
	}
	
	// RPractica
	
	@Override
	public List<IRPractica> getFilterIRPractica() {
		
		List<IPractica> iPracticasList = new ArrayList<>();
		
		for (IEjercicioPractica iEP : getEntity().getIEjercicioPracticas())
			iPracticasList.add(iEP.getIPractica());
		
		return IRFactory.getIRPracticaList(iPracticasList);
		
	}

	// RPracticaEvaluacion
	
	@Override
	public List<IRPracticaEvaluacion> getFilterIRPracticaEvaluacion() {

		List<IPracticaEvaluacion> iPracticasEvaluacionList = new ArrayList<>();
		
		for (IEjercicioPracticaEvaluacion iEPE : getEntity().getIEjercicioPracticasEvaluacion())
			iPracticasEvaluacionList.add(iEPE.getIPracticaEvaluacion());
		
		return IRFactory.getIRPracticaEvaluacionList(iPracticasEvaluacionList);
		
	}

	// RProfesor
	
	@Override
	public List<IRProfesor> getFilterIRProfesor() {
		
		return new ArrayList<>(Arrays.asList(getIRProfesor()));
		
	}

}
