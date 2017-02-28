package command;

import environment.Environment;
import parser.Parser;

import java.io.*;

/**
 * Wc command implementation
 */
public class Wc extends Command {


    @Override
    public Environment run(Environment state, String[] args) throws Exception {
        if (args.length == 0 && state.getEnvString() == "") {
            throw new Exception("no filename in args");
        }

        if (args.length == 0 && state.getEnvString() != "") {
            String stateString = state.getEnvString();
            stateString = countValueFromString(stateString);
            state.setState(stateString);

        } else {
            for (String fileName : args) {
                String line = countValueFromFile(fileName);
                state.setState(line);
            }
        }

        return state;
    }

    /**
     * Counting lines, words and bytes from string
     *
     * @return string from counted values
     */
    private String countValueFromString(String line) {
        int lineCount = line.split("\n").length;
        int wordsCount = countNotEmptyWords(Parser.removeDuplicateSpaces(line).split(" "));
        int bytes = line.length();

        return String.format("%s %s %s", lineCount, wordsCount, bytes);
    }

    /**
     * Counting lines, words and bytes in file.
     *
     * @param fileName file name
     * @return string from counted values
     */
    private String countValueFromFile(String fileName) throws Exception {
        int lineCount = 0;
        int wordsCount = 0;
        long bytes = 0;

        if (fileName.length() == 0) {
            return null;
        }
        try {
            LineNumberReader file = new LineNumberReader(new FileReader(fileName));
            String line = file.readLine();
            while (line != null) {
                lineCount += 1;
                wordsCount += countNotEmptyWords(Parser.removeDuplicateSpaces(line).split(" "));
                line = file.readLine();
            }

            file.close();
        } catch (Exception ex) {
            System.out.println(ex + " count read lines in file " + fileName);
            System.exit(1);
        }

        bytes = getBytes(fileName);

        return String.format("%s %s %s %s", lineCount, wordsCount, bytes, fileName);
    }

    /**
     * Count not empty words in string array
     *
     * @param str string array
     * @return count words
     */
    private int countNotEmptyWords(String[] str) {
        int count = 0;
        for (String words : str) {
            if (words.length() != 0) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Counts file size in bytes
     *
     * @param fileName file name
     * @return count bytes
     */
    private long getBytes(String fileName) throws IOException {
        long bytes = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(fileName));
            bytes = fileInputStream.getChannel().size();
            fileInputStream.close();

        } catch (IOException ex) {
            System.out.println(ex + " get count bytes from file " + fileName);
            System.exit(1);
        }
        return bytes;
    }
}
