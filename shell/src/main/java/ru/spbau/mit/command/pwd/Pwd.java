package ru.spbau.mit.command.pwd;

import ru.spbau.mit.command.Command;
import ru.spbau.mit.environment.Environment;

import java.lang.Exception;

/**
 * Pwd command implementation
 */
public class Pwd implements Command {

    @Override
    public Environment run(Environment state, String[] args) throws Exception {

        String currentDir = System.getProperty("user.dir");
        state.setState(currentDir);
        return state;
    }
}
