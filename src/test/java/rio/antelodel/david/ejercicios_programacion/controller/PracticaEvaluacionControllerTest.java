package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.PracticaEvaluacionController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class PracticaEvaluacionControllerTest extends AControllerTest {

	// Names
	
	protected static final String DATA_NAME = "data";
	
	// Controllers
	
	@Autowired
	private PracticaEvaluacionController practicaEvaluacionController;
	@Autowired
	private IRTitulacionDAO iRTitulacionDAO;
	@Autowired
	private IRDificultadDAO iRDificultadDAO;
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	@Autowired
	private IREjercicioDAO iREjercicioDAO;
	@Autowired
	private IREjercicioPersonaDAO iREjercicioPersonaDAO;
	@Autowired
	private IREjercicioPracticaEvaluacionDAO iREjercicioPracticaEvaluacionDAO;
	@Autowired
	private IRPracticaEvaluacionDAO iRPracticaEvaluacionDAO;
	
	private IRPersona iRPersona1;
	
	private IRProfesor iRProfesor1;
	
	private IRTitulacion iRTitulacion1;
	
	private IRDificultad iRDificultad1;
	
	private IREjercicio iREjercicio1;
	
	// Functions
	
	@Before
	public void before () {
		
		iRPersona1 = IRFactory.newIRPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iRPersonaDAO.save(iRPersona1);
		
		iRProfesor1 = IRFactory.newIRProfesor(iRPersona1);
		iRProfesorDAO.save(iRProfesor1);
		
		iRTitulacion1 = IRFactory.newIRTitulacion("titulacion 1");
		iRTitulacionDAO.save(iRTitulacion1);
		
		iRDificultad1 = IRFactory.newIRDificultad("dificultad 1", 2f);
		iRDificultadDAO.save(iRDificultad1);
		
		iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad1, true);
		iREjercicioDAO.save(iREjercicio1);
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPracticaEvaluacionEditForm_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRPracticaEvaluacion iRPracticaEvaluacion1 = IRFactory.newIRPracticaEvaluacion(1, 1, "practicaEvaluacion 1", iRTitulacion1, false, false);
		iRPracticaEvaluacionDAO.save(iRPracticaEvaluacion1);
	        
		// Testing
		
        ModelAndView modelAndView = practicaEvaluacionController.getPracticaEvaluacionEditForm(iRPracticaEvaluacion1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_NEW_NAME));
        
        JSONObject data = model.getJSONObject(DATA_NAME);
        
        assertEquals(iRPracticaEvaluacion1.getDescripcion(), data.get("descripcion"));
        assertEquals(iRTitulacion1.getId(), model.getJSONObject(TITULACION_CURRENT_DATA_NAME).get("id"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPracticaEvaluacionEditForm_Error () {
		
		// Setup
		
		loginAsAdministrador();
	        
		// Testing
		
		ModelAndView modelAndView = practicaEvaluacionController.getPracticaEvaluacionEditForm(0);
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPracticaEvaluacionNewForm () {
	
		// Setup
		
		loginAsAdministrador();
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = practicaEvaluacionController.getPracticaEvaluacionNewForm(null);
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
	     assertEquals(1, model.getInt(IS_NEW_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreatePracticaEvaluacion_Normal_NoCart () {
		
		// Setup
		
		loginAsProfesor();
		
		// Testing
		
		ModelAndView modelAndView = practicaEvaluacionController.createPracticaEvaluacion("true", null, "practicaEvaluacion 1", Integer.toString(iRTitulacion1.getId()), Integer.toString(1), Integer.toString(1), null);
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRPracticaEvaluacion iRPracticaEvaluacion1 = iRPracticaEvaluacionDAO.getAll().get(0);
		
		assertEquals("practicaEvaluacion 1", iRPracticaEvaluacion1.getDescripcion());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreatePracticaEvaluacion_Normal_Cart () {
		
		// Setup
		
		IRProfesor iRProfesorMe = iRProfesorDAO.find(loginAsProfesor());
		
		IREjercicioPersona iREjercicioPersona1 = IRFactory.newIREjercicioPersona(iREjercicio1, iRProfesorMe.getIRPersona(), 1);
		iREjercicioPersonaDAO.save(iREjercicioPersona1);
		
		iRProfesorMe.getIRPersona().getEntity().setIEjerciciosPersona(new HashSet<>(Arrays.asList(iREjercicioPersona1.getEntity())));
		
		// Testing
		
		assertEquals(0, iREjercicioPracticaEvaluacionDAO.getAll().size());
		
		ModelAndView modelAndView = practicaEvaluacionController.createPracticaEvaluacion("true", null, "practicaEvaluacion 1", Integer.toString(iRTitulacion1.getId()), Integer.toString(1), Integer.toString(1), "true");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRPracticaEvaluacion iRPracticaEvaluacion1 = iRPracticaEvaluacionDAO.getAll().get(0);
		
		assertEquals("practicaEvaluacion 1", iRPracticaEvaluacion1.getDescripcion());
		
		assertEquals(1, iREjercicioPracticaEvaluacionDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreatePracticaEvaluacion_Error () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = practicaEvaluacionController.createPracticaEvaluacion(null, null, null, null, null, null, null);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdatePracticaEvaluacion_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRPracticaEvaluacion iRPracticaEvaluacion1 = IRFactory.newIRPracticaEvaluacion(1, 1, "practicaEvaluacion 1", iRTitulacion1, false, false);
		iRPracticaEvaluacionDAO.save(iRPracticaEvaluacion1);

		// Testing
		
		ModelAndView modelAndView = practicaEvaluacionController.updatePracticaEvaluacion(iRPracticaEvaluacion1.getId(), "true", "true", "practicaEvaluacion updated", Integer.toString(iRTitulacion1.getId()), Integer.toString(1), Integer.toString(1));
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRPracticaEvaluacion1 = iRPracticaEvaluacionDAO.find(iRPracticaEvaluacion1.getId());
		
		assertEquals("practicaEvaluacion updated", iRPracticaEvaluacion1.getDescripcion());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdatePracticaEvaluacion_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = practicaEvaluacionController.updatePracticaEvaluacion(0, "true", "true", "practicaEvaluacion updated", Integer.toString(iRTitulacion1.getId()), Integer.toString(1), Integer.toString(1));
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeletePracticaEvaluacion_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRPracticaEvaluacion iRPracticaEvaluacion1 = IRFactory.newIRPracticaEvaluacion(1, 1, "practicaEvaluacion 1", iRTitulacion1, false, false);
		iRPracticaEvaluacionDAO.save(iRPracticaEvaluacion1);
		
		// Testing
		
		ModelAndView modelAndView = practicaEvaluacionController.deletePracticaEvaluacion(iRPracticaEvaluacion1.getId());
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRPracticaEvaluacion1 = iRPracticaEvaluacionDAO.find(iRPracticaEvaluacion1.getId());
		
		assertTrue(iRPracticaEvaluacion1.isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeletePracticaEvaluacion_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = practicaEvaluacionController.deletePracticaEvaluacion(0);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRPracticaEvaluacion iRPracticaEvaluacion1 = IRFactory.newIRPracticaEvaluacion(1, 1, "practicaEvaluacion 1", iRTitulacion1, false, false);
		iRPracticaEvaluacionDAO.save(iRPracticaEvaluacion1);
		
		// Testing
		
		assertNotNull(practicaEvaluacionController.getDocument(iRPracticaEvaluacion1.getId(), Integer.toString(0)));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Null_NoPracticaEvaluacion () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		assertNull(practicaEvaluacionController.getDocument(0, Integer.toString(0)));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Null_NotPrivileged () {
		
		// Setup
		
		loginAsAlumno();
		
		IRPracticaEvaluacion iRPracticaEvaluacion1 = IRFactory.newIRPracticaEvaluacion(1, 1, "practicaEvaluacion 1", iRTitulacion1, false, false);
		iRPracticaEvaluacionDAO.save(iRPracticaEvaluacion1);
		
		// Testing
		
		assertNull(practicaEvaluacionController.getDocument(iRPracticaEvaluacion1.getId(), Integer.toString(0)));
		
	}
	
}