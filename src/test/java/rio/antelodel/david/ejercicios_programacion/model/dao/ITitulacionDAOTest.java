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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class ITitulacionDAOTest extends ATest {

	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	private ITitulacion iTitulacion1;
	private ITitulacion iTitulacion2;
	
	@Before
	public void before () {
		
		iTitulacion1 = MFactory.newITitulacion("Titulacion 1");
		iTitulacion2 = MFactory.newITitulacion("Titulacion 2");
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iTitulacionDAO.getAll().size());
		
		iTitulacionDAO.save(iTitulacion1);
		
		int id = iTitulacion1.getId();
		
		assertNotNull(iTitulacionDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iTitulacionDAO.save(iTitulacion1);
		
		int id = iTitulacion1.getId();
		
		assertEquals("Titulacion 1", iTitulacionDAO.find(id).getNombre());
		
		iTitulacion1.setNombre("test");
		iTitulacionDAO.update(iTitulacion1);
		
		assertEquals("test", iTitulacionDAO.find(id).getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iTitulacionDAO.save(iTitulacion1);
		
		int id = iTitulacion1.getId();
		
		assertNotNull(iTitulacionDAO.find(id));
		
		iTitulacionDAO.delete(iTitulacion1);
		
		assertNull(iTitulacionDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iTitulacionDAO.getAll().size());
		
		iTitulacionDAO.save(iTitulacion1);
		iTitulacionDAO.save(iTitulacion2);
		
		assertEquals(2, iTitulacionDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iTitulacionDAO.save(iTitulacion1);
		
		int id = iTitulacion1.getId();
		
		assertEquals(iTitulacion1, iTitulacionDAO.find(id));
		
	}
	
}
