/**
 * är en konstruktor som lagrar musikfiler i en hashlista.
 * 
 * @author Master booom
 *
 * @param <Music>
 */
public class Hashing {

	public static Music[] hashSong;
	public static int currentSize;

	public Hashing() {
		hashSong = new Music[100];
		currentSize = 0;
	}

	public void add(Music obj) {
		doubleHashtabell();
		int index = getIndex(obj.song);
		hashSong[index] = obj;
		currentSize++;
	}

	/**
	 * Method that finds the object in the hashtabell.
	 */
	public Music getMusic(String a) {
		int index = 0;
		for (int i = 0; i < a.length(); i++) {
			int letterValue = (int) a.charAt(i);
			index += letterValue * (int) Math.pow(2, (a.length() - i));
		}

		int indexValue = index % hashSong.length;

		int n = 1;
		int i = 0;
		int temp = indexValue;
		while (true) {
			if (hashSong[temp] != null) {
				if (hashSong[temp].song.equals(a)) {
					break;
				} else {
					temp = indexValue + (int) Math.pow(n, 2);
					n++;
					temp = temp % hashSong.length;
				}
			} else if (i++ == 100) {
				System.out.println("Song not found.");
				return null;
			}
		}
		return hashSong[temp];

	}

	public static int getIndex(String a) {

		int index = 0;
		for (int i = 0; i < a.length(); i++) {
			int letterValue = (int) a.charAt(i);
			index += letterValue * (int) Math.pow(2, (a.length() - i));
		}

		int indexValue = index % hashSong.length;

		int n = 1;

		int temp = indexValue;

		while (hashSong[temp] != null) {
			temp = indexValue + (int) Math.pow(n, 2);
			n++;
			temp = temp % hashSong.length;
		}
		return temp;
	}

	public static void doubleHashtabell() {
		if (overLoad()) {
			Music[] temp = new Music[hashSong.length * 2];
			for (int i = 0; i < hashSong.length; i++) {
				temp[i] = hashSong[i];
			}
			hashSong = temp;
		}
	}

	public static boolean overLoad() {
		return (currentSize + 1.0) / (hashSong.length + 0.0) > 0.5;
	}

}
