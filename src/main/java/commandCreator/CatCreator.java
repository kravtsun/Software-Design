package commandCreator;

import command.Cat;
import command.Command;

/**
 * Cat command creator
 */
public class CatCreator extends CommandCreatorFactory {
    @Override
    public Command create() {
        return new Cat();
    }
}
