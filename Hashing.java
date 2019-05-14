
import java.util.ArrayList;
import java.util.Comparator;

/**
 * är en konstruktor som lagrar musikfiler i en hashlista.
 * @author Master booom
 *
 * @param <Music>
 */
public class Hashing {

	public Music[] HashBand;
	public Music[] HashSong;
	public Music[] HashTime;
	
	public Hashing() {
		HashBand=new Music[10000];
		HashSong=new Music[10000];
		HashTime=new Music[500];
	}
	
	/**
	 * Adds a music in all hashlists.
	 * @param Song
	 */
	public void addInAll(Music Song) {
		Song.printInfo();
		addByBand(Song);
		addBySong(Song);
		//addByTime(Song);
		
	}
	private void addByBand(Music Song) {
		int index= 0;
		String bandCode=Song.getBand();
		for(int i=0;i<bandCode.length() ; i++) {
			index+=(int)bandCode.charAt(i);
		}
		
		//checks where to add, sorted manner.
		if(HashBand[index]!=null) {
			
			while(HashBand[index].band.compareTo(Song.band)>0||HashBand[index]!=null) {
				index++;
			}
		}
		HashBand[index]= Song;
		
	}
	private void addBySong(Music Song) {
		int index= 0;
		String Code=Song.getSong();
		
		for(int i=0;i<Code.length() ; i++) {
			index+=(int)Code.charAt(i);
		}
		
		if(HashSong[index]!=null) {
			while(HashSong[index].song.compareTo(Song.song)>0||HashSong[index]!=null) {
				index++;
			}
		}
		HashBand[index]= Song;
		

		
		
	}
	//gives nullpointer-exception.
	private void addByTime(Music Song) {
		int Code=(int)Song.playtime;
		
		while(HashTime[Code].playtime-Song.playtime!=0|| HashTime[Code]!=null)
			Code++;
		HashTime[Code]= Song;
		
		System.out.println("time");
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

	
}
