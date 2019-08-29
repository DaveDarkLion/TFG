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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IArchivoDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IArchivo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IArchivoDAOTest extends ATest {

	@Autowired
	private IArchivoDAO iArchivoDAO;
	
	private IArchivo iArchivo1;
	private IArchivo iArchivo2;
	
	@Before
	public void before () {
		
		iArchivo1 = MFactory.newIArchivo("Archivo 1");
		iArchivo2 = MFactory.newIArchivo("Archivo 2");
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iArchivoDAO.getAll().size());
		
		iArchivoDAO.save(iArchivo1);
		
		int id = iArchivo1.getId();
		
		assertNotNull(iArchivoDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iArchivoDAO.save(iArchivo1);
		
		int id = iArchivo1.getId();
		
		assertEquals("Archivo 1", iArchivoDAO.find(id).getRuta());
		
		iArchivo1.setRuta("test");
		iArchivoDAO.update(iArchivo1);
		
		assertEquals("test", iArchivoDAO.find(id).getRuta());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iArchivoDAO.save(iArchivo1);
		
		int id = iArchivo1.getId();
		
		assertNotNull(iArchivoDAO.find(id));
		
		iArchivoDAO.delete(iArchivo1);
		
		assertNull(iArchivoDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iArchivoDAO.getAll().size());
		
		iArchivoDAO.save(iArchivo1);
		iArchivoDAO.save(iArchivo2);
		
		assertEquals(2, iArchivoDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iArchivoDAO.save(iArchivo1);
		
		int id = iArchivo1.getId();
		
		assertEquals(iArchivo1, iArchivoDAO.find(id));
		
	}
	
}
