package command;

import environment.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * cat command implementation
 */
public class Cat extends Command {


    @Override
    public Environment run(Environment state, String[] args) throws Exception {
        if (args.length == 0 && state.getEnvString() == "") {
            state = catFromConsole(state);
        }

        for (String fileName : args) {
            try {
                String str = readFile(fileName);
                state.setState(str);
            } catch (IOException ex) {
                System.out.println("not found file " + fileName);
                return null;
            }
        }
        return state;
    }

    /**
     * Read a string from the console if you do not apply in command args
     * @param state state of process
     * @return final state of process
     */
    private Environment catFromConsole(Environment state) throws Exception {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        while (true) {
            try {
                String line = sc.next();
                state.setState(line);
                System.out.println(line);
            } catch (NoSuchElementException ex) {
                break;
            }
        }
        return state;
    }

    /**
     * Reading file by name
     * @param fileName file name
     * @return had read string
     */
    private String readFile(String fileName) throws IOException {
        String str = "";
        try {
            File file = new File(fileName);
            InputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();
            for (int i = 0; i < size; i++) {
                str += (char) inputStream.read();
            }
            inputStream.close();
        } catch (IOException ex) {
            System.out.println(ex + " read file " + fileName + " in cat command");
            System.exit(1);
        }
        return str;
    }
}
