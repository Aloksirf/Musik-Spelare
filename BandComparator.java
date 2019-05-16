import java.util.Comparator;
/**
* Class that that implements Comparator and compares two music objects by the bandname.
*
* @author Andreas Stadin, Nickla Kristörm, Vidar Hårding and Oliver Olsson.
*/
public class BandComparator implements Comparator<Music> {
	
	/**
	* Method that compares two music objects by band.
	*
	* @param m1 first music object.
	* @param m2 second music object.
	* @return integer with compareTo-method.
	*/
	public int compare(Music m1, Music m2) {
		return m1.band.compareTo(m2.band);
	}
}
