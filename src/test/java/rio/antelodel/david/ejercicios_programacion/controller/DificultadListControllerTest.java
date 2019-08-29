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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.DificultadListController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class DificultadListControllerTest extends AControllerTest {

	// Names
	
	protected static final String DATA_NAME = "data";
	protected static final String NOMBRE_NAME = "nombre";
	protected static final String FILTER_NAME = "filter";
	protected static final String LINK_NOMBRE_NAME = "linkName";
	
	// Controllers
	
	@Autowired
	private DificultadListController dificultadListController;
	
	// DAOs
	
	@Autowired
	private IRDificultadDAO iRDificultadDAO;
	
	private IRDificultad iRDificultad1;
	private IRDificultad iRDificultad2;
	private IRDificultad iRDificultad3;
	
	// Functions

	@Before
	public void before () {
		
		iRDificultad1 = IRFactory.newIRDificultad("dificultad 1", 2f);
		iRDificultad2 = IRFactory.newIRDificultad("dificultad 2", 5f);
		iRDificultad3 = IRFactory.newIRDificultad("dificultad 3", 8f);
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDificultadListForm () {
		
		// Setup
		
		loginAsAdministrador();
		
		iRDificultadDAO.save(iRDificultad1);
		iRDificultadDAO.save(iRDificultad2);
		iRDificultadDAO.save(iRDificultad3);
		
        // Testing
        
		ModelAndView modelAndView = dificultadListController.getDificultadListForm("dif", Integer.toString(2), Integer.toString(1));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray data = model.getJSONArray(DATA_NAME);
		
		assertEquals("dificultades", model.get(NOMBRE_NAME));
		assertEquals("dificultades", model.get(LINK_NOMBRE_NAME));
		assertEquals("dif", model.get(FILTER_NAME));
		assertEquals(1, data.length());
		assertEquals(iRDificultad2.getId(), data.getJSONObject(0).getInt("id"));
		
	}
		
}