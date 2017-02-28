package commandCreator;

import command.Command;

/**
 * Factory for creating command
 */
public abstract class CommandCreatorFactory {

    /**
     * Prototype function for create command
     *
     * @return created command
     */
    public abstract Command create();
}
