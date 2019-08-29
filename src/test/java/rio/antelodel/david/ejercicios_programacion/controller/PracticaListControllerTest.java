package rio.antelodel.david.ejercicios_programacion.controller;

import static org.junit.Assert.assertEquals;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerNames.MODEL_NAME;

import java.util.Arrays;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.controller.implementation.PracticaListController;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRDificultadDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IREjercicioPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPracticaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRPersonaDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRProfesorDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.dao.iface.IRTitulacionDAO;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRDificultad;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicio;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IREjercicioPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPractica;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRPersona;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRProfesor;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class PracticaListControllerTest extends AControllerTest {

	// Names
	
	protected static final String FILTER_SET_NAME = "filterSet";
	protected static final String SET_DIFICULTADES_DATA_NAME = "setDificultadesData"; 
	protected static final String SETS_DATA_NAME = "setsData";
	protected static final String SET_TYPE_DATA_NAME = "setTypeData";
	
	// Controllers
	
	@Autowired
	private PracticaListController practicaListController;
	
	// DAOs
	
	@Autowired
	private IRPracticaDAO iRPracticaDAO;
	@Autowired
	private IRTitulacionDAO iRTitulacionDAO;
	@Autowired
	private IRDificultadDAO iRDificultadDAO;
	@Autowired
	private IRPersonaDAO iRPersonaDAO;
	@Autowired
	private IRProfesorDAO iRProfesorDAO;
	@Autowired
	private IREjercicioDAO iREjercicioDAO;
	@Autowired
	private IREjercicioPracticaDAO iREjercicioPracticaDAO;
	
	private IRPractica iRPractica1;
	private IRPractica iRPractica2;
	private IRPractica iRPractica3;
	
	private IRTitulacion iRTitulacion1;
	private IRTitulacion iRTitulacion2;
	
	private IRDificultad iRDificultad1;
	private IRDificultad iRDificultad2;
	
	private IRPersona iRPersona1;
	
	private IRProfesor iRProfesor1;
	
	private IREjercicio iREjercicio1;
	
	private IREjercicioPractica iREjercicioPractica1;
	
	// Functions

	@Before
	public void before () {
		
		iRTitulacion1 = IRFactory.newIRTitulacion("titulacion 1");
		iRTitulacionDAO.save(iRTitulacion1);
		iRTitulacion2 = IRFactory.newIRTitulacion("titulacion 2");
		iRTitulacionDAO.save(iRTitulacion2);
		
		iRPractica1 = IRFactory.newIRPractica(1, 2018, "practica 1", iRTitulacion1, true, false);
		iRPractica2 = IRFactory.newIRPractica(1, 2017, "practica 2", iRTitulacion1, false, true);
		iRPractica3 = IRFactory.newIRPractica(1, 2016, "practica 3", iRTitulacion1, true, true);
		
		iRPracticaDAO.save(iRPractica1);
		iRPracticaDAO.save(iRPractica2);
		iRPracticaDAO.save(iRPractica3);
		
		iRDificultad1 = IRFactory.newIRDificultad("dificultad 1", 2f);
		iRDificultadDAO.save(iRDificultad1);
		
		iRDificultad2 = IRFactory.newIRDificultad("dificultad 2", 5f);
		iRDificultadDAO.save(iRDificultad2);
		
		iRPersona1 = IRFactory.newIRPersona("pvazquez@hotmail.com", "Pablo", "Vazquez", "Lopez", "1234");
		iRPersonaDAO.save(iRPersona1);
		
		iRProfesor1 = IRFactory.newIRProfesor(iRPersona1);
		iRProfesorDAO.save(iRProfesor1);
		
		iREjercicio1 = IRFactory.newIREjercicio("ejercicio 1", "ejercicio 1", iRProfesor1, iRDificultad2, true);
		iREjercicioDAO.save(iREjercicio1);
		
		iREjercicioPractica1 = IRFactory.newIREjercicioPractica(iREjercicio1, iRPractica2, 3);
		iREjercicioPracticaDAO.save(iREjercicioPractica1);
		
		iRPractica2.getEntity().setIEjerciciosPractica(new HashSet<>(Arrays.asList(iREjercicioPractica1.getEntity())));
		
	}
	
	@After
	public void after () {
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPracticaListForm_Privileged () {
		
		// Setup
		
		loginAsAdministrador();
		
        // Testing
        
		ModelAndView modelAndView = practicaListController.getPracticaListForm(null, null, Integer.toString(2), Integer.toString(1), null, null, null, null, null, null);
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		assertEquals(iRDificultad2.getId(), model.getJSONArray(SET_DIFICULTADES_DATA_NAME).getJSONObject(0).get("id"));
		
		JSONArray data = model.getJSONArray(SETS_DATA_NAME);
		
		assertEquals(1, data.length());
		assertEquals(iRPractica2.getId(), data.getJSONObject(0).getInt("id"));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testGetPracticaListForm_NotPrivileged () {
		
		// Setup
		
		loginAsAlumno();
		
        // Testing
        
		ModelAndView modelAndView = practicaListController.getPracticaListForm(null, null, Integer.toString(2), Integer.toString(1), null, null, null, null, null, null);
		JSONObject model = getJSONObject(modelAndView.getModel()).getJSONObject(MODEL_NAME);
		
		assertEquals(iRDificultad1.getId(), model.getJSONArray(SET_DIFICULTADES_DATA_NAME).getJSONObject(0).get("id"));
		
		JSONArray data = model.getJSONArray(SETS_DATA_NAME);
		
		assertEquals(1, data.length());
		assertEquals(iRPractica3.getId(), data.getJSONObject(0).getInt("id"));
		
	}
		
}