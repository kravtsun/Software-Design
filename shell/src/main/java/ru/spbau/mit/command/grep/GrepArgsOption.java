package ru.spbau.mit.command.grep;

import org.apache.commons.cli.Options;

/**
 * Grep command arguments
 */
public final class GrepArgsOption {

    private GrepArgsOption() {

    }

    /**
     * Grep command arguments
     */
    private static Options options = new Options()
            .addOption("i", "ignore-case", false,
                    "Invert the sense of matching, to select non-matching lines")
            .addOption("w", "word-regexp", false,
                    "Select  only  those  lines  containing  matches  that form whole\n"
                            + "              words.  The test is that the matching substring must  either  be\n"
                            + "              at  the  beginning  of  the  line,  or  preceded  by  a non-word\n"
                            + "              constituent character.  Similarly, it must be either at the  end\n"
                            + "              of  the  line  or  followed by a non-word constituent character.\n"
                            + "              Word-constituent  characters  are  letters,  digits,   and   the\n"
                            + "              underscore.")
            .addOption("A", "after-context", true, "Print NUM  lines  of  trailing"
                    + "  context  after  matching  lines.");

    public static Options getOptions() {
        return options;
    }

    public static void setOptions(Options options) {
        GrepArgsOption.options = options;
    }
}
