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
Hashing library = new Hashing();

	@Before 
	public void testPlayList() {
		first = new PlayList();
		second = new PlayList();
		third = new PlayList();
	}
	
	@Test
	public void testLoad() {
		
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
		// Method (removeSongForTestCase) is Equal to original (removeSong) but works
		// with this JUnit. 2 rows removed and save focused on "TestCase.txt" and not the actual chosen playlist.
		MainFile.readInFile(library);
		assertEquals("Try to Remove from an empty file.", false, first.removeSongForTestCase("Eat It"));
		first.addToPlayList("Barbie Girl", library);
		first.addToPlayList("Eat It", library);
		first.savePlayList("TestCase");
		assertEquals("Removes Barbie Girl from the playlist", true, first.removeSongForTestCase("Barbie Girl"));
		first.removeSongForTestCase("Eat It"); // Only Works for THIS test case.
		assertEquals("Try to Remove song that isn't there.", false, first.removeSongForTestCase("Barbie Girl"));

		assertEquals("Try to Add song that isn't in Library-file.", false,
				first.addToPlayList("jhasvfajhv", library));
		assertEquals("Try to Add song that is in Library-file.", true, first.addToPlayList("Beat It", library));

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
