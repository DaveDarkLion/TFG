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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IEjercicioPracticaDAOTest extends ATest {

	@Autowired
	private IEjercicioPracticaDAO iEjercicioPracticaDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	@Autowired
	private IProfesorDAO iProfesorDAO;
	@Autowired
	private IEjercicioDAO iEjercicioDAO;
	@Autowired
	private IDificultadDAO iDificultadDAO;
	@Autowired
	private IPracticaDAO iPracticaDAO;
	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	private IEjercicioPractica iEjercicioPractica1;
	private IEjercicioPractica iEjercicioPractica2;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IProfesor iProfesor1;
	
	private IEjercicio iEjercicio1;
	private IEjercicio iEjercicio2;
	
	private IDificultad iDificultad1;
	private IDificultad iDificultad2;
	
	private ITitulacion iTitulacion1;
	
	private IPractica iPractica1;
	private IPractica iPractica2;
	
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
		
		iPractica1 = MFactory.newIPractica(1, 2016, "Practica de matematicas de la primera convocatoria", iTitulacion1, false, true);
		iPractica2 = MFactory.newIPractica(6, 2013, "Practica de quimica de la segunda convocatoria", iTitulacion1, true, false);
		
		iPracticaDAO.save(iPractica1);
		iPracticaDAO.save(iPractica2);
		
		iEjercicioPractica1 = MFactory.newIEjercicioPractica(iEjercicio1, iPractica1, 1);
		iEjercicioPractica2 = MFactory.newIEjercicioPractica(iEjercicio2, iPractica2, 2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iEjercicioPracticaDAO.getAll().size());
		
		iEjercicioPracticaDAO.save(iEjercicioPractica1);
		
		IEjercicioPracticaPK id = new IEjercicioPracticaPK(iEjercicioPractica1.getIEjercicio(), iEjercicioPractica1.getIPractica());
		
		assertNotNull(iEjercicioPracticaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iEjercicioPracticaDAO.save(iEjercicioPractica1);
		
		IEjercicioPracticaPK id = new IEjercicioPracticaPK(iEjercicioPractica1.getIEjercicio(), iEjercicioPractica1.getIPractica());
		
		assertEquals(iEjercicio1, iEjercicioPracticaDAO.find(id).getIEjercicio());
		
		iEjercicioPractica1.setIEjercicio(iEjercicio2);
		iEjercicioPracticaDAO.update(iEjercicioPractica1);
		
		assertEquals(iEjercicio2, iEjercicioPracticaDAO.find(id).getIEjercicio());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iEjercicioPracticaDAO.save(iEjercicioPractica1);
		
		IEjercicioPracticaPK id = new IEjercicioPracticaPK(iEjercicioPractica1.getIEjercicio(), iEjercicioPractica1.getIPractica());
		
		assertNotNull(iEjercicioPracticaDAO.find(id));
		
		iEjercicioPracticaDAO.delete(iEjercicioPractica1);
		
		assertNull(iEjercicioPracticaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iEjercicioPracticaDAO.getAll().size());
		
		iEjercicioPracticaDAO.save(iEjercicioPractica1);
		iEjercicioPracticaDAO.save(iEjercicioPractica2);
		
		assertEquals(2, iEjercicioPracticaDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iEjercicioPracticaDAO.save(iEjercicioPractica1);
		
		IEjercicioPracticaPK id = new IEjercicioPracticaPK(iEjercicioPractica1.getIEjercicio(), iEjercicioPractica1.getIPractica());
		
		assertEquals(iEjercicioPractica1, iEjercicioPracticaDAO.find(id));
		
	}
	
}
