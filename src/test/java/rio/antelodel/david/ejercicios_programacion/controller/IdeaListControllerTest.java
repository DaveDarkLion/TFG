package rio.antelodel.david.ejercicios_programacion.controller;

import static org.junit.Assert.assertEquals;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;

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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.IdeaListController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRIdea;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IdeaListControllerTest extends AControllerTest {

	// Names
	
	protected static final String IDEAS_DATA_NAME = "ideasData";
	protected static final String FILTER_IDEA_NAME = "filterIdea";
	protected static final String FILTER_SHOW_ALL_NAME = "filterShowAll";
	
	// Controllers
	
	@Autowired
	private IdeaListController ideaListController;
	
	// DAOs
	
	@Autowired
	private IRIdeaDAO iRIdeaDAO;
	
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	
	private IRIdea iRIdea1;
	private IRIdea iRIdea2;
	private IRIdea iRIdea3;
	
	private IRPersona iRPersona1;
	
	private IRProfesor iRProfesor1;
	private IRProfesor iRProfesor2;
	
	// Functions

	@Before
	public void before () {
		
		iRProfesor1 = iRProfesorDAO.find(loginAsProfesor());
		
		iRPersona1 = IRFactory.newIRPersona("persona 1", "persona 1", "persona 1", "persona 1", "1234");
		
		iRPersonaDAO.save(iRPersona1);
		
		iRProfesor2 = IRFactory.newIRProfesor(iRPersona1);
		
		iRProfesorDAO.save(iRProfesor2);
		
		iRIdea1 = IRFactory.newIRIdea("idea 1", "idea 1", iRProfesor1);
		iRIdea2 = IRFactory.newIRIdea("idea 2", "idea 2", iRProfesor2);
		iRIdea3 = IRFactory.newIRIdea("idea 3", "idea 3", iRProfesor1);
		
		iRIdeaDAO.save(iRIdea1);
		iRIdeaDAO.save(iRIdea2);
		iRIdeaDAO.save(iRIdea3);
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetIdeaListForm_1 () {
		
        // Testing
        
		ModelAndView modelAndView = ideaListController.getIdeaListForm("ide", null, Integer.toString(2), Integer.toString(1));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray data = model.getJSONArray(IDEAS_DATA_NAME);
		
		assertEquals("ide", model.get(FILTER_IDEA_NAME));
		assertEquals(2, model.getInt(PAGE_LAST_NAME));
		assertEquals(1, data.length());
		assertEquals(iRIdea3.getId(), data.getJSONObject(0).getInt("id"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetIdeaListForm_2 () {
		
        // Testing
        
		ModelAndView modelAndView = ideaListController.getIdeaListForm("ide", "true", Integer.toString(2), Integer.toString(1));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray data = model.getJSONArray(IDEAS_DATA_NAME);
		
		assertEquals("ide", model.get(FILTER_IDEA_NAME));
		assertEquals(3, model.getInt(PAGE_LAST_NAME));
		assertEquals(1, data.length());
		assertEquals(iRIdea2.getId(), data.getJSONObject(0).getInt("id"));
		
	}
		
}