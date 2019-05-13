
public class Music {
	
	public String band;
	public String song;
	public long playtime;
	public String fileCode;
	
	public Music(String b, String s, long l, String c) {
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
	
	public String getBand() {
		return band;
	}
	
	public String getSong() {
		return song;
	}
	
	public String toString() {
		return band + " - " + song + " - " + playtime;
}
	public void printInfo() {
		System.out.println(band+": "+song+ " with PlayTime: "+playtime);
	}
}
