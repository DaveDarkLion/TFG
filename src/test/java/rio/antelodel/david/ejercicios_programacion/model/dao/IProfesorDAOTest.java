package rio.antelodel.david.ejercicios_programacion.model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IProfesorDAOTest extends ATest {

	@Autowired
	private IProfesorDAO iProfesorDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IProfesor iProfesor1;
	private IProfesor iProfesor2;
	
	@Before
	public void before () {
		
		iPersona1 = MFactory.newIPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iPersona2 = MFactory.newIPersona("alores@gmail.com", "Alejandro", "Lores", "Ibañez", "1234");
		
		iPersonaDAO.save(iPersona1);
		iPersonaDAO.save(iPersona2);
		
		iProfesor1 = MFactory.newIProfesor(iPersona1);
		iProfesor2 = MFactory.newIProfesor(iPersona2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
	
		String email = iProfesor1.getIPersona().getEmail();
		
		assertNull(iProfesorDAO.find(email));
		
		iProfesorDAO.save(iProfesor1);
		
		assertNotNull(iProfesorDAO.find(email));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		String email = iProfesor1.getIPersona().getEmail();
		
		iProfesorDAO.save(iProfesor1);
		
		assertEquals("Pablo", iProfesorDAO.find(email).getIPersona().getNombre());
		
		iProfesor1.getIPersona().setNombre("test");
		iProfesorDAO.update(iProfesor1);
		
		assertEquals("test", iProfesorDAO.find(email).getIPersona().getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		String email = iProfesor1.getIPersona().getEmail();
		
		iProfesorDAO.save(iProfesor1);
		
		assertNotNull(iProfesorDAO.find(email));
		
		iProfesorDAO.delete(iProfesor1);
		
		assertNull(iProfesorDAO.find(email));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iProfesorDAO.getAll().size());
		
		iProfesorDAO.save(iProfesor1);
		iProfesorDAO.save(iProfesor2);
		
		assertEquals(2, iProfesorDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		String email = iProfesor1.getIPersona().getEmail();
		
		iProfesorDAO.save(iProfesor1);
		
		assertEquals(iProfesor1, iProfesorDAO.find(email));
		
	}
	
}
