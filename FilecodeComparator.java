import java.util.Comparator;

public class FileCodeComparator implements Comparator<Music> {

	public int compare(Music o1, Music o2) {
		return o1.fileCode.compareTo(o2.fileCode);
	}
}
