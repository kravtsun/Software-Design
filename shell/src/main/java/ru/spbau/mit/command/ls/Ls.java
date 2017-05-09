package ru.spbau.mit.command.ls;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import ru.spbau.mit.command.Command;
import ru.spbau.mit.environment.Environment;
import ru.spbau.mit.command.pwd.Pwd;

import java.io.File;
import java.io.FileFilter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ls command implementation
 */
public class Ls implements Command {
    private static final String DELIMITER = "\n";

    @Override
    public Environment run(Environment state, String[] args) {
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

    /**
     *
     * @return current working directory.
     */
    private static String currentDir() {
        Pwd pwdCommand = new Pwd();
        Environment pwdState = new Environment();
        String[] pwdArgs = null;
        return pwdCommand.run(pwdState, pwdArgs).getEnvString();
    }

    /**
     *
     * @param dir directory in which to search for files.
     * @param pattern hard pattern (without wildcards) for files.
     * @return list of files, concatenated into one line for output.
     */
    private static String lsForPattern(File dir, String pattern) {
        File patternFile = new File(pattern);
        if (patternFile.isDirectory()) {
            dir = patternFile;
            pattern = "*";
        }

        ArrayList<String> fileList = new ArrayList<>();
        FileFilter filter = new WildcardFileFilter(pattern);
        File[] files = dir.listFiles(filter);
        URI dirURI = dir.toURI();
        if (!Objects.isNull(files)) {
            for (File f : files) {
                String filePath = dirURI.relativize(f.toURI()).getPath();
                if (f.isDirectory() && filePath.charAt(filePath.length() - 1) != '/') {
                    filePath += "/";
                }
                fileList.add(filePath);
            }
        }

        return fileList.stream().sorted().collect(Collectors.joining(DELIMITER));
    }
}
