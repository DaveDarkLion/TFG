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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IEjercicioDAOTest extends ATest {

	@Autowired
	private IEjercicioDAO iEjercicioDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	@Autowired
	private IProfesorDAO iProfesorDAO;
	@Autowired
	private IDificultadDAO iDificultadDAO;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IProfesor iProfesor1;
	private IProfesor iProfesor2;
	
	private IDificultad iDificultad1;
	private IDificultad iDificultad2;
	
	private IEjercicio iEjercicio1;
	private IEjercicio iEjercicio2;
	
	@Before
	public void before () {
		
		iPersona1 = MFactory.newIPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iPersona2 = MFactory.newIPersona("alores@gmail.com", "Alejandro", "Lores", "Ibañez", "1234");
		
		iPersonaDAO.save(iPersona1);
		iPersonaDAO.save(iPersona2);
		
		iProfesor1 = MFactory.newIProfesor(iPersona1);
		iProfesor2 = MFactory.newIProfesor(iPersona2);
		
		iProfesorDAO.save(iProfesor1);
		iProfesorDAO.save(iProfesor2);
		
		iDificultad1 = MFactory.newIDificultad("Facil", 2.5f);
		iDificultad2 = MFactory.newIDificultad("Dificil", 7.5f);
		
		iDificultadDAO.save(iDificultad1);
		iDificultadDAO.save(iDificultad2);
		
		iEjercicio1 = MFactory.newIEjercicio("Ejercicio 1", "Ejercicio 1", iProfesor1, iDificultad1, false);
		iEjercicio2 = MFactory.newIEjercicio("Ejercicio 2", "Ejercicio 2", iProfesor2, iDificultad2, false);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iEjercicioDAO.getAll().size());
		
		iEjercicioDAO.save(iEjercicio1);
		
		int id = iEjercicio1.getId();
		
		assertNotNull(iEjercicioDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iEjercicioDAO.save(iEjercicio1);
		
		int id = iEjercicio1.getId();
		
		assertEquals("Ejercicio 1", iEjercicioDAO.find(id).getEnunciado());
		
		iEjercicio1.setEnunciado("test");
		iEjercicioDAO.update(iEjercicio1);
		
		assertEquals("test", iEjercicioDAO.find(id).getEnunciado());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iEjercicioDAO.save(iEjercicio1);
		
		int id = iEjercicio1.getId();
		
		assertNotNull(iEjercicioDAO.find(id));
		
		iEjercicioDAO.delete(iEjercicio1);
		
		assertNull(iEjercicioDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iEjercicioDAO.getAll().size());
		
		iEjercicioDAO.save(iEjercicio1);
		iEjercicioDAO.save(iEjercicio2);
		
		assertEquals(2, iEjercicioDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iEjercicioDAO.save(iEjercicio1);
		
		int id = iEjercicio1.getId();
		
		assertEquals(iEjercicio1, iEjercicioDAO.find(id));
		
	}
	
}
