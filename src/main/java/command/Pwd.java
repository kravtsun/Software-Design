package command;

import environment.Environment;

import java.lang.Exception;

/**
 * Pwd command implementation
 */
public class Pwd extends Command {


    @Override
    public Environment run(Environment state, String[] args) throws Exception {

        String currentDir = System.getProperty("user.dir");
        state.setState(currentDir);
        return state;
    }
}
