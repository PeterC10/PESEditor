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
	private final int[] wristbandVals = CSVAttributes.getWristbandVals();
	private final Map<Integer, String> registeredPositionByValue = CSVAttributes.getRegisteredPositionByValue();
	private final Map<Integer, String> wristbandOptsByValue = CSVAttributes.getWristbandOptsByValue();
	private final Map<Integer, String> physicalOptsByValue = CSVAttributes.getPhysicalOptsByValue();
	private final Map<Integer, String> physicalLinkedOptsByValue = CSVAttributes.getPhysicalLinkedOptsByValue();
	private final Map<Integer, String> headHeightOptsByValue = CSVAttributes.getHeadHeightOptsByValue();
	private final Map<String, String> hairTypesByKey = CSVAttributes.getHairTypesByKey();
	private final Map<Integer, String> glassesNecklaceOptsByValue = CSVAttributes.getGlassesNecklaceOptsByValue();
	private final Map<Integer, String> eyeColor2TypesByValue = CSVAttributes.getEyeColor2TypesByValue();
	private final Map<Integer, String> faceTypesByValue = CSVAttributes.getFaceTypesByValue();
	private final Map<String, Integer> specialFacesByIndexNumber = CSVAttributes.getSpecialFacesByIndexNumber();
	private final Map<Integer, String> growthTypesByValue = CSVAttributes.getGrowthTypesByValue();
	private final String defaultGrowthTypeLabel = CSVAttributes.getDefaultGrowthTypeLabel();

	private final int bytesFactor = CSVAttributes.getBytesFactor();
	private final int singlePhysicalOptsSettingMaxValue = CSVAttributes.getSinglePhysicalOptsSettingMaxValue();


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
		String[] head = {
			//Player Name
			"NAME", "SHIRT NAME", "CALL NAME",
			//Basic Settings
			"AGE", "INJURY TOLERANCE", "DRIBBLE STYLE", "FK STYLE", "PK STYLE", "DK STYLE",
			"GOAL CELEBRATION 1", "GOAL CELEBRATION 2", "GROWTH TYPE",
			//Basic Settings - Hidden
			"SPECIFIC GROWTH TYPE", "CONSISTENCY",
			//Position
			"STRONG FOOT", "FAVOURED SIDE", "REGISTERED POSITION",
			"GK", "SW", "CB", "SB", "DM", "WB", "CM", "SM", "AM", "WF", "SS", "CF",
			//Club & Nationality
			"NATIONALITY", "INTERNATIONAL NUMBER", "CLASSIC NUMBER", "CLUB TEAM", "CLUB NUMBER",
			//Appearance - Head - Face
			"FACE TYPE", "SKIN COLOR", "HEAD HEIGHT", "HEAD WIDTH", "HEAD POSITION", "PRESET FACE NUMBER",
			//Appearance - Head - Face - Brows
			"BROWS TYPE", "BROWS ANGLE", "BROWS HEIGHT", "EYEBROW SPACING",
			//Appearance - Head - Face - Eyes
			"EYES TYPE", "EYES POSITION", "EYES ANGLE", "EYES LENGTH", "EYES WIDTH",
			"EYE COLOR 1", "EYE COLOR 2",
			//Appearance - Head - Face - Nose
			"NOSE TYPE", "NOSE HEIGHT", "NOSE WIDTH",
			//Appearance - Head - Face - Cheeks
			"CHEEK TYPE", "CHEEK SHAPE",
			//Appearance - Head - Face - Mouth
			"MOUTH TYPE", "MOUTH SIZE", "MOUTH POSITION",
			//Appearance - Head - Face - Jaw
			"JAW TYPE", "CHIN HEIGHT", "CHIN WIDTH",
			//Appearance - Head - Hair - Hairstyle
			"HAIR TYPE", "HAIR SHAPE", "HAIR FRONT", "HAIR VOLUME", "HAIR DARKNESS",
			//Appearance - Head - Hair - Hairstyle - Color
			"HAIR COLOR TYPE", "HAIR COLOR PATTERN", "HAIR COLOR RGB-R", "HAIR COLOR RGB-G", "HAIR COLOR RGB-B",
			//Appearance - Head - Hair - Hairstyle - Bandana
			"BANDANA TYPE", "BANDANA COLOR",
			//Appearance - Head - Hair - Hairstyle - Cap
			"CAP", "CAP TYPE",
			//Appearance - Head - Hair - Facial Hair
			"FACIAL HAIR TYPE", "FACIAL HAIR COLOR",
			//Appearance - Head - Hair - Glasses
			"GLASSES TYPE", "GLASSES COLOR",
			//Physique
			"HEIGHT", "WEIGHT",
			"NECK LENGTH", "NECK WIDTH", "SHOULDER HEIGHT", "SHOULDER WIDTH",
			"CHEST MEASUREMENT", "WAIST CIRCUMFERENCE", "ARM CIRCUMFERENCE",
			"LEG CIRCUMFERENCE", "CALF CIRCUMFERENCE", "LEG LENGTH",
			//Accessories
			"NECK WARMER", "NECKLACE TYPE", "NECKLACE COLOR", "WRISTBAND", "WRISTBAND COLOR",
			"BRACELET TYPE", "BRACELET COLOR", "GLOVES", "FINGER BAND TYPE",
			"SHIRT TYPE",  "SLEEVE LENGTH", "UNDER SHORTS", "UNDER SHORTS COLOR",
			"SOCKS TYPE", "ANKLE TAPE",
			//Ability - Standard
			"ATTACK", "DEFENSE", "BALANCE", "STAMINA", "TOP SPEED",
			"ACCELERATION", "RESPONSE", "AGILITY", "DRIBBLE ACCURACY",
			"DRIBBLE SPEED", "SHORT PASS ACCURACY", "SHORT PASS SPEED",
			"LONG PASS ACCURACY", "LONG PASS SPEED", "SHOT ACCURACY",
			"SHOT POWER", "SHOT TECHNIQUE", "FREE KICK ACCURACY",
			"SWERVE", "HEADING", "JUMP", "TECHNIQUE", "AGGRESSION",
			"MENTALITY", "GOALKEEPING", "TEAMWORK", "CONDITION",
			"WEAK FOOT ACCURACY", "WEAK FOOT FREQUENCY",
			//Ability - Special
			"DRIBBLING", "TACTICAL DRIBBLE", "POSITIONING", "REACTION",
			"PLAYMAKING", "PASSING", "SCORING", "1-1 SCORING", "POST PLAYER",
			"LINES", "MIDDLE SHOOTING", "SIDE", "CENTRE", "PENALTIES",
			"1-TOUCH PASS", "OUTSIDE", "MARKING", "SLIDING", "COVERING",
			"D-LINE CONTROL", "PENALTY STOPPER", "1-ON-1 STOPPER", "LONG THROW"
		};
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

		int ia = Player.startAdr + (player * 124);
		if (player >= Player.firstEdit) {
			ia = Player.startAdrE + ((player - Player.firstEdit) * 124);
		}

		byte[] playerData = Arrays.copyOfRange(of.data, ia, ia + 124);

		//Prepare attribute values
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

		int shoulderWidthVal = playerData[109];

		if(shoulderWidthVal > singlePhysicalOptsSettingMaxValue || shoulderWidthVal < 0){
			int factor = (int)Math.floor(shoulderWidthVal / bytesFactor);
			shoulderWidthVal = shoulderWidthVal - (factor * bytesFactor);

			if(shoulderWidthVal < 0){
				shoulderWidthVal = shoulderWidthVal + bytesFactor;
			}

		}

		String shoulderWidthAttribute = physicalOptsByValue.get(shoulderWidthVal);

		String goalCelebration1 = CSVAttributes.getGoalCelebrationLabel(playerData[83]);
		String goalCelebration2 = CSVAttributes.getGoalCelebrationLabel(playerData[84]);

		int growthTypeVal = playerData[86];
		String growthType = defaultGrowthTypeLabel;

		String classicNumber = getClassicNumber(player);

		// Export growth type for classic players and ML defaults as Standard
		if ((player < 4414 || player > 4436) && (classicNumber.length() == 0 || classicNumber.equals("0"))){
			growthType = growthTypesByValue.getOrDefault(growthTypeVal, defaultGrowthTypeLabel);
		}

		String specificGrowthType = Integer.toString(playerData[86]);

		int registeredPositionVal = Integer.parseInt(Stats.getString(of, player, Stats.regPos));
		registeredPositionVal = registeredPositionVal % 12;
		String registeredPosition = registeredPositionByValue.get(registeredPositionVal);

		int skinColor = (Stats.getValue(of, player, Stats.skin) + 1);
		int faceTypeVal = Stats.getValue(of, player, Stats.faceType);
		String faceTypeLabel = faceTypesByValue.get(faceTypeVal);
		int presetFaceNo = (Stats.getValue(of, player, Stats.face) + 1);

		if (faceTypeLabel.equals("Special")){
			String specialFaceKey = skinColor + "/" + presetFaceNo;
			presetFaceNo = specialFacesByIndexNumber.get(specialFaceKey);
		}

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

		String[] hairTypeLabels = getHairTypeLabelByKey(hairTypeKey);

		String hairType = hairTypeLabels[0];
		String hairShape = hairTypeLabels[1];
		String hairFront = hairTypeLabels[2];
		String hairVolume = hairTypeLabels[3];
		String hairDarkness = hairTypeLabels[4];
		String bandanaType = hairTypeLabels[5];
		String bandanaColor = CSVAttributes.getBandanaColorLabel(playerData[109]);

		String facialHairCapLabel = CSVAttributes.getFacialHairCapLabel(facialHairCapVal);
		String[] facialHairCapLabels = facialHairCapLabel.split("/");

		String facialHair = facialHairCapLabels[0];
		String cap = facialHairCapLabels[1];

		String sleeveLengthFacialHairColorLabel = CSVAttributes.getSleeveLengthFacialHairColorLabel(sleeveLengthFacialHairColorVal);
		String[] sleeveLengthFacialHairColorLabels = sleeveLengthFacialHairColorLabel.split("/");

		String sleeveLengthLabel = sleeveLengthFacialHairColorLabels[0];
		String facialHairColorLabel = sleeveLengthFacialHairColorLabels[1];

		String capType = CSVAttributes.getCapTypeLabel(capTypeVal);

		String glassesNecklace = glassesNecklaceOptsByValue.get(glassesNecklaceVal);
		String[] glassesNecklaceVals = glassesNecklace.split("/");
		String glassessLabel = glassesNecklaceVals[0];
		String necklaceLabel = glassesNecklaceVals[1];

		String necklaceColor = CSVAttributes.getNecklaceColorLabel(playerData[98]);

		String glassesColorLabel = CSVAttributes.getGlassesColorLabel(playerData[110]);

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
		String eyesAngle = CSVAttributes.getEyesAngleLabel(playerData[117]);
		String eyesLength = CSVAttributes.getEyesLengthLabel(playerData[117]);
		String eysWidth = CSVAttributes.getEyesWidthLabel(playerData[117], playerData[118]);
		String mouthType = CSVAttributes.getMouthTypeLabel(playerData[120]);
		String mouthPosition = CSVAttributes.getMouthPositionLabel(playerData[120]);
		String browsType = CSVAttributes.getBrowsTypeLabel(playerData[116]);
		String eyesPosition = CSVAttributes.getEyesPositionLabel(playerData[116]);
		String eyesType = CSVAttributes.getEyesTypeLabel(playerData[115]);

		String neckWarmer = CSVAttributes.getNeckWarmerLabel(playerData[93]);
		String barceletType = CSVAttributes.getBraceletTypeLabel(playerData[99]);
		String barceletColor = CSVAttributes.getBraceletColorLabel(playerData[99]);
		String gloves = CSVAttributes.getGlovesLabel(playerData[104]);
		String fingerBandType = CSVAttributes.getFingerBandTypeLabel(playerData[103]);
		String shirtType = CSVAttributes.getShirtTypeLabel(playerData[89]);
		String underShorts = CSVAttributes.getUnderShortsLabel(playerData[99]);
		String underShortsColor = CSVAttributes.getUnderShortsColorLabel(playerData[100]);
		String socksType = CSVAttributes.getSocksTypeLabel(playerData[100]);
		String ankleTape = CSVAttributes.getAnkleTapeLabel(playerData[104]);

		String rgbR = CSVAttributes.getRgbRLabel(playerData[102]);
		String rgbG = CSVAttributes.getRgbGLabel(playerData[103]);
		String rgbB = CSVAttributes.getRgbBLabel(playerData[104]);

		//Player ID
		out.write(Integer.toString(player));
		out.flush();
		out.write(separator);
		out.flush();
		//Player Name
		writeName(player, out);
		out.write(separator);
		out.flush();

		writeShirtName(player, out);
		out.write(separator);
		out.flush();

		out.write(Stats.getString(of, player, Stats.callName));
		out.flush();
		out.write(separator);
		out.flush();

		//Basic Settings
		out.write(Stats.getString(of, player, Stats.age));
		out.flush();
		out.write(separator);
		out.flush();

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

		out.write(goalCelebration1);
		out.flush();
		out.write(separator);
		out.flush();
		
		out.write(goalCelebration2);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(growthType);
		out.flush();
		out.write(separator);
		out.flush();

		//Basic Settings - Hidden
		out.write(specificGrowthType);
		out.flush();
		out.write(separator);
		out.flush();
		
		out.write(Stats.getString(of, player, Stats.consistency));
		out.flush();
		out.write(separator);
		out.flush();

		//Position
		out.write(Stats.getString(of, player, Stats.foot));
		out.flush();
		out.write(separator);
		out.flush();

		out.write(getSide(player));
		out.flush();
		out.write(separator);
		out.flush();

		

		out.write(registeredPosition);
		out.flush();
		out.write(separator);
		out.flush();

		//All positions
		for (int i = 0; i < Stats.roles.length; i++) {
			if (i != 1) {
				out.write(Stats.getString(of, player, Stats.roles[i]));
				out.flush();
				out.write(separator);
				out.flush();
			}
		}

		//Club & Nationality
		out.write(Stats.getString(of, player, Stats.nationality));
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
		
		//Appearance - Head - Face
		out.write(faceTypeLabel);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(Integer.toString(skinColor));
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

		out.write(headPosition);
		out.flush();
		out.write(separator);
		out.flush();
		
		out.write(Integer.toString(presetFaceNo));
		out.flush();
		out.write(separator);
		out.flush();

		//Appearance - Head - Face - Brows
		out.write(browsType);
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

		//Appearance - Head - Face - Eyes
		out.write(eyesType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(eyesPosition);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(eyesAngle);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(eyesLength);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(eysWidth);
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

		//Appearance - Head - Face - Nose
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

		//Appearance - Head - Face - Cheeks
		out.write(cheekType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(cheekShape);
		out.flush();
		out.write(separator);
		out.flush();

		//Appearance - Head - Face - Mouth
		out.write(mouthType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(mouthSize);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(mouthPosition);
		out.flush();
		out.write(separator);
		out.flush();

		//Appearance - Head - Face - Jaw
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
		out.write(separator);
		out.flush();

		//Appearance - Head - Hair - Hairstyle
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

		//Appearance - Head - Hair - Hairstyle - Color
		out.write(hairColorTypeLabel);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(hairPatternLabel);
		out.flush();
		out.write(separator);
		out.flush();
		
		out.write(rgbR);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(rgbG);
		out.flush();
		out.write(separator);
		out.flush();
		
		out.write(rgbB);
		out.flush();
		out.write(separator);
		out.flush();

		//Appearance - Head - Hair - Hairstyle - Bandana
		out.write(bandanaType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(bandanaColor);
		out.flush();
		out.write(separator);

		//Appearance - Head - Hair - Hairstyle - Cap
		out.write(cap);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(capType);
		out.flush();
		out.write(separator);
		out.flush();

		//Appearance - Head - Hair - Facial Hair
		out.write(facialHair);
		out.flush();
		out.write(separator);
		out.flush();
		
		out.write(facialHairColorLabel);
		out.flush();
		out.write(separator);
		out.flush();

		//Appearance - Head - Hair - Glasses
		out.write(glassessLabel);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(glassesColorLabel);
		out.flush();
		out.write(separator);
		out.flush();

		//Physique
		out.write(Stats.getString(of, player, Stats.height));
		out.flush();
		out.write(separator);
		out.flush();

		out.write(Stats.getString(of, player, Stats.weight));
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

		//Accessories
		out.write(neckWarmer);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(necklaceLabel);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(necklaceColor);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(wristbandType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(wristbandColor);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(barceletType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(barceletColor);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(gloves);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(fingerBandType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(shirtType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(sleeveLengthLabel);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(underShorts);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(underShortsColor);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(socksType);
		out.flush();
		out.write(separator);
		out.flush();

		out.write(ankleTape);
		out.flush();
		out.write(separator);
		out.flush();

		//Ability - Standard
		//All standard 1-99 abilities
		for (int i = 0; i < Stats.ability99.length; i++) {
			out.write(Stats.getString(of, player, Stats.ability99[i]));
			out.flush();
			out.write(separator);
			out.flush();
		}

		out.write(Stats.getString(of, player, Stats.condition));
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
		
		for (int i = 0; i < Stats.abilitySpecial.length; i++) {
			out.write(Stats.getString(of, player, Stats.abilitySpecial[i]));
			out.flush();
			//Don't print separator if last value
			if (i != Stats.abilitySpecial.length - 1) {
				out.write(separator);
				out.flush();
			}
		}
		
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
