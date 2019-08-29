package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.IdeaController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IdeaControllerTest extends AControllerTest {

	// Names
	
	protected static final String DATA_NAME = "data";
	protected static final String IS_DIFICULTAD_NAME = "isDificultad";
	
	// Controllers
	
	@Autowired
	private IdeaController ideaController;
	
	// DAOs
	
	@Autowired
	private IRIdeaDAO iRIdeaDAO;
	
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	
	private IRProfesor iRProfesor1;
	
	// Functions
	
	@Before
	public void before () {
		
		iRProfesor1 = iRProfesorDAO.find(loginAsProfesor());
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetIdeaEditForm_Normal () {
		
		// Setup
		
		IRIdea iRIdea1 = IRFactory.newIRIdea("idea 1", "idea 1", iRProfesor1);
		iRIdeaDAO.save(iRIdea1);
	        
		// Testing
		
        ModelAndView modelAndView = ideaController.getIdeaEditForm(iRIdea1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
    
        assertEquals(0, model.getInt(IS_NEW_NAME));
        
        JSONObject data = model.getJSONObject(DATA_NAME);
        
        assertEquals("idea 1", data.get("nombre"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetIdeaEditForm_Error () {
	        
		// Testing
		
		ModelAndView modelAndView = ideaController.getIdeaEditForm(0);
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetIdeaNewForm () {
		
		// Setup
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = ideaController.getIdeaNewForm();
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
	     assertEquals(1, model.getInt(IS_NEW_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateIdea_Normal () {
		
		// Testing
		
		ModelAndView modelAndView = ideaController.createIdea("idea 1", "idea 1");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRIdea iRIdea1 = iRIdeaDAO.getAll().get(0);
		
		assertEquals("idea 1", iRIdea1.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateIdea_Error () {
		
		// Testing
		
		ModelAndView modelAndView = ideaController.createIdea(null, null);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateIdea_Normal () {
		
		// Setup
		
		IRIdea iRIdea1 = IRFactory.newIRIdea("idea 1", "idea 1", iRProfesor1);
		iRIdeaDAO.save(iRIdea1);

		// Testing
		
		ModelAndView modelAndView = ideaController.updateIdea(iRIdea1.getId(), "idea updated", "idea updated");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRIdea1 = iRIdeaDAO.find(iRIdea1.getId());
		
		assertEquals("idea updated", iRIdea1.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateIdea_Null () {
		
		// Testing
		
		ModelAndView modelAndView = ideaController.updateIdea(0, "idea updated", "idea updated");
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteIdea_Normal () {
		
		// Setup
		
		IRIdea iRIdea1 = IRFactory.newIRIdea("idea 1", "idea 1", iRProfesor1);
		iRIdeaDAO.save(iRIdea1);
		
		// Testing
		
		ModelAndView modelAndView = ideaController.deleteIdea(iRIdea1.getId());
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRIdea1 = iRIdeaDAO.find(iRIdea1.getId());
		
		assertTrue(iRIdea1.isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteIdea_Null () {
		
		// Testing
		
		ModelAndView modelAndView = ideaController.deleteIdea(0);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
}