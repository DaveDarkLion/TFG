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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPersonaPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IEjercicioPersonaDAOTest extends ATest {

	@Autowired
	private IEjercicioPersonaDAO iEjercicioPersonaDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	@Autowired
	private IProfesorDAO iProfesorDAO;
	@Autowired
	private IEjercicioDAO iEjercicioDAO;
	@Autowired
	private IDificultadDAO iDificultadDAO;
	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	private IEjercicioPersona iEjercicioPersona1;
	private IEjercicioPersona iEjercicioPersona2;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IProfesor iProfesor1;
	
	private IEjercicio iEjercicio1;
	private IEjercicio iEjercicio2;
	
	private IDificultad iDificultad1;
	private IDificultad iDificultad2;
	
	private ITitulacion iTitulacion1;
	
	@Before
	public void before () {
		
		iPersona1 = MFactory.newIPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iPersona2 = MFactory.newIPersona("alores@gmail.com", "Alejandro", "Lores", "Ibañez", "1234");
		
		iPersonaDAO.save(iPersona1);
		iPersonaDAO.save(iPersona2);
		
		iProfesor1 = MFactory.newIProfesor(iPersona2);
		
		iProfesorDAO.save(iProfesor1);
		
		iDificultad1 = MFactory.newIDificultad("Facil", 2.5f);
		iDificultad2 = MFactory.newIDificultad("Dificil", 7.5f);
		
		iDificultadDAO.save(iDificultad1);
		iDificultadDAO.save(iDificultad2);
		
		iEjercicio1 = MFactory.newIEjercicio("Ejercicio 1", "Ejercicio 1", iProfesor1, iDificultad1, false);
		iEjercicio2 = MFactory.newIEjercicio("Ejercicio 2", "Ejercicio 2", iProfesor1, iDificultad2, false);
		
		iEjercicioDAO.save(iEjercicio1);
		iEjercicioDAO.save(iEjercicio2);
		
		iTitulacion1 = MFactory.newITitulacion("Titulacion 1");
		
		iTitulacionDAO.save(iTitulacion1);
		
		iPersonaDAO.save(iPersona1);
		iPersonaDAO.save(iPersona2);
		
		iEjercicioPersona1 = MFactory.newIEjercicioPersona(iEjercicio1, iPersona1, 1);
		iEjercicioPersona2 = MFactory.newIEjercicioPersona(iEjercicio2, iPersona2, 2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iEjercicioPersonaDAO.getAll().size());
		
		iEjercicioPersonaDAO.save(iEjercicioPersona1);
		
		IEjercicioPersonaPK id = new IEjercicioPersonaPK(iEjercicioPersona1.getIEjercicio(), iEjercicioPersona1.getIPersona());
		
		assertNotNull(iEjercicioPersonaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iEjercicioPersonaDAO.save(iEjercicioPersona1);
		
		IEjercicioPersonaPK id = new IEjercicioPersonaPK(iEjercicioPersona1.getIEjercicio(), iEjercicioPersona1.getIPersona());
		
		assertEquals(iEjercicio1, iEjercicioPersonaDAO.find(id).getIEjercicio());
		
		iEjercicioPersona1.setIEjercicio(iEjercicio2);
		iEjercicioPersonaDAO.update(iEjercicioPersona1);
		
		assertEquals(iEjercicio2, iEjercicioPersonaDAO.find(id).getIEjercicio());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iEjercicioPersonaDAO.save(iEjercicioPersona1);
		
		IEjercicioPersonaPK id = new IEjercicioPersonaPK(iEjercicioPersona1.getIEjercicio(), iEjercicioPersona1.getIPersona());
		
		assertNotNull(iEjercicioPersonaDAO.find(id));
		
		iEjercicioPersonaDAO.delete(iEjercicioPersona1);
		
		assertNull(iEjercicioPersonaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iEjercicioPersonaDAO.getAll().size());
		
		iEjercicioPersonaDAO.save(iEjercicioPersona1);
		iEjercicioPersonaDAO.save(iEjercicioPersona2);
		
		assertEquals(2, iEjercicioPersonaDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iEjercicioPersonaDAO.save(iEjercicioPersona1);
		
		IEjercicioPersonaPK id = new IEjercicioPersonaPK(iEjercicioPersona1.getIEjercicio(), iEjercicioPersona1.getIPersona());
		
		assertEquals(iEjercicioPersona1, iEjercicioPersonaDAO.find(id));
		
	}
	
}
