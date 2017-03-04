package ru.spbau.mit.parser;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import spbau.mit.parser.ArgsOption;

/**
 * Parse command args
 */
public class ArgsParser extends BasicParser {

    private CommandLine commandLine;

    /**
     * Set array with command arguments
     *
     * @param args command arguments
     */
    public void setArgs(String[] args) {
        try {
            commandLine = this.parse(ArgsOption.options, args);
        } catch (ParseException ex) {
            System.out.println("parse failed " + ex);
        }
    }

    /**
     * Сheck what exist argments with name
     *
     * @param nameArg argument name
     * @return true or fasle
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

    @Override
    /**
     * flatten the options and arguments string
     * @param arg0 options
     * @param arg1 argument string
     * @param arg2 boolean flag
     * @return string array of flattened arguments
     */
    protected String[] flatten(Options arg0, String[] arg1, boolean arg2) {
        return super.flatten(arg0, arg1, arg2);
    }
}
