package ru.spbau.mit;

import ru.spbau.mit.command.Command;
import ru.spbau.mit.commandCreator.CommandCreate;
import ru.spbau.mit.environment.Environment;
import ru.spbau.mit.parser.Parser;
import ru.spbau.mit.parser.PreProcess;

import java.util.Arrays;

/**
 * Run commands
 */
public class CommandInvoker {

    private PreProcess preProcessCommand = new PreProcess();

    /**
     * Get command by name
     *
     * @param nameCommand name command
     * @throws Exception command not found
     */
    private Command chooseCommand(String nameCommand) throws Exception {
        return CommandCreate.getCommand(nameCommand);
    }

    /**
     * Add environment variable to table
     *
     * @param name  variable name
     * @param value variable value
     */
    private void addVariable(String name, String value) {
        name = Parser.removeSpacesString(name);
        value = Parser.removeSpacesString(value);
        System.out.println(name + " " + value);
        preProcessCommand.setVariable(name, value);
    }

    /**
     * Parse comman by "=" and set environment variable
     *
     * @param command command
     * @return contains command "="
     */
    private boolean setEnvironmentVariable(String command) {
        if (command.indexOf("=") >= 0) {
            String[] envVariable = Parser.parseEnvVariable(command);

            assert (envVariable.length == 2);

            addVariable(envVariable[0], envVariable[1]);
            return true;
        }
        return false;
    }

    /**
     * Get line from IO, run parsing, check that command arguments are quoted.
     * If full quoted term found, then replace all occurence of it by environment variable.
     * Commands like NAME = VALUE finished by pushing (NAME, VALUE) into table with environment variables
     *
     * @param str : line from IO
     * @return result of commands
     */
    public String run(String str) throws Exception {
        String[] commands = Parser.parseUsePipe(str);

        Environment environment = new Environment();

        int idx = 0;
        for (String command : commands) {

            if (setEnvironmentVariable(command)) {
                return null;
            }

            command = preProcessCommand.preprocess(command);

            String[] parseCommand = Parser.parseCommand(command);

            if (parseCommand.length == 0) {
                System.out.println("not command name");
                return null;
            }

            Command commandRunning = chooseCommand(parseCommand[0]);
            if (commandRunning != null) {
                environment = commandRunning.
                        run(environment, Arrays.copyOfRange(parseCommand, 1, parseCommand.length));
            }
            idx++;
        }
        environment.printState();
        String res = environment.getEnvString();
        environment.removeEnv();
        return res;
    }
}

