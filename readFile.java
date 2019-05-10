import java.io.*;
import java.util.*;

public class readFile {

	public static void main(String[] args) {
		System.out.println("What would you like to do? ");
		System.out.println("1. Add a new Song to a list");
		System.out.println("2. Get a list");
		System.out.println("3. Exit");

		try {
			Scanner readIn = new Scanner(System.in);
			int scan = readIn.nextInt();

			while (scan != 3) {
				System.out.println("Type in your choice: ");
				scan = readIn.nextInt();
				switch (scan) {

				case 1: {
					System.out.println("Type the name of the list you wish to add:");
					String scann = readIn.next();
					addSong(scann);
					break;
				}
				case 2: {
					System.out.println("Type in the name of the list:");
					String read = readIn.next();
					ArrayList<Music> a = readInFile(read);
					for (int i = 0; i < a.size(); i++) {
						System.out.println(a.get(i));
					}
					System.out.println();
					// Music b = a.get(0);
					// System.out.println(a);
					break;
				}
				case 4: {
					break;
				}
				case 3: {
					System.exit(0);
					break;
				}
				}

			}
			readIn.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Reads in a file and creates new music objects to an ArrayList
	 * 
	 * @param fileName The name of the file.
	 * @return The ArrayList filled with music-objects.
	 */
	public static ArrayList<Music> readInFile(String fileName) {
		try {
			Scanner read = new Scanner(new File(fileName));
			ArrayList<Music> newList = new ArrayList<Music>();

			while (read.hasNext()) {
				String line = read.nextLine();
				String[] split = line.split(", ");
				Music mus = new Music(split[0], split[1], Integer.parseInt(split[2]), split[3]);
				newList.add(mus);
			}

			read.close();
			return newList;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
	/**
	 * Adds a song to the decided list.
	 * 
	 * @param listName		the name of the list. 
	 */
	public static void addSong(String listName) {
		try {
			Scanner in = new Scanner(System.in);
			PrintWriter write = new PrintWriter(new FileWriter(listName), true);
			System.out.println("Type in the name of the Band/Group/Artist:");
			write.print(in.nextLine() + ", ");
			System.out.println("Type in the name of the Song:");
			write.print(in.nextLine() + ", ");
			System.out.println("Type in the length of the Song (in seconds):");
			write.print(in.nextLine() + ", ");
			System.out.println("Type in the name of the file:");
			write.println(in.nextLine());

			in.close();
			write.close();
			return;

		} catch (Exception e) {
			System.out.println(e);
		}
		return;
	}

}
