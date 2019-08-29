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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPractica;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IPracticaDAOTest extends ATest {

	@Autowired
	private IPracticaDAO iPracticaDAO;
	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	private ITitulacion iTitulacion1;
	private ITitulacion iTitulacion2;
	
	private IPractica iPractica1;
	private IPractica iPractica2;
	
	@Before
	public void before () {
		
		iTitulacion1 = MFactory.newITitulacion("Titulacion 1");
		iTitulacion2 = MFactory.newITitulacion("Titulacion 2");
		
		iTitulacionDAO.save(iTitulacion1);
		iTitulacionDAO.save(iTitulacion2);
		
		iPractica1 = MFactory.newIPractica(1, 2016, "Practica de matematicas de la primera convocatoria", iTitulacion1, false, true);
		iPractica2 = MFactory.newIPractica(6, 2013, "Practica de quimica de la segunda convocatoria", iTitulacion2, true, false);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iPracticaDAO.getAll().size());
		
		iPracticaDAO.save(iPractica1);
		
		int id = iPractica1.getId();
		
		assertNotNull(iPracticaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iPracticaDAO.save(iPractica1);
		
		int id = iPractica1.getId();
		
		assertEquals(iTitulacion1, iPracticaDAO.find(id).getITitulacion());
		
		iPractica1.setITitulacion(iTitulacion2);
		iPracticaDAO.update(iPractica1);
		
		assertEquals(iTitulacion2, iPracticaDAO.find(id).getITitulacion());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iPracticaDAO.save(iPractica1);
		
		int id = iPractica1.getId();
		
		assertNotNull(iPracticaDAO.find(id));
		
		iPracticaDAO.delete(iPractica1);
		
		assertNull(iPracticaDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iPracticaDAO.getAll().size());
		
		iPracticaDAO.save(iPractica1);
		iPracticaDAO.save(iPractica2);
		
		assertEquals(2, iPracticaDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iPracticaDAO.save(iPractica1);
		
		int id = iPractica1.getId();
		
		assertEquals(iPractica1, iPracticaDAO.find(id));
		
	}
	
}
