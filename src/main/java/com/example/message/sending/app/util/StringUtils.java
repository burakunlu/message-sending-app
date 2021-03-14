package com.example.message.sending.app.util;

/**
 * This class contains common String functions
 *
 * @author Burak Unlu
 */
public class StringUtils {

    /**
     * Constructor that is set private in order to prevent creating instances
     *
     * @throws IllegalStateException
     */
    private StringUtils() {
        throw new IllegalStateException("Utils class");
    }

    /**
     * Function that extracts user name from commandline arguments
     *
     * @param args String[] commandline arguments
     * @return first message
     * @throws IllegalArgumentException
     */
    public static String getConversationType(String[] args) {

        if (args.length > 0 && isStringValid(args[0])) {
            return args[0];
        }

        throw new IllegalArgumentException("Conversation type is not valid");
    }

    /**
     * Function that extracts first message from commandline arguments
     *
     * @param args String[] commandline arguments
     * @return first message
     * @throws IllegalArgumentException
     */
    public static String getFirstMessage(String[] args) {

        if (args.length > 1 && isStringValid(args[1])) {
            return args[1];
        }

        throw new IllegalArgumentException("First message is not valid");
    }

    /**
     * Function that extracts user name from commandline arguments
     *
     * @param args String[] commandline arguments
     * @return first message
     * @throws IllegalArgumentException
     */
    public static String getUserName(String[] args) {

        if (args.length > 2 && isStringValid(args[2])) {
            return args[2];
        }

        throw new IllegalArgumentException("User name is not valid");
    }

    /**
     * Function that extracts user name from commandline arguments
     *
     * @param value String to be checked
     * @return Boolean
     */
    public static Boolean isStringValid(String value) {
        return value != null && !value.equals("");
    }

}
