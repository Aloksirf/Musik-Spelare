
public class MainFile {

	public static Hashing library;
	
	public static void main(String[] args) {
		library = new Hashing();
		readFile.readInFile(library);
		MusicPlayer m = new MusicPlayer();
		m.setVisible(true);
	}
}
