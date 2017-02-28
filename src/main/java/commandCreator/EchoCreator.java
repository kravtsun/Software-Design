package commandCreator;

import command.Command;
import command.Echo;

/**
 * Echo command creator
 */
public class EchoCreator extends CommandCreatorFactory {
    @Override
    public Command create() {
        return new Echo();
    }
}
