package commandCreator;

import command.Command;

/**
 * Exit command creator
 */
public class ExitCreator extends CommandCreatorFactory {
    @Override
    public Command create() {
        return new command.Exit();
    }
}
