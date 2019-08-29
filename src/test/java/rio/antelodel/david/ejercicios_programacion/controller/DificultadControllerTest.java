package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.DificultadController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class DificultadControllerTest extends AControllerTest {

	// Names
	
	protected static final String IS_DIFICULTAD_NAME = "isDificultad";
	protected static final String DATA_NAME = "data";
	
	// Controllers
	
	@Autowired
	private DificultadController dificultadController;
	
	// DAOs
	
	@Autowired
	private IRDificultadDAO iRDificultadDAO;
	
	// Functions
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDificultadEditForm_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRDificultad iRDificultad1 = IRFactory.newIRDificultad("dificultad 1", 5f);
		iRDificultadDAO.save(iRDificultad1);
	        
		// Testing
		
        ModelAndView modelAndView = dificultadController.getDificultadEditForm(iRDificultad1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(1, model.getInt(IS_DIFICULTAD_NAME));
        assertEquals(0, model.getInt(IS_NEW_NAME));
        assertEquals(1, model.getInt(CAN_DELETE_NAME));
        
        JSONObject data = model.getJSONObject(DATA_NAME);
        
        assertEquals(iRDificultad1.getNombre(), data.get("nombre"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDificultadEditForm_Error () {
		
		// Setup
		
		loginAsAdministrador();
	        
		// Testing
		
		ModelAndView modelAndView = dificultadController.getDificultadEditForm(0);
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDificultadNewForm () {
	
		// Setup
		
		loginAsAdministrador();
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = dificultadController.getDificultadNewForm();
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
	     assertEquals(1, model.getInt(IS_DIFICULTAD_NAME));
	     assertEquals(1, model.getInt(IS_NEW_NAME));
	     assertEquals(0, model.getInt(CAN_DELETE_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateDificultad_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = dificultadController.createDificultad("dificultad 1", "5");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRDificultad iRDificultad1 = iRDificultadDAO.getAll().get(0);
		
		assertEquals("dificultad 1", iRDificultad1.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateDificultad_Error () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = dificultadController.createDificultad(null, null);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateDificultad_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRDificultad iRDificultad1 = IRFactory.newIRDificultad("dificultad 1", 5f);
		iRDificultadDAO.save(iRDificultad1);

		// Testing
		
		ModelAndView modelAndView = dificultadController.updateDificultad(iRDificultad1.getId(), "dificultad updated", "0");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRDificultad1 = iRDificultadDAO.find(iRDificultad1.getId());
		
		assertEquals("dificultad updated", iRDificultad1.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateDificultad_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = dificultadController.updateDificultad(0, "dificultad updated 2", "0");
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteDificultad_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRDificultad iRDificultad1 = IRFactory.newIRDificultad("dificultad 1", 5f);
		iRDificultadDAO.save(iRDificultad1);
		
		// Testing
		
		ModelAndView modelAndView = dificultadController.deleteDificultad(iRDificultad1.getId());
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRDificultad1 = iRDificultadDAO.find(iRDificultad1.getId());
		
		assertTrue(iRDificultad1.isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteDificultad_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = dificultadController.deleteDificultad(0);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
}