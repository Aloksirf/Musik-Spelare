import java.util.Comparator;
/**
* Class that that implements Comparator and compares two music objects by the fileCode.
*
* @author Andreas Stadin, Nickla Kristörm, Vidar Hårding and Oliver Olsson.
*/

public class FilecodeComparator implements Comparator<Music> {
	
	/**
	* Method that compares two music objects by fileCode.
	*
	* @param m1 first music object.
	* @param m2 second music object.
	* @return integer with compareTo-method.
	*/	
	public int compare(Music o1, Music o2) {
		return o1.fileCode.compareTo(o2.fileCode);
	}
}

	
	
