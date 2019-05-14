
import java.util.ArrayList;
import java.util.Comparator;

/**
 * är en konstruktor som lagrar musikfiler i en hashlista.
 * @author Master booom
 *
 * @param <Music>
 */
public class Hashing {

	public Music[] hashSong;
	public int currentSize;
	
	public Hashing() {
		hashSong = new Music[100];
		currentSize = 0;
	}
	
	public void add(Music song) {
		doubleHashTabell();
		int index = getIndex(song);
		hashSong[index] = song;
		
	}
	/**
	 * Method that finds the object in the hashtabell.
	 */
	public Music getMusic(Music song) {
		String a = obj.song;

		int index = 0;
		for (int i = 0; i < a.length(); i++) {
			int letterValue = (int) a.charAt(i);
			index += letterValue * 2 ^ (a.length() - i);
		}

		int indexValue = index % hashSong.length;

		int n = 1;
		int temp = indexValue;
		while (hashSong[temp] != song) {
			temp = indexValue + n ^ 2;
			n++;
			temp = temp % hashSong.length;
		}

		return hashSong[temp];
	}
	
	/**
	 * Returns a music array with the songs from a chosen band. 
	 * @param Name
	 * @return
	 */
	public Music[] GetBand(String Name) {
		int index= 0;
		Music[] Array=new Music[150];
		String Code=Name;
		for(int i=0;i<Code.length() ; i++) {
			index+=(int)Code.charAt(i);
		}
		while(HashBand[index].band.compareTo(Name)!=0) {
			index++;
		}
		for(int i=0;HashBand[index+i].band.compareTo(Name)==0;i++) {
			Array[i]=HashBand[index+i];
		}
		return Array;
	}
	/**
	 * Returns a Music with the same song name.
	 * @param Name
	 * @return
	 */
	public Music GetSong(String Name) {
		int index= 0;
		String Code=Name;
		for(int i=0;i<Code.length() ; i++) {
			index+=(int)Code.charAt(i);
		}
		while(HashSong[index].song.compareTo(Name)!=0) {
			index++;
		}
		return HashBand[index];
	}
	
	
	/**
	 * Returns a Music array with songs with the same PlayTime.
	 * @param time
	 * @return
	 */
	public Music[] GetTime(long time) {
		  int index= (int)time;
		Music[] Array=new Music[40];

		while(HashTime[index].playtime-time!=0) {
			index++;
		}
		for(int i=0;HashTime[index+i].playtime-time==0; i++) {
			Array[i]=HashTime[index+i];
		}
		
		return Array;
	}
	
	public static int getIndex(Music obj) {
		String a = obj.song;

		int index = 0;
		for (int i = 0; i < a.length(); i++) {
			int letterValue = (int) a.charAt(i);
			index += letterValue * 2 ^ (a.length() - i);
		}

		int indexValue = index % hashSong.length;

		int n = 1;

		int temp = indexValue;

		while (hashSong[temp] != null) {
			temp = indexValue + n ^ 2;
			n++;
			temp = temp % hashSong.length;
		}

		return temp;
	}
	
	public static void doubleHashtabell() {
		if(overLoad()) {
			String[] temp = new String[hashSong.length*2];
			for(int i = 0; i < hashSong.length; i++) {
				temp[i] = hashSong[i];
			}
			hashSong = temp;
		}		
	}
	
	public static boolean overLoad() {
		return (currentSize+1.0)/(hashSong.length+0.0) > 0.5;
	}
	
}
