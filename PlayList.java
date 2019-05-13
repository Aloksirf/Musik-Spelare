import java.util.ArrayList;

public class PlayList {

	
	
	public void savePlayList(ArrayList<E> Songs, String name) {
		
		String output = "";
		while(!Songs.isEmpty()) {
			output = Songs.remove(0) + /n;
		}
		
		 PrintWriter filout=null;
	      
	      try{
	      
	         filout=new PrintWriter(new BufferedWriter(new FileWriter(name, true)));
	         
	      	
	         filout.write(output);
	      }
	      	
	      catch ( IOException e){}
	      
	      finally{
	         filout.close();
	      
	      
	      }
		
		
	}
	public Music[] loadPlayList(String name) throws FileNotFoundException {
		Scanner reader= new Scanner(new FileReader(new File(name+".txt")));
		
		while(reader.hasNext()) {
			Music mus=();
			
			
		}
		
		return null;
	}
	public void addToPlayList() {
		
	}
	public Music[] sortPlayList() {
		return null;
		
	}
	
}
