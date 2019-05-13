import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.*;

public class readFile {
	static Scanner in = new Scanner(System.in);
	static PrintWriter write = null;
	static Clip musicFile = null;
	static JTextField jfText;
	static int counter = 0;

	public static void main(String[] args) {

		printMenu();
		int scan = in.nextInt();

		while (scan != 4) {
			choices(scan);
			printMenu();
			scan = in.nextInt();
			in.nextLine();
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
	 * @param listName the name of the list.
	 */
	public static void addSong(String listName) {
		try {
			String total = "";

			System.out.println("Type in the name of the Band/Group/Artist:");
			total += in.nextLine() + ", ";
			System.out.println("Type in the name of the Song:");
			total += in.nextLine() + ", ";
			System.out.println("Type in the length of the Song (in seconds):");
			total += in.nextInt() + ", ";
			System.out.println("Type in the name of the file:");
			String temp = in.nextLine();
			temp = in.nextLine();
			total += temp;

			write = new PrintWriter(new BufferedWriter(new FileWriter((listName), true)));
			write.println(total);

			write.close();
			return;

		} catch (Exception e) {
			System.out.println(e);
		}
		return;
	}

	/**
	 * Prints the menu.
	 */
	public static void printMenu() {
		System.out.println("What would you like to do? ");
		System.out.println("1. Add a new Song to a list");
		System.out.println("2. Get a list");
		System.out.println("3. Start MusicPlayer");
		System.out.println("4. Exit");

		System.out.print("\nEnter your choice: ");
	}

	public static void choices(int scan) {
		switch (scan) {

		case 1: {
			System.out.println("Type the name of the list you wish to add:");
			String scann = in.nextLine();
			addSong(scann);
			break;
		}
		case 2: {
			System.out.println("Type in the name of the list:");
			String read = in.next();
			ArrayList<Music> a = readInFile(read);
			for (int i = 0; i < a.size(); i++) {
				System.out.println(a.get(i));
			}
			System.out.println();
			break;
		}
		case 3: {
			musicPlayer mus = new musicPlayer();
			mus.setVisible(true);
			mus.setAlwaysOnTop(false);
			break;
		}
		case 4: {
			System.exit(0);
			break;
		}
		}
	}

}
