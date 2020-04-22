package tests;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import yeelp.portablejukebox.util.NonNullMap;

/**
 * @author Yeelp
 *
 */
public class NonNullMapTest 
{
	private static NonNullMap map;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		map = new NonNullMap<Integer, String>("");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		map.clear();
	}
	
	@Test
	public void testContructor()
	{
		assertTrue(new NonNullMap<Integer, String>("", 1, 2, 3, 4, 5).entrySet().size() == 5);
	}

	@Test
	public void testSizeAndEmpty()
	{
		assertTrue(map.size() == 0);
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void testEmptyPut() 
	{
		assertNull(map.put(0, "A String!"));
	}
	
	@Test
	public void testFullPut()
	{
		map.put(0, "String");
		assertNotNull(map.put(0, "Some other string"));
	}

	@Test
	public void testGet()
	{
		map.put(0, "String");
		map.put(2, "A Different String");
		assertEquals(map.get(0), "String");
		assertEquals(map.get(2), "A Different String");
	}
	
	@Test
	public void testEmptyGet()
	{
		assertEquals(map.get(0), map.getDefaultValue());
	}
	
	@Test
	public void testContainsKey()
	{
		map.put(0, "String");
		assertTrue(map.containsKey(0));
	}
	
	@Test
	public void testContainsValue()
	{
		map.put(0, "String");
		assertTrue(map.containsValue("String"));
	}
	
	@Test
	public void testRemove()
	{
		map.put(0, "String");
		map.remove(0);
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void testEmptyRemove()
	{
		assertNull(map.remove(0));
	}
	
	@Test
	public void testInvalidRemove()
	{
		map.put(0, "String");
		assertNull(map.remove(1));
		assertFalse(map.isEmpty());
	}
	
	@Test
	public void testSetDefault()
	{
		map.put(0, "String");
		int sizeBefore = map.size();
		map.setDefault(0);
		assertEquals(map.get(0), map.getDefaultValue());
		assertTrue(sizeBefore == map.size());
	}
	
	@Test
	public void testPutAll()
	{
		NonNullMap<Integer, String>map2 = new NonNullMap<Integer, String>("", 1, 2, 3, 4, 5);
		map2.put(4, "abc");
		map.putAll(map2);
		for(int i = 0; i < 6; i++)
		{
			assertEquals("Failed on "+i, map.get(i), map2.get(i));
		}
	}
}
