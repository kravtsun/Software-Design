package ru.spbau.mit.environment;

import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * state of process
 */
public class Environment {


    private String state;

    /**
     * Gets process state
     */
    public String getEnvString() {
        return state;
    }

    /**
     * Sets process state
     *
     * @param str state
     */
    public void setState(String str) {
        state = str;
    }


    /**
     * add string to state
     *
     * @param str
     */
    public void addToState(String str) {
        state += str;
    }


    public void addArrayToState(@NotNull List<String> array) {
        for (String str : array) {
            addToState(str);
        }
    }

    /**
     * print state of process
     */
    public void printState() {
        System.out.println(state);
    }


    /**
     * Removes process state
     */
    public void removeEnv() {
        state = "";
    }

}