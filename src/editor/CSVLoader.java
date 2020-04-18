package editor;

import java.io.*;

import java.util.Set;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Arrays;

class SquadPlayer implements Comparable {
	public static int GK = 0;
	public static int DF = 1;
	public static int MF = 2;
	public static int AT = 3;
	public static String positionNames[] = { "GK", "DF", "MF", "AT" };

	public final int club;
	public final int number;
	public final int player;
	public final String name;
	public final int position;
	public final float avgSkill;

	public SquadPlayer(int club, int number, int player, final String name, int position, float avgSkill) {
		this.club = club;
		this.number = number;
		this.player = player;
		this.name = name;
		this.position = position;
		this.avgSkill = avgSkill;
	}

	public int compareTo(Object o) {
		final SquadPlayer sp = (SquadPlayer)o;
		if (this == sp) {
			return 0;
		} else if (this.avgSkill > sp.avgSkill) {
			return -1;
		} else if (this.avgSkill < sp.avgSkill) {
			return 1;
		}
		return 0;
	}

	public String toString() {
		return String.format("%s %d %s", 
			SquadPlayer.positionNames[this.position],
			this.number,
			this.name);
	}

	// help static methods

	public static SquadPlayer fromPlayer(int club, int number, OptionFile of, int player) {
		int regPos = Stats.getValue(of, player, Stats.regPos);
		//"GK  0", "CWP  2", "CBT  3", "SB  4", "DMF  5",
		//"WB  6", "CMF  7", "SMF  8", "AMF  9", "WF 10", "SS  11",
		//"CF 12"
		int position = SquadPlayer.DF;
		if (regPos >= 0 && regPos < 2) {
			position = SquadPlayer.GK;
		} else if (regPos >= 2 && regPos < 5) {
			position = SquadPlayer.DF;
		} else if (regPos >= 5 && regPos < 11) {
			position = SquadPlayer.MF;
		} else if (regPos >= 11) {
			position = SquadPlayer.AT;
		}
		float totalAbility = 0.0f;
		for (int i = 0; i < Stats.ability99.length; i++) {
			totalAbility += Stats.getValue(of, player, Stats.ability99[i]);
		}
		float avgSkill = totalAbility/Stats.ability99.length;

		Player p = new Player(of, player, 0);
		String name = p.name.replaceAll(",", "");
		return new SquadPlayer(club, number, player, name, position, avgSkill);
	}

	private static SquadPlayer popPlayer(final List<SquadPlayer> squad, int position) {
		if (squad.isEmpty()) {
			return null;
		}
		for (int i=0; i<squad.size(); i++) {
			SquadPlayer sp = squad.get(i);
			if (position == sp.position) {
				squad.remove(sp);
				return sp;
			}
		}
		return squad.remove(0);
	}

	public static List<SquadPlayer> getFirstN(final List<SquadPlayer> squad, int n) {
		List<SquadPlayer> dest = new LinkedList<SquadPlayer>();
		// sort by ability
		Collections.sort(squad);
		// choose first eleven: GK,DF,DF,DF,DF,MF,MF,MF,MF,AT,AT
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.GK));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.DF));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.DF));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.DF));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.DF));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.MF));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.MF));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.MF));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.MF));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.AT));
		dest.add(SquadPlayer.popPlayer(squad, SquadPlayer.AT));
		// take the rest of highest ability
		for (int i=0; i<n-11; i++) {
			if (!squad.isEmpty()) {
				dest.add(squad.remove(0));
			} else {
				dest.add(null);
			}
		}
		return dest;
	}

	public static boolean samePlayers(OptionFile of, int club, List<SquadPlayer> squad) {
		Set a = new HashSet();
		Set b = new HashSet();
		for (int ip=0; ip<32; ip++) {
			int b1 = of.toInt(of.data[Squads.slot32 + (64 * club) + (ip * 2)]);
			int b2 = of.toInt(of.data[Squads.slot32 + 1 + (64 * club) + (ip * 2)]);
			int player = (b2 << 8) | b1;
			if (player>0 &&  player != 0xffff) {
				a.add(player);
			}
			if (squad.get(ip) != null) {
				b.add(squad.get(ip).player);
			}
		}
		return a.equals(b);
	}

	public static boolean sameNationalPlayers(OptionFile of, int nat, List<SquadPlayer> squad) {
		Set a = new HashSet();
		Set b = new HashSet();
		for (int ip=0; ip<23; ip++) {
			int b1 = of.toInt(of.data[Squads.slot23 + (46 * nat) + (ip * 2)]);
			int b2 = of.toInt(of.data[Squads.slot23 + 1 + (46 * nat) + (ip * 2)]);
			int player = (b2 << 8) | b1;
			if (player>0 &&  player != 0xffff) {
				a.add(player);
			}
			if (squad.get(ip) != null) {
				b.add(squad.get(ip).player);
			}
		}
		return a.equals(b);
	}
}

public class CSVLoader {
	private RandomAccessFile in;
	private String[] tokens;
	private int tokenCount = 0;

	private static String[] team;

	private OptionFile of;
	private byte[] orgData;

	private static String attValueNotFound = "ATTRIBUTE_VALUE_NOT_FOUND";

	private static String attId = "ID";
	private static String attIdUTF8 = "\uFEFFID";
	private static String attName = "NAME";
	private static String attShirtName = "SHIRT NAME";
	private static String attAge = "AGE";

	private static String attNationality = "NATIONALITY";
	private static String attInternationalNumber = "INTERNATIONAL NUMBER";
	private static String attClubTeam = "CLUB TEAM";
	private static String attClubNumber = "CLUB NUMBER";
	private static String attClassicNumber = "CLASSIC NUMBER";

	private static String attHeight = "HEIGHT";
	private static String attWeight = "WEIGHT";
	private static String attStrongFoot = "STRONG FOOT";
	private static String attFavouredSide = "FAVOURED SIDE";
	private static String attWeakFootAccuracy = "WEAK FOOT ACCURACY";
	private static String attWeakFootFrequency = "WEAK FOOT FREQUENCY";
	private static String attInjuryTolerance = "INJURY TOLERANCE";

	private static String attDribbleStyle = "DRIBBLE STYLE";
	private static String attFkStyle = "FK STYLE";
	private static String attPkStyle = "PK STYLE";
	private static String attDkStyle = "DK STYLE";

	private static String attGk = "GK";
	private static String attSw = "SW";
	private static String attCb = "CB";
	private static String attSb = "SB";
	private static String attDm = "DM";
	private static String attWb = "WB";
	private static String attCm = "CM";
	private static String attSm = "SM";
	private static String attAm = "AM";
	private static String attWf = "WF";
	private static String attSs = "SS";
	private static String attCf = "CF";
	private static String attRegisteredPosition = "REGISTERED POSITION";

