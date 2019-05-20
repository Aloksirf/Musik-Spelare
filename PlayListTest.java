/**
 * PlayListTest is a JUnit test of the methods in
 * PlayList. Run file to execute.
 * @author Vidar.H, Oliver.O, Nicklas.K & Andreas.S
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayListTest {
	
PlayList first;
PlayList second;
PlayList third;

	@Before 
	public void testPlayList() {
		first = new PlayList();
		second = new PlayList();
		third = new PlayList();
	}
	
	@Test
	public void testLoad() {
		
		assertSame(first.songs.size(),0);
		assertEquals("Test if PlayList is empty",true,(first.songs.size()==0));
		first.loadPlayList("Library");
		assertEquals("Test if PlayList is empty",false,(first.songs.size()==0));
		
		second.loadPlayList("Library");
		second.songs.remove(0);
		assertEquals("Test Save", false, second.songs.contains("Barbie Girl"));
		
		second.savePlayList("testList");
		assertEquals("Test Save", false, second.songs.contains("Barbie Girl"));
		
		third.loadPlayList("testList");	
		
		for(int i = 0; i < third.songs.size();i++) {
			assertSame("Test Loaded and Saved List",second.songs.get(i),third.songs.get(i));
			
		}
		
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void getNegativeIndex()
	{
		first.songs.get(-1);
	}
	
	@Test
	public void testAddRemove() {
		
	}
	@Test
	public void testSearch() {
		
		first.loadPlayList("Library");
		
		//Existing bands in Library.txt
		assertEquals("Check existence",true,first.checkBandExistence("Aqua"));
		assertEquals("Check existence",true,first.checkBandExistence("ABBA"));
		assertEquals("Check existence",true,first.checkBandExistence("Queen"));
		assertEquals("Check existence",true,first.checkBandExistence("Vengaboys"));
		assertEquals("Check existence",true,first.checkBandExistence("Michael Jackson"));
		
		//Non existing bands in Library.txt
		assertEquals("Check existence",false,first.checkBandExistence("Anonymous"));
		assertEquals("Check existence",false,first.checkBandExistence("null"));
		assertEquals("Check existence",false,first.checkBandExistence(" "));
		assertEquals("Check existence",false,first.checkBandExistence("#¤%&/()"));
		assertEquals("Check existence",false,first.checkBandExistence("ABBAAA"));

		
	}
}
