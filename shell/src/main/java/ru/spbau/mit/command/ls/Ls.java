package ru.spbau.mit.command.ls;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import ru.spbau.mit.command.Command;
import ru.spbau.mit.environment.Environment;
import ru.spbau.mit.command.pwd.Pwd;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Ls command implementation
 */
public class Ls implements Command {
    private static final String DELIMITER = "\n";

    @Override
    public Environment run(Environment state, String[] args) throws Exception {
        File dir = new File(currentDir());
        ArrayList<String> lsResult = new ArrayList<>();
        if (args.length > 0) {
            for (String arg : args) {
                lsResult.add(lsForPattern(dir, arg));
            }
        } else {
            lsResult.add(lsForPattern(dir, "*"));
        }

        state.setState(String.join(DELIMITER, lsResult));
        return state;
    }

    private static String currentDir() throws Exception {
        Pwd pwdCommand = new Pwd();
        Environment pwdState = new Environment();
        String[] pwdArgs = null;
        return pwdCommand.run(pwdState, pwdArgs).getEnvString();
    }

    private static String lsForPattern(File dir, String pattern) throws IOException {
        ArrayList<String> fileList = new ArrayList<>();
        File patternFile = new File(pattern);
        if (patternFile.isDirectory()) {
            dir = patternFile;
            pattern = "*";
        }

        FileFilter filter = new WildcardFileFilter(pattern);
        File[] files = dir.listFiles(filter);
        URI dirURI = dir.toURI();
        for (File f : files) {
            String filePath = dirURI.relativize(f.toURI()).getPath();
            fileList.add(filePath);
        }
        return String.join(DELIMITER, fileList);
    }
}