	private static String attAttack = "ATTACK";
	private static String attDefense = "DEFENSE";
	private static String attBalance = "BALANCE";
	private static String attStamina = "STAMINA";
	private static String attTopSpeed = "TOP SPEED";
	private static String attAcceleration = "ACCELERATION";
	private static String attResponse = "RESPONSE";
	private static String attAgility = "AGILITY";
	private static String attDribbleAccuracy = "DRIBBLE ACCURACY";
	private static String attDribbleSpeed = "DRIBBLE SPEED";
	private static String attShortPassAccuracy = "SHORT PASS ACCURACY";
	private static String attShortPassSpeed = "SHORT PASS SPEED";
	private static String attLongPassAccuracy = "LONG PASS ACCURACY";
	private static String attLongPassSpeed = "LONG PASS SPEED";
	private static String attShotAccuracy = "SHOT ACCURACY";
	private static String attShotPower = "SHOT POWER";
	private static String attShotTechnique = "SHOT TECHNIQUE";
	private static String attFreeKickAccuracy = "FREE KICK ACCURACY";
	private static String attSwerve = "SWERVE";
	private static String attHeading = "HEADING";
	private static String attJump = "JUMP";
	private static String attTechnique = "TECHNIQUE";
	private static String attAggression = "AGGRESSION";
	private static String attMentality = "MENTALITY";
	private static String attGoalkeeping = "GOALKEEPING";
	private static String attTeamwork = "TEAMWORK";

	private static String attConsistency = "CONSISTENCY";
	private static String attCondition = "CONDITION";

	private static String attDribbling = "DRIBBLING";
	private static String attTacticalDribble = "TACTICAL DRIBBLE";
	private static String attPositioning = "POSITIONING";
	private static String attReaction = "REACTION";
	private static String attPlaymaking = "PLAYMAKING";
	private static String attPassing = "PASSING";
	private static String attScoring = "SCORING";
	private static String attOneOnOneScoring = "1-1 SCORING";
	private static String attPostPlayer = "POST PLAYER";
	private static String attLines = "LINES";
	private static String attMiddleShooting = "MIDDLE SHOOTING";
	private static String attSide = "SIDE";
	private static String attCentre = "CENTRE";
	private static String attPenalties = "PENALTIES";
	private static String attOneTouchPass = "1-TOUCH PASS";
	private static String attOutside = "OUTSIDE";
	private static String attMarking = "MARKING";
	private static String attSliding = "SLIDING";
	private static String attCovering = "COVERING";
	private static String attDLineControl = "D-LINE CONTROL";
	private static String attPenaltyStopper = "PENALTY STOPPER";
	private static String attOneOnOneStopper = "1-ON-1 STOPPER";
	private static String attLongThrow = "LONG THROW";

	private static String attSkinColor = "SKIN COLOR";
	private static String attFaceType = "FACE TYPE";
	
	private static String attPresetFaceNumber = "PRESET FACE NUMBER";
	private static String attGrowthType = "GROWTH TYPE";

	private static String attHeadHeight = "HEAD HEIGHT";
	private static String attHeadWidth = "HEAD WIDTH";
	private static String attNeckLength = "NECK LENGTH";
	private static String attNeckWidth = "NECK WIDTH";
	private static String attShoulderHeight = "SHOULDER HEIGHT";
	private static String attShoulderWidth = "SHOULDER WIDTH";
	private static String attChestMeasurement = "CHEST MEASUREMENT";
	private static String attWaistCircumference = "WAIST CIRCUMFERENCE";
	private static String attArmCircumference = "ARM CIRCUMFERENCE";
	private static String attLegCircumference = "LEG CIRCUMFERENCE";
	private static String attCalfCircumference = "CALF CIRCUMFERENCE";
	private static String attLegLength = "LEG LENGTH";
	private static String attWristband = "WRISTBAND";
	private static String attWristbandColor = "WRISTBAND COLOR";

	private static String attHairType = "HAIR TYPE";
	private static String attHairShape = "HAIR SHAPE";
	private static String attHairFront = "HAIR FRONT";
	private static String attHairVolume = "HAIR VOLUME";
	private static String attHairDarkness = "HAIR DARKNESS";
	private static String attBandanaType = "BANDANA TYPE";
	private static String attFacialHair = "FACIAL HAIR";

	private static String attHairColorType = "HAIR COLOR TYPE";
	private static String attHairColorPattern = "HAIR COLOR PATTERN";
	private static String attFacialHairColor = "FACIAL HAIR COLOR";

	private static String attCap = "CAP";
	private static String attCapType = "CAP TYPE";
	private static String attGlassesType = "GLASSES TYPE";
	private static String attNecklaceType = "NECKLACE TYPE";

	private static String attEyeColor1 = "EYE COLOR 1";
	private static String attEyeColor2 = "EYE COLOR 2";

	private static String attSleveLength = "SLEEVE LENGTH";

	private static String attHeadPosition = "HEAD POSITION";

	private static String attEyesType = "EYES TYPE";
	private static String attEyesPosition = "EYES POSITION";
	private static String attEyesAngle = "EYES ANGLE";
	private static String attEyesLength = "EYES LENGTH";
	private static String attEyesWidth = "EYES WIDTH";

	private static String attNoseType = "NOSE TYPE";
	private static String attNoseHeight = "NOSE HEIGHT";
	private static String attNoseWidth = "NOSE WIDTH";

	private static String attMouthType = "MOUTH TYPE";
	private static String attMouthSize = "MOUTH SIZE";
	private static String attMouthPosition = "MOUTH POSITION";

	private static String attBrowsType = "BROWS TYPE";
	private static String attBrowsAngle = "BROWS ANGLE";
	private static String attBrowsHeight = "BROWS HEIGHT";
	private static String attEyebrowSpacing = "EYEBROW SPACING";

	private static String attCheekType = "CHEEK TYPE";
	private static String attCheekShape = "CHEEK SHAPE";

	private static String attJawType = "JAW TYPE";
	private static String attChinHeight = "CHIN HEIGHT";
	private static String attChinWidth = "CHIN WIDTH";

