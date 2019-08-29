package rio.antelodel.david.ejercicios_programacion.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.ArchivoController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRArchivoDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRArchivo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class ArchivoControllerTest extends AControllerTest {

	String testContent = "testing";
	
	@Autowired
	private ArchivoController archivoController;
	
	@Autowired
	private IRArchivoDAO iRArchivoDAO;
	
	private IRArchivo iRArchivo1;
	
	@Before
	public void before () {
		
		File file = new File("file.txt");
		
		try {
			
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			
			fileWriter.write(testContent);
			fileWriter.close();
			
		}
		
		catch (Exception e) { e.printStackTrace(); }
		
		iRArchivo1 = IRFactory.newIRArchivo(file.getPath());
		iRArchivoDAO.save(iRArchivo1);
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetArchivo_Normal () {
		
		loginAsAdministrador();
			
		HttpEntity<byte[]> result = archivoController.getArchivoFile(iRArchivo1.getId());
		assertEquals(testContent, new String(result.getBody()));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetArchivo_Null () {
		
		loginAsAdministrador();
		
		HttpEntity<byte[]> result = archivoController.getArchivoFile(iRArchivo1.getId() + 1);
		assertNull(result);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetArchivo_Null_NotPermission () {
		
		loginAsAlumno();
			
		HttpEntity<byte[]> result = archivoController.getArchivoFile(iRArchivo1.getId());
		assertNull(result);
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetArchivo_Null_NoFile () {
		
		loginAsAdministrador();
		
		IRArchivo iRArchivo2 = IRFactory.newIRArchivo("fake ruta");
		iRArchivoDAO.save(iRArchivo2);
		
		assertNull(archivoController.getArchivoFile(iRArchivo2.getId()));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetArchivo_Null_NoArchivo () {
		
		loginAsAdministrador();
		
		assertNull(archivoController.getArchivoFile(iRArchivo1.getId() + 1));
		
	}
	
}