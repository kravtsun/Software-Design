package ru.spbau.mit.parser;

import org.apache.commons.cli.*;

/**
 * Parse command args
 */
public class ArgsParser {

    private CommandLineParser commandLineParser = new DefaultParser();
    private CommandLine commandLine;
    private Options options;

    public ArgsParser(Options options) {
        this.options = options;
    }

    /**
     * Set array with command arguments
     *
     * @param args command arguments
     */
    public void setArgs(String[] args) {
        try {
            commandLine = commandLineParser.parse(options, args);
        } catch (ParseException ex) {
            System.out.println("parse failed " + ex);
        }
    }

    /**
     * Checking the existence of argument with name
     *
     * @param nameArg argument name
     * @return true if argument with name exists, otherwise false
     */
    public boolean checkArg(String nameArg) {
        if (commandLine != null && commandLine.getOptions().length != 0) {
            return commandLine.hasOption(nameArg);
        }
        return false;
    }

    /**
     * Get value after argument name
     *
     * @param nameArg argument name
     * @return string with value
     */
    public String getValueOption(String nameArg) {
        if (commandLine != null && commandLine.getOptions().length != 0) {
            return commandLine.getOptionValue(nameArg);
        }
        return null;
    }

    /**
     * Get other arguments
     *
     * @return array of string
     */
    public String[] getArgs() {
        return commandLine.getArgs();
    }

}
