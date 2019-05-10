import java.util.*;

public class Music implements Comparator<Music>{
	
	public String band;
	public String song;
	public int songLength;
	public String fileCode;
	
	public Music(String b, String s, int l, String c) {
		band = b;
		song = s;
		songLength = l;
		fileCode = c;
	}
	
	public Music() {
		band = null;
		song = null;
		songLength = 0;
		code = null;
	}
	
	public String toString() {
		return band + " - " + song + " - " + songLength;
	}
}
