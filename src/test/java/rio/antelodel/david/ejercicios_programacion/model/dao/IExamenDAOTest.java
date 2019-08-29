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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IExamenDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IExamen;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IExamenDAOTest extends ATest {

	@Autowired
	private IExamenDAO iExamenDAO;
	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	private ITitulacion iTitulacion1;
	private ITitulacion iTitulacion2;
	
	private IExamen iExamen1;
	private IExamen iExamen2;
	
	@Before
	public void before () {
		
		iTitulacion1 = MFactory.newITitulacion("Titulacion 1");
		iTitulacion2 = MFactory.newITitulacion("Titulacion 2");
		
		iTitulacionDAO.save(iTitulacion1);
		iTitulacionDAO.save(iTitulacion2);
		
		iExamen1 = MFactory.newIExamen(1, 2016, "Examen de matematicas de la primera convocatoria", iTitulacion1, false, true);
		iExamen2 = MFactory.newIExamen(6, 2013, "Examen de quimica de la segunda convocatoria", iTitulacion2, true, false);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iExamenDAO.getAll().size());
		
		iExamenDAO.save(iExamen1);
		
		int id = iExamen1.getId();
		
		assertNotNull(iExamenDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iExamenDAO.save(iExamen1);
		
		int id = iExamen1.getId();
		
		assertEquals(iTitulacion1, iExamenDAO.find(id).getITitulacion());
		
		iExamen1.setITitulacion(iTitulacion2);
		iExamenDAO.update(iExamen1);
		
		assertEquals(iTitulacion2, iExamenDAO.find(id).getITitulacion());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iExamenDAO.save(iExamen1);
		
		int id = iExamen1.getId();
		
		assertNotNull(iExamenDAO.find(id));
		
		iExamenDAO.delete(iExamen1);
		
		assertNull(iExamenDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iExamenDAO.getAll().size());
		
		iExamenDAO.save(iExamen1);
		iExamenDAO.save(iExamen2);
		
		assertEquals(2, iExamenDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iExamenDAO.save(iExamen1);
		
		int id = iExamen1.getId();
		
		assertEquals(iExamen1, iExamenDAO.find(id));
		
	}
	
}
