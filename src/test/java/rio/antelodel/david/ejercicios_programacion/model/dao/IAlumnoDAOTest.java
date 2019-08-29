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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IAlumnoDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IAlumno;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IAlumnoDAOTest extends ATest {

	@Autowired
	private IAlumnoDAO iAlumnoDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	
	private IPersona iPersona1;
	private IPersona iPersona2;
	
	private IAlumno iAlumno1;
	private IAlumno iAlumno2;
	
	@Before
	public void before () {
		
		iPersona1 = MFactory.newIPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iPersona2 = MFactory.newIPersona("alores@gmail.com", "Alejandro", "Lores", "Ibañez", "1234");
		
		iPersonaDAO.save(iPersona1);
		iPersonaDAO.save(iPersona2);
		
		iAlumno1 = MFactory.newIAlumno(iPersona1);
		iAlumno2 = MFactory.newIAlumno(iPersona2);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
	
		String email = iAlumno1.getIPersona().getEmail();
		
		assertNull(iAlumnoDAO.find(email));
		
		iAlumnoDAO.save(iAlumno1);
		
		assertNotNull(iAlumnoDAO.find(email));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		String email = iAlumno1.getIPersona().getEmail();
		
		iAlumnoDAO.save(iAlumno1);
		
		assertEquals(iAlumnoDAO.find(email).getIPersona().getNombre(), "Pablo");
		
		iAlumno1.getIPersona().setNombre("test");
		iAlumnoDAO.update(iAlumno1);
		
		assertEquals(iAlumnoDAO.find(email).getIPersona().getNombre(), "test");
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		String email = iAlumno1.getIPersona().getEmail();
		
		iAlumnoDAO.save(iAlumno1);
		
		assertNotNull(iAlumnoDAO.find(email));
		
		iAlumnoDAO.delete(iAlumno1);
		
		assertNull(iAlumnoDAO.find(email));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iAlumnoDAO.getAll().size());
		
		iAlumnoDAO.save(iAlumno1);
		iAlumnoDAO.save(iAlumno2);
		
		assertEquals(2, iAlumnoDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		String email = iAlumno1.getIPersona().getEmail();
		
		iAlumnoDAO.save(iAlumno1);
		
		assertEquals(iAlumno1, iAlumnoDAO.find(email));
		
	}
	
}
