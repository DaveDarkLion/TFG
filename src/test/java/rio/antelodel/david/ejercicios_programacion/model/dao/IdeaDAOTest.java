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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IIdeaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IIdea;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPersona;
import rio.antelodel.david.ejercicios_programacion.model.iface.IProfesor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IdeaDAOTest extends ATest {

	@Autowired
	private IIdeaDAO iIdeaDAO;
	@Autowired
	private IPersonaDAO iPersonaDAO;
	@Autowired
	private IProfesorDAO iProfesorDAO;
	
	private IPersona iPersona1;
	
	private IProfesor iProfesor1;
	
	private IIdea iIdea1;
	private IIdea iIdea2;
	
	@Before
	public void before () {
		
		iPersona1 = MFactory.newIPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		
		iPersonaDAO.save(iPersona1);
		
		iProfesor1 = MFactory.newIProfesor(iPersona1);
		
		iProfesorDAO.save(iProfesor1);
		
		iIdea1 = MFactory.newIIdea("Idea 1", "Idea 1", iProfesor1);
		iIdea2 = MFactory.newIIdea("Idea 2", "Idea 2", iProfesor1);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iIdeaDAO.getAll().size());
		
		iIdeaDAO.save(iIdea1);
		
		int id = iIdea1.getId();
		
		assertNotNull(iIdeaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iIdeaDAO.save(iIdea1);
		
		int id = iIdea1.getId();
		
		assertEquals("Idea 1", iIdeaDAO.find(id).getNombre());
		
		iIdea1.setNombre("test");
		iIdeaDAO.update(iIdea1);
		
		assertEquals("test", iIdeaDAO.find(id).getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iIdeaDAO.save(iIdea1);
		
		int id = iIdea1.getId();
		
		assertNotNull(iIdeaDAO.find(id));
		
		iIdeaDAO.delete(iIdea1);
		
		assertNull(iIdeaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iIdeaDAO.getAll().size());
		
		iIdeaDAO.save(iIdea1);
		iIdeaDAO.save(iIdea2);
		
		assertEquals(2, iIdeaDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iIdeaDAO.save(iIdea1);
		
		int id = iIdea1.getId();
		
		assertEquals(iIdea1, iIdeaDAO.find(id));
		
	}
	
}
