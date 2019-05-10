import java.util.Comparator;

public class BandComparator implements Comparator<Music> {

	public int compare(Music m1, Music m2) {
		return m1.band.compareTo(m2.band);
	}
}
