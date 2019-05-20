import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

/**
 * JUnit that tests Hashing and it´s different methods.
 * 
 * @author Andreas Statid; Vidar Hårding, Niklas Kriström and Oliver Olsson.
 *
 */
public class HashingTest {
	Music m1;
	Music m2;
	Music m3;
	Music m4;
	Hashing hash;

	@Before
	public void setup() {
		m1 = new Music("Aqua", "Barbie Girl", 120, "123");
		m2 = new Music("Linkin Park", "New Divide", 140, "234");
		m3 = new Music("Tchaikovsky", "6th symphony 2nd movement", 568, "345");
		m4 = new Music("Michael Jackson", "Earth Song", 218, "456");
		hash = new Hashing();
		hash.add(m1);
		hash.add(m2);
		hash.add(m3);
		hash.add(m4);
	}

	/**
	 * This test method test add and getMusic. It also test that getMusic returns
	 * null if song doesn't exist or if the letters of the song is in wrong order.
	 */
	@Test
	public void testgetMusic() {
		Music m5 = Hashing.getMusic(m1.song);
		Music m6 = Hashing.getMusic(m2.song);
		Music m7 = Hashing.getMusic(m3.song);
		assertEquals("Test add with getMusic", m1, m5);
		assertEquals("Test add with getMusic", m2, m6);
		assertEquals("Test add with getMusic", m3, m7);

		assertEquals("Test getMusic return null if song doesn´t exist", null, Hashing.getMusic("Kent"));
		assertEquals("Checks that New Divide != eNw Divide", null, Hashing.getMusic("eNw Divide"));

	}

	/**
	 * Checks if the array of the hashtabell if doubled when 50% full.
	 */
	@Test
	public void testdoubleHashtabell() {
		Hashing hash_1 = new Hashing();
		assertEquals("Checks that array length is 100", 100, hash_1.hashSong.length);
		for (int i = 0; i < 51; i++) {
			char a = (char) i;
			String b = "" + a;
			hash_1.add(new Music("A", b, 1, "C"));
		}
		assertEquals("Checks if array length has been doubled", 200, hash_1.hashSong.length);
	}

}
