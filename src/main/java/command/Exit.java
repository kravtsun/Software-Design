package command;

import environment.Environment;

/**
 * Exit command implementation
 */
public class Exit extends Command {
    @Override
    public Environment run(Environment state, String[] args) throws Exception {
        System.exit(1);
        return state;
    }
}
