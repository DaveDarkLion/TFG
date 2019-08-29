package rio.antelodel.david.ejercicios_programacion.rich_entity.factory;

import java.util.ArrayList;
import java.util.List;

import rio.antelodel.david.ejercicios_programacion.model.iface.IAdministrador;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;
import rio.antelodel.david.ejercicios_programacion.model.iface.IArchivo;
import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IIdea;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAdministrador;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RAdministrador;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RArchivo;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RTitulacion;

public class IRFactory {
	
	// Private Constructor
	
	private IRFactory () { }
	
	// MFactory
	
	// Administrador
	
	public static IRAdministrador newIRAdministrador (IAdministrador iAdministrador) { return new RAdministrador(iAdministrador); }
	public static IRAdministrador newIRAdministrador (IRPersona iRPersona) { return new RAdministrador(iRPersona); }
	
	public static List<IRAdministrador> getIRAdministradorList (List<IAdministrador> iAdministradorList) {
		
		List<IRAdministrador> iRAdministradorList = new ArrayList<>();
		
		for (IAdministrador e : iAdministradorList) iRAdministradorList.add(newIRAdministrador(e));
		
		return iRAdministradorList;
		
	}
	
	// Alumno
	
	public static IRAlumno newIRAlumno (IAlumno iAlumno) { return new RAlumno(iAlumno); }
	public static IRAlumno newIRAlumno (IRPersona iRPersona) { return new RAlumno(iRPersona); }
	
	public static List<IRAlumno> getIRAlumnoList (List<IAlumno> iAlumnoList) {
		
		List<IRAlumno> iRAlumnoList = new ArrayList<>();
		
		for (IAlumno e : iAlumnoList) iRAlumnoList.add(newIRAlumno(e));
		
		return iRAlumnoList;
		
	}
	
	// Archivo
	
	public static IRArchivo newIRArchivo (IArchivo iArchivo) { return new RArchivo(iArchivo); }
	public static IRArchivo newIRArchivo (String ruta) { return new RArchivo(ruta); }
	
	public static List<IRArchivo> getIRArchivoList (List<IArchivo> iArchivoList) {
		
		List<IRArchivo> iRArchivoList = new ArrayList<>();
		
		for (IArchivo e : iArchivoList) iRArchivoList.add(newIRArchivo(e));
		
		return iRArchivoList;
		
	}
	
	// Categoria
	
	public static IRCategoria newIRCategoria (ICategoria iCategoria) { return new RCategoria(iCategoria); }
	public static IRCategoria newIRCategoria (String nombre) { return new RCategoria(nombre); }
	
	public static List<IRCategoria> getIRCategoriaList (List<ICategoria> iCategoriaList) {
		
		List<IRCategoria> iRCategoriaList = new ArrayList<>();
		
		for (ICategoria e : iCategoriaList) iRCategoriaList.add(newIRCategoria(e));
		
		return iRCategoriaList;
		
	}
	
	// Dificultad
	
	public static IRDificultad newIRDificultad (IDificultad iDificultad) { return new RDificultad(iDificultad); }
	public static IRDificultad newIRDificultad (String nombre, float valor) { return new RDificultad(nombre, valor); }
	
	public static List<IRDificultad> getIRDificultadList (List<IDificultad> iDificultadList) {
		
		List<IRDificultad> iRDificultadList = new ArrayList<>();
		
		for (IDificultad e : iDificultadList) iRDificultadList.add(newIRDificultad(e));
		
		return iRDificultadList;
		
	}
	
	// DificultadAlumnoEjercicio
	
	public static IRDificultadAlumnoEjercicio newIRDificultadAlumnoEjercicio (IDificultadAlumnoEjercicio iDificultadAlumnoEjercicio) {
		return new RDificultadAlumnoEjercicio(iDificultadAlumnoEjercicio);
		}
	public static IRDificultadAlumnoEjercicio newIRDificultadAlumnoEjercicio (IRAlumno iRAlumno, IREjercicio iREjercicio, IRDificultad iRDificultad) {
		return new RDificultadAlumnoEjercicio(iRAlumno, iREjercicio, iRDificultad);
	}
	
	public static List<IRDificultadAlumnoEjercicio> getIRDificultadAlumnoEjercicioList (List<IDificultadAlumnoEjercicio> iDificultadAlumnoEjercicioList) {
		
		List<IRDificultadAlumnoEjercicio> iRDificultadAlumnoEjercicioList = new ArrayList<>();
		
		for (IDificultadAlumnoEjercicio e : iDificultadAlumnoEjercicioList) iRDificultadAlumnoEjercicioList.add(newIRDificultadAlumnoEjercicio(e));
		
		return iRDificultadAlumnoEjercicioList;
		
	}
	
