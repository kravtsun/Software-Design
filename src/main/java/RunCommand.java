import environment.Environment;
import command.Command;
import commandCreator.CommandCreate;
import commandCreator.CommandCreatorFactory;
import parser.PreProcess;
import parser.Parser;

import java.util.Arrays;

/**
 * Run commands
 */
public class RunCommand {

    private PreProcess preProcessCommand = new PreProcess();

    /**
     * Get command by name
     *
     * @param nameCommand name command
     * @throws Exception command not found
     */
    private Command chooseCommand(String nameCommand) throws Exception {
        CommandCreatorFactory commandCreatorFactory = CommandCreate.getCommand(nameCommand);
        return commandCreatorFactory.create();
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
    private boolean setEnvitonmentVariable(String command) {
        if (command.indexOf("=") >= 0) {
            String[] envVariable = Parser.parseEnvVariable(command);

            assert (envVariable.length == 2);

            addVariable(envVariable[0], envVariable[1]);
            return true;
        }
        return false;
    }

    /**
     * Parse line on commands
     *
     * @param command line with commands
     * @return array with commands
     */
    private String[] parseCommand(String command) {
        command = Parser.removeSpacesString(command);
        String[] parseCommand = Parser.parseCommand(command);
        parseCommand = Parser.removeSpacesFromArray(parseCommand);
        return parseCommand;
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

            if (setEnvitonmentVariable(command)) {
                return "";
            }

            if (!Parser.checkWeakQuoting(command)) {
                command = preProcessCommand.preprocess(command);
            }

            String[] parseCommand = parseCommand(command);

            if (parseCommand.length == 0) {
                throw new Exception("command isn't name");
            }

            environment = chooseCommand(parseCommand[0]).
                    run(environment, Arrays.copyOfRange(parseCommand, 1, parseCommand.length));
            idx++;
        }
        environment.printState();
        String res = environment.getEnvString();
        environment.removeEnv();
        return res;
    }
}
