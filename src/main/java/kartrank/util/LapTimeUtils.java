package kartrank.util;

/**
 * Helper class to format milliseconds to strings
 */
public class LapTimeUtils {

    /**
     * Format milliseconds to ISO-8601 based string representing the lap time
     *
     * @param milliseconds lap time in milliseconds
     * @return the formatted lap time
     */
    public static String format(long milliseconds) {
        long minutes = milliseconds / 60000;
        milliseconds = milliseconds % 60000;
        long seconds = milliseconds / 1000;
        milliseconds = milliseconds % 1000;

        return minutes + ":" + String.format("%02d", seconds) + "." + milliseconds;
    }
}
