package ru.spbau.mit.parser;

import java.util.regex.Pattern;

/**
 * parse string: split and remove spaces
 */
public final class Parser {
    private Parser() {

    }

    /**
     * Get list of commands from input string
     *
     * @param str input string
     * @return list of commands
     */
    public static String[] parseUsePipe(String str) {
        return str.split(Pattern.quote("|"));
    }

    /**
     * Parses command name and it's arguments from string representation
     *
     * @param command string with command
     * @return name command and it's arguments
     */
    public static String[] parseCommand(String command) {
        String[] commands = removeDuplicateSpaces(command).split(" ");
        return removeSpacesFromArray(commands);
    }

    /**
     * Parses name and value for variable
     *
     * @param command string with command
     * @return list of names with values
     */
    public static String[] parseEnvVariable(String command) {
        return removeDuplicateSpaces(command).split("=");
    }

    /**
     * Checks weak quoting
     *
     * @param command
     * @return
     */
    public static boolean checkWeakQuoting(String command) {
        return command.indexOf("'") >= 0 && command.lastIndexOf("'") > command.indexOf("'")
                && command.indexOf("\"") < 0;
    }

    /**
     * Filter out spaces from array
     *
     * @param str input string array
     * @return string array without spaces
     */
    public static String[] removeSpacesFromArray(String[] str) {
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].replaceAll("^\\s+|\\s+$", "");
        }
        return str;
    }

    /**
     * Filter out spaces from string
     *
     * @param str input string
     * @return string without spaces
     */
    public static String removeSpacesString(String str) {
        return str.replaceAll("^\\s+|\\s+$", "");
    }

    /**
     * Folding spaces
     *
     * @param str input string
     * @return string with folded spaces
     */
    public static String removeDuplicateSpaces(String str) {
        return str.replaceAll("\\s{2,}", " ").trim();
    }
}
