import java.util.Comparator;

/**
* Class that that implements Comparator and compares two music objects by the playtime.
*
* @author Andreas Stadin, Nickla Kristörm, Vidar Hårding and Oliver Olsson.
*/
public class PlaytimeComparator implements Comparator<Music> {
	
	/**
	* Method that compares two music objects by playtime.
	*
	* @param m1 first music object.
	* @param m2 second music object.
	* @return -1 if m1 < m2, 1 if m1 > m2 and 0 if m1 == m2.
	*/
	public int compare(Music m1, Music m2) {
		if (m1.playtime < m2.playtime) {
			return -1;
		} else if (m1.playtime > m2.playtime) {
			return 1;
		} else {
			return 0;
		}
	}
}

