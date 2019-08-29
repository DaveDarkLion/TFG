package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.UserController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.controller.utility.user_mail_handler.IUserMailHandler;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAdministrador;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRAlumno;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class UserControllerTest extends AControllerTest {

	@Mock
	private IUserMailHandler iUserMailHandler;
	
	// Names
	
	protected static final String DATA_NAME = "data";
	
	// Controllers
	
	@Autowired
	private UserController userController;
	
	// DAOs
	
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	@Autowired
	private IRAlumnoDAO iRAlumnoDAO;
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	@Autowired
	private IRAdministradorDAO iRAdministradorDAO;
	
	private IRPersona iRPersonaMe;
	private IRPersona iRPersona1;
	
	// Functions
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		iRPersonaMe = iRPersonaDAO.find(loginAsTestUser(Arrays.asList(Role.ALUMNO, Role.PROFESOR, Role.ADMIN)));
		
		userController.setIUserMailHandler(iUserMailHandler);
		
		iRPersona1 = IRFactory.newIRPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserEditForm_Normal_AllRoles () {
		
		// Setup
		
		iRPersonaDAO.save(iRPersona1);
		
		IRAlumno iRAlumno1 = IRFactory.newIRAlumno(iRPersona1);
		iRAlumnoDAO.save(iRAlumno1);
		IRProfesor iRProfesor1 = IRFactory.newIRProfesor(iRPersona1);
		iRProfesorDAO.save(iRProfesor1);
		IRAdministrador iRAdministrador1 = IRFactory.newIRAdministrador(iRPersona1);
		iRAdministradorDAO.save(iRAdministrador1);
		
		iRPersona1.getEntity().setIAlumno(iRAlumno1.getEntity());
		iRPersona1.getEntity().setIProfesor(iRProfesor1.getEntity());
		iRPersona1.getEntity().setIAdministrador(iRAdministrador1.getEntity());
		
		iRPersonaDAO.update(iRPersona1);
		
		// Testing
		
        ModelAndView modelAndView = userController.getUserEditForm(iRPersona1.getEmail());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_NEW_NAME));
        
        JSONObject data = model.getJSONObject(DATA_NAME);
        
        assertEquals(iRPersona1.getNombre(), data.get("nombre"));
        assertEquals(3, model.getJSONArray(ROLES_CURRENT_DATA_NAME).length());
		assertEquals(Role.PROFESOR, model.getJSONArray(ROLES_CURRENT_DATA_NAME).getJSONObject(1).get("id"));
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserEditForm_Normal_NoRoles () {
		
		// Setup
		
		iRPersonaDAO.save(iRPersona1);
	        
		// Testing
		
        ModelAndView modelAndView = userController.getUserEditForm(iRPersona1.getEmail());
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(0, model.getInt(IS_NEW_NAME));
        
        JSONObject data = model.getJSONObject(DATA_NAME);
        
        assertEquals(iRPersona1.getNombre(), data.get("nombre"));
        assertEquals(0, model.getJSONArray(ROLES_CURRENT_DATA_NAME).length());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserEditForm_Error () {
	        
		// Testing
		
		ModelAndView modelAndView = userController.getUserEditForm("fake email");
        
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserNewForm () {
	
		// Setup
		
		ModelAndView modelAndView;
		JSONObject model;
		
		// Testing
		
		modelAndView = userController.getUserNewForm();
		model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
	     assertEquals(1, model.getInt(IS_NEW_NAME));
        
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateUser_Normal () {
		
		// Testing
		
		ModelAndView modelAndView = userController.createUser("test user 1", "test user 1", "test user 1", "test user 1", new String [] {Role.PROFESOR, Role.ADMIN});
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRPersona iRPersona1 = iRPersonaDAO.find("test user 1");
		
		assertEquals("test user 1", iRPersona1.getNombre());
		
		assertTrue(iRAlumnoDAO.find(iRPersona1.getEmail()).isNull());
		assertFalse(iRProfesorDAO.find(iRPersona1.getEmail()).isNull());
		assertFalse(iRAdministradorDAO.find(iRPersona1.getEmail()).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateUser_Error_Null () {
		
		// Testing
		
		ModelAndView modelAndView = userController.createUser(null, "test user 1", "test user 1", "test user 1", new String [] {Role.PROFESOR, Role.ADMIN});
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateUser_Error_NoLength () {
		
		// Testing
		
		ModelAndView modelAndView = userController.createUser("", "test user 1", "test user 1", "test user 1", new String [] {Role.PROFESOR, Role.ADMIN});
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateUser_Normal_AllRoles () {

		// Testing
		
		ModelAndView modelAndView = userController.updateUser(iRPersonaMe.getEmail(), "test user updated", "test user updated", "test user updated", new String [] {Role.ALUMNO, Role.PROFESOR, Role.ADMIN});
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRPersona iRPersonaTest = iRPersonaDAO.find(iRPersonaMe.getEmail());
		
		assertFalse(iRAlumnoDAO.find(iRPersonaMe.getEmail()).isNull());
		assertFalse(iRProfesorDAO.find(iRPersonaMe.getEmail()).isNull());
		assertFalse(iRAdministradorDAO.find(iRPersonaMe.getEmail()).isNull());
		
		assertEquals("test user updated", iRPersonaTest.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateUser_Normal_NoRoles () {

		// Testing
		
		ModelAndView modelAndView = userController.updateUser(iRPersonaMe.getEmail(), "test user updated", "test user updated", "test user updated", new String [] {});
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		IRPersona iRPersonaTest = iRPersonaDAO.find(iRPersonaMe.getEmail());
		
		assertTrue(iRAlumnoDAO.find(iRPersonaMe.getEmail()).isNull());
		assertTrue(iRProfesorDAO.find(iRPersonaMe.getEmail()).isNull());
		assertTrue(iRAdministradorDAO.find(iRPersonaMe.getEmail()).isNull());
		
		assertEquals("test user updated", iRPersonaTest.getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateUser_Null () {
		
		// Testing
		
		ModelAndView modelAndView = userController.updateUser("fakeemail", "test user updated", "test user updated", "test user updated", new String [] {});
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteUser_Normal () {
		
		// Setup
		
		iRPersonaDAO.save(iRPersona1);
		
		// Testing
		
		ModelAndView modelAndView = userController.deleteUser(iRPersona1.getEmail());
		
		assertEquals(VIEW_MESSAGE, modelAndView.getViewName());
		
		assertTrue(iRPersonaDAO.find(iRPersona1.getEmail()).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteUser_Null () {
		
		// Testing
		
		ModelAndView modelAndView = userController.deleteUser("fakeemail");
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
}