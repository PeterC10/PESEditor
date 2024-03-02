package editor;

import java.util.List;
import java.util.LinkedList;
import java.io.File;
import java.io.RandomAccessFile;

public class Stats {
	
	final static Stat hair = new Stat(0, 45, 0, 0x7FF, "");
	
	final static Stat faceType = new Stat(0, 55, 0, 0x3, "");

	final static Stat skin = new Stat(0, 41, 6, 0x3, "");

	final static Stat face = new Stat(0, 53, 5, 0x1FF, "");

	final static Stat nameEdited = new Stat(0, 3, 0, 0x1, "");

	final static Stat callEdited = new Stat(0, 3, 2, 0x1, "");

	final static Stat shirtEdited = new Stat(0, 3, 1, 0x1, "");
	
	final static Stat abilityEdited = new Stat(0, 40, 4, 0x1, "");
	
	final static Stat callName = new Stat(0, 1, 0, 0xFFFF, "");

	final static Stat height = new Stat(1, 41, 0, 0x3F, "Height");

	final static Stat foot = new Stat(4, 5, 0, 0x01, "Foot");

	final static Stat favSide = new Stat(0, 33, 14, 0x03, "Fav side");

	final static Stat wfa = new Stat(5, 33, 11, 0x07, "W Foot Acc");

	final static Stat wff = new Stat(5, 33, 3, 0x07, "W Foot Freq");

	final static Stat injury = new Stat(6, 33, 6, 0x03, "Injury T");

	final static Stat dribSty = new Stat(5, 6, 0, 0x03, "");

	final static Stat freekick = new Stat(5, 5, 1, 0x0f, "");

	final static Stat pkStyle = new Stat(5, 5, 5, 0x07, "");

	final static Stat dkSty = new Stat(5, 6, 2, 0x03, "");

	final static Stat age = new Stat(2, 65, 9, 0x1F, "Age");

	final static Stat weight = new Stat(0, 41, 8, 0x7F, "Weight");

	final static Stat nationality = new Stat(3, 65, 0, 0x7F, "Nationality");

	final static Stat consistency = new Stat(5, 33, 0, 0x07, "Consistency");

	final static Stat condition = new Stat(5, 33, 8, 0x07, "Condition");

	final static Stat regPos = new Stat(0, 6, 4, 0xF, "");

	final static Stat gk = new Stat(0, 7, 7, 1, "GK");

	final static Stat cwp = new Stat(0, 7, 15, 1, "CWP");

	final static Stat cbt = new Stat(0, 9, 7, 1, "CBT");

	final static Stat sb = new Stat(0, 9, 15, 1, "SB");

	final static Stat dm = new Stat(0, 11, 7, 1, "DM");

	final static Stat wb = new Stat(0, 11, 15, 1, "WB");

	final static Stat cm = new Stat(0, 13, 7, 1, "CM");

	final static Stat sm = new Stat(0, 13, 15, 1, "SM");

	final static Stat am = new Stat(0, 15, 7, 1, "AM");

	final static Stat wg = new Stat(0, 15, 15, 1, "WG");

	final static Stat ss = new Stat(0, 17, 7, 1, "SS");

	final static Stat cf = new Stat(0, 17, 15, 1, "CF");

	final static Stat[] roles = { gk, new Stat(0, 7, 15, 1, "?"), cwp, cbt, sb,
			dm, wb, cm, sm, am, wg, ss, cf };

	final static Stat attack = new Stat(0, 7, 0, 0x7F, "Attack");

	final static Stat defence = new Stat(0, 8, 0, 0x7F, "Defense");

	final static Stat balance = new Stat(0, 9, 0, 0x7F, "Balance");

	final static Stat stamina = new Stat(0, 10, 0, 0x7F, "Stamina");

	final static Stat speed = new Stat(0, 11, 0, 0x7F, "Speed");

	final static Stat accel = new Stat(0, 12, 0, 0x7F, "Accel");

