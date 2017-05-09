package ru.spbau.mit.command.grep;

import ru.spbau.mit.command.Command;
import ru.spbau.mit.environment.Environment;
import ru.spbau.mit.parser.ArgsParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Grep command implementation
 */
public class Grep implements Command {

    private ArgsParser argsParser = new ArgsParser(GrepArgsOption.getOptions());

    @Override
    public Environment run(Environment state, String[] args) throws Exception {
        if (args.length == 0 && state.getEnvString() == "") {
            System.out.println("file and pattern for grep command not found");
            return state;
        }

        List<String> matches;

        if (args.length == 1 && state.getEnvString() != "") {
            String pattern = args[0];
            String stateString = state.getEnvString();

            matches = findPatternInString(stateString, pattern);
        } else {
            argsParser.setArgs(args);
            String[] argsFromArgParser = argsParser.getArgs();

            matches = findPatternInFiles(argsFromArgParser);
            if (matches == null) {
                System.out.println("command is not correct");
                return state;
            }
        }

        state.removeEnv();
        state.addArrayToState(matches);

        return state;
    }

    /**
     * Find pattern in each lines of file
     *
     * @param args array with pattern and file names
     * @return list string with pattern
     */
    private List<String> findPatternInFiles(String[] args) {
        if (args.length < 2) {
            return null;
        }
        List<String> matchesAllFiles = new ArrayList<String>();
        String pattern = args[0];
        for (String fileName : Arrays.copyOfRange(args, 1, args.length)) {
            try {
                String linesFile = readFile(fileName);
                List<String> matches = findPatternInString(linesFile, pattern);
                matchesAllFiles.addAll(matches);
            } catch (IOException ex) {
                System.out.println("file not found " + fileName);
            }
        }
        return matchesAllFiles;
    }

    /**
     * Find pattern in text (one line)
     *
     * @param lines   text must be only one line
     * @param pattern pattern
     * @return list string with pattern
     */
    private List<String> findPatternInString(String lines, String pattern) {
        String[] linesArray = lines.split("\n");
        List<String> linesWithPattern = new ArrayList<String>();

        boolean argsA = argsParser.checkArg("A");
        int countSaveLines = 0;
        if (argsA) {
            countSaveLines = Integer.parseInt(argsParser.getValueOption("A"));
        }
        for (int i = 0; i < linesArray.length; i++) {
            if (checkPattern(pattern, linesArray[i])) {
                linesWithPattern.add(linesArray[i] + "\n");
                i++;
                for (int j = 0; j < countSaveLines && i < linesArray.length; j++, i++) {
                    linesWithPattern.add(linesArray[i] + "\n");
                }
            }
        }
        return linesWithPattern;
    }

    /**
     * check exist patern in line
     */
    private boolean checkPattern(String pattern, String line) {
        if (argsParser.checkArg("i")) {
            return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(line).find();
        }
        if (argsParser.checkArg("w")) {
            return Pattern.compile("\\s" + pattern + "\\s").matcher(line).find();
        }
        return Pattern.compile(pattern).matcher(line).find();
    }

    /**
     * Read file
     *
     * @param fileName file name
     * @return text in one line
     */
    private String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
