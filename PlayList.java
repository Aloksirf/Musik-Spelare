import java.util.ArrayList;

public class PlayList {

	ArrayList<E> Songs=null;
	
	public void savePlayList( String name) {
		
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
			String line=reader.nextLine();
			String[] info=line.split(", ");
			
			Music mus=new Music(info[0],info[1],Long.parseLong(info[2]),info[3] );
			Songs.add(mus);
			
			
		}
		
		return null;
	}
	public void addToPlayList() {
		
	}
	public Music[] sortPlayList() {
		return null;
		
	}
	
}