	final static Stat response = new Stat(0, 13, 0, 0x7F, "Response");

	final static Stat agility = new Stat(0, 14, 0, 0x7F, "Agility");

	final static Stat dribAcc = new Stat(0, 15, 0, 0x7F, "Drib Acc");

	final static Stat dribSpe = new Stat(0, 16, 0, 0x7F, "Drib Spe");

	final static Stat sPassAcc = new Stat(0, 17, 0, 0x7F, "S Pass Acc");

	final static Stat sPassSpe = new Stat(0, 18, 0, 0x7F, "S Pass Spe");

	final static Stat lPassAcc = new Stat(0, 19, 0, 0x7F, "L Pass Acc");

	final static Stat lPassSpe = new Stat(0, 20, 0, 0x7F, "L Pass Spe");

	final static Stat shotAcc = new Stat(0, 21, 0, 0x7F, "Shot Acc");

	final static Stat shotPow = new Stat(0, 22, 0, 0x7F, "Shot Power");

	final static Stat shotTec = new Stat(0, 23, 0, 0x7F, "Shot Tech");

	final static Stat fk = new Stat(0, 24, 0, 0x7F, "FK Acc");

	final static Stat curling = new Stat(0, 25, 0, 0x7F, "Swerve");

	final static Stat heading = new Stat(0, 26, 0, 0x7F, "Heading");

	final static Stat jump = new Stat(0, 27, 0, 0x7F, "Jump");

	final static Stat tech = new Stat(0, 29, 0, 0x7F, "Tech");

	final static Stat aggress = new Stat(0, 30, 0, 0x7F, "Aggression");

	final static Stat mental = new Stat(0, 31, 0, 0x7F, "Mentality");

	final static Stat gkAbil = new Stat(0, 32, 0, 0x7F, "GK");

	final static Stat team = new Stat(0, 28, 0, 0x7F, "Team Work");

	final static Stat[] ability99 = { attack, defence, balance, stamina, speed,
			accel, response, agility, dribAcc, dribSpe, sPassAcc, sPassSpe,
			lPassAcc, lPassSpe, shotAcc, shotPow, shotTec, fk, curling,
			heading, jump, tech, aggress, mental, gkAbil, team };

	final static Stat[] abilitySpecial = { new Stat(0, 21, 7, 1, "Dribbling"),
			new Stat(0, 21, 15, 1, "Tactical Drib"),
			new Stat(0, 23, 7, 1, "Positioning"),
			new Stat(0, 23, 15, 1, "Reaction"),
			new Stat(0, 25, 7, 1, "Playmaking"),
			new Stat(0, 25, 15, 1, "Passing"),
			new Stat(0, 27, 7, 1, "Scoring"),
			new Stat(0, 27, 15, 1, "1-1 Scoring"),
			new Stat(0, 29, 7, 1, "Post"),
			new Stat(0, 29, 15, 1, "Line Position"),
			new Stat(0, 31, 7, 1, "Mid shooting"),
			new Stat(0, 31, 15, 1, "Side"), new Stat(0, 19, 15, 1, "Centre"),
			new Stat(0, 19, 7, 1, "Penalties"),
			new Stat(0, 35, 0, 1, "1-T Pass"),
			new Stat(0, 35, 1, 1, "Outside"), new Stat(0, 35, 2, 1, "Marking"),
			new Stat(0, 35, 3, 1, "Sliding"), new Stat(0, 35, 4, 1, "Cover"),
			new Stat(0, 35, 5, 1, "D-L Control"),
			new Stat(0, 35, 6, 1, "Penalty GK"),
			new Stat(0, 35, 7, 1, "1-on-1 GK"),
			new Stat(0, 37, 7, 1, "Long Throw") };

	// statX = new Stat(of, 0, 27, 5, 0x7F, "StatX");
	// gkKick = new Stat(of, 0, 33, 7, 1, "GK Kick");
	// bff = new Stat(of, 0, 29, 7, 1, "B F Feint");
	// growth = new Stat(of, 5, 6, 2, 0x03, "");

