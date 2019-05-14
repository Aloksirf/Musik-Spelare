

import java.util.ArrayList;
import java.util.Comparator;

/**
 * är en konstruktor som lagrar musikfiler i en hashlista.
 * @author Master booom
 *
 * @param <Music>
 */
public class Hashing {

	public ArrayList<Music> HashBand;
	public ArrayList<Music> HashSong;
	public ArrayList<Music> HashTime;
	
	public Hashing() {
		HashBand=null;
		HashSong=null;
		HashTime=null;
	}
	
	/**
	 * Adds a music in all hashlists.
	 * @param Song
	 */
	public void addInAll(Music Song) {
		addByBand(Song);
		addBySong(Song);
		addByTime(Song);
		
	}
	private void addByBand(Music Song) {
		int index= 0;
		String bandCode=Song.getBand();
		for(int i=0;i<bandCode.length() ; i++) {
			index+=(int)bandCode.charAt(i);
		}
		//checks where to add, sorted manner.
		if(HashBand.get(index)!=null) {
			while(HashBand.get(index).band.compareTo(Song.band)>0||HashBand.get(index)!=null) {
				index++;
			}
		}
		HashBand.add(index, Song);
	}
	private void addBySong(Music Song) {
		int index= 0;
		String Code=Song.getSong();
		for(int i=0;i<Code.length() ; i++) {
			index+=(int)Code.charAt(i);
		}
		
		if(HashSong.get(index)!=null) {
			while(HashSong.get(index).song.compareTo(Song.song)>0||HashSong.get(index)!=null) {
				index++;
			}
		}
		HashBand.add(index, Song);


		
		
	}
	private void addByTime(Music Song) {
		int Code=(int)Song.playtime;
		
		while(HashTime.get(Code).playtime-Song.playtime!=0|| HashTime.get(Code)!=null)
			Code++;
		HashTime.add(Code, Song);
		
		
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
		while(HashBand.get(index).band.compareTo(Name)!=0) {
			index++;
		}
		for(int i=0;HashBand.get(index+i).band.compareTo(Name)==0;i++) {
			Array[i]=HashBand.get(index+i);
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
		while(HashSong.get(index).song.compareTo(Name)!=0) {
			index++;
		}
		return HashBand.get(index);
	}
	
	
	/**
	 * Returns a Music array with songs with the same PlayTime.
	 * @param time
	 * @return
	 */
	public Music[] GetTime(long time) {
		  int index= (int)time;
		Music[] Array=new Music[40];

		while(HashTime.get(index).playtime-time!=0) {
			index++;
		}
		for(int i=0;HashTime.get(index+i).playtime-time==0; i++) {
			Array[i]=HashTime.get(index+i);
		}
		
		return Array;
	}

	
}
