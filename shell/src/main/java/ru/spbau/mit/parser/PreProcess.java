package ru.spbau.mit.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Saving enviroment variables and replace variable names on values
 */
public class PreProcess {

    /**
     * table with environment variable
     */
    private HashMap<String, String> variable = new HashMap<>();

    private static final String nameFile = "./EnvVariable.txt";

    public PreProcess() {
        File variablesFile = new File(nameFile);
        try {
            if (variablesFile.exists()) {
                Scanner fileWithVariable = new Scanner(variablesFile);
                while (fileWithVariable.hasNext()) {
                    variable.put(fileWithVariable.next(), fileWithVariable.next());
                }
            } else {
                if (!variablesFile.createNewFile())
                    throw new Exception("file don't creat " + nameFile);
            }
        } catch (Exception ex) {
            System.out.println(ex + "    file is not found" + nameFile);
            System.exit(0);
        }
    }

    /**
     * Set value for enviroment variable
     *
     * @param name  name of new environment variable
     * @param value value of new environment variable
     */
    public void setVariable(String name, String value) {
        try {
            FileWriter file = new FileWriter(nameFile, true);
            file.write(name + " " + value + "\n");
            variable.put(name, value);
            file.close();
        } catch (IOException ex) {
            System.out.println("file is not found " + nameFile);
        }
    }

    /**
     * get environment variable
     *
     * @param name name environment variable
     */
    public String getVariable(String name) {
        return variable.get(name);
    }

    /**
     * Check variable exsistence by it's name
     */
    public boolean hasVariable(String name) {
        return variable.containsKey(name);
    }

    /**
     * replaces all environment variables with their value
     *
     * @param str name of environment variable
     */
    public String preprocess(String str) {
        if (Parser.checkWeakQuoting(str)) {
            return str;
        }

        int idx = str.indexOf("$");

        while (idx >= 0) {
            int idxNext = str.substring(idx).indexOf("/") + idx;
            if (idxNext - idx < 0) {
                idxNext = str.substring(idx).indexOf(" ") + idx;
                if (idxNext - idx < 0) {
                    idxNext = str.substring(idx).indexOf("\"") + idx;
                    if (idxNext - idx < 0) {
                        idxNext = str.length();
                    }
                }
            }
            idx += 1;
            str = str.substring(0, idx) + variable.get(str.substring(idx, idxNext)) + str.substring(idxNext);
            str = str.replaceFirst("[$]", "");
            idx = str.indexOf("$");
        }
        str = str.replaceAll("[\"]", "");
        return str;
    }
}