
/**
 * This class creates a playlist that contains Music.
 */
import java.io.*;
import java.util.*;

public class PlayList {

	public static ArrayList<Music> songs;
	public String playList;

	/** Constructor */
	public PlayList() {

		songs = new ArrayList<Music>();
		playList = null;

	}

	/** Constructor */
	public PlayList(String name) {
		try {
			songs = new ArrayList<Music>();
			playList = name;
			loadPlayList(playList);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Writes given playlist to textfile.
	 * 
	 * @param name of file.
	 */
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
			filout.close();

		} catch (IOException e) {
		}
	}

	/**
	 * Removes song from active playlist.
	 * 
	 * @param songName name of the song to be deleted from playlist.
	 * @return false if the song doesn't exist. True if song name exists.
	 */
	public boolean removeSong(String songName) {
		if (songName.equals("null") || songName == null) {
			return false;
		} else {
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

	}

	/**
	 * Same as the one above but is only focused on the JUnit.
	 * 
	 * @param songName name of the song to be deleted from playlist.
	 * @return false if the song doesn't exist. True if song name exists.
	 */
	public boolean removeSongForTestCase(String songName) {
		if (songName.equals("null") || songName == null) {
			return false;
		} else {
			for (int i = 0; i < songs.size(); i++) {
				if (songs.get(i).song.equals(songName)) {
					songs.remove(i);
					savePlayList("TestCase");
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Reads from a textfile (PlayList) and add it as <Music> to an
	 * ArrayList-playlist.
	 * 
	 * @param name of the textfile.
	 * @return the arraylist with music-element.
	 */
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
	 * Creates a new text file with the given name.
	 * 
	 * @param name of the textdocument.
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

	/**
	 * Used to print a playlist that looks like a table.
	 */
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
