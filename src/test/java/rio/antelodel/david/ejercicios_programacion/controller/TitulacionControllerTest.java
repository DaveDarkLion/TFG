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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.TitulacionController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class TitulacionControllerTest extends AControllerTest {

	// Names
	
	protected static final String IS_DIFICULTAD_NAME = "isDificultad";
	protected static final String DATA_NAME = "data";
	
	// Controllers
	
	@Autowired
	private TitulacionController titulacionController;
	
	// DAOs
	
	@Autowired
	private IRTitulacionDAO iRTitulacionDAO;
	
	// Functions
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetTitulacionEditForm_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRTitulacion iRTitulacion1 = IRFactory.newIRTitulacion("titulacion 1");
		iRTitulacionDAO.save(iRTitulacion1);
	        
		// Testing
		
        ModelAndView modelAndView = titulacionController.getTitulacionEditForm(iRTitulacion1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_DIFICULTAD_NAME));
        assertEquals(0, model.getInt(IS_NEW_NAME));
        assertEquals(1, model.getInt(CAN_DELETE_NAME));
        
        JSONObject data = model.getJSONObject(DATA_NAME);
        
        assertEquals(iRTitulacion1.getNombre(), data.get("nombre"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetTitulacionEditForm_Error () {
		
		// Setup
		
		loginAsAdministrador();
	        
		// Testing
		
		ModelAndView modelAndView = titulacionController.getTitulacionEditForm(0);
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetTitulacionNewForm () {
	
		// Setup
		
		loginAsAdministrador();
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = titulacionController.getTitulacionNewForm();
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
	     assertEquals(0, model.getInt(IS_DIFICULTAD_NAME));
	     assertEquals(1, model.getInt(IS_NEW_NAME));
	     assertEquals(0, model.getInt(CAN_DELETE_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateTitulacion_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = titulacionController.createTitulacion("titulacion 1");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRTitulacion iRTitulacion1 = iRTitulacionDAO.getAll().get(0);
		
		assertEquals("titulacion 1", iRTitulacion1.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateTitulacion_Error () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = titulacionController.createTitulacion(null);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateTitulacion_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRTitulacion iRTitulacion1 = IRFactory.newIRTitulacion("titulacion 1");
		iRTitulacionDAO.save(iRTitulacion1);

		// Testing
		
		ModelAndView modelAndView = titulacionController.updateTitulacion(iRTitulacion1.getId(), "titulacion updated");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRTitulacion1 = iRTitulacionDAO.find(iRTitulacion1.getId());
		
		assertEquals("titulacion updated", iRTitulacion1.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateTitulacion_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = titulacionController.updateTitulacion(0, "titulacion updated 2");
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteTitulacion_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRTitulacion iRTitulacion1 = IRFactory.newIRTitulacion("titulacion 1");
		iRTitulacionDAO.save(iRTitulacion1);
		
		// Testing
		
		ModelAndView modelAndView = titulacionController.deleteTitulacion(iRTitulacion1.getId());
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRTitulacion1 = iRTitulacionDAO.find(iRTitulacion1.getId());
		
		assertTrue(iRTitulacion1.isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteTitulacion_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = titulacionController.deleteTitulacion(0);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
}