	private static String[] supportedHeaders = {
		attId,
		attName,
		attShirtName,
		attAge,
		attNationality,
		attInternationalNumber,
		attClubTeam,
		attClubNumber,
		attClassicNumber,
		attHeight,
		attWeight,
		attStrongFoot,
		attFavouredSide,
		attWeakFootAccuracy,
		attWeakFootFrequency,
		attInjuryTolerance,
		attDribbleStyle,
		attFkStyle,
		attPkStyle,
		attDkStyle,
		attGk,
		attSw,
		attCb,
		attSb,
		attDm,
		attWb,
		attCm,
		attSm,
		attAm,
		attWf,
		attSs,
		attCf,
		attRegisteredPosition,
		attAttack,
		attDefense,
		attBalance,
		attStamina,
		attTopSpeed,
		attAcceleration,
		attResponse,
		attAgility,
		attDribbleAccuracy,
		attDribbleSpeed,
		attShortPassAccuracy,
		attShortPassSpeed,
		attLongPassAccuracy,
		attLongPassSpeed,
		attShotAccuracy,
		attShotPower,
		attShotTechnique,
		attFreeKickAccuracy,
		attSwerve,
		attHeading,
		attJump,
		attTechnique,
		attAggression,
		attMentality,
		attGoalkeeping,
		attTeamwork,
		attConsistency,
		attCondition,
		attDribbling,
		attTacticalDribble,
		attPositioning,
		attReaction,
		attPlaymaking,
		attPassing,
		attScoring,
		attOneOnOneScoring,
		attPostPlayer,
		attLines,
		attMiddleShooting,
		attSide,
		attCentre,
		attPenalties,
		attOneTouchPass,
		attOutside,
		attMarking,
		attSliding,
		attCovering,
		attDLineControl,
		attPenaltyStopper,
		attOneOnOneStopper,
		attLongThrow,
		attSkinColor,
		attFaceType,
		attPresetFaceNumber,
		attGrowthType,
		attHeadHeight,
		attHeadWidth,
		attNeckLength,
		attNeckWidth,
		attShoulderHeight,
		attShoulderWidth,
		attChestMeasurement,
		attWaistCircumference,
		attArmCircumference,
		attLegCircumference,
		attCalfCircumference,
		attLegLength,
		attWristband,
		attWristbandColor,
		attHairType,
		attHairShape,
		attHairFront,
		attHairVolume,
		attHairDarkness,
		attBandanaType,
		attFacialHair,
		attHairColorType,
		attHairColorPattern,
		attFacialHairColor,
		attCap,
		attCapType,
		attGlassesType,
		attNecklaceType,
		attEyeColor1,
		attEyeColor2,
		attSleveLength,
		attHeadPosition,
		attEyesType,
		attEyesPosition,
		attEyesAngle,
		attEyesLength,
		attEyesWidth,
		attNoseType,
		attNoseHeight,
		attNoseWidth,
		attMouthType,
		attMouthSize,
		attMouthPosition,
		attBrowsType,
		attBrowsAngle,
		attBrowsHeight,
		attEyebrowSpacing,
		attCheekType,
		attCheekShape,
		attJawType,
		attChinHeight,
		attChinWidth,
	};

	private final CSVAttributes csvAttributes = new CSVAttributes();
	private final Map<String, Integer> footOptsByLabel = csvAttributes.getFootFavSideOptsByLabelFootVal();
	private final Map<String, Integer> favSideOptsByLabel = csvAttributes.getFootFavSideOptsByLabelFavSideVal();
	private final Map<String, Integer> physicalOptsByLabel = csvAttributes.getPhysicalOptsByLabel();
	private final Map<String, Integer> physicalLinkedOptsByLabel = csvAttributes.getPhysicalLinkedOptsByLabel();
	private final Map<String, Integer> headHeightOptsByLabel = csvAttributes.getHeadHeightOptsByLabel();
	private final String[] wristbandLabels = csvAttributes.getWristbandLabels();
	private final Map<String, Integer> wristbandOptsByLabel = csvAttributes.getWristbandOptsByLabel();
	private final Map<String, String> hairTypesByLabel = csvAttributes.getHairTypesByLabel();
	private final Map<String, Integer> capTypeOptsByLabel = csvAttributes.getCapTypeOptsByLabel();
	private final Map<String, Integer> glassesNecklaceOptsByLabel = csvAttributes.getGlassesNecklaceOptsByLabel();
	private final Map<String, Integer> eyeColor2TypesByLabel = csvAttributes.getEyeColor2TypesByLabel();
	private final Map<String, Integer> faceTypesByLabel = csvAttributes.getFaceTypesByLabel();
	private final Map<String, Integer> specialFacesByActualNumber = csvAttributes.getSpecialFacesByActualNumber();
	private final Map<String, Integer> growthTypesByLabel = csvAttributes.getGrowthTypesByLabel();
	private final Map<Integer, String> growthTypesByValue = csvAttributes.getGrowthTypesByValue();
	private final Map<String, Integer> eyeTypesByLabel = csvAttributes.getEyeTypesByLabel();

	private final String defaultGrowthTypeLabel = csvAttributes.getDefaultGrowthTypeLabel();
	private final int defaultGrowthTypeVal = csvAttributes.getDefaultGrowthTypeVal();

	private Map<Integer,List<SquadPlayer>> newSquads;
	private Map<Integer,List<SquadPlayer>> newNationalSquads;

	public String getAttributeValue(String[] tokens, HashMap<String, Integer> attributePositions, String attribute) {
		if (attributePositions.containsKey(attribute)) {
			int attributePosition = attributePositions.get(attribute);
			return tokens[attributePosition];
		}
		else {
			return CSVLoader.attValueNotFound;
		}
	}

	public static Map<Integer,Integer> cNatMap = new HashMap<Integer,Integer>();
	static {
		cNatMap.put(57, 6);
		cNatMap.put(58, 8);
		cNatMap.put(59, 9);
		cNatMap.put(60, 13);
		cNatMap.put(61, 15);
		cNatMap.put(62, 44);
		cNatMap.put(63, 45);
	}

