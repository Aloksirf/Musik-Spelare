package muplay;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayList {

	static ArrayList<Music> Songs=new ArrayList<Music>();
	static String playList="";
	
	public static void savePlayList( String name) {
		 PrintWriter filout=null;
	      
	      try{

		filout=new PrintWriter(new BufferedWriter(new FileWriter(name+".txt", false)));
		String output = "";
		@SuppressWarnings("unchecked")
		ArrayList<Music> CopySongs = (ArrayList<Music>) Songs.clone();
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
	public static ArrayList<Music> loadPlayList(String name) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner reader= new Scanner(new FileReader(new File(name+".txt")));
		
		while(reader.hasNext()) {
			String line=reader.nextLine();
			String[] info=line.split(", ");
			
			Music mus=new Music(info[0],info[1],Long.parseLong(info[2]),info[3] );
			Songs.add(mus);
			
			
		}
		
		return Songs;
	}
	public void addToPlayList(String SongName, Hashing lib) throws FileNotFoundException {
		Songs.add(lib.GetSong(SongName));
		
		savePlayList(playList);
		loadPlayList(playList);
		
	}
	public Music[] sortPlayList() {
		return null;
		
	}
	public static void printPlayList() {
		for(int i =0; i<Songs.size();i++)
		Songs.get(i).printInfo();
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
