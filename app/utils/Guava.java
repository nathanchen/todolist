package utils;

/**
 * User: nathanchen
 * Date: 30/01/2014
 * Time: 10:23 PM
 * Description: those in Guava.jar but in beta version
 */
public class Guava
{
    /**
     * Parses the specified string as a signed decimal long value. The ASCII
     * character {@code '-'} (<code>'&#92;u002D'</code>) is recognized as the
     * minus sign.
     *
     * <p>Unlike {@link Long#parseLong(String)}, this method returns
     * {@code null} instead of throwing an exception if parsing fails.
     *
     * <p>Note that strings prefixed with ASCII {@code '+'} are rejected, even
     * under JDK 7, despite the change to {@link Long#parseLong(String)} for
     * that version.
     *
     * @param string the string representation of a long value
     * @return the long value represented by {@code string}, or {@code null} if
     *     {@code string} has a length of zero or cannot be parsed as a long
     *     value
     * @since 14.0
     */
    public static Long tryParse(String string) {
        if (string == null) {
            return null;
        }
        boolean negative = string.charAt(0) == '-';
        int index = negative ? 1 : 0;
        if (index == string.length()) {
            return null;
        }
        int digit = string.charAt(index++) - '0';
        if (digit < 0 || digit > 9) {
            return null;
        }
        long accum = -digit;
        while (index < string.length()) {
            digit = string.charAt(index++) - '0';
            if (digit < 0 || digit > 9 || accum < Long.MIN_VALUE / 10) {
                return null;
            }
            accum *= 10;
            if (accum < Long.MIN_VALUE + digit) {
                return null;
            }
            accum -= digit;
        }

        if (negative) {
            return accum;
        } else if (accum == Long.MIN_VALUE) {
            return null;
        } else {
            return -accum;
        }
    }
}