	public boolean loadFile(ProgressUI ui, OptionFile opf, File src, boolean updateClubTeams, boolean updateNationalTeams, boolean updateClassicTeams, List<String> changes) {
		of = opf;
		orgData = new byte[of.data.length];
		System.arraycopy(of.data, 0, orgData, 0, of.data.length);
		newSquads = new HashMap<Integer,List<SquadPlayer>>();
		newNationalSquads = new HashMap<Integer,List<SquadPlayer>>();

		boolean done = false;

		try {
			FileReader fr = new FileReader(src);
			BufferedReader in = new BufferedReader(fr);
			//in = new RandomAccessFile(src, "r");
			team = Clubs.getNames(of);
			String headersRow = in.readLine();

			String[] headersArray = headersRow.split(",");

			HashMap<String, Integer> attributePositions = new HashMap<String, Integer>();

			int attributeCount = 0;

			for (String header : headersArray) {
				if (attributeCount == 0 && (!header.equals(CSVLoader.attId) && !header.equals(CSVLoader.attIdUTF8))) {
					throw new Exception("First heading must be ID.");
				}

				String formattedHeader = header.toUpperCase();
				attributePositions.put(formattedHeader, attributeCount);
				attributeCount++;
			}

			int num = Player.firstUnused;
			ui.resetParameters(0, num, "Loading players...");

			int playerImportCounter = 1;
			String csvLine;
			while((csvLine = in.readLine()) != null){
				readPlayerFromLine(csvLine, attributePositions);
				ui.updateProgress(playerImportCounter);
				playerImportCounter++;
			}
			
			//PeterC10 MOD: Only update squads of club teams if set
			if(updateClubTeams){
				// update club squads
				ui.resetParameters(0, Clubs.total*32, "Updating club squads...");

				for (int club = 0; club < Clubs.total; club++) {
					List<SquadPlayer> squad = newSquads.get(club);
					if (squad != null) {
						List<SquadPlayer> newFirst32 = SquadPlayer.getFirstN(squad, 32);
						if (!SquadPlayer.samePlayers(of, club, newFirst32)) {

							for (int ip = 0; ip < 32; ip++) {
								SquadPlayer sp = newFirst32.get(ip);
								int number = (sp!=null)?sp.number:0;
								int id = (sp!=null)?sp.player:0;

								int b1 = id & 0xff;
								int b2 = (id >> 8) & 0xff;

								of.data[Squads.slot32 + (64 * club) + (ip * 2)] = of.toByte(b1);
								of.data[Squads.slot32 + (64 * club) + 1 + (ip * 2)] = of.toByte(b2);
								of.data[Squads.num32 + (32 * club) + ip] = of.toByte(number-1);

								ui.updateProgress(club*32+ip);
							}

							changes.add("Squad updated for team: "+team[club]);
						}
					}
					ui.updateProgress(club*32);
					Thread.sleep(10);
				}
			}

			//PeterC10 MOD: Only update squads of national teams if set
			if(updateNationalTeams){
				// update national squads
				ui.resetParameters(0, 57*23, "Updating national squads...");

				for (int nat = 0; nat < 57; nat++) {
					List<SquadPlayer> squad = newNationalSquads.get(nat);
					if (squad != null) {
						List<SquadPlayer> newFirst23 = SquadPlayer.getFirstN(squad, 23);
						if (!SquadPlayer.sameNationalPlayers(of, nat, newFirst23)) {
							//System.out.println("Team ("+nat+") "+Stats.nation[nat]+": squad changed");
							//System.out.println("new squad: "+newFirst23);
							for (int ip = 0; ip < 23; ip++) {
								SquadPlayer sp = newFirst23.get(ip);
								int number = (sp!=null)?sp.number:0;
								int id = (sp!=null)?sp.player:0;

								int b1 = id & 0xff;
								int b2 = (id >> 8) & 0xff;

								of.data[Squads.slot23 + (46 * nat) + (ip * 2)] = of.toByte(b1);
								of.data[Squads.slot23 + (46 * nat) + 1 + (ip * 2)] = of.toByte(b2);
								of.data[Squads.num23 + (23 * nat) + ip] = of.toByte(number-1);

								ui.updateProgress(nat*23+ip);
							}

							changes.add("Squad updated for team: "+Stats.nation[nat]);
						}
					}
					ui.updateProgress(nat*23);
					Thread.sleep(10);
				}
			}


			//PeterC10 MOD: Only update squads of classic teams if set

			if(updateClassicTeams){
				// update classic squads
				ui.resetParameters(0, 7*23, "Updating classic squads...");

				for (int cNat = 57; cNat < 64; cNat++) {
					List<SquadPlayer> squad = newNationalSquads.get(cNat);
					if (squad != null) {
						List<SquadPlayer> newFirst23 = SquadPlayer.getFirstN(squad, 23);
						if (!SquadPlayer.sameNationalPlayers(of, cNat, newFirst23)) {
							//System.out.println("Team ("+cNat+") Classic "+Stats.nation[cNatMap.get(cNat)]+": squad changed");
							//System.out.println("new squad: "+newFirst23);
							for (int ip = 0; ip < 23; ip++) {
								SquadPlayer sp = newFirst23.get(ip);
								int number = (sp!=null)?sp.number:0;
								int id = (sp!=null)?sp.player:0;

								int b1 = id & 0xff;
								int b2 = (id >> 8) & 0xff;

								of.data[Squads.slot23 + (46 * cNat) + (ip * 2)] = of.toByte(b1);
								of.data[Squads.slot23 + (46 * cNat) + 1 + (ip * 2)] = of.toByte(b2);
								of.data[Squads.num23 + (23 * cNat) + ip] = of.toByte(number-1);

								ui.updateProgress(cNat*23+ip);
							}

							changes.add("Squad updated for team: Classic "+Stats.nation[cNatMap.get(cNat)]);
						}
					}
					ui.updateProgress(cNat*23);
					Thread.sleep(10);
				}
			}

			

			in.close();
			done = true;
		} catch (Exception e) {
			// restore original data, when import fails
			System.arraycopy(orgData, 0, of.data, 0, orgData.length);
			done = false;
			e.printStackTrace();
		} finally {
			ui.done();
		}

		return done;
	}

	private void readName(int player, String name) throws IOException {
		Player p = new Player(of, player, 0);
		if (!name.startsWith("<L")) {
			p.setName(name);
		}
	}

	private void readShirtName(int player, String shirtName) throws IOException {
		Player p = new Player(of, player, 0);
		p.setShirtName(shirtName);
	}

	private void readInterStatus(int player, String v) throws IOException {
		if (!"0".equals(v) && !"".equals(v)) {
			int nat = Stats.getValue(of, player, Stats.nationality);
			int b1;
			int b2;
			int number = Byte.parseByte(v);
			if (nat < 57) {
				for (int ip = 0; ip < 23; ip++) {
					b1 = of.toInt(of.data[Squads.slot23 + (46 * nat) + (ip * 2)]);
					b2 = of.toInt(of.data[Squads.slot23 + 1 + (46 * nat) + (ip * 2)]);
					if (((b2 << 8) | b1) == player) {
						of.data[Squads.num23 + (23 * nat) + ip] = of.toByte(number-1);
					}
				}

				// add to nations for automatic transfers
				if (!newNationalSquads.containsKey(nat)) {
					newNationalSquads.put(nat, new LinkedList<SquadPlayer>());
				}
				List<SquadPlayer> squad = newNationalSquads.get(nat);
				squad.add(SquadPlayer.fromPlayer(nat, number, of, player));
			}
		}
	}

