package rio.antelodel.david.ejercicios_programacion;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAdministrador;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RAdministrador;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.REjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.implementation.RTitulacion;

public class TestDB {
	
	public static void main(String [] args) {
	
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// IRPersonas
		
		final String defaultPassword = "abcd1234";
		
		IRPersonaDAO pDAO = context.getBean(IRPersonaDAO.class);
		
		IRPersona p1 = new RPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", defaultPassword);
		IRPersona p2 = new RPersona("afuentes@terra.es", "Antonio", "Fuentes", "Torres", defaultPassword);
		
		IRPersona p3 = new RPersona("ecasado@gmail.com", "Eduardo", "Casado", "Perez", defaultPassword);
		
		IRPersona p4 = new RPersona("dantelo@gmail.com", "David", "Antelo", "del Rio", defaultPassword);
		IRPersona p5 = new RPersona("jramos@gmx.com", "Jorge", "Ramos", "Alvarez", defaultPassword);
		IRPersona p6 = new RPersona("admin@admin.admin", "Admin", "Admin", "Admin", defaultPassword);
		
		pDAO.save(p1);
		pDAO.save(p2);
		pDAO.save(p3);
		pDAO.save(p4);
		pDAO.save(p5);
		pDAO.save(p6);
		
		// IRAdministradores
		
		IRAdministradorDAO adDAO = context.getBean(IRAdministradorDAO.class);
		
		IRAdministrador ad1 = new RAdministrador(p1);
		IRAdministrador ad2 = new RAdministrador(p2);
		IRAdministrador ad3 = new RAdministrador(p6);
		
		adDAO.save(ad1);
		adDAO.save(ad2);
		adDAO.save(ad3);
		
		// IRProfesores
		
		IRProfesorDAO prDAO = context.getBean(IRProfesorDAO.class);
		
		IRProfesor pr1 = new RProfesor(p2);
		IRProfesor pr2 = new RProfesor(p3);
		IRProfesor pr3 = new RProfesor(p1);
		
		prDAO.save(pr1);
		prDAO.save(pr2);
		prDAO.save(pr3);
		
		// IRAlumnos
		
		IRAlumnoDAO aDAO = context.getBean(IRAlumnoDAO.class);
		
		IRAlumno a1 = new RAlumno(p4);
		IRAlumno a2 = new RAlumno(p5);
		
		aDAO.save(a1);
		aDAO.save(a2);
		
		// IRCategorias
		
		IRCategoriaDAO cDAO = context.getBean(IRCategoriaDAO.class);
		
		IRCategoria c1 = new RCategoria("Tipado");
		IRCategoria c2 = new RCategoria("Sobrecarga");
		IRCategoria c3 = new RCategoria("Constructores");
		IRCategoria c4 = new RCategoria("Spring");
		IRCategoria c5 = new RCategoria("MVC");
		
		cDAO.save(c1);
		cDAO.save(c2);
		cDAO.save(c3);
		cDAO.save(c4);
		cDAO.save(c5);
		
		// IRDificultades
		
		IRDificultadDAO dDAO = context.getBean(IRDificultadDAO.class);
		
		IRDificultad d1 = new RDificultad("Muy baja", 0f);
		IRDificultad d2 = new RDificultad("Baja", 2.5f);
		IRDificultad d3 = new RDificultad("Media", 5f);
		IRDificultad d4 = new RDificultad("Alta", 7.5f);
		IRDificultad d5 = new RDificultad("Muy alta", 10f);
		
		dDAO.save(d1);
		dDAO.save(d2);
		dDAO.save(d3);
		dDAO.save(d4);
		dDAO.save(d5);
		
		// IREjercicios
		
		IREjercicioDAO eDAO = context.getBean(IREjercicioDAO.class);
		
		IREjercicio e1 = new REjercicio("Ejercicio 1", "Ejercicio 1", pr1, d2, false);
		e1.setIRCategorias(new HashSet<>(Arrays.asList(c1, c4)));
		
		IREjercicio e2 = new REjercicio("Ejercicio 2", "Ejercicio 2", pr2, d3, true);
		e2.setIRCategorias(new HashSet<>(Arrays.asList(c3, c5)));
		
		IREjercicio e3 = new REjercicio("Ejercicio 3", "Ejercicio 3", pr1, d5, true);
		e3.setIRCategorias(new HashSet<>(Arrays.asList(c4, c5)));
		
		IREjercicio e4 = new REjercicio("Ejercicio 4", "Ejercicio 4", pr1, d1, false);
		e4.setIRCategorias(new HashSet<>(Arrays.asList(c2, c5)));
		
		IREjercicio e5 = new REjercicio("Ejercicio 5", "Ejercicio 5", pr2, d3, true);
		e5.setIRCategorias(new HashSet<>(Arrays.asList(c1, c3)));
		
		eDAO.save(e1);
		eDAO.save(e2);
		eDAO.save(e3);
		eDAO.save(e4);
		eDAO.save(e5);
		
		// IRTitulaciones
		
		IRTitulacionDAO tDAO = context.getBean(IRTitulacionDAO.class);
		
		IRTitulacion t1 = new RTitulacion("Titulación en quimicas");
		IRTitulacion t2 = new RTitulacion("Titulación en matemáticas");
		IRTitulacion t3 = new RTitulacion("Titulación en biología");
		
		tDAO.save(t1);
		tDAO.save(t2);
		tDAO.save(t3);
		
		// IRExamenes
		
		IRExamenDAO exDAO = context.getBean(IRExamenDAO.class);
		
		IRExamen ex1 = new RExamen(1, 2016, "Examen de matemáticas de la primera convocatoria", t2, false, true);
		IRExamen ex2 = new RExamen(7, 2012, "Examen de química de la segunda convocatoria", t1, true, false);
		
		exDAO.save(ex1);
		exDAO.save(ex2);
		
		// IREjercicios IRExamen
		
		IREjercicioExamenDAO eEDAO = context.getBean(IREjercicioExamenDAO.class);
		IREjercicioExamen eE1 = new REjercicioExamen(e1, ex1, 0);
		IREjercicioExamen eE2 = new REjercicioExamen(e4, ex1, 1);
		
		IREjercicioExamen eE3 = new REjercicioExamen(e2, ex2, 0);
		IREjercicioExamen eE4 = new REjercicioExamen(e3, ex2, 1);
		IREjercicioExamen eE5 = new REjercicioExamen(e4, ex2, 2);
		
		eEDAO.save(eE1);
		eEDAO.save(eE2);
		eEDAO.save(eE3);
		eEDAO.save(eE4);
		eEDAO.save(eE5);
		
		// Practicas
		
		IRPracticaDAO praDAO = context.getBean(IRPracticaDAO.class);
		
		IRPractica pra1 = new RPractica(5, 2018, "Práctica de biología de la primera convocatoria", t2, false, false);
		praDAO.save(pra1);
		
		// IREjercicios Practica
		
		IREjercicioPracticaDAO ePDAO = context.getBean(IREjercicioPracticaDAO.class);
		
		IREjercicioPractica eP1 = new REjercicioPractica(e1, pra1, 0);
		IREjercicioPractica eP2 = new REjercicioPractica(e2, pra1, 1);
		
		ePDAO.save(eP1);
		ePDAO.save(eP2);
		
		// Practicas evaluables
		
		IRPracticaEvaluacionDAO praEDAO = context.getBean(IRPracticaEvaluacionDAO.class);
		
		IRPracticaEvaluacion praE1 = new RPracticaEvaluacion(11, 2014, "Práctica evaluable de matemáticas de convocatoria anticipada", t2, true, true);
		
		praEDAO.save(praE1);
		
		// IREjercicios Practica Evaluable
		
		IREjercicioPracticaEvaluacionDAO ePEDAO = context.getBean(IREjercicioPracticaEvaluacionDAO.class);
		
		IREjercicioPracticaEvaluacion ePE1 = new REjercicioPracticaEvaluacion(e2, praE1, 0);
		IREjercicioPracticaEvaluacion ePE2 = new REjercicioPracticaEvaluacion(e4, praE1, 1);
		
		ePEDAO.save(ePE1);
		ePEDAO.save(ePE2);
		
		context.close();
		
	}
	
}