	final static String[] nation;

	final static int nation_free;

	//PeterC10 MOD: Make DEFAULT_NATION match list of original nations in PES6

	final static String[] DEFAULT_NATION = {
		"Austria",
		"Belgium",
		"Bulgaria",
		"Croatia",
		"Czech Republic",
		"Denmark",
		"England",
		"Finland",
		"France",
		"Germany",
		"Greece",
		"Hungary",
		"Ireland",
		"Italy",
		"Latvia",
		"Netherlands",
		"Northern Ireland",
		"Norway",
		"Poland",
		"Portugal",
		"Romania",
		"Russia",
		"Scotland",
		"Serbia and Montenegro",
		"Slovakia",
		"Slovenia",
		"Spain",
		"Sweden",
		"Switzerland",
		"Turkey",
		"Ukraine",
		"Wales",
		"Angola",
		"Cameroon",
		"Cote d'Ivoire",
		"Ghana",
		"Nigeria",
		"South Africa",
		"Togo",
		"Tunisia",
		"Costa Rica",
		"Mexico",
		"Trinidad and Tobago",
		"United States",
		"Argentina",
		"Brazil",
		"Chile",
		"Colombia",
		"Ecuador",
		"Paraguay",
		"Peru",
		"Uruguay",
		"Iran",
		"Japan",
		"Saudi Arabia",
		"South Korea",
		"Australia",
		"Bosnia and Herzegovina",
		"Estonia",
		"Israel",
		"Honduras",
		"Jamaica",
		"Panama",
		"Bolivia",
		"Venezuela",
		"China",
		"Uzbekistan",
		"Albania",
		"Cyprus",
		"Iceland",
		"Macedonia",
		"Armenia",
		"Belarus",
		"Georgia",
		"Liechtenstein",
		"Lithuania",
		"Algeria",
		"Benin",
		"Burkina Faso",
		"Cape Verde",
		"Congo",
		"DR Congo",
		"Egypt",
		"Equatorial Guinea",
		"Gabon",
		"Gambia",
		"Guinea",
		"Guinea-Bissau",
		"Kenya",
		"Liberia",
		"Libya",
		"Mali",
		"Morocco",
		"Mozambique",
		"Senegal",
		"Sierra Leone",
		"Zambia",
		"Zimbabwe",
		"Canada",
		"Grenada",
		"Guadeloupe",
		"Martinique",
		"Netherlands Antilles",
		"Oman",
		"New Zealand",
		"Free Nationality"
	};

	final static String[] modFoot = { "R", "L" };

	final static String[] modInjury = { "C", "B", "A" };

	final static String[] modFK = { "A", "B", "C", "D", "E", "F", "G", "H" };

	final static String[] mod18 = { "1", "2", "3", "4", "5", "6", "7", "8" };

	static {
		String[] nationArray = DEFAULT_NATION;
		try {
			List<String> nationList = new LinkedList<String>();
			RandomAccessFile in = new RandomAccessFile(new File("nationality.txt"), "r");
			while (true) {
				String value = in.readLine();
				if (value == null) {
					break;
				}
				if (!value.trim().isEmpty()) {
					nationList.add(value.trim());
				}
			}
			in.close();
			nationArray = nationList.toArray(DEFAULT_NATION);
		}
		catch (Exception e) {
		}
		finally {
			nation = nationArray;
			// set free nationality
			int nation_free_guess = -1;
			for (int i=0; i<nation.length; i++) {
				if (nation[i].toLowerCase().startsWith("free ")) {
					nation_free_guess = i;
				}
			}
			nation_free = (nation_free_guess != -1) ? nation_free_guess : 0;
			// DEBUG:
			// for (int i=0; i<nation.length; i++) {
			// 	System.out.println("nationality: " + i + ": {" + nation[i] + "}");
			// }
		}
	}

