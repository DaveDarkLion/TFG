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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IAdministradorDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAdministrador;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IAdministradorDAOTest extends ATest {

	@Autowired
	private IAdministradorDAO iAdministradorDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IAdministrador iAdministrador1;
	private IAdministrador iAdministrador2;
	
	@Before
	public void before () {
		
		iPersona1 = MFactory.newIPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iPersona2 = MFactory.newIPersona("alores@gmail.com", "Alejandro", "Lores", "Ibañez", "1234");
		
		iPersonaDAO.save(iPersona1);
		iPersonaDAO.save(iPersona2);
		
		iAdministrador1 = MFactory.newIAdministrador(iPersona1);
		iAdministrador2 = MFactory.newIAdministrador(iPersona2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
	
		String email = iAdministrador1.getIPersona().getEmail();
		
		assertNull(iAdministradorDAO.find(email));
		
		iAdministradorDAO.save(iAdministrador1);
		
		assertNotNull(iAdministradorDAO.find(email));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		String email = iAdministrador1.getIPersona().getEmail();
		
		iAdministradorDAO.save(iAdministrador1);
		
		assertEquals("Pablo", iAdministradorDAO.find(email).getIPersona().getNombre());
		
		iAdministrador1.getIPersona().setNombre("test");
		iAdministradorDAO.update(iAdministrador1);
		
		assertEquals("test", iAdministradorDAO.find(email).getIPersona().getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		String email = iAdministrador1.getIPersona().getEmail();
		
		iAdministradorDAO.save(iAdministrador1);
		
		assertNotNull(iAdministradorDAO.find(email));
		
		iAdministradorDAO.delete(iAdministrador1);
		
		assertNull(iAdministradorDAO.find(email));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iAdministradorDAO.getAll().size());
		
		iAdministradorDAO.save(iAdministrador1);
		iAdministradorDAO.save(iAdministrador2);
		
		assertEquals(2, iAdministradorDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		String email = iAdministrador1.getIPersona().getEmail();
		
		iAdministradorDAO.save(iAdministrador1);
		
		assertEquals(iAdministrador1, iAdministradorDAO.find(email));
		
	}
	
}
