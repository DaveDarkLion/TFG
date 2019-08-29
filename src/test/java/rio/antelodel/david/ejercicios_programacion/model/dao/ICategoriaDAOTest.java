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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ICategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.ICategoria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class ICategoriaDAOTest extends ATest {

	@Autowired
	private ICategoriaDAO iCategoriaDAO;
	
	private ICategoria iCategoria1;
	private ICategoria iCategoria2;
	
	@Before
	public void before () {
		
		iCategoria1 = MFactory.newICategoria("Categoria 1");
		iCategoria2 = MFactory.newICategoria("Categoria 2");
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iCategoriaDAO.getAll().size());
		
		iCategoriaDAO.save(iCategoria1);
		
		int id = iCategoria1.getId();
		
		assertNotNull(iCategoriaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iCategoriaDAO.save(iCategoria1);
		
		int id = iCategoria1.getId();
		
		assertEquals("Categoria 1", iCategoriaDAO.find(id).getNombre());
		
		iCategoria1.setNombre("test");
		iCategoriaDAO.update(iCategoria1);
		
		assertEquals("test", iCategoriaDAO.find(id).getNombre());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iCategoriaDAO.save(iCategoria1);
		
		int id = iCategoria1.getId();
		
		assertNotNull(iCategoriaDAO.find(id));
		
		iCategoriaDAO.delete(iCategoria1);
		
		assertNull(iCategoriaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iCategoriaDAO.getAll().size());
		
		iCategoriaDAO.save(iCategoria1);
		iCategoriaDAO.save(iCategoria2);
		
		assertEquals(2, iCategoriaDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iCategoriaDAO.save(iCategoria1);
		
		int id = iCategoria1.getId();
		
		assertEquals(iCategoria1, iCategoriaDAO.find(id));
		
	}
	
}