	private void readClassicStatus(int player, String v) throws IOException {
		int nat = Stats.getValue(of, player, Stats.nationality);
		int b1;
		int b2;
		int cNat = 0;
		int number = Byte.parseByte(v);
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
					of.data[Squads.num23 + (23 * cNat) + ip] = of.toByte(number-1);
				}
			}

			// add to nations for automatic transfers
			if (number > 0) {
				if (!newNationalSquads.containsKey(cNat)) {
					newNationalSquads.put(cNat, new LinkedList<SquadPlayer>());
				}
				List<SquadPlayer> squad = newNationalSquads.get(cNat);
				squad.add(SquadPlayer.fromPlayer(cNat, number, of, player));
			}
		}
	}

	private void readTeam(int player, String clubName, String v) throws IOException {
		int number = 0;
		try { number = Byte.parseByte(v); }
		catch (NumberFormatException nfe) {
			Player p = new Player(of, player, 0);
			String name = p.name.replaceAll(",", "");
			System.out.println("WARN: Player (" +
				name + ") has squad number outside of [0,255] range: " +
				String.valueOf(v) + ". Resetting to 0");
			number = 0;
		}
		int club = -1;
		int b1;
		int b2;
		for (int t = 0; t < Clubs.total; t++) {
			if (team[t].equals(clubName)) {
				club = t;
			}
			for (int ip = 0; ip < 32; ip++) {
				b1 = of.toInt(of.data[Squads.slot32 + (64 * t) + (ip * 2)]);
				b2 = of.toInt(of.data[Squads.slot32 + 1 + (64 * t) + (ip * 2)]);
				if (((b2 << 8) | b1) == player) {
					of.data[Squads.num32 + (32 * t) + ip] = of.toByte(number-1);
				}
			}
		}

		// add to newSquads for automatic transfers
		if (club >= 0) {
			if (!newSquads.containsKey(club)) {
				newSquads.put(club, new LinkedList<SquadPlayer>());
			}
			List<SquadPlayer> squad = newSquads.get(club);
			squad.add(SquadPlayer.fromPlayer(club, number, of, player));
		}
	}

	private void readPlayerFromLine(String line, HashMap<String, Integer> attributePositions)
			throws IOException {
		tokens = line.split(",",-1);
		tokenCount = 0;

		int playerId = 0;

		try {
			playerId = Integer.parseInt(this.getAttributeValue(tokens, attributePositions, CSVLoader.attId));
		}
		catch(Exception e) {
			playerId = Integer.parseInt(this.getAttributeValue(tokens, attributePositions, CSVLoader.attIdUTF8));
		}

		if (playerId == 0) {
			throw new IOException("Player ID not specified.");
		}


		String name = this.getAttributeValue(tokens, attributePositions, CSVLoader.attName);
		readName(playerId, name);
		
		String shirtName = this.getAttributeValue(tokens, attributePositions, CSVLoader.attShirtName);
		readShirtName(playerId, shirtName);

		String gk = this.getAttributeValue(tokens, attributePositions, CSVLoader.attGk);
		if (gk != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[0], gk);
		}

		String sw = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSw);
		if (sw != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[2], sw);
		}

		String cb = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCb);
		if (cb != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[3], cb);
		}

		String sb = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSb);
		if (sb != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[4], sb);
		}

		String dm = this.getAttributeValue(tokens, attributePositions, CSVLoader.attDm);
		if (dm != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[5], dm);
		}

		String wb = this.getAttributeValue(tokens, attributePositions, CSVLoader.attWb);
		if (wb != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[6], wb);
		}

		String cm = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCm);
		if (cm != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[7], cm);
		}

		String sm = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSm);
		if (sm != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[8], sm);
		}

		String am = this.getAttributeValue(tokens, attributePositions, CSVLoader.attAm);
		if (am != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[9], am);
		}

		String wf = this.getAttributeValue(tokens, attributePositions, CSVLoader.attWf);
		if (wf != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[10], wf);
		}

		String ss = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSs);
		if (ss != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[11], ss);
		}

		String cf = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCf);
		if (cf != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.roles[12], cf);
		}

		String foot = this.getAttributeValue(tokens, attributePositions, CSVLoader.attStrongFoot);
		String favSide = this.getAttributeValue(tokens, attributePositions, CSVLoader.attFavouredSide);

		if (foot != CSVLoader.attValueNotFound && favSide != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.foot, foot);
			Stats.setValue(of, playerId, Stats.favSide, favSide);
		}

		String weakFootAccuracy = this.getAttributeValue(tokens, attributePositions, CSVLoader.attWeakFootAccuracy);
		if (weakFootAccuracy != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.wfa, weakFootAccuracy);
		}

		String weakFootFrequency = this.getAttributeValue(tokens, attributePositions, CSVLoader.attWeakFootFrequency);
		if (weakFootAccuracy != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.wff, weakFootFrequency);
		}

		String registeredPosition = this.getAttributeValue(tokens, attributePositions, CSVLoader.attRegisteredPosition);
		if (registeredPosition != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.regPos, registeredPosition);
		}

		String attack = this.getAttributeValue(tokens, attributePositions, CSVLoader.attAttack);
		if (attack != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.attack, attack);
		}

		String defense = this.getAttributeValue(tokens, attributePositions, CSVLoader.attDefense);
		if (defense != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.defence, defense);
		}

		String balance = this.getAttributeValue(tokens, attributePositions, CSVLoader.attBalance);
		if (balance != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.balance, balance);
		}

		String stamina = this.getAttributeValue(tokens, attributePositions, CSVLoader.attStamina);
		if (stamina != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.stamina, stamina);
		}

		String topSpeed = this.getAttributeValue(tokens, attributePositions, CSVLoader.attTopSpeed);
		if (topSpeed != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.speed, topSpeed);
		}

		String acceleration = this.getAttributeValue(tokens, attributePositions, CSVLoader.attAcceleration);
		if (acceleration != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.accel, acceleration);
		}

		String response = this.getAttributeValue(tokens, attributePositions, CSVLoader.attResponse);
		if (response != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.response, response);
		}

		String agility = this.getAttributeValue(tokens, attributePositions, CSVLoader.attAgility);
		if (agility != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.agility, agility);
		}

		String dribbleAccuracy = this.getAttributeValue(tokens, attributePositions, CSVLoader.attDribbleAccuracy);
		if (dribbleAccuracy != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.dribAcc, dribbleAccuracy);
		}

		String dribbleSpeed = this.getAttributeValue(tokens, attributePositions, CSVLoader.attDribbleSpeed);
		if (dribbleSpeed != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.dribSpe, dribbleSpeed);
		}

		String shortPassAccuracy = this.getAttributeValue(tokens, attributePositions, CSVLoader.attShortPassAccuracy);
		if (shortPassAccuracy != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.sPassAcc, shortPassAccuracy);
		}

		String shortPassSpeed = this.getAttributeValue(tokens, attributePositions, CSVLoader.attShortPassSpeed);
		if (shortPassSpeed != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.sPassSpe, shortPassSpeed);
		}

		String longPassAccuracy = this.getAttributeValue(tokens, attributePositions, CSVLoader.attLongPassAccuracy);
		if (longPassAccuracy != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.lPassAcc, longPassAccuracy);
		}

		String longPassSpeed = this.getAttributeValue(tokens, attributePositions, CSVLoader.attLongPassSpeed);
		if (longPassSpeed != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.lPassSpe, longPassSpeed);
		}

		String shotAccuracy = this.getAttributeValue(tokens, attributePositions, CSVLoader.attShotAccuracy);
		if (shotAccuracy != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.shotAcc, shotAccuracy);
		}

		String shotPower = this.getAttributeValue(tokens, attributePositions, CSVLoader.attShotPower);
		if (shotPower != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.shotPow, shotPower);
		}

		String shotTechnique = this.getAttributeValue(tokens, attributePositions, CSVLoader.attShotTechnique);
		if (shotTechnique != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.shotTec, shotTechnique);
		}

		String freeKickAccuracy = this.getAttributeValue(tokens, attributePositions, CSVLoader.attFreeKickAccuracy);
		if (freeKickAccuracy != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.fk, freeKickAccuracy);
		}

		String swerve = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSwerve);
		if (swerve != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.curling, swerve);
		}

		String heading = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHeading);
		if (heading != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.heading, heading);
		}

		String jump = this.getAttributeValue(tokens, attributePositions, CSVLoader.attJump);
		if (jump != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.jump, jump);
		}

		String tech = this.getAttributeValue(tokens, attributePositions, CSVLoader.attTechnique);
		if (tech != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.tech, tech);
		}

		String aggression = this.getAttributeValue(tokens, attributePositions, CSVLoader.attAggression);
		if (aggression != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.aggress, aggression);
		}

		String mentality = this.getAttributeValue(tokens, attributePositions, CSVLoader.attMentality);
		if (mentality != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.mental, mentality);
		}

		String goalkeeping = this.getAttributeValue(tokens, attributePositions, CSVLoader.attGoalkeeping);
		if (goalkeeping != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.gkAbil, goalkeeping);
		}

		String teamwork = this.getAttributeValue(tokens, attributePositions, CSVLoader.attTeamwork);
		if (teamwork != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.team, teamwork);
		}

		String consistency = this.getAttributeValue(tokens, attributePositions, CSVLoader.attConsistency);
		if (consistency != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.consistency, consistency);
		}

		String condition = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCondition);
		if (condition != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.condition, condition);
		}

		String dribbling = this.getAttributeValue(tokens, attributePositions, CSVLoader.attDribbling);
		if (dribbling != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[0], dribbling);
		}

		String tacticalDribble = this.getAttributeValue(tokens, attributePositions, CSVLoader.attTacticalDribble);
		if (tacticalDribble != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[1], tacticalDribble);
		}

		String positioning = this.getAttributeValue(tokens, attributePositions, CSVLoader.attPositioning);
		if (positioning != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[2], positioning);
		}

		String reaction = this.getAttributeValue(tokens, attributePositions, CSVLoader.attReaction);
		if (reaction != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[3], reaction);
		}

		String playmaking = this.getAttributeValue(tokens, attributePositions, CSVLoader.attPlaymaking);
		if (playmaking != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[4], playmaking);
		}

		String passing = this.getAttributeValue(tokens, attributePositions, CSVLoader.attPassing);
		if (passing != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[5], passing);
		}

		String scoring = this.getAttributeValue(tokens, attributePositions, CSVLoader.attScoring);
		if (scoring != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[6], scoring);
		}

		String oneOnOneScoring = this.getAttributeValue(tokens, attributePositions, CSVLoader.attOneOnOneScoring);
		if (oneOnOneScoring != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[7], oneOnOneScoring);
		}

		String postPlayer = this.getAttributeValue(tokens, attributePositions, CSVLoader.attPostPlayer);
		if (postPlayer != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[8], postPlayer);
		}

		String lines = this.getAttributeValue(tokens, attributePositions, CSVLoader.attLines);
		if (lines != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[9], lines);
		}

		String middleShooting = this.getAttributeValue(tokens, attributePositions, CSVLoader.attMiddleShooting);
		if (middleShooting != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[10], middleShooting);
		}

		String side = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSide);
		if (side != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[11], side);
		}

		String centre = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCentre);
		if (centre != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[12], centre);
		}

		String penalties = this.getAttributeValue(tokens, attributePositions, CSVLoader.attPenalties);
		if (penalties != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[13], penalties);
		}

		String oneTouchPass = this.getAttributeValue(tokens, attributePositions, CSVLoader.attOneTouchPass);
		if (oneTouchPass != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[14], oneTouchPass);
		}

		String outside = this.getAttributeValue(tokens, attributePositions, CSVLoader.attOutside);
		if (outside != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[15], outside);
		}

		String marking = this.getAttributeValue(tokens, attributePositions, CSVLoader.attMarking);
		if (marking != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[16], marking);
		}

		String sliding = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSliding);
		if (sliding != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[17], sliding);
		}

		String covering = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCovering);
		if (covering != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[18], covering);
		}

		String dLineControl = this.getAttributeValue(tokens, attributePositions, CSVLoader.attDLineControl);
		if (dLineControl != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[19], dLineControl);
		}

		String penaltyStopper = this.getAttributeValue(tokens, attributePositions, CSVLoader.attPenaltyStopper);
		if (penaltyStopper != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[20], penaltyStopper);
		}

		String oneOnOneStopper = this.getAttributeValue(tokens, attributePositions, CSVLoader.attOneOnOneStopper);
		if (oneOnOneStopper != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[21], oneOnOneStopper);
		}

		String longThrow = this.getAttributeValue(tokens, attributePositions, CSVLoader.attLongThrow);
		if (longThrow != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.abilitySpecial[22], longThrow);
		}

		String age = this.getAttributeValue(tokens, attributePositions, CSVLoader.attAge);
		if (age != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.age, age);
		}

		String height = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHeight);
		if (height != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.height, height);
		}

		String weight = this.getAttributeValue(tokens, attributePositions, CSVLoader.attWeight);
		if (weight != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.weight, weight);
		}

		String injuryTolerance = this.getAttributeValue(tokens, attributePositions, CSVLoader.attInjuryTolerance);
		if (injuryTolerance != CSVLoader.attValueNotFound){
			String injuryToleranceVal = injuryTolerance.toUpperCase();
			Stats.setValue(of, playerId, Stats.injury, injuryToleranceVal);
		}

		String dribbleStyle = this.getAttributeValue(tokens, attributePositions, CSVLoader.attDribbleStyle);
		if (dribbleStyle != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.dribSty, dribbleStyle);
		}

		String fkStyle = this.getAttributeValue(tokens, attributePositions, CSVLoader.attFkStyle);
		if (fkStyle != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.freekick, fkStyle);
		}

		String pkStyle = this.getAttributeValue(tokens, attributePositions, CSVLoader.attPkStyle);
		if (pkStyle != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.pkStyle, pkStyle);
		}

		String dkStyle = this.getAttributeValue(tokens, attributePositions, CSVLoader.attDkStyle);
		if (dkStyle != CSVLoader.attValueNotFound){
			Stats.setValue(of, playerId, Stats.dkSty, dkStyle);
		}

		String nationality = this.getAttributeValue(tokens, attributePositions, CSVLoader.attNationality);
		Stats.setValue(of, playerId, Stats.nationality, nationality);

		String skinColor = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSkinColor);
		String faceType = this.getAttributeValue(tokens, attributePositions, CSVLoader.attFaceType);
		String presetFaceNumber = this.getAttributeValue(tokens, attributePositions, CSVLoader.attPresetFaceNumber);
		if (!skinColor.equals(CSVLoader.attValueNotFound) && !faceType.equals(CSVLoader.attValueNotFound) && !presetFaceNumber.equals(CSVLoader.attValueNotFound)){
			int currentSkinColorVal = Stats.getValue(of, playerId, Stats.skin);
			int currentFaceTypeNo = Stats.getValue(of, playerId, Stats.faceType);
			int currentPresetFaceVal = Stats.getValue(of, playerId, Stats.face);

			int skinColorVal = Integer.parseInt(skinColor) - 1;
			int faceTypeVal = faceTypesByLabel.get(faceType);
			int presetFaceNumberVal = Integer.parseInt(presetFaceNumber) - 1;

			if (currentFaceTypeNo != faceTypeVal){
				String faceTypeValStr = Integer.toString(faceTypeVal);
				Stats.setValue(of, playerId, Stats.faceType, faceTypeValStr);
			}

			if (currentPresetFaceVal != presetFaceNumberVal || currentFaceTypeNo != faceTypeVal){
				if (faceType.equals("Special")){
					String specialFaceKey = skinColor + "/" + presetFaceNumber;
					presetFaceNumberVal = (specialFacesByActualNumber.get(specialFaceKey)) - 1;
				}

				String presetFaceNumberValStr = Integer.toString(presetFaceNumberVal);
				Stats.setValue(of, playerId, Stats.face, presetFaceNumberValStr);
			}

			if (currentSkinColorVal != skinColorVal){
				String skinColorValStr = Integer.toString(skinColorVal);
				Stats.setValue(of, playerId, Stats.skin, skinColorValStr);
			}
		}

		int ia = Player.startAdr + (playerId * 124);
		if (playerId >= Player.firstEdit) {
			ia = Player.startAdrE + ((playerId - Player.firstEdit) * 124);
		}

		byte[] playerData = Arrays.copyOfRange(of.data, ia, ia + 124);

		String headHeightLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHeadHeight);
		String headWidthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHeadWidth);
		String neckLengthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attNeckLength);
		String neckWidthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attNeckWidth);
		String shoulderHeightLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attShoulderHeight);
		String shoulderWidthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attShoulderWidth);
		String chestMeasurementLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attChestMeasurement);
		String waistCircumferenceLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attWaistCircumference);
		String armCircumferenceLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attArmCircumference);
		String legCircumferenceLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attLegCircumference);
		String calfCircumferenceLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCalfCircumference);
		String legLengthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attLegLength);

		String headWidthNeckWidthLabel = headWidthLabel + "/" + neckWidthLabel;
		String neckLengthChestMeasurementLabel = neckLengthLabel + "/" + chestMeasurementLabel;
		String armCircumferenceWaistCircumferenceLabel = armCircumferenceLabel + "/" + waistCircumferenceLabel;
		String legCircumferenceCalfCircumferenceLabel = legCircumferenceLabel + "/" + calfCircumferenceLabel;
		String legLengthShoulderHeightLabel = legLengthLabel + "/" + shoulderHeightLabel;

		int headHeightVal = headHeightOptsByLabel.get(headHeightLabel);
		playerData[90] = (byte)headHeightVal;

		int headWidthNeckWidthVal = physicalLinkedOptsByLabel.get(headWidthNeckWidthLabel);
		playerData[91] = (byte)headWidthNeckWidthVal;

		int neckLengthChestMeasurementVal = physicalLinkedOptsByLabel.get(neckLengthChestMeasurementLabel);
		playerData[105] = (byte)neckLengthChestMeasurementVal;

		int armCircumferenceWaistCircumferenceVal = physicalLinkedOptsByLabel.get(armCircumferenceWaistCircumferenceLabel);
		playerData[106] = (byte)armCircumferenceWaistCircumferenceVal;

		int legCircumferenceCalfCircumferenceVal = physicalLinkedOptsByLabel.get(legCircumferenceCalfCircumferenceLabel);
		playerData[107] = (byte)legCircumferenceCalfCircumferenceVal;

		int legLengthShoulderHeightVal = physicalLinkedOptsByLabel.get(legLengthShoulderHeightLabel);
		playerData[108] = (byte)legLengthShoulderHeightVal;

		int shoulderWidthVal = physicalOptsByLabel.get(shoulderWidthLabel);
		playerData[109] = (byte)shoulderWidthVal;

		String wristBandType = this.getAttributeValue(tokens, attributePositions, CSVLoader.attWristband);
		String wristBandColor = this.getAttributeValue(tokens, attributePositions, CSVLoader.attWristbandColor);

		String wristBandKey = wristBandType + "-" + wristBandColor;

		int wristbandVal = 0;

		if (Arrays.asList(wristbandLabels).contains(wristBandKey)){
			wristbandVal = wristbandOptsByLabel.get(wristBandKey);
		}

		playerData[98] = (byte)wristbandVal;

		String hairTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHairType);
		String hairShapeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHairShape);
		String hairFrontLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHairFront);
		String hairVolumeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHairVolume);
		String hairDarknessLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHairDarkness);
		String bandanaTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attBandanaType);
		String hairColorTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHairColorType);
		String hairColorPatternLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHairColorPattern);
		String eyeColor1Label = this.getAttributeValue(tokens, attributePositions, CSVLoader.attEyeColor1);
		String eyeColor2Label = this.getAttributeValue(tokens, attributePositions, CSVLoader.attEyeColor2);

		if (hairTypeLabel != CSVLoader.attValueNotFound && hairShapeLabel != CSVLoader.attValueNotFound
				&& hairFrontLabel != CSVLoader.attValueNotFound && hairVolumeLabel != CSVLoader.attValueNotFound
				&& hairDarknessLabel != CSVLoader.attValueNotFound && bandanaTypeLabel != CSVLoader.attValueNotFound
				&& eyeColor2Label != CSVLoader.attValueNotFound) {

			String fullHairLabel = hairTypeLabel + "/" + hairShapeLabel + "/" + hairFrontLabel + "/" + hairVolumeLabel
					+ "/" + hairDarknessLabel + "/" + bandanaTypeLabel;
			String fullHairCode = hairTypesByLabel.get(fullHairLabel);

			String[] fullHairCodeArray = fullHairCode.split("/");
			int hairCode = Integer.parseInt(fullHairCodeArray[0]);
			int baseHairCode = Integer.parseInt(fullHairCodeArray[1]);
			int multiplyFactor = eyeColor2TypesByLabel.get(eyeColor2Label);
			int baseHairCodeMultiplied = baseHairCode + (8 * multiplyFactor);

			playerData[92] = (byte)hairCode;
			playerData[93] = (byte)baseHairCodeMultiplied;
		}

		if (hairColorTypeLabel != CSVLoader.attValueNotFound && hairColorPatternLabel != CSVLoader.attValueNotFound && eyeColor1Label != CSVLoader.attValueNotFound){
			String hairColorTypeHairPatternEyeColor1Label = hairColorTypeLabel + "/" + hairColorPatternLabel + "/" + eyeColor1Label;
			int hairColorTypeHairPatternEyeColor1Val = csvAttributes.getHairColorTypeHairPatternEyeColor1ValueNoStatic(hairColorTypeHairPatternEyeColor1Label);
			playerData[94] = (byte)hairColorTypeHairPatternEyeColor1Val;
		}

		String facialHairLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attFacialHair);
		String capLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCap);

		if (facialHairLabel != CSVLoader.attValueNotFound && capLabel != CSVLoader.attValueNotFound) {
			String facialHairCapLabel = facialHairLabel + "/" + capLabel;
			int facialHairCapVal = csvAttributes.getFacialHairCapValueNoStatic(facialHairCapLabel);
			playerData[95] = (byte)facialHairCapVal;
		}

		String sleeveLengthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attSleveLength);
		String facialHairColorLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attFacialHairColor);

		if (sleeveLengthLabel != CSVLoader.attValueNotFound && facialHairColorLabel != CSVLoader.attValueNotFound) {
			String sleeveLengthFacialHairColorLabel = sleeveLengthLabel + "/" + facialHairColorLabel;
			int sleeveLengthFacialHairColorValue = csvAttributes.getSleeveLengthFacialHairColorValueNoStatic(sleeveLengthFacialHairColorLabel);
			playerData[96] = (byte)sleeveLengthFacialHairColorValue;
		}

		String capTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCapType);

		if (capTypeLabel != CSVLoader.attValueNotFound) {
			int capTypeVal = capTypeOptsByLabel.get(capTypeLabel);
			playerData[110] = (byte)capTypeVal;
		}

		String glassesTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attGlassesType);
		String necklaceTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attNecklaceType);

		if (glassesTypeLabel != CSVLoader.attValueNotFound && necklaceTypeLabel != CSVLoader.attValueNotFound) {
			String glassesNecklaceTypeKey = glassesTypeLabel + "/" + necklaceTypeLabel;
			int glassesNecklaceTypeVal = glassesNecklaceOptsByLabel.get(glassesNecklaceTypeKey);
			playerData[97] = (byte)glassesNecklaceTypeVal;
		}

		String eyesTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attEyesType);
		if (!eyesTypeLabel.equals(CSVLoader.attValueNotFound)) {
			int eyesTypeValue = eyeTypesByLabel.get(eyesTypeLabel);
			playerData[115] = (byte)eyesTypeValue;
		}

		String browsTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attBrowsType);
		String eyesPositionLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attEyesPosition);

		if (!browsTypeLabel.equals(CSVLoader.attValueNotFound) && !eyesPositionLabel.equals(CSVLoader.attValueNotFound)) {
			int browsTypeEyesPositionValue = csvAttributes.getBrowsTypeEyesPositionValueNoStatic(browsTypeLabel, eyesPositionLabel);
			playerData[116] = (byte)browsTypeEyesPositionValue;
		}

		String cheekTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCheekType);
		String eyesLengthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attEyesLength);
		String browsHeightLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attBrowsHeight);
		String eyesWidthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attEyesWidth);
		String eyesAngleLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attEyesAngle);
		String eyebrowSpacingLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attEyebrowSpacing);
		String browsAngleLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attBrowsAngle);
		String cheekShapeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attCheekShape);

		if (!cheekTypeLabel.equals(CSVLoader.attValueNotFound) && !eyesLengthLabel.equals(CSVLoader.attValueNotFound)
				&& !browsHeightLabel.equals(CSVLoader.attValueNotFound) && !eyesWidthLabel.equals(CSVLoader.attValueNotFound)
				&& !eyesAngleLabel.equals(CSVLoader.attValueNotFound) && !eyebrowSpacingLabel.equals(CSVLoader.attValueNotFound)
				&& !browsAngleLabel.equals(CSVLoader.attValueNotFound) && !cheekShapeLabel.equals(CSVLoader.attValueNotFound)) {
			int[] head2Values = CSVAttributes.getHead2Values(cheekTypeLabel, eyesLengthLabel, browsHeightLabel, eyesWidthLabel, eyesAngleLabel, eyebrowSpacingLabel, browsAngleLabel, cheekShapeLabel);
			int val117 = head2Values[0];
			int val118 = head2Values[1];
			int val119 = head2Values[2];
			playerData[117] = (byte)val117;
			playerData[118] = (byte)val118;
			playerData[119] = (byte)val119;
		}

		String mouthTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attMouthType);
		String mouthPositionLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attMouthPosition);

		if (!mouthTypeLabel.equals(CSVLoader.attValueNotFound) && !mouthPositionLabel.equals(CSVLoader.attValueNotFound)) {
			int mouthTypePositionValue = csvAttributes.getMouthTypePositionValueNoStatic(mouthTypeLabel, mouthPositionLabel);
			playerData[120] = (byte)mouthTypePositionValue;
		}

		String headPositionLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attHeadPosition);
		String noseTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attNoseType);
		String noseHeightLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attNoseHeight);
		String noseWidthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attNoseWidth);
		String mouthSizeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attMouthSize);
		String jawTypeLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attJawType);
		String chinHeightLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attChinHeight);
		String chinWidthLabel = this.getAttributeValue(tokens, attributePositions, CSVLoader.attChinWidth);

		if (!headPositionLabel.equals(CSVLoader.attValueNotFound) && !noseTypeLabel.equals(CSVLoader.attValueNotFound)
				&& !noseHeightLabel.equals(CSVLoader.attValueNotFound) && !noseWidthLabel.equals(CSVLoader.attValueNotFound)
				&& !mouthSizeLabel.equals(CSVLoader.attValueNotFound) && !jawTypeLabel.equals(CSVLoader.attValueNotFound)
				&& !chinHeightLabel.equals(CSVLoader.attValueNotFound) && !chinWidthLabel.equals(CSVLoader.attValueNotFound)) {
			int[] head1Values = CSVAttributes.getHead1Values(headPositionLabel, noseTypeLabel, noseHeightLabel, noseWidthLabel, mouthSizeLabel, jawTypeLabel, chinHeightLabel, chinWidthLabel);
			int val121 = head1Values[0];
			int val122 = head1Values[1];
			int val123 = head1Values[2];
			playerData[121] = (byte)val121;
			playerData[122] = (byte)val122;
			playerData[123] = (byte)val123;
		}

		String classicNumber = this.getAttributeValue(tokens, attributePositions, CSVLoader.attClassicNumber);

		String growthType = this.getAttributeValue(tokens, attributePositions, CSVLoader.attGrowthType);
		// Don't update growth type for classic players of ML defaults or players which already have the same growth type
		if (!growthType.equals(CSVLoader.attValueNotFound) && (playerId < 4414 || playerId > 4436) && (classicNumber.length() == 0) || classicNumber.equals("0")){
			int currentGrowthTypeVal = playerData[86];
			String currentGrowthTypeLabel = growthTypesByValue.getOrDefault(currentGrowthTypeVal, defaultGrowthTypeLabel);

			if (!growthType.equals(currentGrowthTypeLabel)){
				int growthTypeVal = growthTypesByLabel.getOrDefault(growthType, defaultGrowthTypeVal);
				playerData[86] = (byte)growthTypeVal;
			}
		}

		System.arraycopy(playerData, 0, of.data, ia, 124);

		String internationalNumber = this.getAttributeValue(tokens, attributePositions, CSVLoader.attInternationalNumber);
		if (internationalNumber != CSVLoader.attValueNotFound){
			readInterStatus(playerId, internationalNumber);
		}

		if (classicNumber != CSVLoader.attValueNotFound){
			readClassicStatus(playerId, classicNumber);
		}

		String clubTeam = this.getAttributeValue(tokens, attributePositions, CSVLoader.attClubTeam);
		String clubNumber = this.getAttributeValue(tokens, attributePositions, CSVLoader.attClubNumber);
		if (clubTeam != CSVLoader.attValueNotFound && clubNumber != CSVLoader.attValueNotFound){
			readTeam(playerId, clubTeam, clubNumber);
		}
	}
}