	// Ejercicio
	
	public static IREjercicio newIREjercicio (IEjercicio iEjercicio) { return new REjercicio(iEjercicio); }
	public static IREjercicio newIREjercicio (String titulo, String enunciado, IRProfesor iRProfesor, IRDificultad iRDificultad, boolean visible) {
		return new REjercicio(titulo, enunciado, iRProfesor, iRDificultad, visible);
	}
	
	public static List<IREjercicio> getIREjercicioList (List<IEjercicio> iEjercicioList) {
		
		List<IREjercicio> iREjercicioList = new ArrayList<>();
		
		for (IEjercicio e : iEjercicioList) iREjercicioList.add(newIREjercicio(e));
		
		return iREjercicioList;
		
	}
	
	// EjercicioExamen
	
	public static IREjercicioExamen newIREjercicioExamen (IEjercicioExamen iEjercicioExamen) { return new REjercicioExamen(iEjercicioExamen); }
	public static IREjercicioExamen newIREjercicioExamen (IREjercicio iREjercicio, IRExamen iRExamen, int position) {
		return new REjercicioExamen(iREjercicio, iRExamen, position);
	}
	
	public static List<IREjercicioExamen> getIREjercicioExamenList (List<IEjercicioExamen> iEjercicioExamenList) {
		
		List<IREjercicioExamen> iREjercicioExamenList = new ArrayList<>();
		
		for (IEjercicioExamen e : iEjercicioExamenList) iREjercicioExamenList.add(newIREjercicioExamen(e));
		
		return iREjercicioExamenList;
		
	}
	
	// EjercicioPersona
	
	public static IREjercicioPersona newIREjercicioPersona (IEjercicioPersona iEjercicioPersona) { return new REjercicioPersona(iEjercicioPersona); }
	public static IREjercicioPersona newIREjercicioPersona (IREjercicio iREjercicio, IRPersona iRPersona, int position) {
		return new REjercicioPersona(iREjercicio, iRPersona, position);
	}
	
	public static List<IREjercicioPersona> getIREjercicioPersonaList (List<IEjercicioPersona> iEjercicioPersonaList) {
		
		List<IREjercicioPersona> iREjercicioPersonaList = new ArrayList<>();
		
		for (IEjercicioPersona e : iEjercicioPersonaList) iREjercicioPersonaList.add(newIREjercicioPersona(e));
		
		return iREjercicioPersonaList;
		
	}
	
	// EjercicioPractica
	
	public static IREjercicioPractica newIREjercicioPractica (IEjercicioPractica iEjercicioPractica) { return new REjercicioPractica(iEjercicioPractica); }
	public static IREjercicioPractica newIREjercicioPractica (IREjercicio iREjercicio, IRPractica iRPractica, int position) {
		return new REjercicioPractica(iREjercicio, iRPractica, position);
	}
	
	public static List<IREjercicioPractica> getIREjercicioPracticaList (List<IEjercicioPractica> iEjercicioPracticaList) {
		
		List<IREjercicioPractica> iREjercicioPracticaList = new ArrayList<>();
		
		for (IEjercicioPractica e : iEjercicioPracticaList) iREjercicioPracticaList.add(newIREjercicioPractica(e));
		
		return iREjercicioPracticaList;
		
	}
	
	// EjercicioPracticaEvaluacion
	
	public static IREjercicioPracticaEvaluacion newIREjercicioPracticaEvaluacion (IEjercicioPracticaEvaluacion iEjercicioPracticaEvaluacion) {
		return new REjercicioPracticaEvaluacion(iEjercicioPracticaEvaluacion);
	}
	public static IREjercicioPracticaEvaluacion newIREjercicioPracticaEvaluacion (IREjercicio iREjercicio, IRPracticaEvaluacion iRPracticaEvaluacion, int position) {
		return new REjercicioPracticaEvaluacion(iREjercicio, iRPracticaEvaluacion, position);
	}
	
	public static List<IREjercicioPracticaEvaluacion> getIREjercicioPracticaEvaluacionList (List<IEjercicioPracticaEvaluacion> iEjercicioPracticaEvaluacionList) {
		
		List<IREjercicioPracticaEvaluacion> iREjercicioPracticaEvaluacionList = new ArrayList<>();
		
		for (IEjercicioPracticaEvaluacion e : iEjercicioPracticaEvaluacionList) iREjercicioPracticaEvaluacionList.add(newIREjercicioPracticaEvaluacion(e));
		
		return iREjercicioPracticaEvaluacionList;
		
	}
	
	// Examen
	
