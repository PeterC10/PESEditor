package editor;

import java.util.*;

class CSVAttributes {

    private final int[] wristbandVals = { 0, 8, 16, 24, 40, 48, 56, 72, 80, 88, 104, 112, 120, -120, -112, -104, -88,
            -80, -72, -56, -48, -40, -24, -16, -8 };

    private final String[] wristbandLabels = { "N-None", "L-White", "R-White", "B-White", "L-Black", "R-Black",
            "B-Black", "L-Red", "R-Red", "B-Red", "L-Blue", "R-Blue", "B-Blue", "L-Yellow", "R-Yellow", "B-Yellow",
            "L-Green", "R-Green", "B-Green", "L-Purple", "R-Purple", "B-Purple", "L-Cyan", "R-Cyan", "B-Cyan" };

    private final Map<String, Integer> wristbandOptsByLabel = new HashMap<String, Integer>() {
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

    private final Map<Integer, String> wristbandOptsByValue = new HashMap<Integer, String>() {
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

    private final Map<String, Integer> faceTypesByLabel = new HashMap<String, Integer>() {
        {
            put("Build", 0);
            put("Preset1", 1);
            put("Preset2", 2);
        }
    };

    private final Map<Integer, String> faceTypesByValue = new HashMap<Integer, String>() {
        {
            put(0, "Build");
            put(1, "Preset1");
            put(2, "Preset2");
        }
    };

    /**
     * @return the wristbandVals
     */
    public int[] getWristbandVals() {
        return wristbandVals;
    }

    /**
     * @return the wristbandLabels
     */
    public String[] getWristbandLabels() {
        return wristbandLabels;
    }

    /**
     * @return the wristbandOptsByLabel
     */
    public Map<String, Integer> getWristbandOptsByLabel() {
        return wristbandOptsByLabel;
    }

    /**
     * @return the wristbandOptsByValue
     */
    public Map<Integer, String> getWristbandOptsByValue() {
        return wristbandOptsByValue;
    }

        /**
     * @return the faceTypesByValue
     */
    public Map<Integer, String> getFaceTypesByValue() {
        return faceTypesByValue;
    }

    /**
     * @return the faceTypesByLabel
     */
    public Map<String, Integer> getFaceTypesByLabel() {
        return faceTypesByLabel;
    }
    
}
