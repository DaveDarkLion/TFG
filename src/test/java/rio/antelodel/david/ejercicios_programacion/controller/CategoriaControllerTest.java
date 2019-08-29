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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.CategoriaController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class CategoriaControllerTest extends AControllerTest {

	// Names
	
	protected static final String IS_DIFICULTAD_NAME = "isDificultad";
	protected static final String DATA_NAME = "data";
	
	// Controllers
	
	@Autowired
	private CategoriaController categoriaController;
	
	// DAOs
	
	@Autowired
	private IRCategoriaDAO iRCategoriaDAO;
	
	// Functions
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetCategoriaEditForm_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRCategoria iRCategoria1 = IRFactory.newIRCategoria("categoria 1");
		iRCategoriaDAO.save(iRCategoria1);
	        
		// Testing
		
        ModelAndView modelAndView = categoriaController.getCategoriaEditForm(iRCategoria1.getId());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_DIFICULTAD_NAME));
        assertEquals(0, model.getInt(IS_NEW_NAME));
        assertEquals(1, model.getInt(CAN_DELETE_NAME));
        
        JSONObject data = model.getJSONObject(DATA_NAME);
        
        assertEquals(iRCategoria1.getNombre(), data.get("nombre"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetCategoriaEditForm_Error () {
		
		// Setup
		
		loginAsAdministrador();
	        
		// Testing
		
		ModelAndView modelAndView = categoriaController.getCategoriaEditForm(0);
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetCategoriaNewForm () {
	
		// Setup
		
		loginAsAdministrador();
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = categoriaController.getCategoriaNewForm();
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
	     assertEquals(0, model.getInt(IS_DIFICULTAD_NAME));
	     assertEquals(1, model.getInt(IS_NEW_NAME));
	     assertEquals(0, model.getInt(CAN_DELETE_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateCategoria_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = categoriaController.createCategoria("categoria 1");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRCategoria iRCategoria1 = iRCategoriaDAO.getAll().get(0);
		
		assertEquals("categoria 1", iRCategoria1.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateCategoria_Error () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = categoriaController.createCategoria(null);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateCategoria_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRCategoria iRCategoria1 = IRFactory.newIRCategoria("categoria 1");
		iRCategoriaDAO.save(iRCategoria1);

		// Testing
		
		ModelAndView modelAndView = categoriaController.updateCategoria(iRCategoria1.getId(), "categoria updated");
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRCategoria1 = iRCategoriaDAO.find(iRCategoria1.getId());
		
		assertEquals("categoria updated", iRCategoria1.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateCategoria_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = categoriaController.updateCategoria(0, "categoria updated 2");
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteCategoria_Normal () {
		
		// Setup
		
		loginAsAdministrador();
		
		IRCategoria iRCategoria1 = IRFactory.newIRCategoria("categoria 1");
		iRCategoriaDAO.save(iRCategoria1);
		
		// Testing
		
		ModelAndView modelAndView = categoriaController.deleteCategoria(iRCategoria1.getId());
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		iRCategoria1 = iRCategoriaDAO.find(iRCategoria1.getId());
		
		assertTrue(iRCategoria1.isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteCategoria_Null () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		ModelAndView modelAndView = categoriaController.deleteCategoria(0);
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
}