package commandCreator;

import command.Command;
import command.Pwd;

/**
 * Pwd command creator
 */
public class PwdCreator extends CommandCreatorFactory {
    @Override
    public Command create() {
        return new Pwd();
    }
}
