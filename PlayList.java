
import java.io.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class PlayList {

	public static ArrayList<Music> songs;
	public String playList;

	/**
	 * Makes a new playlist pop up window to add name in.
	 */
	public PlayList() {

		songs = new ArrayList<Music>();
		playList = null;

	}

	public PlayList(String name) {
		try {
			songs = new ArrayList<Music>();
			playList = name;
			loadPlayList(playList); // Loads a playlist.
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void savePlayList(String name) {
		PrintWriter filout = null;

		try {

			filout = new PrintWriter(new BufferedWriter(new FileWriter(name + ".txt", false)));
			String output = "";
			@SuppressWarnings("unchecked")
			ArrayList<Music> CopySongs = (ArrayList<Music>) songs.clone();
			while (!CopySongs.isEmpty()) {
				output = CopySongs.get(0).band + ", " + CopySongs.get(0).song + ", " + CopySongs.get(0).playtime + ", "
						+ CopySongs.get(0).fileCode;
				CopySongs.remove(0);
				filout.println(output);
			}

		}

		catch (IOException e) {
		}

		finally {
			filout.close();
		}

	}

	public boolean removeSong(String songName) {
		String pName = MusicPlayer.getPlayList;
		pName = pName.replaceAll(".txt", "");
		for (int i = 0; i < songs.size(); i++) {
			if (songs.get(i).song.equals(songName)) {
				songs.remove(i);
				savePlayList(pName);
				return true;
			}
		}
		return false;

	}

	public ArrayList<Music> loadPlayList(String name) {
		try {
			Scanner reader = new Scanner(new FileReader(new File(name + ".txt")));
			songs = new ArrayList<Music>();
			while (reader.hasNext()) {
				String line = reader.nextLine();
				String[] info = line.split(", ");
				Music mus = new Music(info[0], info[1], Long.parseLong(info[2]), info[3]);
				songs.add(mus);
			}
			reader.close();
			return songs;
		} catch (Exception e) {
		}
		return songs;
	}

	/**
	 * Creates a new .txt file with the chosen name.
	 * 
	 * @param name is the name of the textdocument.
	 */
	public void makePlaylist(String name) {
		try {
			PrintWriter filout = new PrintWriter(new BufferedWriter(new FileWriter(name + ".txt", false)));
			filout.close();
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 * @param SongName name of the song to add
	 * @param lib      what hash to get music file from.
	 * @throws FileNotFoundException
	 */
	public boolean addToPlayList(String songName, Hashing lib) {
		if (lib.getMusic(songName) != null) {
			songs.add(lib.getMusic(songName));
			return true;
		}
		return false;
	}

	/**
	 * Method that sorts a list according to the users input.
	 * 
	 * @param pref User input on how the list will be sorted.
	 * @return sortedList The sorted list.
	 */
	public void sortList(String pref) {

		if (pref.equals("band")) {
			Collections.sort(songs, new BandComparator());
		} else if (pref.equals("song")) {
			Collections.sort(songs, new SongComparator());
		} else if (pref.equals("playtime")) {
			Collections.sort(songs, new PlaytimeComparator());
		} else if (pref.equals("filecode")) {
			Collections.sort(songs, new FilecodeComparator());
		}
	}

	public static void printPlayList() {
		final Object[][] table = new String[songs.size()][];
		for (int i = 0; i < songs.size(); i++) {
			table[i] = new String[] { songs.get(i).band, songs.get(i).song, songs.get(i).playtime + "s" };
		}
		for (final Object[] row : table) {
			System.out.format("%-22s%-22s%-22s\n", row);
		}
	}

	/**
	 * Checks if the String is a band or not. Used to print if true.
	 * 
	 * @param band The band you're searching for.
	 * @return True if you find the band, else return false.
	 */
	public static boolean checkBandExistence(String band) {
		for (int i = 0; i < songs.size(); i++) {
			if (band.equals(songs.get(i).band)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Prints all the songs from a specific Band.
	 * 
	 * @param band The band you wish to see all their songs.
	 */
	public static void printSongsFromBand(String band) {
		for (int i = 0; i < songs.size(); i++) {
			if (band.equals(songs.get(i).band)) {
				System.out.println(songs.get(i).song);
			}
		}
	}

}
