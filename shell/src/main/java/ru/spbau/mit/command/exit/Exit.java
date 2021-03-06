package ru.spbau.mit.command.exit;

import ru.spbau.mit.command.Command;
import ru.spbau.mit.environment.Environment;

/**
 * Exit command implementation
 */
public class Exit implements Command {
    @Override
    public Environment run(Environment state, String[] args) throws Exception {
        System.exit(1);
        return state;
    }
}
