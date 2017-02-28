package command;

import environment.Environment;

/**
 * Echo command implementation
 */
public class Echo extends Command {

    @Override
    public Environment run(Environment state, String[] args) throws Exception {
        String lines = "";
        for (String str : args) {
            lines += str + " ";
        }
        lines = lines.replaceAll("[\"]", "");
        state.setState(lines);
        return state;

    }
}
