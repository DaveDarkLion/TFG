package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerViews.*;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.EjercicioListUpdateController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRCategoriaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRExamenDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaEvaluacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRCategoria;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class EjercicioListUpdateControllerTest extends AControllerTest {
	
	@Autowired
	private EjercicioListUpdateController ejercicioListUpdateController;
	
	@Autowired
	private IREjercicioDAO iREjercicioDAO;
	
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	
	@Autowired
	private IRDificultadDAO iRDificultadDAO;
	
	@Autowired
	private IRTitulacionDAO iRTitulacionDAO;
	
	@Autowired
	private IRCategoriaDAO iRCategoriaDAO;
	
	@Autowired
	private IRExamenDAO iRExamenDAO;
	
	@Autowired
	private IRPracticaDAO iRPracticaDAO;
	
	@Autowired
	private IRPracticaEvaluacionDAO iRPracticaEvaluacionDAO;
	
	@Autowired
	private IREjercicioPersonaDAO iREjercicioPersonaDAO;
	
	@Autowired
	private IREjercicioExamenDAO iREjercicioExamenDAO;
	
	@Autowired
	private IREjercicioPracticaDAO iREjercicioPracticaDAO;
	
	@Autowired
	private IREjercicioPracticaEvaluacionDAO iREjercicioPracticaEvaluacionDAO;
	
	private IREjercicio iREjercicio1;
	private IREjercicio iREjercicio2;
	private IREjercicio iREjercicio3;
	
	private IRPersona iRPersona1;
	
	private IRProfesor iRProfesor1;
	
	private IRDificultad iRDificultad1;
	
	private IRTitulacion iRTitulacion1;
	
	private IRCategoria iRCategoria1;
	
	private IRExamen iRExamen1;
	
	private IRPractica iRPractica1;
	
	private IRPracticaEvaluacion iRPracticaEvaluacion1;
	
	@Before
	public void before () {
		
		iRPersona1 = IRFactory.newIRPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iRPersonaDAO.save(iRPersona1);
		
		iRProfesor1 = IRFactory.newIRProfesor(iRPersona1);
		iRProfesorDAO.save(iRProfesor1);
		
		iRTitulacion1 = IRFactory.newIRTitulacion("titulacion 1");
		iRTitulacionDAO.save(iRTitulacion1);
		
		iRCategoria1 = IRFactory.newIRCategoria("categoria 1");
		iRCategoriaDAO.save(iRCategoria1);
		
		iRDificultad1 = IRFactory.newIRDificultad("dificultad 1", 2f);
		iRDificultadDAO.save(iRDificultad1);
		
		iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad1, true);
		iREjercicio1.setIRCategorias(new HashSet<>(Arrays.asList(iRCategoria1)));
		iREjercicioDAO.save(iREjercicio1);
		
		iREjercicio2 = IRFactory.newIREjercicio("ejercicio 2", "ejercicio 2", iRProfesor1, iRDificultad1, false);
		iREjercicio2.setIRCategorias(new HashSet<>(Arrays.asList(iRCategoria1)));
		iREjercicioDAO.save(iREjercicio2);
		
		iREjercicio3 = IRFactory.newIREjercicio("ejercicio 3", "ejercicio 3", iRProfesor1, iRDificultad1, true);
		iREjercicio3.setIRCategorias(new HashSet<>(Arrays.asList(iRCategoria1)));
		iREjercicioDAO.save(iREjercicio3);
		
		iRExamen1 = IRFactory.newIRExamen(1, 1, "examen 1", iRTitulacion1, true, true);
		iRExamenDAO.save(iRExamen1);
		IREjercicioExamen iREjercicioExamen1 = IRFactory.newIREjercicioExamen(iREjercicio1, iRExamen1, 1);
		IREjercicioExamen iREjercicioExamen2 = IRFactory.newIREjercicioExamen(iREjercicio3, iRExamen1, 2);
		iREjercicioExamenDAO.save(iREjercicioExamen1);
		iREjercicioExamenDAO.save(iREjercicioExamen2);
		iRExamen1.getEntity().setIEjerciciosExamen(new HashSet<>(Arrays.asList(iREjercicioExamen1.getEntity(), iREjercicioExamen2.getEntity())));
		
		iRPractica1 = IRFactory.newIRPractica(1, 1, "practica 1", iRTitulacion1, false, true);
		iRPracticaDAO.save(iRPractica1);
		IREjercicioPractica iREjercicioPractica1 = IRFactory.newIREjercicioPractica(iREjercicio2, iRPractica1, 1);
		IREjercicioPractica iREjercicioPractica2 = IRFactory.newIREjercicioPractica(iREjercicio3, iRPractica1, 2);
		iREjercicioPracticaDAO.save(iREjercicioPractica1);
		iREjercicioPracticaDAO.save(iREjercicioPractica2);
		iRPractica1.getEntity().setIEjerciciosPractica(new HashSet<>(Arrays.asList(iREjercicioPractica1.getEntity(), iREjercicioPractica2.getEntity())));
	
		iRPracticaEvaluacion1 = IRFactory.newIRPracticaEvaluacion(1, 1, "practica evaluacion 1", iRTitulacion1, true, true);
		iRPracticaEvaluacionDAO.save(iRPracticaEvaluacion1);
		IREjercicioPracticaEvaluacion iREjercicioPracticaEvaluacion1 = IRFactory.newIREjercicioPracticaEvaluacion(iREjercicio1, iRPracticaEvaluacion1, 1);
		IREjercicioPracticaEvaluacion iREjercicioPracticaEvaluacion2 = IRFactory.newIREjercicioPracticaEvaluacion(iREjercicio2, iRPracticaEvaluacion1, 2);
		iREjercicioPracticaEvaluacionDAO.save(iREjercicioPracticaEvaluacion1);
		iREjercicioPracticaEvaluacionDAO.save(iREjercicioPracticaEvaluacion2);
		iRPracticaEvaluacion1.getEntity().setIEjerciciosPracticaEvaluacion(new HashSet<>(Arrays.asList(iREjercicioPracticaEvaluacion1.getEntity(), iREjercicioPracticaEvaluacion2.getEntity())));
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_NotModified () {
		
		loginAsProfesor();
		
		ModelAndView modelAndView = ejercicioListUpdateController.updateSet(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
																			null, null, null, Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(2), Integer.toString(iRExamen1.getId()));
		
		assertEquals(VIEW_ERROR, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_Move_1 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, null, Integer.toString(iREjercicio1.getId()), null, "true", null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(2), Integer.toString(iRExamen1.getId()));
		
		assertEquals(2, iREjercicioExamenDAO.find(iREjercicio1, iRExamen1).getPosition());
		assertEquals(1, iREjercicioExamenDAO.find(iREjercicio3, iRExamen1).getPosition());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_Move_2 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, null, Integer.toString(iREjercicio3.getId()), "true", null, null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(3), Integer.toString(iRPractica1.getId()));
		
		assertEquals(2, iREjercicioPracticaDAO.find(iREjercicio2, iRPractica1).getPosition());
		assertEquals(1, iREjercicioPracticaDAO.find(iREjercicio3, iRPractica1).getPosition());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_Move_3 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, null, Integer.toString(iREjercicio2.getId()), "true", null, null, null, null, null, null, null, null, null,
																			null, "true", null, Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
		
		assertEquals(1, iREjercicioPracticaEvaluacionDAO.find(iREjercicio1, iRPracticaEvaluacion1).getPosition());
		assertEquals(2, iREjercicioPracticaEvaluacionDAO.find(iREjercicio2, iRPracticaEvaluacion1).getPosition());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_Move_4 () {
		
		loginAsProfesor();
		
		iRPracticaEvaluacion1.setAbierto(true);
		
		ejercicioListUpdateController.updateSet(null, null, null, null, Integer.toString(iREjercicio2.getId()), "true", null, null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
		
		assertEquals(2, iREjercicioPracticaEvaluacionDAO.find(iREjercicio1, iRPracticaEvaluacion1).getPosition());
		assertEquals(1, iREjercicioPracticaEvaluacionDAO.find(iREjercicio2, iRPracticaEvaluacion1).getPosition());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_Move_5 () {
		
		IRPersona iRPersonaMe = iRPersonaDAO.find(loginAsProfesor());
		
		IREjercicioPersona iREjercicioPersona1 = IRFactory.newIREjercicioPersona(iREjercicio1, iRPersonaMe, 1);
		IREjercicioPersona iREjercicioPersona2 = IRFactory.newIREjercicioPersona(iREjercicio3, iRPersonaMe, 2);
		iREjercicioPersonaDAO.save(iREjercicioPersona1);
		iREjercicioPersonaDAO.save(iREjercicioPersona2);
		iRPersonaMe.getEntity().setIEjerciciosPersona(new HashSet<>(Arrays.asList(iREjercicioPersona1.getEntity(), iREjercicioPersona2.getEntity())));
		
		ejercicioListUpdateController.updateSet(null, null, null, null, Integer.toString(iREjercicio3.getId()), "true", null, null, null, null, null, null, null, null, null,
																			null, "true", null, Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(1), Integer.toString(-1));
		
		assertEquals(2, iREjercicioPersonaDAO.find(iREjercicio1, iRPersonaMe).getPosition());
		assertEquals(1, iREjercicioPersonaDAO.find(iREjercicio3, iRPersonaMe).getPosition());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_Move_6 () {
		
		loginAsProfesor();
		
		iRPracticaEvaluacion1.setAbierto(true);
		
		ejercicioListUpdateController.updateSet(null, null, null, null, Integer.toString(iREjercicio2.getId()), null, null, null, null, null, null, null, null, null, null,
																			null, "true", null, Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
		
		assertEquals(1, iREjercicioPracticaEvaluacionDAO.find(iREjercicio1, iRPracticaEvaluacion1).getPosition());
		assertEquals(2, iREjercicioPracticaEvaluacionDAO.find(iREjercicio2, iRPracticaEvaluacion1).getPosition());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_ModifyCart_1 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, Integer.toString(iREjercicio2.getId()), null, null, null, null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(2), Integer.toString(iRExamen1.getId()));
		
		assertFalse(iREjercicioExamenDAO.find(iREjercicio2, iRExamen1).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_ModifyCart_2 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, Integer.toString(iREjercicio3.getId()), null, null, null, null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(2), Integer.toString(iRExamen1.getId()));
		
		assertTrue(iREjercicioExamenDAO.find(iREjercicio3, iRExamen1).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_ModifyCart_3 () {
		
		IRPersona iRPersonaMe = iRPersonaDAO.find(loginAsProfesor());
		
		ejercicioListUpdateController.updateSet(null, null, null, Integer.toString(iREjercicio2.getId()), null, null, null, null, null, null, null, null, null, null, null,
																			null, null, null, Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(2), Integer.toString(iRExamen1.getId()));
		
		assertFalse(iREjercicioPersonaDAO.find(iREjercicio2, iRPersonaMe).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_ModifyCart_4 () {
		
		IRPersona iRPersonaMe = iRPersonaDAO.find(loginAsProfesor());
		
		IREjercicioPersona iREjercicioPersona1 = IRFactory.newIREjercicioPersona(iREjercicio1, iRPersonaMe, 1);
		IREjercicioPersona iREjercicioPersona2 = IRFactory.newIREjercicioPersona(iREjercicio3, iRPersonaMe, 2);
		iREjercicioPersonaDAO.save(iREjercicioPersona1);
		iREjercicioPersonaDAO.save(iREjercicioPersona2);
		iRPersonaMe.getEntity().setIEjerciciosPersona(new HashSet<>(Arrays.asList(iREjercicioPersona1.getEntity(), iREjercicioPersona2.getEntity())));
		
		ejercicioListUpdateController.updateSet(null, null, null, Integer.toString(iREjercicio3.getId()), null, null, null, null, null, null, null, null, null, null, null,
																			null, null, null, Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(2), Integer.toString(iRExamen1.getId()));
		
		assertTrue(iREjercicioPersonaDAO.find(iREjercicio3, iRPersonaMe).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_ModifyCart_5 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, Integer.toString(iREjercicio1.getId()), null, null, null, null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(3), Integer.toString(iRPractica1.getId()));
		
		assertFalse(iREjercicioPracticaDAO.find(iREjercicio1, iRPractica1).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_ModifyCart_6 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, Integer.toString(iREjercicio3.getId()), null, null, null, null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(3), Integer.toString(iRPractica1.getId()));
		
		assertTrue(iREjercicioPracticaDAO.find(iREjercicio3, iRPractica1).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_ModifyCart_7 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, Integer.toString(iREjercicio3.getId()), null, null, null, null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
		
		assertFalse(iREjercicioPracticaEvaluacionDAO.find(iREjercicio3, iRPracticaEvaluacion1).isNull());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateSet_ModifyCart_8 () {
		
		loginAsProfesor();
		
		ejercicioListUpdateController.updateSet(null, null, null, Integer.toString(iREjercicio2.getId()), null, null, null, null, null, null, null, null, null, null, null, null,
																			null, "true", Integer.toString(1), Integer.toString(10),
																			null, Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
		
		assertTrue(iREjercicioPracticaEvaluacionDAO.find(iREjercicio2, iRPracticaEvaluacion1).isNull());
		
	}
	
}
