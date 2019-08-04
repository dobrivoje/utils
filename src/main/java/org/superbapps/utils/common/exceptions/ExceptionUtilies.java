package org.superbapps.utils.common.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Arrays;

/**
 * @author д06ри, dobri7@gmail.com
 */
public class ExceptionUtilies {

    public static String StackTrace(Exception exception) {
        return StackTrace(exception, "NL");
    }

    public static String StackTrace(Exception exception, String separator) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(ExceptionUtils.getStackFrames(exception)).forEach(e -> sb.append(e).append(separator));

        return sb.toString();
    }
}
