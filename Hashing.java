package muplay;

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
	
	
	public void addInAll(Music Song) {
		addByBand(Song);
		addBySong(Song);
		addByTime(Song);
		
	}
	public void addByBand(Music Song) {
		int index= 0;
		String bandCode=Song.getBand();
		for(int i=0;i<bandCode.length() ; i++) {
			index+=(int)bandCode.charAt(i);
		}
		

		HashBand.add(index, Song);
	}
	public void addBySong(Music Song) {
		int index= 0;
		String Code=Song.getSong();
		for(int i=0;i<Code.length() ; i++) {
			index+=(int)Code.charAt(i);
		}
		

		HashSong.add(index, Song);
		
		
	}
	public void addByTime(Music Song) {
		int Code=Song.playtime;
		HashTime.add(Code, Song);
		
		
	}
	
	
	
	public Music[] GetBand(String Name) {
		int index= 0;
		String Code=Name;
		for(int i=0;i<Code.length() ; i++) {
			index+=(int)Code.charAt(i);
		}
		while(HashBand.get(index).band.compareTo(Name)!=0) {
			index++;
		}
		return HashBand.get(index);
	}
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
	
	
	public Music[] GetTime(int time) {
		int index= time;
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
