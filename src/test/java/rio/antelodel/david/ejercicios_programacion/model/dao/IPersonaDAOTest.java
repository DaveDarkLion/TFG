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
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IPersonaDAOTest extends ATest {
	
	@Autowired
	private IPersonaDAO iPersonaDAO;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	@Before
	public void before () {
		
		iPersona1 = MFactory.newIPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iPersona2 = MFactory.newIPersona("alores@gmail.com", "Alejandro", "Lores", "Ibañez", "1234");
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
	
		String email = iPersona1.getEmail();
		
		assertNull(iPersonaDAO.find(email));
		
		iPersonaDAO.save(iPersona1);
		
		assertNotNull(iPersonaDAO.find(email));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		String email = iPersona1.getEmail();
		
		iPersonaDAO.save(iPersona1);
		
		assertEquals("Pablo", iPersonaDAO.find(email).getNombre());
		
		iPersona1.setNombre("test");
		iPersonaDAO.update(iPersona1);
		
		assertEquals("test", iPersonaDAO.find(email).getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		String email = iPersona1.getEmail();
		
		iPersonaDAO.save(iPersona1);
		
		assertNotNull(iPersonaDAO.find(email));
		
		iPersonaDAO.delete(iPersona1);
		
		assertNull(iPersonaDAO.find(email));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iPersonaDAO.getAll().size());
		
		iPersonaDAO.save(iPersona1);
		iPersonaDAO.save(iPersona2);
		
		assertEquals(2, iPersonaDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		String email = iPersona1.getEmail();
		
		iPersonaDAO.save(iPersona1);
		
		assertEquals(iPersona1, iPersonaDAO.find(email));
		
	}
	
}
