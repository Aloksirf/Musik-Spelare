
import java.io.*;
import java.util.*;

public class readFile {
	Scanner in = new Scanner(System.in);
	PrintWriter write = null;

	/**
	 * Reads in a file and creates new music objects to an ArrayList
	 * 
	 * @param fileName The name of the file.
	 * @return The ArrayList filled with music-objects.
	 */
	public static void readInFile(Hashing toHash) {
		try {
			Scanner read = new Scanner(new File("Library.txt"));
			System.out.println("Current Music Library:");
			while (read.hasNext()) {
				String line = read.nextLine();
				String[] split = line.split(", ");
				Music mus = new Music(split[0], split[1], Integer.parseInt(split[2]), split[3]);
				toHash.add(mus);
				System.out.println(mus.toString());
			}

			read.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
