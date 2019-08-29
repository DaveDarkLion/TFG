package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;

import static org.junit.Assert.assertEquals;

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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.UserListController;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
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
public class UserListControllerTest extends AControllerTest {

	// Names
	
	protected static final String USERS_DATA_NAME = "usersData";
	protected static final String FILTER_NAME = "filter";
	
	// Controllers
	
	@Autowired
	private UserListController userListController;
	
	// DAOs
	
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	@Autowired
	private IRAlumnoDAO iRAlumnoDAO;
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	@Autowired
	private IRAdministradorDAO iRAdministradorDAO;
	
	private IRAlumno iRAlumno1;
	private IRProfesor iRProfesor1;
	private IRAdministrador iRAdministrador1;
	
	@Before
	public void before () {
		
		loginAsAdministrador();
		
		IRPersona iRPersona1 = IRFactory.newIRPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		IRPersona iRPersona2 = IRFactory.newIRPersona("alores@gmail.com", "Alejandro", "Lores", "Ibañez", "1234");
		IRPersona iRPersona3 = IRFactory.newIRPersona("eviñales@gmail.com", "Eduardo", "Viñales", "Casar", "1234");
		
		iRPersonaDAO.save(iRPersona1);
		iRPersonaDAO.save(iRPersona2);
		iRPersonaDAO.save(iRPersona3);
		
		iRAlumno1 = IRFactory.newIRAlumno(iRPersona1);
		iRProfesor1 = IRFactory.newIRProfesor(iRPersona2);
		iRAdministrador1 = IRFactory.newIRAdministrador(iRPersona3);
		
		iRAlumnoDAO.save(iRAlumno1);
		iRProfesorDAO.save(iRProfesor1);
		iRAdministradorDAO.save(iRAdministrador1);
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserListForm_Alumno () {
		
		// Testing
		
		ModelAndView modelAndView = userListController.getUserListForm("", Role.ALUMNO, Integer.toString(1), Integer.toString(3));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray usersData = model.getJSONArray(USERS_DATA_NAME);
		
		assertEquals(1, usersData.length());
		assertEquals(iRAlumno1.getEmail(), usersData.getJSONObject(0).getJSONObject("persona").get("email"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserListForm_Profesor () {
		
		// Testing
		
		ModelAndView modelAndView = userListController.getUserListForm("jan", Role.PROFESOR, Integer.toString(1), Integer.toString(3));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray usersData = model.getJSONArray(USERS_DATA_NAME);
		
		assertEquals(1, usersData.length());
		assertEquals(iRProfesor1.getEmail(), usersData.getJSONObject(0).getJSONObject("persona").get("email"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserListForm_Administrador () {
		
		// Testing
		
		ModelAndView modelAndView = userListController.getUserListForm(null, Role.ADMIN, Integer.toString(1), Integer.toString(3));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray usersData = model.getJSONArray(USERS_DATA_NAME);
		
		assertEquals(2, usersData.length());
		assertEquals(iRAdministrador1.getEmail(), usersData.getJSONObject(1).getJSONObject("persona").get("email"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserListForm_FakeRole () {
		
		// Testing
		
		ModelAndView modelAndView = userListController.getUserListForm(null, "fake role", Integer.toString(1), Integer.toString(3));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray usersData = model.getJSONArray(USERS_DATA_NAME);
		
		assertEquals(3, usersData.length());
		assertEquals(iRProfesor1.getEmail(), usersData.getJSONObject(0).get("email"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetUserListForm_Nullole () {
		
		// Testing
		
		ModelAndView modelAndView = userListController.getUserListForm(null, null, Integer.toString(1), Integer.toString(3));
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		JSONArray usersData = model.getJSONArray(USERS_DATA_NAME);
		
		assertEquals(3, usersData.length());
		assertEquals(iRProfesor1.getEmail(), usersData.getJSONObject(0).get("email"));
		
	}
		
}