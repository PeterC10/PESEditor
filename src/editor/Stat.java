package editor;

public class Stat {

	public int type;

	public int offSet;

	public int shift;

	public int mask;

	public String name;
	
	public Stat(int t, int os, int s, int m, String n) {
		type = t;
		offSet = os;
		shift = s;
		mask = m;
		name = n;
	}
}
