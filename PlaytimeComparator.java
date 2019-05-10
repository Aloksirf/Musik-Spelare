import java.util.Comparator;

public class PlaytimeComparator implements Comparator<Music> {

	public int compare(Music o1, Music o2) {
		if (o1.playtime < o2.playtime) {
			return -1;
		} else if (o1.playtime > o2.playtime) {
			return 1;
		} else {
			return 0;
		}
	}
}
