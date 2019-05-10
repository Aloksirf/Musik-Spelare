import java.util.Comparator;

public class SongComparator implements Comparator<Music> {

	public int compare(Music o1, Music o2) {
		return o1.song.compareTo(o2.song);
	}
}
