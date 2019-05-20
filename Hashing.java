/**
 * Class that creates a hashtabell that stores music objects. 
 * 
 * @author Andreas Stadin, Nicklas Kriström, Vidar Hårding and Oliver Olsson
 * @param <Music>
 */
public class Hashing {

	public static Music[] hashSong;
	public static int currentSize;
	
	/**
	* Constructor that creates an array with 100 empty spaces and a
	* counter that will keep track of amount of objects that has been added.
	*/
	public Hashing() {
		hashSong = new Music[100];
		currentSize = 0;
	}
	
	/**
	* Method that adds a music object to the hashtabell.
	*
	* @param obj The music object that will be added.
	*/
	public void add(Music obj) {
		doubleHashtabell();
		int index = calculateIndex(obj.song);
		hashSong[index] = obj;
		currentSize++;
	}

	/**
	 * Method that finds a specified object in the hashtabell.
	 * 
	 * @param a The name of the song that will be retrived.
	 * @return The music object if it exist and null if object
	 * isn't found.
	 */
	public static Music getMusic(String a) {
		int index = 0;
		for (int i = 0; i < a.length(); i++) {
			int letterValue = (int) a.charAt(i);
			index += letterValue * (int) Math.pow(2, (a.length() - i));
		}

		int indexValue = index % hashSong.length;

		int n = 1;
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
			} else {
				System.out.println("\nSong not found. Be aware of letters with Upper Case.");
				return null;
			}
		}
		return hashSong[temp];

	}

	/**
	* Method that calculates the index at wich the object will be added.
	*
	* @param a Name of the song that will be added.
	* @return temp The index at which the object will be added.
	*/
	public static int calculateIndex(String a) {

		int index = 0;
		for (int i = 0; i < a.length(); i++) {
			int letterValue = (int) a.charAt(i);
			index += letterValue * (int) Math.pow(2, (a.length() - i));
		}

		int temp = index % hashSong.length;
		int n = 1;
		int indexValue = temp;
		while (hashSong[indexValue] != null) {
			indexValue = temp + (int) Math.pow(n, 2);
			n++;
			indexValue = indexValue % hashSong.length;
		}
		return indexValue;
	}
	
	/**
	* Method that doubles the array if the array if the array becomes 50% full. 
	*/
	private static void doubleHashtabell() {
		if (overLoad()) {
			Music[] temp = new Music[hashSong.length * 2];
			for (int i = 0; i < hashSong.length; i++) {
				temp[i] = hashSong[i];
			}
			hashSong = temp;
		}
	}
	
	/**
	*Method that checks if the array will be 50% full after next object has been added. 
	*/
	private static boolean overLoad() {
		return (currentSize + 1.0) / (hashSong.length + 0.0) > 0.5;
	}

}
