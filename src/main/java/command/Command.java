package command;

import environment.Environment;

/**
 * Abstract class for command implementation
 */
public abstract class Command {

    /**
     * Prototype for calling any command
     *
     * @param state state of a process
     * @param args  command args
     */
    public abstract Environment run(Environment state, String[] args) throws Exception;
}
