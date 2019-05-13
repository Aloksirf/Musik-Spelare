
public class PlayList {

	
	
	public void savePlayList() {
		System.out.println("asda");
		
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
