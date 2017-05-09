package ru.spbau.mit.command;

import ru.spbau.mit.environment.Environment;

/**
 * Interface for command implementation
 */
public interface Command {

    /**
     * Prototype for calling any command
     *
     * @param state state of a process
     * @param args  command args
     */
    Environment run(Environment state, String[] args) throws Exception;
}

