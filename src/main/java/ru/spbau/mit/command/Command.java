package ru.spbau.mit.command;

import ru.spbau.mit.environment.Environment;

/**
 * Interface for command implementation
 */
public interface Command {

    /**
     * Prototype for calling any ru.spbau.mit.command
     *
     * @param state state of a process
     * @param args  ru.spbau.mit.command args
     */
    public Environment run(Environment state, String[] args) throws Exception;
}

