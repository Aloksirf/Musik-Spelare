import java.io.File;
import java.util.Scanner;

public class MainFile {

	public static Hashing library;
	
	public static void main(String[] args) {
		library = new Hashing();
		readInFile(library);
		MusicPlayer m = new MusicPlayer();
		System.out.println("------------- Printing Library File -------------");
		MusicPlayer.myPlayList.loadPlayList("Library");
		m.setVisible(true);
	}
	
	/**
	 * Reads in a file and creates new music objects to an ArrayList
	 * 
	 * @param fileName The name of the file.
	 * @return The ArrayList filled with music-objects.
	 */
	public static void readInFile(Hashing toHash) {
		
		try {
			Scanner read = new Scanner(new File("Library.txt"));
			while (read.hasNext()) {
				String line = read.nextLine();
				String[] split = line.split(", ");
				Music mus = new Music(split[0], split[1], Integer.parseInt(split[2]), split[3]);
				toHash.add(mus);
			}
			read.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
