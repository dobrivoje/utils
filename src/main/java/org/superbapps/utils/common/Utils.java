package org.superbapps.utils.common;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class Utils {

    public static String getShorterString(String str) {
        return getShorterString(str, 30);
    }

    public static String getShorterString(String str, int maxChars) {
        try {
            if (str.length() > maxChars) {
                str = str.substring(0, maxChars);
                str = str.substring(0, str.lastIndexOf(" ")).concat("...");
            }
        } catch (Exception e) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, "Gre\u0161ka za string=\"{0}\"", str);
        }

        return str;
    }

    public static String getLeadingUpperCapital(String str) {
        return str.substring(0, 1).toUpperCase()
                .concat(str.substring(1).toLowerCase());
    }
}
