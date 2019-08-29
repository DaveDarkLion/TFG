package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.*;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.json.JSONObject;
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
import rio.antelodel.david.ejercicios_programacion.controller.implementation.EjercicioListController;
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
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRExamen;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPracticaEvaluacion;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class EjercicioListControllerTest extends AControllerTest {

	protected static final String EJERCICIOS_DATA_NAME = "ejerciciosData";
	protected static final String EJERCICIOS_SET_DATA_NAME = "ejerciciosSetData";
	protected static final String EJERCICIOS_CHECKED_DATA_NAME = "ejerciciosCheckedData";
	
	private static final String VIEW_EJERCICIO_LIST = "ejercicio/ejercicio-list/ejercicio-list";
	
	@Autowired
	private EjercicioListController ejercicioListController;
	
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
		iREjercicioDAO.save(iREjercicio2);
		
		iREjercicio3 = IRFactory.newIREjercicio("ejercicio 3", "ejercicio 3", iRProfesor1, iRDificultad1, true);
		iREjercicio3.setIRCategorias(new HashSet<>(Arrays.asList(iRCategoria1)));
		iREjercicioDAO.save(iREjercicio3);
		
		iRExamen1 = IRFactory.newIRExamen(1, 1, "examen 1", iRTitulacion1, true, false);
		iRExamenDAO.save(iRExamen1);
		iREjercicioExamenDAO.save(IRFactory.newIREjercicioExamen(iREjercicio1, iRExamen1, 1));
		iREjercicioExamenDAO.save(IRFactory.newIREjercicioExamen(iREjercicio3, iRExamen1, 2));
		
		iRPractica1 = IRFactory.newIRPractica(1, 1, "practica 1", iRTitulacion1, false, true);
		iRPracticaDAO.save(iRPractica1);
		iREjercicioPracticaDAO.save(IRFactory.newIREjercicioPractica(iREjercicio1, iRPractica1, 1));
		iREjercicioPracticaDAO.save(IRFactory.newIREjercicioPractica(iREjercicio2, iRPractica1, 2));
	
		iRPracticaEvaluacion1 = IRFactory.newIRPracticaEvaluacion(1, 1, "practica evaluacion 1", iRTitulacion1, true, false);
		iRPracticaEvaluacionDAO.save(iRPracticaEvaluacion1);
		iREjercicioPracticaEvaluacionDAO.save(IRFactory.newIREjercicioPracticaEvaluacion(iREjercicio2, iRPracticaEvaluacion1, 1));
		iREjercicioPracticaEvaluacionDAO.save(IRFactory.newIREjercicioPracticaEvaluacion(iREjercicio3, iRPracticaEvaluacion1, 2));
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_Error_1 () {
		
		loginAsAlumno();
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, null, null, null, null, null, null, null, null, null, null,
																					Integer.toString(1), Integer.toString(10), null, 
																					Integer.toString(3), Integer.toString(iRPractica1.getId()));
        
        assertNotEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_1 () {
		
		loginAsAdministrador();
		
		String[] categorias = new String [1];
		categorias[0] = Integer.toString(iRCategoria1.getId());
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, null, null, null, null, null, null, null, null, null, null,
																					Integer.toString(1), Integer.toString(10), categorias, 
																					Integer.toString(0), Integer.toString(-1));
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
        
        assertEquals(2, model.getJSONArray(EJERCICIOS_DATA_NAME).length());
        assertEquals(iREjercicio1.getId(), model.getJSONArray(EJERCICIOS_DATA_NAME).getJSONObject(0).getInt("id"));
        assertEquals(iREjercicio3.getId(), model.getJSONArray(EJERCICIOS_DATA_NAME).getJSONObject(1).getInt("id"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_2 () {
		
		loginAsAlumno();
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, null, null, null, null, null, null, null, null, null, null,
																					Integer.toString(1), Integer.toString(10), null, 
																					Integer.toString(0), Integer.toString(-1));
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
        
        assertEquals(2, model.getJSONArray(EJERCICIOS_DATA_NAME).length());
        assertEquals(iREjercicio1.getId(), model.getJSONArray(EJERCICIOS_DATA_NAME).getJSONObject(0).getInt("id"));
        assertEquals(iREjercicio3.getId(), model.getJSONArray(EJERCICIOS_DATA_NAME).getJSONObject(1).getInt("id"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_3 () {
		
		loginAsAdministrador();
		
		String[] categorias = new String [2];
		categorias[0] = Integer.toString(iRCategoria1.getId() + 1);
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, "false", "false", "false", "false", "false", "false", "false", "false", "false", "false",
																					Integer.toString(1), Integer.toString(10), categorias, 
																					Integer.toString(1), Integer.toString(1));
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
        
        assertEquals(0, model.getJSONArray(EJERCICIOS_DATA_NAME).length());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_4 () {
		
		loginAsAlumno();
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, null, null, null, null, null, null, null, null, null, null,
																					Integer.toString(1), Integer.toString(10), null, 
																					Integer.toString(2), Integer.toString(iRExamen1.getId()));
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
        
        assertEquals(2, model.getJSONArray(EJERCICIOS_DATA_NAME).length());
        assertEquals(iREjercicio1.getId(), model.getJSONArray(EJERCICIOS_DATA_NAME).getJSONObject(0).getInt("id"));
        assertEquals(iREjercicio3.getId(), model.getJSONArray(EJERCICIOS_DATA_NAME).getJSONObject(1).getInt("id"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_5 () {
		
		loginAsAdministrador();
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, null, null, "true", null, "2", null, "2000", "2020", null, null,
																					Integer.toString(1), Integer.toString(10), null, 
																					Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
        
        assertEquals(0, model.getJSONArray(EJERCICIOS_DATA_NAME).length());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_6 () {
		
		loginAsAdministrador();
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, null, null, "true", null, "3", null, "2000", "2020", null, null,
																					Integer.toString(1), Integer.toString(10), null, 
																					Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
        
        assertEquals(0, model.getJSONArray(EJERCICIOS_DATA_NAME).length());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_7 () {
		
		loginAsAdministrador();
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, null, null, "true", null, "4", null, "2000", "2020", null, null,
																					Integer.toString(1), Integer.toString(10), null, 
																					Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
        
        assertEquals(0, model.getJSONArray(EJERCICIOS_DATA_NAME).length());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetEjercicioListForm_8 () {
		
		loginAsAdministrador();
		
		ModelAndView modelAndView = ejercicioListController.getEjercicioListForm(	null, null, null, null, null, "true", null, "-1", null, "2000", "2020", "true", null,
																					Integer.toString(1), Integer.toString(10), null, 
																					Integer.toString(4), Integer.toString(iRPracticaEvaluacion1.getId()));
        JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
        
        assertEquals(VIEW_EJERCICIO_LIST, modelAndView.getViewName());
        
        assertEquals(0, model.getJSONArray(EJERCICIOS_DATA_NAME).length());
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Normal () {
		
		loginAsAdministrador();
		
		assertNotNull(ejercicioListController.getDocument(Integer.toString(0)));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Normal_2 () {
		
		IRPersona iRPersonaMe = iRPersonaDAO.find(loginAsAlumno());
		
		IREjercicio iREjercicio4 = IRFactory.newIREjercicio("ejercicio 4", "ejercicio 4", iRProfesor1, iRDificultad1, false);
		iREjercicioDAO.save(iREjercicio4);
		
		IREjercicioPersona iREjercicioPersona1 = IRFactory.newIREjercicioPersona(iREjercicio4, iRPersonaMe, 1);
		iREjercicioPersonaDAO.save(iREjercicioPersona1);
		IREjercicioPersona iREjercicioPersona2 = IRFactory.newIREjercicioPersona(iREjercicio1, iRPersonaMe, 2);
		iREjercicioPersonaDAO.save(iREjercicioPersona2);
		
		iRPersonaMe.getEntity().setIEjerciciosPersona(new HashSet<>(Arrays.asList(iREjercicioPersona1.getEntity())));
		
		assertNotNull(ejercicioListController.getDocument(Integer.toString(0)));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Normal_3 () {
		
		IRPersona iRPersonaMe = iRPersonaDAO.find(loginAsAdministrador());
		
		IREjercicio iREjercicio4 = IRFactory.newIREjercicio("ejercicio 4", "ejercicio 4", iRProfesor1, iRDificultad1, false);
		iREjercicioDAO.save(iREjercicio4);
		
		IREjercicioPersona iREjercicioPersona1 = IRFactory.newIREjercicioPersona(iREjercicio4, iRPersonaMe, 1);
		iREjercicioPersonaDAO.save(iREjercicioPersona1);
		IREjercicioPersona iREjercicioPersona2 = IRFactory.newIREjercicioPersona(iREjercicio1, iRPersonaMe, 2);
		iREjercicioPersonaDAO.save(iREjercicioPersona2);
		
		iRPersonaMe.getEntity().setIEjerciciosPersona(new HashSet<>(Arrays.asList(iREjercicioPersona1.getEntity())));
		
		assertNotNull(ejercicioListController.getDocument(Integer.toString(0)));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetDocument_Null () {
		
		loginAsAdministrador();
		
		assertNotNull(ejercicioListController.getDocument(null));
		
	}
	
}
