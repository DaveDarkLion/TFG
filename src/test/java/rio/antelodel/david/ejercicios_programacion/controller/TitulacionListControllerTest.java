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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.TitulacionListController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class TitulacionListControllerTest extends AControllerTest {

	// Names
	
	protected static final String DATA_NAME = "data";
	protected static final String NOMBRE_NAME = "nombre";
	protected static final String FILTER_NAME = "filter";
	protected static final String LINK_NOMBRE_NAME = "linkName";
	
	// Controllers
	
	@Autowired
	private TitulacionListController titulacionListController;
	
	// DAOs
	
	@Autowired
	private IRTitulacionDAO iRTitulacionDAO;
	
	private IRTitulacion iRTitulacion1;
	private IRTitulacion iRTitulacion2;
	private IRTitulacion iRTitulacion3;
	
	// Functions

	@Before
	public void before () {
		
		iRTitulacion1 = IRFactory.newIRTitulacion("titulacion 1");
		iRTitulacion2 = IRFactory.newIRTitulacion("titulacion 2");
		iRTitulacion3 = IRFactory.newIRTitulacion("titulacion 3");
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetTitulacionListForm () {
		
		// Setup
		
		loginAsAdministrador();
		
		iRTitulacionDAO.save(iRTitulacion1);
		iRTitulacionDAO.save(iRTitulacion2);
		iRTitulacionDAO.save(iRTitulacion3);
		
        // Testing
        
		ModelAndView modelAndView = titulacionListController.getTitulacionListForm("titul", Integer.toString(2), Integer.toString(1));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray data = model.getJSONArray(DATA_NAME);
		
		assertEquals("titulaciones", model.get(NOMBRE_NAME));
		assertEquals("titulaciones", model.get(LINK_NOMBRE_NAME));
		assertEquals("titul", model.get(FILTER_NAME));
		assertEquals(1, data.length());
		assertEquals(iRTitulacion2.getId(), data.getJSONObject(0).getInt("id"));
		
	}
		
}