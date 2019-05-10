import java.util.*;

public class Music implements Comparator<Music>{
	
	public String band;
	public String song;
	public int playtime;
	public String fileCode;
	
	public Music(String b, String s, int l, String c) {
		band = b;
		song = s;
		playtime = l;
		fileCode = c;
	}
	
	public Music() {
		band = null;
		song = null;
		playtime = 0;
		fileCode = null;
	}
	
	public String toString() {
		return band + " - " + song + " - " + playtime;
	}
}
