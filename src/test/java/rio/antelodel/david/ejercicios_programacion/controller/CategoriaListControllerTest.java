package rio.antelodel.david.ejercicios_programacion.controller;

import static org.junit.Assert.assertEquals;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.MODEL_NAME;

import org.json.JSONArray;
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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.CategoriaListController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class CategoriaListControllerTest extends AControllerTest {

	// Names
	
	protected static final String DATA_NAME = "data";
	protected static final String NOMBRE_NAME = "nombre";
	protected static final String FILTER_NAME = "filter";
	protected static final String LINK_NOMBRE_NAME = "linkName";
	
	// Controllers
	
	@Autowired
	private CategoriaListController categoriaListController;
	
	// DAOs
	
	@Autowired
	private IRCategoriaDAO iRCategoriaDAO;
	
	private IRCategoria iRCategoria1;
	private IRCategoria iRCategoria2;
	private IRCategoria iRCategoria3;
	
	// Functions

	@Before
	public void before () {
		
		iRCategoria1 = IRFactory.newIRCategoria("categoria 1");
		iRCategoria2 = IRFactory.newIRCategoria("categoria 2");
		iRCategoria3 = IRFactory.newIRCategoria("categoria 3");
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetCategoriaListForm () {
		
		// Setup
		
		loginAsAdministrador();
		
		iRCategoriaDAO.save(iRCategoria1);
		iRCategoriaDAO.save(iRCategoria2);
		iRCategoriaDAO.save(iRCategoria3);
		
        // Testing
        
		ModelAndView modelAndView = categoriaListController.getCategoriaListForm("cat", Integer.toString(2), Integer.toString(1));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray data = model.getJSONArray(DATA_NAME);
		
		assertEquals("categorías", model.get(NOMBRE_NAME));
		assertEquals("categorias", model.get(LINK_NOMBRE_NAME));
		assertEquals("cat", model.get(FILTER_NAME));
		assertEquals(1, data.length());
		assertEquals(iRCategoria2.getId(), data.getJSONObject(0).getInt("id"));
		
	}
		
}