	public static int getValue(OptionFile of, int player, Stat stat) {

		int a = Player.startAdr + 48 + (player * 124) + stat.offSet;
		if (player >= Player.firstEdit) {
			a = Player.startAdrE + 48 + ((player - Player.firstEdit) * 124)
					+ stat.offSet;
		}
		// System.out.println(a);
		int val = (of.toInt(of.data[a]) << 8) | of.toInt(of.data[a - 1]);
		// System.out.println(val);
		val = val >>> stat.shift;
		// System.out.println(val);
		val = val & stat.mask;
		// System.out.println(val);
		return val;
	}

	public static void setValue(OptionFile of, int player, Stat stat, int v) {
		int a = Player.startAdr + 48 + (player * 124) + stat.offSet;
		if (player >= Player.firstEdit) {
			a = Player.startAdrE + 48 + ((player - Player.firstEdit) * 124)
					+ stat.offSet;
		}
		int old = (of.toInt(of.data[a]) << 8) | of.toInt(of.data[a - 1]);
		int oldMask = 0xFFFF & (~(stat.mask << stat.shift));
		old = old & oldMask;
		v = v & stat.mask;
		v = v << stat.shift;
		v = old | v;
		of.data[a - 1] = of.toByte(v & 0xff);
		of.data[a] = of.toByte(v >>> 8);
	}

	public static void setValue(OptionFile of, int player, Stat stat, String s) {
		int v = 0;
		try {
			if (stat.type == 0) {
				v = Integer.parseInt(s);
			}
			if (stat.type == 1) {
				v = Integer.parseInt(s) - 148;
			}
			if (stat.type == 2) {
				v = Integer.parseInt(s) - 15;
			}
			if (stat.type == 3) {
				boolean matched = false;
				for (int i = 0; i < nation.length; i++) {
					if (s.equals(nation[i])) {
						v = i;
						matched = true;
						break;
					}
				}
				if (!matched) {
					// default to Free Nationality
					v = nation_free;
				}
				// System.out.println(v);
				/*
				 * if (v == 118) { v = 291; } else { if (v > 56) { v = v + 164; } }
				 */
				// System.out.println(v);
			}
			if (stat.type == 4) {
				for (int i = 0; i < modFoot.length; i++) {
					if (s.equals(modFoot[i])) {
						v = i;
					}
				}
			}
			if (stat.type == 5) {
				v = Integer.parseInt(s) - 1;
			}
			if (stat.type == 6) {
				for (int i = 0; i < modInjury.length; i++) {
					if (s.equals(modInjury[i])) {
						v = i;
					}
				}
			}
			if (stat.type == 7) {
				for (int i = 0; i < modFK.length; i++) {
					if (s.equals(modFK[i])) {
						v = i;
					}
				}
			}
			setValue(of, player, stat, v);
		} catch (NumberFormatException nfe) {
		}
	}

	public static String getString(OptionFile of, int player, Stat stat) {
		String result = "";
		int val = getValue(of, player, stat);

		if (stat.type == 0) {
			result = String.valueOf(val);
		}
		if (stat.type == 1) {
			result = String.valueOf(val + 148);
		}
		if (stat.type == 2) {
			result = String.valueOf(val + 15);
		}
		if (stat.type == 3) {
			// System.out.println(val);
			if (val >= 0 && val < nation.length) {
				result = nation[val];
			} else {
				result = String.valueOf(val) + "?";
			}
		}
		if (stat.type == 4) {
			result = "R";
			if (val == 1) {
				result = "L";
			}
		}
		if (stat.type == 5) {
			result = String.valueOf(val + 1);
		}
		if (stat.type == 6) {
			result = "A";
			if (val == 1) {
				result = "B";
			}
			if (val == 0) {
				result = "C";
			}
		}
		if (stat.type == 7) {
			result = modFK[val];
		}
		return result;
	}
}
