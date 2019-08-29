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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IDificultad;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IDificultadDAOTest extends ATest {

	@Autowired
	private IDificultadDAO iDificultadDAO;
	
	private IDificultad iDificultad1;
	private IDificultad iDificultad2;
	
	@Before
	public void before () {
		
		iDificultad1 = MFactory.newIDificultad("Dificultad 1", 2.5f);
		iDificultad2 = MFactory.newIDificultad("Dificultad 2", 7.5f);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iDificultadDAO.getAll().size());
		
		iDificultadDAO.save(iDificultad1);
		
		int id = iDificultad1.getId();
		
		assertNotNull(iDificultadDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iDificultadDAO.save(iDificultad1);
		
		int id = iDificultad1.getId();
		
		assertEquals("Dificultad 1", iDificultadDAO.find(id).getNombre());
		
		iDificultad1.setNombre("test");
		iDificultadDAO.update(iDificultad1);
		
		assertEquals("test", iDificultadDAO.find(id).getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iDificultadDAO.save(iDificultad1);
		
		int id = iDificultad1.getId();
		
		assertNotNull(iDificultadDAO.find(id));
		
		iDificultadDAO.delete(iDificultad1);
		
		assertNull(iDificultadDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iDificultadDAO.getAll().size());
		
		iDificultadDAO.save(iDificultad1);
		iDificultadDAO.save(iDificultad2);
		
		assertEquals(2, iDificultadDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iDificultadDAO.save(iDificultad1);
		
		int id = iDificultad1.getId();
		
		assertEquals(iDificultad1, iDificultadDAO.find(id));
		
	}
	
}
