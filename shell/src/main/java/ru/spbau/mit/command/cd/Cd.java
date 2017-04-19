package ru.spbau.mit.command.cd;

import ru.spbau.mit.command.Command;
import ru.spbau.mit.environment.Environment;

import java.io.File;

public class Cd implements Command {
    private static final String PWD_PROPERTY = "user.dir";
    private static final String HOME_PROPERTY = "user.home";

    @Override
    public Environment run(Environment state, String[] args) throws Exception {
        String newDir = System.getProperty(HOME_PROPERTY);
        if (args.length > 0) {
            newDir = args[0];
        }
        File newDirFile = new File(newDir);
        if (newDirFile == null || !newDirFile.isDirectory() ) {
            System.out.println("not found directory " + newDir);
        } else {
            String newDirPath = newDirFile.getCanonicalPath();
            System.setProperty(PWD_PROPERTY, newDirPath);
        }
        state.removeEnv();
        return state;
    }
}
