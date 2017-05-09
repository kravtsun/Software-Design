import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.command.grep.GrepArgsOption;
import ru.spbau.mit.parser.ArgsParser;
import ru.spbau.mit.parser.Parser;

public class ArgsParserTest {

    private ArgsParser argsParser = new ArgsParser(GrepArgsOption.getOptions());

    @Test
    public void checkArgsWithIWA() {
        String grepSamples = " grep -i -w -A 2 file";
        String[] args = Parser.parseCommand(grepSamples);

        argsParser.setArgs(args);

        Assert.assertTrue(argsParser.checkArg("i"));
        Assert.assertTrue(argsParser.checkArg("w"));
        Assert.assertTrue(argsParser.checkArg("A"));
        Assert.assertEquals("2", argsParser.getValueOption("A"));
    }

    @Test
    public void checkArgsWithI() {
        String str = "grep -i";
        String[] args = Parser.parseCommand(str);
        argsParser.setArgs(args);

        Assert.assertTrue(argsParser.checkArg("i"));
        Assert.assertFalse(argsParser.checkArg("w"));
        Assert.assertFalse(argsParser.checkArg("A"));
    }


    @Test
    public void checkArgsWithW() {
        String str = "grep -w";
        String[] args = Parser.parseCommand(str);
        argsParser.setArgs(args);

        Assert.assertFalse(argsParser.checkArg("i"));
        Assert.assertTrue(argsParser.checkArg("w"));
        Assert.assertFalse(argsParser.checkArg("A"));
    }

    @Test
    public void checkArgsWithA() {
        String str = "grep -A 10";
        String[] args = Parser.parseCommand(str);
        argsParser.setArgs(args);

        Assert.assertFalse(argsParser.checkArg("i"));
        Assert.assertFalse(argsParser.checkArg("w"));
        Assert.assertTrue(argsParser.checkArg("A"));

        Assert.assertEquals("10", argsParser.getValueOption("A"));
    }
}

