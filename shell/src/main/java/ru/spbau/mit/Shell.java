package ru.spbau.mit;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class for start
 */
public final class Shell {

    private Shell() {

    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        CommandInvoker commandInvoker = new CommandInvoker();
        while (true) {
            try {
                String line = sc.next();
                commandInvoker.run(line);
            } catch (NoSuchElementException ex) {
                break;
            }
        }
    }
}
