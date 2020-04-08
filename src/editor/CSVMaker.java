package editor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class CSVMaker {
	private static String[] team;

	private static char separator = ',';

	private OptionFile of;
	private final CSVAttributes csvAttributes = new CSVAttributes();
	private final int[] wristbandVals = csvAttributes.getWristbandVals();
	private final Map<Integer, String> wristbandOptsByValue = csvAttributes.getWristbandOptsByValue();
	private final Map<Integer, String> physicalOptsByValue = csvAttributes.getPhysicalOptsByValue();
	private final Map<Integer, String> physicalLinkedOptsByValue = csvAttributes.getPhysicalLinkedOptsByValue();
	private final Map<Integer, String> headHeightOptsByValue = csvAttributes.getHeadHeightOptsByValue();
	private final Map<String, String> hairTypesByKey = csvAttributes.getHairTypesByKey();
	private final int bytesFactor = csvAttributes.getBytesFactor();
	private final int singlePhysicalOptsSettingMaxValue = csvAttributes.getSinglePhysicalOptsSettingMaxValue();


	public boolean makeFile(ProgressUI ui, OptionFile opf, File dest, boolean headings,
			boolean extra, boolean create) {
		of = opf;

		boolean done = false;

		try {
			FileOutputStream fs = new FileOutputStream(dest);
			OutputStreamWriter ow = new OutputStreamWriter(fs, "UTF8");
			ow.write('\uFEFF');
			BufferedWriter out = new BufferedWriter(ow);

			team = Clubs.getNames(of);

			int num = Player.firstUnused;
			if (extra) num = Player.total;
			if (create) num += 200;
			ui.resetParameters(0, num, "Saving players...");

			if (headings) {
				writeHeadings(out);
				out.write(13);
				out.flush();
				out.write(10);
				out.flush();
			}
			for (int player = 1; player < Player.firstUnused; player++) {
				writePlayer(player, out);
				ui.updateProgress(player);
			}
			if (extra) {
				for (int player = Player.firstUnused; player < Player.total; player++) {
					writePlayer(player, out);
					ui.updateProgress(player);
				}
			}
			if (create) {
				for (int player = Player.firstEdit; player < 32952; player++) {
					writePlayer(player, out);
					ui.updateProgress(player);
				}
			}
			out.close();
			done = true;
		} catch (Exception e) {
			done = false;
			e.printStackTrace();
		} finally {
			ui.done();
		}
		return done;
	}

	private void writeName(int player, BufferedWriter out) throws IOException {
		Player p = new Player(of, player, 0);
		String name = p.name.replaceAll(",", "");
		out.write(name);
		out.flush();
	}

	private String getName(int player) {
		Player p = new Player(of, player, 0);
		String name = p.name.replaceAll(",", "");
		return name;
	}

	private void writeShirtName(int player, BufferedWriter out) throws IOException {
		Player p = new Player(of, player, 0);
		String name = p.getShirtName();
		out.write(name);
		out.flush();
	}

	private String[] getHairTypeLabelByKey(String key) {
		String labelKey = hairTypesByKey.get(key);
		return labelKey.split("/");
	}

	private void writeHeadings(BufferedWriter out) throws IOException {
		String[] head = {"NAME", "SHIRT NAME", "GK", "SW", "CB", "SB", 
				"DM", "WB", "CM", "SM", "AM", "WF", "SS", "CF", "REGISTERED POSITION",
				"HEIGHT", "WEIGHT", "STRONG FOOT", "FAVOURED SIDE", "WEAK FOOT ACCURACY",
				"WEAK FOOT FREQUENCY", "ATTACK", "DEFENSE", "BALANCE", "STAMINA", "TOP SPEED",
				"ACCELERATION", "RESPONSE", "AGILITY", "DRIBBLE ACCURACY",
				"DRIBBLE SPEED", "SHORT PASS ACCURACY", "SHORT PASS SPEED",
				"LONG PASS ACCURACY", "LONG PASS SPEED", "SHOT ACCURACY",
				"SHOT POWER", "SHOT TECHNIQUE", "FREE KICK ACCURACY",
				"SWERVE", "HEADING", "JUMP", "TECHNIQUE", "AGGRESSION",
				"MENTALITY", "GOAL KEEPING", "TEAM WORK", "CONSISTENCY",
				"CONDITION / FITNESS", "DRIBBLING", "TACTICAL DRIBBLE",
				"POSITIONING", "REACTION", "PLAYMAKING", "PASSING", "SCORING",
				"1-1 SCORING", "POST PLAYER", "LINES", "MIDDLE SHOOTING",
				"SIDE", "CENTRE", "PENALTIES", "1-TOUCH PASS", "OUTSIDE",
				"MARKING", "SLIDING", "COVERING", "D-LINE CONTROL",
				"PENALTY STOPPER", "1-ON-1 STOPPER", "LONG THROW",
				"INJURY TOLERANCE", "DRIBBLE STYLE", "FREE KICK STYLE",
				"PK STYLE", "DROP KICK STYLE", "AGE", "NATIONALITY",
				"SKIN COLOR", "FACE TYPE", "PRESET FACE NUMBER", 
				"HEAD HEIGHT", "HEAD WIDTH", "NECK LENGTH", "NECK WIDTH",
				"SHOULDER HEIGHT", "SHOULDER WIDTH", "CHEST MEASUREMENT",
				"WAIST CIRCUMFERENCE", "ARM CIRCUMFERENCE", "LEG CIRCUMFERENCE",
				"CALF CIRCUMFERENCE", "LEG LENGTH", "WRISTBAND", "WRISTBAND COLOR",
				"INTERNATIONAL NUMBER", "CLASSIC NUMBER", "CLUB TEAM", "CLUB NUMBER",
				"HAIR TYPE", "HAIR SHAPE", "HAIR FRONT", "HAIR VOLUME", "HAIR DARKNESS",
				"BANDANA TYPE" };
		out.write("ID");
		out.flush();
		for (int h = 0; h < head.length; h++) {
			out.write(separator);
			out.flush();
			out.write(head[h]);
			out.flush();
		}
	}

	private void writeInterStatus(int player, BufferedWriter out) throws IOException {
		String intPlayNo = "0";
		int nat = Stats.getValue(of, player, Stats.nationality);
		int num;
		int b1;
		int b2;
		if (nat < 57) {
			for (int ip = 0; ip < 23; ip++) {
				b1 = of.toInt(of.data[Squads.slot23 + (46 * nat) + (ip * 2)]);
				b2 = of.toInt(of.data[Squads.slot23 + 1 + (46 * nat) + (ip * 2)]);
				if (((b2 << 8) | b1) == player) {
					num = of.toInt(of.data[Squads.num23 + (23 * nat) + ip]) + 1;
					intPlayNo = String.valueOf(num);
				}
			}
		}
		out.write(intPlayNo);
		out.flush();
	}

	private void writeClassicStatus(int player, BufferedWriter out) throws IOException {
		String intPlayNo = "0";
		int nat = Stats.getValue(of, player, Stats.nationality);
		int num;
		int b1;
		int b2;
		int cNat = 0;
		if (nat == 6 || nat == 8 || nat == 9 || nat == 13 || nat == 15
				|| nat == 44 || nat == 45) {
			if (nat == 6) {
				cNat = 57;
			}
			if (nat == 8) {
				cNat = 58;
			}
			if (nat == 9) {
				cNat = 59;
			}
			if (nat == 13) {
				cNat = 60;
			}
			if (nat == 15) {
				cNat = 61;
			}
			if (nat == 44) {
				cNat = 62;
			}
			if (nat == 45) {
				cNat = 63;
			}
			for (int ip = 0; ip < 23; ip++) {
				b1 = of.toInt(of.data[Squads.slot23 + (46 * cNat) + (ip * 2)]);
				b2 = of.toInt(of.data[Squads.slot23 + 1 + (46 * cNat) + (ip * 2)]);
				if (((b2 << 8) | b1) == player) {
					num = of.toInt(of.data[Squads.num23 + (23 * cNat) + ip]) + 1;
					intPlayNo = String.valueOf(num);
				}
			}
		}
		out.write(intPlayNo);
		out.flush();
	}

	private void writeTeam(int player, BufferedWriter out) throws IOException {
		String intPlayNo = "0";
		String club = "";
		int num;
		int b1;
		int b2;
		for (int t = 0; t < Clubs.total; t++) {
			for (int ip = 0; ip < 32; ip++) {
				b1 = of.toInt(of.data[Squads.slot32 + (64 * t) + (ip * 2)]);
				b2 = of.toInt(of.data[Squads.slot32 + 1 + (64 * t) + (ip * 2)]);
				if (((b2 << 8) | b1) == player) {
					num = of.toInt(of.data[Squads.num32 + (32 * t) + ip]) + 1;
					club = team[t];
					// check num for correctness
					if (0 > num || num > 255) {
						num = 0;
					}
					intPlayNo = String.valueOf(num);
				}
			}
		}
		out.write(club);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(intPlayNo);
		out.flush();
	}

	/*
	 * private boolean playerExists(int player) throws IOException { boolean
	 * exists = false; if (of.ofileData[405641+(player*128)+of.offSet] != 0) {
	 * exists = true; } return exists; }
	 */

	private void writePlayer(int player, BufferedWriter out)
			throws IOException {

		out.write(Integer.toString(player));
		out.flush();
		out.write(separator);
		out.flush();
		writeName(player, out);
		out.write(separator);
		out.flush();
		writeShirtName(player, out);
		out.write(separator);
		out.flush();

		for (int i = 0; i < Stats.roles.length; i++) {
			if (i != 1) {
				out.write(Stats.getString(of, player, Stats.roles[i]));
				out.flush();
				out.write(separator);
				out.flush();
			}
		}

		out.write(Stats.getString(of, player, Stats.regPos));
		out.flush();
		out.write(separator);
		out.flush();
		
		out.write(Stats.getString(of, player, Stats.height));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.weight));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.foot));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(getSide(player));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.wfa));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.wff));
		out.flush();
		out.write(separator);
		out.flush();
		
		for (int i = 0; i < Stats.ability99.length; i++) {
			out.write(Stats.getString(of, player, Stats.ability99[i]));
			out.flush();
			out.write(separator);
			out.flush();
		}
		
		out.write(Stats.getString(of, player, Stats.consistency));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.condition));
		out.flush();
		out.write(separator);
		out.flush();
		
		for (int i = 0; i < Stats.abilitySpecial.length; i++) {
			out.write(Stats.getString(of, player, Stats.abilitySpecial[i]));
			out.flush();
			out.write(separator);
			out.flush();
		}
		
		out.write(Stats.getString(of, player, Stats.injury));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.dribSty));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.freekick));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.pkStyle));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.dkSty));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.age));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Stats.getString(of, player, Stats.nationality));
		out.flush();
		out.write(separator);
		out.flush();

		out.write(Integer.toString((Stats.getValue(of, player, Stats.skin) + 1)));
		out.flush();
		out.write(separator);
		out.flush();

		out.write(Integer.toString(Stats.getValue(of, player, Stats.faceType)));
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Integer.toString((Stats.getValue(of, player, Stats.face) + 1)));
		out.flush();
		out.write(separator);
		out.flush();

		int ia = Player.startAdr + (player * 124);
		if (player >= Player.firstEdit) {
			ia = Player.startAdrE + ((player - Player.firstEdit) * 124);
		}

		byte[] playerData = Arrays.copyOfRange(of.data, ia, ia + 124);

		int headHeightVal = playerData[90];
		String headHeightAttribute = headHeightOptsByValue.get(headHeightVal);

		int headWidthNeckWidthVal = playerData[91];
		String[] headWidthNeckWidthAttributes = physicalLinkedOptsByValue.get(headWidthNeckWidthVal).split("/");
		String headWidthAttribute = headWidthNeckWidthAttributes[0];
		String neckWidthAttribute = headWidthNeckWidthAttributes[1];

		int neckLengthChestMeasurementVal = playerData[105];
		String[] neckLengthChestMeasurementAttributes = physicalLinkedOptsByValue.get(neckLengthChestMeasurementVal).split("/");
		String neckLengthAttribute = neckLengthChestMeasurementAttributes[0];
		String chestMeasurementAttribute = neckLengthChestMeasurementAttributes[1];

		int armCircumferenceWaistCircumferenceVal = playerData[106];
		String[] armCircumferenceWaistCircumferenceAttributes = physicalLinkedOptsByValue.get(armCircumferenceWaistCircumferenceVal).split("/");
		String armCircumferenceAttribute = armCircumferenceWaistCircumferenceAttributes[0];
		String waistCircumferenceAttribute = armCircumferenceWaistCircumferenceAttributes[1];

		int legCircumferenceCalfCircumferenceVal = playerData[107];
		String[] legCircumferenceCalfCircumferenceAttributes = physicalLinkedOptsByValue.get(legCircumferenceCalfCircumferenceVal).split("/");
		String legCircumferenceAttribute = legCircumferenceCalfCircumferenceAttributes[0];
		String calfCircumferenceAttribute = legCircumferenceCalfCircumferenceAttributes[1];

		int legLengthShoulderHeightVal = playerData[108];
		String[] legLengthShoulderHeightAttributes = physicalLinkedOptsByValue.get(legLengthShoulderHeightVal).split("/");
		String legLengthAttribute = legLengthShoulderHeightAttributes[0];
		String ShoulderHeightAttribute = legLengthShoulderHeightAttributes[1];

		int hairTypeVal1 = playerData[92];
		int hairTypeVal2 = playerData[93];

		int hairTypeVal2Modulus = -1;

		if(hairTypeVal2 >= 0){
			hairTypeVal2Modulus = hairTypeVal2 % 8;
		}
		else {
			int hairTypeVal2Positive = -hairTypeVal2;

			int negativeValModulus = hairTypeVal2Positive % 8;

			switch(negativeValModulus){
				case 0:
					hairTypeVal2Modulus = 0;
				case 3:
					hairTypeVal2Modulus = 5;
				case 4:
					hairTypeVal2Modulus = 4;
				case 5:
					hairTypeVal2Modulus = 3;
				case 6:
					hairTypeVal2Modulus = 2;
				case 7:
					hairTypeVal2Modulus = 1;
			}
		}

		

		String hairTypeKey = hairTypeVal1 + "/" + hairTypeVal2Modulus;

		// 109 also potentially linked to bandana colour!!
		int shoulderWidthVal = playerData[109];

		if(shoulderWidthVal > singlePhysicalOptsSettingMaxValue){
			int factor = (int)Math.floor(shoulderWidthVal / bytesFactor);
			shoulderWidthVal = shoulderWidthVal - (factor * bytesFactor);
		}

		String shoulderWidthAttribute = physicalOptsByValue.get(shoulderWidthVal);

		out.write(headHeightAttribute);
		out.flush();
		out.write(separator);
		out.flush();
		
		out.write(headWidthAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(neckLengthAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(neckWidthAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(ShoulderHeightAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(shoulderWidthAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(chestMeasurementAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(waistCircumferenceAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(armCircumferenceAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(legCircumferenceAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(calfCircumferenceAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(legLengthAttribute);
		out.flush();
		out.write(separator);
		out.flush();

		int wristbandVal = playerData[98];
		boolean wristbandValFound = false;

		for (int val : wristbandVals) {
            if (val == wristbandVal) {
                wristbandValFound = true;
                break;
            }
		}
		
		if(!wristbandValFound){
			wristbandVal = 0;
		}

		String[] wristbandTypeColor = wristbandOptsByValue.get(wristbandVal).split("-");

		String wristbandType = wristbandTypeColor[0];
		String wristbandColor = wristbandTypeColor[1];
		
		out.write(wristbandType);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(wristbandColor);
		out.flush();
		out.write(separator);
		out.flush();

		writeInterStatus(player, out);
		out.write(separator);
		out.flush();
		writeClassicStatus(player, out);
		out.write(separator);
		out.flush();
		writeTeam(player, out);
		out.write(separator);		

		String[] hairTypeLabels = getHairTypeLabelByKey(hairTypeKey);

		String hairType = hairTypeLabels[0];
		String hairShape = hairTypeLabels[1];
		String hairFront = hairTypeLabels[2];
		String hairVolume = hairTypeLabels[3];
		String hairDarkness = hairTypeLabels[4];
		String bandanaType = hairTypeLabels[5];

		out.write(hairType);
		out.write(separator);
		out.write(hairShape);
		out.write(separator);
		out.write(hairFront);
		out.write(separator);
		out.write(hairVolume);
		out.write(separator);
		out.write(hairDarkness);
		out.write(separator);
		out.write(bandanaType);

		out.write(13);
		out.flush();
		out.write(10);
		out.flush();
	}

	private String getSide(int p) {
		String side = "B";
		int s = Stats.getValue(of, p, Stats.favSide);
		if (s == 0) {
			side = Stats.getString(of, p, Stats.foot);
		}
		if (s == 1) {
			if (Stats.getValue(of, p, Stats.foot) == 0) {
				side = "L";
			} else {
				side = "R";
			}
		}
		return side;
	}
}
