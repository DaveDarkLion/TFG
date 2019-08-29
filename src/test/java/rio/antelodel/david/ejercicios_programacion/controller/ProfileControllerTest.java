package rio.antelodel.david.ejercicios_programacion.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.MODEL_NAME;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.VIEW_ERROR;

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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.ProfileController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class ProfileControllerTest extends AControllerTest {
	
	protected static final String PASSWORD_WRONG_NAME = "wrongPassword";
	protected static final String PASSWORD_WRONG_FORMAT_NAME = "wrongPasswordFormat";
	protected static final String PASSWORDS_DO_NOT_MATCH_NAME = "passwordsDoNotMatch";
	
	protected static final String VIEW_PASSWORD = "/admin/user/user-password";
	
	@Autowired
	private ProfileController profileController;
	
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	
	private IRPersona iRPersonaMe;
	
	@Before
	public void before () {
		
		iRPersonaMe = iRPersonaDAO.find(loginAsAdministrador());
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testChangePassword_Normal () {
	
		ModelAndView modelAndView = profileController.changePassword(iRPersonaMe.getPassword(), "newpass", "newpass");
		
        assertNotEquals(VIEW_PASSWORD, modelAndView.getViewName());
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testChangePassword_Error_Everything () {
	
		ModelAndView modelAndView = profileController.changePassword("fake pass", "fake1", "fake2");
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
        assertEquals(VIEW_PASSWORD, modelAndView.getViewName());
        
        assertEquals(1, model.getInt(PASSWORD_WRONG_NAME));
        assertEquals(1, model.getInt(PASSWORD_WRONG_FORMAT_NAME));
        assertEquals(1, model.getInt(PASSWORDS_DO_NOT_MATCH_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testChangePassword_Error () {
	
		ModelAndView modelAndView = profileController.changePassword(iRPersonaMe.getPassword(), null, null);
		
        assertEquals(VIEW_ERROR, modelAndView.getViewName());
        
	}
	
}