package rio.antelodel.david.ejercicios_programacion.model.factory;

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
import rio.antelodel.david.ejercicios_programacion.model.implementation.Administrador;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Alumno;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Archivo;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Categoria;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Dificultad;
import rio.antelodel.david.ejercicios_programacion.model.implementation.DificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Ejercicio;
import rio.antelodel.david.ejercicios_programacion.model.implementation.EjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.implementation.EjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.implementation.EjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.implementation.EjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Examen;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Idea;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Persona;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Practica;
import rio.antelodel.david.ejercicios_programacion.model.implementation.PracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Profesor;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Titulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;

public class MFactory {
	
	// Private Constructor
	
	private MFactory () { }
	
	// MFactory
	
	// Administrador
	
	public static IAdministrador newIAdministrador (IPersona iPersona) { return new Administrador(iPersona); }
	
	// Alumno
	
	public static IAlumno newIAlumno (IPersona iPersona) { return new Alumno(iPersona); }
	
	// Archivo
	
	public static IArchivo newIArchivo (String ruta) { return new Archivo(ruta); }
	
	public static List<IArchivo> getIArchivoList (List<IRArchivo> iRArchivoList) {
		
		List<IArchivo> iArchivoList = new ArrayList<>();
		
		for (IRArchivo e : iRArchivoList) iArchivoList.add(e.getEntity());
		
		return iArchivoList;
		
	}
	
	// Categoria
	
	public static ICategoria newICategoria (String nombre) { return new Categoria(nombre); }
	
	// Dificultad
	
	public static IDificultad newIDificultad (String nombre, float valor) { return new Dificultad(nombre, valor); }
	
	// DificultadAlumnoEjercicio
	
	public static IDificultadAlumnoEjercicio newIDificultadAlumnoEjercicio (IAlumno iAlumno, IEjercicio iEjercicio, IDificultad iDificultad) {
		return new DificultadAlumnoEjercicio(iAlumno, iEjercicio, iDificultad);
	}
	
	// Ejercicio
	
	public static IEjercicio newIEjercicio (String titulo, String enunciado, IProfesor iProfesor, IDificultad iDificultad, boolean visible) {
		return new Ejercicio(titulo, enunciado, iProfesor, iDificultad, visible);
	}
	
	// EjercicioExamen
	
	public static IEjercicioExamen newIEjercicioExamen (IEjercicio iEjercicio, IExamen iExamen, int position) {
		return new EjercicioExamen(iEjercicio, iExamen, position);
	}
	
	// EjercicioPersona
	
	public static IEjercicioPersona newIEjercicioPersona (IEjercicio iEjercicio, IPersona iPersona, int position) {
		return new EjercicioPersona(iEjercicio, iPersona, position);
	}
	
	// EjercicioPractica
	
	public static IEjercicioPractica newIEjercicioPractica (IEjercicio iEjercicio, IPractica iPractica, int position) {
		return new EjercicioPractica(iEjercicio, iPractica, position);
	}
	
	// EjercicioPracticaEvaluacion
	
	public static IEjercicioPracticaEvaluacion newIEjercicioPracticaEvaluacion (IEjercicio iEjercicio, IPracticaEvaluacion iPracticaEvaluacion, int position) {
		return new EjercicioPracticaEvaluacion(iEjercicio, iPracticaEvaluacion, position);
	}
	
	// Examen
	
	public static IExamen newIExamen (int mes, int ano, String descripcion, ITitulacion iTitulacion, boolean visible, boolean abierto) {
		return new Examen(mes, ano, descripcion, iTitulacion, visible, abierto);
	}
	
	// Idea
	
	public static IIdea newIIdea (String nombre, String ideaText, IProfesor iProfesor) { return new Idea(nombre, ideaText, iProfesor); }
	
	// Persona
	
	public static IPersona newIPersona (String email, String nombre, String apellido1, String apellido2, String password) {
		return new Persona(email, nombre, apellido1, apellido2, password);
	}
	
	// Practica
	
	public static IPractica newIPractica (int mes, int ano, String descripcion, ITitulacion iTitulacion, boolean visible, boolean abierto) {
		return new Practica(mes, ano, descripcion, iTitulacion, visible, abierto);
	}
	
	// PracticaEvaluacion
	
	public static IPracticaEvaluacion newIPracticaEvaluacion (int mes, int ano, String descripcion, ITitulacion iTitulacion, boolean visible, boolean abierto) {
		return new PracticaEvaluacion(mes, ano, descripcion, iTitulacion, visible, abierto);
	}
	
	// Profesor
	
	public static IProfesor newIProfesor (IPersona iPersona) { return new Profesor(iPersona); }
	
	// Titulacion
	
	public static ITitulacion newITitulacion (String nombre) { return new Titulacion(nombre); }
	
}
