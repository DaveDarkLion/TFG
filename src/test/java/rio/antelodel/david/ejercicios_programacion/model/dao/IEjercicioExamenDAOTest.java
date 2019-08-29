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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IEjercicioExamenDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IExamenDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicio;
import rio.antelodel.david.ejercicios_programacion.model.iface.IEjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;
import rio.antelodel.david.ejercicios_programacion.model.primary_key.IEjercicioExamenPK;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IEjercicioExamenDAOTest extends ATest {

	@Autowired
	private IEjercicioExamenDAO iEjercicioExamenDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	@Autowired
	private IProfesorDAO iProfesorDAO;
	@Autowired
	private IEjercicioDAO iEjercicioDAO;
	@Autowired
	private IDificultadDAO iDificultadDAO;
	@Autowired
	private IExamenDAO iExamenDAO;
	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	private IEjercicioExamen iEjercicioExamen1;
	private IEjercicioExamen iEjercicioExamen2;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IProfesor iProfesor1;
	
	private IEjercicio iEjercicio1;
	private IEjercicio iEjercicio2;
	
	private IDificultad iDificultad1;
	private IDificultad iDificultad2;
	
	private ITitulacion iTitulacion1;
	
	private IExamen iExamen1;
	private IExamen iExamen2;
	
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
		
		iExamen1 = MFactory.newIExamen(1, 2016, "Examen de matematicas de la primera convocatoria", iTitulacion1, false, true);
		iExamen2 = MFactory.newIExamen(6, 2013, "Examen de quimica de la segunda convocatoria", iTitulacion1, true, false);
		
		iExamenDAO.save(iExamen1);
		iExamenDAO.save(iExamen2);
		
		iEjercicioExamen1 = MFactory.newIEjercicioExamen(iEjercicio1, iExamen1, 1);
		iEjercicioExamen2 = MFactory.newIEjercicioExamen(iEjercicio2, iExamen2, 2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iEjercicioExamenDAO.getAll().size());
		
		iEjercicioExamenDAO.save(iEjercicioExamen1);
		
		IEjercicioExamenPK id = new IEjercicioExamenPK(iEjercicioExamen1.getIEjercicio(), iEjercicioExamen1.getIExamen());
		
		assertNotNull(iEjercicioExamenDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iEjercicioExamenDAO.save(iEjercicioExamen1);
		
		IEjercicioExamenPK id = new IEjercicioExamenPK(iEjercicioExamen1.getIEjercicio(), iEjercicioExamen1.getIExamen());
		
		assertEquals(iEjercicio1, iEjercicioExamenDAO.find(id).getIEjercicio());
		
		iEjercicioExamen1.setIEjercicio(iEjercicio2);
		iEjercicioExamenDAO.update(iEjercicioExamen1);
		
		assertEquals(iEjercicio2, iEjercicioExamenDAO.find(id).getIEjercicio());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iEjercicioExamenDAO.save(iEjercicioExamen1);
		
		IEjercicioExamenPK id = new IEjercicioExamenPK(iEjercicioExamen1.getIEjercicio(), iEjercicioExamen1.getIExamen());
		
		assertNotNull(iEjercicioExamenDAO.find(id));
		
		iEjercicioExamenDAO.delete(iEjercicioExamen1);
		
		assertNull(iEjercicioExamenDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iEjercicioExamenDAO.getAll().size());
		
		iEjercicioExamenDAO.save(iEjercicioExamen1);
		iEjercicioExamenDAO.save(iEjercicioExamen2);
		
		assertEquals(2, iEjercicioExamenDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iEjercicioExamenDAO.save(iEjercicioExamen1);
		
		IEjercicioExamenPK id = new IEjercicioExamenPK(iEjercicioExamen1.getIEjercicio(), iEjercicioExamen1.getIExamen());
		
		assertEquals(iEjercicioExamen1, iEjercicioExamenDAO.find(id));
		
	}
	
}
