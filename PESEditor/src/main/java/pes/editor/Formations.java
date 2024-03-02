package pes.editor;

public class Formations {
	static final int startAdr = 677202;
	
	static final int size = 364;
	
	static final int abcSize = 82;
	
	static byte getPos(OptionFile of, int squad, int abc, int i) {
		return of.data[startAdr + 138 + (size * squad) + (abc * abcSize) + i];
	}
	
	static void setPos(OptionFile of, int squad, int abc, int i, int p) {
		of.data[startAdr + 138 + (size * squad) + (abc * abcSize) + i] = (byte)p;
	}
	
	static byte getSlot(OptionFile of, int squad, int i) {
		return of.data[startAdr + 6 + (size * squad) + i];
	}
	
	static void setSlot(OptionFile of, int squad, int i, byte p) {
		of.data[startAdr + 6 + (size * squad) + i] = p;
	}
	
	static byte getJob(OptionFile of, int squad, int j) {
		return of.data[startAdr + 111 + (size * squad) + j];
	}
	
	static void setJob(OptionFile of, int squad, int j, byte i) {
		of.data[startAdr + 111 + (size * squad) + j] = i;
	}
	
	static byte getX(OptionFile of, int squad, int abc, int i) {
		return of.data[startAdr + 118 + (size * squad) + (abc * abcSize) + (2 * (i - 1))];
	}
	
	static byte getY(OptionFile of, int squad, int abc, int i) {
		return of.data[startAdr + 119 + (size * squad)  + (abc * abcSize) + (2 * (i - 1))];
	}
	
	static void setX(OptionFile of, int squad, int abc, int i, int x) {
		of.data[startAdr + 118 + (size * squad) + (abc * abcSize) + (2 * (i - 1))] = (byte)x;
	}
	
	static void setY(OptionFile of, int squad, int abc, int i, int y) {
		of.data[startAdr + 119 + (size * squad)  + (abc * abcSize) + (2 * (i - 1))] = (byte)y;
	}
	
	static boolean getAtk(OptionFile of, int squad, int abc, int i, int direction) {
		boolean result = false;
		byte t = of.data[startAdr + 149 + (size * squad)  + (abc * abcSize) + i];
		if (((t >>> direction) & 1) == 1) {
			result = true;
		}
		return result;
	}
	
	static void setAtk(OptionFile of, int squad, int abc, int i, int direction) {
		if (direction < 0) {
			of.data[startAdr + 149 + (size * squad)  + (abc * abcSize) + i] = 0;
		} else {
			int t = of.data[startAdr + 149 + (size * squad)  + (abc * abcSize) + i];
			t = t ^ (1 << direction);
			of.data[startAdr + 149 + (size * squad)  + (abc * abcSize) + i] = (byte)t;
		}
	}
	
	static byte getDef(OptionFile of, int squad, int abc, int i) {
		return of.data[startAdr + 160 + (size * squad)  + (abc * abcSize) + i];
	}
	
	static void setDef(OptionFile of, int squad, int abc, int i, int d) {
		of.data[startAdr + 160 + (size * squad)  + (abc * abcSize) + i] = (byte)d;
	}
	
	static byte getStrat(OptionFile of, int squad, int button) {
		return of.data[startAdr + 102 + (size * squad)  + button];
	}
	
	static void setStrat(OptionFile of, int squad, int button, int strat) {
		of.data[startAdr + 102 + (size * squad)  + button] = (byte)strat;
	}
	
	static byte getStratOlCB(OptionFile of, int squad) {
		return of.data[startAdr + 106 + (size * squad)];
	}
	
	static void setStratOlCB(OptionFile of, int squad, int cb) {
		of.data[startAdr + 106 + (size * squad)] = (byte)cb;
	}
	
	static boolean getStratAuto(OptionFile of, int squad) {
		boolean auto = false;
		if (of.data[startAdr + 107 + (size * squad)] == 1) {
			auto = true;
		}
		return auto;
	}
	
	static void setStratAuto(OptionFile of, int squad, boolean auto) {
		byte b = 0;
		if (auto) {
			b = 1;
		}
		of.data[startAdr + 107 + (size * squad)] = b;
	}
	
	static byte getTeam(OptionFile of, int squad, int abc, int set) {
		return of.data[startAdr + 194 + (size * squad) + (abc * abcSize) + set];
	}
	
	static void setTeam(OptionFile of, int squad, int abc, int set, int v) {
		of.data[startAdr + 194 + (size * squad)  + (abc * abcSize) + set] = (byte)v;
	}
	
}
