package rio.antelodel.david.ejercicios_programacion.model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IDificultadAlumnoEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultadAlumnoEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;
import rio.antelodel.david.ejercicios_programacion.model.implementation.Ejercicio;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IDificultadAlumnoEjercicioPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IDificultadAlumnoEjercicioDAOTest extends ATest {

	@Autowired
	private IDificultadAlumnoEjercicioDAO iDificultadAlumnoEjercicioDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	@Autowired
	private IAlumnoDAO iAlumnoDAO;
	@Autowired
	private IProfesorDAO iProfesorDAO;
	@Autowired
	private IEjercicioDAO iEjercicioDAO;
	@Autowired
	private IDificultadDAO iDificultadDAO;
	
	private IDificultadAlumnoEjercicio iDificultadAlumnoEjercicio1;
	private IDificultadAlumnoEjercicio iDificultadAlumnoEjercicio2;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IAlumno iAlumno1;
	private IProfesor iProfesor1;
	
	private IEjercicio iEjercicio1;
	private IEjercicio iEjercicio2;
	
	private IDificultad iDificultad1;
	private IDificultad iDificultad2;
	
	@Before
	public void before () {
		
		iPersona1 = MFactory.newIPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iPersona2 = MFactory.newIPersona("alores@gmail.com", "Alejandro", "Lores", "Ibañez", "1234");
		
		iPersonaDAO.save(iPersona1);
		iPersonaDAO.save(iPersona2);
		
		iAlumno1 = MFactory.newIAlumno(iPersona1);
		
		iAlumnoDAO.save(iAlumno1);
		
		iProfesor1 = MFactory.newIProfesor(iPersona2);
		
		iProfesorDAO.save(iProfesor1);
		
		iDificultad1 = MFactory.newIDificultad("Facil", 2.5f);
		iDificultad2 = MFactory.newIDificultad("Dificil", 7.5f);
		
		iDificultadDAO.save(iDificultad1);
		iDificultadDAO.save(iDificultad2);
		
		iEjercicio1 = new Ejercicio("Ejercicio 1", "Ejercicio 1", iProfesor1, iDificultad1, false);
		iEjercicio2 = new Ejercicio("Ejercicio 2", "Ejercicio 2", iProfesor1, iDificultad2, false);
		
		iEjercicioDAO.save(iEjercicio1);
		iEjercicioDAO.save(iEjercicio2);
		
		iDificultadAlumnoEjercicio1 = MFactory.newIDificultadAlumnoEjercicio(iAlumno1, iEjercicio1, iDificultad1);
		iDificultadAlumnoEjercicio2 = MFactory.newIDificultadAlumnoEjercicio(iAlumno1, iEjercicio2, iDificultad2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iDificultadAlumnoEjercicioDAO.getAll().size());
		
		iDificultadAlumnoEjercicioDAO.save(iDificultadAlumnoEjercicio1);
		
		IDificultadAlumnoEjercicioPK id = new IDificultadAlumnoEjercicioPK(iDificultadAlumnoEjercicio1.getIAlumno(), iDificultadAlumnoEjercicio1.getIEjercicio());
		
		assertNotNull(iDificultadAlumnoEjercicioDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iDificultadAlumnoEjercicioDAO.save(iDificultadAlumnoEjercicio1);
		
		IDificultadAlumnoEjercicioPK id = new IDificultadAlumnoEjercicioPK(iDificultadAlumnoEjercicio1.getIAlumno(), iDificultadAlumnoEjercicio1.getIEjercicio());
		
		assertEquals(iDificultad1, iDificultadAlumnoEjercicioDAO.find(id).getIDificultad());
		
		iDificultadAlumnoEjercicio1.setIDificultad(iDificultad2);
		iDificultadAlumnoEjercicioDAO.update(iDificultadAlumnoEjercicio1);
		
		assertEquals(iDificultad2, iDificultadAlumnoEjercicioDAO.find(id).getIDificultad());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iDificultadAlumnoEjercicioDAO.save(iDificultadAlumnoEjercicio1);
		
		IDificultadAlumnoEjercicioPK id = new IDificultadAlumnoEjercicioPK(iDificultadAlumnoEjercicio1.getIAlumno(), iDificultadAlumnoEjercicio1.getIEjercicio());
		
		assertNotNull(iDificultadAlumnoEjercicioDAO.find(id));
		
		iDificultadAlumnoEjercicioDAO.delete(iDificultadAlumnoEjercicio1);
		
		assertNull(iDificultadAlumnoEjercicioDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iDificultadAlumnoEjercicioDAO.getAll().size());
		
		iDificultadAlumnoEjercicioDAO.save(iDificultadAlumnoEjercicio1);
		iDificultadAlumnoEjercicioDAO.save(iDificultadAlumnoEjercicio2);
		
		assertEquals(2, iDificultadAlumnoEjercicioDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iDificultadAlumnoEjercicioDAO.save(iDificultadAlumnoEjercicio1);
		
		IDificultadAlumnoEjercicioPK id = new IDificultadAlumnoEjercicioPK(iDificultadAlumnoEjercicio1.getIAlumno(), iDificultadAlumnoEjercicio1.getIEjercicio());
		
		assertEquals(iDificultadAlumnoEjercicio1, iDificultadAlumnoEjercicioDAO.find(id));
		
	}
	
}
