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
	private final Map<Integer, String> capTypeOptsByValue = csvAttributes.getCapTypeOptsByValue();
	private final String capTypeOptsDefaultValue = csvAttributes.getCapTypeOptsDefaultValue();
	private final Map<Integer, String> glassesNecklaceOptsByValue = csvAttributes.getGlassesNecklaceOptsByValue();
	private final Map<Integer, String> eyeColor2TypesByValue = csvAttributes.getEyeColor2TypesByValue();
	private final Map<Integer, String> faceTypesByValue = csvAttributes.getFaceTypesByValue();
	private final Map<String, Integer> specialFacesByIndexNumber = csvAttributes.getSpecialFacesByIndexNumber();
	private final Map<Integer, String> growthTypesByValue = csvAttributes.getGrowthTypesByValue();
	private final String defaultGrowthTypeLabel = csvAttributes.getDefaultGrowthTypeLabel();

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
		String labelKey = hairTypesByKey.getOrDefault(key, "Bald/1/NA/NA/NA/NA");
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
				"MENTALITY", "GOALKEEPING", "TEAMWORK", "CONSISTENCY",
				"CONDITION", "DRIBBLING", "TACTICAL DRIBBLE",
				"POSITIONING", "REACTION", "PLAYMAKING", "PASSING", "SCORING",
				"1-1 SCORING", "POST PLAYER", "LINES", "MIDDLE SHOOTING",
				"SIDE", "CENTRE", "PENALTIES", "1-TOUCH PASS", "OUTSIDE",
				"MARKING", "SLIDING", "COVERING", "D-LINE CONTROL",
				"PENALTY STOPPER", "1-ON-1 STOPPER", "LONG THROW",
				"INJURY TOLERANCE", "DRIBBLE STYLE", "FK STYLE",
				"PK STYLE", "DK STYLE", "AGE", "NATIONALITY",
				"SKIN COLOR", "FACE TYPE", "PRESET FACE NUMBER", "GROWTH TYPE",
				"HEAD HEIGHT", "HEAD WIDTH", "NECK LENGTH", "NECK WIDTH",
				"SHOULDER HEIGHT", "SHOULDER WIDTH", "CHEST MEASUREMENT",
				"WAIST CIRCUMFERENCE", "ARM CIRCUMFERENCE", "LEG CIRCUMFERENCE",
				"CALF CIRCUMFERENCE", "LEG LENGTH", "WRISTBAND", "WRISTBAND COLOR",
				"INTERNATIONAL NUMBER", "CLASSIC NUMBER", "CLUB TEAM", "CLUB NUMBER",
				"HAIR TYPE", "HAIR SHAPE", "HAIR FRONT", "HAIR VOLUME", "HAIR DARKNESS",
				"BANDANA TYPE", "FACIAL HAIR", "HAIR COLOR TYPE", "HAIR COLOR PATTERN",
				"FACIAL HAIR COLOR", "CAP", "CAP TYPE", "GLASSES TYPE", "NECKLACE TYPE",
				"EYE COLOR 1", "EYE COLOR 2", "SLEEVE LENGTH",
				"HEAD POSITION", "BROWS ANGLE", "BROWS HEIGHT", "EYEBROW SPACING",
				"NOSE TYPE", "NOSE HEIGHT", "NOSE WIDTH",
				"CHEEK TYPE", "CHEEK SHAPE", "MOUTH SIZE",
				"JAW TYPE", "CHIN HEIGHT", "CHIN WIDTH" };
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

	private String getClassicNumber(int player) {
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
		return intPlayNo;
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

		int skinColor = (Stats.getValue(of, player, Stats.skin) + 1);

		out.write(Integer.toString(skinColor));
		out.flush();
		out.write(separator);
		out.flush();

		int faceTypeVal = Stats.getValue(of, player, Stats.faceType);
		String faceTypeLabel = faceTypesByValue.get(faceTypeVal);
		int presetFaceNo = (Stats.getValue(of, player, Stats.face) + 1);

		if (faceTypeLabel.equals("Special")){
			String specialFaceKey = skinColor + "/" + presetFaceNo;
			presetFaceNo = specialFacesByIndexNumber.get(specialFaceKey);
		}

		out.write(faceTypeLabel);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(Integer.toString(presetFaceNo));
		out.flush();
		out.write(separator);
		out.flush();

		int ia = Player.startAdr + (player * 124);
		if (player >= Player.firstEdit) {
			ia = Player.startAdrE + ((player - Player.firstEdit) * 124);
		}

		byte[] playerData = Arrays.copyOfRange(of.data, ia, ia + 124);

		int growthTypeVal = playerData[86];
		String growthType = defaultGrowthTypeLabel;

		String classicNumber = getClassicNumber(player);

		// Export growth type for classic players and ML defaults as Standard
		if ((player < 4414 || player > 4436) && (classicNumber.length() == 0 || classicNumber.equals("0"))){
			growthType = growthTypesByValue.getOrDefault(growthTypeVal, defaultGrowthTypeLabel);
		}

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

		int eyeColor2Val = 0;

		if (hairTypeVal2 >= 0) {
			eyeColor2Val = hairTypeVal2 / 8;
		}
		else {
			eyeColor2Val = (128 + hairTypeVal2) / 8;
		}

		String eyeColor2Label  = eyeColor2TypesByValue.getOrDefault(eyeColor2Val, "???");

		int hairColorTypeHairPatternEyeColor1Val = playerData[94];

		String hairColorTypeHairPatternEyeColor1Label = CSVAttributes.getHairColorTypeHairPatternEyeColor1Label(hairColorTypeHairPatternEyeColor1Val);
		String[] hairPatternEyeColor1Labels = hairColorTypeHairPatternEyeColor1Label.split("/");
		String hairColorTypeLabel = hairPatternEyeColor1Labels[0];
		String hairPatternLabel = hairPatternEyeColor1Labels[1];
		String eyeColor1Label = hairPatternEyeColor1Labels[2];

		int facialHairCapVal = playerData[95];
		int sleeveLengthFacialHairColorVal = playerData[96];

		int capTypeVal = playerData[110];

		int glassesNecklaceVal = playerData[97];
		if (glassesNecklaceVal < 0){
			glassesNecklaceVal = -glassesNecklaceVal;
		}
		glassesNecklaceVal = glassesNecklaceVal % 8;

		// 109 also potentially linked to bandana colour!!
		int shoulderWidthVal = playerData[109];

		if(shoulderWidthVal > singlePhysicalOptsSettingMaxValue){
			int factor = (int)Math.floor(shoulderWidthVal / bytesFactor);
			shoulderWidthVal = shoulderWidthVal - (factor * bytesFactor);
		}

		String shoulderWidthAttribute = physicalOptsByValue.get(shoulderWidthVal);

		out.write(growthType);
		out.flush();
		out.write(separator);
		out.flush();
		
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
		out.flush();
		out.write(separator);
		out.flush();
		out.write(hairShape);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(hairFront);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(hairVolume);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(hairDarkness);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(bandanaType);
		out.flush();
		out.write(separator);

		String facialHairCapLabel = CSVAttributes.getFacialHairCapLabel(facialHairCapVal);
		String[] facialHairCapLabels = facialHairCapLabel.split("/");

		String facialHair = facialHairCapLabels[0];
		String cap = facialHairCapLabels[1];

		String sleeveLengthFacialHairColorLabel = CSVAttributes.getSleeveLengthFacialHairColorLabel(sleeveLengthFacialHairColorVal);
		String[] sleeveLengthFacialHairColorLabels = sleeveLengthFacialHairColorLabel.split("/");

		String sleeveLengthLabel = sleeveLengthFacialHairColorLabels[0];
		String facialHairColorLabel = sleeveLengthFacialHairColorLabels[1];

		out.write(facialHair);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(hairColorTypeLabel);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(hairPatternLabel);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(facialHairColorLabel);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(cap);
		out.flush();
		out.write(separator);
		out.flush();
		
		String capType = capTypeOptsByValue.getOrDefault(capTypeVal, capTypeOptsDefaultValue);

		out.write(capType);
		out.flush();
		out.write(separator);
		out.flush();

		String glassesNecklace = glassesNecklaceOptsByValue.get(glassesNecklaceVal);
		String[] glassesNecklaceVals = glassesNecklace.split("/");
		String glassessVal = glassesNecklaceVals[0];
		String necklaceVal = glassesNecklaceVals[1];

		out.write(glassessVal);
		out.flush();
		out.write(separator);
		out.flush();
		out.write(necklaceVal);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(eyeColor1Label);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(eyeColor2Label);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(sleeveLengthLabel);
		out.flush();

		String headPosition = CSVAttributes.getHeadPositionLabel(playerData[123]);
		String noseType = CSVAttributes.getNoseTypeLabel(playerData[121]);
		String noseHeight = CSVAttributes.getNoseHeightLabel(playerData[122]);
		String noseWidth = CSVAttributes.getNoseWidthLabel(playerData[121], playerData[122]);
		String mouthSize = CSVAttributes.getMouthSizeLabel(playerData[121]);
		String jawType = CSVAttributes.getJawTypeLabel(playerData[122]);
		String chinHeight = CSVAttributes.getChinHeightLabel(playerData[122], playerData[123]);
		String chinWidth = CSVAttributes.getChinWidthLabel(playerData[123]);
		String cheekType = CSVAttributes.getCheekTypeLabel(playerData[119]);
		String cheekShape = CSVAttributes.getCheekShapeLabel(playerData[119]);
		String browsHeight = CSVAttributes.getBrowsHeightLabel(playerData[118]);
		String eyebrowSpacing = CSVAttributes.getEyebrowSpacingLabel(playerData[118]);
		String browsAngle = CSVAttributes.getBrowsAngleLabel(playerData[118], playerData[119]);

		out.write(separator);
		out.flush();
		out.write(headPosition);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(browsAngle);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(browsHeight);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(eyebrowSpacing);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(noseType);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(noseHeight);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(noseWidth);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(cheekType);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(cheekShape);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(mouthSize);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(jawType);
		out.flush();

		out.write(separator);
		out.flush();
		out.write(chinHeight);
		out.flush();
		
		out.write(separator);
		out.flush();
		out.write(chinWidth);
		out.flush();

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
