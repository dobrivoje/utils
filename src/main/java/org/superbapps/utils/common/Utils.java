package org.superbapps.utils.common;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class Utils {

    public static String getShorterString(String str) {
        return getShorterString(str, 30);
    }

    public static String getShorterString(String str, int maxChars) {
        if (str.length() > maxChars) {
            str = str.substring(0, maxChars);
            str = str.substring(0, str.lastIndexOf(" ")).concat("...");
        }

        return str;
    }

}
