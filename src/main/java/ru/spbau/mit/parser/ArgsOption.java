package spbau.mit.parser;

import org.apache.commons.cli.Options;

/**
 * Any command arguments
 */
public class ArgsOption {

    /**
     * Any command arguments
     */
    public static Options options = new Options()
            .addOption("i", "ignore-case", false, "Invert the sense of matching, to select non-matching lines")
            .addOption("w", "word-regexp", false, "Select  only  those  lines  containing  matches  that form whole\n" +
                    "              words.  The test is that the matching substring must  either  be\n" +
                    "              at  the  beginning  of  the  line,  or  preceded  by  a non-word\n" +
                    "              constituent character.  Similarly, it must be either at the  end\n" +
                    "              of  the  line  or  followed by a non-word constituent character.\n" +
                    "              Word-constituent  characters  are  letters,  digits,   and   the\n" +
                    "              underscore.")
            .addOption("A", "after-context", true, "Print NUM  lines  of  trailing" +
                    "  context  after  matching  lines.");
}
