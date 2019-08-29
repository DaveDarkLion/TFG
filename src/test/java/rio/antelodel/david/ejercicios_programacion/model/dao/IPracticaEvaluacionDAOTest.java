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
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.IPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.dao.iface.ITitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.model.factory.MFactory;
import rio.antelodel.david.ejercicios_programacion.model.iface.IPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.model.iface.ITitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class IPracticaEvaluacionDAOTest extends ATest {

	@Autowired
	private IPracticaEvaluacionDAO iPracticaEvaluacionDAO;
	@Autowired
	private ITitulacionDAO iTitulacionDAO;
	
	private ITitulacion iTitulacion1;
	private ITitulacion iTitulacion2;
	
	private IPracticaEvaluacion iPracticaEvaluacion1;
	private IPracticaEvaluacion iPracticaEvaluacion2;
	
	@Before
	public void before () {
		
		iTitulacion1 = MFactory.newITitulacion("Titulacion 1");
		iTitulacion2 = MFactory.newITitulacion("Titulacion 2");
		
		iTitulacionDAO.save(iTitulacion1);
		iTitulacionDAO.save(iTitulacion2);
		
		iPracticaEvaluacion1 = MFactory.newIPracticaEvaluacion(1, 2016, "PracticaEvaluacion de matematicas de la primera convocatoria", iTitulacion1, false, true);
		iPracticaEvaluacion2 = MFactory.newIPracticaEvaluacion(6, 2013, "PracticaEvaluacion de quimica de la segunda convocatoria", iTitulacion2, true, false);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testSave () {
		
		assertEquals(0, iPracticaEvaluacionDAO.getAll().size());
		
		iPracticaEvaluacionDAO.save(iPracticaEvaluacion1);
		
		int id = iPracticaEvaluacion1.getId();
		
		assertNotNull(iPracticaEvaluacionDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdate () {
		
		iPracticaEvaluacionDAO.save(iPracticaEvaluacion1);
		
		int id = iPracticaEvaluacion1.getId();
		
		assertEquals(iTitulacion1, iPracticaEvaluacionDAO.find(id).getITitulacion());
		
		iPracticaEvaluacion1.setITitulacion(iTitulacion2);
		iPracticaEvaluacionDAO.update(iPracticaEvaluacion1);
		
		assertEquals(iTitulacion2, iPracticaEvaluacionDAO.find(id).getITitulacion());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDelete () {
		
		iPracticaEvaluacionDAO.save(iPracticaEvaluacion1);
		
		int id = iPracticaEvaluacion1.getId();
		
		assertNotNull(iPracticaEvaluacionDAO.find(id));
		
		iPracticaEvaluacionDAO.delete(iPracticaEvaluacion1);
		
		assertNull(iPracticaEvaluacionDAO.find(id));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetAll () {
		
		assertEquals(0, iPracticaEvaluacionDAO.getAll().size());
		
		iPracticaEvaluacionDAO.save(iPracticaEvaluacion1);
		iPracticaEvaluacionDAO.save(iPracticaEvaluacion2);
		
		assertEquals(2, iPracticaEvaluacionDAO.getAll().size());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testFind () {
		
		iPracticaEvaluacionDAO.save(iPracticaEvaluacion1);
		
		int id = iPracticaEvaluacion1.getId();
		
		assertEquals(iPracticaEvaluacion1, iPracticaEvaluacionDAO.find(id));
		
	}
	
}
