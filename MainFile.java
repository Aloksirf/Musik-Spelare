
public class MainFile {

	public static Hashing library;
	
	public static void main(String[] args) {
		library = new Hashing();
		readFile.readInFile(library);
		MusicPlayer.getPlayList = "Library.txt";
		System.out.println("------------- Printing Library File -------------");
		MusicPlayer.myPlayList.loadPlayList("Library");
		MusicPlayer m = new MusicPlayer();
		m.setVisible(true);
	}
}
