package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.EjercicioController;
import rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class EjercicioControllerTest extends AControllerTest {

	// Names
	
	protected static final String ENUNCIADO_NAME = "enunciado";
	protected static final String EJERCICIO_DATA_NAME = "ejercicioData";
	protected static final String DIFICULTAD_ALUMNO_DATA_NAME = "dificultadAlumnoData";
	protected static final String DIFICULTAD_ALUMNO_AVERAGE_DATA_NAME = "dificultadAlumnoAverageData";
	
	protected static final String VIEW_EJERCICIO_VIEW = "ejercicio/ejercicio-edit/ejercicio-view";
	protected static final String VIEW_EJERCICIO_EDIT = "ejercicio/ejercicio-edit/ejercicio-edit";
	
	// Controllers
	
	@Autowired
	private EjercicioController ejercicioController;
	
	// DAOs
	
	@Autowired
	private IREjercicioDAO iREjercicioDAO;
	
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	@Autowired
	private IRDificultadDAO iRDificultadDAO;
	@Autowired
	private IRCategoriaDAO iRCategoriaDAO;
	
	private IRPersona iRPersona1;
	
	private IRProfesor iRProfesor1;
	
	private IRDificultad iRDificultad1;
	
	private IRCategoria iRCategoria1;
	
	// Functions
	
	@Before
	public void before () {
		
		iRPersona1 = IRFactory.newIRPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iRPersonaDAO.save(iRPersona1);
		
		iRProfesor1 = IRFactory.newIRProfesor(iRPersona1);
		iRProfesorDAO.save(iRProfesor1);
		
		iRCategoria1 = IRFactory.newIRCategoria("categoria 1");
		iRCategoriaDAO.save(iRCategoria1);
		
		iRDificultad1 = IRFactory.newIRDificultad("dificultad 1", 2f);
		iRDificultadDAO.save(iRDificultad1);
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioEditForm_Normal_1 () {
		
		// Setup
		
		loginAsAdministrador();
		
		IREjercicio iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad1, true);
		iREjercicioDAO.save(iREjercicio1);
	        
		// Testing
		
        ModelAndView modelAndView = ejercicioController.getEjercicioEditForm(iREjercicio1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_NEW_NAME));
        
        JSONObject data = model.getJSONObject(EJERCICIO_DATA_NAME);
        
        assertEquals(iREjercicio1.getEnunciado(), data.get("enunciado"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioEditForm_Normal_2 () {
		
		// Setup
		
		loginAsAlumno();
		
		IREjercicio iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad1, false);
		iREjercicioDAO.save(iREjercicio1);
	        
		// Testing
		
        ModelAndView modelAndView = ejercicioController.getEjercicioEditForm(iREjercicio1.getId());
        
        assertEquals(null, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioEditForm_Normal_4 () {
		
		// Setup
		
		loginAsProfesor();
		
		IREjercicio iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad1, true);
		iREjercicioDAO.save(iREjercicio1);
	        
		// Testing
		
        ModelAndView modelAndView = ejercicioController.getEjercicioEditForm(iREjercicio1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_NEW_NAME));
        
        JSONObject data = model.getJSONObject(EJERCICIO_DATA_NAME);
        
        assertEquals(iREjercicio1.getEnunciado(), data.get("enunciado"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioEditForm_AccessDenied () {
		
		// Setup
		
		loginAsTestUser(new ArrayList<>());
		
		IREjercicio iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad1, true);
		iREjercicioDAO.save(iREjercicio1);
	        
		// Testing
		
        ModelAndView modelAndView = ejercicioController.getEjercicioEditForm(iREjercicio1.getId());

        assertEquals(ControllerViews.getAccessDeniedView().getViewName(), modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioEditForm_Error () {
		
		// Setup
		
		loginAsAdministrador();
	        
		// Testing
		
		ModelAndView modelAndView = ejercicioController.getEjercicioEditForm(0);
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioViewForm_Normal_1 () {
		
		// Setup
		
		loginAsAdministrador();
		
		IREjercicio iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad1, true);
		iREjercicioDAO.save(iREjercicio1);
	        
		// Testing
		
        ModelAndView modelAndView = ejercicioController.getEjercicioEditForm(iREjercicio1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_NEW_NAME));
        
        JSONObject data = model.getJSONObject(EJERCICIO_DATA_NAME);
        
        assertEquals(iREjercicio1.getEnunciado(), data.get("enunciado"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioViewForm_Error () {
		
		// Setup
		
		loginAsAdministrador();
	        
		// Testing
		
		ModelAndView modelAndView = ejercicioController.getEjercicioViewForm(0);
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioNewForm_1 () {
	
		// Setup
		
		loginAsProfesor();
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = ejercicioController.getEjercicioNewForm("idea");
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
	
	     assertEquals(1, model.getInt(IS_NEW_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioNewForm_2 () {
	
		// Setup
		
		loginAsProfesor();
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = ejercicioController.getEjercicioNewForm(null);
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
	
	     assertEquals(1, model.getInt(IS_NEW_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteEjercicio_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IREjercicio iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad1, true);
		iREjercicioDAO.save(iREjercicio1);
		
		// Testing
		
		ModelAndView modelAndView = ejercicioController.deleteEjercicio(iREjercicio1.getId());
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iREjercicio1 = iREjercicioDAO.find(iREjercicio1.getId());
		
		assertTrue(iREjercicio1.isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteEjercicio_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = ejercicioController.deleteEjercicio(0);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
}