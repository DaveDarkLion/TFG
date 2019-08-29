package rio.antelodel.david.ejercicios_programacion.rich_entity.filter;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rio.antelodel.david.ejercicios_programacion.ATest;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.helper.IFilterHelper.Operation;
import rio.antelodel.david.ejercicios_programacion.rich_entity.filter.implementation.FilterIRUser;
import rio.antelodel.david.ejercicios_programacion.rich_entity.iface.container.IRUser;

public class FilterRUserTest extends ATest {

	private final String filter1 = "user1";
	private final String filter2 = "user2";
	private final String filter3 = "user3";
	
	private List<String> filters;
	
	private FilterIRUser filterIRUserEmail;
	
	@Mock private IRUser iRUser1;
	@Mock private IRUser iRUser2;
	@Mock private IRUser iRUser3;
	
	private List<IRUser> iRUserList;
	
	@Before
	public void before () {
		
		MockitoAnnotations.initMocks(this);
		
		filters = Arrays.asList(filter1, filter2, filter3);
		
		filterIRUserEmail = new FilterIRUser();
		
		when(iRUser1.getEmail()).thenReturn("user1");
		when(iRUser2.getEmail()).thenReturn("user2");
		when(iRUser3.getEmail()).thenReturn("user3");
		
		when(iRUser1.getFilterIRUser()).thenReturn(Arrays.asList(iRUser1, iRUser2, iRUser3));
		when(iRUser2.getFilterIRUser()).thenReturn(Arrays.asList(iRUser1));
		when(iRUser3.getFilterIRUser()).thenReturn(Arrays.asList(iRUser1, iRUser3));
		
		iRUserList = Arrays.asList(iRUser1, iRUser2, iRUser3);
		
	}
	
	@Test
	public void testMatches_True () {
		
		assertTrue(filterIRUserEmail.matches(iRUser1, filter1));
		
	}
	
	@Test
	public void testMatches_False () {
		
		assertFalse(filterIRUserEmail.matches(iRUser1, filter2));
		
	}
	
	@Test
	public void testMatchesObject_FilterIsAny () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(true).when(filterIRUserSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRUserSpy.matchesObject(iRUser1, filter1));
		verify(filterIRUserSpy, never()).matches(Matchers.anyObject(), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_True () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(false).when(filterIRUserSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRUserSpy).matches(Matchers.eq(iRUser1), Matchers.anyString());
		doReturn(true).when(filterIRUserSpy).matches(Matchers.eq(iRUser2), Matchers.anyString());
		
		// Testing
		
		assertTrue(filterIRUserSpy.matchesObject(iRUser1, filter2));
		verify(filterIRUserSpy).matches(iRUser1, filter2);
		verify(filterIRUserSpy).matches(iRUser2, filter2);
		verify(filterIRUserSpy, never()).matches(Matchers.eq(iRUser3), Matchers.anyString());
		
	}
	
	@Test
	public void testMatchesObject_False () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(false).when(filterIRUserSpy).filterIsAny(Matchers.anyString());
		
		doReturn(false).when(filterIRUserSpy).matches(Matchers.eq(iRUser1), Matchers.anyString());
		doReturn(false).when(filterIRUserSpy).matches(Matchers.eq(iRUser2), Matchers.anyString());
		doReturn(false).when(filterIRUserSpy).matches(Matchers.eq(iRUser3), Matchers.anyString());
		
		// Testing
		
