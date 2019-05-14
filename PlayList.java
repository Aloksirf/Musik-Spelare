
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class PlayList {
	
	
	

	static ArrayList<Music> songs;
	static String playList="";
	
	
	
	/**
	 * Makes a new playlist pop up window to add name in.
	 */
	public PlayList() {
		
		JFrame frame=new JFrame("New Playlist");
			
		songs=new ArrayList<Music>();
		
		JTextField textField=new JTextField("Please write a save file name.");
		frame.getContentPane().add(textField);
		
		playList=textField.getText();

		savePlayList(playList);

	}
	public PlayList(String name) throws FileNotFoundException {
		
		songs=new ArrayList<Music>();
		playList=name;
		//Loads a playlist.
		loadPlayList(playList);
		
	}
	
	
	public static void savePlayList( String name) {
		 PrintWriter filout=null;
	      
	      try{

		filout=new PrintWriter(new BufferedWriter(new FileWriter(name+".txt", false)));
		String output = "";
		@SuppressWarnings("unchecked")
		ArrayList<Music> CopySongs = (ArrayList<Music>) songs.clone();
		while(!CopySongs.isEmpty()) {
			output = CopySongs.get(0).band+ ", " +CopySongs.get(0).song+ ", " + CopySongs.get(0).playtime + ", "+ CopySongs.get(0).fileCode;
			CopySongs.remove(0);
	         filout.println(output);
		}
		

	         
	      	

	      }
	      	
	      catch ( IOException e){}
	      
	      finally{
	         filout.close();
	      
	      
	      }
		
		
	}
	public static void createPlaylist(Music[] hashlist){
		for(int i = 0; i < hashlist.length; i++){
			if(hashlist[i] != null){
				songs.add(hashlist[i]);
			}
		}
	}	
	public static ArrayList<Music> loadPlayList(String name) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader= new Scanner(new FileReader(new File(name)));
		
		while(reader.hasNext()) {
			String line=reader.nextLine();
			String[] info=line.split(", ");
			
			Music mus=new Music(info[0],info[1],Long.parseLong(info[2]),info[3] );
			songs.add(mus);
			
			
		}
		
		return songs;
	}
	/**
	 * 
	 * @param SongName name of the song to add
	 * @param lib what hash to get music file from.
	 * @throws FileNotFoundException
	 */
	public void addToPlayList(String SongName, Hashing lib) throws FileNotFoundException {
		songs.add(lib.GetSong(SongName));
		
		savePlayList(playList);
		loadPlayList(playList);
		
	}
	/**
	 * Method that sorts a list according to the users input.
	 * 
	 * @param pref     User input on how the list will be sorted.
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
		for(int i =0; i<songs.size();i++)
		songs.get(i).printInfo();
	}
	/*public static void main(String[] args) {
		
		try {
			ArrayList<Music> test=loadPlayList("list");
			savePlayList("test");
			printPlayList();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}*/
	
}
