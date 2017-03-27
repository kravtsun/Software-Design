package ru.spbau.mit.command;

/**
 * exception handling for unfound command
 */
public class NotFoundCommandNameException extends Exception {
    public NotFoundCommandNameException() {
        System.out.println("not found command name");
    }
}
