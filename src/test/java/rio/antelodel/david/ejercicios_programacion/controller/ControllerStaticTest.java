package rio.antelodel.david.ejercicios_programacion.controller;

import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerUtility.*;
import static rio.antelodel.david.ejercicios_programacion.controller.utility.ControllerData.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.auxiliar.pair.Pair;
import rio.antelodel.david.ejercicios_programacion.controller.role.Role;
import rio.antelodel.david.ejercicios_programacion.rich_entity.factory.IRFactory;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.IRTitulacion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = ATest.CONTEXT_CONFIGURATION)
public class ControllerStaticTest extends AControllerTest {
	
	protected SecurityContext getSecurityContext () { return SecurityContextHolder.getContext(); }
	
	@Test
	public void testIsValid () {
		
		String test1 = null;
		
		assertFalse(isValid(test1));
		assertFalse(isValid(""));
		assertTrue(isValid("test"));
		
		assertFalse(isValid(test1, "test"));
		assertTrue(isValid("test", "not test"));
		assertFalse(isValid("test", "test"));
		
		assertFalse(isValid(test1, 1));
		assertTrue(isValid(Integer.toString(1), 2));
		assertFalse(isValid(Integer.toString(1), 1));
		
		String [] test2 = null;
		String [] test3 = new String[0];
		String [] test4 = new String[3];
		
		assertFalse(isValid(test2));
		assertFalse(isValid(test3));
		assertTrue(isValid(test4));
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUserIsRoleAlumno () {
		
		// Setup
		
		loginAsAlumno();
		
		// Testing
		
		assertTrue(userIsRole(Role.ALUMNO, getSecurityContext()));
		assertFalse(userIsRole(Role.PROFESOR, getSecurityContext()));
		assertFalse(userIsRole(Role.ADMIN, getSecurityContext()));
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUserIsRoleProfesor () {
		
		// Setup
		
		loginAsProfesor();
		
		// Testing
		
		assertFalse(userIsRole(Role.ALUMNO, getSecurityContext()));
		assertTrue(userIsRole(Role.PROFESOR, getSecurityContext()));
		assertFalse(userIsRole(Role.ADMIN, getSecurityContext()));
		
		logout();
		
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUserIsRoleAdministrador () {
		
		// Setup
		
		loginAsAdministrador();
		
		// Testing
		
		assertFalse(userIsRole(Role.ALUMNO, getSecurityContext()));
		assertFalse(userIsRole(Role.PROFESOR, getSecurityContext()));
		assertTrue(userIsRole(Role.ADMIN, getSecurityContext()));
		
		logout();
		
	}
	
	@Test
	public void testToInt () {
		
		// Testing
		
		assertEquals(0, toInt(null, 0));
		assertEquals(3, toInt("3"));
		
	}
	
	@Test
	public void testToFloat () {
		
		// Testing
		
		assertEquals(0f, toFloat(null, 0f), 0.1f);
		assertEquals(3.5f, toFloat("3.5f"), 0.1f);
		
	}
	
	@Test
	public void testToStr () {
		
		// Testing
		
		assertEquals("", toStr(null, ""));
		assertEquals("test", toStr("test"));
		
	}
	
	@Test
	public void testGetGenericDataPairList () {
		
		// Setup
		
		Pair<Integer, String> pairZero = new Pair<>(0, "zero");
		Pair<Integer, String> pairOne = new Pair<>(1, "one");
		Pair<Integer, String> pairTwo = new Pair<>(2, "two");
		
		List<Pair<Integer, String>> pairs = new ArrayList<>(Arrays.asList(pairZero, pairOne, pairTwo));
		
		// Testing
		
		JSONObject pairsData = getGenericData(pairOne, pairs);
		
		JSONObject current = pairsData.getJSONObject("current");
		
		assertEquals(pairOne.getFirst(), current.get("id"));
		assertEquals(pairOne.getSecond(), current.get("name"));
		
		JSONArray list = pairsData.getJSONArray("list");
		
		assertEquals(pairZero.getFirst(), list.getJSONObject(0).get("id"));
		assertEquals(pairZero.getSecond(), list.getJSONObject(0).get("name"));
		
		assertEquals(pairOne.getFirst(), list.getJSONObject(1).get("id"));
		assertEquals(pairOne.getSecond(), list.getJSONObject(1).get("name"));
		
		assertEquals(pairTwo.getFirst(), list.getJSONObject(2).get("id"));
		assertEquals(pairTwo.getSecond(), list.getJSONObject(2).get("name"));
		
	}
	
	@Test
	public void testGetPageInfo () {
		
		// Testing
		
		assertEquals(3, getPageInfo(Integer.toString(3), 0));
		assertEquals(0, getPageInfo(Integer.toString(-3), 0));
		assertEquals(0, getPageInfo(null, 0));
		
	}
	
	@Test
	public void testGetPageLast () {
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		
		assertEquals(4, getPageLast(list, 2));
		
	}
	
	@Test
	public void testGetPagedList () {
		
		// Setup
		
		IRTitulacion iRTitulacion1 = IRFactory.newIRTitulacion("titulacion 1");
		iRTitulacion1.getEntity().setId(0);
		IRTitulacion iRTitulacion2 = IRFactory.newIRTitulacion("titulacion 2");
		iRTitulacion2.getEntity().setId(1);
		IRTitulacion iRTitulacion3 = IRFactory.newIRTitulacion("titulacion 3");
		iRTitulacion3.getEntity().setId(2);
		
		List<IRTitulacion> iRTitulacionList = Arrays.asList(iRTitulacion1, iRTitulacion2, iRTitulacion3);
		
		// Testing
		
		List<IRTitulacion> iRTitulacionListPaged = getPagedList(iRTitulacionList, 2, 1);
		
		assertEquals(1, iRTitulacionListPaged.size());
		assertEquals(iRTitulacion2, iRTitulacionListPaged.get(0));
		
	}
	
	@Test
	public void testGetList () {
		
		assertEquals(new ArrayList<>(), getList(null));
		assertEquals(Arrays.asList(1, 2, 3), getList(new Integer[] {1, 2, 3}));
		
	}
	
}