	public static IRExamen newIRExamen (IExamen iExamen) { return new RExamen(iExamen); }
	public static IRExamen newIRExamen (int mes, int ano, String descripcion, IRTitulacion iRTitulacion, boolean visible, boolean abierto) {
		return new RExamen(mes, ano, descripcion, iRTitulacion, visible, abierto);
	}
	
	public static List<IRExamen> getIRExamenList (List<IExamen> iExamenList) {
		
		List<IRExamen> iRExamenList = new ArrayList<>();
		
		for (IExamen e : iExamenList) iRExamenList.add(newIRExamen(e));
		
		return iRExamenList;
		
	}
	
	// Idea
	
	public static IRIdea newIRIdea (IIdea iIdea) { return new RIdea(iIdea); }
	public static IRIdea newIRIdea (String nombre, String ideaText, IRProfesor iRProfesor) { return new RIdea(nombre, ideaText, iRProfesor); }
	
	public static List<IRIdea> getIRIdeaList (List<IIdea> iIdeaList) {
		
		List<IRIdea> iRIdeaList = new ArrayList<>();
		
		for (IIdea e : iIdeaList) iRIdeaList.add(newIRIdea(e));
		
		return iRIdeaList;
		
	}
	
	// Persona
	
	public static IRPersona newIRPersona (IPersona iPersona) { return new RPersona(iPersona); }
	public static IRPersona newIRPersona (String email, String nombre, String apellido1, String apellido2, String password) {
		return new RPersona(email, nombre, apellido1, apellido2, password);
	}
	
	public static List<IRPersona> getIRPersonaList (List<IPersona> iPersonaList) {
		
		List<IRPersona> iRPersonaList = new ArrayList<>();
		
		for (IPersona e : iPersonaList) iRPersonaList.add(newIRPersona(e));
		
		return iRPersonaList;
		
	}
	
	// Practica
	
	public static IRPractica newIRPractica (IPractica iPractica) { return new RPractica(iPractica); }
	public static IRPractica newIRPractica (int mes, int ano, String descripcion, IRTitulacion iRTitulacion, boolean visible, boolean abierto) {
		return new RPractica(mes, ano, descripcion, iRTitulacion, visible, abierto);
	}
	
	public static List<IRPractica> getIRPracticaList (List<IPractica> iPracticaList) {
		
		List<IRPractica> iRPracticaList = new ArrayList<>();
		
		for (IPractica e : iPracticaList) iRPracticaList.add(newIRPractica(e));
		
		return iRPracticaList;
		
	}
	
	// PracticaEvaluacion
	
	public static IRPracticaEvaluacion newIRPracticaEvaluacion (IPracticaEvaluacion iPracticaEvaluacion) { return new RPracticaEvaluacion(iPracticaEvaluacion); }
	public static IRPracticaEvaluacion newIRPracticaEvaluacion (int mes, int ano, String descripcion, IRTitulacion iRTitulacion, boolean visible, boolean abierto) {
		return new RPracticaEvaluacion(mes, ano, descripcion, iRTitulacion, visible, abierto);
	}
	
	public static List<IRPracticaEvaluacion> getIRPracticaEvaluacionList (List<IPracticaEvaluacion> iPracticaEvaluacionList) {
		
		List<IRPracticaEvaluacion> iRPracticaEvaluacionList = new ArrayList<>();
		
		for (IPracticaEvaluacion e : iPracticaEvaluacionList) iRPracticaEvaluacionList.add(newIRPracticaEvaluacion(e));
		
		return iRPracticaEvaluacionList;
		
	}
	
	// Profesor
	
	public static IRProfesor newIRProfesor (IProfesor iProfesor) { return new RProfesor(iProfesor); }
	public static IRProfesor newIRProfesor (IRPersona iRPersona) { return new RProfesor(iRPersona); }
	
	public static List<IRProfesor> getIRProfesorList (List<IProfesor> iProfesorList) {
		
		List<IRProfesor> iRProfesorList = new ArrayList<>();
		
		for (IProfesor e : iProfesorList) iRProfesorList.add(newIRProfesor(e));
		
		return iRProfesorList;
		
	}
	
	// Titulacion
	
	public static IRTitulacion newIRTitulacion (ITitulacion iTitulacion) { return new RTitulacion(iTitulacion); }
	public static IRTitulacion newIRTitulacion (String nombre) { return new RTitulacion(nombre); }
	
	public static List<IRTitulacion> getIRTitulacionList (List<ITitulacion> iTitulacionList) {
		
		List<IRTitulacion> iRTitulacionList = new ArrayList<>();
		
		for (ITitulacion e : iTitulacionList) iRTitulacionList.add(newIRTitulacion(e));
		
		return iRTitulacionList;
		
	}
	
}
