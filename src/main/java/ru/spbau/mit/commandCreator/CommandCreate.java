package ru.spbau.mit.commandCreator;

import ru.spbau.mit.command.*;

/**
 * Choose command by command name
 */
public class CommandCreate {

    /**
     * Get command by name
     *
     * @param nameCommand name ru.spbau.mit.command
     * @return created ru.spbau.mit.command
     * @throws Exception ru.spbau.mit.command name not found
     */
    public static Command getCommand(String nameCommand) throws Exception {
        Command command;

        switch (nameCommand) {
            case "pwd":
                command = new Pwd();
                break;

            case "echo":
                command = new Echo();
                break;

            case "cat":
                command = new Cat();
                break;

            case "wc":
                command = new Wc();
                break;

            case "exit":
                command = new Exit();
                break;

            default:
                NotFoundCommandNameException ex = new NotFoundCommandNameException();
                return null;
        }
        return command;
    }

}
