package ru.spbau.mit.environment;

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