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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.PracticaController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class PracticaControllerTest extends AControllerTest {

	// Names
	
	protected static final String DATA_NAME = "data";
	
	// Controllers
	
	@Autowired
	private PracticaController practicaController;
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
	private IREjercicioPracticaDAO iREjercicioPracticaDAO;
	@Autowired
	private IRPracticaDAO iRPracticaDAO;
	
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
	public void testGetPracticaEditForm_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRPractica iRPractica1 = IRFactory.newIRPractica(1, 1, "practica 1", iRTitulacion1, false, false);
		iRPracticaDAO.save(iRPractica1);
	        
		// Testing
		
        ModelAndView modelAndView = practicaController.getPracticaEditForm(iRPractica1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_NEW_NAME));
        
        JSONObject data = model.getJSONObject(DATA_NAME);
        
        assertEquals(iRPractica1.getDescripcion(), data.get("descripcion"));
        assertEquals(iRTitulacion1.getId(), model.getJSONObject(TITULACION_CURRENT_DATA_NAME).get("id"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPracticaEditForm_Error () {
		
		// Setup
		
		loginAsAdministrador();
	        
		// Testing
		
		ModelAndView modelAndView = practicaController.getPracticaEditForm(0);
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPracticaNewForm () {
	
		// Setup
		
		loginAsAdministrador();
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = practicaController.getPracticaNewForm(null);
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
	     assertEquals(1, model.getInt(IS_NEW_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreatePractica_Normal_NoCart () {
		
		// Setup
		
		loginAsProfesor();
		
		// Testing
		
		ModelAndView modelAndView = practicaController.createPractica("true", null, "practica 1", Integer.toString(iRTitulacion1.getId()), Integer.toString(1), Integer.toString(1), null);
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRPractica iRPractica1 = iRPracticaDAO.getAll().get(0);
		
		assertEquals("practica 1", iRPractica1.getDescripcion());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreatePractica_Normal_Cart () {
		
		// Setup
		
		IRProfesor iRProfesorMe = iRProfesorDAO.find(loginAsProfesor());
		
		IREjercicioPersona iREjercicioPersona1 = IRFactory.newIREjercicioPersona(iREjercicio1, iRProfesorMe.getIRPersona(), 1);
		iREjercicioPersonaDAO.save(iREjercicioPersona1);
		
		iRProfesorMe.getIRPersona().getEntity().setIEjerciciosPersona(new HashSet<>(Arrays.asList(iREjercicioPersona1.getEntity())));
		
		// Testing
		
		assertEquals(0, iREjercicioPracticaDAO.getAll().size());
		
		ModelAndView modelAndView = practicaController.createPractica("true", null, "practica 1", Integer.toString(iRTitulacion1.getId()), Integer.toString(1), Integer.toString(1), "true");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRPractica iRPractica1 = iRPracticaDAO.getAll().get(0);
		
		assertEquals("practica 1", iRPractica1.getDescripcion());
		
		assertEquals(1, iREjercicioPracticaDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreatePractica_Error () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = practicaController.createPractica(null, null, null, null, null, null, null);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdatePractica_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRPractica iRPractica1 = IRFactory.newIRPractica(1, 1, "practica 1", iRTitulacion1, false, false);
		iRPracticaDAO.save(iRPractica1);

		// Testing
		
		ModelAndView modelAndView = practicaController.updatePractica(iRPractica1.getId(), "true", "true", "practica updated", Integer.toString(iRTitulacion1.getId()), Integer.toString(1), Integer.toString(1));
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRPractica1 = iRPracticaDAO.find(iRPractica1.getId());
		
		assertEquals("practica updated", iRPractica1.getDescripcion());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdatePractica_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = practicaController.updatePractica(0, "true", "true", "practica updated", Integer.toString(iRTitulacion1.getId()), Integer.toString(1), Integer.toString(1));
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeletePractica_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRPractica iRPractica1 = IRFactory.newIRPractica(1, 1, "practica 1", iRTitulacion1, false, false);
		iRPracticaDAO.save(iRPractica1);
		
		// Testing
		
		ModelAndView modelAndView = practicaController.deletePractica(iRPractica1.getId());
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRPractica1 = iRPracticaDAO.find(iRPractica1.getId());
		
		assertTrue(iRPractica1.isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeletePractica_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = practicaController.deletePractica(0);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRPractica iRPractica1 = IRFactory.newIRPractica(1, 1, "practica 1", iRTitulacion1, false, false);
		iRPracticaDAO.save(iRPractica1);
		
		// Testing
		
		assertNotNull(practicaController.getDocument(iRPractica1.getId(), Integer.toString(0)));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Null_NoPractica () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		assertNull(practicaController.getDocument(0, Integer.toString(0)));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Null_NotPrivileged () {
		
		// Setup
		
		loginAsAlumno();
		
		IRPractica iRPractica1 = IRFactory.newIRPractica(1, 1, "practica 1", iRTitulacion1, false, false);
		iRPracticaDAO.save(iRPractica1);
		
		// Testing
		
		assertNull(practicaController.getDocument(iRPractica1.getId(), Integer.toString(0)));
		
	}
	
}