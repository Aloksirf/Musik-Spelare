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

	ArrayList<Music> Songs=null;
	String playList="";
	
	public void savePlayList( String name) {
		playList=name;
		String output = "";
		ArrayList<Music> CopySongs = Songs;
		while(!Songs.isEmpty()) {
			output = CopySongs.get(0).band+ ", " +CopySongs.get(0).song+ ", " + CopySongs.get(0).playtime + ", "+ CopySongs.get(0).fileCode +"/n";
			CopySongs.remove(0);
		}
		
		 PrintWriter filout=null;
	      
	      try{
	      
	         filout=new PrintWriter(new BufferedWriter(new FileWriter(name, false)));
	         
	      	
	         filout.write(output);
	      }
	      	
	      catch ( IOException e){}
	      
	      finally{
	         filout.close();
	      
	      
	      }
		
		
	}
	public ArrayList<Music> loadPlayList(String name) throws FileNotFoundException {
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
	
}
