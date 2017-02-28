package commandCreator;

import command.Command;
import command.Wc;

/**
 * Wc command creator
 */
public class WcCreator extends CommandCreatorFactory {
    @Override
    public Command create() {
        return new Wc();
    }
}