		assertFalse(filterIRUserSpy.matchesObject(iRUser1, filter2));
		verify(filterIRUserSpy).matches(iRUser1, filter2);
		verify(filterIRUserSpy).matches(iRUser2, filter2);
		verify(filterIRUserSpy).matches(iRUser3, filter2);
		
	}
	
	@Test
	public void testMatchesObjectOr_True () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(false).when(filterIRUserSpy).matchesObject(iRUser1, filter1);
		doReturn(true).when(filterIRUserSpy).matchesObject(iRUser1, filter2);
		
		// Testing
		
		assertTrue(filterIRUserSpy.matchesObject(iRUser1, filters, Operation.OR));
		verify(filterIRUserSpy).matchesObject(iRUser1, filter1);
		verify(filterIRUserSpy).matchesObject(iRUser1, filter2);
		verify(filterIRUserSpy, never()).matchesObject(iRUser1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectOr_False () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(false).when(filterIRUserSpy).matchesObject(iRUser1, filter1);
		doReturn(false).when(filterIRUserSpy).matchesObject(iRUser1, filter2);
		doReturn(false).when(filterIRUserSpy).matchesObject(iRUser1, filter3);
		
		// Testing
		
		assertFalse(filterIRUserSpy.matchesObject(iRUser1, filters, Operation.OR));
		verify(filterIRUserSpy).matchesObject(iRUser1, filter1);
		verify(filterIRUserSpy).matchesObject(iRUser1, filter2);
		verify(filterIRUserSpy).matchesObject(iRUser1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_True () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(true).when(filterIRUserSpy).matchesObject(iRUser1, filter1);
		doReturn(true).when(filterIRUserSpy).matchesObject(iRUser1, filter2);
		doReturn(true).when(filterIRUserSpy).matchesObject(iRUser1, filter3);
		
		// Testing
		
		assertTrue(filterIRUserSpy.matchesObject(iRUser1, filters, Operation.AND));
		verify(filterIRUserSpy).matchesObject(iRUser1, filter1);
		verify(filterIRUserSpy).matchesObject(iRUser1, filter2);
		verify(filterIRUserSpy).matchesObject(iRUser1, filter3);
		
	}
	
	@Test
	public void testMatchesObjectAnd_False () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(true).when(filterIRUserSpy).matchesObject(iRUser1, filter1);
		doReturn(false).when(filterIRUserSpy).matchesObject(iRUser1, filter2);
		doReturn(true).when(filterIRUserSpy).matchesObject(iRUser1, filter3);
		
		// Testing
		
		assertFalse(filterIRUserSpy.matchesObject(iRUser1, filters, Operation.AND));
		verify(filterIRUserSpy).matchesObject(iRUser1, filter1);
		verify(filterIRUserSpy).matchesObject(iRUser1, filter2);
		verify(filterIRUserSpy, never()).matchesObject(iRUser1, filter3);
		
	}
	
	@Test
	public void testApply () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(false).when(filterIRUserSpy).matchesObject(Matchers.eq(iRUser1), Matchers.anyString());
		doReturn(true).when(filterIRUserSpy).matchesObject(Matchers.eq(iRUser2), Matchers.anyString());
		doReturn(false).when(filterIRUserSpy).matchesObject(Matchers.eq(iRUser3), Matchers.anyString());
		
		// Testing
		
		assertEquals(1, filterIRUserSpy.apply(iRUserList, filter1).size());
		verify(filterIRUserSpy).matchesObject(iRUser1, filter1);
		verify(filterIRUserSpy).matchesObject(iRUser2, filter1);
		verify(filterIRUserSpy).matchesObject(iRUser3, filter1);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_FiltersAreAny () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(true).when(filterIRUserSpy).filtersAreAny(Matchers.anyList());
		
		// Testing
		
		assertEquals(iRUserList, filterIRUserSpy.apply(iRUserList, filters, Operation.AND));
		verify(filterIRUserSpy, never()).matchesObject(Matchers.anyObject(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testApply_Normal () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(false).when(filterIRUserSpy).filtersAreAny(Matchers.anyList());
		
		doReturn(false).when(filterIRUserSpy).matchesObject(Matchers.eq(iRUser1), Matchers.anyList(), Matchers.any());
		doReturn(true).when(filterIRUserSpy).matchesObject(Matchers.eq(iRUser2), Matchers.anyList(), Matchers.any());
		doReturn(false).when(filterIRUserSpy).matchesObject(Matchers.eq(iRUser3), Matchers.anyList(), Matchers.any());
		
		// Testing
		
		assertEquals(Arrays.asList(iRUser2), filterIRUserSpy.apply(iRUserList, filters, Operation.AND));
		verify(filterIRUserSpy).matchesObject(iRUser1, filters, Operation.AND);
		verify(filterIRUserSpy).matchesObject(iRUser2, filters, Operation.AND);
		verify(filterIRUserSpy).matchesObject(iRUser3, filters, Operation.AND);
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_FilterIsAny () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(true).when(filterIRUserSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		assertEquals(iRUserList, filterIRUserSpy.cleanAndApply(iRUserList, filter1, Operation.AND));
		verify(filterIRUserSpy, never()).apply(Matchers.anyList(), Matchers.anyList(), Matchers.any());
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testCleanAndApply_Normal () {
		
		// Setup
		
		FilterIRUser filterIRUserSpy = spy(filterIRUserEmail);
		
		doReturn(false).when(filterIRUserSpy).filterIsAny(Matchers.anyString());
		
		// Testing
		
		filterIRUserSpy.cleanAndApply(iRUserList, filter1, Operation.AND);
		verify(filterIRUserSpy).apply(Matchers.eq(iRUserList), Matchers.anyList(), Matchers.eq(Operation.AND));
		
	}
	
}