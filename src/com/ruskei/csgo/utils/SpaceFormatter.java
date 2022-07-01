package com.ruskei.csgo.utils;

public class SpaceFormatter {

    public static String formatSpaces(String toFormat) {
        String toReturn = toFormat.replace("{space512}", "" + '\uF82E');
        toReturn = toReturn.replace("{space256}", "" + '\uF82D');
        toReturn = toReturn.replace("{space128}", "" + '\uF82C');
        toReturn = toReturn.replace("{space64}", "" + '\uF82B');
        toReturn = toReturn.replace("{space32}", "" + '\uF82A');
        toReturn = toReturn.replace("{space16}", "" + '\uF829');
        toReturn = toReturn.replace("{space8}", "" + '\uF828');
        toReturn = toReturn.replace("{space7}", "" + '\uF827');
        toReturn = toReturn.replace("{space6}", "" + '\uF826');
        toReturn = toReturn.replace("{space5}", "" + '\uF825');
        toReturn = toReturn.replace("{space4}", "" + '\uF824');
        toReturn = toReturn.replace("{space3}", "" + '\uF823');
        toReturn = toReturn.replace("{space2}", "" + '\uF822');
        toReturn = toReturn.replace("{space1}", "" + '\uF821');

        toReturn = toReturn.replace("{0}", "" + '\uE011');
        toReturn = toReturn.replace("{1}", "" + '\uE002');
        toReturn = toReturn.replace("{2}", "" + '\uE003');
        toReturn = toReturn.replace("{3}", "" + '\uE004');
        toReturn = toReturn.replace("{4}", "" + '\uE005');
        toReturn = toReturn.replace("{5}", "" + '\uE006');
        toReturn = toReturn.replace("{6}", "" + '\uE007');
        toReturn = toReturn.replace("{7}", "" + '\uE008');
        toReturn = toReturn.replace("{8}", "" + '\uE009');
        toReturn = toReturn.replace("{9}", "" + '\uE010');

        toReturn = toReturn.replace("{slash}", "" + '\uE001');

        return toReturn;
    }
}
