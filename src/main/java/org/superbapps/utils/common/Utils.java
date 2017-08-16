package org.superbapps.utils.common;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author д06ри, dobri7@gmail.com
 */
public class Utils {

    public static final String NL = String.format("%n", "");

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

    /**
     * Get a string list from a collection. Iterate through a collection, and
     * make a string with "new line/break" termination sign.
     *
     * @param <T> generic type
     * @param collection Collection
     *
     * @return Formatted string in nice form
     */
    public static <T> String getListFromCollection(Collection<T> collection) {
        StringBuilder sb = new StringBuilder();

        collection.stream().forEach((elem) -> {
            sb.append(elem).append(NL);
        });

        return sb.toString();
    }

}
