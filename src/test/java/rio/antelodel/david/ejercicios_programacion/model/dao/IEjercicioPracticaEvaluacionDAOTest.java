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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioPracticaEvaluacionPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IEjercicioPracticaEvaluacionDAOTest extends ATest {

	@Autowired
	private IEjercicioPracticaEvaluacionDAO iEjercicioPracticaEvaluacionDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	@Autowired
	private IProfesorDAO iProfesorDAO;
	@Autowired
	private IEjercicioDAO iEjercicioDAO;
	@Autowired
	private IDificultadDAO iDificultadDAO;
	@Autowired
	private IPracticaEvaluacionDAO iPracticaEvaluacionDAO;
	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	private IEjercicioPracticaEvaluacion iEjercicioPracticaEvaluacion1;
	private IEjercicioPracticaEvaluacion iEjercicioPracticaEvaluacion2;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IProfesor iProfesor1;
	
	private IEjercicio iEjercicio1;
	private IEjercicio iEjercicio2;
	
	private IDificultad iDificultad1;
	private IDificultad iDificultad2;
	
	private ITitulacion iTitulacion1;
	
	private IPracticaEvaluacion iPracticaEvaluacion1;
	private IPracticaEvaluacion iPracticaEvaluacion2;
	
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
		
		iPracticaEvaluacion1 = MFactory.newIPracticaEvaluacion(1, 2016, "PracticaEvaluacion de matematicas de la primera convocatoria", iTitulacion1, false, true);
		iPracticaEvaluacion2 = MFactory.newIPracticaEvaluacion(6, 2013, "PracticaEvaluacion de quimica de la segunda convocatoria", iTitulacion1, true, false);
		
		iPracticaEvaluacionDAO.save(iPracticaEvaluacion1);
		iPracticaEvaluacionDAO.save(iPracticaEvaluacion2);
		
		iEjercicioPracticaEvaluacion1 = MFactory.newIEjercicioPracticaEvaluacion(iEjercicio1, iPracticaEvaluacion1, 1);
		iEjercicioPracticaEvaluacion2 = MFactory.newIEjercicioPracticaEvaluacion(iEjercicio2, iPracticaEvaluacion2, 2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iEjercicioPracticaEvaluacionDAO.getAll().size());
		
		iEjercicioPracticaEvaluacionDAO.save(iEjercicioPracticaEvaluacion1);
		
		IEjercicioPracticaEvaluacionPK id = new IEjercicioPracticaEvaluacionPK(iEjercicioPracticaEvaluacion1.getIEjercicio(), iEjercicioPracticaEvaluacion1.getIPracticaEvaluacion());
		
		assertNotNull(iEjercicioPracticaEvaluacionDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iEjercicioPracticaEvaluacionDAO.save(iEjercicioPracticaEvaluacion1);
		
		IEjercicioPracticaEvaluacionPK id = new IEjercicioPracticaEvaluacionPK(iEjercicioPracticaEvaluacion1.getIEjercicio(), iEjercicioPracticaEvaluacion1.getIPracticaEvaluacion());
		
		assertEquals(iEjercicio1, iEjercicioPracticaEvaluacionDAO.find(id).getIEjercicio());
		
		iEjercicioPracticaEvaluacion1.setIEjercicio(iEjercicio2);
		iEjercicioPracticaEvaluacionDAO.update(iEjercicioPracticaEvaluacion1);
		
		assertEquals(iEjercicio2, iEjercicioPracticaEvaluacionDAO.find(id).getIEjercicio());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iEjercicioPracticaEvaluacionDAO.save(iEjercicioPracticaEvaluacion1);
		
		IEjercicioPracticaEvaluacionPK id = new IEjercicioPracticaEvaluacionPK(iEjercicioPracticaEvaluacion1.getIEjercicio(), iEjercicioPracticaEvaluacion1.getIPracticaEvaluacion());
		
		assertNotNull(iEjercicioPracticaEvaluacionDAO.find(id));
		
		iEjercicioPracticaEvaluacionDAO.delete(iEjercicioPracticaEvaluacion1);
		
		assertNull(iEjercicioPracticaEvaluacionDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iEjercicioPracticaEvaluacionDAO.getAll().size());
		
		iEjercicioPracticaEvaluacionDAO.save(iEjercicioPracticaEvaluacion1);
		iEjercicioPracticaEvaluacionDAO.save(iEjercicioPracticaEvaluacion2);
		
		assertEquals(2, iEjercicioPracticaEvaluacionDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iEjercicioPracticaEvaluacionDAO.save(iEjercicioPracticaEvaluacion1);
		
		IEjercicioPracticaEvaluacionPK id = new IEjercicioPracticaEvaluacionPK(iEjercicioPracticaEvaluacion1.getIEjercicio(), iEjercicioPracticaEvaluacion1.getIPracticaEvaluacion());
		
		assertEquals(iEjercicioPracticaEvaluacion1, iEjercicioPracticaEvaluacionDAO.find(id));
		
	}
	
}
