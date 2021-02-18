package editor;

import java.util.*;

class CSVAttributes {

    private static final int bytesFactor = 16;
    private static final int singlePhysicalOptsSettingMinValue = 0;
    private static final int singlePhysicalOptsSettingMaxValue = 14;

    private static final Map<Integer, String> registeredPositionByValue = new HashMap<Integer, String>() {
        {
            put(0, "GK");
            put(1, "GK");
            put(2, "SW");
            put(3, "CB");
            put(4, "SB");
            put(5, "DM");
            put(6, "WB");
            put(7, "CM");
            put(8, "SM");
            put(9, "AM");
            put(10, "WF");
            put(11, "SS");
            put(12, "CF");
            put(13, "GK");
        }
    };

    private static final Map<String, Integer> registeredPositionByLabel = new HashMap<String, Integer>() {
        {
            put("GK", 0);
            put("SW", 2);
            put("CB", 3);
            put("SB", 4);
            put("DM", 5);
            put("WB", 6);
            put("CM", 7);
            put("SM", 8);
            put("AM", 9);
            put("WF", 10);
            put("SS", 11);
            put("CF", 12);
        }
    };

    private static final Map<String, Integer> footFavSideOptsByLabelFootVal = new HashMap<String, Integer>() {
        {
            put("R/R", 0);
            put("R/L", 0);
            put("R/B", 0);
            put("L/L", 1);
            put("L/R", 1);
            put("L/B", 1);
        }
    };

    private static final Map<String, Integer> footFavSideOptsByLabelFavSideVal = new HashMap<String, Integer>() {
        {
            put("R/R", 0);
            put("R/L", 1);
            put("R/B", 2);
            put("L/L", 0);
            put("L/R", 1);
            put("L/B", 2);
        }
    };

    private static final Map<String, Integer> physicalOptsByLabel = new HashMap<String, Integer>() {
        {
            put("-7", 0);
            put("-6", 1);
            put("-5", 2);
            put("-4", 3);
            put("-3", 4);
            put("-2", 5);
            put("-1", 6);
            put("0", 7);
            put("1", 8);
            put("2", 9);
            put("3", 10);
            put("4", 11);
            put("5", 12);
            put("6", 13);
            put("7", 14);
        }
    };

    private static final Map<Integer, String> physicalOptsByValue = new HashMap<Integer, String>() {
        {
            put(0, "-7");
            put(1, "-6");
            put(2, "-5");
            put(3, "-4");
            put(4, "-3");
            put(5, "-2");
            put(6, "-1");
            put(7, "0");
            put(8, "1");
            put(9, "2");
            put(10, "3");
            put(11, "4");
            put(12, "5");
            put(13, "6");
            put(14, "7");
        }
    };

    // FIXME: Head height value appears to be linked to another attribute but no
    // idea what that is yet...

    private static final Map<String, Integer> headHeightOptsByLabel = new HashMap<String, Integer>() {
        {
            put("-7", 0);
            put("-7", 1);
            put("-7", 2);
            put("-7", 3);
            put("-7", 4);
            put("-7", 5);
            put("-7", 6);
            put("-7", 7);
            put("-7", 8);
            put("-7", 9);
            put("-7", 10);
            put("-7", 11);
            put("-7", 12);
            put("-7", 13);
            put("-7", 14);

            put("-6", 16);
            put("-6", 17);
            put("-6", 18);
            put("-6", 19);
            put("-6", 20);
            put("-6", 21);
            put("-6", 22);
            put("-6", 23);
            put("-6", 24);
            put("-6", 25);
            put("-6", 26);
            put("-6", 27);
            put("-6", 28);
            put("-6", 29);
            put("-6", 30);

            put("-5", 32);
            put("-5", 33);
            put("-5", 34);
            put("-5", 35);
            put("-5", 36);
            put("-5", 37);
            put("-5", 38);
            put("-5", 39);
            put("-5", 40);
            put("-5", 41);
            put("-5", 42);
            put("-5", 43);
            put("-5", 44);
            put("-5", 45);
            put("-5", 46);

            put("-4", 48);
            put("-4", 49);
            put("-4", 50);
            put("-4", 51);
            put("-4", 52);
            put("-4", 53);
            put("-4", 54);
            put("-4", 55);
            put("-4", 56);
            put("-4", 57);
            put("-4", 58);
            put("-4", 59);
            put("-4", 60);
            put("-4", 61);
            put("-4", 62);

            put("-3", 64);
            put("-3", 65);
            put("-3", 66);
            put("-3", 67);
            put("-3", 68);
            put("-3", 69);
            put("-3", 70);
            put("-3", 71);
            put("-3", 72);
            put("-3", 73);
            put("-3", 74);
            put("-3", 75);
            put("-3", 76);
            put("-3", 77);
            put("-3", 78);

            put("-2", 80);
            put("-2", 81);
            put("-2", 82);
            put("-2", 83);
            put("-2", 84);
            put("-2", 85);
            put("-2", 86);
            put("-2", 87);
            put("-2", 88);
            put("-2", 89);
            put("-2", 90);
            put("-2", 91);
            put("-2", 92);
            put("-2", 93);
            put("-2", 94);

            put("-1", 96);
            put("-1", 97);
            put("-1", 98);
            put("-1", 99);
            put("-1", 100);
            put("-1", 101);
            put("-1", 102);
            put("-1", 103);
            put("-1", 104);
            put("-1", 105);
            put("-1", 106);
            put("-1", 107);
            put("-1", 108);
            put("-1", 109);
            put("-1", 110);

            put("0", 112);
            put("0", 113);
            put("0", 114);
            put("0", 115);
            put("0", 116);
            put("0", 117);
            put("0", 118);
            put("0", 119);
            put("0", 120);
            put("0", 121);
            put("0", 122);
            put("0", 123);
            put("0", 124);
            put("0", 125);
            put("0", 126);

            put("1", -128);
            put("1", -127);
            put("1", -126);
            put("1", -125);
            put("1", -124);
            put("1", -123);
            put("1", -122);
            put("1", -121);
            put("1", -120);
            put("1", -119);
            put("1", -118);
            put("1", -117);
            put("1", -116);
            put("1", -115);
            put("1", -114);

            put("2", -112);
            put("2", -111);
            put("2", -110);
            put("2", -109);
            put("2", -108);
            put("2", -107);
            put("2", -106);
            put("2", -105);
            put("2", -104);
            put("2", -103);
            put("2", -102);
            put("2", -101);
            put("2", -100);
            put("2", -99);
            put("2", -98);

            put("3", -96);
            put("3", -95);
            put("3", -94);
            put("3", -93);
            put("3", -92);
            put("3", -91);
            put("3", -90);
            put("3", -89);
            put("3", -88);
            put("3", -87);
            put("3", -86);
            put("3", -85);
            put("3", -84);
            put("3", -83);
            put("3", -82);

            put("4", -80);
            put("4", -79);
            put("4", -78);
            put("4", -77);
            put("4", -76);
            put("4", -75);
            put("4", -74);
            put("4", -73);
            put("4", -72);
            put("4", -71);
            put("4", -70);
            put("4", -69);
            put("4", -68);
            put("4", -67);
            put("4", -66);

            put("5", -64);
            put("5", -63);
            put("5", -62);
            put("5", -61);
            put("5", -60);
            put("5", -59);
            put("5", -58);
            put("5", -57);
            put("5", -56);
            put("5", -55);
            put("5", -54);
            put("5", -53);
            put("5", -52);
            put("5", -51);
            put("5", -50);

            put("6", -48);
            put("6", -47);
            put("6", -46);
            put("6", -45);
            put("6", -44);
            put("6", -43);
            put("6", -42);
            put("6", -41);
            put("6", -40);
            put("6", -39);
            put("6", -38);
            put("6", -37);
            put("6", -36);
            put("6", -35);
            put("6", -34);

            put("7", -32);
            put("7", -31);
            put("7", -30);
            put("7", -29);
            put("7", -28);
            put("7", -27);
            put("7", -26);
            put("7", -25);
            put("7", -24);
            put("7", -23);
            put("7", -22);
            put("7", -21);
            put("7", -20);
            put("7", -19);
            put("7", -18);
        }
    };

    private static final Map<Integer, String> headHeightOptsByValue = new HashMap<Integer, String>() {
        {
            put(0, "-7");
            put(1, "-7");
            put(2, "-7");
            put(3, "-7");
            put(4, "-7");
            put(5, "-7");
            put(6, "-7");
            put(7, "-7");
            put(8, "-7");
            put(9, "-7");
            put(10, "-7");
            put(11, "-7");
            put(12, "-7");
            put(13, "-7");
            put(14, "-7");

            put(16, "-6");
            put(17, "-6");
            put(18, "-6");
            put(19, "-6");
            put(20, "-6");
            put(21, "-6");
            put(22, "-6");
            put(23, "-6");
            put(24, "-6");
            put(25, "-6");
            put(26, "-6");
            put(27, "-6");
            put(28, "-6");
            put(29, "-6");
            put(30, "-6");

            put(32, "-5");
            put(33, "-5");
            put(34, "-5");
            put(35, "-5");
            put(36, "-5");
            put(37, "-5");
            put(38, "-5");
            put(39, "-5");
            put(40, "-5");
            put(41, "-5");
            put(42, "-5");
            put(43, "-5");
            put(44, "-5");
            put(45, "-5");
            put(46, "-5");

            put(48, "-4");
            put(49, "-4");
            put(50, "-4");
            put(51, "-4");
            put(52, "-4");
            put(53, "-4");
            put(54, "-4");
            put(55, "-4");
            put(56, "-4");
            put(57, "-4");
            put(58, "-4");
            put(59, "-4");
            put(60, "-4");
            put(61, "-4");
            put(62, "-4");

            put(64, "-3");
            put(65, "-3");
            put(66, "-3");
            put(67, "-3");
            put(68, "-3");
            put(69, "-3");
            put(70, "-3");
            put(71, "-3");
            put(72, "-3");
            put(73, "-3");
            put(74, "-3");
            put(75, "-3");
            put(76, "-3");
            put(77, "-3");
            put(78, "-3");

            put(80, "-2");
            put(81, "-2");
            put(82, "-2");
            put(83, "-2");
            put(84, "-2");
            put(85, "-2");
            put(86, "-2");
            put(87, "-2");
            put(88, "-2");
            put(89, "-2");
            put(90, "-2");
            put(91, "-2");
            put(92, "-2");
            put(93, "-2");
            put(94, "-2");

            put(96, "-1");
            put(97, "-1");
            put(98, "-1");
            put(99, "-1");
            put(100, "-1");
            put(101, "-1");
            put(102, "-1");
            put(103, "-1");
            put(104, "-1");
            put(105, "-1");
            put(106, "-1");
            put(107, "-1");
            put(108, "-1");
            put(109, "-1");
            put(110, "-1");

            put(112, "0");
            put(113, "0");
            put(114, "0");
            put(115, "0");
            put(116, "0");
            put(117, "0");
            put(118, "0");
            put(119, "0");
            put(120, "0");
            put(121, "0");
            put(122, "0");
            put(123, "0");
            put(124, "0");
            put(125, "0");
            put(126, "0");

            put(-128, "1");
            put(-127, "1");
            put(-126, "1");
            put(-125, "1");
            put(-124, "1");
            put(-123, "1");
            put(-122, "1");
            put(-121, "1");
            put(-120, "1");
            put(-119, "1");
            put(-118, "1");
            put(-117, "1");
            put(-116, "1");
            put(-115, "1");
            put(-114, "1");

            put(-112, "2");
            put(-111, "2");
            put(-110, "2");
            put(-109, "2");
            put(-108, "2");
            put(-107, "2");
            put(-106, "2");
            put(-105, "2");
            put(-104, "2");
            put(-103, "2");
            put(-102, "2");
            put(-101, "2");
            put(-100, "2");
            put(-99, "2");
            put(-98, "2");

            put(-96, "3");
            put(-95, "3");
            put(-94, "3");
            put(-93, "3");
            put(-92, "3");
            put(-91, "3");
            put(-90, "3");
            put(-89, "3");
            put(-88, "3");
            put(-87, "3");
            put(-86, "3");
            put(-85, "3");
            put(-84, "3");
            put(-83, "3");
            put(-82, "3");

            put(-80, "4");
            put(-79, "4");
            put(-78, "4");
            put(-77, "4");
            put(-76, "4");
            put(-75, "4");
            put(-74, "4");
            put(-73, "4");
            put(-72, "4");
            put(-71, "4");
            put(-70, "4");
            put(-69, "4");
            put(-68, "4");
            put(-67, "4");
            put(-66, "4");

            put(-64, "5");
            put(-63, "5");
            put(-62, "5");
            put(-61, "5");
            put(-60, "5");
            put(-59, "5");
            put(-58, "5");
            put(-57, "5");
            put(-56, "5");
            put(-55, "5");
            put(-54, "5");
            put(-53, "5");
            put(-52, "5");
            put(-51, "5");
            put(-50, "5");

            put(-48, "6");
            put(-47, "6");
            put(-46, "6");
            put(-45, "6");
            put(-44, "6");
            put(-43, "6");
            put(-42, "6");
            put(-41, "6");
            put(-40, "6");
            put(-39, "6");
            put(-38, "6");
            put(-37, "6");
            put(-36, "6");
            put(-35, "6");
            put(-34, "6");

            put(-32, "7");
            put(-31, "7");
            put(-30, "7");
            put(-29, "7");
            put(-28, "7");
            put(-27, "7");
            put(-26, "7");
            put(-25, "7");
            put(-24, "7");
            put(-23, "7");
            put(-22, "7");
            put(-21, "7");
            put(-20, "7");
            put(-19, "7");
            put(-18, "7");
        }
    };

    private static final Map<String, Integer> physicalLinkedOptsByLabel = new HashMap<String, Integer>() {
        {
            put("-7/-7", 0);
            put("-6/-7", 1);
            put("-5/-7", 2);
            put("-4/-7", 3);
            put("-3/-7", 4);
            put("-2/-7", 5);
            put("-1/-7", 6);
            put("0/-7", 7);
            put("1/-7", 8);
            put("2/-7", 9);
            put("3/-7", 10);
            put("4/-7", 11);
            put("5/-7", 12);
            put("6/-7", 13);
            put("7/-7", 14);

            put("-7/-6", 16);
            put("-6/-6", 17);
            put("-5/-6", 18);
            put("-4/-6", 19);
            put("-3/-6", 20);
            put("-2/-6", 21);
            put("-1/-6", 22);
            put("0/-6", 23);
            put("1/-6", 24);
            put("2/-6", 25);
            put("3/-6", 26);
            put("4/-6", 27);
            put("5/-6", 28);
            put("6/-6", 29);
            put("7/-6", 30);

            put("-7/-5", 32);
            put("-6/-5", 33);
            put("-5/-5", 34);
            put("-4/-5", 35);
            put("-3/-5", 36);
            put("-2/-5", 37);
            put("-1/-5", 38);
            put("0/-5", 39);
            put("1/-5", 40);
            put("2/-5", 41);
            put("3/-5", 42);
            put("4/-5", 43);
            put("5/-5", 44);
            put("6/-5", 45);
            put("7/-5", 46);

            put("-7/-4", 48);
            put("-6/-4", 49);
            put("-5/-4", 50);
            put("-4/-4", 51);
            put("-3/-4", 52);
            put("-2/-4", 53);
            put("-1/-4", 54);
            put("0/-4", 55);
            put("1/-4", 56);
            put("2/-4", 57);
            put("3/-4", 58);
            put("4/-4", 59);
            put("5/-4", 60);
            put("6/-4", 61);
            put("7/-4", 62);

            put("-7/-3", 64);
            put("-6/-3", 65);
            put("-5/-3", 66);
            put("-4/-3", 67);
            put("-3/-3", 68);
            put("-2/-3", 69);
            put("-1/-3", 70);
            put("0/-3", 71);
            put("1/-3", 72);
            put("2/-3", 73);
            put("3/-3", 74);
            put("4/-3", 75);
            put("5/-3", 76);
            put("6/-3", 77);
            put("7/-3", 78);

            put("-7/-2", 80);
            put("-6/-2", 81);
            put("-5/-2", 82);
            put("-4/-2", 83);
            put("-3/-2", 84);
            put("-2/-2", 85);
            put("-1/-2", 86);
            put("0/-2", 87);
            put("1/-2", 88);
            put("2/-2", 89);
            put("3/-2", 90);
            put("4/-2", 91);
            put("5/-2", 92);
            put("6/-2", 93);
            put("7/-2", 94);

            put("-7/-1", 96);
            put("-6/-1", 97);
            put("-5/-1", 98);
            put("-4/-1", 99);
            put("-3/-1", 100);
            put("-2/-1", 101);
            put("-1/-1", 102);
            put("0/-1", 103);
            put("1/-1", 104);
            put("2/-1", 105);
            put("3/-1", 106);
            put("4/-1", 107);
            put("5/-1", 108);
            put("6/-1", 109);
            put("7/-1", 110);

            put("-7/0", 112);
            put("-6/0", 113);
            put("-5/0", 114);
            put("-4/0", 115);
            put("-3/0", 116);
            put("-2/0", 117);
            put("-1/0", 118);
            put("0/0", 119);
            put("1/0", 120);
            put("2/0", 121);
            put("3/0", 122);
            put("4/0", 123);
            put("5/0", 124);
            put("6/0", 125);
            put("7/0", 126);

            put("-7/1", -128);
            put("-6/1", -127);
            put("-5/1", -126);
            put("-4/1", -125);
            put("-3/1", -124);
            put("-2/1", -123);
            put("-1/1", -122);
            put("0/1", -121);
            put("1/1", -120);
            put("2/1", -119);
            put("3/1", -118);
            put("4/1", -117);
            put("5/1", -116);
            put("6/1", -115);
            put("7/1", -114);

            put("-7/2", -112);
            put("-6/2", -111);
            put("-5/2", -110);
            put("-4/2", -109);
            put("-3/2", -108);
            put("-2/2", -107);
            put("-1/2", -106);
            put("0/2", -105);
            put("1/2", -104);
            put("2/2", -103);
            put("3/2", -102);
            put("4/2", -101);
            put("5/2", -100);
            put("6/2", -99);
            put("7/2", -98);

            put("-7/3", -96);
            put("-6/3", -95);
            put("-5/3", -94);
            put("-4/3", -93);
            put("-3/3", -92);
            put("-2/3", -91);
            put("-1/3", -90);
            put("0/3", -89);
            put("1/3", -88);
            put("2/3", -87);
            put("3/3", -86);
            put("4/3", -85);
            put("5/3", -84);
            put("6/3", -83);
            put("7/3", -82);

            put("-7/4", -80);
            put("-6/4", -79);
            put("-5/4", -78);
            put("-4/4", -77);
            put("-3/4", -76);
            put("-2/4", -75);
            put("-1/4", -74);
            put("0/4", -73);
            put("1/4", -72);
            put("2/4", -71);
            put("3/4", -70);
            put("4/4", -69);
            put("5/4", -68);
            put("6/4", -67);
            put("7/4", -66);

            put("-7/5", -64);
            put("-6/5", -63);
            put("-5/5", -62);
            put("-4/5", -61);
            put("-3/5", -60);
            put("-2/5", -59);
            put("-1/5", -58);
            put("0/5", -57);
            put("1/5", -56);
            put("2/5", -55);
            put("3/5", -54);
            put("4/5", -53);
            put("5/5", -52);
            put("6/5", -51);
            put("7/5", -50);

            put("-7/6", -48);
            put("-6/6", -47);
            put("-5/6", -46);
            put("-4/6", -45);
            put("-3/6", -44);
            put("-2/6", -43);
            put("-1/6", -42);
            put("0/6", -41);
            put("1/6", -40);
            put("2/6", -39);
            put("3/6", -38);
            put("4/6", -37);
            put("5/6", -36);
            put("6/6", -35);
            put("7/6", -34);

            put("-7/7", -32);
            put("-6/7", -31);
            put("-5/7", -30);
            put("-4/7", -29);
            put("-3/7", -28);
            put("-2/7", -27);
            put("-1/7", -26);
            put("0/7", -25);
            put("1/7", -24);
            put("2/7", -23);
            put("3/7", -22);
            put("4/7", -21);
            put("5/7", -20);
            put("6/7", -19);
            put("7/7", -18);
        }
    };

    private static final Map<Integer, String> physicalLinkedOptsByValue = new HashMap<Integer, String>() {
        {
            put(0, "-7/-7");
            put(1, "-6/-7");
            put(2, "-5/-7");
            put(3, "-4/-7");
            put(4, "-3/-7");
            put(5, "-2/-7");
            put(6, "-1/-7");
            put(7, "0/-7");
            put(8, "1/-7");
            put(9, "2/-7");
            put(10, "3/-7");
            put(11, "4/-7");
            put(12, "5/-7");
            put(13, "6/-7");
            put(14, "7/-7");

            put(16, "-7/-6");
            put(17, "-6/-6");
            put(18, "-5/-6");
            put(19, "-4/-6");
            put(20, "-3/-6");
            put(21, "-2/-6");
            put(22, "-1/-6");
            put(23, "0/-6");
            put(24, "1/-6");
            put(25, "2/-6");
            put(26, "3/-6");
            put(27, "4/-6");
            put(28, "5/-6");
            put(29, "6/-6");
            put(30, "7/-6");

            put(32, "-7/-5");
            put(33, "-6/-5");
            put(34, "-5/-5");
            put(35, "-4/-5");
            put(36, "-3/-5");
            put(37, "-2/-5");
            put(38, "-1/-5");
            put(39, "0/-5");
            put(40, "1/-5");
            put(41, "2/-5");
            put(42, "3/-5");
            put(43, "4/-5");
            put(44, "5/-5");
            put(45, "6/-5");
            put(46, "7/-5");

            put(48, "-7/-4");
            put(49, "-6/-4");
            put(50, "-5/-4");
            put(51, "-4/-4");
            put(52, "-3/-4");
            put(53, "-2/-4");
            put(54, "-1/-4");
            put(55, "0/-4");
            put(56, "1/-4");
            put(57, "2/-4");
            put(58, "3/-4");
            put(59, "4/-4");
            put(60, "5/-4");
            put(61, "6/-4");
            put(62, "7/-4");

            put(64, "-7/-3");
            put(65, "-6/-3");
            put(66, "-5/-3");
            put(67, "-4/-3");
            put(68, "-3/-3");
            put(69, "-2/-3");
            put(70, "-1/-3");
            put(71, "0/-3");
            put(72, "1/-3");
            put(73, "2/-3");
            put(74, "3/-3");
            put(75, "4/-3");
            put(76, "5/-3");
            put(77, "6/-3");
            put(78, "7/-3");

            put(80, "-7/-2");
            put(81, "-6/-2");
            put(82, "-5/-2");
            put(83, "-4/-2");
            put(84, "-3/-2");
            put(85, "-2/-2");
            put(86, "-1/-2");
            put(87, "0/-2");
            put(88, "1/-2");
            put(89, "2/-2");
            put(90, "3/-2");
            put(91, "4/-2");
            put(92, "5/-2");
            put(93, "6/-2");
            put(94, "7/-2");

            put(96, "-7/-1");
            put(97, "-6/-1");
            put(98, "-5/-1");
            put(99, "-4/-1");
            put(100, "-3/-1");
            put(101, "-2/-1");
            put(102, "-1/-1");
            put(103, "0/-1");
            put(104, "1/-1");
            put(105, "2/-1");
            put(106, "3/-1");
            put(107, "4/-1");
            put(108, "5/-1");
            put(109, "6/-1");
            put(110, "7/-1");

            put(112, "-7/0");
            put(113, "-6/0");
            put(114, "-5/0");
            put(115, "-4/0");
            put(116, "-3/0");
            put(117, "-2/0");
            put(118, "-1/0");
            put(119, "0/0");
            put(120, "1/0");
            put(121, "2/0");
            put(122, "3/0");
            put(123, "4/0");
            put(124, "5/0");
            put(125, "6/0");
            put(126, "7/0");

            put(-128, "-7/1");
            put(-127, "-6/1");
            put(-126, "-5/1");
            put(-125, "-4/1");
            put(-124, "-3/1");
            put(-123, "-2/1");
            put(-122, "-1/1");
            put(-121, "0/1");
            put(-120, "1/1");
            put(-119, "2/1");
            put(-118, "3/1");
            put(-117, "4/1");
            put(-116, "5/1");
            put(-115, "6/1");
            put(-114, "7/1");

            put(-112, "-7/2");
            put(-111, "-6/2");
            put(-110, "-5/2");
            put(-109, "-4/2");
            put(-108, "-3/2");
            put(-107, "-2/2");
            put(-106, "-1/2");
            put(-105, "0/2");
            put(-104, "1/2");
            put(-103, "2/2");
            put(-102, "3/2");
            put(-101, "4/2");
            put(-100, "5/2");
            put(-99, "6/2");
            put(-98, "7/2");

            put(-96, "-7/3");
            put(-95, "-6/3");
            put(-94, "-5/3");
            put(-93, "-4/3");
            put(-92, "-3/3");
            put(-91, "-2/3");
            put(-90, "-1/3");
            put(-89, "0/3");
            put(-88, "1/3");
            put(-87, "2/3");
            put(-86, "3/3");
            put(-85, "4/3");
            put(-84, "5/3");
            put(-83, "6/3");
            put(-82, "7/3");

            put(-80, "-7/4");
            put(-79, "-6/4");
            put(-78, "-5/4");
            put(-77, "-4/4");
            put(-76, "-3/4");
            put(-75, "-2/4");
            put(-74, "-1/4");
            put(-73, "0/4");
            put(-72, "1/4");
            put(-71, "2/4");
            put(-70, "3/4");
            put(-69, "4/4");
            put(-68, "5/4");
            put(-67, "6/4");
            put(-66, "7/4");

            put(-64, "-7/5");
            put(-63, "-6/5");
            put(-62, "-5/5");
            put(-61, "-4/5");
            put(-60, "-3/5");
            put(-59, "-2/5");
            put(-58, "-1/5");
            put(-57, "0/5");
            put(-56, "1/5");
            put(-55, "2/5");
            put(-54, "3/5");
            put(-53, "4/5");
            put(-52, "5/5");
            put(-51, "6/5");
            put(-50, "7/5");

            put(-48, "-7/6");
            put(-47, "-6/6");
            put(-46, "-5/6");
            put(-45, "-4/6");
            put(-44, "-3/6");
            put(-43, "-2/6");
            put(-42, "-1/6");
            put(-41, "0/6");
            put(-40, "1/6");
            put(-39, "2/6");
            put(-38, "3/6");
            put(-37, "4/6");
            put(-36, "5/6");
            put(-35, "6/6");
            put(-34, "7/6");

            put(-32, "-7/7");
            put(-31, "-6/7");
            put(-30, "-5/7");
            put(-29, "-4/7");
            put(-28, "-3/7");
            put(-27, "-2/7");
            put(-26, "-1/7");
            put(-25, "0/7");
            put(-24, "1/7");
            put(-23, "2/7");
            put(-22, "3/7");
            put(-21, "4/7");
            put(-20, "5/7");
            put(-19, "6/7");
            put(-18, "7/7");
        }
    };

    private static final Map<String, Integer> glassesNecklaceOptsByLabel = new HashMap<String, Integer>() {
        {
            put("N/N", 0);
            put("1/N", 1);
            put("2/N", 2);
            put("N/1", 4);
            put("1/1", 5);
            put("2/1", 6);
            put("N/2", 8);
            put("1/2", 9);
            put("2/2", 10);
        }
    };

    private static final Map<Integer, String> glassesNecklaceOptsByValue = new HashMap<Integer, String>() {
        {
            put(0, "N/N");
            put(1, "1/N");
            put(2, "2/N");
            put(4, "N/1");
            put(5, "1/1");
            put(6, "2/1");
            put(8, "N/2");
            put(9, "1/2");
            put(10, "2/2");
        }
    };

    private static final int[] wristbandVals = { 0, 8, 16, 24, 40, 48, 56, 72, 80, 88, 104, 112, 120, -120, -112, -104,
            -88, -80, -72, -56, -48, -40, -24, -16, -8 };

    private static final String[] wristbandLabels = { "N-None", "L-White", "R-White", "B-White", "L-Black", "R-Black",
            "B-Black", "L-Red", "R-Red", "B-Red", "L-Blue", "R-Blue", "B-Blue", "L-Yellow", "R-Yellow", "B-Yellow",
            "L-Green", "R-Green", "B-Green", "L-Purple", "R-Purple", "B-Purple", "L-Cyan", "R-Cyan", "B-Cyan" };

    private static final Map<String, Integer> wristbandOptsByLabel = new HashMap<String, Integer>() {
        {
            put("N-None", 0);
            put("L-White", 8);
            put("R-White", 16);
            put("B-White", 24);
            put("L-Black", 40);
            put("R-Black", 48);
            put("B-Black", 56);
            put("L-Red", 72);
            put("R-Red", 80);
            put("B-Red", 88);
            put("L-Blue", 104);
            put("R-Blue", 112);
            put("B-Blue", 120);
            put("L-Yellow", -120);
            put("R-Yellow", -112);
            put("B-Yellow", -104);
            put("L-Green", -88);
            put("R-Green", -80);
            put("B-Green", -72);
            put("L-Purple", -56);
            put("R-Purple", -48);
            put("B-Purple", -40);
            put("L-Cyan", -24);
            put("R-Cyan", -16);
            put("B-Cyan", -8);
        }
    };

    private static final Map<Integer, String> wristbandOptsByValue = new HashMap<Integer, String>() {
        {
            put(0, "N-None");
            put(8, "L-White");
            put(16, "R-White");
            put(24, "B-White");
            put(40, "L-Black");
            put(48, "R-Black");
            put(56, "B-Black");
            put(72, "L-Red");
            put(80, "R-Red");
            put(88, "B-Red");
            put(104, "L-Blue");
            put(112, "R-Blue");
            put(120, "B-Blue");
            put(-120, "L-Yellow");
            put(-112, "R-Yellow");
            put(-104, "B-Yellow");
            put(-88, "L-Green");
            put(-80, "R-Green");
            put(-72, "B-Green");
            put(-56, "L-Purple");
            put(-48, "R-Purple");
            put(-40, "B-Purple");
            put(-24, "L-Cyan");
            put(-16, "R-Cyan");
            put(-8, "B-Cyan");
        }
    };

    private static final Map<String, Integer> faceTypesByLabel = new HashMap<String, Integer>() {
        {
            put("Build", 0);
            put("Special", 1);
            put("Preset", 2);
        }
    };

    private static final Map<Integer, String> faceTypesByValue = new HashMap<Integer, String>() {
        {
            put(0, "Build");
            put(1, "Special");
            put(2, "Preset");
        }
    };

    private static final Map<String, Integer> eyeColor2TypesByLabel = new HashMap<String, Integer>() {
        {
            put("Black 1", 0);
            put("Black 2", 1);
            put("Dark Grey 1", 2);
            put("Dark Grey 2", 3);
            put("Brown 1", 4);
            put("Brown 2", 5);
            put("Light Blue 1", 6);
            put("Light Blue 2", 7);
            put("Blue 1", 8);
            put("Blue 2", 9);
            put("Green 1", 10);
            put("Green 2", 11);
        }
    };

    private static final Map<Integer, String> eyeColor2TypesByValue = new HashMap<Integer, String>() {
        {
            put(0, "Black 1");
            put(1, "Black 2");
            put(2, "Dark Grey 1");
            put(3, "Dark Grey 2");
            put(4, "Brown 1");
            put(5, "Brown 2");
            put(6, "Light Blue 1");
            put(7, "Light Blue 2");
            put(8, "Blue 1");
            put(9, "Blue 2");
            put(10, "Green 1");
            put(11, "Green 2");
        }
    };

    private static final Map<String, String> hairTypesByLabel = new HashMap<String, String>() {
        {
            put("Bald/1/NA/NA/NA/NA", "0/0");
            put("Bald/2/NA/NA/NA/NA", "1/0");
            put("Bald/3/NA/NA/NA/NA", "2/0");
            put("Bald/4/NA/NA/NA/NA", "3/0");
            put("Buzz Cut/1/1/NA/1/NA", "4/0");
            put("Buzz Cut/1/1/NA/2/NA", "5/0");
            put("Buzz Cut/1/1/NA/3/NA", "6/0");
            put("Buzz Cut/1/1/NA/4/NA", "7/0");
            put("Buzz Cut/1/2/NA/1/NA", "8/0");
            put("Buzz Cut/1/2/NA/2/NA", "9/0");
            put("Buzz Cut/1/2/NA/3/NA", "10/0");
            put("Buzz Cut/1/2/NA/4/NA", "11/0");
            put("Buzz Cut/1/3/NA/1/NA", "12/0");
            put("Buzz Cut/1/3/NA/2/NA", "13/0");
            put("Buzz Cut/1/3/NA/3/NA", "14/0");
            put("Buzz Cut/1/3/NA/4/NA", "15/0");
            put("Buzz Cut/1/4/NA/1/NA", "16/0");
            put("Buzz Cut/1/4/NA/2/NA", "17/0");
            put("Buzz Cut/1/4/NA/3/NA", "18/0");
            put("Buzz Cut/1/4/NA/4/NA", "19/0");
            put("Buzz Cut/1/5/NA/1/NA", "20/0");
            put("Buzz Cut/1/5/NA/2/NA", "21/0");
            put("Buzz Cut/1/5/NA/3/NA", "22/0");
            put("Buzz Cut/1/5/NA/4/NA", "23/0");
            put("Buzz Cut/2/1/NA/1/NA", "24/0");
            put("Buzz Cut/2/1/NA/2/NA", "25/0");
            put("Buzz Cut/2/1/NA/3/NA", "26/0");
            put("Buzz Cut/2/1/NA/4/NA", "27/0");
            put("Buzz Cut/2/2/NA/1/NA", "28/0");
            put("Buzz Cut/2/2/NA/2/NA", "29/0");
            put("Buzz Cut/2/2/NA/3/NA", "30/0");
            put("Buzz Cut/2/2/NA/4/NA", "31/0");
            put("Buzz Cut/2/3/NA/1/NA", "32/0");
            put("Buzz Cut/2/3/NA/2/NA", "33/0");
            put("Buzz Cut/2/3/NA/3/NA", "34/0");
            put("Buzz Cut/2/3/NA/4/NA", "35/0");
            put("Buzz Cut/2/4/NA/1/NA", "36/0");
            put("Buzz Cut/2/4/NA/2/NA", "37/0");
            put("Buzz Cut/2/4/NA/3/NA", "38/0");
            put("Buzz Cut/2/4/NA/4/NA", "39/0");
            put("Buzz Cut/2/5/NA/1/NA", "40/0");
            put("Buzz Cut/2/5/NA/2/NA", "41/0");
            put("Buzz Cut/2/5/NA/3/NA", "42/0");
            put("Buzz Cut/2/5/NA/4/NA", "43/0");
            put("Buzz Cut/3/1/NA/1/NA", "44/0");
            put("Buzz Cut/3/1/NA/2/NA", "45/0");
            put("Buzz Cut/3/1/NA/3/NA", "46/0");
            put("Buzz Cut/3/1/NA/4/NA", "47/0");
            put("Buzz Cut/3/2/NA/1/NA", "48/0");
            put("Buzz Cut/3/2/NA/2/NA", "49/0");
            put("Buzz Cut/3/2/NA/3/NA", "50/0");
            put("Buzz Cut/3/2/NA/4/NA", "51/0");
            put("Buzz Cut/3/3/NA/1/NA", "52/0");
            put("Buzz Cut/3/3/NA/2/NA", "53/0");
            put("Buzz Cut/3/3/NA/3/NA", "54/0");
            put("Buzz Cut/3/3/NA/4/NA", "55/0");
            put("Buzz Cut/3/4/NA/1/NA", "56/0");
            put("Buzz Cut/3/4/NA/2/NA", "57/0");
            put("Buzz Cut/3/4/NA/3/NA", "58/0");
            put("Buzz Cut/3/4/NA/4/NA", "59/0");
            put("Buzz Cut/3/5/NA/1/NA", "60/0");
            put("Buzz Cut/3/5/NA/2/NA", "61/0");
            put("Buzz Cut/3/5/NA/3/NA", "62/0");
            put("Buzz Cut/3/5/NA/4/NA", "63/0");
            put("Buzz Cut/4/1/NA/1/NA", "64/0");
            put("Buzz Cut/4/1/NA/2/NA", "65/0");
            put("Buzz Cut/4/1/NA/3/NA", "66/0");
            put("Buzz Cut/4/1/NA/4/NA", "67/0");
            put("Buzz Cut/4/2/NA/1/NA", "68/0");
            put("Buzz Cut/4/2/NA/2/NA", "69/0");
            put("Buzz Cut/4/2/NA/3/NA", "70/0");
            put("Buzz Cut/4/2/NA/4/NA", "71/0");
            put("Buzz Cut/4/3/NA/1/NA", "72/0");
            put("Buzz Cut/4/3/NA/2/NA", "73/0");
            put("Buzz Cut/4/3/NA/3/NA", "74/0");
            put("Buzz Cut/4/3/NA/4/NA", "75/0");
            put("Buzz Cut/4/4/NA/1/NA", "76/0");
            put("Buzz Cut/4/4/NA/2/NA", "77/0");
            put("Buzz Cut/4/4/NA/3/NA", "78/0");
            put("Buzz Cut/4/4/NA/4/NA", "79/0");
            put("Buzz Cut/4/5/NA/1/NA", "80/0");
            put("Buzz Cut/4/5/NA/2/NA", "81/0");
            put("Buzz Cut/4/5/NA/3/NA", "82/0");
            put("Buzz Cut/4/5/NA/4/NA", "83/0");
            put("Very Short 1/1/1/NA/NA/NA", "84/0");
            put("Very Short 1/1/2/NA/NA/NA", "85/0");
            put("Very Short 1/1/3/NA/NA/NA", "86/0");
            put("Very Short 1/1/4/NA/NA/NA", "87/0");
            put("Very Short 1/1/5/NA/NA/NA", "88/0");
            put("Very Short 1/1/6/NA/NA/NA", "89/0");
            put("Very Short 1/2/1/NA/NA/NA", "90/0");
            put("Very Short 1/2/2/NA/NA/NA", "91/0");
            put("Very Short 1/2/3/NA/NA/NA", "92/0");
            put("Very Short 1/2/4/NA/NA/NA", "93/0");
            put("Very Short 1/2/5/NA/NA/NA", "94/0");
            put("Very Short 1/2/6/NA/NA/NA", "95/0");
            put("Very Short 1/3/1/NA/NA/NA", "96/0");
            put("Very Short 1/3/2/NA/NA/NA", "97/0");
            put("Very Short 1/3/3/NA/NA/NA", "98/0");
            put("Very Short 1/3/4/NA/NA/NA", "99/0");
            put("Very Short 1/3/5/NA/NA/NA", "100/0");
            put("Very Short 1/3/6/NA/NA/NA", "101/0");
            put("Very Short 1/4/1/NA/NA/NA", "102/0");
            put("Very Short 1/4/2/NA/NA/NA", "103/0");
            put("Very Short 1/4/3/NA/NA/NA", "104/0");
            put("Very Short 1/4/4/NA/NA/NA", "105/0");
            put("Very Short 1/4/5/NA/NA/NA", "106/0");
            put("Very Short 1/4/6/NA/NA/NA", "107/0");
            put("Very Short 2/1/1/NA/NA/NA", "108/0");
            put("Very Short 2/1/2/NA/NA/NA", "109/0");
            put("Very Short 2/1/3/NA/NA/NA", "110/0");
            put("Very Short 2/1/4/NA/NA/NA", "111/0");
            put("Very Short 2/1/5/NA/NA/NA", "112/0");
            put("Very Short 2/1/6/NA/NA/NA", "113/0");
            put("Very Short 2/1/7/NA/NA/NA", "114/0");
            put("Very Short 2/1/8/NA/NA/NA", "115/0");
            put("Very Short 2/1/9/NA/NA/NA", "116/0");
            put("Very Short 2/1/10/NA/NA/NA", "117/0");
            put("Very Short 2/2/1/NA/NA/NA", "118/0");
            put("Very Short 2/2/2/NA/NA/NA", "119/0");
            put("Very Short 2/2/3/NA/NA/NA", "120/0");
            put("Very Short 2/2/4/NA/NA/NA", "121/0");
            put("Very Short 2/2/5/NA/NA/NA", "122/0");
            put("Very Short 2/2/6/NA/NA/NA", "123/0");
            put("Very Short 2/2/7/NA/NA/NA", "124/0");
            put("Very Short 2/2/8/NA/NA/NA", "125/0");
            put("Very Short 2/2/9/NA/NA/NA", "126/0");
            put("Very Short 2/2/10/NA/NA/NA", "127/0");
            put("Very Short 2/3/1/NA/NA/NA", "-128/0");
            put("Very Short 2/3/2/NA/NA/NA", "-127/0");
            put("Very Short 2/3/3/NA/NA/NA", "-126/0");
            put("Very Short 2/3/4/NA/NA/NA", "-125/0");
            put("Very Short 2/3/5/NA/NA/NA", "-124/0");
            put("Very Short 2/3/6/NA/NA/NA", "-123/0");
            put("Very Short 2/3/7/NA/NA/NA", "-122/0");
            put("Very Short 2/3/8/NA/NA/NA", "-121/0");
            put("Very Short 2/3/9/NA/NA/NA", "-120/0");
            put("Very Short 2/3/10/NA/NA/NA", "-119/0");
            put("Very Short 2/4/1/NA/NA/NA", "-118/0");
            put("Very Short 2/4/2/NA/NA/NA", "-117/0");
            put("Very Short 2/4/3/NA/NA/NA", "-116/0");
            put("Very Short 2/4/4/NA/NA/NA", "-115/0");
            put("Very Short 2/4/5/NA/NA/NA", "-114/0");
            put("Very Short 2/5/1/NA/NA/NA", "-113/0");
            put("Very Short 2/5/2/NA/NA/NA", "-112/0");
            put("Very Short 2/5/3/NA/NA/NA", "-111/0");
            put("Very Short 2/5/4/NA/NA/NA", "-110/0");
            put("Very Short 2/5/5/NA/NA/NA", "-109/0");
            put("Very Short 2/6/1/NA/NA/NA", "-108/0");
            put("Very Short 2/6/2/NA/NA/NA", "-107/0");
            put("Very Short 2/6/3/NA/NA/NA", "-106/0");
            put("Very Short 2/6/4/NA/NA/NA", "-105/0");
            put("Very Short 2/6/5/NA/NA/NA", "-104/0");
            put("Straight 1/1/1/1/NA/N", "-103/0");
            put("Straight 1/1/1/1/NA/1", "-102/0");
            put("Straight 1/1/1/1/NA/2", "-101/0");
            put("Straight 1/1/1/2/NA/N", "-100/0");
            put("Straight 1/1/1/2/NA/1", "-99/0");
            put("Straight 1/1/1/2/NA/2", "-98/0");
            put("Straight 1/1/1/3/NA/N", "-97/0");
            put("Straight 1/1/1/3/NA/1", "-96/0");
            put("Straight 1/1/1/3/NA/2", "-95/0");
            put("Straight 1/1/2/1/NA/N", "-94/0");
            put("Straight 1/1/2/1/NA/1", "-93/0");
            put("Straight 1/1/2/1/NA/2", "-92/0");
            put("Straight 1/1/2/2/NA/N", "-91/0");
            put("Straight 1/1/2/2/NA/1", "-90/0");
            put("Straight 1/1/2/2/NA/2", "-89/0");
            put("Straight 1/1/2/3/NA/N", "-88/0");
            put("Straight 1/1/2/3/NA/1", "-87/0");
            put("Straight 1/1/2/3/NA/2", "-86/0");
            put("Straight 1/1/3/1/NA/N", "-85/0");
            put("Straight 1/1/3/1/NA/1", "-84/0");
            put("Straight 1/1/3/1/NA/2", "-83/0");
            put("Straight 1/1/3/2/NA/N", "-82/0");
            put("Straight 1/1/3/2/NA/1", "-81/0");
            put("Straight 1/1/3/2/NA/2", "-80/0");
            put("Straight 1/1/3/3/NA/N", "-79/0");
            put("Straight 1/1/3/3/NA/1", "-78/0");
            put("Straight 1/1/3/3/NA/2", "-77/0");
            put("Straight 1/1/4/1/NA/N", "-76/0");
            put("Straight 1/1/4/1/NA/1", "-75/0");
            put("Straight 1/1/4/1/NA/2", "-74/0");
            put("Straight 1/1/4/2/NA/N", "-73/0");
            put("Straight 1/1/4/2/NA/1", "-72/0");
            put("Straight 1/1/4/2/NA/2", "-71/0");
            put("Straight 1/1/4/3/NA/N", "-70/0");
            put("Straight 1/1/4/3/NA/1", "-69/0");
            put("Straight 1/1/4/3/NA/2", "-68/0");
            put("Straight 1/1/5/1/NA/N", "-67/0");
            put("Straight 1/1/5/1/NA/1", "-66/0");
            put("Straight 1/1/5/1/NA/2", "-65/0");
            put("Straight 1/1/5/2/NA/N", "-64/0");
            put("Straight 1/1/5/2/NA/1", "-63/0");
            put("Straight 1/1/5/2/NA/2", "-62/0");
            put("Straight 1/1/5/3/NA/N", "-61/0");
            put("Straight 1/1/5/3/NA/1", "-60/0");
            put("Straight 1/1/5/3/NA/2", "-59/0");
            put("Straight 1/1/6/1/NA/N", "-58/0");
            put("Straight 1/1/6/1/NA/1", "-57/0");
            put("Straight 1/1/6/1/NA/2", "-56/0");
            put("Straight 1/1/6/2/NA/N", "-55/0");
            put("Straight 1/1/6/2/NA/1", "-54/0");
            put("Straight 1/1/6/2/NA/2", "-53/0");
            put("Straight 1/1/6/3/NA/N", "-52/0");
            put("Straight 1/1/6/3/NA/1", "-51/0");
            put("Straight 1/1/6/3/NA/2", "-50/0");
            put("Straight 1/1/7/1/NA/N", "-49/0");
            put("Straight 1/1/7/1/NA/1", "-48/0");
            put("Straight 1/1/7/1/NA/2", "-47/0");
            put("Straight 1/1/7/2/NA/N", "-46/0");
            put("Straight 1/1/7/2/NA/1", "-45/0");
            put("Straight 1/1/7/2/NA/2", "-44/0");
            put("Straight 1/1/7/3/NA/N", "-43/0");
            put("Straight 1/1/7/3/NA/1", "-42/0");
            put("Straight 1/1/7/3/NA/2", "-41/0");
            put("Straight 1/1/8/1/NA/N", "-40/0");
            put("Straight 1/1/8/1/NA/1", "-39/0");
            put("Straight 1/1/8/1/NA/2", "-38/0");
            put("Straight 1/1/8/2/NA/N", "-37/0");
            put("Straight 1/1/8/2/NA/1", "-36/0");
            put("Straight 1/1/8/2/NA/2", "-35/0");
            put("Straight 1/1/8/3/NA/N", "-34/0");
            put("Straight 1/1/8/3/NA/1", "-33/0");
            put("Straight 1/1/8/3/NA/2", "-32/0");
            put("Straight 1/1/9/1/NA/N", "-31/0");
            put("Straight 1/1/9/1/NA/1", "-30/0");
            put("Straight 1/1/9/1/NA/2", "-29/0");
            put("Straight 1/1/9/2/NA/N", "-28/0");
            put("Straight 1/1/9/2/NA/1", "-27/0");
            put("Straight 1/1/9/2/NA/2", "-26/0");
            put("Straight 1/1/9/3/NA/N", "-25/0");
            put("Straight 1/1/9/3/NA/1", "-24/0");
            put("Straight 1/1/9/3/NA/2", "-23/0");
            put("Straight 1/1/10/1/NA/NA", "-22/0");
            put("Straight 1/1/10/2/NA/NA", "-21/0");
            put("Straight 1/1/10/3/NA/NA", "-20/0");
            put("Straight 1/1/11/1/NA/NA", "-19/0");
            put("Straight 1/1/11/2/NA/NA", "-18/0");
            put("Straight 1/1/11/3/NA/NA", "-17/0");
            put("Straight 1/1/12/1/NA/NA", "-16/0");
            put("Straight 1/1/12/2/NA/NA", "-15/0");
            put("Straight 1/1/12/3/NA/NA", "-14/0");
            put("Straight 1/1/13/1/NA/NA", "-13/0");
            put("Straight 1/1/13/2/NA/NA", "-12/0");
            put("Straight 1/1/13/3/NA/NA", "-11/0");
            put("Straight 1/1/14/1/NA/NA", "-10/0");
            put("Straight 1/1/14/2/NA/NA", "-9/0");
            put("Straight 1/1/14/3/NA/NA", "-8/0");
            put("Straight 1/1/15/1/NA/NA", "-7/0");
            put("Straight 1/1/15/2/NA/NA", "-6/0");
            put("Straight 1/1/15/3/NA/NA", "-5/0");
            put("Straight 1/1/16/1/NA/NA", "-4/0");
            put("Straight 1/1/16/2/NA/NA", "-3/0");
            put("Straight 1/1/16/3/NA/NA", "-2/0");
            put("Straight 1/2/1/1/NA/N", "-1/0");
            put("Straight 1/2/1/1/NA/1", "0/1");
            put("Straight 1/2/1/1/NA/2", "1/1");
            put("Straight 1/2/1/2/NA/N", "2/1");
            put("Straight 1/2/1/2/NA/1", "3/1");
            put("Straight 1/2/1/2/NA/2", "4/1");
            put("Straight 1/2/1/3/NA/N", "5/1");
            put("Straight 1/2/1/3/NA/1", "6/1");
            put("Straight 1/2/1/3/NA/2", "7/1");
            put("Straight 1/2/2/1/NA/N", "8/1");
            put("Straight 1/2/2/1/NA/1", "9/1");
            put("Straight 1/2/2/1/NA/2", "10/1");
            put("Straight 1/2/2/2/NA/N", "11/1");
            put("Straight 1/2/2/2/NA/1", "12/1");
            put("Straight 1/2/2/2/NA/2", "13/1");
            put("Straight 1/2/2/3/NA/N", "14/1");
            put("Straight 1/2/2/3/NA/1", "15/1");
            put("Straight 1/2/2/3/NA/2", "16/1");
            put("Straight 1/2/3/1/NA/N", "17/1");
            put("Straight 1/2/3/1/NA/1", "18/1");
            put("Straight 1/2/3/1/NA/2", "19/1");
            put("Straight 1/2/3/2/NA/N", "20/1");
            put("Straight 1/2/3/2/NA/1", "21/1");
            put("Straight 1/2/3/2/NA/2", "22/1");
            put("Straight 1/2/3/3/NA/N", "23/1");
            put("Straight 1/2/3/3/NA/1", "24/1");
            put("Straight 1/2/3/3/NA/2", "25/1");
            put("Straight 1/2/4/1/NA/N", "26/1");
            put("Straight 1/2/4/1/NA/1", "27/1");
            put("Straight 1/2/4/1/NA/2", "28/1");
            put("Straight 1/2/4/2/NA/N", "29/1");
            put("Straight 1/2/4/2/NA/1", "30/1");
            put("Straight 1/2/4/2/NA/2", "31/1");
            put("Straight 1/2/4/3/NA/N", "32/1");
            put("Straight 1/2/4/3/NA/1", "33/1");
            put("Straight 1/2/4/3/NA/2", "34/1");
            put("Straight 1/2/5/1/NA/N", "35/1");
            put("Straight 1/2/5/1/NA/1", "36/1");
            put("Straight 1/2/5/1/NA/2", "37/1");
            put("Straight 1/2/5/2/NA/N", "38/1");
            put("Straight 1/2/5/2/NA/1", "39/1");
            put("Straight 1/2/5/2/NA/2", "40/1");
            put("Straight 1/2/5/3/NA/N", "41/1");
            put("Straight 1/2/5/3/NA/1", "42/1");
            put("Straight 1/2/5/3/NA/2", "43/1");
            put("Straight 1/2/6/1/NA/N", "44/1");
            put("Straight 1/2/6/1/NA/1", "45/1");
            put("Straight 1/2/6/1/NA/2", "46/1");
            put("Straight 1/2/6/2/NA/N", "47/1");
            put("Straight 1/2/6/2/NA/1", "48/1");
            put("Straight 1/2/6/2/NA/2", "49/1");
            put("Straight 1/2/6/3/NA/N", "50/1");
            put("Straight 1/2/6/3/NA/1", "51/1");
            put("Straight 1/2/6/3/NA/2", "52/1");
            put("Straight 1/2/7/1/NA/N", "53/1");
            put("Straight 1/2/7/1/NA/1", "54/1");
            put("Straight 1/2/7/1/NA/2", "55/1");
            put("Straight 1/2/7/2/NA/N", "56/1");
            put("Straight 1/2/7/2/NA/1", "57/1");
            put("Straight 1/2/7/2/NA/2", "58/1");
            put("Straight 1/2/7/3/NA/N", "59/1");
            put("Straight 1/2/7/3/NA/1", "60/1");
            put("Straight 1/2/7/3/NA/2", "61/1");
            put("Straight 1/2/8/1/NA/N", "62/1");
            put("Straight 1/2/8/1/NA/1", "63/1");
            put("Straight 1/2/8/1/NA/2", "64/1");
            put("Straight 1/2/8/2/NA/N", "65/1");
            put("Straight 1/2/8/2/NA/1", "66/1");
            put("Straight 1/2/8/2/NA/2", "67/1");
            put("Straight 1/2/8/3/NA/N", "68/1");
            put("Straight 1/2/8/3/NA/1", "69/1");
            put("Straight 1/2/8/3/NA/2", "70/1");
            put("Straight 1/2/9/1/NA/N", "71/1");
            put("Straight 1/2/9/1/NA/1", "72/1");
            put("Straight 1/2/9/1/NA/2", "73/1");
            put("Straight 1/2/9/2/NA/N", "74/1");
            put("Straight 1/2/9/2/NA/1", "75/1");
            put("Straight 1/2/9/2/NA/2", "76/1");
            put("Straight 1/2/9/3/NA/N", "77/1");
            put("Straight 1/2/9/3/NA/1", "78/1");
            put("Straight 1/2/9/3/NA/2", "79/1");
            put("Straight 1/2/10/1/NA/NA", "80/1");
            put("Straight 1/2/10/2/NA/NA", "81/1");
            put("Straight 1/2/10/3/NA/NA", "82/1");
            put("Straight 1/2/11/1/NA/NA", "83/1");
            put("Straight 1/2/11/2/NA/NA", "84/1");
            put("Straight 1/2/11/3/NA/NA", "85/1");
            put("Straight 1/2/12/1/NA/NA", "86/1");
            put("Straight 1/2/12/2/NA/NA", "87/1");
            put("Straight 1/2/12/3/NA/NA", "88/1");
            put("Straight 1/2/13/1/NA/NA", "89/1");
            put("Straight 1/2/13/2/NA/NA", "90/1");
            put("Straight 1/2/13/3/NA/NA", "91/1");
            put("Straight 1/2/14/1/NA/NA", "92/1");
            put("Straight 1/2/14/2/NA/NA", "93/1");
            put("Straight 1/2/14/3/NA/NA", "94/1");
            put("Straight 1/2/15/1/NA/NA", "95/1");
            put("Straight 1/2/15/2/NA/NA", "96/1");
            put("Straight 1/2/15/3/NA/NA", "97/1");
            put("Straight 1/2/16/1/NA/NA", "98/1");
            put("Straight 1/2/16/2/NA/NA", "99/1");
            put("Straight 1/2/16/3/NA/NA", "100/1");
            put("Straight 1/3/1/1/NA/N", "101/1");
            put("Straight 1/3/1/1/NA/1", "102/1");
            put("Straight 1/3/1/1/NA/2", "103/1");
            put("Straight 1/3/1/2/NA/N", "104/1");
            put("Straight 1/3/1/2/NA/1", "105/1");
            put("Straight 1/3/1/2/NA/2", "106/1");
            put("Straight 1/3/1/3/NA/N", "107/1");
            put("Straight 1/3/1/3/NA/1", "108/1");
            put("Straight 1/3/1/3/NA/2", "109/1");
            put("Straight 1/3/2/1/NA/N", "110/1");
            put("Straight 1/3/2/1/NA/1", "111/1");
            put("Straight 1/3/2/1/NA/2", "112/1");
            put("Straight 1/3/2/2/NA/N", "113/1");
            put("Straight 1/3/2/2/NA/1", "114/1");
            put("Straight 1/3/2/2/NA/2", "115/1");
            put("Straight 1/3/2/3/NA/N", "116/1");
            put("Straight 1/3/2/3/NA/1", "117/1");
            put("Straight 1/3/2/3/NA/2", "118/1");
            put("Straight 1/3/3/1/NA/N", "119/1");
            put("Straight 1/3/3/1/NA/1", "120/1");
            put("Straight 1/3/3/1/NA/2", "121/1");
            put("Straight 1/3/3/2/NA/N", "122/1");
            put("Straight 1/3/3/2/NA/1", "123/1");
            put("Straight 1/3/3/2/NA/2", "124/1");
            put("Straight 1/3/3/3/NA/N", "125/1");
            put("Straight 1/3/3/3/NA/1", "126/1");
            put("Straight 1/3/3/3/NA/2", "127/1");
            put("Straight 1/3/4/1/NA/N", "-128/1");
            put("Straight 1/3/4/1/NA/1", "-127/1");
            put("Straight 1/3/4/1/NA/2", "-126/1");
            put("Straight 1/3/4/2/NA/N", "-125/1");
            put("Straight 1/3/4/2/NA/1", "-124/1");
            put("Straight 1/3/4/2/NA/2", "-123/1");
            put("Straight 1/3/4/3/NA/N", "-122/1");
            put("Straight 1/3/4/3/NA/1", "-121/1");
            put("Straight 1/3/4/3/NA/2", "-120/1");
            put("Straight 1/3/5/1/NA/N", "-119/1");
            put("Straight 1/3/5/1/NA/1", "-118/1");
            put("Straight 1/3/5/1/NA/2", "-117/1");
            put("Straight 1/3/5/2/NA/N", "-116/1");
            put("Straight 1/3/5/2/NA/1", "-115/1");
            put("Straight 1/3/5/2/NA/2", "-114/1");
            put("Straight 1/3/5/3/NA/N", "-113/1");
            put("Straight 1/3/5/3/NA/1", "-112/1");
            put("Straight 1/3/5/3/NA/2", "-111/1");
            put("Straight 1/3/6/1/NA/N", "-110/1");
            put("Straight 1/3/6/1/NA/1", "-109/1");
            put("Straight 1/3/6/1/NA/2", "-108/1");
            put("Straight 1/3/6/2/NA/N", "-107/1");
            put("Straight 1/3/6/2/NA/1", "-106/1");
            put("Straight 1/3/6/2/NA/2", "-105/1");
            put("Straight 1/3/6/3/NA/N", "-104/1");
            put("Straight 1/3/6/3/NA/1", "-103/1");
            put("Straight 1/3/6/3/NA/2", "-102/1");
            put("Straight 1/3/7/1/NA/N", "-101/1");
            put("Straight 1/3/7/1/NA/1", "-100/1");
            put("Straight 1/3/7/1/NA/2", "-99/1");
            put("Straight 1/3/7/2/NA/N", "-98/1");
            put("Straight 1/3/7/2/NA/1", "-97/1");
            put("Straight 1/3/7/2/NA/2", "-96/1");
            put("Straight 1/3/7/3/NA/N", "-95/1");
            put("Straight 1/3/7/3/NA/1", "-94/1");
            put("Straight 1/3/7/3/NA/2", "-93/1");
            put("Straight 1/3/8/1/NA/N", "-92/1");
            put("Straight 1/3/8/1/NA/1", "-91/1");
            put("Straight 1/3/8/1/NA/2", "-90/1");
            put("Straight 1/3/8/2/NA/N", "-89/1");
            put("Straight 1/3/8/2/NA/1", "-88/1");
            put("Straight 1/3/8/2/NA/2", "-87/1");
            put("Straight 1/3/8/3/NA/N", "-86/1");
            put("Straight 1/3/8/3/NA/1", "-85/1");
            put("Straight 1/3/8/3/NA/2", "-84/1");
            put("Straight 1/3/9/1/NA/N", "-83/1");
            put("Straight 1/3/9/1/NA/1", "-82/1");
            put("Straight 1/3/9/1/NA/2", "-81/1");
            put("Straight 1/3/9/2/NA/N", "-80/1");
            put("Straight 1/3/9/2/NA/1", "-79/1");
            put("Straight 1/3/9/2/NA/2", "-78/1");
            put("Straight 1/3/9/3/NA/N", "-77/1");
            put("Straight 1/3/9/3/NA/1", "-76/1");
            put("Straight 1/3/9/3/NA/2", "-75/1");
            put("Straight 1/3/10/1/NA/NA", "-74/1");
            put("Straight 1/3/10/2/NA/NA", "-73/1");
            put("Straight 1/3/10/3/NA/NA", "-72/1");
            put("Straight 1/3/11/1/NA/NA", "-71/1");
            put("Straight 1/3/11/2/NA/NA", "-70/1");
            put("Straight 1/3/11/3/NA/NA", "-69/1");
            put("Straight 1/3/12/1/NA/NA", "-68/1");
            put("Straight 1/3/12/2/NA/NA", "-67/1");
            put("Straight 1/3/12/3/NA/NA", "-66/1");
            put("Straight 1/3/13/1/NA/NA", "-65/1");
            put("Straight 1/3/13/2/NA/NA", "-64/1");
            put("Straight 1/3/13/3/NA/NA", "-63/1");
            put("Straight 1/3/14/1/NA/NA", "-62/1");
            put("Straight 1/3/14/2/NA/NA", "-61/1");
            put("Straight 1/3/14/3/NA/NA", "-60/1");
            put("Straight 1/3/15/1/NA/NA", "-59/1");
            put("Straight 1/3/15/2/NA/NA", "-58/1");
            put("Straight 1/3/15/3/NA/NA", "-57/1");
            put("Straight 1/3/16/1/NA/NA", "-56/1");
            put("Straight 1/3/16/2/NA/NA", "-55/1");
            put("Straight 1/3/16/3/NA/NA", "-54/1");
            put("Straight 1/4/1/1/NA/N", "-53/1");
            put("Straight 1/4/1/1/NA/1", "-52/1");
            put("Straight 1/4/1/1/NA/2", "-51/1");
            put("Straight 1/4/1/2/NA/N", "-50/1");
            put("Straight 1/4/1/2/NA/1", "-49/1");
            put("Straight 1/4/1/2/NA/2", "-48/1");
            put("Straight 1/4/1/3/NA/N", "-47/1");
            put("Straight 1/4/1/3/NA/1", "-46/1");
            put("Straight 1/4/1/3/NA/2", "-45/1");
            put("Straight 1/4/2/1/NA/N", "-44/1");
            put("Straight 1/4/2/1/NA/1", "-43/1");
            put("Straight 1/4/2/1/NA/2", "-42/1");
            put("Straight 1/4/2/2/NA/N", "-41/1");
            put("Straight 1/4/2/2/NA/1", "-40/1");
            put("Straight 1/4/2/2/NA/2", "-39/1");
            put("Straight 1/4/2/3/NA/N", "-38/1");
            put("Straight 1/4/2/3/NA/1", "-37/1");
            put("Straight 1/4/2/3/NA/2", "-36/1");
            put("Straight 1/4/3/1/NA/N", "-35/1");
            put("Straight 1/4/3/1/NA/1", "-34/1");
            put("Straight 1/4/3/1/NA/2", "-33/1");
            put("Straight 1/4/3/2/NA/N", "-32/1");
            put("Straight 1/4/3/2/NA/1", "-31/1");
            put("Straight 1/4/3/2/NA/2", "-30/1");
            put("Straight 1/4/3/3/NA/N", "-29/1");
            put("Straight 1/4/3/3/NA/1", "-28/1");
            put("Straight 1/4/3/3/NA/2", "-27/1");
            put("Straight 1/4/4/1/NA/N", "-26/1");
            put("Straight 1/4/4/1/NA/1", "-25/1");
            put("Straight 1/4/4/1/NA/2", "-24/1");
            put("Straight 1/4/4/2/NA/N", "-23/1");
            put("Straight 1/4/4/2/NA/1", "-22/1");
            put("Straight 1/4/4/2/NA/2", "-21/1");
            put("Straight 1/4/4/3/NA/N", "-20/1");
            put("Straight 1/4/4/3/NA/1", "-19/1");
            put("Straight 1/4/4/3/NA/2", "-18/1");
            put("Straight 1/4/5/1/NA/N", "-17/1");
            put("Straight 1/4/5/1/NA/1", "-16/1");
            put("Straight 1/4/5/1/NA/2", "-15/1");
            put("Straight 1/4/5/2/NA/N", "-14/1");
            put("Straight 1/4/5/2/NA/1", "-13/1");
            put("Straight 1/4/5/2/NA/2", "-12/1");
            put("Straight 1/4/5/3/NA/N", "-11/1");
            put("Straight 1/4/5/3/NA/1", "-10/1");
            put("Straight 1/4/5/3/NA/2", "-9/1");
            put("Straight 1/4/6/1/NA/N", "-8/1");
            put("Straight 1/4/6/1/NA/1", "-7/1");
            put("Straight 1/4/6/1/NA/2", "-6/1");
            put("Straight 1/4/6/2/NA/N", "-5/1");
            put("Straight 1/4/6/2/NA/1", "-4/1");
            put("Straight 1/4/6/2/NA/2", "-3/1");
            put("Straight 1/4/6/3/NA/N", "-2/1");
            put("Straight 1/4/6/3/NA/1", "-1/1");
            put("Straight 1/4/6/3/NA/2", "0/2");
            put("Straight 1/4/7/1/NA/N", "1/2");
            put("Straight 1/4/7/1/NA/1", "2/2");
            put("Straight 1/4/7/1/NA/2", "3/2");
            put("Straight 1/4/7/2/NA/N", "4/2");
            put("Straight 1/4/7/2/NA/1", "5/2");
            put("Straight 1/4/7/2/NA/2", "6/2");
            put("Straight 1/4/7/3/NA/N", "7/2");
            put("Straight 1/4/7/3/NA/1", "8/2");
            put("Straight 1/4/7/3/NA/2", "9/2");
            put("Straight 1/4/8/1/NA/N", "10/2");
            put("Straight 1/4/8/1/NA/1", "11/2");
            put("Straight 1/4/8/1/NA/2", "12/2");
            put("Straight 1/4/8/2/NA/N", "13/2");
            put("Straight 1/4/8/2/NA/1", "14/2");
            put("Straight 1/4/8/2/NA/2", "15/2");
            put("Straight 1/4/8/3/NA/N", "16/2");
            put("Straight 1/4/8/3/NA/1", "17/2");
            put("Straight 1/4/8/3/NA/2", "18/2");
            put("Straight 1/4/9/1/NA/N", "19/2");
            put("Straight 1/4/9/1/NA/1", "20/2");
            put("Straight 1/4/9/1/NA/2", "21/2");
            put("Straight 1/4/9/2/NA/N", "22/2");
            put("Straight 1/4/9/2/NA/1", "23/2");
            put("Straight 1/4/9/2/NA/2", "24/2");
            put("Straight 1/4/9/3/NA/N", "25/2");
            put("Straight 1/4/9/3/NA/1", "26/2");
            put("Straight 1/4/9/3/NA/2", "27/2");
            put("Straight 1/4/10/1/NA/NA", "28/2");
            put("Straight 1/4/10/2/NA/NA", "29/2");
            put("Straight 1/4/10/3/NA/NA", "30/2");
            put("Straight 1/4/11/1/NA/NA", "31/2");
            put("Straight 1/4/11/2/NA/NA", "32/2");
            put("Straight 1/4/11/3/NA/NA", "33/2");
            put("Straight 1/4/12/1/NA/NA", "34/2");
            put("Straight 1/4/12/2/NA/NA", "35/2");
            put("Straight 1/4/12/3/NA/NA", "36/2");
            put("Straight 1/4/13/1/NA/NA", "37/2");
            put("Straight 1/4/13/2/NA/NA", "38/2");
            put("Straight 1/4/13/3/NA/NA", "39/2");
            put("Straight 1/4/14/1/NA/NA", "40/2");
            put("Straight 1/4/14/2/NA/NA", "41/2");
            put("Straight 1/4/14/3/NA/NA", "42/2");
            put("Straight 1/4/15/1/NA/NA", "43/2");
            put("Straight 1/4/15/2/NA/NA", "44/2");
            put("Straight 1/4/15/3/NA/NA", "45/2");
            put("Straight 1/4/16/1/NA/NA", "46/2");
            put("Straight 1/4/16/2/NA/NA", "47/2");
            put("Straight 1/4/16/3/NA/NA", "48/2");
            put("Straight 2/1/1/1/NA/N", "49/2");
            put("Straight 2/1/1/1/NA/1", "50/2");
            put("Straight 2/1/1/1/NA/2", "51/2");
            put("Straight 2/1/1/2/NA/N", "52/2");
            put("Straight 2/1/1/2/NA/1", "53/2");
            put("Straight 2/1/1/2/NA/2", "54/2");
            put("Straight 2/1/1/3/NA/N", "55/2");
            put("Straight 2/1/1/3/NA/1", "56/2");
            put("Straight 2/1/1/3/NA/2", "57/2");
            put("Straight 2/1/2/1/NA/N", "58/2");
            put("Straight 2/1/2/1/NA/1", "59/2");
            put("Straight 2/1/2/1/NA/2", "60/2");
            put("Straight 2/1/2/2/NA/N", "61/2");
            put("Straight 2/1/2/2/NA/1", "62/2");
            put("Straight 2/1/2/2/NA/2", "63/2");
            put("Straight 2/1/2/3/NA/N", "64/2");
            put("Straight 2/1/2/3/NA/1", "65/2");
            put("Straight 2/1/2/3/NA/2", "66/2");
            put("Straight 2/1/3/1/NA/NA", "67/2");
            put("Straight 2/1/3/2/NA/NA", "68/2");
            put("Straight 2/1/3/3/NA/NA", "69/2");
            put("Straight 2/1/4/1/NA/NA", "70/2");
            put("Straight 2/1/4/2/NA/NA", "71/2");
            put("Straight 2/1/4/3/NA/NA", "72/2");
            put("Straight 2/1/5/1/NA/NA", "73/2");
            put("Straight 2/1/5/2/NA/NA", "74/2");
            put("Straight 2/1/5/3/NA/NA", "75/2");
            put("Straight 2/1/6/1/NA/NA", "76/2");
            put("Straight 2/1/6/2/NA/NA", "77/2");
            put("Straight 2/1/6/3/NA/NA", "78/2");
            put("Straight 2/1/7/1/NA/NA", "79/2");
            put("Straight 2/1/7/2/NA/NA", "80/2");
            put("Straight 2/1/7/3/NA/NA", "81/2");
            put("Straight 2/2/1/1/NA/N", "82/2");
            put("Straight 2/2/1/1/NA/1", "83/2");
            put("Straight 2/2/1/1/NA/2", "84/2");
            put("Straight 2/2/1/2/NA/N", "85/2");
            put("Straight 2/2/1/2/NA/1", "86/2");
            put("Straight 2/2/1/2/NA/2", "87/2");
            put("Straight 2/2/1/3/NA/N", "88/2");
            put("Straight 2/2/1/3/NA/1", "89/2");
            put("Straight 2/2/1/3/NA/2", "90/2");
            put("Straight 2/2/2/1/NA/N", "91/2");
            put("Straight 2/2/2/1/NA/1", "92/2");
            put("Straight 2/2/2/1/NA/2", "93/2");
            put("Straight 2/2/2/2/NA/N", "94/2");
            put("Straight 2/2/2/2/NA/1", "95/2");
            put("Straight 2/2/2/2/NA/2", "96/2");
            put("Straight 2/2/2/3/NA/N", "97/2");
            put("Straight 2/2/2/3/NA/1", "98/2");
            put("Straight 2/2/2/3/NA/2", "99/2");
            put("Straight 2/2/3/1/NA/NA", "100/2");
            put("Straight 2/2/3/2/NA/NA", "101/2");
            put("Straight 2/2/3/3/NA/NA", "102/2");
            put("Straight 2/2/4/1/NA/NA", "103/2");
            put("Straight 2/2/4/2/NA/NA", "104/2");
            put("Straight 2/2/4/3/NA/NA", "105/2");
            put("Straight 2/2/5/1/NA/NA", "106/2");
            put("Straight 2/2/5/2/NA/NA", "107/2");
            put("Straight 2/2/5/3/NA/NA", "108/2");
            put("Straight 2/2/6/1/NA/NA", "109/2");
            put("Straight 2/2/6/2/NA/NA", "110/2");
            put("Straight 2/2/6/3/NA/NA", "111/2");
            put("Straight 2/2/7/1/NA/NA", "112/2");
            put("Straight 2/2/7/2/NA/NA", "113/2");
            put("Straight 2/2/7/3/NA/NA", "114/2");
            put("Straight 2/3/1/1/NA/N", "115/2");
            put("Straight 2/3/1/1/NA/1", "116/2");
            put("Straight 2/3/1/1/NA/2", "117/2");
            put("Straight 2/3/1/2/NA/N", "118/2");
            put("Straight 2/3/1/2/NA/1", "119/2");
            put("Straight 2/3/1/2/NA/2", "120/2");
            put("Straight 2/3/1/3/NA/N", "121/2");
            put("Straight 2/3/1/3/NA/1", "122/2");
            put("Straight 2/3/1/3/NA/2", "123/2");
            put("Straight 2/3/2/1/NA/N", "124/2");
            put("Straight 2/3/2/1/NA/1", "125/2");
            put("Straight 2/3/2/1/NA/2", "126/2");
            put("Straight 2/3/2/2/NA/N", "127/2");
            put("Straight 2/3/2/2/NA/1", "-128/2");
            put("Straight 2/3/2/2/NA/2", "-127/2");
            put("Straight 2/3/2/3/NA/N", "-126/2");
            put("Straight 2/3/2/3/NA/1", "-125/2");
            put("Straight 2/3/2/3/NA/2", "-124/2");
            put("Straight 2/3/3/1/NA/NA", "-123/2");
            put("Straight 2/3/3/2/NA/NA", "-122/2");
            put("Straight 2/3/3/3/NA/NA", "-121/2");
            put("Straight 2/3/4/1/NA/NA", "-120/2");
            put("Straight 2/3/4/2/NA/NA", "-119/2");
            put("Straight 2/3/4/3/NA/NA", "-118/2");
            put("Straight 2/3/5/1/NA/NA", "-117/2");
            put("Straight 2/3/5/2/NA/NA", "-116/2");
            put("Straight 2/3/5/3/NA/NA", "-115/2");
            put("Straight 2/3/6/1/NA/NA", "-114/2");
            put("Straight 2/3/6/2/NA/NA", "-113/2");
            put("Straight 2/3/6/3/NA/NA", "-112/2");
            put("Straight 2/3/7/1/NA/NA", "-111/2");
            put("Straight 2/3/7/2/NA/NA", "-110/2");
            put("Straight 2/3/7/3/NA/NA", "-109/2");
            put("Curly 1/1/1/1/NA/N", "-108/2");
            put("Curly 1/1/1/1/NA/1", "-107/2");
            put("Curly 1/1/1/1/NA/2", "-106/2");
            put("Curly 1/1/1/2/NA/N", "-105/2");
            put("Curly 1/1/1/2/NA/1", "-104/2");
            put("Curly 1/1/1/2/NA/2", "-103/2");
            put("Curly 1/1/1/3/NA/N", "-102/2");
            put("Curly 1/1/1/3/NA/1", "-101/2");
            put("Curly 1/1/1/3/NA/2", "-100/2");
            put("Curly 1/1/2/1/NA/N", "-99/2");
            put("Curly 1/1/2/1/NA/1", "-98/2");
            put("Curly 1/1/2/1/NA/2", "-97/2");
            put("Curly 1/1/2/2/NA/N", "-96/2");
            put("Curly 1/1/2/2/NA/1", "-95/2");
            put("Curly 1/1/2/2/NA/2", "-94/2");
            put("Curly 1/1/2/3/NA/N", "-93/2");
            put("Curly 1/1/2/3/NA/1", "-92/2");
            put("Curly 1/1/2/3/NA/2", "-91/2");
            put("Curly 1/1/3/1/NA/N", "-90/2");
            put("Curly 1/1/3/1/NA/1", "-89/2");
            put("Curly 1/1/3/1/NA/2", "-88/2");
            put("Curly 1/1/3/2/NA/N", "-87/2");
            put("Curly 1/1/3/2/NA/1", "-86/2");
            put("Curly 1/1/3/2/NA/2", "-85/2");
            put("Curly 1/1/3/3/NA/N", "-84/2");
            put("Curly 1/1/3/3/NA/1", "-83/2");
            put("Curly 1/1/3/3/NA/2", "-82/2");
            put("Curly 1/1/4/1/NA/N", "-81/2");
            put("Curly 1/1/4/1/NA/1", "-80/2");
            put("Curly 1/1/4/1/NA/2", "-79/2");
            put("Curly 1/1/4/2/NA/N", "-78/2");
            put("Curly 1/1/4/2/NA/1", "-77/2");
            put("Curly 1/1/4/2/NA/2", "-76/2");
            put("Curly 1/1/4/3/NA/N", "-75/2");
            put("Curly 1/1/4/3/NA/1", "-74/2");
            put("Curly 1/1/4/3/NA/2", "-73/2");
            put("Curly 1/1/5/1/NA/N", "-72/2");
            put("Curly 1/1/5/1/NA/1", "-71/2");
            put("Curly 1/1/5/1/NA/2", "-70/2");
            put("Curly 1/1/5/2/NA/N", "-69/2");
            put("Curly 1/1/5/2/NA/1", "-68/2");
            put("Curly 1/1/5/2/NA/2", "-67/2");
            put("Curly 1/1/5/3/NA/N", "-66/2");
            put("Curly 1/1/5/3/NA/1", "-65/2");
            put("Curly 1/1/5/3/NA/2", "-64/2");
            put("Curly 1/1/6/1/NA/NA", "-63/2");
            put("Curly 1/1/6/2/NA/NA", "-62/2");
            put("Curly 1/1/6/3/NA/NA", "-61/2");
            put("Curly 1/1/7/1/NA/NA", "-60/2");
            put("Curly 1/1/7/2/NA/NA", "-59/2");
            put("Curly 1/1/7/3/NA/NA", "-58/2");
            put("Curly 1/2/1/1/NA/N", "-57/2");
            put("Curly 1/2/1/1/NA/1", "-56/2");
            put("Curly 1/2/1/1/NA/2", "-55/2");
            put("Curly 1/2/1/2/NA/N", "-54/2");
            put("Curly 1/2/1/2/NA/1", "-53/2");
            put("Curly 1/2/1/2/NA/2", "-52/2");
            put("Curly 1/2/1/3/NA/N", "-51/2");
            put("Curly 1/2/1/3/NA/1", "-50/2");
            put("Curly 1/2/1/3/NA/2", "-49/2");
            put("Curly 1/2/2/1/NA/N", "-48/2");
            put("Curly 1/2/2/1/NA/1", "-47/2");
            put("Curly 1/2/2/1/NA/2", "-46/2");
            put("Curly 1/2/2/2/NA/N", "-45/2");
            put("Curly 1/2/2/2/NA/1", "-44/2");
            put("Curly 1/2/2/2/NA/2", "-43/2");
            put("Curly 1/2/2/3/NA/N", "-42/2");
            put("Curly 1/2/2/3/NA/1", "-41/2");
            put("Curly 1/2/2/3/NA/2", "-40/2");
            put("Curly 1/2/3/1/NA/N", "-39/2");
            put("Curly 1/2/3/1/NA/1", "-38/2");
            put("Curly 1/2/3/1/NA/2", "-37/2");
            put("Curly 1/2/3/2/NA/N", "-36/2");
            put("Curly 1/2/3/2/NA/1", "-35/2");
            put("Curly 1/2/3/2/NA/2", "-34/2");
            put("Curly 1/2/3/3/NA/N", "-33/2");
            put("Curly 1/2/3/3/NA/1", "-32/2");
            put("Curly 1/2/3/3/NA/2", "-31/2");
            put("Curly 1/2/4/1/NA/N", "-30/2");
            put("Curly 1/2/4/1/NA/1", "-29/2");
            put("Curly 1/2/4/1/NA/2", "-28/2");
            put("Curly 1/2/4/2/NA/N", "-27/2");
            put("Curly 1/2/4/2/NA/1", "-26/2");
            put("Curly 1/2/4/2/NA/2", "-25/2");
            put("Curly 1/2/4/3/NA/N", "-24/2");
            put("Curly 1/2/4/3/NA/1", "-23/2");
            put("Curly 1/2/4/3/NA/2", "-22/2");
            put("Curly 1/2/5/1/NA/N", "-21/2");
            put("Curly 1/2/5/1/NA/1", "-20/2");
            put("Curly 1/2/5/1/NA/2", "-19/2");
            put("Curly 1/2/5/2/NA/N", "-18/2");
            put("Curly 1/2/5/2/NA/1", "-17/2");
            put("Curly 1/2/5/2/NA/2", "-16/2");
            put("Curly 1/2/5/3/NA/N", "-15/2");
            put("Curly 1/2/5/3/NA/1", "-14/2");
            put("Curly 1/2/5/3/NA/2", "-13/2");
            put("Curly 1/2/6/1/NA/NA", "-12/2");
            put("Curly 1/2/6/2/NA/NA", "-11/2");
            put("Curly 1/2/6/3/NA/NA", "-10/2");
            put("Curly 1/2/7/1/NA/NA", "-9/2");
            put("Curly 1/2/7/2/NA/NA", "-8/2");
            put("Curly 1/2/7/3/NA/NA", "-7/2");
            put("Curly 1/3/1/1/NA/N", "-6/2");
            put("Curly 1/3/1/1/NA/1", "-5/2");
            put("Curly 1/3/1/1/NA/2", "-4/2");
            put("Curly 1/3/1/2/NA/N", "-3/2");
            put("Curly 1/3/1/2/NA/1", "-2/2");
            put("Curly 1/3/1/2/NA/2", "-1/2");
            put("Curly 1/3/1/3/NA/N", "0/3");
            put("Curly 1/3/1/3/NA/1", "1/3");
            put("Curly 1/3/1/3/NA/2", "2/3");
            put("Curly 1/3/2/1/NA/N", "3/3");
            put("Curly 1/3/2/1/NA/1", "4/3");
            put("Curly 1/3/2/1/NA/2", "5/3");
            put("Curly 1/3/2/2/NA/N", "6/3");
            put("Curly 1/3/2/2/NA/1", "7/3");
            put("Curly 1/3/2/2/NA/2", "8/3");
            put("Curly 1/3/2/3/NA/N", "9/3");
            put("Curly 1/3/2/3/NA/1", "10/3");
            put("Curly 1/3/2/3/NA/2", "11/3");
            put("Curly 1/3/3/1/NA/N", "12/3");
            put("Curly 1/3/3/1/NA/1", "13/3");
            put("Curly 1/3/3/1/NA/2", "14/3");
            put("Curly 1/3/3/2/NA/N", "15/3");
            put("Curly 1/3/3/2/NA/1", "16/3");
            put("Curly 1/3/3/2/NA/2", "17/3");
            put("Curly 1/3/3/3/NA/N", "18/3");
            put("Curly 1/3/3/3/NA/1", "19/3");
            put("Curly 1/3/3/3/NA/2", "20/3");
            put("Curly 1/3/4/1/NA/N", "21/3");
            put("Curly 1/3/4/1/NA/1", "22/3");
            put("Curly 1/3/4/1/NA/2", "23/3");
            put("Curly 1/3/4/2/NA/N", "24/3");
            put("Curly 1/3/4/2/NA/1", "25/3");
            put("Curly 1/3/4/2/NA/2", "26/3");
            put("Curly 1/3/4/3/NA/N", "27/3");
            put("Curly 1/3/4/3/NA/1", "28/3");
            put("Curly 1/3/4/3/NA/2", "29/3");
            put("Curly 1/3/5/1/NA/N", "30/3");
            put("Curly 1/3/5/1/NA/1", "31/3");
            put("Curly 1/3/5/1/NA/2", "32/3");
            put("Curly 1/3/5/2/NA/N", "33/3");
            put("Curly 1/3/5/2/NA/1", "34/3");
            put("Curly 1/3/5/2/NA/2", "35/3");
            put("Curly 1/3/5/3/NA/N", "36/3");
            put("Curly 1/3/5/3/NA/1", "37/3");
            put("Curly 1/3/5/3/NA/2", "38/3");
            put("Curly 1/3/6/1/NA/NA", "39/3");
            put("Curly 1/3/6/2/NA/NA", "40/3");
            put("Curly 1/3/6/3/NA/NA", "41/3");
            put("Curly 1/3/7/1/NA/NA", "42/3");
            put("Curly 1/3/7/2/NA/NA", "43/3");
            put("Curly 1/3/7/3/NA/NA", "44/3");
            put("Curly 1/4/1/1/NA/N", "45/3");
            put("Curly 1/4/1/1/NA/1", "46/3");
            put("Curly 1/4/1/1/NA/2", "47/3");
            put("Curly 1/4/1/2/NA/N", "48/3");
            put("Curly 1/4/1/2/NA/1", "49/3");
            put("Curly 1/4/1/2/NA/2", "50/3");
            put("Curly 1/4/1/3/NA/N", "51/3");
            put("Curly 1/4/1/3/NA/1", "52/3");
            put("Curly 1/4/1/3/NA/2", "53/3");
            put("Curly 1/4/2/1/NA/N", "54/3");
            put("Curly 1/4/2/1/NA/1", "55/3");
            put("Curly 1/4/2/1/NA/2", "56/3");
            put("Curly 1/4/2/2/NA/N", "57/3");
            put("Curly 1/4/2/2/NA/1", "58/3");
            put("Curly 1/4/2/2/NA/2", "59/3");
            put("Curly 1/4/2/3/NA/N", "60/3");
            put("Curly 1/4/2/3/NA/1", "61/3");
            put("Curly 1/4/2/3/NA/2", "62/3");
            put("Curly 1/4/3/1/NA/N", "63/3");
            put("Curly 1/4/3/1/NA/1", "64/3");
            put("Curly 1/4/3/1/NA/2", "65/3");
            put("Curly 1/4/3/2/NA/N", "66/3");
            put("Curly 1/4/3/2/NA/1", "67/3");
            put("Curly 1/4/3/2/NA/2", "68/3");
            put("Curly 1/4/3/3/NA/N", "69/3");
            put("Curly 1/4/3/3/NA/1", "70/3");
            put("Curly 1/4/3/3/NA/2", "71/3");
            put("Curly 1/4/4/1/NA/N", "72/3");
            put("Curly 1/4/4/1/NA/1", "73/3");
            put("Curly 1/4/4/1/NA/2", "74/3");
            put("Curly 1/4/4/2/NA/N", "75/3");
            put("Curly 1/4/4/2/NA/1", "76/3");
            put("Curly 1/4/4/2/NA/2", "77/3");
            put("Curly 1/4/4/3/NA/N", "78/3");
            put("Curly 1/4/4/3/NA/1", "79/3");
            put("Curly 1/4/4/3/NA/2", "80/3");
            put("Curly 1/4/5/1/NA/N", "81/3");
            put("Curly 1/4/5/1/NA/1", "82/3");
            put("Curly 1/4/5/1/NA/2", "83/3");
            put("Curly 1/4/5/2/NA/N", "84/3");
            put("Curly 1/4/5/2/NA/1", "85/3");
            put("Curly 1/4/5/2/NA/2", "86/3");
            put("Curly 1/4/5/3/NA/N", "87/3");
            put("Curly 1/4/5/3/NA/1", "88/3");
            put("Curly 1/4/5/3/NA/2", "89/3");
            put("Curly 1/4/6/1/NA/NA", "90/3");
            put("Curly 1/4/6/2/NA/NA", "91/3");
            put("Curly 1/4/6/3/NA/NA", "92/3");
            put("Curly 1/4/7/1/NA/NA", "93/3");
            put("Curly 1/4/7/2/NA/NA", "94/3");
            put("Curly 1/4/7/3/NA/NA", "95/3");
            put("Curly 2/1/1/1/NA/NA", "96/3");
            put("Curly 2/1/1/2/NA/NA", "97/3");
            put("Curly 2/1/2/1/NA/NA", "98/3");
            put("Curly 2/1/2/2/NA/NA", "99/3");
            put("Curly 2/1/3/1/NA/NA", "100/3");
            put("Curly 2/1/3/2/NA/NA", "101/3");
            put("Curly 2/1/4/1/NA/NA", "102/3");
            put("Curly 2/1/4/2/NA/NA", "103/3");
            put("Curly 2/1/5/1/NA/NA", "104/3");
            put("Curly 2/1/5/2/NA/NA", "105/3");
            put("Curly 2/1/6/1/NA/NA", "106/3");
            put("Curly 2/1/6/2/NA/NA", "107/3");
            put("Curly 2/2/1/1/NA/NA", "108/3");
            put("Curly 2/2/1/2/NA/NA", "109/3");
            put("Curly 2/2/2/1/NA/NA", "110/3");
            put("Curly 2/2/2/2/NA/NA", "111/3");
            put("Curly 2/2/3/1/NA/NA", "112/3");
            put("Curly 2/2/3/2/NA/NA", "113/3");
            put("Curly 2/2/4/1/NA/NA", "114/3");
            put("Curly 2/2/4/2/NA/NA", "115/3");
            put("Curly 2/2/5/1/NA/NA", "116/3");
            put("Curly 2/2/5/2/NA/NA", "117/3");
            put("Curly 2/2/6/1/NA/NA", "118/3");
            put("Curly 2/2/6/2/NA/NA", "119/3");
            put("Curly 2/3/1/1/NA/NA", "120/3");
            put("Curly 2/3/1/2/NA/NA", "121/3");
            put("Curly 2/3/2/1/NA/NA", "122/3");
            put("Curly 2/3/2/2/NA/NA", "123/3");
            put("Curly 2/3/3/1/NA/NA", "124/3");
            put("Curly 2/3/3/2/NA/NA", "125/3");
            put("Curly 2/3/4/1/NA/NA", "126/3");
            put("Curly 2/3/4/2/NA/NA", "127/3");
            put("Curly 2/3/5/1/NA/NA", "-128/3");
            put("Curly 2/3/5/2/NA/NA", "-127/3");
            put("Curly 2/3/6/1/NA/NA", "-126/3");
            put("Curly 2/3/6/2/NA/NA", "-125/3");
            put("Curly 2/4/1/1/NA/NA", "-124/3");
            put("Curly 2/4/1/2/NA/NA", "-123/3");
            put("Curly 2/4/2/1/NA/NA", "-122/3");
            put("Curly 2/4/2/2/NA/NA", "-121/3");
            put("Curly 2/4/3/1/NA/NA", "-120/3");
            put("Curly 2/4/3/2/NA/NA", "-119/3");
            put("Curly 2/4/4/1/NA/NA", "-118/3");
            put("Curly 2/4/4/2/NA/NA", "-117/3");
            put("Curly 2/4/5/1/NA/NA", "-116/3");
            put("Curly 2/4/5/2/NA/NA", "-115/3");
            put("Curly 2/4/6/1/NA/NA", "-114/3");
            put("Curly 2/4/6/2/NA/NA", "-113/3");
            put("Ponytail 1/1/1/1/NA/NA", "-112/3");
            put("Ponytail 1/1/1/2/NA/NA", "-111/3");
            put("Ponytail 1/1/1/3/NA/NA", "-110/3");
            put("Ponytail 1/1/2/1/NA/NA", "-109/3");
            put("Ponytail 1/1/2/2/NA/NA", "-108/3");
            put("Ponytail 1/1/2/3/NA/NA", "-107/3");
            put("Ponytail 1/1/3/1/NA/NA", "-106/3");
            put("Ponytail 1/1/3/2/NA/NA", "-105/3");
            put("Ponytail 1/1/3/3/NA/NA", "-104/3");
            put("Ponytail 1/1/4/1/NA/NA", "-103/3");
            put("Ponytail 1/1/4/2/NA/NA", "-102/3");
            put("Ponytail 1/1/4/3/NA/NA", "-101/3");
            put("Ponytail 1/2/1/1/NA/NA", "-100/3");
            put("Ponytail 1/2/1/2/NA/NA", "-99/3");
            put("Ponytail 1/2/1/3/NA/NA", "-98/3");
            put("Ponytail 1/2/2/1/NA/NA", "-97/3");
            put("Ponytail 1/2/2/2/NA/NA", "-96/3");
            put("Ponytail 1/2/2/3/NA/NA", "-95/3");
            put("Ponytail 1/2/3/1/NA/NA", "-94/3");
            put("Ponytail 1/2/3/2/NA/NA", "-93/3");
            put("Ponytail 1/2/3/3/NA/NA", "-92/3");
            put("Ponytail 1/2/4/1/NA/NA", "-91/3");
            put("Ponytail 1/2/4/2/NA/NA", "-90/3");
            put("Ponytail 1/2/4/3/NA/NA", "-89/3");
            put("Ponytail 1/3/1/1/NA/NA", "-88/3");
            put("Ponytail 1/3/1/2/NA/NA", "-87/3");
            put("Ponytail 1/3/1/3/NA/NA", "-86/3");
            put("Ponytail 1/3/2/1/NA/NA", "-85/3");
            put("Ponytail 1/3/2/2/NA/NA", "-84/3");
            put("Ponytail 1/3/2/3/NA/NA", "-83/3");
            put("Ponytail 1/3/3/1/NA/NA", "-82/3");
            put("Ponytail 1/3/3/2/NA/NA", "-81/3");
            put("Ponytail 1/3/3/3/NA/NA", "-80/3");
            put("Ponytail 1/3/4/1/NA/NA", "-79/3");
            put("Ponytail 1/3/4/2/NA/NA", "-78/3");
            put("Ponytail 1/3/4/3/NA/NA", "-77/3");
            put("Ponytail 2/1/1/1/NA/NA", "-76/3");
            put("Ponytail 2/1/1/2/NA/NA", "-75/3");
            put("Ponytail 2/1/1/3/NA/NA", "-74/3");
            put("Ponytail 2/1/2/1/NA/NA", "-73/3");
            put("Ponytail 2/1/2/2/NA/NA", "-72/3");
            put("Ponytail 2/1/2/3/NA/NA", "-71/3");
            put("Ponytail 2/1/3/1/NA/NA", "-70/3");
            put("Ponytail 2/1/3/2/NA/NA", "-69/3");
            put("Ponytail 2/1/3/3/NA/NA", "-68/3");
            put("Ponytail 2/1/4/1/NA/NA", "-67/3");
            put("Ponytail 2/1/4/2/NA/NA", "-66/3");
            put("Ponytail 2/1/4/3/NA/NA", "-65/3");
            put("Ponytail 2/2/1/1/NA/NA", "-64/3");
            put("Ponytail 2/2/1/2/NA/NA", "-63/3");
            put("Ponytail 2/2/1/3/NA/NA", "-62/3");
            put("Ponytail 2/2/2/1/NA/NA", "-61/3");
            put("Ponytail 2/2/2/2/NA/NA", "-60/3");
            put("Ponytail 2/2/2/3/NA/NA", "-59/3");
            put("Ponytail 2/2/3/1/NA/NA", "-58/3");
            put("Ponytail 2/2/3/2/NA/NA", "-57/3");
            put("Ponytail 2/2/3/3/NA/NA", "-56/3");
            put("Ponytail 2/2/4/1/NA/NA", "-55/3");
            put("Ponytail 2/2/4/2/NA/NA", "-54/3");
            put("Ponytail 2/2/4/3/NA/NA", "-53/3");
            put("Ponytail 2/3/1/1/NA/NA", "-52/3");
            put("Ponytail 2/3/1/2/NA/NA", "-51/3");
            put("Ponytail 2/3/1/3/NA/NA", "-50/3");
            put("Ponytail 2/3/2/1/NA/NA", "-49/3");
            put("Ponytail 2/3/2/2/NA/NA", "-48/3");
            put("Ponytail 2/3/2/3/NA/NA", "-47/3");
            put("Ponytail 2/3/3/1/NA/NA", "-46/3");
            put("Ponytail 2/3/3/2/NA/NA", "-45/3");
            put("Ponytail 2/3/3/3/NA/NA", "-44/3");
            put("Ponytail 2/3/4/1/NA/NA", "-43/3");
            put("Ponytail 2/3/4/2/NA/NA", "-42/3");
            put("Ponytail 2/3/4/3/NA/NA", "-41/3");
            put("Dreadlocks/1/1/1/NA/NA", "-40/3");
            put("Dreadlocks/1/1/2/NA/NA", "-39/3");
            put("Dreadlocks/1/2/1/NA/NA", "-38/3");
            put("Dreadlocks/1/2/2/NA/NA", "-37/3");
            put("Dreadlocks/1/3/1/NA/NA", "-36/3");
            put("Dreadlocks/1/3/2/NA/NA", "-35/3");
            put("Dreadlocks/1/4/1/NA/NA", "-34/3");
            put("Dreadlocks/1/4/2/NA/NA", "-33/3");
            put("Dreadlocks/2/1/1/NA/NA", "-32/3");
            put("Dreadlocks/2/1/2/NA/NA", "-31/3");
            put("Dreadlocks/2/2/1/NA/NA", "-30/3");
            put("Dreadlocks/2/2/2/NA/NA", "-29/3");
            put("Dreadlocks/2/3/1/NA/NA", "-28/3");
            put("Dreadlocks/2/3/2/NA/NA", "-27/3");
            put("Dreadlocks/2/4/1/NA/NA", "-26/3");
            put("Dreadlocks/2/4/2/NA/NA", "-25/3");
            put("Dreadlocks/3/1/1/NA/NA", "-24/3");
            put("Dreadlocks/3/1/2/NA/NA", "-23/3");
            put("Dreadlocks/3/2/1/NA/NA", "-22/3");
            put("Dreadlocks/3/2/2/NA/NA", "-21/3");
            put("Dreadlocks/3/3/1/NA/NA", "-20/3");
            put("Dreadlocks/3/3/2/NA/NA", "-19/3");
            put("Dreadlocks/3/4/1/NA/NA", "-18/3");
            put("Dreadlocks/3/4/2/NA/NA", "-17/3");
            put("Pulled Back/1/1/NA/NA/NA", "-16/3");
            put("Pulled Back/1/2/NA/NA/NA", "-15/3");
            put("Pulled Back/1/3/NA/NA/NA", "-14/3");
            put("Pulled Back/1/4/NA/NA/NA", "-13/3");
            put("Pulled Back/1/5/NA/NA/NA", "-12/3");
            put("Pulled Back/1/6/NA/NA/NA", "-11/3");
            put("Pulled Back/2/1/NA/NA/NA", "-10/3");
            put("Pulled Back/2/2/NA/NA/NA", "-9/3");
            put("Pulled Back/2/3/NA/NA/NA", "-8/3");
            put("Pulled Back/2/4/NA/NA/NA", "-7/3");
            put("Pulled Back/2/5/NA/NA/NA", "-6/3");
            put("Pulled Back/2/6/NA/NA/NA", "-5/3");
            put("Pulled Back/3/1/NA/NA/NA", "-4/3");
            put("Pulled Back/3/2/NA/NA/NA", "-3/3");
            put("Pulled Back/3/3/NA/NA/NA", "-2/3");
            put("Pulled Back/3/4/NA/NA/NA", "-1/3");
            put("Pulled Back/3/5/NA/NA/NA", "0/4");
            put("Pulled Back/3/6/NA/NA/NA", "1/4");
            put("Special/1/NA/NA/NA/NA", "2/4");
            put("Special/2/NA/NA/NA/NA", "3/4");
            put("Special/3/NA/NA/NA/NA", "4/4");
            put("Special/4/NA/NA/NA/NA", "5/4");
            put("Special/5/NA/NA/NA/NA", "6/4");
            put("Special/6/NA/NA/NA/NA", "7/4");
            put("Special/7/NA/NA/NA/NA", "8/4");
            put("Special/8/NA/NA/NA/NA", "9/4");
            put("Special/9/NA/NA/NA/NA", "10/4");
            put("Special/10/NA/NA/NA/NA", "11/4");
            put("Special/11/NA/NA/NA/NA", "12/4");
            put("Special/12/NA/NA/NA/NA", "13/4");
            put("Special/13/NA/NA/NA/NA", "14/4");
            put("Special/14/NA/NA/NA/NA", "15/4");
            put("Special/15/NA/NA/NA/NA", "16/4");
            put("Special/16/NA/NA/NA/NA", "17/4");
            put("Special/17/NA/NA/NA/NA", "18/4");
            put("Special/18/NA/NA/NA/NA", "19/4");
            put("Special/19/NA/NA/NA/NA", "20/4");
            put("Special/20/NA/NA/NA/NA", "21/4");
            put("Special/21/NA/NA/NA/NA", "22/4");
            put("Special/22/NA/NA/NA/NA", "23/4");
            put("Special/23/NA/NA/NA/NA", "24/4");
            put("Special/24/NA/NA/NA/NA", "25/4");
            put("Special/25/NA/NA/NA/NA", "26/4");
            put("Special/26/NA/NA/NA/NA", "27/4");
            put("Special/27/NA/NA/NA/NA", "28/4");
            put("Special/28/NA/NA/NA/NA", "29/4");
            put("Special/29/NA/NA/NA/NA", "30/4");
            put("Special/30/NA/NA/NA/NA", "31/4");
            put("Special/31/NA/NA/NA/NA", "32/4");
            put("Special/32/NA/NA/NA/NA", "33/4");
            put("Special/33/NA/NA/NA/NA", "34/4");
            put("Special/34/NA/NA/NA/NA", "35/4");
            put("Special/35/NA/NA/NA/NA", "36/4");
            put("Special/36/NA/NA/NA/NA", "37/4");
            put("Special/37/NA/NA/NA/NA", "38/4");
            put("Special/38/NA/NA/NA/NA", "39/4");
            put("Special/39/NA/NA/NA/NA", "40/4");
            put("Special/40/NA/NA/NA/NA", "41/4");
            put("Special/41/NA/NA/NA/NA", "42/4");
            put("Special/42/NA/NA/NA/NA", "43/4");
            put("Special/43/NA/NA/NA/NA", "44/4");
            put("Special/44/NA/NA/NA/NA", "45/4");
            put("Special/45/NA/NA/NA/NA", "46/4");
            put("Special/46/NA/NA/NA/NA", "47/4");
            put("Special/47/NA/NA/NA/NA", "48/4");
            put("Special/48/NA/NA/NA/NA", "49/4");
            put("Special/49/NA/NA/NA/NA", "50/4");
            put("Special/50/NA/NA/NA/NA", "51/4");
            put("Special/51/NA/NA/NA/NA", "52/4");
            put("Special/52/NA/NA/NA/NA", "53/4");
            put("Special/53/NA/NA/NA/NA", "54/4");
            put("Special/54/NA/NA/NA/NA", "55/4");
            put("Special/55/NA/NA/NA/NA", "56/4");
            put("Special/56/NA/NA/NA/NA", "57/4");
            put("Special/57/NA/NA/NA/NA", "58/4");
            put("Special/58/NA/NA/NA/NA", "59/4");
            put("Special/59/NA/NA/NA/NA", "60/4");
            put("Special/60/NA/NA/NA/NA", "61/4");
            put("Special/61/NA/NA/NA/NA", "62/4");
            put("Special/62/NA/NA/NA/NA", "63/4");
            put("Special/63/NA/NA/NA/NA", "64/4");
            put("Special/64/NA/NA/NA/NA", "65/4");
            put("Special/65/NA/NA/NA/NA", "66/4");
            put("Special/66/NA/NA/NA/NA", "67/4");
            put("Special/67/NA/NA/NA/NA", "68/4");
            put("Special/68/NA/NA/NA/NA", "69/4");
            put("Special/69/NA/NA/NA/NA", "70/4");
            put("Special/70/NA/NA/NA/NA", "71/4");
            put("Special/71/NA/NA/NA/NA", "72/4");
            put("Special/72/NA/NA/NA/NA", "73/4");
            put("Special/73/NA/NA/NA/NA", "74/4");
            put("Special/74/NA/NA/NA/NA", "75/4");
            put("Special/75/NA/NA/NA/NA", "76/4");
            put("Special/76/NA/NA/NA/NA", "77/4");
            put("Special/77/NA/NA/NA/NA", "78/4");
            put("Special/78/NA/NA/NA/NA", "79/4");
            put("Special/79/NA/NA/NA/NA", "80/4");
            put("Special/80/NA/NA/NA/NA", "81/4");
            put("Special/81/NA/NA/NA/NA", "82/4");
            put("Special/82/NA/NA/NA/NA", "83/4");
            put("Special/83/NA/NA/NA/NA", "84/4");
            put("Special/84/NA/NA/NA/NA", "85/4");
            put("Special/85/NA/NA/NA/NA", "86/4");
            put("Special/86/NA/NA/NA/NA", "87/4");
            put("Special/87/NA/NA/NA/NA", "88/4");
            put("Special/88/NA/NA/NA/NA", "89/4");
            put("Special/89/NA/NA/NA/NA", "90/4");
            put("Special/90/NA/NA/NA/NA", "91/4");
            put("Special/91/NA/NA/NA/NA", "92/4");
            put("Special/92/NA/NA/NA/NA", "93/4");
            put("Special/93/NA/NA/NA/NA", "94/4");
            put("Special/94/NA/NA/NA/NA", "95/4");
            put("Special/95/NA/NA/NA/NA", "96/4");
            put("Special/96/NA/NA/NA/NA", "97/4");
            put("Special/97/NA/NA/NA/NA", "98/4");
            put("Special/98/NA/NA/NA/NA", "99/4");
            put("Special/99/NA/NA/NA/NA", "100/4");
            put("Special/100/NA/NA/NA/NA", "101/4");
            put("Special/101/NA/NA/NA/NA", "102/4");
            put("Special/102/NA/NA/NA/NA", "103/4");
            put("Special/103/NA/NA/NA/NA", "104/4");
            put("Special/104/NA/NA/NA/NA", "105/4");
            put("Special/105/NA/NA/NA/NA", "106/4");
            put("Special/106/NA/NA/NA/NA", "107/4");
            put("Special/107/NA/NA/NA/NA", "108/4");
            put("Special/108/NA/NA/NA/NA", "109/4");
            put("Special/109/NA/NA/NA/NA", "110/4");
            put("Special/110/NA/NA/NA/NA", "111/4");
            put("Special/111/NA/NA/NA/NA", "112/4");
            put("Special/112/NA/NA/NA/NA", "113/4");
            put("Special/113/NA/NA/NA/NA", "114/4");
            put("Special/114/NA/NA/NA/NA", "115/4");
            put("Special/115/NA/NA/NA/NA", "116/4");
            put("Special/116/NA/NA/NA/NA", "117/4");
            put("Special/117/NA/NA/NA/NA", "118/4");
            put("Special/118/NA/NA/NA/NA", "119/4");
            put("Special/119/NA/NA/NA/NA", "120/4");
            put("Special/120/NA/NA/NA/NA", "121/4");
            put("Special/121/NA/NA/NA/NA", "122/4");
            put("Special/122/NA/NA/NA/NA", "123/4");
            put("Special/123/NA/NA/NA/NA", "124/4");
            put("Special/124/NA/NA/NA/NA", "125/4");
            put("Special/125/NA/NA/NA/NA", "126/4");
            put("Special/126/NA/NA/NA/NA", "127/4");
            put("Special/127/NA/NA/NA/NA", "-128/4");
            put("Special/128/NA/NA/NA/NA", "-127/4");
            put("Special/129/NA/NA/NA/NA", "-126/4");
            put("Special/130/NA/NA/NA/NA", "-125/4");
            put("Special/131/NA/NA/NA/NA", "-124/4");
            put("Special/132/NA/NA/NA/NA", "-123/4");
            put("Special/133/NA/NA/NA/NA", "-122/4");
            put("Special/134/NA/NA/NA/NA", "-121/4");
            put("Special/135/NA/NA/NA/NA", "-120/4");
            put("Special/136/NA/NA/NA/NA", "-119/4");
            put("Special/137/NA/NA/NA/NA", "-118/4");
            put("Special/138/NA/NA/NA/NA", "-117/4");
            put("Special/139/NA/NA/NA/NA", "-116/4");
            put("Special/140/NA/NA/NA/NA", "-115/4");
            put("Special/141/NA/NA/NA/NA", "-114/4");
            put("Special/142/NA/NA/NA/NA", "-113/4");
            put("Special/143/NA/NA/NA/NA", "-112/4");
            put("Special/144/NA/NA/NA/NA", "-111/4");
            put("Special/145/NA/NA/NA/NA", "-110/4");
            put("Special/146/NA/NA/NA/NA", "-109/4");
            put("Special/147/NA/NA/NA/NA", "-108/4");
            put("Special/148/NA/NA/NA/NA", "-107/4");
            put("Special/149/NA/NA/NA/NA", "-106/4");
            put("Special/150/NA/NA/NA/NA", "-105/4");
            put("Special/151/NA/NA/NA/NA", "-104/4");
            put("Special/152/NA/NA/NA/NA", "-103/4");
            put("Special/153/NA/NA/NA/NA", "-102/4");
            put("Special/154/NA/NA/NA/NA", "-101/4");
            put("Special/155/NA/NA/NA/NA", "-100/4");
            put("Special/156/NA/NA/NA/NA", "-99/4");
            put("Special/157/NA/NA/NA/NA", "-98/4");
            put("Special/158/NA/NA/NA/NA", "-97/4");
            put("Special/159/NA/NA/NA/NA", "-96/4");
            put("Special/160/NA/NA/NA/NA", "-95/4");
            put("Special/161/NA/NA/NA/NA", "-94/4");
            put("Special/162/NA/NA/NA/NA", "-93/4");
            put("Special/163/NA/NA/NA/NA", "-92/4");
            put("Special/164/NA/NA/NA/NA", "-91/4");
            put("Special/165/NA/NA/NA/NA", "-90/4");
            put("Special/166/NA/NA/NA/NA", "-89/4");
            put("Special/167/NA/NA/NA/NA", "-88/4");
            put("Special/168/NA/NA/NA/NA", "-87/4");
            put("Special/169/NA/NA/NA/NA", "-86/4");
            put("Special/170/NA/NA/NA/NA", "-85/4");
            put("Special/171/NA/NA/NA/NA", "-84/4");
            put("Special/172/NA/NA/NA/NA", "-83/4");
            put("Special/173/NA/NA/NA/NA", "-82/4");
            put("Special/174/NA/NA/NA/NA", "-81/4");
            put("Special/175/NA/NA/NA/NA", "-80/4");
            put("Special/176/NA/NA/NA/NA", "-79/4");
            put("Special/177/NA/NA/NA/NA", "-78/4");
            put("Special/178/NA/NA/NA/NA", "-77/4");
            put("Special/179/NA/NA/NA/NA", "-76/4");
            put("Special/180/NA/NA/NA/NA", "-75/4");
            put("Special/181/NA/NA/NA/NA", "-74/4");
            put("Special/182/NA/NA/NA/NA", "-73/4");
            put("Special/183/NA/NA/NA/NA", "-72/4");
            put("Special/184/NA/NA/NA/NA", "-71/4");
            put("Special/185/NA/NA/NA/NA", "-70/4");
            put("Special/186/NA/NA/NA/NA", "-69/4");
            put("Special/187/NA/NA/NA/NA", "-68/4");
            put("Special/188/NA/NA/NA/NA", "-67/4");
            put("Special/189/NA/NA/NA/NA", "-66/4");
            put("Special/190/NA/NA/NA/NA", "-65/4");
            put("Special/191/NA/NA/NA/NA", "-64/4");
            put("Special/192/NA/NA/NA/NA", "-63/4");
            put("Special/193/NA/NA/NA/NA", "-62/4");
            put("Special/194/NA/NA/NA/NA", "-61/4");
            put("Special/195/NA/NA/NA/NA", "-60/4");
            put("Special/196/NA/NA/NA/NA", "-59/4");
            put("Special/197/NA/NA/NA/NA", "-58/4");
            put("Special/198/NA/NA/NA/NA", "-57/4");
            put("Special/199/NA/NA/NA/NA", "-56/4");
            put("Special/200/NA/NA/NA/NA", "-55/4");
            put("Special/201/NA/NA/NA/NA", "-54/4");
            put("Special/202/NA/NA/NA/NA", "-53/4");
            put("Special/203/NA/NA/NA/NA", "-52/4");
            put("Special/204/NA/NA/NA/NA", "-51/4");
            put("Special/205/NA/NA/NA/NA", "-50/4");
            put("Special/206/NA/NA/NA/NA", "-49/4");
            put("Special/207/NA/NA/NA/NA", "-48/4");
            put("Special/208/NA/NA/NA/NA", "-47/4");
            put("Special/209/NA/NA/NA/NA", "-46/4");
            put("Special/210/NA/NA/NA/NA", "-45/4");
            put("Special/211/NA/NA/NA/NA", "-44/4");
            put("Special/212/NA/NA/NA/NA", "-43/4");
            put("Special/213/NA/NA/NA/NA", "-42/4");
            put("Special/214/NA/NA/NA/NA", "-41/4");
            put("Special/215/NA/NA/NA/NA", "-40/4");
            put("Special/216/NA/NA/NA/NA", "-39/4");
            put("Special/217/NA/NA/NA/NA", "-38/4");
            put("Special/218/NA/NA/NA/NA", "-37/4");
            put("Special/219/NA/NA/NA/NA", "-36/4");
            put("Special/220/NA/NA/NA/NA", "-35/4");
            put("Special/221/NA/NA/NA/NA", "-34/4");
            put("Special/222/NA/NA/NA/NA", "-33/4");
            put("Special/223/NA/NA/NA/NA", "-32/4");
            put("Special/224/NA/NA/NA/NA", "-31/4");
            put("Special/225/NA/NA/NA/NA", "-30/4");
            put("Special/226/NA/NA/NA/NA", "-29/4");
            put("Special/227/NA/NA/NA/NA", "-28/4");
            put("Special/228/NA/NA/NA/NA", "-27/4");
            put("Special/229/NA/NA/NA/NA", "-26/4");
            put("Special/230/NA/NA/NA/NA", "-25/4");
            put("Special/231/NA/NA/NA/NA", "-24/4");
            put("Special/232/NA/NA/NA/NA", "-23/4");
            put("Special/233/NA/NA/NA/NA", "-22/4");
            put("Special/234/NA/NA/NA/NA", "-21/4");
            put("Special/235/NA/NA/NA/NA", "-20/4");
            put("Special/236/NA/NA/NA/NA", "-19/4");
            put("Special/237/NA/NA/NA/NA", "-18/4");
            put("Special/238/NA/NA/NA/NA", "-17/4");
            put("Special/239/NA/NA/NA/NA", "-16/4");
            put("Special/240/NA/NA/NA/NA", "-15/4");
            put("Special/241/NA/NA/NA/NA", "-14/4");
            put("Special/242/NA/NA/NA/NA", "-13/4");
            put("Special/243/NA/NA/NA/NA", "-12/4");
            put("Special/244/NA/NA/NA/NA", "-11/4");
            put("Special/245/NA/NA/NA/NA", "-10/4");
            put("Special/246/NA/NA/NA/NA", "-9/4");
            put("Special/247/NA/NA/NA/NA", "-8/4");
            put("Special/248/NA/NA/NA/NA", "-7/4");
            put("Special/249/NA/NA/NA/NA", "-6/4");
            put("Special/250/NA/NA/NA/NA", "-5/4");
            put("Special/251/NA/NA/NA/NA", "-4/4");
            put("Special/252/NA/NA/NA/NA", "-3/4");
            put("Special/253/NA/NA/NA/NA", "-2/4");
            put("Special/254/NA/NA/NA/NA", "-1/4");
            put("Special/255/NA/NA/NA/NA", "0/5");
            put("Special/256/NA/NA/NA/NA", "1/5");
            put("Special/257/NA/NA/NA/NA", "2/5");
            put("Special/258/NA/NA/NA/NA", "3/5");
            put("Special/259/NA/NA/NA/NA", "4/5");
            put("Special/260/NA/NA/NA/NA", "5/5");
            put("Special/261/NA/NA/NA/NA", "6/5");
            put("Special/262/NA/NA/NA/NA", "7/5");
            put("Special/263/NA/NA/NA/NA", "8/5");
            put("Special/264/NA/NA/NA/NA", "9/5");
            put("Special/265/NA/NA/NA/NA", "10/5");
            put("Special/266/NA/NA/NA/NA", "11/5");
            put("Special/267/NA/NA/NA/NA", "12/5");
            put("Special/268/NA/NA/NA/NA", "13/5");
            put("Special/269/NA/NA/NA/NA", "14/5");
            put("Special/270/NA/NA/NA/NA", "15/5");
            put("Special/271/NA/NA/NA/NA", "16/5");
            put("Special/272/NA/NA/NA/NA", "17/5");
            put("Special/273/NA/NA/NA/NA", "18/5");
            put("Special/274/NA/NA/NA/NA", "19/5");
            put("Special/275/NA/NA/NA/NA", "20/5");
            put("Special/276/NA/NA/NA/NA", "21/5");
            put("Special/277/NA/NA/NA/NA", "22/5");
            put("Special/278/NA/NA/NA/NA", "23/5");
            put("Special/279/NA/NA/NA/NA", "24/5");
            put("Special/280/NA/NA/NA/NA", "25/5");
            put("Special/281/NA/NA/NA/NA", "26/5");
            put("Special/282/NA/NA/NA/NA", "27/5");
            put("Special/283/NA/NA/NA/NA", "28/5");
            put("Special/284/NA/NA/NA/NA", "29/5");
            put("Special/285/NA/NA/NA/NA", "30/5");
            put("Special/286/NA/NA/NA/NA", "31/5");
            put("Special/287/NA/NA/NA/NA", "32/5");
            put("Special/288/NA/NA/NA/NA", "33/5");
            put("Special/289/NA/NA/NA/NA", "34/5");
            put("Special/290/NA/NA/NA/NA", "35/5");
            put("Special/291/NA/NA/NA/NA", "36/5");
            put("Special/292/NA/NA/NA/NA", "37/5");
            put("Special/293/NA/NA/NA/NA", "38/5");
            put("Special/294/NA/NA/NA/NA", "39/5");
            put("Special/295/NA/NA/NA/NA", "40/5");
            put("Special/296/NA/NA/NA/NA", "41/5");
            put("Special/297/NA/NA/NA/NA", "42/5");
            put("Special/298/NA/NA/NA/NA", "43/5");
            put("Special/299/NA/NA/NA/NA", "44/5");
            put("Special/300/NA/NA/NA/NA", "45/5");
            put("Special/301/NA/NA/NA/NA", "46/5");
            put("Special/302/NA/NA/NA/NA", "47/5");
            put("Special/303/NA/NA/NA/NA", "48/5");
            put("Special/304/NA/NA/NA/NA", "49/5");
            put("Special/305/NA/NA/NA/NA", "50/5");
            put("Special/306/NA/NA/NA/NA", "51/5");
            put("Special/307/NA/NA/NA/NA", "52/5");
            put("Special/308/NA/NA/NA/NA", "53/5");
            put("Special/309/NA/NA/NA/NA", "54/5");
            put("Special/310/NA/NA/NA/NA", "55/5");
            put("Special/311/NA/NA/NA/NA", "56/5");
            put("Special/312/NA/NA/NA/NA", "57/5");
            put("Special/313/NA/NA/NA/NA", "58/5");
            put("Special/314/NA/NA/NA/NA", "59/5");
            put("Special/315/NA/NA/NA/NA", "60/5");
            put("Special/316/NA/NA/NA/NA", "61/5");
            put("Special/317/NA/NA/NA/NA", "62/5");
            put("Special/318/NA/NA/NA/NA", "63/5");
            put("Special/319/NA/NA/NA/NA", "64/5");
            put("Special/320/NA/NA/NA/NA", "65/5");
            put("Special/321/NA/NA/NA/NA", "66/5");
            put("Special/322/NA/NA/NA/NA", "67/5");
            put("Special/323/NA/NA/NA/NA", "68/5");
            put("Special/324/NA/NA/NA/NA", "69/5");
            put("Special/325/NA/NA/NA/NA", "70/5");
            put("Special/326/NA/NA/NA/NA", "71/5");
            put("Special/327/NA/NA/NA/NA", "72/5");
            put("Special/328/NA/NA/NA/NA", "73/5");
            put("Special/329/NA/NA/NA/NA", "74/5");
            put("Special/330/NA/NA/NA/NA", "75/5");
            put("Special/331/NA/NA/NA/NA", "76/5");
            put("Special/332/NA/NA/NA/NA", "77/5");
            put("Special/333/NA/NA/NA/NA", "78/5");
            put("Special/334/NA/NA/NA/NA", "79/5");
            put("Special/335/NA/NA/NA/NA", "80/5");
            put("Special/336/NA/NA/NA/NA", "81/5");
            put("Special/337/NA/NA/NA/NA", "82/5");
            put("Special/338/NA/NA/NA/NA", "83/5");
            put("Special/339/NA/NA/NA/NA", "84/5");
            put("Special/340/NA/NA/NA/NA", "85/5");
            put("Special/341/NA/NA/NA/NA", "86/5");
            put("Special/342/NA/NA/NA/NA", "87/5");
            put("Special/343/NA/NA/NA/NA", "88/5");
            put("Special/344/NA/NA/NA/NA", "89/5");
            put("Special/345/NA/NA/NA/NA", "90/5");
            put("Special/346/NA/NA/NA/NA", "91/5");
            put("Special/347/NA/NA/NA/NA", "92/5");
            put("Special/348/NA/NA/NA/NA", "93/5");
            put("Special/349/NA/NA/NA/NA", "94/5");
            put("Special/350/NA/NA/NA/NA", "95/5");
            put("Special/351/NA/NA/NA/NA", "96/5");
            put("Special/352/NA/NA/NA/NA", "97/5");
            put("Special/353/NA/NA/NA/NA", "98/5");
            put("Special/354/NA/NA/NA/NA", "99/5");
            put("Special/355/NA/NA/NA/NA", "100/5");
            put("Special/356/NA/NA/NA/NA", "101/5");
            put("Special/357/NA/NA/NA/NA", "102/5");
            put("Special/358/NA/NA/NA/NA", "103/5");
            put("Special/359/NA/NA/NA/NA", "104/5");
            put("Special/360/NA/NA/NA/NA", "105/5");
            put("Special/361/NA/NA/NA/NA", "106/5");
            put("Special/362/NA/NA/NA/NA", "107/5");
            put("Special/363/NA/NA/NA/NA", "108/5");
            put("Special/364/NA/NA/NA/NA", "109/5");
            put("Special/365/NA/NA/NA/NA", "110/5");
            put("Special/366/NA/NA/NA/NA", "111/5");
            put("Special/367/NA/NA/NA/NA", "112/5");
            put("Special/368/NA/NA/NA/NA", "113/5");
            put("Special/369/NA/NA/NA/NA", "114/5");
            put("Special/370/NA/NA/NA/NA", "115/5");
            put("Special/371/NA/NA/NA/NA", "116/5");
            put("Special/372/NA/NA/NA/NA", "117/5");
            put("Special/373/NA/NA/NA/NA", "118/5");
            put("Special/374/NA/NA/NA/NA", "119/5");
            put("Special/375/NA/NA/NA/NA", "120/5");
            put("Special/376/NA/NA/NA/NA", "121/5");
            put("Special/377/NA/NA/NA/NA", "122/5");
            put("Special/378/NA/NA/NA/NA", "123/5");
            put("Special/379/NA/NA/NA/NA", "124/5");
            put("Special/380/NA/NA/NA/NA", "125/5");
            put("Special/381/NA/NA/NA/NA", "126/5");
            put("Special/382/NA/NA/NA/NA", "127/5");
            put("Special/383/NA/NA/NA/NA", "-128/5");
            put("Special/384/NA/NA/NA/NA", "-127/5");
            put("Special/385/NA/NA/NA/NA", "-126/5");
            put("Special/386/NA/NA/NA/NA", "-125/5");
            put("Special/387/NA/NA/NA/NA", "-124/5");
            put("Special/388/NA/NA/NA/NA", "-123/5");
            put("Special/389/NA/NA/NA/NA", "-122/5");
            put("Special/390/NA/NA/NA/NA", "-121/5");
            put("Special/391/NA/NA/NA/NA", "-120/5");
            put("Special/392/NA/NA/NA/NA", "-119/5");
            put("Special/393/NA/NA/NA/NA", "-118/5");
            put("Special/394/NA/NA/NA/NA", "-117/5");
            put("Special/395/NA/NA/NA/NA", "-116/5");
            put("Special/396/NA/NA/NA/NA", "-115/5");
            put("Special/397/NA/NA/NA/NA", "-114/5");
            put("Special/398/NA/NA/NA/NA", "-113/5");
            put("Special/399/NA/NA/NA/NA", "-112/5");
            put("Special/400/NA/NA/NA/NA", "-111/5");
            put("Special/401/NA/NA/NA/NA", "-110/5");
            put("Special/402/NA/NA/NA/NA", "-109/5");
            put("Special/403/NA/NA/NA/NA", "-108/5");
            put("Special/404/NA/NA/NA/NA", "-107/5");
            put("Special/405/NA/NA/NA/NA", "-106/5");
            put("Special/406/NA/NA/NA/NA", "-105/5");
            put("Special/407/NA/NA/NA/NA", "-104/5");
            put("Special/408/NA/NA/NA/NA", "-103/5");
            put("Special/409/NA/NA/NA/NA", "-102/5");
            put("Special/410/NA/NA/NA/NA", "-101/5");
            put("Special/411/NA/NA/NA/NA", "-100/5");
            put("Special/412/NA/NA/NA/NA", "-99/5");
            put("Special/413/NA/NA/NA/NA", "-98/5");
            put("Special/414/NA/NA/NA/NA", "-97/5");
            put("Special/415/NA/NA/NA/NA", "-96/5");
            put("Special/416/NA/NA/NA/NA", "-95/5");
            put("Special/417/NA/NA/NA/NA", "-94/5");
            put("Special/418/NA/NA/NA/NA", "-93/5");
            put("Special/419/NA/NA/NA/NA", "-92/5");
            put("Special/420/NA/NA/NA/NA", "-91/5");
            put("Special/421/NA/NA/NA/NA", "-90/5");
            put("Special/422/NA/NA/NA/NA", "-89/5");
            put("Special/423/NA/NA/NA/NA", "-88/5");
            put("Special/424/NA/NA/NA/NA", "-87/5");
            put("Special/425/NA/NA/NA/NA", "-86/5");
            put("Special/426/NA/NA/NA/NA", "-85/5");
            put("Special/427/NA/NA/NA/NA", "-84/5");
            put("Special/428/NA/NA/NA/NA", "-83/5");
            put("Special/429/NA/NA/NA/NA", "-82/5");
            put("Special/430/NA/NA/NA/NA", "-81/5");
            put("Special/431/NA/NA/NA/NA", "-80/5");
            put("Special/432/NA/NA/NA/NA", "-79/5");
            put("Special/433/NA/NA/NA/NA", "-78/5");
            put("Special/434/NA/NA/NA/NA", "-77/5");
            put("Special/435/NA/NA/NA/NA", "-76/5");
            put("Special/436/NA/NA/NA/NA", "-75/5");
            put("Special/437/NA/NA/NA/NA", "-74/5");
            put("Special/438/NA/NA/NA/NA", "-73/5");
            put("Special/439/NA/NA/NA/NA", "-72/5");
            put("Special/440/NA/NA/NA/NA", "-71/5");
            put("Special/441/NA/NA/NA/NA", "-70/5");
            put("Special/442/NA/NA/NA/NA", "-69/5");
            put("Special/443/NA/NA/NA/NA", "-68/5");
            put("Special/444/NA/NA/NA/NA", "-67/5");
            put("Special/445/NA/NA/NA/NA", "-66/5");
            put("Special/446/NA/NA/NA/NA", "-65/5");
            put("Special/447/NA/NA/NA/NA", "-64/5");
            put("Special/448/NA/NA/NA/NA", "-63/5");
            put("Special/449/NA/NA/NA/NA", "-62/5");
            put("Special/450/NA/NA/NA/NA", "-61/5");
            put("Special/451/NA/NA/NA/NA", "-60/5");
            put("Special/452/NA/NA/NA/NA", "-59/5");
            put("Special/453/NA/NA/NA/NA", "-58/5");
            put("Special/454/NA/NA/NA/NA", "-57/5");
            put("Special/455/NA/NA/NA/NA", "-56/5");
            put("Special/456/NA/NA/NA/NA", "-55/5");
            put("Special/457/NA/NA/NA/NA", "-54/5");
            put("Special/458/NA/NA/NA/NA", "-53/5");
            put("Special/459/NA/NA/NA/NA", "-52/5");
            put("Special/460/NA/NA/NA/NA", "-51/5");
            put("Special/461/NA/NA/NA/NA", "-50/5");
            put("Special/462/NA/NA/NA/NA", "-49/5");
            put("Special/463/NA/NA/NA/NA", "-48/5");
            put("Special/464/NA/NA/NA/NA", "-47/5");
            put("Special/465/NA/NA/NA/NA", "-46/5");
            put("Special/466/NA/NA/NA/NA", "-45/5");
            put("Special/467/NA/NA/NA/NA", "-44/5");
            put("Special/468/NA/NA/NA/NA", "-43/5");
            put("Special/469/NA/NA/NA/NA", "-42/5");
            put("Special/470/NA/NA/NA/NA", "-41/5");
            put("Special/471/NA/NA/NA/NA", "-40/5");
            put("Special/472/NA/NA/NA/NA", "-39/5");
            put("Special/473/NA/NA/NA/NA", "-38/5");
        }
    };

    private static final Map<String, String> hairTypesByKey = new HashMap<String, String>() {
        {
            put("0/0", "Bald/1/NA/NA/NA/NA");
            put("1/0", "Bald/2/NA/NA/NA/NA");
            put("2/0", "Bald/3/NA/NA/NA/NA");
            put("3/0", "Bald/4/NA/NA/NA/NA");
            put("4/0", "Buzz Cut/1/1/NA/1/NA");
            put("5/0", "Buzz Cut/1/1/NA/2/NA");
            put("6/0", "Buzz Cut/1/1/NA/3/NA");
            put("7/0", "Buzz Cut/1/1/NA/4/NA");
            put("8/0", "Buzz Cut/1/2/NA/1/NA");
            put("9/0", "Buzz Cut/1/2/NA/2/NA");
            put("10/0", "Buzz Cut/1/2/NA/3/NA");
            put("11/0", "Buzz Cut/1/2/NA/4/NA");
            put("12/0", "Buzz Cut/1/3/NA/1/NA");
            put("13/0", "Buzz Cut/1/3/NA/2/NA");
            put("14/0", "Buzz Cut/1/3/NA/3/NA");
            put("15/0", "Buzz Cut/1/3/NA/4/NA");
            put("16/0", "Buzz Cut/1/4/NA/1/NA");
            put("17/0", "Buzz Cut/1/4/NA/2/NA");
            put("18/0", "Buzz Cut/1/4/NA/3/NA");
            put("19/0", "Buzz Cut/1/4/NA/4/NA");
            put("20/0", "Buzz Cut/1/5/NA/1/NA");
            put("21/0", "Buzz Cut/1/5/NA/2/NA");
            put("22/0", "Buzz Cut/1/5/NA/3/NA");
            put("23/0", "Buzz Cut/1/5/NA/4/NA");
            put("24/0", "Buzz Cut/2/1/NA/1/NA");
            put("25/0", "Buzz Cut/2/1/NA/2/NA");
            put("26/0", "Buzz Cut/2/1/NA/3/NA");
            put("27/0", "Buzz Cut/2/1/NA/4/NA");
            put("28/0", "Buzz Cut/2/2/NA/1/NA");
            put("29/0", "Buzz Cut/2/2/NA/2/NA");
            put("30/0", "Buzz Cut/2/2/NA/3/NA");
            put("31/0", "Buzz Cut/2/2/NA/4/NA");
            put("32/0", "Buzz Cut/2/3/NA/1/NA");
            put("33/0", "Buzz Cut/2/3/NA/2/NA");
            put("34/0", "Buzz Cut/2/3/NA/3/NA");
            put("35/0", "Buzz Cut/2/3/NA/4/NA");
            put("36/0", "Buzz Cut/2/4/NA/1/NA");
            put("37/0", "Buzz Cut/2/4/NA/2/NA");
            put("38/0", "Buzz Cut/2/4/NA/3/NA");
            put("39/0", "Buzz Cut/2/4/NA/4/NA");
            put("40/0", "Buzz Cut/2/5/NA/1/NA");
            put("41/0", "Buzz Cut/2/5/NA/2/NA");
            put("42/0", "Buzz Cut/2/5/NA/3/NA");
            put("43/0", "Buzz Cut/2/5/NA/4/NA");
            put("44/0", "Buzz Cut/3/1/NA/1/NA");
            put("45/0", "Buzz Cut/3/1/NA/2/NA");
            put("46/0", "Buzz Cut/3/1/NA/3/NA");
            put("47/0", "Buzz Cut/3/1/NA/4/NA");
            put("48/0", "Buzz Cut/3/2/NA/1/NA");
            put("49/0", "Buzz Cut/3/2/NA/2/NA");
            put("50/0", "Buzz Cut/3/2/NA/3/NA");
            put("51/0", "Buzz Cut/3/2/NA/4/NA");
            put("52/0", "Buzz Cut/3/3/NA/1/NA");
            put("53/0", "Buzz Cut/3/3/NA/2/NA");
            put("54/0", "Buzz Cut/3/3/NA/3/NA");
            put("55/0", "Buzz Cut/3/3/NA/4/NA");
            put("56/0", "Buzz Cut/3/4/NA/1/NA");
            put("57/0", "Buzz Cut/3/4/NA/2/NA");
            put("58/0", "Buzz Cut/3/4/NA/3/NA");
            put("59/0", "Buzz Cut/3/4/NA/4/NA");
            put("60/0", "Buzz Cut/3/5/NA/1/NA");
            put("61/0", "Buzz Cut/3/5/NA/2/NA");
            put("62/0", "Buzz Cut/3/5/NA/3/NA");
            put("63/0", "Buzz Cut/3/5/NA/4/NA");
            put("64/0", "Buzz Cut/4/1/NA/1/NA");
            put("65/0", "Buzz Cut/4/1/NA/2/NA");
            put("66/0", "Buzz Cut/4/1/NA/3/NA");
            put("67/0", "Buzz Cut/4/1/NA/4/NA");
            put("68/0", "Buzz Cut/4/2/NA/1/NA");
            put("69/0", "Buzz Cut/4/2/NA/2/NA");
            put("70/0", "Buzz Cut/4/2/NA/3/NA");
            put("71/0", "Buzz Cut/4/2/NA/4/NA");
            put("72/0", "Buzz Cut/4/3/NA/1/NA");
            put("73/0", "Buzz Cut/4/3/NA/2/NA");
            put("74/0", "Buzz Cut/4/3/NA/3/NA");
            put("75/0", "Buzz Cut/4/3/NA/4/NA");
            put("76/0", "Buzz Cut/4/4/NA/1/NA");
            put("77/0", "Buzz Cut/4/4/NA/2/NA");
            put("78/0", "Buzz Cut/4/4/NA/3/NA");
            put("79/0", "Buzz Cut/4/4/NA/4/NA");
            put("80/0", "Buzz Cut/4/5/NA/1/NA");
            put("81/0", "Buzz Cut/4/5/NA/2/NA");
            put("82/0", "Buzz Cut/4/5/NA/3/NA");
            put("83/0", "Buzz Cut/4/5/NA/4/NA");
            put("84/0", "Very Short 1/1/1/NA/NA/NA");
            put("85/0", "Very Short 1/1/2/NA/NA/NA");
            put("86/0", "Very Short 1/1/3/NA/NA/NA");
            put("87/0", "Very Short 1/1/4/NA/NA/NA");
            put("88/0", "Very Short 1/1/5/NA/NA/NA");
            put("89/0", "Very Short 1/1/6/NA/NA/NA");
            put("90/0", "Very Short 1/2/1/NA/NA/NA");
            put("91/0", "Very Short 1/2/2/NA/NA/NA");
            put("92/0", "Very Short 1/2/3/NA/NA/NA");
            put("93/0", "Very Short 1/2/4/NA/NA/NA");
            put("94/0", "Very Short 1/2/5/NA/NA/NA");
            put("95/0", "Very Short 1/2/6/NA/NA/NA");
            put("96/0", "Very Short 1/3/1/NA/NA/NA");
            put("97/0", "Very Short 1/3/2/NA/NA/NA");
            put("98/0", "Very Short 1/3/3/NA/NA/NA");
            put("99/0", "Very Short 1/3/4/NA/NA/NA");
            put("100/0", "Very Short 1/3/5/NA/NA/NA");
            put("101/0", "Very Short 1/3/6/NA/NA/NA");
            put("102/0", "Very Short 1/4/1/NA/NA/NA");
            put("103/0", "Very Short 1/4/2/NA/NA/NA");
            put("104/0", "Very Short 1/4/3/NA/NA/NA");
            put("105/0", "Very Short 1/4/4/NA/NA/NA");
            put("106/0", "Very Short 1/4/5/NA/NA/NA");
            put("107/0", "Very Short 1/4/6/NA/NA/NA");
            put("108/0", "Very Short 2/1/1/NA/NA/NA");
            put("109/0", "Very Short 2/1/2/NA/NA/NA");
            put("110/0", "Very Short 2/1/3/NA/NA/NA");
            put("111/0", "Very Short 2/1/4/NA/NA/NA");
            put("112/0", "Very Short 2/1/5/NA/NA/NA");
            put("113/0", "Very Short 2/1/6/NA/NA/NA");
            put("114/0", "Very Short 2/1/7/NA/NA/NA");
            put("115/0", "Very Short 2/1/8/NA/NA/NA");
            put("116/0", "Very Short 2/1/9/NA/NA/NA");
            put("117/0", "Very Short 2/1/10/NA/NA/NA");
            put("118/0", "Very Short 2/2/1/NA/NA/NA");
            put("119/0", "Very Short 2/2/2/NA/NA/NA");
            put("120/0", "Very Short 2/2/3/NA/NA/NA");
            put("121/0", "Very Short 2/2/4/NA/NA/NA");
            put("122/0", "Very Short 2/2/5/NA/NA/NA");
            put("123/0", "Very Short 2/2/6/NA/NA/NA");
            put("124/0", "Very Short 2/2/7/NA/NA/NA");
            put("125/0", "Very Short 2/2/8/NA/NA/NA");
            put("126/0", "Very Short 2/2/9/NA/NA/NA");
            put("127/0", "Very Short 2/2/10/NA/NA/NA");
            put("-128/0", "Very Short 2/3/1/NA/NA/NA");
            put("-127/0", "Very Short 2/3/2/NA/NA/NA");
            put("-126/0", "Very Short 2/3/3/NA/NA/NA");
            put("-125/0", "Very Short 2/3/4/NA/NA/NA");
            put("-124/0", "Very Short 2/3/5/NA/NA/NA");
            put("-123/0", "Very Short 2/3/6/NA/NA/NA");
            put("-122/0", "Very Short 2/3/7/NA/NA/NA");
            put("-121/0", "Very Short 2/3/8/NA/NA/NA");
            put("-120/0", "Very Short 2/3/9/NA/NA/NA");
            put("-119/0", "Very Short 2/3/10/NA/NA/NA");
            put("-118/0", "Very Short 2/4/1/NA/NA/NA");
            put("-117/0", "Very Short 2/4/2/NA/NA/NA");
            put("-116/0", "Very Short 2/4/3/NA/NA/NA");
            put("-115/0", "Very Short 2/4/4/NA/NA/NA");
            put("-114/0", "Very Short 2/4/5/NA/NA/NA");
            put("-113/0", "Very Short 2/5/1/NA/NA/NA");
            put("-112/0", "Very Short 2/5/2/NA/NA/NA");
            put("-111/0", "Very Short 2/5/3/NA/NA/NA");
            put("-110/0", "Very Short 2/5/4/NA/NA/NA");
            put("-109/0", "Very Short 2/5/5/NA/NA/NA");
            put("-108/0", "Very Short 2/6/1/NA/NA/NA");
            put("-107/0", "Very Short 2/6/2/NA/NA/NA");
            put("-106/0", "Very Short 2/6/3/NA/NA/NA");
            put("-105/0", "Very Short 2/6/4/NA/NA/NA");
            put("-104/0", "Very Short 2/6/5/NA/NA/NA");
            put("-103/0", "Straight 1/1/1/1/NA/N");
            put("-102/0", "Straight 1/1/1/1/NA/1");
            put("-101/0", "Straight 1/1/1/1/NA/2");
            put("-100/0", "Straight 1/1/1/2/NA/N");
            put("-99/0", "Straight 1/1/1/2/NA/1");
            put("-98/0", "Straight 1/1/1/2/NA/2");
            put("-97/0", "Straight 1/1/1/3/NA/N");
            put("-96/0", "Straight 1/1/1/3/NA/1");
            put("-95/0", "Straight 1/1/1/3/NA/2");
            put("-94/0", "Straight 1/1/2/1/NA/N");
            put("-93/0", "Straight 1/1/2/1/NA/1");
            put("-92/0", "Straight 1/1/2/1/NA/2");
            put("-91/0", "Straight 1/1/2/2/NA/N");
            put("-90/0", "Straight 1/1/2/2/NA/1");
            put("-89/0", "Straight 1/1/2/2/NA/2");
            put("-88/0", "Straight 1/1/2/3/NA/N");
            put("-87/0", "Straight 1/1/2/3/NA/1");
            put("-86/0", "Straight 1/1/2/3/NA/2");
            put("-85/0", "Straight 1/1/3/1/NA/N");
            put("-84/0", "Straight 1/1/3/1/NA/1");
            put("-83/0", "Straight 1/1/3/1/NA/2");
            put("-82/0", "Straight 1/1/3/2/NA/N");
            put("-81/0", "Straight 1/1/3/2/NA/1");
            put("-80/0", "Straight 1/1/3/2/NA/2");
            put("-79/0", "Straight 1/1/3/3/NA/N");
            put("-78/0", "Straight 1/1/3/3/NA/1");
            put("-77/0", "Straight 1/1/3/3/NA/2");
            put("-76/0", "Straight 1/1/4/1/NA/N");
            put("-75/0", "Straight 1/1/4/1/NA/1");
            put("-74/0", "Straight 1/1/4/1/NA/2");
            put("-73/0", "Straight 1/1/4/2/NA/N");
            put("-72/0", "Straight 1/1/4/2/NA/1");
            put("-71/0", "Straight 1/1/4/2/NA/2");
            put("-70/0", "Straight 1/1/4/3/NA/N");
            put("-69/0", "Straight 1/1/4/3/NA/1");
            put("-68/0", "Straight 1/1/4/3/NA/2");
            put("-67/0", "Straight 1/1/5/1/NA/N");
            put("-66/0", "Straight 1/1/5/1/NA/1");
            put("-65/0", "Straight 1/1/5/1/NA/2");
            put("-64/0", "Straight 1/1/5/2/NA/N");
            put("-63/0", "Straight 1/1/5/2/NA/1");
            put("-62/0", "Straight 1/1/5/2/NA/2");
            put("-61/0", "Straight 1/1/5/3/NA/N");
            put("-60/0", "Straight 1/1/5/3/NA/1");
            put("-59/0", "Straight 1/1/5/3/NA/2");
            put("-58/0", "Straight 1/1/6/1/NA/N");
            put("-57/0", "Straight 1/1/6/1/NA/1");
            put("-56/0", "Straight 1/1/6/1/NA/2");
            put("-55/0", "Straight 1/1/6/2/NA/N");
            put("-54/0", "Straight 1/1/6/2/NA/1");
            put("-53/0", "Straight 1/1/6/2/NA/2");
            put("-52/0", "Straight 1/1/6/3/NA/N");
            put("-51/0", "Straight 1/1/6/3/NA/1");
            put("-50/0", "Straight 1/1/6/3/NA/2");
            put("-49/0", "Straight 1/1/7/1/NA/N");
            put("-48/0", "Straight 1/1/7/1/NA/1");
            put("-47/0", "Straight 1/1/7/1/NA/2");
            put("-46/0", "Straight 1/1/7/2/NA/N");
            put("-45/0", "Straight 1/1/7/2/NA/1");
            put("-44/0", "Straight 1/1/7/2/NA/2");
            put("-43/0", "Straight 1/1/7/3/NA/N");
            put("-42/0", "Straight 1/1/7/3/NA/1");
            put("-41/0", "Straight 1/1/7/3/NA/2");
            put("-40/0", "Straight 1/1/8/1/NA/N");
            put("-39/0", "Straight 1/1/8/1/NA/1");
            put("-38/0", "Straight 1/1/8/1/NA/2");
            put("-37/0", "Straight 1/1/8/2/NA/N");
            put("-36/0", "Straight 1/1/8/2/NA/1");
            put("-35/0", "Straight 1/1/8/2/NA/2");
            put("-34/0", "Straight 1/1/8/3/NA/N");
            put("-33/0", "Straight 1/1/8/3/NA/1");
            put("-32/0", "Straight 1/1/8/3/NA/2");
            put("-31/0", "Straight 1/1/9/1/NA/N");
            put("-30/0", "Straight 1/1/9/1/NA/1");
            put("-29/0", "Straight 1/1/9/1/NA/2");
            put("-28/0", "Straight 1/1/9/2/NA/N");
            put("-27/0", "Straight 1/1/9/2/NA/1");
            put("-26/0", "Straight 1/1/9/2/NA/2");
            put("-25/0", "Straight 1/1/9/3/NA/N");
            put("-24/0", "Straight 1/1/9/3/NA/1");
            put("-23/0", "Straight 1/1/9/3/NA/2");
            put("-22/0", "Straight 1/1/10/1/NA/NA");
            put("-21/0", "Straight 1/1/10/2/NA/NA");
            put("-20/0", "Straight 1/1/10/3/NA/NA");
            put("-19/0", "Straight 1/1/11/1/NA/NA");
            put("-18/0", "Straight 1/1/11/2/NA/NA");
            put("-17/0", "Straight 1/1/11/3/NA/NA");
            put("-16/0", "Straight 1/1/12/1/NA/NA");
            put("-15/0", "Straight 1/1/12/2/NA/NA");
            put("-14/0", "Straight 1/1/12/3/NA/NA");
            put("-13/0", "Straight 1/1/13/1/NA/NA");
            put("-12/0", "Straight 1/1/13/2/NA/NA");
            put("-11/0", "Straight 1/1/13/3/NA/NA");
            put("-10/0", "Straight 1/1/14/1/NA/NA");
            put("-9/0", "Straight 1/1/14/2/NA/NA");
            put("-8/0", "Straight 1/1/14/3/NA/NA");
            put("-7/0", "Straight 1/1/15/1/NA/NA");
            put("-6/0", "Straight 1/1/15/2/NA/NA");
            put("-5/0", "Straight 1/1/15/3/NA/NA");
            put("-4/0", "Straight 1/1/16/1/NA/NA");
            put("-3/0", "Straight 1/1/16/2/NA/NA");
            put("-2/0", "Straight 1/1/16/3/NA/NA");
            put("-1/0", "Straight 1/2/1/1/NA/N");
            put("0/1", "Straight 1/2/1/1/NA/1");
            put("1/1", "Straight 1/2/1/1/NA/2");
            put("2/1", "Straight 1/2/1/2/NA/N");
            put("3/1", "Straight 1/2/1/2/NA/1");
            put("4/1", "Straight 1/2/1/2/NA/2");
            put("5/1", "Straight 1/2/1/3/NA/N");
            put("6/1", "Straight 1/2/1/3/NA/1");
            put("7/1", "Straight 1/2/1/3/NA/2");
            put("8/1", "Straight 1/2/2/1/NA/N");
            put("9/1", "Straight 1/2/2/1/NA/1");
            put("10/1", "Straight 1/2/2/1/NA/2");
            put("11/1", "Straight 1/2/2/2/NA/N");
            put("12/1", "Straight 1/2/2/2/NA/1");
            put("13/1", "Straight 1/2/2/2/NA/2");
            put("14/1", "Straight 1/2/2/3/NA/N");
            put("15/1", "Straight 1/2/2/3/NA/1");
            put("16/1", "Straight 1/2/2/3/NA/2");
            put("17/1", "Straight 1/2/3/1/NA/N");
            put("18/1", "Straight 1/2/3/1/NA/1");
            put("19/1", "Straight 1/2/3/1/NA/2");
            put("20/1", "Straight 1/2/3/2/NA/N");
            put("21/1", "Straight 1/2/3/2/NA/1");
            put("22/1", "Straight 1/2/3/2/NA/2");
            put("23/1", "Straight 1/2/3/3/NA/N");
            put("24/1", "Straight 1/2/3/3/NA/1");
            put("25/1", "Straight 1/2/3/3/NA/2");
            put("26/1", "Straight 1/2/4/1/NA/N");
            put("27/1", "Straight 1/2/4/1/NA/1");
            put("28/1", "Straight 1/2/4/1/NA/2");
            put("29/1", "Straight 1/2/4/2/NA/N");
            put("30/1", "Straight 1/2/4/2/NA/1");
            put("31/1", "Straight 1/2/4/2/NA/2");
            put("32/1", "Straight 1/2/4/3/NA/N");
            put("33/1", "Straight 1/2/4/3/NA/1");
            put("34/1", "Straight 1/2/4/3/NA/2");
            put("35/1", "Straight 1/2/5/1/NA/N");
            put("36/1", "Straight 1/2/5/1/NA/1");
            put("37/1", "Straight 1/2/5/1/NA/2");
            put("38/1", "Straight 1/2/5/2/NA/N");
            put("39/1", "Straight 1/2/5/2/NA/1");
            put("40/1", "Straight 1/2/5/2/NA/2");
            put("41/1", "Straight 1/2/5/3/NA/N");
            put("42/1", "Straight 1/2/5/3/NA/1");
            put("43/1", "Straight 1/2/5/3/NA/2");
            put("44/1", "Straight 1/2/6/1/NA/N");
            put("45/1", "Straight 1/2/6/1/NA/1");
            put("46/1", "Straight 1/2/6/1/NA/2");
            put("47/1", "Straight 1/2/6/2/NA/N");
            put("48/1", "Straight 1/2/6/2/NA/1");
            put("49/1", "Straight 1/2/6/2/NA/2");
            put("50/1", "Straight 1/2/6/3/NA/N");
            put("51/1", "Straight 1/2/6/3/NA/1");
            put("52/1", "Straight 1/2/6/3/NA/2");
            put("53/1", "Straight 1/2/7/1/NA/N");
            put("54/1", "Straight 1/2/7/1/NA/1");
            put("55/1", "Straight 1/2/7/1/NA/2");
            put("56/1", "Straight 1/2/7/2/NA/N");
            put("57/1", "Straight 1/2/7/2/NA/1");
            put("58/1", "Straight 1/2/7/2/NA/2");
            put("59/1", "Straight 1/2/7/3/NA/N");
            put("60/1", "Straight 1/2/7/3/NA/1");
            put("61/1", "Straight 1/2/7/3/NA/2");
            put("62/1", "Straight 1/2/8/1/NA/N");
            put("63/1", "Straight 1/2/8/1/NA/1");
            put("64/1", "Straight 1/2/8/1/NA/2");
            put("65/1", "Straight 1/2/8/2/NA/N");
            put("66/1", "Straight 1/2/8/2/NA/1");
            put("67/1", "Straight 1/2/8/2/NA/2");
            put("68/1", "Straight 1/2/8/3/NA/N");
            put("69/1", "Straight 1/2/8/3/NA/1");
            put("70/1", "Straight 1/2/8/3/NA/2");
            put("71/1", "Straight 1/2/9/1/NA/N");
            put("72/1", "Straight 1/2/9/1/NA/1");
            put("73/1", "Straight 1/2/9/1/NA/2");
            put("74/1", "Straight 1/2/9/2/NA/N");
            put("75/1", "Straight 1/2/9/2/NA/1");
            put("76/1", "Straight 1/2/9/2/NA/2");
            put("77/1", "Straight 1/2/9/3/NA/N");
            put("78/1", "Straight 1/2/9/3/NA/1");
            put("79/1", "Straight 1/2/9/3/NA/2");
            put("80/1", "Straight 1/2/10/1/NA/NA");
            put("81/1", "Straight 1/2/10/2/NA/NA");
            put("82/1", "Straight 1/2/10/3/NA/NA");
            put("83/1", "Straight 1/2/11/1/NA/NA");
            put("84/1", "Straight 1/2/11/2/NA/NA");
            put("85/1", "Straight 1/2/11/3/NA/NA");
            put("86/1", "Straight 1/2/12/1/NA/NA");
            put("87/1", "Straight 1/2/12/2/NA/NA");
            put("88/1", "Straight 1/2/12/3/NA/NA");
            put("89/1", "Straight 1/2/13/1/NA/NA");
            put("90/1", "Straight 1/2/13/2/NA/NA");
            put("91/1", "Straight 1/2/13/3/NA/NA");
            put("92/1", "Straight 1/2/14/1/NA/NA");
            put("93/1", "Straight 1/2/14/2/NA/NA");
            put("94/1", "Straight 1/2/14/3/NA/NA");
            put("95/1", "Straight 1/2/15/1/NA/NA");
            put("96/1", "Straight 1/2/15/2/NA/NA");
            put("97/1", "Straight 1/2/15/3/NA/NA");
            put("98/1", "Straight 1/2/16/1/NA/NA");
            put("99/1", "Straight 1/2/16/2/NA/NA");
            put("100/1", "Straight 1/2/16/3/NA/NA");
            put("101/1", "Straight 1/3/1/1/NA/N");
            put("102/1", "Straight 1/3/1/1/NA/1");
            put("103/1", "Straight 1/3/1/1/NA/2");
            put("104/1", "Straight 1/3/1/2/NA/N");
            put("105/1", "Straight 1/3/1/2/NA/1");
            put("106/1", "Straight 1/3/1/2/NA/2");
            put("107/1", "Straight 1/3/1/3/NA/N");
            put("108/1", "Straight 1/3/1/3/NA/1");
            put("109/1", "Straight 1/3/1/3/NA/2");
            put("110/1", "Straight 1/3/2/1/NA/N");
            put("111/1", "Straight 1/3/2/1/NA/1");
            put("112/1", "Straight 1/3/2/1/NA/2");
            put("113/1", "Straight 1/3/2/2/NA/N");
            put("114/1", "Straight 1/3/2/2/NA/1");
            put("115/1", "Straight 1/3/2/2/NA/2");
            put("116/1", "Straight 1/3/2/3/NA/N");
            put("117/1", "Straight 1/3/2/3/NA/1");
            put("118/1", "Straight 1/3/2/3/NA/2");
            put("119/1", "Straight 1/3/3/1/NA/N");
            put("120/1", "Straight 1/3/3/1/NA/1");
            put("121/1", "Straight 1/3/3/1/NA/2");
            put("122/1", "Straight 1/3/3/2/NA/N");
            put("123/1", "Straight 1/3/3/2/NA/1");
            put("124/1", "Straight 1/3/3/2/NA/2");
            put("125/1", "Straight 1/3/3/3/NA/N");
            put("126/1", "Straight 1/3/3/3/NA/1");
            put("127/1", "Straight 1/3/3/3/NA/2");
            put("-128/1", "Straight 1/3/4/1/NA/N");
            put("-127/1", "Straight 1/3/4/1/NA/1");
            put("-126/1", "Straight 1/3/4/1/NA/2");
            put("-125/1", "Straight 1/3/4/2/NA/N");
            put("-124/1", "Straight 1/3/4/2/NA/1");
            put("-123/1", "Straight 1/3/4/2/NA/2");
            put("-122/1", "Straight 1/3/4/3/NA/N");
            put("-121/1", "Straight 1/3/4/3/NA/1");
            put("-120/1", "Straight 1/3/4/3/NA/2");
            put("-119/1", "Straight 1/3/5/1/NA/N");
            put("-118/1", "Straight 1/3/5/1/NA/1");
            put("-117/1", "Straight 1/3/5/1/NA/2");
            put("-116/1", "Straight 1/3/5/2/NA/N");
            put("-115/1", "Straight 1/3/5/2/NA/1");
            put("-114/1", "Straight 1/3/5/2/NA/2");
            put("-113/1", "Straight 1/3/5/3/NA/N");
            put("-112/1", "Straight 1/3/5/3/NA/1");
            put("-111/1", "Straight 1/3/5/3/NA/2");
            put("-110/1", "Straight 1/3/6/1/NA/N");
            put("-109/1", "Straight 1/3/6/1/NA/1");
            put("-108/1", "Straight 1/3/6/1/NA/2");
            put("-107/1", "Straight 1/3/6/2/NA/N");
            put("-106/1", "Straight 1/3/6/2/NA/1");
            put("-105/1", "Straight 1/3/6/2/NA/2");
            put("-104/1", "Straight 1/3/6/3/NA/N");
            put("-103/1", "Straight 1/3/6/3/NA/1");
            put("-102/1", "Straight 1/3/6/3/NA/2");
            put("-101/1", "Straight 1/3/7/1/NA/N");
            put("-100/1", "Straight 1/3/7/1/NA/1");
            put("-99/1", "Straight 1/3/7/1/NA/2");
            put("-98/1", "Straight 1/3/7/2/NA/N");
            put("-97/1", "Straight 1/3/7/2/NA/1");
            put("-96/1", "Straight 1/3/7/2/NA/2");
            put("-95/1", "Straight 1/3/7/3/NA/N");
            put("-94/1", "Straight 1/3/7/3/NA/1");
            put("-93/1", "Straight 1/3/7/3/NA/2");
            put("-92/1", "Straight 1/3/8/1/NA/N");
            put("-91/1", "Straight 1/3/8/1/NA/1");
            put("-90/1", "Straight 1/3/8/1/NA/2");
            put("-89/1", "Straight 1/3/8/2/NA/N");
            put("-88/1", "Straight 1/3/8/2/NA/1");
            put("-87/1", "Straight 1/3/8/2/NA/2");
            put("-86/1", "Straight 1/3/8/3/NA/N");
            put("-85/1", "Straight 1/3/8/3/NA/1");
            put("-84/1", "Straight 1/3/8/3/NA/2");
            put("-83/1", "Straight 1/3/9/1/NA/N");
            put("-82/1", "Straight 1/3/9/1/NA/1");
            put("-81/1", "Straight 1/3/9/1/NA/2");
            put("-80/1", "Straight 1/3/9/2/NA/N");
            put("-79/1", "Straight 1/3/9/2/NA/1");
            put("-78/1", "Straight 1/3/9/2/NA/2");
            put("-77/1", "Straight 1/3/9/3/NA/N");
            put("-76/1", "Straight 1/3/9/3/NA/1");
            put("-75/1", "Straight 1/3/9/3/NA/2");
            put("-74/1", "Straight 1/3/10/1/NA/NA");
            put("-73/1", "Straight 1/3/10/2/NA/NA");
            put("-72/1", "Straight 1/3/10/3/NA/NA");
            put("-71/1", "Straight 1/3/11/1/NA/NA");
            put("-70/1", "Straight 1/3/11/2/NA/NA");
            put("-69/1", "Straight 1/3/11/3/NA/NA");
            put("-68/1", "Straight 1/3/12/1/NA/NA");
            put("-67/1", "Straight 1/3/12/2/NA/NA");
            put("-66/1", "Straight 1/3/12/3/NA/NA");
            put("-65/1", "Straight 1/3/13/1/NA/NA");
            put("-64/1", "Straight 1/3/13/2/NA/NA");
            put("-63/1", "Straight 1/3/13/3/NA/NA");
            put("-62/1", "Straight 1/3/14/1/NA/NA");
            put("-61/1", "Straight 1/3/14/2/NA/NA");
            put("-60/1", "Straight 1/3/14/3/NA/NA");
            put("-59/1", "Straight 1/3/15/1/NA/NA");
            put("-58/1", "Straight 1/3/15/2/NA/NA");
            put("-57/1", "Straight 1/3/15/3/NA/NA");
            put("-56/1", "Straight 1/3/16/1/NA/NA");
            put("-55/1", "Straight 1/3/16/2/NA/NA");
            put("-54/1", "Straight 1/3/16/3/NA/NA");
            put("-53/1", "Straight 1/4/1/1/NA/N");
            put("-52/1", "Straight 1/4/1/1/NA/1");
            put("-51/1", "Straight 1/4/1/1/NA/2");
            put("-50/1", "Straight 1/4/1/2/NA/N");
            put("-49/1", "Straight 1/4/1/2/NA/1");
            put("-48/1", "Straight 1/4/1/2/NA/2");
            put("-47/1", "Straight 1/4/1/3/NA/N");
            put("-46/1", "Straight 1/4/1/3/NA/1");
            put("-45/1", "Straight 1/4/1/3/NA/2");
            put("-44/1", "Straight 1/4/2/1/NA/N");
            put("-43/1", "Straight 1/4/2/1/NA/1");
            put("-42/1", "Straight 1/4/2/1/NA/2");
            put("-41/1", "Straight 1/4/2/2/NA/N");
            put("-40/1", "Straight 1/4/2/2/NA/1");
            put("-39/1", "Straight 1/4/2/2/NA/2");
            put("-38/1", "Straight 1/4/2/3/NA/N");
            put("-37/1", "Straight 1/4/2/3/NA/1");
            put("-36/1", "Straight 1/4/2/3/NA/2");
            put("-35/1", "Straight 1/4/3/1/NA/N");
            put("-34/1", "Straight 1/4/3/1/NA/1");
            put("-33/1", "Straight 1/4/3/1/NA/2");
            put("-32/1", "Straight 1/4/3/2/NA/N");
            put("-31/1", "Straight 1/4/3/2/NA/1");
            put("-30/1", "Straight 1/4/3/2/NA/2");
            put("-29/1", "Straight 1/4/3/3/NA/N");
            put("-28/1", "Straight 1/4/3/3/NA/1");
            put("-27/1", "Straight 1/4/3/3/NA/2");
            put("-26/1", "Straight 1/4/4/1/NA/N");
            put("-25/1", "Straight 1/4/4/1/NA/1");
            put("-24/1", "Straight 1/4/4/1/NA/2");
            put("-23/1", "Straight 1/4/4/2/NA/N");
            put("-22/1", "Straight 1/4/4/2/NA/1");
            put("-21/1", "Straight 1/4/4/2/NA/2");
            put("-20/1", "Straight 1/4/4/3/NA/N");
            put("-19/1", "Straight 1/4/4/3/NA/1");
            put("-18/1", "Straight 1/4/4/3/NA/2");
            put("-17/1", "Straight 1/4/5/1/NA/N");
            put("-16/1", "Straight 1/4/5/1/NA/1");
            put("-15/1", "Straight 1/4/5/1/NA/2");
            put("-14/1", "Straight 1/4/5/2/NA/N");
            put("-13/1", "Straight 1/4/5/2/NA/1");
            put("-12/1", "Straight 1/4/5/2/NA/2");
            put("-11/1", "Straight 1/4/5/3/NA/N");
            put("-10/1", "Straight 1/4/5/3/NA/1");
            put("-9/1", "Straight 1/4/5/3/NA/2");
            put("-8/1", "Straight 1/4/6/1/NA/N");
            put("-7/1", "Straight 1/4/6/1/NA/1");
            put("-6/1", "Straight 1/4/6/1/NA/2");
            put("-5/1", "Straight 1/4/6/2/NA/N");
            put("-4/1", "Straight 1/4/6/2/NA/1");
            put("-3/1", "Straight 1/4/6/2/NA/2");
            put("-2/1", "Straight 1/4/6/3/NA/N");
            put("-1/1", "Straight 1/4/6/3/NA/1");
            put("0/2", "Straight 1/4/6/3/NA/2");
            put("1/2", "Straight 1/4/7/1/NA/N");
            put("2/2", "Straight 1/4/7/1/NA/1");
            put("3/2", "Straight 1/4/7/1/NA/2");
            put("4/2", "Straight 1/4/7/2/NA/N");
            put("5/2", "Straight 1/4/7/2/NA/1");
            put("6/2", "Straight 1/4/7/2/NA/2");
            put("7/2", "Straight 1/4/7/3/NA/N");
            put("8/2", "Straight 1/4/7/3/NA/1");
            put("9/2", "Straight 1/4/7/3/NA/2");
            put("10/2", "Straight 1/4/8/1/NA/N");
            put("11/2", "Straight 1/4/8/1/NA/1");
            put("12/2", "Straight 1/4/8/1/NA/2");
            put("13/2", "Straight 1/4/8/2/NA/N");
            put("14/2", "Straight 1/4/8/2/NA/1");
            put("15/2", "Straight 1/4/8/2/NA/2");
            put("16/2", "Straight 1/4/8/3/NA/N");
            put("17/2", "Straight 1/4/8/3/NA/1");
            put("18/2", "Straight 1/4/8/3/NA/2");
            put("19/2", "Straight 1/4/9/1/NA/N");
            put("20/2", "Straight 1/4/9/1/NA/1");
            put("21/2", "Straight 1/4/9/1/NA/2");
            put("22/2", "Straight 1/4/9/2/NA/N");
            put("23/2", "Straight 1/4/9/2/NA/1");
            put("24/2", "Straight 1/4/9/2/NA/2");
            put("25/2", "Straight 1/4/9/3/NA/N");
            put("26/2", "Straight 1/4/9/3/NA/1");
            put("27/2", "Straight 1/4/9/3/NA/2");
            put("28/2", "Straight 1/4/10/1/NA/NA");
            put("29/2", "Straight 1/4/10/2/NA/NA");
            put("30/2", "Straight 1/4/10/3/NA/NA");
            put("31/2", "Straight 1/4/11/1/NA/NA");
            put("32/2", "Straight 1/4/11/2/NA/NA");
            put("33/2", "Straight 1/4/11/3/NA/NA");
            put("34/2", "Straight 1/4/12/1/NA/NA");
            put("35/2", "Straight 1/4/12/2/NA/NA");
            put("36/2", "Straight 1/4/12/3/NA/NA");
            put("37/2", "Straight 1/4/13/1/NA/NA");
            put("38/2", "Straight 1/4/13/2/NA/NA");
            put("39/2", "Straight 1/4/13/3/NA/NA");
            put("40/2", "Straight 1/4/14/1/NA/NA");
            put("41/2", "Straight 1/4/14/2/NA/NA");
            put("42/2", "Straight 1/4/14/3/NA/NA");
            put("43/2", "Straight 1/4/15/1/NA/NA");
            put("44/2", "Straight 1/4/15/2/NA/NA");
            put("45/2", "Straight 1/4/15/3/NA/NA");
            put("46/2", "Straight 1/4/16/1/NA/NA");
            put("47/2", "Straight 1/4/16/2/NA/NA");
            put("48/2", "Straight 1/4/16/3/NA/NA");
            put("49/2", "Straight 2/1/1/1/NA/N");
            put("50/2", "Straight 2/1/1/1/NA/1");
            put("51/2", "Straight 2/1/1/1/NA/2");
            put("52/2", "Straight 2/1/1/2/NA/N");
            put("53/2", "Straight 2/1/1/2/NA/1");
            put("54/2", "Straight 2/1/1/2/NA/2");
            put("55/2", "Straight 2/1/1/3/NA/N");
            put("56/2", "Straight 2/1/1/3/NA/1");
            put("57/2", "Straight 2/1/1/3/NA/2");
            put("58/2", "Straight 2/1/2/1/NA/N");
            put("59/2", "Straight 2/1/2/1/NA/1");
            put("60/2", "Straight 2/1/2/1/NA/2");
            put("61/2", "Straight 2/1/2/2/NA/N");
            put("62/2", "Straight 2/1/2/2/NA/1");
            put("63/2", "Straight 2/1/2/2/NA/2");
            put("64/2", "Straight 2/1/2/3/NA/N");
            put("65/2", "Straight 2/1/2/3/NA/1");
            put("66/2", "Straight 2/1/2/3/NA/2");
            put("67/2", "Straight 2/1/3/1/NA/NA");
            put("68/2", "Straight 2/1/3/2/NA/NA");
            put("69/2", "Straight 2/1/3/3/NA/NA");
            put("70/2", "Straight 2/1/4/1/NA/NA");
            put("71/2", "Straight 2/1/4/2/NA/NA");
            put("72/2", "Straight 2/1/4/3/NA/NA");
            put("73/2", "Straight 2/1/5/1/NA/NA");
            put("74/2", "Straight 2/1/5/2/NA/NA");
            put("75/2", "Straight 2/1/5/3/NA/NA");
            put("76/2", "Straight 2/1/6/1/NA/NA");
            put("77/2", "Straight 2/1/6/2/NA/NA");
            put("78/2", "Straight 2/1/6/3/NA/NA");
            put("79/2", "Straight 2/1/7/1/NA/NA");
            put("80/2", "Straight 2/1/7/2/NA/NA");
            put("81/2", "Straight 2/1/7/3/NA/NA");
            put("82/2", "Straight 2/2/1/1/NA/N");
            put("83/2", "Straight 2/2/1/1/NA/1");
            put("84/2", "Straight 2/2/1/1/NA/2");
            put("85/2", "Straight 2/2/1/2/NA/N");
            put("86/2", "Straight 2/2/1/2/NA/1");
            put("87/2", "Straight 2/2/1/2/NA/2");
            put("88/2", "Straight 2/2/1/3/NA/N");
            put("89/2", "Straight 2/2/1/3/NA/1");
            put("90/2", "Straight 2/2/1/3/NA/2");
            put("91/2", "Straight 2/2/2/1/NA/N");
            put("92/2", "Straight 2/2/2/1/NA/1");
            put("93/2", "Straight 2/2/2/1/NA/2");
            put("94/2", "Straight 2/2/2/2/NA/N");
            put("95/2", "Straight 2/2/2/2/NA/1");
            put("96/2", "Straight 2/2/2/2/NA/2");
            put("97/2", "Straight 2/2/2/3/NA/N");
            put("98/2", "Straight 2/2/2/3/NA/1");
            put("99/2", "Straight 2/2/2/3/NA/2");
            put("100/2", "Straight 2/2/3/1/NA/NA");
            put("101/2", "Straight 2/2/3/2/NA/NA");
            put("102/2", "Straight 2/2/3/3/NA/NA");
            put("103/2", "Straight 2/2/4/1/NA/NA");
            put("104/2", "Straight 2/2/4/2/NA/NA");
            put("105/2", "Straight 2/2/4/3/NA/NA");
            put("106/2", "Straight 2/2/5/1/NA/NA");
            put("107/2", "Straight 2/2/5/2/NA/NA");
            put("108/2", "Straight 2/2/5/3/NA/NA");
            put("109/2", "Straight 2/2/6/1/NA/NA");
            put("110/2", "Straight 2/2/6/2/NA/NA");
            put("111/2", "Straight 2/2/6/3/NA/NA");
            put("112/2", "Straight 2/2/7/1/NA/NA");
            put("113/2", "Straight 2/2/7/2/NA/NA");
            put("114/2", "Straight 2/2/7/3/NA/NA");
            put("115/2", "Straight 2/3/1/1/NA/N");
            put("116/2", "Straight 2/3/1/1/NA/1");
            put("117/2", "Straight 2/3/1/1/NA/2");
            put("118/2", "Straight 2/3/1/2/NA/N");
            put("119/2", "Straight 2/3/1/2/NA/1");
            put("120/2", "Straight 2/3/1/2/NA/2");
            put("121/2", "Straight 2/3/1/3/NA/N");
            put("122/2", "Straight 2/3/1/3/NA/1");
            put("123/2", "Straight 2/3/1/3/NA/2");
            put("124/2", "Straight 2/3/2/1/NA/N");
            put("125/2", "Straight 2/3/2/1/NA/1");
            put("126/2", "Straight 2/3/2/1/NA/2");
            put("127/2", "Straight 2/3/2/2/NA/N");
            put("-128/2", "Straight 2/3/2/2/NA/1");
            put("-127/2", "Straight 2/3/2/2/NA/2");
            put("-126/2", "Straight 2/3/2/3/NA/N");
            put("-125/2", "Straight 2/3/2/3/NA/1");
            put("-124/2", "Straight 2/3/2/3/NA/2");
            put("-123/2", "Straight 2/3/3/1/NA/NA");
            put("-122/2", "Straight 2/3/3/2/NA/NA");
            put("-121/2", "Straight 2/3/3/3/NA/NA");
            put("-120/2", "Straight 2/3/4/1/NA/NA");
            put("-119/2", "Straight 2/3/4/2/NA/NA");
            put("-118/2", "Straight 2/3/4/3/NA/NA");
            put("-117/2", "Straight 2/3/5/1/NA/NA");
            put("-116/2", "Straight 2/3/5/2/NA/NA");
            put("-115/2", "Straight 2/3/5/3/NA/NA");
            put("-114/2", "Straight 2/3/6/1/NA/NA");
            put("-113/2", "Straight 2/3/6/2/NA/NA");
            put("-112/2", "Straight 2/3/6/3/NA/NA");
            put("-111/2", "Straight 2/3/7/1/NA/NA");
            put("-110/2", "Straight 2/3/7/2/NA/NA");
            put("-109/2", "Straight 2/3/7/3/NA/NA");
            put("-108/2", "Curly 1/1/1/1/NA/N");
            put("-107/2", "Curly 1/1/1/1/NA/1");
            put("-106/2", "Curly 1/1/1/1/NA/2");
            put("-105/2", "Curly 1/1/1/2/NA/N");
            put("-104/2", "Curly 1/1/1/2/NA/1");
            put("-103/2", "Curly 1/1/1/2/NA/2");
            put("-102/2", "Curly 1/1/1/3/NA/N");
            put("-101/2", "Curly 1/1/1/3/NA/1");
            put("-100/2", "Curly 1/1/1/3/NA/2");
            put("-99/2", "Curly 1/1/2/1/NA/N");
            put("-98/2", "Curly 1/1/2/1/NA/1");
            put("-97/2", "Curly 1/1/2/1/NA/2");
            put("-96/2", "Curly 1/1/2/2/NA/N");
            put("-95/2", "Curly 1/1/2/2/NA/1");
            put("-94/2", "Curly 1/1/2/2/NA/2");
            put("-93/2", "Curly 1/1/2/3/NA/N");
            put("-92/2", "Curly 1/1/2/3/NA/1");
            put("-91/2", "Curly 1/1/2/3/NA/2");
            put("-90/2", "Curly 1/1/3/1/NA/N");
            put("-89/2", "Curly 1/1/3/1/NA/1");
            put("-88/2", "Curly 1/1/3/1/NA/2");
            put("-87/2", "Curly 1/1/3/2/NA/N");
            put("-86/2", "Curly 1/1/3/2/NA/1");
            put("-85/2", "Curly 1/1/3/2/NA/2");
            put("-84/2", "Curly 1/1/3/3/NA/N");
            put("-83/2", "Curly 1/1/3/3/NA/1");
            put("-82/2", "Curly 1/1/3/3/NA/2");
            put("-81/2", "Curly 1/1/4/1/NA/N");
            put("-80/2", "Curly 1/1/4/1/NA/1");
            put("-79/2", "Curly 1/1/4/1/NA/2");
            put("-78/2", "Curly 1/1/4/2/NA/N");
            put("-77/2", "Curly 1/1/4/2/NA/1");
            put("-76/2", "Curly 1/1/4/2/NA/2");
            put("-75/2", "Curly 1/1/4/3/NA/N");
            put("-74/2", "Curly 1/1/4/3/NA/1");
            put("-73/2", "Curly 1/1/4/3/NA/2");
            put("-72/2", "Curly 1/1/5/1/NA/N");
            put("-71/2", "Curly 1/1/5/1/NA/1");
            put("-70/2", "Curly 1/1/5/1/NA/2");
            put("-69/2", "Curly 1/1/5/2/NA/N");
            put("-68/2", "Curly 1/1/5/2/NA/1");
            put("-67/2", "Curly 1/1/5/2/NA/2");
            put("-66/2", "Curly 1/1/5/3/NA/N");
            put("-65/2", "Curly 1/1/5/3/NA/1");
            put("-64/2", "Curly 1/1/5/3/NA/2");
            put("-63/2", "Curly 1/1/6/1/NA/NA");
            put("-62/2", "Curly 1/1/6/2/NA/NA");
            put("-61/2", "Curly 1/1/6/3/NA/NA");
            put("-60/2", "Curly 1/1/7/1/NA/NA");
            put("-59/2", "Curly 1/1/7/2/NA/NA");
            put("-58/2", "Curly 1/1/7/3/NA/NA");
            put("-57/2", "Curly 1/2/1/1/NA/N");
            put("-56/2", "Curly 1/2/1/1/NA/1");
            put("-55/2", "Curly 1/2/1/1/NA/2");
            put("-54/2", "Curly 1/2/1/2/NA/N");
            put("-53/2", "Curly 1/2/1/2/NA/1");
            put("-52/2", "Curly 1/2/1/2/NA/2");
            put("-51/2", "Curly 1/2/1/3/NA/N");
            put("-50/2", "Curly 1/2/1/3/NA/1");
            put("-49/2", "Curly 1/2/1/3/NA/2");
            put("-48/2", "Curly 1/2/2/1/NA/N");
            put("-47/2", "Curly 1/2/2/1/NA/1");
            put("-46/2", "Curly 1/2/2/1/NA/2");
            put("-45/2", "Curly 1/2/2/2/NA/N");
            put("-44/2", "Curly 1/2/2/2/NA/1");
            put("-43/2", "Curly 1/2/2/2/NA/2");
            put("-42/2", "Curly 1/2/2/3/NA/N");
            put("-41/2", "Curly 1/2/2/3/NA/1");
            put("-40/2", "Curly 1/2/2/3/NA/2");
            put("-39/2", "Curly 1/2/3/1/NA/N");
            put("-38/2", "Curly 1/2/3/1/NA/1");
            put("-37/2", "Curly 1/2/3/1/NA/2");
            put("-36/2", "Curly 1/2/3/2/NA/N");
            put("-35/2", "Curly 1/2/3/2/NA/1");
            put("-34/2", "Curly 1/2/3/2/NA/2");
            put("-33/2", "Curly 1/2/3/3/NA/N");
            put("-32/2", "Curly 1/2/3/3/NA/1");
            put("-31/2", "Curly 1/2/3/3/NA/2");
            put("-30/2", "Curly 1/2/4/1/NA/N");
            put("-29/2", "Curly 1/2/4/1/NA/1");
            put("-28/2", "Curly 1/2/4/1/NA/2");
            put("-27/2", "Curly 1/2/4/2/NA/N");
            put("-26/2", "Curly 1/2/4/2/NA/1");
            put("-25/2", "Curly 1/2/4/2/NA/2");
            put("-24/2", "Curly 1/2/4/3/NA/N");
            put("-23/2", "Curly 1/2/4/3/NA/1");
            put("-22/2", "Curly 1/2/4/3/NA/2");
            put("-21/2", "Curly 1/2/5/1/NA/N");
            put("-20/2", "Curly 1/2/5/1/NA/1");
            put("-19/2", "Curly 1/2/5/1/NA/2");
            put("-18/2", "Curly 1/2/5/2/NA/N");
            put("-17/2", "Curly 1/2/5/2/NA/1");
            put("-16/2", "Curly 1/2/5/2/NA/2");
            put("-15/2", "Curly 1/2/5/3/NA/N");
            put("-14/2", "Curly 1/2/5/3/NA/1");
            put("-13/2", "Curly 1/2/5/3/NA/2");
            put("-12/2", "Curly 1/2/6/1/NA/NA");
            put("-11/2", "Curly 1/2/6/2/NA/NA");
            put("-10/2", "Curly 1/2/6/3/NA/NA");
            put("-9/2", "Curly 1/2/7/1/NA/NA");
            put("-8/2", "Curly 1/2/7/2/NA/NA");
            put("-7/2", "Curly 1/2/7/3/NA/NA");
            put("-6/2", "Curly 1/3/1/1/NA/N");
            put("-5/2", "Curly 1/3/1/1/NA/1");
            put("-4/2", "Curly 1/3/1/1/NA/2");
            put("-3/2", "Curly 1/3/1/2/NA/N");
            put("-2/2", "Curly 1/3/1/2/NA/1");
            put("-1/2", "Curly 1/3/1/2/NA/2");
            put("0/3", "Curly 1/3/1/3/NA/N");
            put("1/3", "Curly 1/3/1/3/NA/1");
            put("2/3", "Curly 1/3/1/3/NA/2");
            put("3/3", "Curly 1/3/2/1/NA/N");
            put("4/3", "Curly 1/3/2/1/NA/1");
            put("5/3", "Curly 1/3/2/1/NA/2");
            put("6/3", "Curly 1/3/2/2/NA/N");
            put("7/3", "Curly 1/3/2/2/NA/1");
            put("8/3", "Curly 1/3/2/2/NA/2");
            put("9/3", "Curly 1/3/2/3/NA/N");
            put("10/3", "Curly 1/3/2/3/NA/1");
            put("11/3", "Curly 1/3/2/3/NA/2");
            put("12/3", "Curly 1/3/3/1/NA/N");
            put("13/3", "Curly 1/3/3/1/NA/1");
            put("14/3", "Curly 1/3/3/1/NA/2");
            put("15/3", "Curly 1/3/3/2/NA/N");
            put("16/3", "Curly 1/3/3/2/NA/1");
            put("17/3", "Curly 1/3/3/2/NA/2");
            put("18/3", "Curly 1/3/3/3/NA/N");
            put("19/3", "Curly 1/3/3/3/NA/1");
            put("20/3", "Curly 1/3/3/3/NA/2");
            put("21/3", "Curly 1/3/4/1/NA/N");
            put("22/3", "Curly 1/3/4/1/NA/1");
            put("23/3", "Curly 1/3/4/1/NA/2");
            put("24/3", "Curly 1/3/4/2/NA/N");
            put("25/3", "Curly 1/3/4/2/NA/1");
            put("26/3", "Curly 1/3/4/2/NA/2");
            put("27/3", "Curly 1/3/4/3/NA/N");
            put("28/3", "Curly 1/3/4/3/NA/1");
            put("29/3", "Curly 1/3/4/3/NA/2");
            put("30/3", "Curly 1/3/5/1/NA/N");
            put("31/3", "Curly 1/3/5/1/NA/1");
            put("32/3", "Curly 1/3/5/1/NA/2");
            put("33/3", "Curly 1/3/5/2/NA/N");
            put("34/3", "Curly 1/3/5/2/NA/1");
            put("35/3", "Curly 1/3/5/2/NA/2");
            put("36/3", "Curly 1/3/5/3/NA/N");
            put("37/3", "Curly 1/3/5/3/NA/1");
            put("38/3", "Curly 1/3/5/3/NA/2");
            put("39/3", "Curly 1/3/6/1/NA/NA");
            put("40/3", "Curly 1/3/6/2/NA/NA");
            put("41/3", "Curly 1/3/6/3/NA/NA");
            put("42/3", "Curly 1/3/7/1/NA/NA");
            put("43/3", "Curly 1/3/7/2/NA/NA");
            put("44/3", "Curly 1/3/7/3/NA/NA");
            put("45/3", "Curly 1/4/1/1/NA/N");
            put("46/3", "Curly 1/4/1/1/NA/1");
            put("47/3", "Curly 1/4/1/1/NA/2");
            put("48/3", "Curly 1/4/1/2/NA/N");
            put("49/3", "Curly 1/4/1/2/NA/1");
            put("50/3", "Curly 1/4/1/2/NA/2");
            put("51/3", "Curly 1/4/1/3/NA/N");
            put("52/3", "Curly 1/4/1/3/NA/1");
            put("53/3", "Curly 1/4/1/3/NA/2");
            put("54/3", "Curly 1/4/2/1/NA/N");
            put("55/3", "Curly 1/4/2/1/NA/1");
            put("56/3", "Curly 1/4/2/1/NA/2");
            put("57/3", "Curly 1/4/2/2/NA/N");
            put("58/3", "Curly 1/4/2/2/NA/1");
            put("59/3", "Curly 1/4/2/2/NA/2");
            put("60/3", "Curly 1/4/2/3/NA/N");
            put("61/3", "Curly 1/4/2/3/NA/1");
            put("62/3", "Curly 1/4/2/3/NA/2");
            put("63/3", "Curly 1/4/3/1/NA/N");
            put("64/3", "Curly 1/4/3/1/NA/1");
            put("65/3", "Curly 1/4/3/1/NA/2");
            put("66/3", "Curly 1/4/3/2/NA/N");
            put("67/3", "Curly 1/4/3/2/NA/1");
            put("68/3", "Curly 1/4/3/2/NA/2");
            put("69/3", "Curly 1/4/3/3/NA/N");
            put("70/3", "Curly 1/4/3/3/NA/1");
            put("71/3", "Curly 1/4/3/3/NA/2");
            put("72/3", "Curly 1/4/4/1/NA/N");
            put("73/3", "Curly 1/4/4/1/NA/1");
            put("74/3", "Curly 1/4/4/1/NA/2");
            put("75/3", "Curly 1/4/4/2/NA/N");
            put("76/3", "Curly 1/4/4/2/NA/1");
            put("77/3", "Curly 1/4/4/2/NA/2");
            put("78/3", "Curly 1/4/4/3/NA/N");
            put("79/3", "Curly 1/4/4/3/NA/1");
            put("80/3", "Curly 1/4/4/3/NA/2");
            put("81/3", "Curly 1/4/5/1/NA/N");
            put("82/3", "Curly 1/4/5/1/NA/1");
            put("83/3", "Curly 1/4/5/1/NA/2");
            put("84/3", "Curly 1/4/5/2/NA/N");
            put("85/3", "Curly 1/4/5/2/NA/1");
            put("86/3", "Curly 1/4/5/2/NA/2");
            put("87/3", "Curly 1/4/5/3/NA/N");
            put("88/3", "Curly 1/4/5/3/NA/1");
            put("89/3", "Curly 1/4/5/3/NA/2");
            put("90/3", "Curly 1/4/6/1/NA/NA");
            put("91/3", "Curly 1/4/6/2/NA/NA");
            put("92/3", "Curly 1/4/6/3/NA/NA");
            put("93/3", "Curly 1/4/7/1/NA/NA");
            put("94/3", "Curly 1/4/7/2/NA/NA");
            put("95/3", "Curly 1/4/7/3/NA/NA");
            put("96/3", "Curly 2/1/1/1/NA/NA");
            put("97/3", "Curly 2/1/1/2/NA/NA");
            put("98/3", "Curly 2/1/2/1/NA/NA");
            put("99/3", "Curly 2/1/2/2/NA/NA");
            put("100/3", "Curly 2/1/3/1/NA/NA");
            put("101/3", "Curly 2/1/3/2/NA/NA");
            put("102/3", "Curly 2/1/4/1/NA/NA");
            put("103/3", "Curly 2/1/4/2/NA/NA");
            put("104/3", "Curly 2/1/5/1/NA/NA");
            put("105/3", "Curly 2/1/5/2/NA/NA");
            put("106/3", "Curly 2/1/6/1/NA/NA");
            put("107/3", "Curly 2/1/6/2/NA/NA");
            put("108/3", "Curly 2/2/1/1/NA/NA");
            put("109/3", "Curly 2/2/1/2/NA/NA");
            put("110/3", "Curly 2/2/2/1/NA/NA");
            put("111/3", "Curly 2/2/2/2/NA/NA");
            put("112/3", "Curly 2/2/3/1/NA/NA");
            put("113/3", "Curly 2/2/3/2/NA/NA");
            put("114/3", "Curly 2/2/4/1/NA/NA");
            put("115/3", "Curly 2/2/4/2/NA/NA");
            put("116/3", "Curly 2/2/5/1/NA/NA");
            put("117/3", "Curly 2/2/5/2/NA/NA");
            put("118/3", "Curly 2/2/6/1/NA/NA");
            put("119/3", "Curly 2/2/6/2/NA/NA");
            put("120/3", "Curly 2/3/1/1/NA/NA");
            put("121/3", "Curly 2/3/1/2/NA/NA");
            put("122/3", "Curly 2/3/2/1/NA/NA");
            put("123/3", "Curly 2/3/2/2/NA/NA");
            put("124/3", "Curly 2/3/3/1/NA/NA");
            put("125/3", "Curly 2/3/3/2/NA/NA");
            put("126/3", "Curly 2/3/4/1/NA/NA");
            put("127/3", "Curly 2/3/4/2/NA/NA");
            put("-128/3", "Curly 2/3/5/1/NA/NA");
            put("-127/3", "Curly 2/3/5/2/NA/NA");
            put("-126/3", "Curly 2/3/6/1/NA/NA");
            put("-125/3", "Curly 2/3/6/2/NA/NA");
            put("-124/3", "Curly 2/4/1/1/NA/NA");
            put("-123/3", "Curly 2/4/1/2/NA/NA");
            put("-122/3", "Curly 2/4/2/1/NA/NA");
            put("-121/3", "Curly 2/4/2/2/NA/NA");
            put("-120/3", "Curly 2/4/3/1/NA/NA");
            put("-119/3", "Curly 2/4/3/2/NA/NA");
            put("-118/3", "Curly 2/4/4/1/NA/NA");
            put("-117/3", "Curly 2/4/4/2/NA/NA");
            put("-116/3", "Curly 2/4/5/1/NA/NA");
            put("-115/3", "Curly 2/4/5/2/NA/NA");
            put("-114/3", "Curly 2/4/6/1/NA/NA");
            put("-113/3", "Curly 2/4/6/2/NA/NA");
            put("-112/3", "Ponytail 1/1/1/1/NA/NA");
            put("-111/3", "Ponytail 1/1/1/2/NA/NA");
            put("-110/3", "Ponytail 1/1/1/3/NA/NA");
            put("-109/3", "Ponytail 1/1/2/1/NA/NA");
            put("-108/3", "Ponytail 1/1/2/2/NA/NA");
            put("-107/3", "Ponytail 1/1/2/3/NA/NA");
            put("-106/3", "Ponytail 1/1/3/1/NA/NA");
            put("-105/3", "Ponytail 1/1/3/2/NA/NA");
            put("-104/3", "Ponytail 1/1/3/3/NA/NA");
            put("-103/3", "Ponytail 1/1/4/1/NA/NA");
            put("-102/3", "Ponytail 1/1/4/2/NA/NA");
            put("-101/3", "Ponytail 1/1/4/3/NA/NA");
            put("-100/3", "Ponytail 1/2/1/1/NA/NA");
            put("-99/3", "Ponytail 1/2/1/2/NA/NA");
            put("-98/3", "Ponytail 1/2/1/3/NA/NA");
            put("-97/3", "Ponytail 1/2/2/1/NA/NA");
            put("-96/3", "Ponytail 1/2/2/2/NA/NA");
            put("-95/3", "Ponytail 1/2/2/3/NA/NA");
            put("-94/3", "Ponytail 1/2/3/1/NA/NA");
            put("-93/3", "Ponytail 1/2/3/2/NA/NA");
            put("-92/3", "Ponytail 1/2/3/3/NA/NA");
            put("-91/3", "Ponytail 1/2/4/1/NA/NA");
            put("-90/3", "Ponytail 1/2/4/2/NA/NA");
            put("-89/3", "Ponytail 1/2/4/3/NA/NA");
            put("-88/3", "Ponytail 1/3/1/1/NA/NA");
            put("-87/3", "Ponytail 1/3/1/2/NA/NA");
            put("-86/3", "Ponytail 1/3/1/3/NA/NA");
            put("-85/3", "Ponytail 1/3/2/1/NA/NA");
            put("-84/3", "Ponytail 1/3/2/2/NA/NA");
            put("-83/3", "Ponytail 1/3/2/3/NA/NA");
            put("-82/3", "Ponytail 1/3/3/1/NA/NA");
            put("-81/3", "Ponytail 1/3/3/2/NA/NA");
            put("-80/3", "Ponytail 1/3/3/3/NA/NA");
            put("-79/3", "Ponytail 1/3/4/1/NA/NA");
            put("-78/3", "Ponytail 1/3/4/2/NA/NA");
            put("-77/3", "Ponytail 1/3/4/3/NA/NA");
            put("-76/3", "Ponytail 2/1/1/1/NA/NA");
            put("-75/3", "Ponytail 2/1/1/2/NA/NA");
            put("-74/3", "Ponytail 2/1/1/3/NA/NA");
            put("-73/3", "Ponytail 2/1/2/1/NA/NA");
            put("-72/3", "Ponytail 2/1/2/2/NA/NA");
            put("-71/3", "Ponytail 2/1/2/3/NA/NA");
            put("-70/3", "Ponytail 2/1/3/1/NA/NA");
            put("-69/3", "Ponytail 2/1/3/2/NA/NA");
            put("-68/3", "Ponytail 2/1/3/3/NA/NA");
            put("-67/3", "Ponytail 2/1/4/1/NA/NA");
            put("-66/3", "Ponytail 2/1/4/2/NA/NA");
            put("-65/3", "Ponytail 2/1/4/3/NA/NA");
            put("-64/3", "Ponytail 2/2/1/1/NA/NA");
            put("-63/3", "Ponytail 2/2/1/2/NA/NA");
            put("-62/3", "Ponytail 2/2/1/3/NA/NA");
            put("-61/3", "Ponytail 2/2/2/1/NA/NA");
            put("-60/3", "Ponytail 2/2/2/2/NA/NA");
            put("-59/3", "Ponytail 2/2/2/3/NA/NA");
            put("-58/3", "Ponytail 2/2/3/1/NA/NA");
            put("-57/3", "Ponytail 2/2/3/2/NA/NA");
            put("-56/3", "Ponytail 2/2/3/3/NA/NA");
            put("-55/3", "Ponytail 2/2/4/1/NA/NA");
            put("-54/3", "Ponytail 2/2/4/2/NA/NA");
            put("-53/3", "Ponytail 2/2/4/3/NA/NA");
            put("-52/3", "Ponytail 2/3/1/1/NA/NA");
            put("-51/3", "Ponytail 2/3/1/2/NA/NA");
            put("-50/3", "Ponytail 2/3/1/3/NA/NA");
            put("-49/3", "Ponytail 2/3/2/1/NA/NA");
            put("-48/3", "Ponytail 2/3/2/2/NA/NA");
            put("-47/3", "Ponytail 2/3/2/3/NA/NA");
            put("-46/3", "Ponytail 2/3/3/1/NA/NA");
            put("-45/3", "Ponytail 2/3/3/2/NA/NA");
            put("-44/3", "Ponytail 2/3/3/3/NA/NA");
            put("-43/3", "Ponytail 2/3/4/1/NA/NA");
            put("-42/3", "Ponytail 2/3/4/2/NA/NA");
            put("-41/3", "Ponytail 2/3/4/3/NA/NA");
            put("-40/3", "Dreadlocks/1/1/1/NA/NA");
            put("-39/3", "Dreadlocks/1/1/2/NA/NA");
            put("-38/3", "Dreadlocks/1/2/1/NA/NA");
            put("-37/3", "Dreadlocks/1/2/2/NA/NA");
            put("-36/3", "Dreadlocks/1/3/1/NA/NA");
            put("-35/3", "Dreadlocks/1/3/2/NA/NA");
            put("-34/3", "Dreadlocks/1/4/1/NA/NA");
            put("-33/3", "Dreadlocks/1/4/2/NA/NA");
            put("-32/3", "Dreadlocks/2/1/1/NA/NA");
            put("-31/3", "Dreadlocks/2/1/2/NA/NA");
            put("-30/3", "Dreadlocks/2/2/1/NA/NA");
            put("-29/3", "Dreadlocks/2/2/2/NA/NA");
            put("-28/3", "Dreadlocks/2/3/1/NA/NA");
            put("-27/3", "Dreadlocks/2/3/2/NA/NA");
            put("-26/3", "Dreadlocks/2/4/1/NA/NA");
            put("-25/3", "Dreadlocks/2/4/2/NA/NA");
            put("-24/3", "Dreadlocks/3/1/1/NA/NA");
            put("-23/3", "Dreadlocks/3/1/2/NA/NA");
            put("-22/3", "Dreadlocks/3/2/1/NA/NA");
            put("-21/3", "Dreadlocks/3/2/2/NA/NA");
            put("-20/3", "Dreadlocks/3/3/1/NA/NA");
            put("-19/3", "Dreadlocks/3/3/2/NA/NA");
            put("-18/3", "Dreadlocks/3/4/1/NA/NA");
            put("-17/3", "Dreadlocks/3/4/2/NA/NA");
            put("-16/3", "Pulled Back/1/1/NA/NA/NA");
            put("-15/3", "Pulled Back/1/2/NA/NA/NA");
            put("-14/3", "Pulled Back/1/3/NA/NA/NA");
            put("-13/3", "Pulled Back/1/4/NA/NA/NA");
            put("-12/3", "Pulled Back/1/5/NA/NA/NA");
            put("-11/3", "Pulled Back/1/6/NA/NA/NA");
            put("-10/3", "Pulled Back/2/1/NA/NA/NA");
            put("-9/3", "Pulled Back/2/2/NA/NA/NA");
            put("-8/3", "Pulled Back/2/3/NA/NA/NA");
            put("-7/3", "Pulled Back/2/4/NA/NA/NA");
            put("-6/3", "Pulled Back/2/5/NA/NA/NA");
            put("-5/3", "Pulled Back/2/6/NA/NA/NA");
            put("-4/3", "Pulled Back/3/1/NA/NA/NA");
            put("-3/3", "Pulled Back/3/2/NA/NA/NA");
            put("-2/3", "Pulled Back/3/3/NA/NA/NA");
            put("-1/3", "Pulled Back/3/4/NA/NA/NA");
            put("0/4", "Pulled Back/3/5/NA/NA/NA");
            put("1/4", "Pulled Back/3/6/NA/NA/NA");
            put("2/4", "Special/1/NA/NA/NA/NA");
            put("3/4", "Special/2/NA/NA/NA/NA");
            put("4/4", "Special/3/NA/NA/NA/NA");
            put("5/4", "Special/4/NA/NA/NA/NA");
            put("6/4", "Special/5/NA/NA/NA/NA");
            put("7/4", "Special/6/NA/NA/NA/NA");
            put("8/4", "Special/7/NA/NA/NA/NA");
            put("9/4", "Special/8/NA/NA/NA/NA");
            put("10/4", "Special/9/NA/NA/NA/NA");
            put("11/4", "Special/10/NA/NA/NA/NA");
            put("12/4", "Special/11/NA/NA/NA/NA");
            put("13/4", "Special/12/NA/NA/NA/NA");
            put("14/4", "Special/13/NA/NA/NA/NA");
            put("15/4", "Special/14/NA/NA/NA/NA");
            put("16/4", "Special/15/NA/NA/NA/NA");
            put("17/4", "Special/16/NA/NA/NA/NA");
            put("18/4", "Special/17/NA/NA/NA/NA");
            put("19/4", "Special/18/NA/NA/NA/NA");
            put("20/4", "Special/19/NA/NA/NA/NA");
            put("21/4", "Special/20/NA/NA/NA/NA");
            put("22/4", "Special/21/NA/NA/NA/NA");
            put("23/4", "Special/22/NA/NA/NA/NA");
            put("24/4", "Special/23/NA/NA/NA/NA");
            put("25/4", "Special/24/NA/NA/NA/NA");
            put("26/4", "Special/25/NA/NA/NA/NA");
            put("27/4", "Special/26/NA/NA/NA/NA");
            put("28/4", "Special/27/NA/NA/NA/NA");
            put("29/4", "Special/28/NA/NA/NA/NA");
            put("30/4", "Special/29/NA/NA/NA/NA");
            put("31/4", "Special/30/NA/NA/NA/NA");
            put("32/4", "Special/31/NA/NA/NA/NA");
            put("33/4", "Special/32/NA/NA/NA/NA");
            put("34/4", "Special/33/NA/NA/NA/NA");
            put("35/4", "Special/34/NA/NA/NA/NA");
            put("36/4", "Special/35/NA/NA/NA/NA");
            put("37/4", "Special/36/NA/NA/NA/NA");
            put("38/4", "Special/37/NA/NA/NA/NA");
            put("39/4", "Special/38/NA/NA/NA/NA");
            put("40/4", "Special/39/NA/NA/NA/NA");
            put("41/4", "Special/40/NA/NA/NA/NA");
            put("42/4", "Special/41/NA/NA/NA/NA");
            put("43/4", "Special/42/NA/NA/NA/NA");
            put("44/4", "Special/43/NA/NA/NA/NA");
            put("45/4", "Special/44/NA/NA/NA/NA");
            put("46/4", "Special/45/NA/NA/NA/NA");
            put("47/4", "Special/46/NA/NA/NA/NA");
            put("48/4", "Special/47/NA/NA/NA/NA");
            put("49/4", "Special/48/NA/NA/NA/NA");
            put("50/4", "Special/49/NA/NA/NA/NA");
            put("51/4", "Special/50/NA/NA/NA/NA");
            put("52/4", "Special/51/NA/NA/NA/NA");
            put("53/4", "Special/52/NA/NA/NA/NA");
            put("54/4", "Special/53/NA/NA/NA/NA");
            put("55/4", "Special/54/NA/NA/NA/NA");
            put("56/4", "Special/55/NA/NA/NA/NA");
            put("57/4", "Special/56/NA/NA/NA/NA");
            put("58/4", "Special/57/NA/NA/NA/NA");
            put("59/4", "Special/58/NA/NA/NA/NA");
            put("60/4", "Special/59/NA/NA/NA/NA");
            put("61/4", "Special/60/NA/NA/NA/NA");
            put("62/4", "Special/61/NA/NA/NA/NA");
            put("63/4", "Special/62/NA/NA/NA/NA");
            put("64/4", "Special/63/NA/NA/NA/NA");
            put("65/4", "Special/64/NA/NA/NA/NA");
            put("66/4", "Special/65/NA/NA/NA/NA");
            put("67/4", "Special/66/NA/NA/NA/NA");
            put("68/4", "Special/67/NA/NA/NA/NA");
            put("69/4", "Special/68/NA/NA/NA/NA");
            put("70/4", "Special/69/NA/NA/NA/NA");
            put("71/4", "Special/70/NA/NA/NA/NA");
            put("72/4", "Special/71/NA/NA/NA/NA");
            put("73/4", "Special/72/NA/NA/NA/NA");
            put("74/4", "Special/73/NA/NA/NA/NA");
            put("75/4", "Special/74/NA/NA/NA/NA");
            put("76/4", "Special/75/NA/NA/NA/NA");
            put("77/4", "Special/76/NA/NA/NA/NA");
            put("78/4", "Special/77/NA/NA/NA/NA");
            put("79/4", "Special/78/NA/NA/NA/NA");
            put("80/4", "Special/79/NA/NA/NA/NA");
            put("81/4", "Special/80/NA/NA/NA/NA");
            put("82/4", "Special/81/NA/NA/NA/NA");
            put("83/4", "Special/82/NA/NA/NA/NA");
            put("84/4", "Special/83/NA/NA/NA/NA");
            put("85/4", "Special/84/NA/NA/NA/NA");
            put("86/4", "Special/85/NA/NA/NA/NA");
            put("87/4", "Special/86/NA/NA/NA/NA");
            put("88/4", "Special/87/NA/NA/NA/NA");
            put("89/4", "Special/88/NA/NA/NA/NA");
            put("90/4", "Special/89/NA/NA/NA/NA");
            put("91/4", "Special/90/NA/NA/NA/NA");
            put("92/4", "Special/91/NA/NA/NA/NA");
            put("93/4", "Special/92/NA/NA/NA/NA");
            put("94/4", "Special/93/NA/NA/NA/NA");
            put("95/4", "Special/94/NA/NA/NA/NA");
            put("96/4", "Special/95/NA/NA/NA/NA");
            put("97/4", "Special/96/NA/NA/NA/NA");
            put("98/4", "Special/97/NA/NA/NA/NA");
            put("99/4", "Special/98/NA/NA/NA/NA");
            put("100/4", "Special/99/NA/NA/NA/NA");
            put("101/4", "Special/100/NA/NA/NA/NA");
            put("102/4", "Special/101/NA/NA/NA/NA");
            put("103/4", "Special/102/NA/NA/NA/NA");
            put("104/4", "Special/103/NA/NA/NA/NA");
            put("105/4", "Special/104/NA/NA/NA/NA");
            put("106/4", "Special/105/NA/NA/NA/NA");
            put("107/4", "Special/106/NA/NA/NA/NA");
            put("108/4", "Special/107/NA/NA/NA/NA");
            put("109/4", "Special/108/NA/NA/NA/NA");
            put("110/4", "Special/109/NA/NA/NA/NA");
            put("111/4", "Special/110/NA/NA/NA/NA");
            put("112/4", "Special/111/NA/NA/NA/NA");
            put("113/4", "Special/112/NA/NA/NA/NA");
            put("114/4", "Special/113/NA/NA/NA/NA");
            put("115/4", "Special/114/NA/NA/NA/NA");
            put("116/4", "Special/115/NA/NA/NA/NA");
            put("117/4", "Special/116/NA/NA/NA/NA");
            put("118/4", "Special/117/NA/NA/NA/NA");
            put("119/4", "Special/118/NA/NA/NA/NA");
            put("120/4", "Special/119/NA/NA/NA/NA");
            put("121/4", "Special/120/NA/NA/NA/NA");
            put("122/4", "Special/121/NA/NA/NA/NA");
            put("123/4", "Special/122/NA/NA/NA/NA");
            put("124/4", "Special/123/NA/NA/NA/NA");
            put("125/4", "Special/124/NA/NA/NA/NA");
            put("126/4", "Special/125/NA/NA/NA/NA");
            put("127/4", "Special/126/NA/NA/NA/NA");
            put("-128/4", "Special/127/NA/NA/NA/NA");
            put("-127/4", "Special/128/NA/NA/NA/NA");
            put("-126/4", "Special/129/NA/NA/NA/NA");
            put("-125/4", "Special/130/NA/NA/NA/NA");
            put("-124/4", "Special/131/NA/NA/NA/NA");
            put("-123/4", "Special/132/NA/NA/NA/NA");
            put("-122/4", "Special/133/NA/NA/NA/NA");
            put("-121/4", "Special/134/NA/NA/NA/NA");
            put("-120/4", "Special/135/NA/NA/NA/NA");
            put("-119/4", "Special/136/NA/NA/NA/NA");
            put("-118/4", "Special/137/NA/NA/NA/NA");
            put("-117/4", "Special/138/NA/NA/NA/NA");
            put("-116/4", "Special/139/NA/NA/NA/NA");
            put("-115/4", "Special/140/NA/NA/NA/NA");
            put("-114/4", "Special/141/NA/NA/NA/NA");
            put("-113/4", "Special/142/NA/NA/NA/NA");
            put("-112/4", "Special/143/NA/NA/NA/NA");
            put("-111/4", "Special/144/NA/NA/NA/NA");
            put("-110/4", "Special/145/NA/NA/NA/NA");
            put("-109/4", "Special/146/NA/NA/NA/NA");
            put("-108/4", "Special/147/NA/NA/NA/NA");
            put("-107/4", "Special/148/NA/NA/NA/NA");
            put("-106/4", "Special/149/NA/NA/NA/NA");
            put("-105/4", "Special/150/NA/NA/NA/NA");
            put("-104/4", "Special/151/NA/NA/NA/NA");
            put("-103/4", "Special/152/NA/NA/NA/NA");
            put("-102/4", "Special/153/NA/NA/NA/NA");
            put("-101/4", "Special/154/NA/NA/NA/NA");
            put("-100/4", "Special/155/NA/NA/NA/NA");
            put("-99/4", "Special/156/NA/NA/NA/NA");
            put("-98/4", "Special/157/NA/NA/NA/NA");
            put("-97/4", "Special/158/NA/NA/NA/NA");
            put("-96/4", "Special/159/NA/NA/NA/NA");
            put("-95/4", "Special/160/NA/NA/NA/NA");
            put("-94/4", "Special/161/NA/NA/NA/NA");
            put("-93/4", "Special/162/NA/NA/NA/NA");
            put("-92/4", "Special/163/NA/NA/NA/NA");
            put("-91/4", "Special/164/NA/NA/NA/NA");
            put("-90/4", "Special/165/NA/NA/NA/NA");
            put("-89/4", "Special/166/NA/NA/NA/NA");
            put("-88/4", "Special/167/NA/NA/NA/NA");
            put("-87/4", "Special/168/NA/NA/NA/NA");
            put("-86/4", "Special/169/NA/NA/NA/NA");
            put("-85/4", "Special/170/NA/NA/NA/NA");
            put("-84/4", "Special/171/NA/NA/NA/NA");
            put("-83/4", "Special/172/NA/NA/NA/NA");
            put("-82/4", "Special/173/NA/NA/NA/NA");
            put("-81/4", "Special/174/NA/NA/NA/NA");
            put("-80/4", "Special/175/NA/NA/NA/NA");
            put("-79/4", "Special/176/NA/NA/NA/NA");
            put("-78/4", "Special/177/NA/NA/NA/NA");
            put("-77/4", "Special/178/NA/NA/NA/NA");
            put("-76/4", "Special/179/NA/NA/NA/NA");
            put("-75/4", "Special/180/NA/NA/NA/NA");
            put("-74/4", "Special/181/NA/NA/NA/NA");
            put("-73/4", "Special/182/NA/NA/NA/NA");
            put("-72/4", "Special/183/NA/NA/NA/NA");
            put("-71/4", "Special/184/NA/NA/NA/NA");
            put("-70/4", "Special/185/NA/NA/NA/NA");
            put("-69/4", "Special/186/NA/NA/NA/NA");
            put("-68/4", "Special/187/NA/NA/NA/NA");
            put("-67/4", "Special/188/NA/NA/NA/NA");
            put("-66/4", "Special/189/NA/NA/NA/NA");
            put("-65/4", "Special/190/NA/NA/NA/NA");
            put("-64/4", "Special/191/NA/NA/NA/NA");
            put("-63/4", "Special/192/NA/NA/NA/NA");
            put("-62/4", "Special/193/NA/NA/NA/NA");
            put("-61/4", "Special/194/NA/NA/NA/NA");
            put("-60/4", "Special/195/NA/NA/NA/NA");
            put("-59/4", "Special/196/NA/NA/NA/NA");
            put("-58/4", "Special/197/NA/NA/NA/NA");
            put("-57/4", "Special/198/NA/NA/NA/NA");
            put("-56/4", "Special/199/NA/NA/NA/NA");
            put("-55/4", "Special/200/NA/NA/NA/NA");
            put("-54/4", "Special/201/NA/NA/NA/NA");
            put("-53/4", "Special/202/NA/NA/NA/NA");
            put("-52/4", "Special/203/NA/NA/NA/NA");
            put("-51/4", "Special/204/NA/NA/NA/NA");
            put("-50/4", "Special/205/NA/NA/NA/NA");
            put("-49/4", "Special/206/NA/NA/NA/NA");
            put("-48/4", "Special/207/NA/NA/NA/NA");
            put("-47/4", "Special/208/NA/NA/NA/NA");
            put("-46/4", "Special/209/NA/NA/NA/NA");
            put("-45/4", "Special/210/NA/NA/NA/NA");
            put("-44/4", "Special/211/NA/NA/NA/NA");
            put("-43/4", "Special/212/NA/NA/NA/NA");
            put("-42/4", "Special/213/NA/NA/NA/NA");
            put("-41/4", "Special/214/NA/NA/NA/NA");
            put("-40/4", "Special/215/NA/NA/NA/NA");
            put("-39/4", "Special/216/NA/NA/NA/NA");
            put("-38/4", "Special/217/NA/NA/NA/NA");
            put("-37/4", "Special/218/NA/NA/NA/NA");
            put("-36/4", "Special/219/NA/NA/NA/NA");
            put("-35/4", "Special/220/NA/NA/NA/NA");
            put("-34/4", "Special/221/NA/NA/NA/NA");
            put("-33/4", "Special/222/NA/NA/NA/NA");
            put("-32/4", "Special/223/NA/NA/NA/NA");
            put("-31/4", "Special/224/NA/NA/NA/NA");
            put("-30/4", "Special/225/NA/NA/NA/NA");
            put("-29/4", "Special/226/NA/NA/NA/NA");
            put("-28/4", "Special/227/NA/NA/NA/NA");
            put("-27/4", "Special/228/NA/NA/NA/NA");
            put("-26/4", "Special/229/NA/NA/NA/NA");
            put("-25/4", "Special/230/NA/NA/NA/NA");
            put("-24/4", "Special/231/NA/NA/NA/NA");
            put("-23/4", "Special/232/NA/NA/NA/NA");
            put("-22/4", "Special/233/NA/NA/NA/NA");
            put("-21/4", "Special/234/NA/NA/NA/NA");
            put("-20/4", "Special/235/NA/NA/NA/NA");
            put("-19/4", "Special/236/NA/NA/NA/NA");
            put("-18/4", "Special/237/NA/NA/NA/NA");
            put("-17/4", "Special/238/NA/NA/NA/NA");
            put("-16/4", "Special/239/NA/NA/NA/NA");
            put("-15/4", "Special/240/NA/NA/NA/NA");
            put("-14/4", "Special/241/NA/NA/NA/NA");
            put("-13/4", "Special/242/NA/NA/NA/NA");
            put("-12/4", "Special/243/NA/NA/NA/NA");
            put("-11/4", "Special/244/NA/NA/NA/NA");
            put("-10/4", "Special/245/NA/NA/NA/NA");
            put("-9/4", "Special/246/NA/NA/NA/NA");
            put("-8/4", "Special/247/NA/NA/NA/NA");
            put("-7/4", "Special/248/NA/NA/NA/NA");
            put("-6/4", "Special/249/NA/NA/NA/NA");
            put("-5/4", "Special/250/NA/NA/NA/NA");
            put("-4/4", "Special/251/NA/NA/NA/NA");
            put("-3/4", "Special/252/NA/NA/NA/NA");
            put("-2/4", "Special/253/NA/NA/NA/NA");
            put("-1/4", "Special/254/NA/NA/NA/NA");
            put("0/5", "Special/255/NA/NA/NA/NA");
            put("1/5", "Special/256/NA/NA/NA/NA");
            put("2/5", "Special/257/NA/NA/NA/NA");
            put("3/5", "Special/258/NA/NA/NA/NA");
            put("4/5", "Special/259/NA/NA/NA/NA");
            put("5/5", "Special/260/NA/NA/NA/NA");
            put("6/5", "Special/261/NA/NA/NA/NA");
            put("7/5", "Special/262/NA/NA/NA/NA");
            put("8/5", "Special/263/NA/NA/NA/NA");
            put("9/5", "Special/264/NA/NA/NA/NA");
            put("10/5", "Special/265/NA/NA/NA/NA");
            put("11/5", "Special/266/NA/NA/NA/NA");
            put("12/5", "Special/267/NA/NA/NA/NA");
            put("13/5", "Special/268/NA/NA/NA/NA");
            put("14/5", "Special/269/NA/NA/NA/NA");
            put("15/5", "Special/270/NA/NA/NA/NA");
            put("16/5", "Special/271/NA/NA/NA/NA");
            put("17/5", "Special/272/NA/NA/NA/NA");
            put("18/5", "Special/273/NA/NA/NA/NA");
            put("19/5", "Special/274/NA/NA/NA/NA");
            put("20/5", "Special/275/NA/NA/NA/NA");
            put("21/5", "Special/276/NA/NA/NA/NA");
            put("22/5", "Special/277/NA/NA/NA/NA");
            put("23/5", "Special/278/NA/NA/NA/NA");
            put("24/5", "Special/279/NA/NA/NA/NA");
            put("25/5", "Special/280/NA/NA/NA/NA");
            put("26/5", "Special/281/NA/NA/NA/NA");
            put("27/5", "Special/282/NA/NA/NA/NA");
            put("28/5", "Special/283/NA/NA/NA/NA");
            put("29/5", "Special/284/NA/NA/NA/NA");
            put("30/5", "Special/285/NA/NA/NA/NA");
            put("31/5", "Special/286/NA/NA/NA/NA");
            put("32/5", "Special/287/NA/NA/NA/NA");
            put("33/5", "Special/288/NA/NA/NA/NA");
            put("34/5", "Special/289/NA/NA/NA/NA");
            put("35/5", "Special/290/NA/NA/NA/NA");
            put("36/5", "Special/291/NA/NA/NA/NA");
            put("37/5", "Special/292/NA/NA/NA/NA");
            put("38/5", "Special/293/NA/NA/NA/NA");
            put("39/5", "Special/294/NA/NA/NA/NA");
            put("40/5", "Special/295/NA/NA/NA/NA");
            put("41/5", "Special/296/NA/NA/NA/NA");
            put("42/5", "Special/297/NA/NA/NA/NA");
            put("43/5", "Special/298/NA/NA/NA/NA");
            put("44/5", "Special/299/NA/NA/NA/NA");
            put("45/5", "Special/300/NA/NA/NA/NA");
            put("46/5", "Special/301/NA/NA/NA/NA");
            put("47/5", "Special/302/NA/NA/NA/NA");
            put("48/5", "Special/303/NA/NA/NA/NA");
            put("49/5", "Special/304/NA/NA/NA/NA");
            put("50/5", "Special/305/NA/NA/NA/NA");
            put("51/5", "Special/306/NA/NA/NA/NA");
            put("52/5", "Special/307/NA/NA/NA/NA");
            put("53/5", "Special/308/NA/NA/NA/NA");
            put("54/5", "Special/309/NA/NA/NA/NA");
            put("55/5", "Special/310/NA/NA/NA/NA");
            put("56/5", "Special/311/NA/NA/NA/NA");
            put("57/5", "Special/312/NA/NA/NA/NA");
            put("58/5", "Special/313/NA/NA/NA/NA");
            put("59/5", "Special/314/NA/NA/NA/NA");
            put("60/5", "Special/315/NA/NA/NA/NA");
            put("61/5", "Special/316/NA/NA/NA/NA");
            put("62/5", "Special/317/NA/NA/NA/NA");
            put("63/5", "Special/318/NA/NA/NA/NA");
            put("64/5", "Special/319/NA/NA/NA/NA");
            put("65/5", "Special/320/NA/NA/NA/NA");
            put("66/5", "Special/321/NA/NA/NA/NA");
            put("67/5", "Special/322/NA/NA/NA/NA");
            put("68/5", "Special/323/NA/NA/NA/NA");
            put("69/5", "Special/324/NA/NA/NA/NA");
            put("70/5", "Special/325/NA/NA/NA/NA");
            put("71/5", "Special/326/NA/NA/NA/NA");
            put("72/5", "Special/327/NA/NA/NA/NA");
            put("73/5", "Special/328/NA/NA/NA/NA");
            put("74/5", "Special/329/NA/NA/NA/NA");
            put("75/5", "Special/330/NA/NA/NA/NA");
            put("76/5", "Special/331/NA/NA/NA/NA");
            put("77/5", "Special/332/NA/NA/NA/NA");
            put("78/5", "Special/333/NA/NA/NA/NA");
            put("79/5", "Special/334/NA/NA/NA/NA");
            put("80/5", "Special/335/NA/NA/NA/NA");
            put("81/5", "Special/336/NA/NA/NA/NA");
            put("82/5", "Special/337/NA/NA/NA/NA");
            put("83/5", "Special/338/NA/NA/NA/NA");
            put("84/5", "Special/339/NA/NA/NA/NA");
            put("85/5", "Special/340/NA/NA/NA/NA");
            put("86/5", "Special/341/NA/NA/NA/NA");
            put("87/5", "Special/342/NA/NA/NA/NA");
            put("88/5", "Special/343/NA/NA/NA/NA");
            put("89/5", "Special/344/NA/NA/NA/NA");
            put("90/5", "Special/345/NA/NA/NA/NA");
            put("91/5", "Special/346/NA/NA/NA/NA");
            put("92/5", "Special/347/NA/NA/NA/NA");
            put("93/5", "Special/348/NA/NA/NA/NA");
            put("94/5", "Special/349/NA/NA/NA/NA");
            put("95/5", "Special/350/NA/NA/NA/NA");
            put("96/5", "Special/351/NA/NA/NA/NA");
            put("97/5", "Special/352/NA/NA/NA/NA");
            put("98/5", "Special/353/NA/NA/NA/NA");
            put("99/5", "Special/354/NA/NA/NA/NA");
            put("100/5", "Special/355/NA/NA/NA/NA");
            put("101/5", "Special/356/NA/NA/NA/NA");
            put("102/5", "Special/357/NA/NA/NA/NA");
            put("103/5", "Special/358/NA/NA/NA/NA");
            put("104/5", "Special/359/NA/NA/NA/NA");
            put("105/5", "Special/360/NA/NA/NA/NA");
            put("106/5", "Special/361/NA/NA/NA/NA");
            put("107/5", "Special/362/NA/NA/NA/NA");
            put("108/5", "Special/363/NA/NA/NA/NA");
            put("109/5", "Special/364/NA/NA/NA/NA");
            put("110/5", "Special/365/NA/NA/NA/NA");
            put("111/5", "Special/366/NA/NA/NA/NA");
            put("112/5", "Special/367/NA/NA/NA/NA");
            put("113/5", "Special/368/NA/NA/NA/NA");
            put("114/5", "Special/369/NA/NA/NA/NA");
            put("115/5", "Special/370/NA/NA/NA/NA");
            put("116/5", "Special/371/NA/NA/NA/NA");
            put("117/5", "Special/372/NA/NA/NA/NA");
            put("118/5", "Special/373/NA/NA/NA/NA");
            put("119/5", "Special/374/NA/NA/NA/NA");
            put("120/5", "Special/375/NA/NA/NA/NA");
            put("121/5", "Special/376/NA/NA/NA/NA");
            put("122/5", "Special/377/NA/NA/NA/NA");
            put("123/5", "Special/378/NA/NA/NA/NA");
            put("124/5", "Special/379/NA/NA/NA/NA");
            put("125/5", "Special/380/NA/NA/NA/NA");
            put("126/5", "Special/381/NA/NA/NA/NA");
            put("127/5", "Special/382/NA/NA/NA/NA");
            put("-128/5", "Special/383/NA/NA/NA/NA");
            put("-127/5", "Special/384/NA/NA/NA/NA");
            put("-126/5", "Special/385/NA/NA/NA/NA");
            put("-125/5", "Special/386/NA/NA/NA/NA");
            put("-124/5", "Special/387/NA/NA/NA/NA");
            put("-123/5", "Special/388/NA/NA/NA/NA");
            put("-122/5", "Special/389/NA/NA/NA/NA");
            put("-121/5", "Special/390/NA/NA/NA/NA");
            put("-120/5", "Special/391/NA/NA/NA/NA");
            put("-119/5", "Special/392/NA/NA/NA/NA");
            put("-118/5", "Special/393/NA/NA/NA/NA");
            put("-117/5", "Special/394/NA/NA/NA/NA");
            put("-116/5", "Special/395/NA/NA/NA/NA");
            put("-115/5", "Special/396/NA/NA/NA/NA");
            put("-114/5", "Special/397/NA/NA/NA/NA");
            put("-113/5", "Special/398/NA/NA/NA/NA");
            put("-112/5", "Special/399/NA/NA/NA/NA");
            put("-111/5", "Special/400/NA/NA/NA/NA");
            put("-110/5", "Special/401/NA/NA/NA/NA");
            put("-109/5", "Special/402/NA/NA/NA/NA");
            put("-108/5", "Special/403/NA/NA/NA/NA");
            put("-107/5", "Special/404/NA/NA/NA/NA");
            put("-106/5", "Special/405/NA/NA/NA/NA");
            put("-105/5", "Special/406/NA/NA/NA/NA");
            put("-104/5", "Special/407/NA/NA/NA/NA");
            put("-103/5", "Special/408/NA/NA/NA/NA");
            put("-102/5", "Special/409/NA/NA/NA/NA");
            put("-101/5", "Special/410/NA/NA/NA/NA");
            put("-100/5", "Special/411/NA/NA/NA/NA");
            put("-99/5", "Special/412/NA/NA/NA/NA");
            put("-98/5", "Special/413/NA/NA/NA/NA");
            put("-97/5", "Special/414/NA/NA/NA/NA");
            put("-96/5", "Special/415/NA/NA/NA/NA");
            put("-95/5", "Special/416/NA/NA/NA/NA");
            put("-94/5", "Special/417/NA/NA/NA/NA");
            put("-93/5", "Special/418/NA/NA/NA/NA");
            put("-92/5", "Special/419/NA/NA/NA/NA");
            put("-91/5", "Special/420/NA/NA/NA/NA");
            put("-90/5", "Special/421/NA/NA/NA/NA");
            put("-89/5", "Special/422/NA/NA/NA/NA");
            put("-88/5", "Special/423/NA/NA/NA/NA");
            put("-87/5", "Special/424/NA/NA/NA/NA");
            put("-86/5", "Special/425/NA/NA/NA/NA");
            put("-85/5", "Special/426/NA/NA/NA/NA");
            put("-84/5", "Special/427/NA/NA/NA/NA");
            put("-83/5", "Special/428/NA/NA/NA/NA");
            put("-82/5", "Special/429/NA/NA/NA/NA");
            put("-81/5", "Special/430/NA/NA/NA/NA");
            put("-80/5", "Special/431/NA/NA/NA/NA");
            put("-79/5", "Special/432/NA/NA/NA/NA");
            put("-78/5", "Special/433/NA/NA/NA/NA");
            put("-77/5", "Special/434/NA/NA/NA/NA");
            put("-76/5", "Special/435/NA/NA/NA/NA");
            put("-75/5", "Special/436/NA/NA/NA/NA");
            put("-74/5", "Special/437/NA/NA/NA/NA");
            put("-73/5", "Special/438/NA/NA/NA/NA");
            put("-72/5", "Special/439/NA/NA/NA/NA");
            put("-71/5", "Special/440/NA/NA/NA/NA");
            put("-70/5", "Special/441/NA/NA/NA/NA");
            put("-69/5", "Special/442/NA/NA/NA/NA");
            put("-68/5", "Special/443/NA/NA/NA/NA");
            put("-67/5", "Special/444/NA/NA/NA/NA");
            put("-66/5", "Special/445/NA/NA/NA/NA");
            put("-65/5", "Special/446/NA/NA/NA/NA");
            put("-64/5", "Special/447/NA/NA/NA/NA");
            put("-63/5", "Special/448/NA/NA/NA/NA");
            put("-62/5", "Special/449/NA/NA/NA/NA");
            put("-61/5", "Special/450/NA/NA/NA/NA");
            put("-60/5", "Special/451/NA/NA/NA/NA");
            put("-59/5", "Special/452/NA/NA/NA/NA");
            put("-58/5", "Special/453/NA/NA/NA/NA");
            put("-57/5", "Special/454/NA/NA/NA/NA");
            put("-56/5", "Special/455/NA/NA/NA/NA");
            put("-55/5", "Special/456/NA/NA/NA/NA");
            put("-54/5", "Special/457/NA/NA/NA/NA");
            put("-53/5", "Special/458/NA/NA/NA/NA");
            put("-52/5", "Special/459/NA/NA/NA/NA");
            put("-51/5", "Special/460/NA/NA/NA/NA");
            put("-50/5", "Special/461/NA/NA/NA/NA");
            put("-49/5", "Special/462/NA/NA/NA/NA");
            put("-48/5", "Special/463/NA/NA/NA/NA");
            put("-47/5", "Special/464/NA/NA/NA/NA");
            put("-46/5", "Special/465/NA/NA/NA/NA");
            put("-45/5", "Special/466/NA/NA/NA/NA");
            put("-44/5", "Special/467/NA/NA/NA/NA");
            put("-43/5", "Special/468/NA/NA/NA/NA");
            put("-42/5", "Special/469/NA/NA/NA/NA");
            put("-41/5", "Special/470/NA/NA/NA/NA");
            put("-40/5", "Special/471/NA/NA/NA/NA");
            put("-39/5", "Special/472/NA/NA/NA/NA");
            put("-38/5", "Special/473/NA/NA/NA/NA");
        }
    };

    private static final Map<String, Integer> specialFacesByIndexNumber = new HashMap<String, Integer>() {
        {
            put("1/3", 3);
            put("1/4", 4);
            put("1/5", 5);
            put("1/6", 6);
            put("1/7", 8);
            put("1/9", 11);
            put("1/10", 12);
            put("1/11", 14);
            put("1/12", 16);
            put("1/13", 17);
            put("1/14", 18);
            put("1/15", 19);
            put("1/16", 20);
            put("1/19", 26);
            put("1/20", 29);
            put("1/21", 32);
            put("1/22", 33);
            put("1/24", 35);
            put("1/25", 36);
            put("1/26", 39);
            put("1/27", 40);
            put("1/28", 61);
            put("1/29", 62);
            put("1/30", 63);
            put("1/34", 75);
            put("1/36", 81);
            put("1/38", 83);
            put("1/40", 85);
            put("1/43", 88);
            put("1/45", 90);
            put("1/46", 98);
            put("1/47", 99);
            put("1/49", 101);
            put("1/50", 102);
            put("1/51", 103);
            put("1/52", 104);
            put("1/53", 105);
            put("1/54", 106);
            put("1/55", 110);
            put("1/57", 112);
            put("1/58", 117);
            put("1/59", 118);
            put("1/60", 119);
            put("1/61", 123);
            put("1/62", 124);
            put("1/63", 125);
            put("1/64", 126);
            put("1/65", 127);
            put("1/66", 128);
            put("1/67", 129);
            put("1/68", 130);
            put("1/69", 131);
            put("1/70", 132);
            put("1/71", 133);
            put("1/72", 134);
            put("1/73", 137);
            put("1/75", 142);
            put("1/76", 143);
            put("1/77", 144);
            put("1/78", 145);
            put("1/79", 146);
            put("1/80", 147);
            put("1/81", 148);
            put("1/82", 150);
            put("1/83", 151);
            put("1/84", 156);
            put("1/85", 157);
            put("1/86", 158);
            put("1/87", 161);
            put("1/88", 162);
            put("1/89", 163);
            put("1/90", 164);
            put("1/91", 165);
            put("1/92", 166);
            put("1/93", 167);
            put("1/94", 168);
            put("1/95", 169);
            put("1/96", 170);
            put("1/97", 171);
            put("1/98", 172);
            put("1/99", 173);
            put("1/100", 174);
            put("1/102", 182);
            put("1/104", 185);
            put("1/105", 189);
            put("1/106", 191);
            put("1/107", 192);
            put("1/108", 194);
            put("1/109", 195);
            put("1/110", 196);
            put("1/111", 197);
            put("1/112", 201);
            put("1/113", 202);
            put("1/114", 205);
            put("1/117", 209);
            put("1/118", 210);
            put("1/119", 211);
            put("1/120", 213);
            put("1/121", 214);
            put("1/123", 217);
            put("1/124", 218);
            put("1/125", 219);
            put("1/126", 220);
            put("1/127", 222);
            put("1/129", 227);
            put("1/131", 229);
            put("1/133", 231);
            put("1/134", 233);
            put("1/135", 234);
            put("1/138", 238);
            put("1/139", 241);
            put("1/140", 243);
            put("1/141", 244);
            put("1/142", 245);
            put("1/143", 248);
            put("1/145", 251);
            put("1/146", 252);
            put("1/147", 253);
            put("1/148", 254);
            put("1/150", 256);
            put("1/151", 257);
            put("1/152", 258);
            put("1/153", 259);
            put("1/155", 261);
            put("1/156", 262);
            put("1/158", 264);
            put("1/159", 265);
            put("1/161", 267);
            put("1/162", 270);
            put("1/163", 271);
            put("1/164", 272);
            put("1/167", 276);
            put("1/168", 277);
            put("1/169", 279);
            put("1/170", 280);
            put("1/171", 281);
            put("1/172", 282);
            put("1/173", 283);
            put("1/174", 284);
            put("1/175", 285);
            put("1/176", 286);
            put("1/177", 287);
            put("1/178", 288);
            put("1/179", 289);
            put("1/180", 290);
            put("1/181", 291);
            put("1/182", 292);
            put("1/183", 293);
            put("1/184", 294);
            put("1/185", 297);
            put("1/186", 299);
            put("1/187", 300);
            put("1/188", 302);
            put("1/189", 305);
            put("1/190", 306);
            put("1/191", 307);
            put("1/193", 311);
            put("1/194", 312);
            put("1/195", 334);
            put("1/196", 336);
            put("1/197", 340);
            put("1/198", 341);
            put("1/200", 345);
            put("1/201", 347);
            put("2/2", 5);
            put("2/3", 6);
            put("2/4", 7);
            put("2/5", 9);
            put("2/6", 11);
            put("2/7", 12);
            put("2/8", 13);
            put("2/9", 14);
            put("2/10", 16);
            put("2/11", 18);
            put("2/12", 19);
            put("2/13", 20);
            put("2/15", 22);
            put("2/16", 24);
            put("2/17", 25);
            put("2/18", 27);
            put("2/19", 30);
            put("2/21", 32);
            put("2/22", 33);
            put("2/23", 34);
            put("2/24", 35);
            put("2/25", 36);
            put("2/26", 40);
            put("2/29", 46);
            put("2/30", 47);
            put("2/33", 51);
            put("2/34", 53);
            put("2/36", 57);
            put("2/39", 60);
            put("2/42", 67);
            put("2/43", 68);
            put("2/45", 73);
            put("2/48", 76);
            put("2/50", 78);
            put("2/51", 79);
            put("2/59", 88);
            put("2/81", 110);
            put("2/82", 111);
            put("2/83", 112);
            put("2/89", 122);
            put("2/90", 123);
            put("2/91", 124);
            put("2/92", 125);
            put("2/93", 128);
            put("2/94", 129);
            put("2/95", 131);
            put("2/96", 132);
            put("2/97", 133);
            put("2/98", 134);
            put("2/99", 135);
            put("2/100", 136);
            put("2/101", 137);
            put("2/103", 139);
            put("2/104", 140);
            put("2/105", 141);
            put("2/107", 143);
            put("2/109", 145);
            put("2/110", 146);
            put("2/111", 147);
            put("2/112", 150);
            put("2/113", 151);
            put("2/114", 154);
            put("2/115", 155);
            put("2/116", 156);
            put("2/117", 157);
            put("2/118", 158);
            put("2/119", 159);
            put("2/120", 160);
            put("2/121", 161);
            put("2/122", 162);
            put("2/123", 163);
            put("2/126", 170);
            put("3/2", 2);
            put("3/3", 3);
            put("3/4", 4);
            put("3/5", 5);
            put("3/6", 6);
            put("3/7", 7);
            put("3/8", 8);
            put("3/9", 10);
            put("3/10", 13);
            put("3/11", 14);
            put("3/12", 15);
            put("3/13", 20);
            put("3/14", 21);
            put("3/15", 22);
            put("3/16", 23);
            put("3/17", 24);
            put("3/18", 28);
            put("3/19", 29);
            put("3/20", 31);
            put("3/21", 32);
            put("3/22", 33);
            put("3/23", 34);
            put("3/24", 36);
            put("3/25", 37);
            put("3/26", 41);
            put("3/27", 42);
            put("3/28", 47);
            put("3/29", 51);
            put("3/30", 52);
            put("3/32", 56);
            put("3/33", 57);
            put("3/35", 59);
            put("3/36", 60);
            put("3/37", 61);
            put("3/38", 62);
            put("3/39", 65);
            put("3/40", 66);
            put("4/1", 2);
            put("4/2", 4);
            put("4/3", 5);
            put("4/4", 7);
            put("4/5", 8);
            put("4/6", 9);
            put("4/7", 11);
            put("4/8", 13);
            put("4/9", 14);
            put("4/10", 15);
            put("4/11", 16);
            put("4/13", 19);
            put("4/14", 21);
            put("4/15", 22);
            put("4/18", 26);
            put("4/19", 33);
            put("4/20", 35);
            put("4/22", 38);
            put("4/23", 39);
            put("4/24", 40);
            put("4/25", 41);
            put("4/26", 45);
        }
    };

    private static final Map<String, Integer> specialFacesByActualNumber = new HashMap<String, Integer>() {
        {
            put("1/3", 3);
            put("1/4", 4);
            put("1/5", 5);
            put("1/6", 6);
            put("1/8", 7);
            put("1/11", 9);
            put("1/12", 10);
            put("1/14", 11);
            put("1/16", 12);
            put("1/17", 13);
            put("1/18", 14);
            put("1/19", 15);
            put("1/20", 16);
            put("1/26", 19);
            put("1/29", 20);
            put("1/32", 21);
            put("1/33", 22);
            put("1/35", 24);
            put("1/36", 25);
            put("1/39", 26);
            put("1/40", 27);
            put("1/61", 28);
            put("1/62", 29);
            put("1/63", 30);
            put("1/75", 34);
            put("1/81", 36);
            put("1/83", 38);
            put("1/85", 40);
            put("1/88", 43);
            put("1/90", 45);
            put("1/98", 46);
            put("1/99", 47);
            put("1/101", 49);
            put("1/102", 50);
            put("1/103", 51);
            put("1/104", 52);
            put("1/105", 53);
            put("1/106", 54);
            put("1/110", 55);
            put("1/112", 57);
            put("1/117", 58);
            put("1/118", 59);
            put("1/119", 60);
            put("1/123", 61);
            put("1/124", 62);
            put("1/125", 63);
            put("1/126", 64);
            put("1/127", 65);
            put("1/128", 66);
            put("1/129", 67);
            put("1/130", 68);
            put("1/131", 69);
            put("1/132", 70);
            put("1/133", 71);
            put("1/134", 72);
            put("1/137", 73);
            put("1/142", 75);
            put("1/143", 76);
            put("1/144", 77);
            put("1/145", 78);
            put("1/146", 79);
            put("1/147", 80);
            put("1/148", 81);
            put("1/150", 82);
            put("1/151", 83);
            put("1/156", 84);
            put("1/157", 85);
            put("1/158", 86);
            put("1/161", 87);
            put("1/162", 88);
            put("1/163", 89);
            put("1/164", 90);
            put("1/165", 91);
            put("1/166", 92);
            put("1/167", 93);
            put("1/168", 94);
            put("1/169", 95);
            put("1/170", 96);
            put("1/171", 97);
            put("1/172", 98);
            put("1/173", 99);
            put("1/174", 100);
            put("1/182", 102);
            put("1/185", 104);
            put("1/189", 105);
            put("1/191", 106);
            put("1/192", 107);
            put("1/194", 108);
            put("1/195", 109);
            put("1/196", 110);
            put("1/197", 111);
            put("1/201", 112);
            put("1/202", 113);
            put("1/205", 114);
            put("1/209", 117);
            put("1/210", 118);
            put("1/211", 119);
            put("1/213", 120);
            put("1/214", 121);
            put("1/217", 123);
            put("1/218", 124);
            put("1/219", 125);
            put("1/220", 126);
            put("1/222", 127);
            put("1/227", 129);
            put("1/229", 131);
            put("1/231", 133);
            put("1/233", 134);
            put("1/234", 135);
            put("1/238", 138);
            put("1/241", 139);
            put("1/243", 140);
            put("1/244", 141);
            put("1/245", 142);
            put("1/248", 143);
            put("1/251", 145);
            put("1/252", 146);
            put("1/253", 147);
            put("1/254", 148);
            put("1/256", 150);
            put("1/257", 151);
            put("1/258", 152);
            put("1/259", 153);
            put("1/261", 155);
            put("1/262", 156);
            put("1/264", 158);
            put("1/265", 159);
            put("1/267", 161);
            put("1/270", 162);
            put("1/271", 163);
            put("1/272", 164);
            put("1/276", 167);
            put("1/277", 168);
            put("1/279", 169);
            put("1/280", 170);
            put("1/281", 171);
            put("1/282", 172);
            put("1/283", 173);
            put("1/284", 174);
            put("1/285", 175);
            put("1/286", 176);
            put("1/287", 177);
            put("1/288", 178);
            put("1/289", 179);
            put("1/290", 180);
            put("1/291", 181);
            put("1/292", 182);
            put("1/293", 183);
            put("1/294", 184);
            put("1/297", 185);
            put("1/299", 186);
            put("1/300", 187);
            put("1/302", 188);
            put("1/305", 189);
            put("1/306", 190);
            put("1/307", 191);
            put("1/311", 193);
            put("1/312", 194);
            put("1/334", 195);
            put("1/336", 196);
            put("1/340", 197);
            put("1/341", 198);
            put("1/345", 200);
            put("1/347", 201);
            put("2/5", 2);
            put("2/6", 3);
            put("2/7", 4);
            put("2/9", 5);
            put("2/11", 6);
            put("2/12", 7);
            put("2/13", 8);
            put("2/14", 9);
            put("2/16", 10);
            put("2/18", 11);
            put("2/19", 12);
            put("2/20", 13);
            put("2/22", 15);
            put("2/24", 16);
            put("2/25", 17);
            put("2/27", 18);
            put("2/30", 19);
            put("2/32", 21);
            put("2/33", 22);
            put("2/34", 23);
            put("2/35", 24);
            put("2/36", 25);
            put("2/40", 26);
            put("2/46", 29);
            put("2/47", 30);
            put("2/51", 33);
            put("2/53", 34);
            put("2/57", 36);
            put("2/60", 39);
            put("2/67", 42);
            put("2/68", 43);
            put("2/73", 45);
            put("2/76", 48);
            put("2/78", 50);
            put("2/79", 51);
            put("2/88", 59);
            put("2/110", 81);
            put("2/111", 82);
            put("2/112", 83);
            put("2/122", 89);
            put("2/123", 90);
            put("2/124", 91);
            put("2/125", 92);
            put("2/128", 93);
            put("2/129", 94);
            put("2/131", 95);
            put("2/132", 96);
            put("2/133", 97);
            put("2/134", 98);
            put("2/135", 99);
            put("2/136", 100);
            put("2/137", 101);
            put("2/139", 103);
            put("2/140", 104);
            put("2/141", 105);
            put("2/143", 107);
            put("2/145", 109);
            put("2/146", 110);
            put("2/147", 111);
            put("2/150", 112);
            put("2/151", 113);
            put("2/154", 114);
            put("2/155", 115);
            put("2/156", 116);
            put("2/157", 117);
            put("2/158", 118);
            put("2/159", 119);
            put("2/160", 120);
            put("2/161", 121);
            put("2/162", 122);
            put("2/163", 123);
            put("2/170", 126);
            put("3/2", 2);
            put("3/3", 3);
            put("3/4", 4);
            put("3/5", 5);
            put("3/6", 6);
            put("3/7", 7);
            put("3/8", 8);
            put("3/10", 9);
            put("3/13", 10);
            put("3/14", 11);
            put("3/15", 12);
            put("3/20", 13);
            put("3/21", 14);
            put("3/22", 15);
            put("3/23", 16);
            put("3/24", 17);
            put("3/28", 18);
            put("3/29", 19);
            put("3/31", 20);
            put("3/32", 21);
            put("3/33", 22);
            put("3/34", 23);
            put("3/36", 24);
            put("3/37", 25);
            put("3/41", 26);
            put("3/42", 27);
            put("3/47", 28);
            put("3/51", 29);
            put("3/52", 30);
            put("3/56", 32);
            put("3/57", 33);
            put("3/59", 35);
            put("3/60", 36);
            put("3/61", 37);
            put("3/62", 38);
            put("3/65", 39);
            put("3/66", 40);
            put("4/2", 1);
            put("4/4", 2);
            put("4/5", 3);
            put("4/7", 4);
            put("4/8", 5);
            put("4/9", 6);
            put("4/11", 7);
            put("4/13", 8);
            put("4/14", 9);
            put("4/15", 10);
            put("4/16", 11);
            put("4/19", 13);
            put("4/21", 14);
            put("4/22", 15);
            put("4/26", 18);
            put("4/33", 19);
            put("4/35", 20);
            put("4/38", 22);
            put("4/39", 23);
            put("4/40", 24);
            put("4/41", 25);
            put("4/45", 26);
        }
    };

    private static final Map<String, Integer> growthTypesByLabel = new HashMap<String, Integer>() {
        {
            put("Early", 9);
            put("Early/Lasting", 51);
            put("Standard", 55);
            put("Standard/Lasting", 11);
            put("Late", 8);
            put("Late/Lasting", 10);
        }
    };

    private static final Map<Integer, String> growthTypesByValue = new HashMap<Integer, String>() {
        {
            put(-128, "Early");
            put(-127, "Standard/Lasting");
            put(-126, "Early");
            put(-125, "Standard/Lasting");
            put(-124, "Standard");
            put(-123, "Late");
            put(-122, "Late/Lasting");
            put(-121, "Standard/Lasting");
            put(-120, "Early/Lasting");
            put(-119, "Late");
            put(-118, "Standard/Lasting");
            put(-117, "Standard");
            put(-116, "Late");
            put(-115, "Early");
            put(-114, "Early/Lasting");
            put(-113, "Standard/Lasting");
            put(-112, "Standard");
            put(-111, "Standard");
            put(-110, "Early");
            put(-109, "Standard");
            put(-108, "Early/Lasting");
            put(-107, "Early");
            put(-106, "Standard");
            put(-105, "Standard/Lasting");
            put(-104, "Standard");
            put(-103, "Standard");
            put(-102, "Early");
            put(-101, "Standard");
            put(-100, "Late");
            put(-99, "Late");
            put(-97, "Standard");
            put(-96, "Standard/Lasting");
            put(-95, "Early/Lasting");
            put(-94, "Standard");
            put(-93, "Late");
            put(-92, "Early");
            put(-91, "Standard");
            put(-90, "Early");
            put(-89, "Late/Lasting");
            put(-88, "Early");
            put(-87, "Standard");
            put(-86, "Early");
            put(-85, "Late");
            put(-84, "Early/Lasting");
            put(-83, "Early/Lasting");
            put(-82, "Early");
            put(-81, "Early");
            put(-80, "Early");
            put(-79, "Standard");
            put(-78, "Early");
            put(-77, "Standard");
            put(-76, "Standard");
            put(-75, "Standard");
            put(-74, "Standard/Lasting");
            put(-73, "Standard");
            put(-72, "Late");
            put(-71, "Late");
            put(-70, "Standard/Lasting");
            put(-69, "Standard");
            put(-68, "Early");
            put(-67, "Early/Lasting");
            put(-66, "Early");
            put(-65, "Standard/Lasting");
            put(-64, "Standard");
            put(-63, "Early/Lasting");
            put(-62, "Standard");
            put(-61, "Standard/Lasting");
            put(-60, "Standard/Lasting");
            put(-59, "Late/Lasting");
            put(-58, "Standard");
            put(-57, "Late");
            put(-56, "Standard");
            put(-55, "Standard");
            put(-54, "Early/Lasting");
            put(-53, "Standard");
            put(-52, "Early/Lasting");
            put(-51, "Late/Lasting");
            put(-50, "Late");
            put(-49, "Late");
            put(-48, "Standard");
            put(-47, "Late");
            put(-46, "Early");
            put(-45, "Standard/Lasting");
            put(-44, "Early");
            put(-41, "Late");
            put(-40, "Standard/Lasting");
            put(-39, "Late");
            put(-38, "Late");
            put(-35, "Standard");
            put(-33, "Standard/Lasting");
            put(-31, "Late");
            put(-28, "Late/Lasting");
            put(-24, "Late");
            put(-23, "Late");
            put(-22, "Standard");
            put(-19, "Standard/Lasting");
            put(-14, "Early/Lasting");
            put(-13, "Standard");
            put(-12, "Early");
            put(-11, "Standard");
            put(-9, "Early/Lasting");
            put(-8, "Early");
            put(-4, "Late");
            put(1, "Standard");
            put(2, "Late");
            put(3, "Standard");
            put(4, "Standard");
            put(5, "Standard/Lasting");
            put(6, "Standard");
            put(7, "Standard/Lasting");
            put(8, "Late");
            put(9, "Early");
            put(10, "Late/Lasting");
            put(11, "Standard/Lasting");
            put(12, "Standard");
            put(13, "Standard/Lasting");
            put(14, "Standard/Lasting");
            put(15, "Standard/Lasting");
            put(16, "Standard");
            put(17, "Late");
            put(18, "Standard");
            put(19, "Standard");
            put(20, "Standard");
            put(21, "Late");
            put(22, "Late");
            put(23, "Standard/Lasting");
            put(24, "Late/Lasting");
            put(25, "Early/Lasting");
            put(26, "Standard");
            put(28, "Standard");
            put(29, "Standard");
            put(30, "Standard");
            put(31, "Standard");
            put(32, "Standard/Lasting");
            put(33, "Late");
            put(34, "Standard/Lasting");
            put(35, "Late");
            put(36, "Standard/Lasting");
            put(37, "Late");
            put(38, "Standard/Lasting");
            put(39, "Late");
            put(40, "Standard");
            put(41, "Late/Lasting");
            put(42, "Standard");
            put(43, "Late");
            put(44, "Late");
            put(46, "Late");
            put(47, "Standard/Lasting");
            put(48, "Standard/Lasting");
            put(49, "Early");
            put(51, "Early/Lasting");
            put(52, "Standard");
            put(53, "Standard");
            put(54, "Standard");
            put(55, "Standard");
            put(56, "Standard");
            put(57, "Standard");
            put(58, "Standard");
            put(59, "Standard");
            put(60, "Standard");
            put(61, "Standard");
            put(62, "Standard");
            put(63, "Standard");
            put(64, "Late");
            put(65, "Standard");
            put(66, "Late");
            put(67, "Early/Lasting");
            put(68, "Standard");
            put(69, "Standard");
            put(70, "Early/Lasting");
            put(71, "Late");
            put(72, "Standard");
            put(73, "Standard");
            put(74, "Standard");
            put(75, "Standard");
            put(76, "Standard");
            put(77, "Standard");
            put(78, "Standard");
            put(79, "Standard");
            put(80, "Standard");
            put(81, "Standard/Lasting");
            put(82, "Early/Lasting");
            put(83, "Standard");
            put(84, "Standard");
            put(85, "Standard");
            put(86, "Late");
            put(87, "Standard");
            put(88, "Early/Lasting");
            put(89, "Early/Lasting");
            put(90, "Late/Lasting");
            put(91, "Standard/Lasting");
            put(92, "Standard/Lasting");
            put(93, "Late");
            put(94, "Standard");
            put(95, "Standard");
            put(96, "Standard/Lasting");
            put(97, "Standard");
            put(98, "Late");
            put(99, "Standard/Lasting");
            put(100, "Standard");
            put(101, "Standard");
            put(102, "Late");
            put(103, "Late");
            put(104, "Standard");
            put(105, "Early");
            put(106, "Standard");
            put(107, "Early/Lasting");
            put(108, "Standard/Lasting");
            put(109, "Late");
            put(110, "Standard/Lasting");
            put(111, "Late");
            put(112, "Standard");
            put(113, "Standard/Lasting");
            put(114, "Standard");
            put(115, "Standard");
            put(116, "Standard");
            put(117, "Late");
            put(118, "Early");
            put(119, "Standard");
            put(120, "Standard");
            put(121, "Early/Lasting");
            put(122, "Standard");
            put(123, "Early");
            put(124, "Early");
            put(125, "Standard");
            put(126, "Early");
            put(127, "Early/Lasting");
        }
    };

    private static final String defaultGrowthTypeLabel = "Standard";

    private static final int defaultGrowthTypeVal = 55;

    private static final String[] growthTypeLabelsAll = { "Early", "Early/Lasting", "Standard", "Standard/Lasting",
            "Late", "Late/Lasting", };

    private static final int[] growthTypeValuesAll = { -128, -127, -126, -125, -124, -123, -122, -121, -120, -119, -118,
            -117, -116, -115, -114, -113, -112, -111, -110, -109, -108, -107, -106, -105, -104, -103, -102, -101, -100,
            -99, -97, -96, -95, -94, -93, -92, -91, -90, -89, -88, -87, -86, -85, -84, -83, -82, -81, -80, -79, -78,
            -77, -76, -75, -74, -73, -72, -71, -70, -69, -68, -67, -66, -65, -64, -63, -62, -61, -60, -59, -58, -57,
            -56, -55, -54, -53, -52, -51, -50, -49, -48, -47, -46, -45, -44, -41, -40, -39, -38, -35, -33, -31, -28,
            -24, -23, -22, -19, -14, -13, -12, -11, -9, -8, -4, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43,
            44, 46, 47, 48, 49, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72,
            73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99,
            100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
            121, 122, 123, 124, 125, 126, 127 };
    private static final int[] growthTypeValuesEarly = { -128, -126, -115, -110, -107, -102, -92, -90, -88, -86, -82,
            -81, -80, -78, -68, -66, -46, -44, -12, -8, 9, 49, 105, 118, 123, 124, 126 };
    private static final int[] growthTypeValuesEarlyLasting = { -120, -114, -108, -95, -84, -83, -67, -63, -54, -52,
            -14, -9, 25, 51, 67, 70, 82, 88, 89, 107, 121, 127 };
    private static final int[] growthTypeValuesStandard = { -124, -117, -112, -111, -109, -106, -104, -103, -101, -97,
            -94, -91, -87, -79, -77, -76, -75, -73, -69, -64, -62, -58, -56, -55, -53, -48, -35, -22, -13, -11, 0, 1, 3,
            4, 6, 12, 16, 18, 19, 20, 26, 28, 29, 30, 31, 40, 42, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 65,
            68, 69, 72, 73, 74, 75, 76, 77, 78, 79, 80, 83, 84, 85, 87, 94, 95, 97, 100, 101, 104, 106, 112, 114, 115,
            116, 119, 120, 122, 125 };
    private static final int[] growthTypeValuesStandardLasting = { -127, -125, -121, -118, -113, -105, -96, -74, -70,
            -65, -61, -60, -45, -40, -33, -19, 5, 7, 11, 13, 14, 15, 23, 32, 34, 36, 38, 47, 48, 81, 91, 92, 96, 99,
            108, 110, 113 };
    private static final int[] growthTypeValuesLate = { -123, -119, -116, -100, -99, -93, -85, -72, -71, -57, -50, -49,
            -47, -41, -39, -38, -31, -24, -23, -4, 2, 8, 17, 21, 22, 33, 35, 37, 39, 43, 44, 46, 64, 66, 71, 86, 93, 98,
            102, 103, 109, 111, 117 };
    private static final int[] growthTypeValuesLateLasting = { -122, -89, -59, -51, -28, 10, 24, 41, 90 };

    private static final Map<String, Integer> eyeTypesByLabel = new HashMap<String, Integer>() {
        {
            put("1", 0);
            put("2", 8);
            put("3", 16);
            put("4", 24);
            put("5", 32);
            put("6", 40);
            put("7", 48);
            put("8", 56);
            put("9", 64);
            put("10", 72);
            put("11", 80);
            put("12", 88);
            put("13", 96);
            put("14", 104);
            put("15", 112);
            put("16", 120);
            put("17", -128);
            put("18", -120);
            put("19", -112);
            put("20", -104);
            put("21", -96);
            put("22", -88);
            put("23", -80);
            put("24", -72);
            put("25", -64);
            put("26", -56);
            put("27", -48);
            put("28", -40);
            put("29", -32);
            put("30", -24);
            put("31", -16);
            put("32", -8);
        }
    };

    private static final Map<String, Integer> neckWarmerBaseHairCodeOffsetByLabel = new HashMap<String, Integer>() {
        {
            put("N", 0);
            put("Y", -128);
        }
    };

    private static final Map<Integer, String> undershortsColorByValue = new HashMap<Integer, String>() {
        {
            put(0, "White");
            put(1, "Black");
            put(2, "Red");
            put(3, "Blue");
            put(4, "Yellow");
            put(5, "Green");
            put(6, "Purple");
            put(7, "Same");
        }
    };

    private static final Map<Integer, String> braceletTypeByValue = new HashMap<Integer, String>() {
        {
            put(0, "None");
            put(1, "Left");
            put(2, "Right");
            put(3, "Both");
        }
    };

    /**
     * @return the wristbandVals
     */
    public static int[] getWristbandVals() {
        return wristbandVals;
    }

    public static Map<String, Integer> getRegisteredPositionByLabel() {
        return registeredPositionByLabel;
    }

    public static Map<Integer, String> getRegisteredPositionByValue() {
        return registeredPositionByValue;
    }

    public static int[] getGrowthTypeValuesLateLasting() {
        return growthTypeValuesLateLasting;
    }

    public static int[] getGrowthTypeValuesLate() {
        return growthTypeValuesLate;
    }

    public static int[] getGrowthTypeValuesStandardLasting() {
        return growthTypeValuesStandardLasting;
    }

    public static int[] getGrowthTypeValuesStandard() {
        return growthTypeValuesStandard;
    }

    public static int[] getGrowthTypeValuesEarlyLasting() {
        return growthTypeValuesEarlyLasting;
    }

    public static int[] getGrowthTypeValuesEarly() {
        return growthTypeValuesEarly;
    }

    public static int[] getGrowthTypeValuesAll() {
        return growthTypeValuesAll;
    }

    public static String[] getGrowthTypeLabelsAll() {
        return growthTypeLabelsAll;
    }

    public static int getDefaultGrowthTypeVal() {
        return defaultGrowthTypeVal;
    }

    public static String getDefaultGrowthTypeLabel() {
        return defaultGrowthTypeLabel;
    }

    public static Map<Integer, String> getGrowthTypesByValue() {
        return growthTypesByValue;
    }

    public static Map<String, Integer> getGrowthTypesByLabel() {
        return growthTypesByLabel;
    }

    public static Map<String, Integer> getSpecialFacesByActualNumber() {
        return specialFacesByActualNumber;
    }

    public static Map<String, Integer> getSpecialFacesByIndexNumber() {
        return specialFacesByIndexNumber;
    }

    public static Map<Integer, String> getEyeColor2TypesByValue() {
        return eyeColor2TypesByValue;
    }

    public static Map<String, Integer> getEyeColor2TypesByLabel() {
        return eyeColor2TypesByLabel;
    }

    public static Map<Integer, String> getGlassesNecklaceOptsByValue() {
        return glassesNecklaceOptsByValue;
    }

    public static Map<String, Integer> getGlassesNecklaceOptsByLabel() {
        return glassesNecklaceOptsByLabel;
    }

    public static Map<String, String> getHairTypesByKey() {
        return hairTypesByKey;
    }

    public static Map<String, String> getHairTypesByLabel() {
        return hairTypesByLabel;
    }

    public static Map<String, Integer> getHeadHeightOptsByLabel() {
        return headHeightOptsByLabel;
    }

    public static Map<Integer, String> getHeadHeightOptsByValue() {
        return headHeightOptsByValue;
    }

    /**
     * @return the footFavSideOptsByLabelFavSideVal
     */
    public static Map<String, Integer> getFootFavSideOptsByLabelFavSideVal() {
        return footFavSideOptsByLabelFavSideVal;
    }

    /**
     * @return the footFavSideOptsByLabelFootVal
     */
    public static Map<String, Integer> getFootFavSideOptsByLabelFootVal() {
        return footFavSideOptsByLabelFootVal;
    }

    /**
     * @return the bytesFactor
     */
    public static int getBytesFactor() {
        return bytesFactor;
    }

    /**
     * @return the singlePhysicalOptsSettingMaxValue
     */
    public static int getSinglePhysicalOptsSettingMaxValue() {
        return singlePhysicalOptsSettingMaxValue;
    }

    /**
     * @return the singlePhysicalOptsSettingMinValue
     */
    public static int getSinglePhysicalOptsSettingMinValue() {
        return singlePhysicalOptsSettingMinValue;
    }

    /**
     * @return the physicalLinkedOptsByValue
     */
    public static Map<Integer, String> getPhysicalLinkedOptsByValue() {
        return physicalLinkedOptsByValue;
    }

    /**
     * @return the physicalLinkedOptsByLabel
     */
    public static Map<String, Integer> getPhysicalLinkedOptsByLabel() {
        return physicalLinkedOptsByLabel;
    }

    /**
     * @return the physicalOptsByValue
     */
    public static Map<Integer, String> getPhysicalOptsByValue() {
        return physicalOptsByValue;
    }

    /**
     * @return the physicalOptsByLabel
     */
    public static Map<String, Integer> getPhysicalOptsByLabel() {
        return physicalOptsByLabel;
    }

    /**
     * @return the wristbandLabels
     */
    public static String[] getWristbandLabels() {
        return wristbandLabels;
    }

    /**
     * @return the wristbandOptsByLabel
     */
    public static Map<String, Integer> getWristbandOptsByLabel() {
        return wristbandOptsByLabel;
    }

    /**
     * @return the wristbandOptsByValue
     */
    public static Map<Integer, String> getWristbandOptsByValue() {
        return wristbandOptsByValue;
    }

        /**
     * @return the faceTypesByValue
     */
    public static Map<Integer, String> getFaceTypesByValue() {
        return faceTypesByValue;
    }

    /**
     * @return the faceTypesByLabel
     */
    public static Map<String, Integer> getFaceTypesByLabel() {
        return faceTypesByLabel;
    }

    public static String getFacialHairCapLabel(int facialHairCapValue) {
        int topByteValue = 128;
        int facialHairCode = 0;
        int highestNegativeValue = -12;
        int highestPositiveValue = 116;
        String facialHairCapLabel = "???";

        if (facialHairCapValue == 0){
            facialHairCapLabel = "N/N";
        }
        else if (facialHairCapValue == -128){
            facialHairCapLabel = "N/Y";
        }
        else if (facialHairCapValue < 0 && facialHairCapValue <= highestNegativeValue){
            facialHairCode = topByteValue + facialHairCapValue;
            facialHairCapLabel = facialHairCode + "/Y";
        }
        else if (facialHairCapValue >= 0 && facialHairCapValue <= highestPositiveValue){
            facialHairCapLabel = facialHairCapValue + "/N";
        }

        return facialHairCapLabel;
    }

    public static int getFacialHairCapValue(String facialHairCapLabel) {
        int facialHairCapValue = 0;
        if (facialHairCapLabel != "N/N"){
            int topByteValue = -128;

            String[] facialHairCapLabels = facialHairCapLabel.split("/");
            String facialHairLabel = facialHairCapLabels[0];
            String capLabel = facialHairCapLabels[1];

            int facialHairNo = 0;

            if (!facialHairLabel.equals("N")){
                facialHairNo = Integer.parseInt(facialHairLabel);
            }

            if (facialHairNo >= 0 && facialHairNo <= 116){
                if (capLabel.equals("Y")){
                    facialHairCapValue = facialHairNo + topByteValue;
                }
                else if (capLabel.equals("N")){
                    facialHairCapValue = facialHairNo;
                }
            }
        }
        return facialHairCapValue;
    }
    
    public static String getHairColorTypeHairPatternEyeColor1Label(int hairPatternEyeColor1Value) {
        int byteCount = 63;
        int negativeByteDiff = 129;

        int hairPatternColor = 1;
        int eyeColor1 = 1;

        String hairColorType = "Pattern";

        if (hairPatternEyeColor1Value >= 0 && hairPatternEyeColor1Value <= 61){
            hairPatternColor = hairPatternEyeColor1Value + 1;
        }
        else if (hairPatternEyeColor1Value >= 64 && hairPatternEyeColor1Value <= 125){
            eyeColor1 = 2;
            hairPatternColor = hairPatternEyeColor1Value - byteCount;
        }
        else if (hairPatternEyeColor1Value <= -67 && hairPatternEyeColor1Value >= -128){
            eyeColor1 = 3;
            hairPatternColor = hairPatternEyeColor1Value + negativeByteDiff;
        }
        else {
            if (hairPatternEyeColor1Value == 62){
                hairColorType = "RGB";
            }
            else if (hairPatternEyeColor1Value == 126){
                hairColorType = "RGB";
                eyeColor1 = 2;
            }
            else if (hairPatternEyeColor1Value == -66){
                hairColorType = "RGB";
                eyeColor1 = 3;
            }
        }

        String hairPatternEyeColor1Label = hairColorType + "/" + hairPatternColor + "/" + eyeColor1;

        return hairPatternEyeColor1Label;
    }

    public static int getHairColorTypeHairPatternEyeColor1Value(String hairPatternEyeColor1Label) {
        int hairPatternEyeColor1Value = 0;
        int byteCount = 63;
        int negativeByteDiff = -129;

        String[] hairPatternEyeColor1Labels = hairPatternEyeColor1Label.split("/");
        String hairColorType = hairPatternEyeColor1Labels[0];
        int hairPatternColor = Integer.parseInt(hairPatternEyeColor1Labels[1]);
        int eyeColor1 = Integer.parseInt(hairPatternEyeColor1Labels[2]);

        if (hairColorType.equals("Pattern")){
            if (eyeColor1 == 1){
                hairPatternEyeColor1Value = hairPatternColor - 1;
            }
            else if (eyeColor1 == 2){
                hairPatternEyeColor1Value = hairPatternColor + byteCount;
            }
            else if (eyeColor1 == 3){
                hairPatternEyeColor1Value = negativeByteDiff + hairPatternColor;
            }
        }
        else if (hairColorType.equals("RGB")) {
            if (eyeColor1 == 1){
                hairPatternEyeColor1Value = 62;
            }
            else if (eyeColor1 == 2){
                hairPatternEyeColor1Value = 126;
            }
            else if (eyeColor1 == 3){
                hairPatternEyeColor1Value = -66;
            }
        }

        return hairPatternEyeColor1Value;
    }

    public static String getSleeveLengthFacialHairColorLabel(int sleeveLengthFacialHairColorValue) {
        String sleeveLengthLabel = "Auto";
        String facialHairColorLabel = "Same";
        int facialHairColor = 0;

        int byteCount = 63;
        int negativeByteDiff = 129;
        
        if (sleeveLengthFacialHairColorValue >= 0 && sleeveLengthFacialHairColorValue <= 61){
            facialHairColor = sleeveLengthFacialHairColorValue + 1;
        }
        else if (sleeveLengthFacialHairColorValue >= 64 && sleeveLengthFacialHairColorValue <= 126){
            sleeveLengthLabel = "Short";
            if (sleeveLengthFacialHairColorValue != 126){
                facialHairColor = sleeveLengthFacialHairColorValue - byteCount;
            }
        }
        else if (sleeveLengthFacialHairColorValue >= -128 && sleeveLengthFacialHairColorValue <= -66){
            sleeveLengthLabel = "Long";
            if (sleeveLengthFacialHairColorValue != -66){
                facialHairColor =  sleeveLengthFacialHairColorValue + negativeByteDiff;
                
            }
        }

        if (facialHairColor != 0){
            facialHairColorLabel = Integer.toString(facialHairColor);
        }

        String sleeveLengthFacialHairColorLabel = sleeveLengthLabel + "/" + facialHairColorLabel;

        return sleeveLengthFacialHairColorLabel;
    }

    public static int getSleeveLengthFacialHairColorValue(String sleeveLengthFacialHairColorLabel) {
        int sleeveLengthFacialHairColorValue = 62;

        String[] sleeveLengthFacialHairColorLabels = sleeveLengthFacialHairColorLabel.split("/");
        String sleeveLengthLabel = sleeveLengthFacialHairColorLabels[0];
        String facialHairColorLabel = sleeveLengthFacialHairColorLabels[1];

        int byteCount = 63;
        int negativeByteDiff = -129;

        if (facialHairColorLabel.equals("Same")){
            if (sleeveLengthLabel.equals("Short")){
                sleeveLengthFacialHairColorValue = 126;
            }
            else if (sleeveLengthLabel.equals("Long")){
                sleeveLengthFacialHairColorValue = -66;
            }
        }
        else {
            int facialHairColorNo = Integer.parseInt(facialHairColorLabel);
            if (sleeveLengthLabel.equals("Auto")){
                sleeveLengthFacialHairColorValue = facialHairColorNo - 1;
            }
            else if (sleeveLengthLabel.equals("Short")){
                sleeveLengthFacialHairColorValue = facialHairColorNo + byteCount;
            }
            else if (sleeveLengthLabel.equals("Long")){
                sleeveLengthFacialHairColorValue = facialHairColorNo + negativeByteDiff;
            }
        }


        return sleeveLengthFacialHairColorValue;
    }

    //TODO: Clean up/simplify nose, jaw, head position and chin methods code
    
    public static String getNoseTypeLabel(int noseTypeValue) {
        String noseTypeLabel = "???";

        if ((noseTypeValue >= -128 && noseTypeValue <= -121) || (noseTypeValue >= -64 && noseTypeValue <= -57)
                || (noseTypeValue >= 0 && noseTypeValue <= 7) || (noseTypeValue >= 64 && noseTypeValue <= 71)) {
            noseTypeLabel = "1";
        }

        else if ((noseTypeValue >= -120 && noseTypeValue <= -113) || (noseTypeValue >= -56 && noseTypeValue <= -49)
                || (noseTypeValue >= 8 && noseTypeValue <= 15) || (noseTypeValue >= 72 && noseTypeValue <= 79)) {
            noseTypeLabel = "2";
        }

        else if ((noseTypeValue >= -112 && noseTypeValue <= -105) || (noseTypeValue >= -48 && noseTypeValue <= -41)
                || (noseTypeValue >= 16 && noseTypeValue <= 23) || (noseTypeValue >= 80 && noseTypeValue <= 87)) {
            noseTypeLabel = "3";
        }

        else if ((noseTypeValue >= -104 && noseTypeValue <= -97) || (noseTypeValue >= -40 && noseTypeValue <= -33)
                || (noseTypeValue >= 24 && noseTypeValue <= 31) || (noseTypeValue >= 88 && noseTypeValue <= 95)) {
            noseTypeLabel = "4";
        }

        else if ((noseTypeValue >= -96 && noseTypeValue <= -89) || (noseTypeValue >= -32 && noseTypeValue <= -25)
                || (noseTypeValue >= 32 && noseTypeValue <= 39) || (noseTypeValue >= 96 && noseTypeValue <= 103)) {
            noseTypeLabel = "5";
        }

        else if ((noseTypeValue >= -88 && noseTypeValue <= -81) || (noseTypeValue >= -24 && noseTypeValue <= -17)
                || (noseTypeValue >= 40 && noseTypeValue <= 47) || (noseTypeValue >= 104 && noseTypeValue <= 111)) {
            noseTypeLabel = "6";
        }

        else if ((noseTypeValue >= -80 && noseTypeValue <= -73) || (noseTypeValue >= -16 && noseTypeValue <= -9)
                || (noseTypeValue >= 48 && noseTypeValue <= 55) || (noseTypeValue >= 112 && noseTypeValue <= 119)) {
            noseTypeLabel = "7";
        }

        else if ((noseTypeValue >= -72 && noseTypeValue <= -65) || (noseTypeValue >= -8 && noseTypeValue <= -1)
                || (noseTypeValue >= 56 && noseTypeValue <= 63) || (noseTypeValue >= 120 && noseTypeValue <= 127)) {
            noseTypeLabel = "8";
        }

        return noseTypeLabel;
    }

    public static String getJawTypeLabel(int jawTypeValue) {
        String jawTypeLabel = "???";

        if ((jawTypeValue >= -128 && jawTypeValue <= -113) || (jawTypeValue >= 0 && jawTypeValue <= 15)) {
            jawTypeLabel = "1";
        }

        else if ((jawTypeValue >= -112 && jawTypeValue <= -97) || (jawTypeValue >= 16 && jawTypeValue <= 31)) {
            jawTypeLabel = "2";
        }

        else if ((jawTypeValue >= -96 && jawTypeValue <= -81) || (jawTypeValue >= 32 && jawTypeValue <= 47)) {
            jawTypeLabel = "3";
        }

        else if ((jawTypeValue >= -80 && jawTypeValue <= -65) || (jawTypeValue >= 48 && jawTypeValue <= 63)) {
            jawTypeLabel = "4";
        }

        else if ((jawTypeValue >= -64 && jawTypeValue <= -49) || (jawTypeValue >= 64 && jawTypeValue <= 79)) {
            jawTypeLabel = "5";
        }

        else if ((jawTypeValue >= -48 && jawTypeValue <= -33) || (jawTypeValue >= 80 && jawTypeValue <= 95)) {
            jawTypeLabel = "6";
        }

        else if ((jawTypeValue >= -32 && jawTypeValue <= -17) || (jawTypeValue >= 96 && jawTypeValue <= 111)) {
            jawTypeLabel = "7";
        }

        else if ((jawTypeValue >= -16 && jawTypeValue <= -1) || (jawTypeValue >= 112 && jawTypeValue <= 127)) {
            jawTypeLabel = "8";
        }

        return jawTypeLabel;
    }

    public static String getHeadPositionLabel(int headPositionValue) {
        String headPositionLabel = "???";

        if (headPositionValue >= -32 && headPositionValue <= -1) {
            headPositionLabel = "-4";
        }

        else if (headPositionValue >= -64 && headPositionValue <= -33) {
            headPositionLabel = "-3";
        }

        else if (headPositionValue >= -96 && headPositionValue <= -65) {
            headPositionLabel = "-2";
        }

        else if (headPositionValue >= -128 && headPositionValue <= -97) {
            headPositionLabel = "-1";
        }

        else if (headPositionValue >= 96 && headPositionValue <= 127) {
            headPositionLabel = "0";
        }

        else if (headPositionValue >= 64 && headPositionValue <= 95) {
            headPositionLabel = "1";
        }

        else if (headPositionValue >= 32 && headPositionValue <= 63) {
            headPositionLabel = "2";
        }

        else if (headPositionValue >= 0 && headPositionValue <= 31) {
            headPositionLabel = "3";
        }

        return headPositionLabel;
    }

    public static String getNoseHeightLabel(int noseHeightValue) {
        String noseHeightLabel = "???";

        if (noseHeightValue == -114 || noseHeightValue == -113 || noseHeightValue == -98 || noseHeightValue == -97
                || noseHeightValue == -82 || noseHeightValue == -81 || noseHeightValue == -66 || noseHeightValue == -65
                || noseHeightValue == -50 || noseHeightValue == -49 || noseHeightValue == -34 || noseHeightValue == -33
                || noseHeightValue == -18 || noseHeightValue == -17 || noseHeightValue == -2 || noseHeightValue == -1
                || noseHeightValue == 14 || noseHeightValue == 15 || noseHeightValue == 30 || noseHeightValue == 31
                || noseHeightValue == 46 || noseHeightValue == 47 || noseHeightValue == 62 || noseHeightValue == 63
                || noseHeightValue == 78 || noseHeightValue == 79 || noseHeightValue == 94 || noseHeightValue == 95
                || noseHeightValue == 110 || noseHeightValue == 111 || noseHeightValue == 126 || noseHeightValue == 127) {
            noseHeightLabel = "-4";
        }

        else if (noseHeightValue == -116 || noseHeightValue == -115 || noseHeightValue == -100 || noseHeightValue == -99
                || noseHeightValue == -84 || noseHeightValue == -83 || noseHeightValue == -68 || noseHeightValue == -67
                || noseHeightValue == -52 || noseHeightValue == -51 || noseHeightValue == -36 || noseHeightValue == -35
                || noseHeightValue == -20 || noseHeightValue == -19 || noseHeightValue == -4 || noseHeightValue == -3
                || noseHeightValue == 12 || noseHeightValue == 13 || noseHeightValue == 28 || noseHeightValue == 29
                || noseHeightValue == 44 || noseHeightValue == 45 || noseHeightValue == 60 || noseHeightValue == 61
                || noseHeightValue == 76 || noseHeightValue == 77 || noseHeightValue == 92 || noseHeightValue == 93
                || noseHeightValue == 108 || noseHeightValue == 109 || noseHeightValue == 124 || noseHeightValue == 125) {
            noseHeightLabel = "-3";
        }

        else if (noseHeightValue == -118 || noseHeightValue == -117 || noseHeightValue == -102 || noseHeightValue == -101
                || noseHeightValue == -86 || noseHeightValue == -85 || noseHeightValue == -70 || noseHeightValue == -69
                || noseHeightValue == -54 || noseHeightValue == -53 || noseHeightValue == -38 || noseHeightValue == -37
                || noseHeightValue == -22 || noseHeightValue == -21 || noseHeightValue == -6 || noseHeightValue == -5
                || noseHeightValue == 10 || noseHeightValue == 11 || noseHeightValue == 26 || noseHeightValue == 27
                || noseHeightValue == 42 || noseHeightValue == 43 || noseHeightValue == 58 || noseHeightValue == 59
                || noseHeightValue == 74 || noseHeightValue == 75 || noseHeightValue == 90 || noseHeightValue == 91
                || noseHeightValue == 106 || noseHeightValue == 107 || noseHeightValue == 122 || noseHeightValue == 123) {
            noseHeightLabel = "-2";
        }

        else if (noseHeightValue == -120 || noseHeightValue == -119 || noseHeightValue == -104 || noseHeightValue == -103
                || noseHeightValue == -88 || noseHeightValue == -87 || noseHeightValue == -72 || noseHeightValue == -71
                || noseHeightValue == -56 || noseHeightValue == -55 || noseHeightValue == -40 || noseHeightValue == -39
                || noseHeightValue == -24 || noseHeightValue == -23 || noseHeightValue == -8 || noseHeightValue == -7
                || noseHeightValue == 8 || noseHeightValue == 9 || noseHeightValue == 24 || noseHeightValue == 25
                || noseHeightValue == 40 || noseHeightValue == 41 || noseHeightValue == 56 || noseHeightValue == 57
                || noseHeightValue == 72 || noseHeightValue == 73 || noseHeightValue == 88 || noseHeightValue == 89
                || noseHeightValue == 104 || noseHeightValue == 105 || noseHeightValue == 120 || noseHeightValue == 121) {
            noseHeightLabel = "-1";
        }

        else if (noseHeightValue == -122 || noseHeightValue == -121 || noseHeightValue == -106 || noseHeightValue == -105
                || noseHeightValue == -90 || noseHeightValue == -89 || noseHeightValue == -74 || noseHeightValue == -73
                || noseHeightValue == -58 || noseHeightValue == -57 || noseHeightValue == -42 || noseHeightValue == -41
                || noseHeightValue == -26 || noseHeightValue == -25 || noseHeightValue == -10 || noseHeightValue == -9
                || noseHeightValue == 6 || noseHeightValue == 7 || noseHeightValue == 22 || noseHeightValue == 23
                || noseHeightValue == 38 || noseHeightValue == 39 || noseHeightValue == 54 || noseHeightValue == 55
                || noseHeightValue == 70 || noseHeightValue == 71 || noseHeightValue == 86 || noseHeightValue == 87
                || noseHeightValue == 102 || noseHeightValue == 103 || noseHeightValue == 118 || noseHeightValue == 119) {
            noseHeightLabel = "0";
        }

        else if (noseHeightValue == -124 || noseHeightValue == -123 || noseHeightValue == -108 || noseHeightValue == -107
                || noseHeightValue == -92 || noseHeightValue == -91 || noseHeightValue == -76 || noseHeightValue == -75
                || noseHeightValue == -60 || noseHeightValue == -59 || noseHeightValue == -44 || noseHeightValue == -43
                || noseHeightValue == -28 || noseHeightValue == -27 || noseHeightValue == -12 || noseHeightValue == -11
                || noseHeightValue == 4 || noseHeightValue == 5 || noseHeightValue == 20 || noseHeightValue == 21
                || noseHeightValue == 36 || noseHeightValue == 37 || noseHeightValue == 52 || noseHeightValue == 53
                || noseHeightValue == 68 || noseHeightValue == 69 || noseHeightValue == 84 || noseHeightValue == 85
                || noseHeightValue == 100 || noseHeightValue == 101 || noseHeightValue == 116 || noseHeightValue == 117) {
            noseHeightLabel = "1";
        }

        else if (noseHeightValue == -126 || noseHeightValue == -125 || noseHeightValue == -110 || noseHeightValue == -109
                || noseHeightValue == -94 || noseHeightValue == -93 || noseHeightValue == -78 || noseHeightValue == -77
                || noseHeightValue == -62 || noseHeightValue == -61 || noseHeightValue == -46 || noseHeightValue == -45
                || noseHeightValue == -30 || noseHeightValue == -29 || noseHeightValue == -14 || noseHeightValue == -13
                || noseHeightValue == 2 || noseHeightValue == 3 || noseHeightValue == 18 || noseHeightValue == 19
                || noseHeightValue == 34 || noseHeightValue == 35 || noseHeightValue == 50 || noseHeightValue == 51
                || noseHeightValue == 66 || noseHeightValue == 67 || noseHeightValue == 82 || noseHeightValue == 83
                || noseHeightValue == 98 || noseHeightValue == 99 || noseHeightValue == 114 || noseHeightValue == 115) {
            noseHeightLabel = "2";
        }

        else if (noseHeightValue == -128 || noseHeightValue == -127 || noseHeightValue == -112 || noseHeightValue == -111
                || noseHeightValue == -96 || noseHeightValue == -95 || noseHeightValue == -80 || noseHeightValue == -79
                || noseHeightValue == -64 || noseHeightValue == -63 || noseHeightValue == -48 || noseHeightValue == -47
                || noseHeightValue == -32 || noseHeightValue == -31 || noseHeightValue == -16 || noseHeightValue == -15
                || noseHeightValue == 0 || noseHeightValue == 1 || noseHeightValue == 16 || noseHeightValue == 17
                || noseHeightValue == 32 || noseHeightValue == 33 || noseHeightValue == 48 || noseHeightValue == 49
                || noseHeightValue == 64 || noseHeightValue == 65 || noseHeightValue == 80 || noseHeightValue == 81
                || noseHeightValue == 96 || noseHeightValue == 97 || noseHeightValue == 112 || noseHeightValue == 113) {
            noseHeightLabel = "3";
        }

        return noseHeightLabel;
    }

    public static String getNoseWidthLabel(int noseWidthValue, int noseWidthValueOther) {
        String noseWidthLabel = "???";
        Boolean otherValueEven = (noseWidthValueOther & 1) == 0;

        if (otherValueEven) {
            if (noseWidthValue >= -64 && noseWidthValue <= -1) {
                noseWidthLabel = "0";
            }
    
            else if (noseWidthValue >= -128 && noseWidthValue <= -65) {
                noseWidthLabel = "1";
            }
    
            else if (noseWidthValue >= 64 && noseWidthValue <= 127) {
                noseWidthLabel = "2";
            }
    
            else if (noseWidthValue >= 0 && noseWidthValue <= 63) {
                noseWidthLabel = "3";
            }
        }
        else {
            if (noseWidthValue >= -64 && noseWidthValue <= -1) {
                noseWidthLabel = "-4";
            }
    
            else if (noseWidthValue >= -128 && noseWidthValue <= -65) {
                noseWidthLabel = "-3";
            }
    
            else if (noseWidthValue >= 64 && noseWidthValue <= 127) {
                noseWidthLabel = "-2";
            }
    
            else if (noseWidthValue >= 0 && noseWidthValue <= 63) {
                noseWidthLabel = "-1";
            }
        }

        return noseWidthLabel;
    }

    public static String getMouthSizeLabel(int mouthSizeValue) {
        String mouthSizeLabel = "???";

        if (mouthSizeValue == -121 || mouthSizeValue == -113 || mouthSizeValue == -105 || mouthSizeValue == -97
                || mouthSizeValue == -89 || mouthSizeValue == -81 || mouthSizeValue == -73 || mouthSizeValue == -65
                || mouthSizeValue == -57 || mouthSizeValue == -49 || mouthSizeValue == -41 || mouthSizeValue == -33
                || mouthSizeValue == -25 || mouthSizeValue == -17 || mouthSizeValue == -9 || mouthSizeValue == -1
                || mouthSizeValue == 7 || mouthSizeValue == 15 || mouthSizeValue == 23 || mouthSizeValue == 31
                || mouthSizeValue == 39 || mouthSizeValue == 47 || mouthSizeValue == 55 || mouthSizeValue == 63
                || mouthSizeValue == 71 || mouthSizeValue == 79 || mouthSizeValue == 87 || mouthSizeValue == 95
                || mouthSizeValue == 103 || mouthSizeValue == 111 || mouthSizeValue == 119 || mouthSizeValue == 127) {
            mouthSizeLabel = "-4";
        }

        else if (mouthSizeValue == -122 || mouthSizeValue == -114 || mouthSizeValue == -106 || mouthSizeValue == -98
                || mouthSizeValue == -90 || mouthSizeValue == -82 || mouthSizeValue == -74 || mouthSizeValue == -66
                || mouthSizeValue == -58 || mouthSizeValue == -50 || mouthSizeValue == -42 || mouthSizeValue == -34
                || mouthSizeValue == -26 || mouthSizeValue == -18 || mouthSizeValue == -10 || mouthSizeValue == -2
                || mouthSizeValue == 6 || mouthSizeValue == 14 || mouthSizeValue == 22 || mouthSizeValue == 30
                || mouthSizeValue == 38 || mouthSizeValue == 46 || mouthSizeValue == 54 || mouthSizeValue == 62
                || mouthSizeValue == 70 || mouthSizeValue == 78 || mouthSizeValue == 86 || mouthSizeValue == 94
                || mouthSizeValue == 102 || mouthSizeValue == 110 || mouthSizeValue == 118 || mouthSizeValue == 126) {
            mouthSizeLabel = "-3";
        }

        else if (mouthSizeValue == -123 || mouthSizeValue == -115 || mouthSizeValue == -107 || mouthSizeValue == -99
                || mouthSizeValue == -91 || mouthSizeValue == -83 || mouthSizeValue == -75 || mouthSizeValue == -67
                || mouthSizeValue == -59 || mouthSizeValue == -51 || mouthSizeValue == -43 || mouthSizeValue == -35
                || mouthSizeValue == -27 || mouthSizeValue == -19 || mouthSizeValue == -11 || mouthSizeValue == -3
                || mouthSizeValue == 5 || mouthSizeValue == 13 || mouthSizeValue == 21 || mouthSizeValue == 29
                || mouthSizeValue == 37 || mouthSizeValue == 45 || mouthSizeValue == 53 || mouthSizeValue == 61
                || mouthSizeValue == 69 || mouthSizeValue == 77 || mouthSizeValue == 85 || mouthSizeValue == 93
                || mouthSizeValue == 101 || mouthSizeValue == 109 || mouthSizeValue == 117 || mouthSizeValue == 125) {
            mouthSizeLabel = "-2";
        }

        else if (mouthSizeValue == -124 || mouthSizeValue == -116 || mouthSizeValue == -108 || mouthSizeValue == -100
                || mouthSizeValue == -92 || mouthSizeValue == -84 || mouthSizeValue == -76 || mouthSizeValue == -68
                || mouthSizeValue == -60 || mouthSizeValue == -52 || mouthSizeValue == -44 || mouthSizeValue == -36
                || mouthSizeValue == -28 || mouthSizeValue == -20 || mouthSizeValue == -12 || mouthSizeValue == -4
                || mouthSizeValue == 4 || mouthSizeValue == 12 || mouthSizeValue == 20 || mouthSizeValue == 28
                || mouthSizeValue == 36 || mouthSizeValue == 44 || mouthSizeValue == 52 || mouthSizeValue == 60
                || mouthSizeValue == 68 || mouthSizeValue == 76 || mouthSizeValue == 84 || mouthSizeValue == 92
                || mouthSizeValue == 100 || mouthSizeValue == 108 || mouthSizeValue == 116 || mouthSizeValue == 124) {
            mouthSizeLabel = "-1";
        }

        else if (mouthSizeValue == -125 || mouthSizeValue == -117 || mouthSizeValue == -109 || mouthSizeValue == -101
                || mouthSizeValue == -93 || mouthSizeValue == -85 || mouthSizeValue == -77 || mouthSizeValue == -69
                || mouthSizeValue == -61 || mouthSizeValue == -53 || mouthSizeValue == -45 || mouthSizeValue == -37
                || mouthSizeValue == -29 || mouthSizeValue == -21 || mouthSizeValue == -13 || mouthSizeValue == -5
                || mouthSizeValue == 3 || mouthSizeValue == 11 || mouthSizeValue == 19 || mouthSizeValue == 27
                || mouthSizeValue == 35 || mouthSizeValue == 43 || mouthSizeValue == 51 || mouthSizeValue == 59
                || mouthSizeValue == 67 || mouthSizeValue == 75 || mouthSizeValue == 83 || mouthSizeValue == 91
                || mouthSizeValue == 99 || mouthSizeValue == 107 || mouthSizeValue == 115 || mouthSizeValue == 123) {
            mouthSizeLabel = "0";
        }

        else if (mouthSizeValue == -126 || mouthSizeValue == -118 || mouthSizeValue == -110 || mouthSizeValue == -102
                || mouthSizeValue == -94 || mouthSizeValue == -86 || mouthSizeValue == -78 || mouthSizeValue == -70
                || mouthSizeValue == -62 || mouthSizeValue == -54 || mouthSizeValue == -46 || mouthSizeValue == -38
                || mouthSizeValue == -30 || mouthSizeValue == -22 || mouthSizeValue == -14 || mouthSizeValue == -6
                || mouthSizeValue == 2 || mouthSizeValue == 10 || mouthSizeValue == 18 || mouthSizeValue == 26
                || mouthSizeValue == 34 || mouthSizeValue == 42 || mouthSizeValue == 50 || mouthSizeValue == 58
                || mouthSizeValue == 66 || mouthSizeValue == 74 || mouthSizeValue == 82 || mouthSizeValue == 90
                || mouthSizeValue == 98 || mouthSizeValue == 106 || mouthSizeValue == 114 || mouthSizeValue == 122) {
            mouthSizeLabel = "1";
        }

        else if (mouthSizeValue == -127 || mouthSizeValue == -119 || mouthSizeValue == -111 || mouthSizeValue == -103
                || mouthSizeValue == -95 || mouthSizeValue == -87 || mouthSizeValue == -79 || mouthSizeValue == -71
                || mouthSizeValue == -63 || mouthSizeValue == -55 || mouthSizeValue == -47 || mouthSizeValue == -39
                || mouthSizeValue == -31 || mouthSizeValue == -23 || mouthSizeValue == -15 || mouthSizeValue == -7
                || mouthSizeValue == 1 || mouthSizeValue == 9 || mouthSizeValue == 17 || mouthSizeValue == 25
                || mouthSizeValue == 33 || mouthSizeValue == 41 || mouthSizeValue == 49 || mouthSizeValue == 57
                || mouthSizeValue == 65 || mouthSizeValue == 73 || mouthSizeValue == 81 || mouthSizeValue == 89
                || mouthSizeValue == 97 || mouthSizeValue == 105 || mouthSizeValue == 113 || mouthSizeValue == 121) {
            mouthSizeLabel = "2";
        }

        else if (mouthSizeValue == -128 || mouthSizeValue == -120 || mouthSizeValue == -112 || mouthSizeValue == -104
                || mouthSizeValue == -96 || mouthSizeValue == -88 || mouthSizeValue == -80 || mouthSizeValue == -72
                || mouthSizeValue == -64 || mouthSizeValue == -56 || mouthSizeValue == -48 || mouthSizeValue == -40
                || mouthSizeValue == -32 || mouthSizeValue == -24 || mouthSizeValue == -16 || mouthSizeValue == -8
                || mouthSizeValue == 0 || mouthSizeValue == 8 || mouthSizeValue == 16 || mouthSizeValue == 24
                || mouthSizeValue == 32 || mouthSizeValue == 40 || mouthSizeValue == 48 || mouthSizeValue == 56
                || mouthSizeValue == 64 || mouthSizeValue == 72 || mouthSizeValue == 80 || mouthSizeValue == 88
                || mouthSizeValue == 96 || mouthSizeValue == 104 || mouthSizeValue == 112 || mouthSizeValue == 120) {
            mouthSizeLabel = "3";
        }

        return mouthSizeLabel;
    }

    public static String getChinHeightLabel(int chinHeightValueBase, int chinHeightValue) {
        String chinHeightLabel = "???";
        Boolean chinHeightValueBaseNegative = chinHeightValueBase < 0;

        if (chinHeightValue == -125 || chinHeightValue == -121 || chinHeightValue == -117 || chinHeightValue == -113
                || chinHeightValue == -109 || chinHeightValue == -105 || chinHeightValue == -101 || chinHeightValue == -97
                || chinHeightValue == -93 || chinHeightValue == -89 || chinHeightValue == -85 || chinHeightValue == -81
                || chinHeightValue == -77 || chinHeightValue == -73 || chinHeightValue == -69 || chinHeightValue == -65
                || chinHeightValue == -61 || chinHeightValue == -57 || chinHeightValue == -53 || chinHeightValue == -49
                || chinHeightValue == -45 || chinHeightValue == -41 || chinHeightValue == -37 || chinHeightValue == -33
                || chinHeightValue == -29 || chinHeightValue == -25 || chinHeightValue == -21 || chinHeightValue == -17
                || chinHeightValue == -13 || chinHeightValue == -9 || chinHeightValue == -5 || chinHeightValue == -1
                || chinHeightValue == 3 || chinHeightValue == 7 || chinHeightValue == 11 || chinHeightValue == 15
                || chinHeightValue == 19 || chinHeightValue == 23 || chinHeightValue == 27 || chinHeightValue == 31
                || chinHeightValue == 35 || chinHeightValue == 39 || chinHeightValue == 43 || chinHeightValue == 47
                || chinHeightValue == 51 || chinHeightValue == 55 || chinHeightValue == 59 || chinHeightValue == 63
                || chinHeightValue == 67 || chinHeightValue == 71 || chinHeightValue == 75 || chinHeightValue == 79
                || chinHeightValue == 83 || chinHeightValue == 87 || chinHeightValue == 91 || chinHeightValue == 95
                || chinHeightValue == 99 || chinHeightValue == 103 || chinHeightValue == 107 || chinHeightValue == 111
                || chinHeightValue == 115 || chinHeightValue == 119 || chinHeightValue == 123 || chinHeightValue == 127) {
            if (chinHeightValueBaseNegative) {
                chinHeightLabel = "-4";
            }
            else {
                chinHeightLabel = "-3";
            }
        }

        else if (chinHeightValue == -126 || chinHeightValue == -122 || chinHeightValue == -118 || chinHeightValue == -114
                || chinHeightValue == -110 || chinHeightValue == -106 || chinHeightValue == -102 || chinHeightValue == -98
                || chinHeightValue == -94 || chinHeightValue == -90 || chinHeightValue == -86 || chinHeightValue == -82
                || chinHeightValue == -78 || chinHeightValue == -74 || chinHeightValue == -70 || chinHeightValue == -66
                || chinHeightValue == -62 || chinHeightValue == -58 || chinHeightValue == -54 || chinHeightValue == -50
                || chinHeightValue == -46 || chinHeightValue == -42 || chinHeightValue == -38 || chinHeightValue == -34
                || chinHeightValue == -30 || chinHeightValue == -26 || chinHeightValue == -22 || chinHeightValue == -18
                || chinHeightValue == -14 || chinHeightValue == -10 || chinHeightValue == -6 || chinHeightValue == -2
                || chinHeightValue == 2 || chinHeightValue == 6 || chinHeightValue == 10 || chinHeightValue == 14
                || chinHeightValue == 18 || chinHeightValue == 22 || chinHeightValue == 26 || chinHeightValue == 30
                || chinHeightValue == 34 || chinHeightValue == 38 || chinHeightValue == 42 || chinHeightValue == 46
                || chinHeightValue == 50 || chinHeightValue == 54 || chinHeightValue == 58 || chinHeightValue == 62
                || chinHeightValue == 66 || chinHeightValue == 70 || chinHeightValue == 74 || chinHeightValue == 78
                || chinHeightValue == 82 || chinHeightValue == 86 || chinHeightValue == 90 || chinHeightValue == 94
                || chinHeightValue == 98 || chinHeightValue == 102 || chinHeightValue == 106 || chinHeightValue == 110
                || chinHeightValue == 114 || chinHeightValue == 118 || chinHeightValue == 122 || chinHeightValue == 126) {
            if (chinHeightValueBaseNegative) {
                chinHeightLabel = "-2";
            }
            else {
                chinHeightLabel = "-1";
            }
        }

        else if (chinHeightValue == -127 || chinHeightValue == -123 || chinHeightValue == -119 || chinHeightValue == -115
                || chinHeightValue == -111 || chinHeightValue == -107 || chinHeightValue == -103 || chinHeightValue == -99
                || chinHeightValue == -95 || chinHeightValue == -91 || chinHeightValue == -87 || chinHeightValue == -83
                || chinHeightValue == -79 || chinHeightValue == -75 || chinHeightValue == -71 || chinHeightValue == -67
                || chinHeightValue == -63 || chinHeightValue == -59 || chinHeightValue == -55 || chinHeightValue == -51
                || chinHeightValue == -47 || chinHeightValue == -43 || chinHeightValue == -39 || chinHeightValue == -35
                || chinHeightValue == -31 || chinHeightValue == -27 || chinHeightValue == -23 || chinHeightValue == -19
                || chinHeightValue == -15 || chinHeightValue == -11 || chinHeightValue == -7 || chinHeightValue == -3
                || chinHeightValue == 1 || chinHeightValue == 5 || chinHeightValue == 9 || chinHeightValue == 13
                || chinHeightValue == 17 || chinHeightValue == 21 || chinHeightValue == 25 || chinHeightValue == 29
                || chinHeightValue == 33 || chinHeightValue == 37 || chinHeightValue == 41 || chinHeightValue == 45
                || chinHeightValue == 49 || chinHeightValue == 53 || chinHeightValue == 57 || chinHeightValue == 61
                || chinHeightValue == 65 || chinHeightValue == 69 || chinHeightValue == 73 || chinHeightValue == 77
                || chinHeightValue == 81 || chinHeightValue == 85 || chinHeightValue == 89 || chinHeightValue == 93
                || chinHeightValue == 97 || chinHeightValue == 101 || chinHeightValue == 105 || chinHeightValue == 109
                || chinHeightValue == 113 || chinHeightValue == 117 || chinHeightValue == 121 || chinHeightValue == 125) {
            if (chinHeightValueBaseNegative) {
                chinHeightLabel = "0";
            }
            else {
                chinHeightLabel = "1";
            }
        }

        else if (chinHeightValue == -128 || chinHeightValue == -124 || chinHeightValue == -120 || chinHeightValue == -116
                || chinHeightValue == -112 || chinHeightValue == -108 || chinHeightValue == -104 || chinHeightValue == -100
                || chinHeightValue == -96 || chinHeightValue == -92 || chinHeightValue == -88 || chinHeightValue == -84
                || chinHeightValue == -80 || chinHeightValue == -76 || chinHeightValue == -72 || chinHeightValue == -68
                || chinHeightValue == -64 || chinHeightValue == -60 || chinHeightValue == -56 || chinHeightValue == -52
                || chinHeightValue == -48 || chinHeightValue == -44 || chinHeightValue == -40 || chinHeightValue == -36
                || chinHeightValue == -32 || chinHeightValue == -28 || chinHeightValue == -24 || chinHeightValue == -20
                || chinHeightValue == -16 || chinHeightValue == -12 || chinHeightValue == -8 || chinHeightValue == -4
                || chinHeightValue == 0 || chinHeightValue == 4 || chinHeightValue == 8 || chinHeightValue == 12
                || chinHeightValue == 16 || chinHeightValue == 20 || chinHeightValue == 24 || chinHeightValue == 28
                || chinHeightValue == 32 || chinHeightValue == 36 || chinHeightValue == 40 || chinHeightValue == 44
                || chinHeightValue == 48 || chinHeightValue == 52 || chinHeightValue == 56 || chinHeightValue == 60
                || chinHeightValue == 64 || chinHeightValue == 68 || chinHeightValue == 72 || chinHeightValue == 76
                || chinHeightValue == 80 || chinHeightValue == 84 || chinHeightValue == 88 || chinHeightValue == 92
                || chinHeightValue == 96 || chinHeightValue == 100 || chinHeightValue == 104 || chinHeightValue == 108
                || chinHeightValue == 112 || chinHeightValue == 116 || chinHeightValue == 120 || chinHeightValue == 124) {
            if (chinHeightValueBaseNegative) {
                chinHeightLabel = "2";
            }
            else {
                chinHeightLabel = "3";
            }
        }
        
        return chinHeightLabel;
    }

    public static String getChinWidthLabel(int chinWidthValue) {
        String chinWidthLabel = "???";

        if ((chinWidthValue >= -100 && chinWidthValue <= -97) || (chinWidthValue >= -68 && chinWidthValue <= -65)
                || (chinWidthValue >= -36 && chinWidthValue <= -33) || (chinWidthValue >= -4 && chinWidthValue <= -1)
                || (chinWidthValue >= 28 && chinWidthValue <= 31) || (chinWidthValue >= 60 && chinWidthValue <= 63)
                || (chinWidthValue >= 92 && chinWidthValue <= 95) || (chinWidthValue >= 124 && chinWidthValue <= 127)) {
            chinWidthLabel = "-4";
        }

        else if ((chinWidthValue >= -104 && chinWidthValue <= -101) || (chinWidthValue >= -72 && chinWidthValue <= -69)
                || (chinWidthValue >= -40 && chinWidthValue <= -37) || (chinWidthValue >= -8 && chinWidthValue <= -5)
                || (chinWidthValue >= 24 && chinWidthValue <= 27) || (chinWidthValue >= 56 && chinWidthValue <= 59)
                || (chinWidthValue >= 88 && chinWidthValue <= 91) || (chinWidthValue >= 120 && chinWidthValue <= 123)) {
            chinWidthLabel = "-3";
        }

        else if ((chinWidthValue >= -108 && chinWidthValue <= -105) || (chinWidthValue >= -76 && chinWidthValue <= -73)
                || (chinWidthValue >= -44 && chinWidthValue <= -41) || (chinWidthValue >= -12 && chinWidthValue <= -9)
                || (chinWidthValue >= 20 && chinWidthValue <= 23) || (chinWidthValue >= 52 && chinWidthValue <= 55)
                || (chinWidthValue >= 84 && chinWidthValue <= 87) || (chinWidthValue >= 116 && chinWidthValue <= 119)) {
            chinWidthLabel = "-2";
        }

        else if ((chinWidthValue >= -112 && chinWidthValue <= -109) || (chinWidthValue >= -80 && chinWidthValue <= -77)
                || (chinWidthValue >= -48 && chinWidthValue <= -45) || (chinWidthValue >= -16 && chinWidthValue <= -13)
                || (chinWidthValue >= 16 && chinWidthValue <= 19) || (chinWidthValue >= 48 && chinWidthValue <= 51)
                || (chinWidthValue >= 80 && chinWidthValue <= 83) || (chinWidthValue >= 112 && chinWidthValue <= 115)) {
            chinWidthLabel = "-1";
        }

        else if ((chinWidthValue >= -116 && chinWidthValue <= -113) || (chinWidthValue >= -84 && chinWidthValue <= -81)
                || (chinWidthValue >= -52 && chinWidthValue <= -49) || (chinWidthValue >= -20 && chinWidthValue <= -17)
                || (chinWidthValue >= 12 && chinWidthValue <= 15) || (chinWidthValue >= 44 && chinWidthValue <= 47)
                || (chinWidthValue >= 76 && chinWidthValue <= 79) || (chinWidthValue >= 108 && chinWidthValue <= 111)) {
            chinWidthLabel = "0";
        }

        else if ((chinWidthValue >= -120 && chinWidthValue <= -117) || (chinWidthValue >= -88 && chinWidthValue <= -85)
                || (chinWidthValue >= -56 && chinWidthValue <= -53) || (chinWidthValue >= -24 && chinWidthValue <= -21)
                || (chinWidthValue >= 8 && chinWidthValue <= 11) || (chinWidthValue >= 40 && chinWidthValue <= 43)
                || (chinWidthValue >= 72 && chinWidthValue <= 75) || (chinWidthValue >= 104 && chinWidthValue <= 107)) {
            chinWidthLabel = "1";
        }

        else if ((chinWidthValue >= -124 && chinWidthValue <= -121) || (chinWidthValue >= -92 && chinWidthValue <= -89)
                || (chinWidthValue >= -60 && chinWidthValue <= -57) || (chinWidthValue >= -28 && chinWidthValue <= -25)
                || (chinWidthValue >= 4 && chinWidthValue <= 7) || (chinWidthValue >= 36 && chinWidthValue <= 39)
                || (chinWidthValue >= 68 && chinWidthValue <= 71) || (chinWidthValue >= 100 && chinWidthValue <= 103)) {
            chinWidthLabel = "2";
        }

        else if ((chinWidthValue >= -128 && chinWidthValue <= -125) || (chinWidthValue >= -96 && chinWidthValue <= -93)
                || (chinWidthValue >= -64 && chinWidthValue <= -61) || (chinWidthValue >= -32 && chinWidthValue <= -29)
                || (chinWidthValue >= 0 && chinWidthValue <= 3) || (chinWidthValue >= 32 && chinWidthValue <= 35)
                || (chinWidthValue >= 64 && chinWidthValue <= 67) || (chinWidthValue >= 96 && chinWidthValue <= 99)) {
            chinWidthLabel = "3";
        }
        
        return chinWidthLabel;
    }

    private static final Map<Integer, Integer> val121NoseWidthBase = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 64);
            put(1, -128);
            put(0, -64);
            put(-1, 0);
            put(-2, 64);
            put(-3, -128);
            put(-4, -64);
        }
    };

    private static final Map<Integer, Integer> val121NoseTypeOffset = new HashMap<Integer, Integer>() {
        {
            put(1, 0);
            put(2, 8);
            put(3, 16);
            put(4, 24);
            put(5, 32);
            put(6, 40);
            put(7, 48);
            put(8, 56);
        }
    };

    private static final Map<Integer, Integer> val121MouthSizeOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 1);
            put(1, 2);
            put(0, 3);
            put(-1, 4);
            put(-2, 5);
            put(-3, 6);
            put(-4, 7);
        }
    };

    private static final Map<Integer, List<Integer>> val122JawTypeBases = new HashMap<Integer, List<Integer>>() {
        {
            put(1, new ArrayList<Integer>() {
                {
                    add(-128);
                    add(0);
                }
            });
            put(2, new ArrayList<Integer>() {
                {
                    add(-112);
                    add(16);
                }
            });
            put(3, new ArrayList<Integer>() {
                {
                    add(-96);
                    add(32);
                }
            });
            put(4, new ArrayList<Integer>() {
                {
                    add(-80);
                    add(48);
                }
            });
            put(5, new ArrayList<Integer>() {
                {
                    add(-64);
                    add(64);
                }
            });
            put(6, new ArrayList<Integer>() {
                {
                    add(-48);
                    add(80);
                }
            });
            put(7, new ArrayList<Integer>() {
                {
                    add(-32);
                    add(96);
                }
            });
            put(8, new ArrayList<Integer>() {
                {
                    add(-16);
                    add(112);
                }
            });
        }
    };

    private static final Map<Integer, Integer> val122NoseHeightOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 2);
            put(1, 4);
            put(0, 6);
            put(-1, 8);
            put(-2, 10);
            put(-3, 12);
            put(-4, 14);
        }
    };

    private static final Map<Integer, Integer> val123HeadPositionBase = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 32);
            put(1, 64);
            put(0, 96);
            put(-1, -128);
            put(-2, -96);
            put(-3, -64);
            put(-4, -32);
        }
    };

    private static final Map<Integer, Integer> val123ChinWidthOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 4);
            put(1, 8);
            put(0, 12);
            put(-1, 16);
            put(-2, 20);
            put(-3, 24);
            put(-4, 28);
        }
    };

    private static final Map<Integer, Integer> val123ChinHeightOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 0);
            put(1, 1);
            put(0, 1);
            put(-1, 2);
            put(-2, 2);
            put(-3, 3);
            put(-4, 3);
        }
    };

    //121, 122, 123 values for Head Position, Nose Type, Nose Height, Nose Width, Mouth Size, Jaw Type, Chin Height, Chin Width
    public static int[] getHead1Values(String headPositionLabel, String noseTypeLabel, String noseHeightLabel,
            String noseWidthLabel, String mouthSizeLabel, String jawTypeLabel, String chinHeightLabel,
            String chinWidthLabel) {

        int headPosition = Integer.parseInt(headPositionLabel);
        int noseType = Integer.parseInt(noseTypeLabel);
        int noseHeight = Integer.parseInt(noseHeightLabel);
        int noseWidth = Integer.parseInt(noseWidthLabel);
        int mouthSize = Integer.parseInt(mouthSizeLabel);
        int jawType = Integer.parseInt(jawTypeLabel);
        int chinHeight = Integer.parseInt(chinHeightLabel);
        int chinWidth = Integer.parseInt(chinWidthLabel);

        int val121 = val121NoseWidthBase.get(noseWidth);
        val121 += val121NoseTypeOffset.get(noseType);
        val121 += val121MouthSizeOffset.get(mouthSize);

        List<Integer> jawTypeBases = val122JawTypeBases.get(jawType);

        Boolean chinHeightEven = (chinHeight & 1) == 0;
        int chinHeightOffset = chinHeightEven ? 0:1;
        int jawTypeBase = jawTypeBases.get(chinHeightOffset);

        Boolean noseWidthPositive = noseWidth >= 0;
        int noseWidthPositiveOffset = noseWidthPositive ? 0:1;

        int noseHeightOffset = val122NoseHeightOffset.get(noseHeight) + noseWidthPositiveOffset;
        int val122 = jawTypeBase + noseHeightOffset;

        int val123 = val123HeadPositionBase.get(headPosition);
        val123 += val123ChinWidthOffset.get(chinWidth);
        val123 += val123ChinHeightOffset.get(chinHeight);

        int[] head1Values = {val121, val122, val123};
        
        return head1Values;
    }

    public static String getCheekTypeLabel(int cheekTypeValue) {
        String cheekTypeLabel = "???";

        if ((cheekTypeValue >= -128 && cheekTypeValue <= -125) || (cheekTypeValue >= -96 && cheekTypeValue <= -93)
                || (cheekTypeValue >= -64 && cheekTypeValue <= -61) || (cheekTypeValue >= -32 && cheekTypeValue <= -29)
                || (cheekTypeValue >= 0 && cheekTypeValue <= 3) || (cheekTypeValue >= 32 && cheekTypeValue <= 35)
                || (cheekTypeValue >= 64 && cheekTypeValue <= 67) || (cheekTypeValue >= 96 && cheekTypeValue <= 99)) {
            cheekTypeLabel = "1";
        }

        if ((cheekTypeValue >= -124 && cheekTypeValue <= -121) || (cheekTypeValue >= -92 && cheekTypeValue <= -89)
                || (cheekTypeValue >= -60 && cheekTypeValue <= -57) || (cheekTypeValue >= -28 && cheekTypeValue <= -25)
                || (cheekTypeValue >= 4 && cheekTypeValue <= 7) || (cheekTypeValue >= 36 && cheekTypeValue <= 39)
                || (cheekTypeValue >= 68 && cheekTypeValue <= 71) || (cheekTypeValue >= 100 && cheekTypeValue <= 103)) {
            cheekTypeLabel = "2";
        }

        if ((cheekTypeValue >= -120 && cheekTypeValue <= -117) || (cheekTypeValue >= -88 && cheekTypeValue <= -85)
                || (cheekTypeValue >= -56 && cheekTypeValue <= -53) || (cheekTypeValue >= -24 && cheekTypeValue <= -21)
                || (cheekTypeValue >= 8 && cheekTypeValue <= 11) || (cheekTypeValue >= 40 && cheekTypeValue <= 43)
                || (cheekTypeValue >= 72 && cheekTypeValue <= 75) || (cheekTypeValue >= 104 && cheekTypeValue <= 107)) {
            cheekTypeLabel = "3";
        }

        if ((cheekTypeValue >= -116 && cheekTypeValue <= -113) || (cheekTypeValue >= -84 && cheekTypeValue <= -81)
                || (cheekTypeValue >= -52 && cheekTypeValue <= -49) || (cheekTypeValue >= -20 && cheekTypeValue <= -17)
                || (cheekTypeValue >= 12 && cheekTypeValue <= 15) || (cheekTypeValue >= 44 && cheekTypeValue <= 47)
                || (cheekTypeValue >= 76 && cheekTypeValue <= 79) || (cheekTypeValue >= 108 && cheekTypeValue <= 111)) {
            cheekTypeLabel = "4";
        }

        if ((cheekTypeValue >= -112 && cheekTypeValue <= -109) || (cheekTypeValue >= -80 && cheekTypeValue <= -77)
                || (cheekTypeValue >= -48 && cheekTypeValue <= -45) || (cheekTypeValue >= -16 && cheekTypeValue <= -13)
                || (cheekTypeValue >= 16 && cheekTypeValue <= 19) || (cheekTypeValue >= 48 && cheekTypeValue <= 51)
                || (cheekTypeValue >= 80 && cheekTypeValue <= 83) || (cheekTypeValue >= 112 && cheekTypeValue <= 115)) {
            cheekTypeLabel = "5";
        }

        if ((cheekTypeValue >= -108 && cheekTypeValue <= -105) || (cheekTypeValue >= -76 && cheekTypeValue <= -73)
                || (cheekTypeValue >= -44 && cheekTypeValue <= -41) || (cheekTypeValue >= -12 && cheekTypeValue <= -9)
                || (cheekTypeValue >= 20 && cheekTypeValue <= 23) || (cheekTypeValue >= 52 && cheekTypeValue <= 55)
                || (cheekTypeValue >= 84 && cheekTypeValue <= 87) || (cheekTypeValue >= 116 && cheekTypeValue <= 119)) {
            cheekTypeLabel = "6";
        }

        if ((cheekTypeValue >= -104 && cheekTypeValue <= -101) || (cheekTypeValue >= -72 && cheekTypeValue <= -69)
                || (cheekTypeValue >= -40 && cheekTypeValue <= -37) || (cheekTypeValue >= -8 && cheekTypeValue <= -5)
                || (cheekTypeValue >= 24 && cheekTypeValue <= 27) || (cheekTypeValue >= 56 && cheekTypeValue <= 59)
                || (cheekTypeValue >= 88 && cheekTypeValue <= 91) || (cheekTypeValue >= 120 && cheekTypeValue <= 123)) {
            cheekTypeLabel = "7";
        }

        if ((cheekTypeValue >= -100 && cheekTypeValue <= -97) || (cheekTypeValue >= -68 && cheekTypeValue <= -65)
                || (cheekTypeValue >= -36 && cheekTypeValue <= -33) || (cheekTypeValue >= -4 && cheekTypeValue <= -1)
                || (cheekTypeValue >= 28 && cheekTypeValue <= 31) || (cheekTypeValue >= 60 && cheekTypeValue <= 63)
                || (cheekTypeValue >= 92 && cheekTypeValue <= 95) || (cheekTypeValue >= 124 && cheekTypeValue <= 127)) {
            cheekTypeLabel = "8";
        }

        return cheekTypeLabel;
    }

    public static String getCheekShapeLabel(int cheekShapeValue) {
        String cheekShapeLabel = "???";

        if (cheekShapeValue >= -32 && cheekShapeValue <= -1) {
            cheekShapeLabel = "-4";
        }

        else if (cheekShapeValue >= -64 && cheekShapeValue <= -33) {
            cheekShapeLabel = "-3";
        }

        else if (cheekShapeValue >= -96 && cheekShapeValue <= -65) {
            cheekShapeLabel = "-2";
        }

        else if (cheekShapeValue >= -128 && cheekShapeValue <= -97) {
            cheekShapeLabel = "-1";
        }

        else if (cheekShapeValue >= 96 && cheekShapeValue <= 127) {
            cheekShapeLabel = "0";
        }

        else if (cheekShapeValue >= 64 && cheekShapeValue <= 95) {
            cheekShapeLabel = "1";
        }

        else if (cheekShapeValue >= 32 && cheekShapeValue <= 63) {
            cheekShapeLabel = "2";
        }

        else if (cheekShapeValue >= 0 && cheekShapeValue <= 31) {
            cheekShapeLabel = "3";
        }

        return cheekShapeLabel;
    }

    public static String getBrowsHeightLabel(int browsHeightValue) {
        String browsHeightLabel = "???";

        if (browsHeightValue == -114 || browsHeightValue == -113 || browsHeightValue == -98 || browsHeightValue == -97
                || browsHeightValue == -82 || browsHeightValue == -81 || browsHeightValue == -66 || browsHeightValue == -65
                || browsHeightValue == -50 || browsHeightValue == -49 || browsHeightValue == -34 || browsHeightValue == -33
                || browsHeightValue == -18 || browsHeightValue == -17 || browsHeightValue == -2 || browsHeightValue == -1
                || browsHeightValue == 14 || browsHeightValue == 15 || browsHeightValue == 30 || browsHeightValue == 31
                || browsHeightValue == 46 || browsHeightValue == 47 || browsHeightValue == 62 || browsHeightValue == 63
                || browsHeightValue == 78 || browsHeightValue == 79 || browsHeightValue == 94 || browsHeightValue == 95
                || browsHeightValue == 110 || browsHeightValue == 111 || browsHeightValue == 126 || browsHeightValue == 127) {
            browsHeightLabel = "-4";
        }

        else if (browsHeightValue == -116 || browsHeightValue == -115 || browsHeightValue == -100 || browsHeightValue == -99
                || browsHeightValue == -84 || browsHeightValue == -83 || browsHeightValue == -68 || browsHeightValue == -67
                || browsHeightValue == -52 || browsHeightValue == -51 || browsHeightValue == -36 || browsHeightValue == -35
                || browsHeightValue == -20 || browsHeightValue == -19 || browsHeightValue == -4 || browsHeightValue == -3
                || browsHeightValue == 12 || browsHeightValue == 13 || browsHeightValue == 28 || browsHeightValue == 29
                || browsHeightValue == 44 || browsHeightValue == 45 || browsHeightValue == 60 || browsHeightValue == 61
                || browsHeightValue == 76 || browsHeightValue == 77 || browsHeightValue == 92 || browsHeightValue == 93
                || browsHeightValue == 108 || browsHeightValue == 109 || browsHeightValue == 124 || browsHeightValue == 125) {
            browsHeightLabel = "-3";
        }

        else if (browsHeightValue == -118 || browsHeightValue == -117 || browsHeightValue == -102 || browsHeightValue == -101
                || browsHeightValue == -86 || browsHeightValue == -85 || browsHeightValue == -70 || browsHeightValue == -69
                || browsHeightValue == -54 || browsHeightValue == -53 || browsHeightValue == -38 || browsHeightValue == -37
                || browsHeightValue == -22 || browsHeightValue == -21 || browsHeightValue == -6 || browsHeightValue == -5
                || browsHeightValue == 10 || browsHeightValue == 11 || browsHeightValue == 26 || browsHeightValue == 27
                || browsHeightValue == 42 || browsHeightValue == 43 || browsHeightValue == 58 || browsHeightValue == 59
                || browsHeightValue == 74 || browsHeightValue == 75 || browsHeightValue == 90 || browsHeightValue == 91
                || browsHeightValue == 106 || browsHeightValue == 107 || browsHeightValue == 122 || browsHeightValue == 123) {
            browsHeightLabel = "-2";
        }

        else if (browsHeightValue == -120 || browsHeightValue == -119 || browsHeightValue == -104 || browsHeightValue == -103
                || browsHeightValue == -88 || browsHeightValue == -87 || browsHeightValue == -72 || browsHeightValue == -71
                || browsHeightValue == -56 || browsHeightValue == -55 || browsHeightValue == -40 || browsHeightValue == -39
                || browsHeightValue == -24 || browsHeightValue == -23 || browsHeightValue == -8 || browsHeightValue == -7
                || browsHeightValue == 8 || browsHeightValue == 9 || browsHeightValue == 24 || browsHeightValue == 25
                || browsHeightValue == 40 || browsHeightValue == 41 || browsHeightValue == 56 || browsHeightValue == 57
                || browsHeightValue == 72 || browsHeightValue == 73 || browsHeightValue == 88 || browsHeightValue == 89
                || browsHeightValue == 104 || browsHeightValue == 105 || browsHeightValue == 120 || browsHeightValue == 121) {
            browsHeightLabel = "-1";
        }

        else if (browsHeightValue == -122 || browsHeightValue == -121 || browsHeightValue == -106 || browsHeightValue == -105
                || browsHeightValue == -90 || browsHeightValue == -89 || browsHeightValue == -74 || browsHeightValue == -73
                || browsHeightValue == -58 || browsHeightValue == -57 || browsHeightValue == -42 || browsHeightValue == -41
                || browsHeightValue == -26 || browsHeightValue == -25 || browsHeightValue == -10 || browsHeightValue == -9
                || browsHeightValue == 6 || browsHeightValue == 7 || browsHeightValue == 22 || browsHeightValue == 23
                || browsHeightValue == 38 || browsHeightValue == 39 || browsHeightValue == 54 || browsHeightValue == 55
                || browsHeightValue == 70 || browsHeightValue == 71 || browsHeightValue == 86 || browsHeightValue == 87
                || browsHeightValue == 102 || browsHeightValue == 103 || browsHeightValue == 118 || browsHeightValue == 119) {
            browsHeightLabel = "0";
        }

        else if (browsHeightValue == -124 || browsHeightValue == -123 || browsHeightValue == -108 || browsHeightValue == -107
                || browsHeightValue == -92 || browsHeightValue == -91 || browsHeightValue == -76 || browsHeightValue == -75
                || browsHeightValue == -60 || browsHeightValue == -59 || browsHeightValue == -44 || browsHeightValue == -43
                || browsHeightValue == -28 || browsHeightValue == -27 || browsHeightValue == -12 || browsHeightValue == -11
                || browsHeightValue == 4 || browsHeightValue == 5 || browsHeightValue == 20 || browsHeightValue == 21
                || browsHeightValue == 36 || browsHeightValue == 37 || browsHeightValue == 52 || browsHeightValue == 53
                || browsHeightValue == 68 || browsHeightValue == 69 || browsHeightValue == 84 || browsHeightValue == 85
                || browsHeightValue == 100 || browsHeightValue == 101 || browsHeightValue == 116 || browsHeightValue == 117) {
            browsHeightLabel = "1";
        }

        else if (browsHeightValue == -126 || browsHeightValue == -125 || browsHeightValue == -110 || browsHeightValue == -109
                || browsHeightValue == -94 || browsHeightValue == -93 || browsHeightValue == -78 || browsHeightValue == -77
                || browsHeightValue == -62 || browsHeightValue == -61 || browsHeightValue == -46 || browsHeightValue == -45
                || browsHeightValue == -30 || browsHeightValue == -29 || browsHeightValue == -14 || browsHeightValue == -13
                || browsHeightValue == 2 || browsHeightValue == 3 || browsHeightValue == 18 || browsHeightValue == 19
                || browsHeightValue == 34 || browsHeightValue == 35 || browsHeightValue == 50 || browsHeightValue == 51
                || browsHeightValue == 66 || browsHeightValue == 67 || browsHeightValue == 82 || browsHeightValue == 83
                || browsHeightValue == 98 || browsHeightValue == 99 || browsHeightValue == 114 || browsHeightValue == 115) {
            browsHeightLabel = "2";
        }

        else if (browsHeightValue == -128 || browsHeightValue == -127 || browsHeightValue == -112 || browsHeightValue == -111
                || browsHeightValue == -96 || browsHeightValue == -95 || browsHeightValue == -80 || browsHeightValue == -79
                || browsHeightValue == -64 || browsHeightValue == -63 || browsHeightValue == -48 || browsHeightValue == -47
                || browsHeightValue == -32 || browsHeightValue == -31 || browsHeightValue == -16 || browsHeightValue == -15
                || browsHeightValue == 0 || browsHeightValue == 1 || browsHeightValue == 16 || browsHeightValue == 17
                || browsHeightValue == 32 || browsHeightValue == 33 || browsHeightValue == 48 || browsHeightValue == 49
                || browsHeightValue == 64 || browsHeightValue == 65 || browsHeightValue == 80 || browsHeightValue == 81
                || browsHeightValue == 96 || browsHeightValue == 97 || browsHeightValue == 112 || browsHeightValue == 113) {
            browsHeightLabel = "3";
        }

        return browsHeightLabel;
    }

    public static String getEyebrowSpacingLabel(int eyebrowSpacingValue) {
        String eyebrowSpacingLabel = "???";

        if ((eyebrowSpacingValue >= -128 && eyebrowSpacingValue <= -113) || (eyebrowSpacingValue >= 0 && eyebrowSpacingValue <= 15)) {
            eyebrowSpacingLabel = "3";
        }

        else if ((eyebrowSpacingValue >= -112 && eyebrowSpacingValue <= -97) || (eyebrowSpacingValue >= 16 && eyebrowSpacingValue <= 31)) {
            eyebrowSpacingLabel = "2";
        }

        else if ((eyebrowSpacingValue >= -96 && eyebrowSpacingValue <= -81) || (eyebrowSpacingValue >= 32 && eyebrowSpacingValue <= 47)) {
            eyebrowSpacingLabel = "1";
        }

        else if ((eyebrowSpacingValue >= -80 && eyebrowSpacingValue <= -65) || (eyebrowSpacingValue >= 48 && eyebrowSpacingValue <= 63)) {
            eyebrowSpacingLabel = "0";
        }

        else if ((eyebrowSpacingValue >= -64 && eyebrowSpacingValue <= -49) || (eyebrowSpacingValue >= 64 && eyebrowSpacingValue <= 79)) {
            eyebrowSpacingLabel = "-1";
        }

        else if ((eyebrowSpacingValue >= -48 && eyebrowSpacingValue <= -33) || (eyebrowSpacingValue >= 80 && eyebrowSpacingValue <= 95)) {
            eyebrowSpacingLabel = "-2";
        }

        else if ((eyebrowSpacingValue >= -32 && eyebrowSpacingValue <= -17) || (eyebrowSpacingValue >= 96 && eyebrowSpacingValue <= 111)) {
            eyebrowSpacingLabel = "-3";
        }

        else if ((eyebrowSpacingValue >= -16 && eyebrowSpacingValue <= -1) || (eyebrowSpacingValue >= 112 && eyebrowSpacingValue <= 127)) {
            eyebrowSpacingLabel = "-4";
        }

        return eyebrowSpacingLabel;
    }

    public static String getBrowsAngleLabel(int browsAngleValueBase, int browsAngleValue) {
        String browsAngleLabel = "???";
        Boolean browsAngleValueBaseNegative = browsAngleValueBase < 0;

        if (browsAngleValue == -125 || browsAngleValue == -121 || browsAngleValue == -117 || browsAngleValue == -113
                || browsAngleValue == -109 || browsAngleValue == -105 || browsAngleValue == -101 || browsAngleValue == -97
                || browsAngleValue == -93 || browsAngleValue == -89 || browsAngleValue == -85 || browsAngleValue == -81
                || browsAngleValue == -77 || browsAngleValue == -73 || browsAngleValue == -69 || browsAngleValue == -65
                || browsAngleValue == -61 || browsAngleValue == -57 || browsAngleValue == -53 || browsAngleValue == -49
                || browsAngleValue == -45 || browsAngleValue == -41 || browsAngleValue == -37 || browsAngleValue == -33
                || browsAngleValue == -29 || browsAngleValue == -25 || browsAngleValue == -21 || browsAngleValue == -17
                || browsAngleValue == -13 || browsAngleValue == -9 || browsAngleValue == -5 || browsAngleValue == -1
                || browsAngleValue == 3 || browsAngleValue == 7 || browsAngleValue == 11 || browsAngleValue == 15
                || browsAngleValue == 19 || browsAngleValue == 23 || browsAngleValue == 27 || browsAngleValue == 31
                || browsAngleValue == 35 || browsAngleValue == 39 || browsAngleValue == 43 || browsAngleValue == 47
                || browsAngleValue == 51 || browsAngleValue == 55 || browsAngleValue == 59 || browsAngleValue == 63
                || browsAngleValue == 67 || browsAngleValue == 71 || browsAngleValue == 75 || browsAngleValue == 79
                || browsAngleValue == 83 || browsAngleValue == 87 || browsAngleValue == 91 || browsAngleValue == 95
                || browsAngleValue == 99 || browsAngleValue == 103 || browsAngleValue == 107 || browsAngleValue == 111
                || browsAngleValue == 115 || browsAngleValue == 119 || browsAngleValue == 123 || browsAngleValue == 127) {
            if (browsAngleValueBaseNegative) {
                browsAngleLabel = "-4";
            }
            else {
                browsAngleLabel = "-3";
            }
        }

        else if (browsAngleValue == -126 || browsAngleValue == -122 || browsAngleValue == -118 || browsAngleValue == -114
                || browsAngleValue == -110 || browsAngleValue == -106 || browsAngleValue == -102 || browsAngleValue == -98
                || browsAngleValue == -94 || browsAngleValue == -90 || browsAngleValue == -86 || browsAngleValue == -82
                || browsAngleValue == -78 || browsAngleValue == -74 || browsAngleValue == -70 || browsAngleValue == -66
                || browsAngleValue == -62 || browsAngleValue == -58 || browsAngleValue == -54 || browsAngleValue == -50
                || browsAngleValue == -46 || browsAngleValue == -42 || browsAngleValue == -38 || browsAngleValue == -34
                || browsAngleValue == -30 || browsAngleValue == -26 || browsAngleValue == -22 || browsAngleValue == -18
                || browsAngleValue == -14 || browsAngleValue == -10 || browsAngleValue == -6 || browsAngleValue == -2
                || browsAngleValue == 2 || browsAngleValue == 6 || browsAngleValue == 10 || browsAngleValue == 14
                || browsAngleValue == 18 || browsAngleValue == 22 || browsAngleValue == 26 || browsAngleValue == 30
                || browsAngleValue == 34 || browsAngleValue == 38 || browsAngleValue == 42 || browsAngleValue == 46
                || browsAngleValue == 50 || browsAngleValue == 54 || browsAngleValue == 58 || browsAngleValue == 62
                || browsAngleValue == 66 || browsAngleValue == 70 || browsAngleValue == 74 || browsAngleValue == 78
                || browsAngleValue == 82 || browsAngleValue == 86 || browsAngleValue == 90 || browsAngleValue == 94
                || browsAngleValue == 98 || browsAngleValue == 102 || browsAngleValue == 106 || browsAngleValue == 110
                || browsAngleValue == 114 || browsAngleValue == 118 || browsAngleValue == 122 || browsAngleValue == 126) {
            if (browsAngleValueBaseNegative) {
                browsAngleLabel = "-2";
            }
            else {
                browsAngleLabel = "-1";
            }
        }

        else if (browsAngleValue == -127 || browsAngleValue == -123 || browsAngleValue == -119 || browsAngleValue == -115
                || browsAngleValue == -111 || browsAngleValue == -107 || browsAngleValue == -103 || browsAngleValue == -99
                || browsAngleValue == -95 || browsAngleValue == -91 || browsAngleValue == -87 || browsAngleValue == -83
                || browsAngleValue == -79 || browsAngleValue == -75 || browsAngleValue == -71 || browsAngleValue == -67
                || browsAngleValue == -63 || browsAngleValue == -59 || browsAngleValue == -55 || browsAngleValue == -51
                || browsAngleValue == -47 || browsAngleValue == -43 || browsAngleValue == -39 || browsAngleValue == -35
                || browsAngleValue == -31 || browsAngleValue == -27 || browsAngleValue == -23 || browsAngleValue == -19
                || browsAngleValue == -15 || browsAngleValue == -11 || browsAngleValue == -7 || browsAngleValue == -3
                || browsAngleValue == 1 || browsAngleValue == 5 || browsAngleValue == 9 || browsAngleValue == 13
                || browsAngleValue == 17 || browsAngleValue == 21 || browsAngleValue == 25 || browsAngleValue == 29
                || browsAngleValue == 33 || browsAngleValue == 37 || browsAngleValue == 41 || browsAngleValue == 45
                || browsAngleValue == 49 || browsAngleValue == 53 || browsAngleValue == 57 || browsAngleValue == 61
                || browsAngleValue == 65 || browsAngleValue == 69 || browsAngleValue == 73 || browsAngleValue == 77
                || browsAngleValue == 81 || browsAngleValue == 85 || browsAngleValue == 89 || browsAngleValue == 93
                || browsAngleValue == 97 || browsAngleValue == 101 || browsAngleValue == 105 || browsAngleValue == 109
                || browsAngleValue == 113 || browsAngleValue == 117 || browsAngleValue == 121 || browsAngleValue == 125) {
            if (browsAngleValueBaseNegative) {
                browsAngleLabel = "0";
            }
            else {
                browsAngleLabel = "1";
            }
        }

        else if (browsAngleValue == -128 || browsAngleValue == -124 || browsAngleValue == -120 || browsAngleValue == -116
                || browsAngleValue == -112 || browsAngleValue == -108 || browsAngleValue == -104 || browsAngleValue == -100
                || browsAngleValue == -96 || browsAngleValue == -92 || browsAngleValue == -88 || browsAngleValue == -84
                || browsAngleValue == -80 || browsAngleValue == -76 || browsAngleValue == -72 || browsAngleValue == -68
                || browsAngleValue == -64 || browsAngleValue == -60 || browsAngleValue == -56 || browsAngleValue == -52
                || browsAngleValue == -48 || browsAngleValue == -44 || browsAngleValue == -40 || browsAngleValue == -36
                || browsAngleValue == -32 || browsAngleValue == -28 || browsAngleValue == -24 || browsAngleValue == -20
                || browsAngleValue == -16 || browsAngleValue == -12 || browsAngleValue == -8 || browsAngleValue == -4
                || browsAngleValue == 0 || browsAngleValue == 4 || browsAngleValue == 8 || browsAngleValue == 12
                || browsAngleValue == 16 || browsAngleValue == 20 || browsAngleValue == 24 || browsAngleValue == 28
                || browsAngleValue == 32 || browsAngleValue == 36 || browsAngleValue == 40 || browsAngleValue == 44
                || browsAngleValue == 48 || browsAngleValue == 52 || browsAngleValue == 56 || browsAngleValue == 60
                || browsAngleValue == 64 || browsAngleValue == 68 || browsAngleValue == 72 || browsAngleValue == 76
                || browsAngleValue == 80 || browsAngleValue == 84 || browsAngleValue == 88 || browsAngleValue == 92
                || browsAngleValue == 96 || browsAngleValue == 100 || browsAngleValue == 104 || browsAngleValue == 108
                || browsAngleValue == 112 || browsAngleValue == 116 || browsAngleValue == 120 || browsAngleValue == 124) {
            if (browsAngleValueBaseNegative) {
                browsAngleLabel = "2";
            }
            else {
                browsAngleLabel = "3";
            }
        }
        
        return browsAngleLabel;
    }

    public static String getEyesAngleLabel(int eyesAngleValue) {
        String eyesAngleLabel = "???";

        if (eyesAngleValue == -121 || eyesAngleValue == -113 || eyesAngleValue == -105 || eyesAngleValue == -97
                || eyesAngleValue == -89 || eyesAngleValue == -81 || eyesAngleValue == -73 || eyesAngleValue == -65
                || eyesAngleValue == -57 || eyesAngleValue == -49 || eyesAngleValue == -41 || eyesAngleValue == -33
                || eyesAngleValue == -25 || eyesAngleValue == -17 || eyesAngleValue == -9 || eyesAngleValue == -1
                || eyesAngleValue == 7 || eyesAngleValue == 15 || eyesAngleValue == 23 || eyesAngleValue == 31
                || eyesAngleValue == 39 || eyesAngleValue == 47 || eyesAngleValue == 55 || eyesAngleValue == 63
                || eyesAngleValue == 71 || eyesAngleValue == 79 || eyesAngleValue == 87 || eyesAngleValue == 95
                || eyesAngleValue == 103 || eyesAngleValue == 111 || eyesAngleValue == 119 || eyesAngleValue == 127) {
            eyesAngleLabel = "-4";
        }

        else if (eyesAngleValue == -122 || eyesAngleValue == -114 || eyesAngleValue == -106 || eyesAngleValue == -98
                || eyesAngleValue == -90 || eyesAngleValue == -82 || eyesAngleValue == -74 || eyesAngleValue == -66
                || eyesAngleValue == -58 || eyesAngleValue == -50 || eyesAngleValue == -42 || eyesAngleValue == -34
                || eyesAngleValue == -26 || eyesAngleValue == -18 || eyesAngleValue == -10 || eyesAngleValue == -2
                || eyesAngleValue == 6 || eyesAngleValue == 14 || eyesAngleValue == 22 || eyesAngleValue == 30
                || eyesAngleValue == 38 || eyesAngleValue == 46 || eyesAngleValue == 54 || eyesAngleValue == 62
                || eyesAngleValue == 70 || eyesAngleValue == 78 || eyesAngleValue == 86 || eyesAngleValue == 94
                || eyesAngleValue == 102 || eyesAngleValue == 110 || eyesAngleValue == 118 || eyesAngleValue == 126) {
            eyesAngleLabel = "-3";
        }

        else if (eyesAngleValue == -123 || eyesAngleValue == -115 || eyesAngleValue == -107 || eyesAngleValue == -99
                || eyesAngleValue == -91 || eyesAngleValue == -83 || eyesAngleValue == -75 || eyesAngleValue == -67
                || eyesAngleValue == -59 || eyesAngleValue == -51 || eyesAngleValue == -43 || eyesAngleValue == -35
                || eyesAngleValue == -27 || eyesAngleValue == -19 || eyesAngleValue == -11 || eyesAngleValue == -3
                || eyesAngleValue == 5 || eyesAngleValue == 13 || eyesAngleValue == 21 || eyesAngleValue == 29
                || eyesAngleValue == 37 || eyesAngleValue == 45 || eyesAngleValue == 53 || eyesAngleValue == 61
                || eyesAngleValue == 69 || eyesAngleValue == 77 || eyesAngleValue == 85 || eyesAngleValue == 93
                || eyesAngleValue == 101 || eyesAngleValue == 109 || eyesAngleValue == 117 || eyesAngleValue == 125) {
            eyesAngleLabel = "-2";
        }

        else if (eyesAngleValue == -124 || eyesAngleValue == -116 || eyesAngleValue == -108 || eyesAngleValue == -100
                || eyesAngleValue == -92 || eyesAngleValue == -84 || eyesAngleValue == -76 || eyesAngleValue == -68
                || eyesAngleValue == -60 || eyesAngleValue == -52 || eyesAngleValue == -44 || eyesAngleValue == -36
                || eyesAngleValue == -28 || eyesAngleValue == -20 || eyesAngleValue == -12 || eyesAngleValue == -4
                || eyesAngleValue == 4 || eyesAngleValue == 12 || eyesAngleValue == 20 || eyesAngleValue == 28
                || eyesAngleValue == 36 || eyesAngleValue == 44 || eyesAngleValue == 52 || eyesAngleValue == 60
                || eyesAngleValue == 68 || eyesAngleValue == 76 || eyesAngleValue == 84 || eyesAngleValue == 92
                || eyesAngleValue == 100 || eyesAngleValue == 108 || eyesAngleValue == 116 || eyesAngleValue == 124) {
            eyesAngleLabel = "-1";
        }

        else if (eyesAngleValue == -125 || eyesAngleValue == -117 || eyesAngleValue == -109 || eyesAngleValue == -101
                || eyesAngleValue == -93 || eyesAngleValue == -85 || eyesAngleValue == -77 || eyesAngleValue == -69
                || eyesAngleValue == -61 || eyesAngleValue == -53 || eyesAngleValue == -45 || eyesAngleValue == -37
                || eyesAngleValue == -29 || eyesAngleValue == -21 || eyesAngleValue == -13 || eyesAngleValue == -5
                || eyesAngleValue == 3 || eyesAngleValue == 11 || eyesAngleValue == 19 || eyesAngleValue == 27
                || eyesAngleValue == 35 || eyesAngleValue == 43 || eyesAngleValue == 51 || eyesAngleValue == 59
                || eyesAngleValue == 67 || eyesAngleValue == 75 || eyesAngleValue == 83 || eyesAngleValue == 91
                || eyesAngleValue == 99 || eyesAngleValue == 107 || eyesAngleValue == 115 || eyesAngleValue == 123) {
            eyesAngleLabel = "0";
        }

        else if (eyesAngleValue == -126 || eyesAngleValue == -118 || eyesAngleValue == -110 || eyesAngleValue == -102
                || eyesAngleValue == -94 || eyesAngleValue == -86 || eyesAngleValue == -78 || eyesAngleValue == -70
                || eyesAngleValue == -62 || eyesAngleValue == -54 || eyesAngleValue == -46 || eyesAngleValue == -38
                || eyesAngleValue == -30 || eyesAngleValue == -22 || eyesAngleValue == -14 || eyesAngleValue == -6
                || eyesAngleValue == 2 || eyesAngleValue == 10 || eyesAngleValue == 18 || eyesAngleValue == 26
                || eyesAngleValue == 34 || eyesAngleValue == 42 || eyesAngleValue == 50 || eyesAngleValue == 58
                || eyesAngleValue == 66 || eyesAngleValue == 74 || eyesAngleValue == 82 || eyesAngleValue == 90
                || eyesAngleValue == 98 || eyesAngleValue == 106 || eyesAngleValue == 114 || eyesAngleValue == 122) {
            eyesAngleLabel = "1";
        }

        else if (eyesAngleValue == -127 || eyesAngleValue == -119 || eyesAngleValue == -111 || eyesAngleValue == -103
                || eyesAngleValue == -95 || eyesAngleValue == -87 || eyesAngleValue == -79 || eyesAngleValue == -71
                || eyesAngleValue == -63 || eyesAngleValue == -55 || eyesAngleValue == -47 || eyesAngleValue == -39
                || eyesAngleValue == -31 || eyesAngleValue == -23 || eyesAngleValue == -15 || eyesAngleValue == -7
                || eyesAngleValue == 1 || eyesAngleValue == 9 || eyesAngleValue == 17 || eyesAngleValue == 25
                || eyesAngleValue == 33 || eyesAngleValue == 41 || eyesAngleValue == 49 || eyesAngleValue == 57
                || eyesAngleValue == 65 || eyesAngleValue == 73 || eyesAngleValue == 81 || eyesAngleValue == 89
                || eyesAngleValue == 97 || eyesAngleValue == 105 || eyesAngleValue == 113 || eyesAngleValue == 121) {
            eyesAngleLabel = "2";
        }

        else if (eyesAngleValue == -128 || eyesAngleValue == -120 || eyesAngleValue == -112 || eyesAngleValue == -104
                || eyesAngleValue == -96 || eyesAngleValue == -88 || eyesAngleValue == -80 || eyesAngleValue == -72
                || eyesAngleValue == -64 || eyesAngleValue == -56 || eyesAngleValue == -48 || eyesAngleValue == -40
                || eyesAngleValue == -32 || eyesAngleValue == -24 || eyesAngleValue == -16 || eyesAngleValue == -8
                || eyesAngleValue == 0 || eyesAngleValue == 8 || eyesAngleValue == 16 || eyesAngleValue == 24
                || eyesAngleValue == 32 || eyesAngleValue == 40 || eyesAngleValue == 48 || eyesAngleValue == 56
                || eyesAngleValue == 64 || eyesAngleValue == 72 || eyesAngleValue == 80 || eyesAngleValue == 88
                || eyesAngleValue == 96 || eyesAngleValue == 104 || eyesAngleValue == 112 || eyesAngleValue == 120) {
            eyesAngleLabel = "3";
        }

        return eyesAngleLabel;
    }

    public static String getEyesLengthLabel(int eyesLengthValue) {
        String eyesLengthLabel = "???";

        if ((eyesLengthValue >= -128 && eyesLengthValue <= -121) || (eyesLengthValue >= -64 && eyesLengthValue <= -57)
                || (eyesLengthValue >= 0 && eyesLengthValue <= 7) || (eyesLengthValue >= 64 && eyesLengthValue <= 71)) {
            eyesLengthLabel = "3";
        }

        else if ((eyesLengthValue >= -120 && eyesLengthValue <= -113) || (eyesLengthValue >= -56 && eyesLengthValue <= -49)
                || (eyesLengthValue >= 8 && eyesLengthValue <= 15) || (eyesLengthValue >= 72 && eyesLengthValue <= 79)) {
            eyesLengthLabel = "2";
        }

        else if ((eyesLengthValue >= -112 && eyesLengthValue <= -105) || (eyesLengthValue >= -48 && eyesLengthValue <= -41)
                || (eyesLengthValue >= 16 && eyesLengthValue <= 23) || (eyesLengthValue >= 80 && eyesLengthValue <= 87)) {
            eyesLengthLabel = "1";
        }

        else if ((eyesLengthValue >= -104 && eyesLengthValue <= -97) || (eyesLengthValue >= -40 && eyesLengthValue <= -33)
                || (eyesLengthValue >= 24 && eyesLengthValue <= 31) || (eyesLengthValue >= 88 && eyesLengthValue <= 95)) {
            eyesLengthLabel = "0";
        }

        else if ((eyesLengthValue >= -96 && eyesLengthValue <= -89) || (eyesLengthValue >= -32 && eyesLengthValue <= -25)
                || (eyesLengthValue >= 32 && eyesLengthValue <= 39) || (eyesLengthValue >= 96 && eyesLengthValue <= 103)) {
            eyesLengthLabel = "-1";
        }

        else if ((eyesLengthValue >= -88 && eyesLengthValue <= -81) || (eyesLengthValue >= -24 && eyesLengthValue <= -17)
                || (eyesLengthValue >= 40 && eyesLengthValue <= 47) || (eyesLengthValue >= 104 && eyesLengthValue <= 111)) {
            eyesLengthLabel = "-2";
        }

        else if ((eyesLengthValue >= -80 && eyesLengthValue <= -73) || (eyesLengthValue >= -16 && eyesLengthValue <= -9)
                || (eyesLengthValue >= 48 && eyesLengthValue <= 55) || (eyesLengthValue >= 112 && eyesLengthValue <= 119)) {
            eyesLengthLabel = "-3";
        }

        else if ((eyesLengthValue >= -72 && eyesLengthValue <= -65) || (eyesLengthValue >= -8 && eyesLengthValue <= -1)
                || (eyesLengthValue >= 56 && eyesLengthValue <= 63) || (eyesLengthValue >= 120 && eyesLengthValue <= 127)) {
            eyesLengthLabel = "8";
        }

        return eyesLengthLabel;
    }

    public static String getEyesWidthLabel(int eyesWidthValue, int eyesWidthValueOther) {
        String eyesWidthLabel = "???";
        Boolean otherValueEven = (eyesWidthValueOther & 1) == 0;

        if (otherValueEven) {
            if (eyesWidthValue >= -64 && eyesWidthValue <= -1) {
                eyesWidthLabel = "0";
            }
    
            else if (eyesWidthValue >= -128 && eyesWidthValue <= -65) {
                eyesWidthLabel = "1";
            }
    
            else if (eyesWidthValue >= 64 && eyesWidthValue <= 127) {
                eyesWidthLabel = "2";
            }
    
            else if (eyesWidthValue >= 0 && eyesWidthValue <= 63) {
                eyesWidthLabel = "3";
            }
        }
        else {
            if (eyesWidthValue >= -64 && eyesWidthValue <= -1) {
                eyesWidthLabel = "-4";
            }
    
            else if (eyesWidthValue >= -128 && eyesWidthValue <= -65) {
                eyesWidthLabel = "-3";
            }
    
            else if (eyesWidthValue >= 64 && eyesWidthValue <= 127) {
                eyesWidthLabel = "-2";
            }
    
            else if (eyesWidthValue >= 0 && eyesWidthValue <= 63) {
                eyesWidthLabel = "-1";
            }
        }

        return eyesWidthLabel;
    }

    private static final Map<Integer, Integer> val117EyesWidthBase = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 64);
            put(1, -128);
            put(0, -64);
            put(-1, 0);
            put(-2, 64);
            put(-3, -128);
            put(-4, -64);
        }
    };

    private static final Map<Integer, Integer> val117EyesLengthOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 8);
            put(1, 16);
            put(0, 24);
            put(-1, 32);
            put(-2, 40);
            put(-3, 48);
            put(-4, 56);
        }
    };

    private static final Map<Integer, Integer> val117EyesAngleOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 1);
            put(1, 2);
            put(0, 3);
            put(-1, 4);
            put(-2, 5);
            put(-3, 6);
            put(-4, 7);
        }
    };

    private static final Map<Integer, Integer> val119CheekTypeOffset = new HashMap<Integer, Integer>() {
        {
            put(1, 0);
            put(2, 4);
            put(3, 8);
            put(4, 12);
            put(5, 16);
            put(6, 20);
            put(7, 24);
            put(8, 28);
        }
    };

    private static final Map<Integer, Integer> val119BrowsAngleOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 0);
            put(1, 1);
            put(0, 1);
            put(-1, 2);
            put(-2, 2);
            put(-3, 3);
            put(-4, 3);
        }
    };

    private static final Map<Integer, Integer> val119CheekShapeBase = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 32);
            put(1, 64);
            put(0, 96);
            put(-1, -128);
            put(-2, -96);
            put(-3, -64);
            put(-4, -32);
        }
    };

    private static final Map<Integer, List<Integer>> val118EyebrowSpacingBases = new HashMap<Integer, List<Integer>>() {
        {
            put(3, new ArrayList<Integer>() {
                {
                    add(-128);
                    add(0);
                }
            });
            put(2, new ArrayList<Integer>() {
                {
                    add(-112);
                    add(16);
                }
            });
            put(1, new ArrayList<Integer>() {
                {
                    add(-96);
                    add(32);
                }
            });
            put(0, new ArrayList<Integer>() {
                {
                    add(-80);
                    add(48);
                }
            });
            put(-1, new ArrayList<Integer>() {
                {
                    add(-64);
                    add(64);
                }
            });
            put(-2, new ArrayList<Integer>() {
                {
                    add(-48);
                    add(80);
                }
            });
            put(-3, new ArrayList<Integer>() {
                {
                    add(-32);
                    add(96);
                }
            });
            put(-4, new ArrayList<Integer>() {
                {
                    add(-16);
                    add(112);
                }
            });
        }
    };

    private static final Map<Integer, Integer> val118BrowsHeightOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 2);
            put(1, 4);
            put(0, 6);
            put(-1, 8);
            put(-2, 10);
            put(-3, 12);
            put(-4, 14);
        }
    };

    // 117, 118, 119 values for Cheek Type, Eyes Length, Brows Height, Eyes Width,
    // Eyes Angle, Eyebrow Spacing, Brows Angle, Cheek Shape
    public static int[] getHead2Values(String cheekTypeLabel, String eyesLengthLabel, String browsHeightLabel,
            String eyesWidthLabel, String eyesAngleLabel, String eyebrowSpacingLabel, String browsAngleLabel,
            String cheekShapeLabel) {

        int cheekType = Integer.parseInt(cheekTypeLabel);
        int eyesLength = Integer.parseInt(eyesLengthLabel);
        int browsHeight = Integer.parseInt(browsHeightLabel);
        int eyesWidth = Integer.parseInt(eyesWidthLabel);
        int eyesAngle = Integer.parseInt(eyesAngleLabel);
        int eyebrowSpacing = Integer.parseInt(eyebrowSpacingLabel);
        int browsAngle = Integer.parseInt(browsAngleLabel);
        int cheekShape = Integer.parseInt(cheekShapeLabel);
        
        int val117 = val117EyesWidthBase.get(eyesWidth);
        val117 += val117EyesLengthOffset.get(eyesLength);
        val117 += val117EyesAngleOffset.get(eyesAngle);

        List<Integer> eyebrowSpacingBases = val118EyebrowSpacingBases.get(eyebrowSpacing);

        Boolean chinHeightEven = (browsAngle & 1) == 0;
        int chinHeightOffset = chinHeightEven ? 0:1;
        int eyebrowSpacingBase = eyebrowSpacingBases.get(chinHeightOffset);

        Boolean eyesWidthPositive = eyesWidth >= 0;
        int eyesWidthPositiveOffset = eyesWidthPositive ? 0:1;

        int browsHeightOffset = val118BrowsHeightOffset.get(browsHeight) + eyesWidthPositiveOffset;
        int val118 = eyebrowSpacingBase + browsHeightOffset;

        int val119 = val119CheekShapeBase.get(cheekShape);
        val119 += val119CheekTypeOffset.get(cheekType);
        val119 += val119BrowsAngleOffset.get(browsAngle);

        int[] head2Values = {val117, val118, val119};
        
        return head2Values;
    }

    public static String getMouthTypeLabel(int mouthTypeValue) {
        String mouthTypeLabel = "???";

        if (mouthTypeValue == -128 || mouthTypeValue == -96 || mouthTypeValue == -64 || mouthTypeValue == -32
                || mouthTypeValue == 0 || mouthTypeValue == 32 || mouthTypeValue == 64 || mouthTypeValue == 96) {
            mouthTypeLabel = "1";
        }

        if (mouthTypeValue == -127 || mouthTypeValue == -95 || mouthTypeValue == -63 || mouthTypeValue == -31
                || mouthTypeValue == 1 || mouthTypeValue == 33 || mouthTypeValue == 65 || mouthTypeValue == 97) {
            mouthTypeLabel = "2";
        }

        if (mouthTypeValue == -126 || mouthTypeValue == -94 || mouthTypeValue == -62 || mouthTypeValue == -30
                || mouthTypeValue == 2 || mouthTypeValue == 34 || mouthTypeValue == 66 || mouthTypeValue == 98) {
            mouthTypeLabel = "3";
        }

        if (mouthTypeValue == -125 || mouthTypeValue == -93 || mouthTypeValue == -61 || mouthTypeValue == -29
                || mouthTypeValue == 3 || mouthTypeValue == 35 || mouthTypeValue == 67 || mouthTypeValue == 99) {
            mouthTypeLabel = "4";
        }

        if (mouthTypeValue == -124 || mouthTypeValue == -92 || mouthTypeValue == -60 || mouthTypeValue == -28
                || mouthTypeValue == 4 || mouthTypeValue == 36 || mouthTypeValue == 68 || mouthTypeValue == 100) {
            mouthTypeLabel = "5";
        }

        if (mouthTypeValue == -123 || mouthTypeValue == -91 || mouthTypeValue == -59 || mouthTypeValue == -27
                || mouthTypeValue == 5 || mouthTypeValue == 37 || mouthTypeValue == 69 || mouthTypeValue == 101) {
            mouthTypeLabel = "6";
        }

        if (mouthTypeValue == -122 || mouthTypeValue == -90 || mouthTypeValue == -58 || mouthTypeValue == -26
                || mouthTypeValue == 6 || mouthTypeValue == 38 || mouthTypeValue == 70 || mouthTypeValue == 102) {
            mouthTypeLabel = "7";
        }

        if (mouthTypeValue == -121 || mouthTypeValue == -89 || mouthTypeValue == -57 || mouthTypeValue == -25
                || mouthTypeValue == 7 || mouthTypeValue == 39 || mouthTypeValue == 71 || mouthTypeValue == 103) {
            mouthTypeLabel = "8";
        }

        if (mouthTypeValue == -120 || mouthTypeValue == -88 || mouthTypeValue == -56 || mouthTypeValue == -24
                || mouthTypeValue == 8 || mouthTypeValue == 40 || mouthTypeValue == 72 || mouthTypeValue == 104) {
            mouthTypeLabel = "9";
        }

        if (mouthTypeValue == -119 || mouthTypeValue == -87 || mouthTypeValue == -55 || mouthTypeValue == -23
                || mouthTypeValue == 9 || mouthTypeValue == 41 || mouthTypeValue == 73 || mouthTypeValue == 105) {
            mouthTypeLabel = "10";
        }

        if (mouthTypeValue == -118 || mouthTypeValue == -86 || mouthTypeValue == -54 || mouthTypeValue == -22
                || mouthTypeValue == 10 || mouthTypeValue == 42 || mouthTypeValue == 74 || mouthTypeValue == 106) {
            mouthTypeLabel = "11";
        }

        if (mouthTypeValue == -117 || mouthTypeValue == -85 || mouthTypeValue == -53 || mouthTypeValue == -21
                || mouthTypeValue == 11 || mouthTypeValue == 43 || mouthTypeValue == 75 || mouthTypeValue == 107) {
            mouthTypeLabel = "12";
        }

        if (mouthTypeValue == -116 || mouthTypeValue == -84 || mouthTypeValue == -52 || mouthTypeValue == -20
                || mouthTypeValue == 12 || mouthTypeValue == 44 || mouthTypeValue == 76 || mouthTypeValue == 108) {
            mouthTypeLabel = "13";
        }

        if (mouthTypeValue == -115 || mouthTypeValue == -83 || mouthTypeValue == -51 || mouthTypeValue == -19
                || mouthTypeValue == 13 || mouthTypeValue == 45 || mouthTypeValue == 77 || mouthTypeValue == 109) {
            mouthTypeLabel = "14";
        }

        if (mouthTypeValue == -114 || mouthTypeValue == -82 || mouthTypeValue == -50 || mouthTypeValue == -18
                || mouthTypeValue == 14 || mouthTypeValue == 46 || mouthTypeValue == 78 || mouthTypeValue == 110) {
            mouthTypeLabel = "15";
        }

        if (mouthTypeValue == -113 || mouthTypeValue == -81 || mouthTypeValue == -49 || mouthTypeValue == -17
                || mouthTypeValue == 15 || mouthTypeValue == 47 || mouthTypeValue == 79 || mouthTypeValue == 111) {
            mouthTypeLabel = "16";
        }

        if (mouthTypeValue == -112 || mouthTypeValue == -80 || mouthTypeValue == -48 || mouthTypeValue == -16
                || mouthTypeValue == 16 || mouthTypeValue == 48 || mouthTypeValue == 80 || mouthTypeValue == 112) {
            mouthTypeLabel = "17";
        }

        if (mouthTypeValue == -111 || mouthTypeValue == -79 || mouthTypeValue == -47 || mouthTypeValue == -15
                || mouthTypeValue == 17 || mouthTypeValue == 49 || mouthTypeValue == 81 || mouthTypeValue == 113) {
            mouthTypeLabel = "18";
        }

        if (mouthTypeValue == -110 || mouthTypeValue == -78 || mouthTypeValue == -46 || mouthTypeValue == -14
                || mouthTypeValue == 18 || mouthTypeValue == 50 || mouthTypeValue == 82 || mouthTypeValue == 114) {
            mouthTypeLabel = "19";
        }

        if (mouthTypeValue == -109 || mouthTypeValue == -77 || mouthTypeValue == -45 || mouthTypeValue == -13
                || mouthTypeValue == 19 || mouthTypeValue == 51 || mouthTypeValue == 83 || mouthTypeValue == 115) {
            mouthTypeLabel = "20";
        }

        return mouthTypeLabel;
    }

    public static String getMouthPositionLabel(int mouthPositionValue) {
        String mouthPositionLabel = "???";

        if (mouthPositionValue >= -32 && mouthPositionValue <= -13) {
            mouthPositionLabel = "-4";
        }

        else if (mouthPositionValue >= -64 && mouthPositionValue <= -45) {
            mouthPositionLabel = "-3";
        }

        else if (mouthPositionValue >= -96 && mouthPositionValue <= -77) {
            mouthPositionLabel = "-2";
        }

        else if (mouthPositionValue >= -128 && mouthPositionValue <= -109) {
            mouthPositionLabel = "-1";
        }

        else if (mouthPositionValue >= 96 && mouthPositionValue <= 115) {
            mouthPositionLabel = "0";
        }

        else if (mouthPositionValue >= 64 && mouthPositionValue <= 83) {
            mouthPositionLabel = "1";
        }

        else if (mouthPositionValue >= 32 && mouthPositionValue <= 51) {
            mouthPositionLabel = "2";
        }

        else if (mouthPositionValue >= 0 && mouthPositionValue <= 19) {
            mouthPositionLabel = "3";
        }

        return mouthPositionLabel;
    }

    private static final Map<Integer, Integer> mouthPositionOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 1);
            put(1, 2);
            put(0, 3);
            put(-1, 0);
            put(-2, 1);
            put(-3, 2);
            put(-4, 3);
        }
    };

    public static int getMouthTypePositionValue(String mouthTypeLabel, String mouthPositionLabel) {
        int mouthTypePositionValue = 0;
        int mouthType = Integer.parseInt(mouthTypeLabel);
        int mouthPosition = Integer.parseInt(mouthPositionLabel);
        int mouthPositionOffsetVal = mouthPositionOffset.get(mouthPosition);
        Boolean mouthPositionPositive = mouthPosition >= 0;
        int mouthTypePositionBaseValue = mouthPositionPositive ? 0 : -128;

        mouthTypePositionValue = mouthTypePositionBaseValue;
        mouthTypePositionValue += mouthPositionOffsetVal * 32;
        mouthTypePositionValue += mouthType - 1;

        return mouthTypePositionValue;
    }

    public static String getBrowsTypeLabel(int browsTypeValue) {
        String browsTypeLabel = "???";

        if (browsTypeValue == -128 || browsTypeValue == -96 || browsTypeValue == -64 || browsTypeValue == -32
                || browsTypeValue == 0 || browsTypeValue == 32 || browsTypeValue == 64 || browsTypeValue == 96) {
            browsTypeLabel = "1";
        }

        if (browsTypeValue == -127 || browsTypeValue == -95 || browsTypeValue == -63 || browsTypeValue == -31
                || browsTypeValue == 1 || browsTypeValue == 33 || browsTypeValue == 65 || browsTypeValue == 97) {
            browsTypeLabel = "2";
        }

        if (browsTypeValue == -126 || browsTypeValue == -94 || browsTypeValue == -62 || browsTypeValue == -30
                || browsTypeValue == 2 || browsTypeValue == 34 || browsTypeValue == 66 || browsTypeValue == 98) {
            browsTypeLabel = "3";
        }

        if (browsTypeValue == -125 || browsTypeValue == -93 || browsTypeValue == -61 || browsTypeValue == -29
                || browsTypeValue == 3 || browsTypeValue == 35 || browsTypeValue == 67 || browsTypeValue == 99) {
            browsTypeLabel = "4";
        }

        if (browsTypeValue == -124 || browsTypeValue == -92 || browsTypeValue == -60 || browsTypeValue == -28
                || browsTypeValue == 4 || browsTypeValue == 36 || browsTypeValue == 68 || browsTypeValue == 100) {
            browsTypeLabel = "5";
        }

        if (browsTypeValue == -123 || browsTypeValue == -91 || browsTypeValue == -59 || browsTypeValue == -27
                || browsTypeValue == 5 || browsTypeValue == 37 || browsTypeValue == 69 || browsTypeValue == 101) {
            browsTypeLabel = "6";
        }

        if (browsTypeValue == -122 || browsTypeValue == -90 || browsTypeValue == -58 || browsTypeValue == -26
                || browsTypeValue == 6 || browsTypeValue == 38 || browsTypeValue == 70 || browsTypeValue == 102) {
            browsTypeLabel = "7";
        }

        if (browsTypeValue == -121 || browsTypeValue == -89 || browsTypeValue == -57 || browsTypeValue == -25
                || browsTypeValue == 7 || browsTypeValue == 39 || browsTypeValue == 71 || browsTypeValue == 103) {
            browsTypeLabel = "8";
        }

        if (browsTypeValue == -120 || browsTypeValue == -88 || browsTypeValue == -56 || browsTypeValue == -24
                || browsTypeValue == 8 || browsTypeValue == 40 || browsTypeValue == 72 || browsTypeValue == 104) {
            browsTypeLabel = "9";
        }

        if (browsTypeValue == -119 || browsTypeValue == -87 || browsTypeValue == -55 || browsTypeValue == -23
                || browsTypeValue == 9 || browsTypeValue == 41 || browsTypeValue == 73 || browsTypeValue == 105) {
            browsTypeLabel = "10";
        }

        if (browsTypeValue == -118 || browsTypeValue == -86 || browsTypeValue == -54 || browsTypeValue == -22
                || browsTypeValue == 10 || browsTypeValue == 42 || browsTypeValue == 74 || browsTypeValue == 106) {
            browsTypeLabel = "11";
        }

        if (browsTypeValue == -117 || browsTypeValue == -85 || browsTypeValue == -53 || browsTypeValue == -21
                || browsTypeValue == 11 || browsTypeValue == 43 || browsTypeValue == 75 || browsTypeValue == 107) {
            browsTypeLabel = "12";
        }

        if (browsTypeValue == -116 || browsTypeValue == -84 || browsTypeValue == -52 || browsTypeValue == -20
                || browsTypeValue == 12 || browsTypeValue == 44 || browsTypeValue == 76 || browsTypeValue == 108) {
            browsTypeLabel = "13";
        }

        if (browsTypeValue == -115 || browsTypeValue == -83 || browsTypeValue == -51 || browsTypeValue == -19
                || browsTypeValue == 13 || browsTypeValue == 45 || browsTypeValue == 77 || browsTypeValue == 109) {
            browsTypeLabel = "14";
        }

        if (browsTypeValue == -114 || browsTypeValue == -82 || browsTypeValue == -50 || browsTypeValue == -18
                || browsTypeValue == 14 || browsTypeValue == 46 || browsTypeValue == 78 || browsTypeValue == 110) {
            browsTypeLabel = "15";
        }

        if (browsTypeValue == -113 || browsTypeValue == -81 || browsTypeValue == -49 || browsTypeValue == -17
                || browsTypeValue == 15 || browsTypeValue == 47 || browsTypeValue == 79 || browsTypeValue == 111) {
            browsTypeLabel = "16";
        }

        if (browsTypeValue == -112 || browsTypeValue == -80 || browsTypeValue == -48 || browsTypeValue == -16
                || browsTypeValue == 16 || browsTypeValue == 48 || browsTypeValue == 80 || browsTypeValue == 112) {
            browsTypeLabel = "17";
        }

        if (browsTypeValue == -111 || browsTypeValue == -79 || browsTypeValue == -47 || browsTypeValue == -15
                || browsTypeValue == 17 || browsTypeValue == 49 || browsTypeValue == 81 || browsTypeValue == 113) {
            browsTypeLabel = "18";
        }

        if (browsTypeValue == -110 || browsTypeValue == -78 || browsTypeValue == -46 || browsTypeValue == -14
                || browsTypeValue == 18 || browsTypeValue == 50 || browsTypeValue == 82 || browsTypeValue == 114) {
            browsTypeLabel = "19";
        }

        if (browsTypeValue == -109 || browsTypeValue == -77 || browsTypeValue == -45 || browsTypeValue == -13
                || browsTypeValue == 19 || browsTypeValue == 51 || browsTypeValue == 83 || browsTypeValue == 115) {
            browsTypeLabel = "20";
        }

        if (browsTypeValue == -108 || browsTypeValue == -76 || browsTypeValue == -44 || browsTypeValue == -12
                || browsTypeValue == 20 || browsTypeValue == 52 || browsTypeValue == 84 || browsTypeValue == 116) {
            browsTypeLabel = "21";
        }

        if (browsTypeValue == -107 || browsTypeValue == -75 || browsTypeValue == -43 || browsTypeValue == -11
                || browsTypeValue == 21 || browsTypeValue == 53 || browsTypeValue == 85 || browsTypeValue == 117) {
            browsTypeLabel = "22";
        }

        if (browsTypeValue == -106 || browsTypeValue == -74 || browsTypeValue == -42 || browsTypeValue == -10
                || browsTypeValue == 22 || browsTypeValue == 54 || browsTypeValue == 86 || browsTypeValue == 118) {
            browsTypeLabel = "23";
        }

        if (browsTypeValue == -105 || browsTypeValue == -73 || browsTypeValue == -41 || browsTypeValue == -9
                || browsTypeValue == 23 || browsTypeValue == 55 || browsTypeValue == 87 || browsTypeValue == 119) {
            browsTypeLabel = "24";
        }

        if (browsTypeValue == -104 || browsTypeValue == -72 || browsTypeValue == -40 || browsTypeValue == -8
                || browsTypeValue == 24 || browsTypeValue == 56 || browsTypeValue == 88 || browsTypeValue == 120) {
            browsTypeLabel = "25";
        }

        if (browsTypeValue == -103 || browsTypeValue == -71 || browsTypeValue == -39 || browsTypeValue == -7
                || browsTypeValue == 25 || browsTypeValue == 57 || browsTypeValue == 89 || browsTypeValue == 121) {
            browsTypeLabel = "26";
        }

        if (browsTypeValue == -102 || browsTypeValue == -70 || browsTypeValue == -38 || browsTypeValue == -6
                || browsTypeValue == 26 || browsTypeValue == 58 || browsTypeValue == 90 || browsTypeValue == 122) {
            browsTypeLabel = "27";
        }

        if (browsTypeValue == -101 || browsTypeValue == -69 || browsTypeValue == -37 || browsTypeValue == -5
                || browsTypeValue == 27 || browsTypeValue == 59 || browsTypeValue == 91 || browsTypeValue == 123) {
            browsTypeLabel = "28";
        }

        if (browsTypeValue == -100 || browsTypeValue == -68 || browsTypeValue == -36 || browsTypeValue == -4
                || browsTypeValue == 28 || browsTypeValue == 60 || browsTypeValue == 92 || browsTypeValue == 124) {
            browsTypeLabel = "29";
        }

        if (browsTypeValue == -99 || browsTypeValue == -67 || browsTypeValue == -35 || browsTypeValue == -3
                || browsTypeValue == 29 || browsTypeValue == 61 || browsTypeValue == 93 || browsTypeValue == 125) {
            browsTypeLabel = "30";
        }

        if (browsTypeValue == -98 || browsTypeValue == -66 || browsTypeValue == -34 || browsTypeValue == -2
                || browsTypeValue == 30 || browsTypeValue == 62 || browsTypeValue == 94 || browsTypeValue == 126) {
            browsTypeLabel = "31";
        }

        if (browsTypeValue == -97 || browsTypeValue == -65 || browsTypeValue == -33 || browsTypeValue == -1
                || browsTypeValue == 31 || browsTypeValue == 63 || browsTypeValue == 95 || browsTypeValue == 127) {
            browsTypeLabel = "32";
        }

        return browsTypeLabel;
    }

    public static String getEyesPositionLabel(int eyesPositionValue) {
        String eyesPositionLabel = "???";

        if (eyesPositionValue >= -32 && eyesPositionValue <= -1) {
            eyesPositionLabel = "-4";
        }

        else if (eyesPositionValue >= -64 && eyesPositionValue <= -33) {
            eyesPositionLabel = "-3";
        }

        else if (eyesPositionValue >= -96 && eyesPositionValue <= -65) {
            eyesPositionLabel = "-2";
        }

        else if (eyesPositionValue >= -128 && eyesPositionValue <= -97) {
            eyesPositionLabel = "-1";
        }

        else if (eyesPositionValue >= 96 && eyesPositionValue <= 127) {
            eyesPositionLabel = "0";
        }

        else if (eyesPositionValue >= 64 && eyesPositionValue <= 95) {
            eyesPositionLabel = "1";
        }

        else if (eyesPositionValue >= 32 && eyesPositionValue <= 63) {
            eyesPositionLabel = "2";
        }

        else if (eyesPositionValue >= 0 && eyesPositionValue <= 31) {
            eyesPositionLabel = "3";
        }

        return eyesPositionLabel;
    }

    private static final Map<Integer, Integer> eyesPositionOffset = new HashMap<Integer, Integer>() {
        {
            put(3, 0);
            put(2, 1);
            put(1, 2);
            put(0, 3);
            put(-1, 0);
            put(-2, 1);
            put(-3, 2);
            put(-4, 3);
        }
    };

    public static int getBrowsTypeEyesPositionValue(String browsTypeLabel, String eyesPositionLabel) {
        int browsTypePositionValue = 0;
        int browsType = Integer.parseInt(browsTypeLabel);
        int eyesPosition = Integer.parseInt(eyesPositionLabel);
        int eyesPositionOffsetVal = eyesPositionOffset.get(eyesPosition);
        Boolean eyesPositionPositive = eyesPosition >= 0;
        int browsTypePositionBaseValue = eyesPositionPositive ? 0 : -128;

        browsTypePositionValue = browsTypePositionBaseValue;
        browsTypePositionValue += eyesPositionOffsetVal * 32;
        browsTypePositionValue += browsType - 1;

        return browsTypePositionValue;
    }

    public static String getEyesTypeLabel(int eyesTypeValue) {
        String eyesTypeLabel = "???";

        if (eyesTypeValue >= 0 && eyesTypeValue <= 7) {
            eyesTypeLabel = "1";
        }

        else if (eyesTypeValue >= 8 && eyesTypeValue <= 15) {
            eyesTypeLabel = "2";
        }

        else if (eyesTypeValue >= 16 && eyesTypeValue <= 23) {
            eyesTypeLabel = "3";
        }

        else if (eyesTypeValue >= 24 && eyesTypeValue <= 31) {
            eyesTypeLabel = "4";
        }

        else if (eyesTypeValue >= 32 && eyesTypeValue <= 39) {
            eyesTypeLabel = "5";
        }

        else if (eyesTypeValue >= 40 && eyesTypeValue <= 47) {
            eyesTypeLabel = "6";
        }

        else if (eyesTypeValue >= 48 && eyesTypeValue <= 55) {
            eyesTypeLabel = "7";
        }

        else if (eyesTypeValue >= 56 && eyesTypeValue <= 63) {
            eyesTypeLabel = "8";
        }

        else if (eyesTypeValue >= 64 && eyesTypeValue <= 71) {
            eyesTypeLabel = "9";
        }

        else if (eyesTypeValue >= 72 && eyesTypeValue <= 79) {
            eyesTypeLabel = "10";
        }

        else if (eyesTypeValue >= 80 && eyesTypeValue <= 87) {
            eyesTypeLabel = "11";
        }

        else if (eyesTypeValue >= 88 && eyesTypeValue <= 95) {
            eyesTypeLabel = "12";
        }

        else if (eyesTypeValue >= 96 && eyesTypeValue <= 103) {
            eyesTypeLabel = "13";
        }

        else if (eyesTypeValue >= 104 && eyesTypeValue <= 111) {
            eyesTypeLabel = "14";
        }

        else if (eyesTypeValue >= 112 && eyesTypeValue <= 119) {
            eyesTypeLabel = "15";
        }

        else if (eyesTypeValue >= 120 && eyesTypeValue <= 127) {
            eyesTypeLabel = "16";
        }

        else if (eyesTypeValue >= -128 && eyesTypeValue <= -121) {
            eyesTypeLabel = "17";
        }

        else if (eyesTypeValue >= -120 && eyesTypeValue <= -113) {
            eyesTypeLabel = "18";
        }

        else if (eyesTypeValue >= -112 && eyesTypeValue <= -105) {
            eyesTypeLabel = "19";
        }

        else if (eyesTypeValue >= -104 && eyesTypeValue <= -97) {
            eyesTypeLabel = "20";
        }

        else if (eyesTypeValue >= -96 && eyesTypeValue <= -89) {
            eyesTypeLabel = "21";
        }

        else if (eyesTypeValue >= -88 && eyesTypeValue <= -81) {
            eyesTypeLabel = "22";
        }

        else if (eyesTypeValue >= -80 && eyesTypeValue <= -73) {
            eyesTypeLabel = "23";
        }

        else if (eyesTypeValue >= -72 && eyesTypeValue <= -65) {
            eyesTypeLabel = "24";
        }

        else if (eyesTypeValue >= -64 && eyesTypeValue <= -57) {
            eyesTypeLabel = "25";
        }

        else if (eyesTypeValue >= -56 && eyesTypeValue <= -49) {
            eyesTypeLabel = "26";
        }

        else if (eyesTypeValue >= -48 && eyesTypeValue <= -41) {
            eyesTypeLabel = "27";
        }

        else if (eyesTypeValue >= -40 && eyesTypeValue <= -33) {
            eyesTypeLabel = "28";
        }

        else if (eyesTypeValue >= -32 && eyesTypeValue <= -25) {
            eyesTypeLabel = "29";
        }

        else if (eyesTypeValue >= -24 && eyesTypeValue <= -17) {
            eyesTypeLabel = "30";
        }

        else if (eyesTypeValue >= -16 && eyesTypeValue <= -9) {
            eyesTypeLabel = "31";
        }

        else if (eyesTypeValue >= -8 && eyesTypeValue <= -1) {
            eyesTypeLabel = "32";
        }

        return eyesTypeLabel;
    }

    public static Map<String, Integer> getEyeTypesByLabel() {
        return eyeTypesByLabel;
    }

    public static String getNeckWarmerLabel(int neckWarmerValue) {
        String neckWarmerLabel = "???";

        if (neckWarmerValue >= 0 && neckWarmerValue <= 127) {
            neckWarmerLabel = "N";
        }

        else if (neckWarmerValue >= -128 && neckWarmerValue <= -1) {
            neckWarmerLabel = "Y";
        }

        return neckWarmerLabel;
    }

    public static Map<String, Integer> getNeckWarmerBaseHairCodeOffsetByLabel() {
        return neckWarmerBaseHairCodeOffsetByLabel;
    }

    public static String getUnderShortsLabel(int underShortsValue) {
        String underShortsLabel = "???";

        if (underShortsValue >= 0 && underShortsValue <= 127) {
            underShortsLabel = "N";
        }

        else if (underShortsValue >= -128 && underShortsValue <= -1) {
            underShortsLabel = "Y";
        }

        return underShortsLabel;
    }

    public static String getUnderShortsColorLabel(int underShortsColorValue) {
        String underShortsColorLabel = "???";
        int underShortsColorBaseValue = 0;
        int offsetValue = 0;

        if (underShortsColorValue >= -128 && underShortsColorValue <= -1) {
            offsetValue = 8;
        }

        underShortsColorBaseValue = (underShortsColorValue % 8) + offsetValue;

        if (underShortsColorBaseValue == 8){
            underShortsColorBaseValue = 0;
        }

        underShortsColorLabel = undershortsColorByValue.get(underShortsColorBaseValue);

        return underShortsColorLabel;
    }

    public static String getBraceletTypeLabel(int braceletTypeValue) {
        String braceletTypeLabel = "???";
        int braceletTypeBaseValue = 0;
        int offsetValue = 0;

        if (braceletTypeValue >= -128 && braceletTypeValue <= -1) {
            offsetValue = 128;
        }

        braceletTypeBaseValue = (braceletTypeValue + offsetValue) % 4;

        braceletTypeLabel = braceletTypeByValue.get(braceletTypeBaseValue);

        return braceletTypeLabel;
    }

    public static String getBraceletColorLabel(int braceletColorValue) {
        String braceletColorLabel = "???";
        int offsetValue = 0;

        if (braceletColorValue >= -128 && braceletColorValue <= -1) {
            offsetValue = 128;
        }

        int braceletColorValueWithOffset = braceletColorValue + offsetValue;

        if ((braceletColorValueWithOffset >= 0 && braceletColorValueWithOffset <= 3)
                || (braceletColorValueWithOffset >= 32 && braceletColorValueWithOffset <= 35)
                || (braceletColorValueWithOffset >= 64 && braceletColorValueWithOffset <= 67)
                || (braceletColorValueWithOffset >= 96 && braceletColorValueWithOffset <= 99)) {
            braceletColorLabel = "White";
        }
        else if ((braceletColorValueWithOffset >= 4 && braceletColorValueWithOffset <= 7)
                || (braceletColorValueWithOffset >= 36 && braceletColorValueWithOffset <= 39)
                || (braceletColorValueWithOffset >= 68 && braceletColorValueWithOffset <= 71)
                || (braceletColorValueWithOffset >= 100 && braceletColorValueWithOffset <= 103)) {
            braceletColorLabel = "Black";
        }
        else if ((braceletColorValueWithOffset >= 8 && braceletColorValueWithOffset <= 11)
                || (braceletColorValueWithOffset >= 40 && braceletColorValueWithOffset <= 43)
                || (braceletColorValueWithOffset >= 72 && braceletColorValueWithOffset <= 75)
                || (braceletColorValueWithOffset >= 104 && braceletColorValueWithOffset <= 107)) {
            braceletColorLabel = "Red";
        }
        else if ((braceletColorValueWithOffset >= 12 && braceletColorValueWithOffset <= 15)
                || (braceletColorValueWithOffset >= 44 && braceletColorValueWithOffset <= 47)
                || (braceletColorValueWithOffset >= 76 && braceletColorValueWithOffset <= 79)
                || (braceletColorValueWithOffset >= 108 && braceletColorValueWithOffset <= 111)) {
            braceletColorLabel = "Blue";
        }
        else if ((braceletColorValueWithOffset >= 16 && braceletColorValueWithOffset <= 19)
                || (braceletColorValueWithOffset >= 48 && braceletColorValueWithOffset <= 51)
                || (braceletColorValueWithOffset >= 80 && braceletColorValueWithOffset <= 83)
                || (braceletColorValueWithOffset >= 112 && braceletColorValueWithOffset <= 115)) {
            braceletColorLabel = "Yellow";
        }
        else if ((braceletColorValueWithOffset >= 20 && braceletColorValueWithOffset <= 23)
                || (braceletColorValueWithOffset >= 52 && braceletColorValueWithOffset <= 55)
                || (braceletColorValueWithOffset >= 84 && braceletColorValueWithOffset <= 87)
                || (braceletColorValueWithOffset >= 116 && braceletColorValueWithOffset <= 119)) {
            braceletColorLabel = "Green";
        }
        else if ((braceletColorValueWithOffset >= 24 && braceletColorValueWithOffset <= 27)
                || (braceletColorValueWithOffset >= 56 && braceletColorValueWithOffset <= 59)
                || (braceletColorValueWithOffset >= 88 && braceletColorValueWithOffset <= 91)
                || (braceletColorValueWithOffset >= 120 && braceletColorValueWithOffset <= 123)) {
            braceletColorLabel = "Purple";
        }
        else if ((braceletColorValueWithOffset >= 28 && braceletColorValueWithOffset <= 31)
                || (braceletColorValueWithOffset >= 60 && braceletColorValueWithOffset <= 63)
                || (braceletColorValueWithOffset >= 92 && braceletColorValueWithOffset <= 95)
                || (braceletColorValueWithOffset >= 124 && braceletColorValueWithOffset <= 127)) {
            braceletColorLabel = "Cyan";
        }

        return braceletColorLabel;
    }

    private static final Map<String, Integer> braceletTypeBaseValues = new HashMap<String, Integer>() {
        {
            put("None", 0);
            put("Left", 1);
            put("Right", 2);
        }
    };

    private static final Map<String, Integer> braceletColorOffsetValues = new HashMap<String, Integer>() {
        {
            put("White", 0);
            put("Black", 1);
            put("Red", 2);
            put("Blue", 3);
            put("Yellow", 4);
            put("Green", 5);
            put("Purple", 6);
            put("Cyan", 7);
        }
    };

    private static final Map<String, Integer> underShortsOffsetValues = new HashMap<String, Integer>() {
        {
            put("N", 0);
            put("Y", -128);
        }
    };

    public static int getUnderShortsBraceletTypeColorValue(String underShortsLabel, String braceletTypeLabel, String braceletColorLabel) {
        int underShortsBraceletTypeColorValue;
        int byteOffset = 4;

        int underShortsBraceletTypeColorBaseValue = braceletTypeBaseValues.get(braceletTypeLabel);
        int braceletColorOffset = braceletColorOffsetValues.get(braceletColorLabel) * byteOffset;
        int underShortsOffset = underShortsOffsetValues.get(underShortsLabel);

        underShortsBraceletTypeColorValue = (underShortsBraceletTypeColorBaseValue + braceletColorOffset) + underShortsOffset;

        return underShortsBraceletTypeColorValue;
    }

    public static String getSocksTypeLabel(int socksTypeValue) {
        String socksTypeLabel = "???";
        int offsetValue = 0;

        if (socksTypeValue >= -128 && socksTypeValue <= -1) {
            offsetValue = 128;
        }

        int socksTypeValueWithOffset = socksTypeValue + offsetValue;

        if ((socksTypeValueWithOffset >= 0 && socksTypeValueWithOffset <= 7)
                || (socksTypeValueWithOffset >= 32 && socksTypeValueWithOffset <= 39)
                || (socksTypeValueWithOffset >= 64 && socksTypeValueWithOffset <= 71)
                || (socksTypeValueWithOffset >= 96 && socksTypeValueWithOffset <= 103)) {
            socksTypeLabel = "Long";
        }

        if ((socksTypeValueWithOffset >= 8 && socksTypeValueWithOffset <= 15)
                || (socksTypeValueWithOffset >= 40 && socksTypeValueWithOffset <= 47)
                || (socksTypeValueWithOffset >= 72 && socksTypeValueWithOffset <= 79)
                || (socksTypeValueWithOffset >= 104 && socksTypeValueWithOffset <= 111)) {
            socksTypeLabel = "Standard";
        }

        if ((socksTypeValueWithOffset >= 16 && socksTypeValueWithOffset <= 23)
                || (socksTypeValueWithOffset >= 48 && socksTypeValueWithOffset <= 55)
                || (socksTypeValueWithOffset >= 80 && socksTypeValueWithOffset <= 87)
                || (socksTypeValueWithOffset >= 112 && socksTypeValueWithOffset <= 119)) {
            socksTypeLabel = "Short";
        }

        return socksTypeLabel;
    }

    private static final Map<String, Integer> underShortsColorBaseValues = new HashMap<String, Integer>() {
        {
            put("White", 0);
            put("Black", 1);
            put("Red", 2);
            put("Blue", 3);
            put("Yellow", 4);
            put("Green", 5);
            put("Purple", 6);
            put("Same", 7);
        }
    };

    private static final Map<String, Integer> socksTypeOffsetValues = new HashMap<String, Integer>() {
        {
            put("Long", 0);
            put("Standard", 1);
            put("Short", 2);
        }
    };

    public static int getUnderShortsColorSocksTypeValue(String underShortsColorLabel, String socksTypeLabel) {
        int underShortsColorSocksTypeValue;
        int byteOffset = 8;

        int underShortsColorSocksTypeBaseValue = underShortsColorBaseValues.get(underShortsColorLabel);
        int socksTypeOffset = socksTypeOffsetValues.get(socksTypeLabel) * byteOffset;

        underShortsColorSocksTypeValue = underShortsColorSocksTypeBaseValue + socksTypeOffset;

        return underShortsColorSocksTypeValue;
    }

    public static String getGlovesLabel(int glovesLabelValue) {
        String glovesLabel = "???";

        if (glovesLabelValue >= -128 && glovesLabelValue <= -1) {
            glovesLabel = "Y";
        }
        else if (glovesLabelValue >= 0 && glovesLabelValue <= 127) {
            glovesLabel = "N";
        }

        return glovesLabel;
    }

    public static String getAnkleTapeLabel(int ankleTapeValue) {
        String ankleTapeLabel = "???";

        if ((ankleTapeValue >= -64 && ankleTapeValue <= -1) || (ankleTapeValue >= 64 && ankleTapeValue <= 127)) {
            ankleTapeLabel = "Y";
        }
        else if ((ankleTapeValue >= -128 && ankleTapeValue <= -65) || (ankleTapeValue >= 0 && ankleTapeValue <= 63)) {
            ankleTapeLabel = "N";
        }

        return ankleTapeLabel;
    }

    public static String getFingerBandTypeLabel(int fingerBandTypeValue) {
        String fingerBandTypeLabel = "???";

        if (fingerBandTypeValue >= 0 && fingerBandTypeValue <= 63){
            fingerBandTypeLabel = "N";
        }
        else if (fingerBandTypeValue >= 64 && fingerBandTypeValue <= 127){
            fingerBandTypeLabel = "1";
        }
        else if (fingerBandTypeValue >= -128 && fingerBandTypeValue <= -65){
            fingerBandTypeLabel = "2";
        }

        return fingerBandTypeLabel;
    }

    public static String getShirtTypeLabel(int shirtTypeValue) {
        String shirtTypeLabel = "???";

        if (shirtTypeValue >= -128 && shirtTypeValue <= -1) {
            shirtTypeLabel = "Untucked";
        }
        else if (shirtTypeValue >= 0 && shirtTypeValue <= 127) {
            shirtTypeLabel = "Standard";
        }

        return shirtTypeLabel;
    }

    public static int getShirtTypeWeightValue(String shirtTypeLabel, String weightLabel) {
        int shirtTypeValue;
        int untuckedShirtOffset = -128;

        int weightValue = Integer.parseInt(weightLabel);

        shirtTypeValue = weightValue;

        if (shirtTypeLabel.equals("Untucked")) {
            shirtTypeValue += untuckedShirtOffset;
        }

        return shirtTypeValue;
    }

    public static String getRgbRLabel(int rgbRValue) {
        String rgbRLabel = "???";

        if (rgbRValue >= 0 && rgbRValue <= 2) {
            rgbRLabel = "63";
        }
        else if (rgbRValue >= 4 && rgbRValue <= 6) {
            rgbRLabel = "62";
        }
        else if (rgbRValue >= 8 && rgbRValue <= 10) {
            rgbRLabel = "61";
        }
        else if (rgbRValue >= 12 && rgbRValue <= 14) {
            rgbRLabel = "60";
        }
        else if (rgbRValue >= 16 && rgbRValue <= 18) {
            rgbRLabel = "59";
        }
        else if (rgbRValue >= 20 && rgbRValue <= 22) {
            rgbRLabel = "58";
        }
        else if (rgbRValue >= 24 && rgbRValue <= 26) {
            rgbRLabel = "57";
        }
        else if (rgbRValue >= 28 && rgbRValue <= 30) {
            rgbRLabel = "56";
        }
        else if (rgbRValue >= 32 && rgbRValue <= 34) {
            rgbRLabel = "55";
        }
        else if (rgbRValue >= 36 && rgbRValue <= 38) {
            rgbRLabel = "54";
        }
        else if (rgbRValue >= 40 && rgbRValue <= 42) {
            rgbRLabel = "53";
        }
        else if (rgbRValue >= 44 && rgbRValue <= 46) {
            rgbRLabel = "52";
        }
        else if (rgbRValue >= 48 && rgbRValue <= 50) {
            rgbRLabel = "51";
        }
        else if (rgbRValue >= 52 && rgbRValue <= 54) {
            rgbRLabel = "50";
        }
        else if (rgbRValue >= 56 && rgbRValue <= 58) {
            rgbRLabel = "49";
        }
        else if (rgbRValue >= 60 && rgbRValue <= 62) {
            rgbRLabel = "48";
        }
        else if (rgbRValue >= 64 && rgbRValue <= 66) {
            rgbRLabel = "47";
        }
        else if (rgbRValue >= 68 && rgbRValue <= 70) {
            rgbRLabel = "46";
        }
        else if (rgbRValue >= 72 && rgbRValue <= 74) {
            rgbRLabel = "45";
        }
        else if (rgbRValue >= 76 && rgbRValue <= 78) {
            rgbRLabel = "44";
        }
        else if (rgbRValue >= 80 && rgbRValue <= 82) {
            rgbRLabel = "43";
        }
        else if (rgbRValue >= 84 && rgbRValue <= 86) {
            rgbRLabel = "42";
        }
        else if (rgbRValue >= 88 && rgbRValue <= 90) {
            rgbRLabel = "41";
        }
        else if (rgbRValue >= 92 && rgbRValue <= 94) {
            rgbRLabel = "40";
        }
        else if (rgbRValue >= 96 && rgbRValue <= 98) {
            rgbRLabel = "39";
        }
        else if (rgbRValue >= 100 && rgbRValue <= 102) {
            rgbRLabel = "38";
        }
        else if (rgbRValue >= 104 && rgbRValue <= 106) {
            rgbRLabel = "37";
        }
        else if (rgbRValue >= 108 && rgbRValue <= 110) {
            rgbRLabel = "36";
        }
        else if (rgbRValue >= 112 && rgbRValue <= 114) {
            rgbRLabel = "35";
        }
        else if (rgbRValue >= 116 && rgbRValue <= 118) {
            rgbRLabel = "34";
        }
        else if (rgbRValue >= 120 && rgbRValue <= 122) {
            rgbRLabel = "33";
        }
        else if (rgbRValue >= 124 && rgbRValue <= 126) {
            rgbRLabel = "32";
        }
        else if (rgbRValue >= -128 && rgbRValue <= -126) {
            rgbRLabel = "31";
        }
        else if (rgbRValue >= -124 && rgbRValue <= -122) {
            rgbRLabel = "30";
        }
        else if (rgbRValue >= -120 && rgbRValue <= -118) {
            rgbRLabel = "29";
        }
        else if (rgbRValue >= -116 && rgbRValue <= -114) {
            rgbRLabel = "28";
        }
        else if (rgbRValue >= -112 && rgbRValue <= -110) {
            rgbRLabel = "27";
        }
        else if (rgbRValue >= -108 && rgbRValue <= -106) {
            rgbRLabel = "26";
        }
        else if (rgbRValue >= -104 && rgbRValue <= -102) {
            rgbRLabel = "25";
        }
        else if (rgbRValue >= -100 && rgbRValue <= -98) {
            rgbRLabel = "24";
        }
        else if (rgbRValue >= -96 && rgbRValue <= -94) {
            rgbRLabel = "23";
        }
        else if (rgbRValue >= -92 && rgbRValue <= -90) {
            rgbRLabel = "22";
        }
        else if (rgbRValue >= -88 && rgbRValue <= -86) {
            rgbRLabel = "21";
        }
        else if (rgbRValue >= -84 && rgbRValue <= -82) {
            rgbRLabel = "20";
        }
        else if (rgbRValue >= -80 && rgbRValue <= -78) {
            rgbRLabel = "19";
        }
        else if (rgbRValue >= -76 && rgbRValue <= -74) {
            rgbRLabel = "18";
        }
        else if (rgbRValue >= -72 && rgbRValue <= -70) {
            rgbRLabel = "17";
        }
        else if (rgbRValue >= -68 && rgbRValue <= -66) {
            rgbRLabel = "16";
        }
        else if (rgbRValue >= -64 && rgbRValue <= -62) {
            rgbRLabel = "15";
        }
        else if (rgbRValue >= -60 && rgbRValue <= -58) {
            rgbRLabel = "14";
        }
        else if (rgbRValue >= -56 && rgbRValue <= -54) {
            rgbRLabel = "13";
        }
        else if (rgbRValue >= -52 && rgbRValue <= -50) {
            rgbRLabel = "12";
        }
        else if (rgbRValue >= -48 && rgbRValue <= -46) {
            rgbRLabel = "11";
        }
        else if (rgbRValue >= -44 && rgbRValue <= -42) {
            rgbRLabel = "10";
        }
        else if (rgbRValue >= -40 && rgbRValue <= -38) {
            rgbRLabel = "9";
        }
        else if (rgbRValue >= -36 && rgbRValue <= -34) {
            rgbRLabel = "8";
        }
        else if (rgbRValue >= -32 && rgbRValue <= -30) {
            rgbRLabel = "7";
        }
        else if (rgbRValue >= -28 && rgbRValue <= -26) {
            rgbRLabel = "6";
        }
        else if (rgbRValue >= -24 && rgbRValue <= -22) {
            rgbRLabel = "5";
        }
        else if (rgbRValue >= -20 && rgbRValue <= -18) {
            rgbRLabel = "4";
        }
        else if (rgbRValue >= -16 && rgbRValue <= -14) {
            rgbRLabel = "3";
        }
        else if (rgbRValue >= -12 && rgbRValue <= -10) {
            rgbRLabel = "2";
        }
        else if (rgbRValue >= -8 && rgbRValue <= -6) {
            rgbRLabel = "1";
        }
        else if (rgbRValue >= -4 && rgbRValue <= -2) {
            rgbRLabel = "0";
        }

        return rgbRLabel;
    }

    private static final Map<String, Integer> rgbRBaseValues = new HashMap<String, Integer>() {
        {
            put("31", -128);
            put("30", -124);
            put("29", -120);
            put("28", -116);
            put("27", -112);
            put("26", -108);
            put("25", -104);
            put("24", -100);
            put("23", -96);
            put("22", -92);
            put("21", -88);
            put("20", -84);
            put("19", -80);
            put("18", -76);
            put("17", -72);
            put("16", -68);
            put("15", -64);
            put("14", -60);
            put("13", -56);
            put("12", -52);
            put("11", -48);
            put("10", -44);
            put("9", -40);
            put("8", -36);
            put("7", -32);
            put("6", -28);
            put("5", -24);
            put("4", -20);
            put("3", -16);
            put("2", -12);
            put("1", -8);
            put("0", -4);
            put("63", 0);
            put("62", 4);
            put("61", 8);
            put("60", 12);
            put("59", 16);
            put("58", 20);
            put("57", 24);
            put("56", 28);
            put("55", 32);
            put("54", 36);
            put("53", 40);
            put("52", 44);
            put("51", 48);
            put("50", 52);
            put("49", 56);
            put("48", 60);
            put("47", 64);
            put("46", 68);
            put("45", 72);
            put("44", 76);
            put("43", 80);
            put("42", 84);
            put("41", 88);
            put("40", 92);
            put("39", 96);
            put("38", 100);
            put("37", 104);
            put("36", 108);
            put("35", 112);
            put("34", 116);
            put("33", 120);
            put("32", 124);
        }
    };

    public static int getRgbRFaceTypeValue(String rgbRLabel, String faceTypeLabel) {
        int rgbRValue;
        int rgbRBaseValue = rgbRBaseValues.get(rgbRLabel);
        int faceTypeOffset = faceTypesByLabel.get(faceTypeLabel);

        rgbRValue = rgbRBaseValue + faceTypeOffset;

        return rgbRValue;
    }

    public static String getRgbGLabel(int rgbGValue) {
        String rgbGLabel = "???";

        if (rgbGValue == 0 || rgbGValue == 64 || rgbGValue == -128) {
            rgbGLabel = "63";
        }
        else if (rgbGValue == 1 || rgbGValue == 65 || rgbGValue == -127) {
            rgbGLabel = "62";
        }
        else if (rgbGValue == 2 || rgbGValue == 66 || rgbGValue == -126) {
            rgbGLabel = "61";
        }
        else if (rgbGValue == 3 || rgbGValue == 67 || rgbGValue == -125) {
            rgbGLabel = "60";
        }
        else if (rgbGValue == 4 || rgbGValue == 68 || rgbGValue == -124) {
            rgbGLabel = "59";
        }
        else if (rgbGValue == 5 || rgbGValue == 69 || rgbGValue == -123) {
            rgbGLabel = "58";
        }
        else if (rgbGValue == 6 || rgbGValue == 70 || rgbGValue == -122) {
            rgbGLabel = "57";
        }
        else if (rgbGValue == 7 || rgbGValue == 71 || rgbGValue == -121) {
            rgbGLabel = "56";
        }
        else if (rgbGValue == 8 || rgbGValue == 72 || rgbGValue == -120) {
            rgbGLabel = "55";
        }
        else if (rgbGValue == 9 || rgbGValue == 73 || rgbGValue == -119) {
            rgbGLabel = "54";
        }
        else if (rgbGValue == 10 || rgbGValue == 74 || rgbGValue == -118) {
            rgbGLabel = "53";
        }
        else if (rgbGValue == 11 || rgbGValue == 75 || rgbGValue == -117) {
            rgbGLabel = "52";
        }
        else if (rgbGValue == 12 || rgbGValue == 76 || rgbGValue == -116) {
            rgbGLabel = "51";
        }
        else if (rgbGValue == 13 || rgbGValue == 77 || rgbGValue == -115) {
            rgbGLabel = "50";
        }
        else if (rgbGValue == 14 || rgbGValue == 78 || rgbGValue == -114) {
            rgbGLabel = "49";
        }
        else if (rgbGValue == 15 || rgbGValue == 79 || rgbGValue == -113) {
            rgbGLabel = "48";
        }
        else if (rgbGValue == 16 || rgbGValue == 80 || rgbGValue == -112) {
            rgbGLabel = "47";
        }
        else if (rgbGValue == 17 || rgbGValue == 81 || rgbGValue == -111) {
            rgbGLabel = "46";
        }
        else if (rgbGValue == 18 || rgbGValue == 82 || rgbGValue == -110) {
            rgbGLabel = "45";
        }
        else if (rgbGValue == 19 || rgbGValue == 83 || rgbGValue == -109) {
            rgbGLabel = "44";
        }
        else if (rgbGValue == 20 || rgbGValue == 84 || rgbGValue == -108) {
            rgbGLabel = "43";
        }
        else if (rgbGValue == 21 || rgbGValue == 85 || rgbGValue == -107) {
            rgbGLabel = "42";
        }
        else if (rgbGValue == 22 || rgbGValue == 86 || rgbGValue == -106) {
            rgbGLabel = "41";
        }
        else if (rgbGValue == 23 || rgbGValue == 87 || rgbGValue == -105) {
            rgbGLabel = "40";
        }
        else if (rgbGValue == 24 || rgbGValue == 88 || rgbGValue == -104) {
            rgbGLabel = "39";
        }
        else if (rgbGValue == 25 || rgbGValue == 89 || rgbGValue == -103) {
            rgbGLabel = "38";
        }
        else if (rgbGValue == 26 || rgbGValue == 90 || rgbGValue == -102) {
            rgbGLabel = "37";
        }
        else if (rgbGValue == 27 || rgbGValue == 91 || rgbGValue == -101) {
            rgbGLabel = "36";
        }
        else if (rgbGValue == 28 || rgbGValue == 92 || rgbGValue == -100) {
            rgbGLabel = "35";
        }
        else if (rgbGValue == 29 || rgbGValue == 93 || rgbGValue == -99) {
            rgbGLabel = "34";
        }
        else if (rgbGValue == 30 || rgbGValue == 94 || rgbGValue == -98) {
            rgbGLabel = "33";
        }
        else if (rgbGValue == 31 || rgbGValue == 95 || rgbGValue == -97) {
            rgbGLabel = "32";
        }
        else if (rgbGValue == 32 || rgbGValue == 96 || rgbGValue == -96) {
            rgbGLabel = "31";
        }
        else if (rgbGValue == 33 || rgbGValue == 97 || rgbGValue == -95) {
            rgbGLabel = "30";
        }
        else if (rgbGValue == 34 || rgbGValue == 98 || rgbGValue == -94) {
            rgbGLabel = "29";
        }
        else if (rgbGValue == 35 || rgbGValue == 99 || rgbGValue == -93) {
            rgbGLabel = "28";
        }
        else if (rgbGValue == 36 || rgbGValue == 100 || rgbGValue == -92) {
            rgbGLabel = "27";
        }
        else if (rgbGValue == 37 || rgbGValue == 101 || rgbGValue == -91) {
            rgbGLabel = "26";
        }
        else if (rgbGValue == 38 || rgbGValue == 102 || rgbGValue == -90) {
            rgbGLabel = "25";
        }
        else if (rgbGValue == 39 || rgbGValue == 103 || rgbGValue == -89) {
            rgbGLabel = "24";
        }
        else if (rgbGValue == 40 || rgbGValue == 104 || rgbGValue == -88) {
            rgbGLabel = "23";
        }
        else if (rgbGValue == 41 || rgbGValue == 105 || rgbGValue == -87) {
            rgbGLabel = "22";
        }
        else if (rgbGValue == 42 || rgbGValue == 106 || rgbGValue == -86) {
            rgbGLabel = "21";
        }
        else if (rgbGValue == 43 || rgbGValue == 107 || rgbGValue == -85) {
            rgbGLabel = "20";
        }
        else if (rgbGValue == 44 || rgbGValue == 108 || rgbGValue == -84) {
            rgbGLabel = "19";
        }
        else if (rgbGValue == 45 || rgbGValue == 109 || rgbGValue == -83) {
            rgbGLabel = "18";
        }
        else if (rgbGValue == 46 || rgbGValue == 110 || rgbGValue == -82) {
            rgbGLabel = "17";
        }
        else if (rgbGValue == 47 || rgbGValue == 111 || rgbGValue == -81) {
            rgbGLabel = "16";
        }
        else if (rgbGValue == 48 || rgbGValue == 112 || rgbGValue == -80) {
            rgbGLabel = "15";
        }
        else if (rgbGValue == 49 || rgbGValue == 113 || rgbGValue == -79) {
            rgbGLabel = "14";
        }
        else if (rgbGValue == 50 || rgbGValue == 114 || rgbGValue == -78) {
            rgbGLabel = "13";
        }
        else if (rgbGValue == 51 || rgbGValue == 115 || rgbGValue == -77) {
            rgbGLabel = "12";
        }
        else if (rgbGValue == 52 || rgbGValue == 116 || rgbGValue == -76) {
            rgbGLabel = "11";
        }
        else if (rgbGValue == 53 || rgbGValue == 117 || rgbGValue == -75) {
            rgbGLabel = "10";
        }
        else if (rgbGValue == 54 || rgbGValue == 118 || rgbGValue == -74) {
            rgbGLabel = "9";
        }
        else if (rgbGValue == 55 || rgbGValue == 119 || rgbGValue == -73) {
            rgbGLabel = "8";
        }
        else if (rgbGValue == 56 || rgbGValue == 120 || rgbGValue == -72) {
            rgbGLabel = "7";
        }
        else if (rgbGValue == 57 || rgbGValue == 121 || rgbGValue == -71) {
            rgbGLabel = "6";
        }
        else if (rgbGValue == 58 || rgbGValue == 122 || rgbGValue == -70) {
            rgbGLabel = "5";
        }
        else if (rgbGValue == 59 || rgbGValue == 123 || rgbGValue == -69) {
            rgbGLabel = "4";
        }
        else if (rgbGValue == 60 || rgbGValue == 124 || rgbGValue == -68) {
            rgbGLabel = "3";
        }
        else if (rgbGValue == 61 || rgbGValue == 125 || rgbGValue == -67) {
            rgbGLabel = "2";
        }
        else if (rgbGValue == 62 || rgbGValue == 126 || rgbGValue == -66) {
            rgbGLabel = "1";
        }
        else if (rgbGValue == 63 || rgbGValue == 127 || rgbGValue == -65) {
            rgbGLabel = "0";
        }

        return rgbGLabel;
    }

    private static final Map<String, Integer> rgbGBaseValues = new HashMap<String, Integer>() {
        {
            put("0", 63);
            put("1", 62);
            put("2", 61);
            put("3", 60);
            put("4", 59);
            put("5", 58);
            put("6", 57);
            put("7", 56);
            put("8", 55);
            put("9", 54);
            put("10", 53);
            put("11", 52);
            put("12", 51);
            put("13", 50);
            put("14", 49);
            put("15", 48);
            put("16", 47);
            put("17", 46);
            put("18", 45);
            put("19", 44);
            put("20", 43);
            put("21", 42);
            put("22", 41);
            put("23", 40);
            put("24", 39);
            put("25", 38);
            put("26", 37);
            put("27", 36);
            put("28", 35);
            put("29", 34);
            put("30", 33);
            put("31", 32);
            put("32", 31);
            put("33", 30);
            put("34", 29);
            put("35", 28);
            put("36", 27);
            put("37", 26);
            put("38", 25);
            put("39", 24);
            put("40", 23);
            put("41", 22);
            put("42", 21);
            put("43", 20);
            put("44", 19);
            put("45", 18);
            put("46", 17);
            put("47", 16);
            put("48", 15);
            put("49", 14);
            put("50", 13);
            put("51", 12);
            put("52", 11);
            put("53", 10);
            put("54", 9);
            put("55", 8);
            put("56", 7);
            put("57", 6);
            put("58", 5);
            put("59", 4);
            put("60", 3);
            put("61", 2);
            put("62", 1);
            put("63", 0);
        }
    };

    private static final Map<String, Integer> rgbGFingerBandOffsetValues = new HashMap<String, Integer>() {
        {
            put("N", 0);
            put("1", 64);
            put("2", -128);
        }
    };

    public static int getRgbGFingerBandTypeValue(String rgbGLabel, String fingerBandTypeLabel) {
        int rgbGValue;
        int rgbGBaseValue = rgbGBaseValues.get(rgbGLabel);
        int fingerBandTypeOffset = rgbGFingerBandOffsetValues.get(fingerBandTypeLabel);

        rgbGValue = rgbGBaseValue + fingerBandTypeOffset;

        return rgbGValue;
    }

    public static String getRgbBLabel(int rgbBValue) {
        String rgbBLabel = "???";

        if (rgbBValue == 0 || rgbBValue == 64 || rgbBValue == -128 || rgbBValue == -64) {
            rgbBLabel = "63";
        }
        else if (rgbBValue == 1 || rgbBValue == 65 || rgbBValue == -127 || rgbBValue == -63) {
            rgbBLabel = "62";
        }
        else if (rgbBValue == 2 || rgbBValue == 66 || rgbBValue == -126 || rgbBValue == -62) {
            rgbBLabel = "61";
        }
        else if (rgbBValue == 3 || rgbBValue == 67 || rgbBValue == -125 || rgbBValue == -61) {
            rgbBLabel = "60";
        }
        else if (rgbBValue == 4 || rgbBValue == 68 || rgbBValue == -124 || rgbBValue == -60) {
            rgbBLabel = "59";
        }
        else if (rgbBValue == 5 || rgbBValue == 69 || rgbBValue == -123 || rgbBValue == -59) {
            rgbBLabel = "58";
        }
        else if (rgbBValue == 6 || rgbBValue == 70 || rgbBValue == -122 || rgbBValue == -58) {
            rgbBLabel = "57";
        }
        else if (rgbBValue == 7 || rgbBValue == 71 || rgbBValue == -121 || rgbBValue == -57) {
            rgbBLabel = "56";
        }
        else if (rgbBValue == 8 || rgbBValue == 72 || rgbBValue == -120 || rgbBValue == -56) {
            rgbBLabel = "55";
        }
        else if (rgbBValue == 9 || rgbBValue == 73 || rgbBValue == -119 || rgbBValue == -55) {
            rgbBLabel = "54";
        }
        else if (rgbBValue == 10 || rgbBValue == 74 || rgbBValue == -118 || rgbBValue == -54) {
            rgbBLabel = "53";
        }
        else if (rgbBValue == 11 || rgbBValue == 75 || rgbBValue == -117 || rgbBValue == -53) {
            rgbBLabel = "52";
        }
        else if (rgbBValue == 12 || rgbBValue == 76 || rgbBValue == -116 || rgbBValue == -52) {
            rgbBLabel = "51";
        }
        else if (rgbBValue == 13 || rgbBValue == 77 || rgbBValue == -115 || rgbBValue == -51) {
            rgbBLabel = "50";
        }
        else if (rgbBValue == 14 || rgbBValue == 78 || rgbBValue == -114 || rgbBValue == -50) {
            rgbBLabel = "49";
        }
        else if (rgbBValue == 15 || rgbBValue == 79 || rgbBValue == -113 || rgbBValue == -49) {
            rgbBLabel = "48";
        }
        else if (rgbBValue == 16 || rgbBValue == 80 || rgbBValue == -112 || rgbBValue == -48) {
            rgbBLabel = "47";
        }
        else if (rgbBValue == 17 || rgbBValue == 81 || rgbBValue == -111 || rgbBValue == -47) {
            rgbBLabel = "46";
        }
        else if (rgbBValue == 18 || rgbBValue == 82 || rgbBValue == -110 || rgbBValue == -46) {
            rgbBLabel = "45";
        }
        else if (rgbBValue == 19 || rgbBValue == 83 || rgbBValue == -109 || rgbBValue == -45) {
            rgbBLabel = "44";
        }
        else if (rgbBValue == 20 || rgbBValue == 84 || rgbBValue == -108 || rgbBValue == -44) {
            rgbBLabel = "43";
        }
        else if (rgbBValue == 21 || rgbBValue == 85 || rgbBValue == -107 || rgbBValue == -43) {
            rgbBLabel = "42";
        }
        else if (rgbBValue == 22 || rgbBValue == 86 || rgbBValue == -106 || rgbBValue == -42) {
            rgbBLabel = "41";
        }
        else if (rgbBValue == 23 || rgbBValue == 87 || rgbBValue == -105 || rgbBValue == -41) {
            rgbBLabel = "40";
        }
        else if (rgbBValue == 24 || rgbBValue == 88 || rgbBValue == -104 || rgbBValue == -40) {
            rgbBLabel = "39";
        }
        else if (rgbBValue == 25 || rgbBValue == 89 || rgbBValue == -103 || rgbBValue == -39) {
            rgbBLabel = "38";
        }
        else if (rgbBValue == 26 || rgbBValue == 90 || rgbBValue == -102 || rgbBValue == -38) {
            rgbBLabel = "37";
        }
        else if (rgbBValue == 27 || rgbBValue == 91 || rgbBValue == -101 || rgbBValue == -37) {
            rgbBLabel = "36";
        }
        else if (rgbBValue == 28 || rgbBValue == 92 || rgbBValue == -100 || rgbBValue == -36) {
            rgbBLabel = "35";
        }
        else if (rgbBValue == 29 || rgbBValue == 93 || rgbBValue == -99 || rgbBValue == -35) {
            rgbBLabel = "34";
        }
        else if (rgbBValue == 30 || rgbBValue == 94 || rgbBValue == -98 || rgbBValue == -34) {
            rgbBLabel = "33";
        }
        else if (rgbBValue == 31 || rgbBValue == 95 || rgbBValue == -97 || rgbBValue == -33) {
            rgbBLabel = "32";
        }
        else if (rgbBValue == 32 || rgbBValue == 96 || rgbBValue == -96 || rgbBValue == -32) {
            rgbBLabel = "31";
        }
        else if (rgbBValue == 33 || rgbBValue == 97 || rgbBValue == -95 || rgbBValue == -31) {
            rgbBLabel = "30";
        }
        else if (rgbBValue == 34 || rgbBValue == 98 || rgbBValue == -94 || rgbBValue == -30) {
            rgbBLabel = "29";
        }
        else if (rgbBValue == 35 || rgbBValue == 99 || rgbBValue == -93 || rgbBValue == -29) {
            rgbBLabel = "28";
        }
        else if (rgbBValue == 36 || rgbBValue == 100 || rgbBValue == -92 || rgbBValue == -28) {
            rgbBLabel = "27";
        }
        else if (rgbBValue == 37 || rgbBValue == 101 || rgbBValue == -91 || rgbBValue == -27) {
            rgbBLabel = "26";
        }
        else if (rgbBValue == 38 || rgbBValue == 102 || rgbBValue == -90 || rgbBValue == -26) {
            rgbBLabel = "25";
        }
        else if (rgbBValue == 39 || rgbBValue == 103 || rgbBValue == -89 || rgbBValue == -25) {
            rgbBLabel = "24";
        }
        else if (rgbBValue == 40 || rgbBValue == 104 || rgbBValue == -88 || rgbBValue == -24) {
            rgbBLabel = "23";
        }
        else if (rgbBValue == 41 || rgbBValue == 105 || rgbBValue == -87 || rgbBValue == -23) {
            rgbBLabel = "22";
        }
        else if (rgbBValue == 42 || rgbBValue == 106 || rgbBValue == -86 || rgbBValue == -22) {
            rgbBLabel = "21";
        }
        else if (rgbBValue == 43 || rgbBValue == 107 || rgbBValue == -85 || rgbBValue == -21) {
            rgbBLabel = "20";
        }
        else if (rgbBValue == 44 || rgbBValue == 108 || rgbBValue == -84 || rgbBValue == -20) {
            rgbBLabel = "19";
        }
        else if (rgbBValue == 45 || rgbBValue == 109 || rgbBValue == -83 || rgbBValue == -19) {
            rgbBLabel = "18";
        }
        else if (rgbBValue == 46 || rgbBValue == 110 || rgbBValue == -82 || rgbBValue == -18) {
            rgbBLabel = "17";
        }
        else if (rgbBValue == 47 || rgbBValue == 111 || rgbBValue == -81 || rgbBValue == -17) {
            rgbBLabel = "16";
        }
        else if (rgbBValue == 48 || rgbBValue == 112 || rgbBValue == -80 || rgbBValue == -16) {
            rgbBLabel = "15";
        }
        else if (rgbBValue == 49 || rgbBValue == 113 || rgbBValue == -79 || rgbBValue == -15) {
            rgbBLabel = "14";
        }
        else if (rgbBValue == 50 || rgbBValue == 114 || rgbBValue == -78 || rgbBValue == -14) {
            rgbBLabel = "13";
        }
        else if (rgbBValue == 51 || rgbBValue == 115 || rgbBValue == -77 || rgbBValue == -13) {
            rgbBLabel = "12";
        }
        else if (rgbBValue == 52 || rgbBValue == 116 || rgbBValue == -76 || rgbBValue == -12) {
            rgbBLabel = "11";
        }
        else if (rgbBValue == 53 || rgbBValue == 117 || rgbBValue == -75 || rgbBValue == -11) {
            rgbBLabel = "10";
        }
        else if (rgbBValue == 54 || rgbBValue == 118 || rgbBValue == -74 || rgbBValue == -10) {
            rgbBLabel = "9";
        }
        else if (rgbBValue == 55 || rgbBValue == 119 || rgbBValue == -73 || rgbBValue == -9) {
            rgbBLabel = "8";
        }
        else if (rgbBValue == 56 || rgbBValue == 120 || rgbBValue == -72 || rgbBValue == -8) {
            rgbBLabel = "7";
        }
        else if (rgbBValue == 57 || rgbBValue == 121 || rgbBValue == -71 || rgbBValue == -7) {
            rgbBLabel = "6";
        }
        else if (rgbBValue == 58 || rgbBValue == 122 || rgbBValue == -70 || rgbBValue == -6) {
            rgbBLabel = "5";
        }
        else if (rgbBValue == 59 || rgbBValue == 123 || rgbBValue == -69 || rgbBValue == -5) {
            rgbBLabel = "4";
        }
        else if (rgbBValue == 60 || rgbBValue == 124 || rgbBValue == -68 || rgbBValue == -4) {
            rgbBLabel = "3";
        }
        else if (rgbBValue == 61 || rgbBValue == 125 || rgbBValue == -67 || rgbBValue == -3) {
            rgbBLabel = "2";
        }
        else if (rgbBValue == 62 || rgbBValue == 126 || rgbBValue == -66 || rgbBValue == -2) {
            rgbBLabel = "1";
        }
        else if (rgbBValue == 63 || rgbBValue == 127 || rgbBValue == -65 || rgbBValue == -1) {
            rgbBLabel = "0";
        }

        return rgbBLabel;
    }

    private static final Map<String, Integer> rgbBBaseValues = new HashMap<String, Integer>() {
        {
            put("0", 63);
            put("1", 62);
            put("2", 61);
            put("3", 60);
            put("4", 59);
            put("5", 58);
            put("6", 57);
            put("7", 56);
            put("8", 55);
            put("9", 54);
            put("10", 53);
            put("11", 52);
            put("12", 51);
            put("13", 50);
            put("14", 49);
            put("15", 48);
            put("16", 47);
            put("17", 46);
            put("18", 45);
            put("19", 44);
            put("20", 43);
            put("21", 42);
            put("22", 41);
            put("23", 40);
            put("24", 39);
            put("25", 38);
            put("26", 37);
            put("27", 36);
            put("28", 35);
            put("29", 34);
            put("30", 33);
            put("31", 32);
            put("32", 31);
            put("33", 30);
            put("34", 29);
            put("35", 28);
            put("36", 27);
            put("37", 26);
            put("38", 25);
            put("39", 24);
            put("40", 23);
            put("41", 22);
            put("42", 21);
            put("43", 20);
            put("44", 19);
            put("45", 18);
            put("46", 17);
            put("47", 16);
            put("48", 15);
            put("49", 14);
            put("50", 13);
            put("51", 12);
            put("52", 11);
            put("53", 10);
            put("54", 9);
            put("55", 8);
            put("56", 7);
            put("57", 6);
            put("58", 5);
            put("59", 4);
            put("60", 3);
            put("61", 2);
            put("62", 1);
            put("63", 0);
        }
    };

    private static final Map<String, Integer> rgbBGlovesAnkleTapeOffsetValues = new HashMap<String, Integer>() {
        {
            put("N/N", 0);
            put("N/Y", 64);
            put("Y/N", -128);
            put("Y/Y", -64);
        }
    };

    public static int getRgbBGlovesAnkleTapeValue(String rgbBLabel, String glovesLabel, String ankleTapeLabel) {
        int rgbBValue;
        String glovesAnkleTapeLabel = glovesLabel + "/" + ankleTapeLabel;

        int rgbBBaseValue = rgbBBaseValues.get(rgbBLabel);
        int glovesAnkleTapeOffset = rgbBGlovesAnkleTapeOffsetValues.get(glovesAnkleTapeLabel);

        rgbBValue = rgbBBaseValue + glovesAnkleTapeOffset;

        return rgbBValue;
    }

    public static String getBandanaColorLabel(int bandanaColorValue) {
        String bandanaColorLabel = "???";

        if ((bandanaColorValue >= 0 && bandanaColorValue <= 14) || (bandanaColorValue >= -128 && bandanaColorValue <= -114)) {
            bandanaColorLabel = "White";
        }
        else if ((bandanaColorValue >= 16 && bandanaColorValue <= 30) || (bandanaColorValue >= -112 && bandanaColorValue <= -98)) {
            bandanaColorLabel = "Black";
        }
        else if ((bandanaColorValue >= 32 && bandanaColorValue <= 46) || (bandanaColorValue >= -96 && bandanaColorValue <= -82)) {
            bandanaColorLabel = "Red";
        }
        else if ((bandanaColorValue >= 48 && bandanaColorValue <= 62) || (bandanaColorValue >= -80 && bandanaColorValue <= -66)) {
            bandanaColorLabel = "Blue";
        }
        else if ((bandanaColorValue >= 64 && bandanaColorValue <= 78) || (bandanaColorValue >= -64 && bandanaColorValue <= -50)) {
            bandanaColorLabel = "Purple";
        }
        else if ((bandanaColorValue >= 80 && bandanaColorValue <= 94) || (bandanaColorValue >= -48 && bandanaColorValue <= -34)) {
            bandanaColorLabel = "Cyan";
        }
        else if ((bandanaColorValue >= 96 && bandanaColorValue <= 110) || (bandanaColorValue >= -32 && bandanaColorValue <= -18)) {
            bandanaColorLabel = "Yellow";
        }
        else if ((bandanaColorValue >= 112 && bandanaColorValue <= 126) || (bandanaColorValue >= -16 && bandanaColorValue <= -2)) {
            bandanaColorLabel = "Green";
        }

        return bandanaColorLabel;
    }

    private static final Map<String, Integer> bandanaColorMultipliers = new HashMap<String, Integer>() {
        {
            put("White", 0);
            put("Black", 1);
            put("Red", 2);
            put("Blue", 3);
            put("Purple", 4);
            put("Cyan", 5);
            put("Yellow", 6);
            put("Green", 7);
        }
    };

    public static int getShoulderWidthBandanaColorValue(String shoulderWidthLabel, String bandanaColorLabel) {
        int shoulderWidthBandanaColorValue;
        int byteOffset = 16;

        int shoulderWidthBandanaColorBaseValue = physicalOptsByLabel.get(shoulderWidthLabel);
        int bandanaColorMultiplier = bandanaColorMultipliers.get(bandanaColorLabel);

        shoulderWidthBandanaColorValue = shoulderWidthBandanaColorBaseValue + (byteOffset * bandanaColorMultiplier);

        return shoulderWidthBandanaColorValue;
    }

    public static int getFavouredSideValue(String strongFootLabel, String favouredSideLabel){
        int favouredSideValue = 0;

        if (favouredSideLabel.equals("B")) {
            favouredSideValue = 2;
        }
        else if ((strongFootLabel.equals("R") || strongFootLabel.equals("L"))
                && (favouredSideLabel.equals("R") || favouredSideLabel.equals("L"))) {
            if (!strongFootLabel.equals(favouredSideLabel)){
                favouredSideValue = 1;
            }
        }

        return favouredSideValue;
    }

    public static String getGoalCelebrationLabel(int goalCelebrationValue) {
        String goalCelebrationLabel = "???";
        int negativeValueOffset = 128;

        if (goalCelebrationValue == 0 || goalCelebrationValue == -128) {
            goalCelebrationLabel = "N";
        }
        else if (goalCelebrationValue >= 0 && goalCelebrationValue <= 127) {
            goalCelebrationLabel = Integer.toString(goalCelebrationValue);
        }
        else if (goalCelebrationValue >= -128 && goalCelebrationValue <= -1) {
            goalCelebrationLabel = Integer.toString(goalCelebrationValue + negativeValueOffset);
        }

        return goalCelebrationLabel;
    }

    public static int getGoalCelebrationValue(String goalCelebrationLabel) {
        int goalCelebrationValue = 0;

        if (!goalCelebrationLabel.equals("N")) {
            goalCelebrationValue = Integer.parseInt(goalCelebrationLabel);
        }

        return goalCelebrationValue;
    }

    public static String getCapTypeLabel(int capTypeValue) {
        String capTypeLabel = "???";

        if (capTypeValue >= 0 && capTypeValue <= 7) {
            capTypeLabel = "1";
        }
        if (capTypeValue >= 8 && capTypeValue <= 15) {
            capTypeLabel = "2";
        }
        if (capTypeValue >= 16 && capTypeValue <= 23) {
            capTypeLabel = "3";
        }
        if (capTypeValue >= 24 && capTypeValue <= 31) {
            capTypeLabel = "4";
        }
        if (capTypeValue >= 32 && capTypeValue <= 39) {
            capTypeLabel = "5";
        }
        if (capTypeValue >= 40 && capTypeValue <= 47) {
            capTypeLabel = "6";
        }
        if (capTypeValue >= 48 && capTypeValue <= 55) {
            capTypeLabel = "7";
        }
        if (capTypeValue >= 56 && capTypeValue <= 63) {
            capTypeLabel = "8";
        }

        return capTypeLabel;
    }

    public static String getGlassesColorLabel(int glassesColorValue) {
        String glassesColorLabel = "???";
        int valueRange = 8;
        int glassesColor = 1;

        if (glassesColorValue >= 0) {
            glassesColor = (glassesColorValue % valueRange) + 1;
            glassesColorLabel = Integer.toString(glassesColor);
        }

        return glassesColorLabel;
    }

    private static final Map<String, Integer> capTypeBaseValues = new HashMap<String, Integer>() {
        {
            put("1", 0);
            put("2", 8);
            put("3", 16);
            put("4", 24);
            put("5", 32);
            put("6", 40);
            put("7", 48);
            put("8", 56);
        }
    };

    private static final Map<String, Integer> glassesColorOffsetValues = new HashMap<String, Integer>() {
        {
            put("1", 0);
            put("2", 1);
            put("3", 2);
            put("4", 3);
            put("5", 4);
            put("6", 5);
            put("7", 6);
            put("8", 7);
        }
    };

    public static int getCapTypeGlassesColorValue(String capTypeLabel, String glassesColorLabel) {
        int capTypeGlassesColorValue = 0;

        int capTypeBaseValue = capTypeBaseValues.get(capTypeLabel);
        int glassesColorOffsetValue = glassesColorOffsetValues.get(glassesColorLabel);
        
        capTypeGlassesColorValue = capTypeBaseValue + glassesColorOffsetValue;


        return capTypeGlassesColorValue;
    }

    private static final Map<String, Integer> necklaceColorOffsetValues = new HashMap<String, Integer>() {
        {
            put("White", 0);
            put("Black", 1);
            put("Red", 2);
            put("Blue", 3);
            put("Yellow", 4);
            put("Green", 5);
            put("Purple", 6);
            put("Cyan", 7);
        }
    };

    private static final Map<Integer, String> necklaceColorLabels = new HashMap<Integer, String>() {
        {
            put(0, "White");
            put(1, "Black");
            put(2, "Red");
            put(3, "Blue");
            put(4, "Yellow");
            put(5, "Green");
            put(6, "Purple");
            put(7, "Cyan");
        }
    };

    public static String getNecklaceColorLabel(int necklaceColorValue) {
        String necklaceColorLabel = "???";
        int valueRange = 8;
        int necklaceColor = 0;

        if (necklaceColorValue >= 0 && necklaceColorValue <= 120) {
            necklaceColor = necklaceColorValue % valueRange;
        }
        else if (necklaceColorValue >= -128 && necklaceColorValue <= -1) {
            necklaceColor = necklaceColorValue % valueRange;
            if (necklaceColor != 0) {
                necklaceColor += 8;
            }
        }

        necklaceColorLabel = necklaceColorLabels.get(necklaceColor);

        return necklaceColorLabel;
    }

    public static int getWristbandNecklaceColorValue(String wirstbandLabel, String necklaceColorLabel) {
        int wristbandNecklaceColorValue = 0;

        int wristbandBaseValue = wristbandOptsByLabel.get(wirstbandLabel);
        int necklaceColorOffsetValue = necklaceColorOffsetValues.get(necklaceColorLabel);
        
        wristbandNecklaceColorValue = wristbandBaseValue + necklaceColorOffsetValue;

        return wristbandNecklaceColorValue;
    }
}
