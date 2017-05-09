import org.junit.Test;
import ru.spbau.mit.CommandInvoker;

public class GrepTest {

    @Test
    public void checkGrep() throws Exception {
        String grep = "grep plugin ./build.gradle";
        System.out.println(grep);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.run(grep);
    }

    @Test
    public void checkGrepWithPipe() throws Exception {
        String grep = "cat ./build.gradle | grep group";
        System.out.println(grep);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.run(grep);
    }

    @Test
    public void checkGrepTestFile() throws Exception {
        String grep = "cat ./testGrep.txt | grep test";
        System.out.println(grep);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.run(grep);
    }

    @Test
    public void checkGrepWithW() throws Exception {
        String grep = "grep -w grou ./build.gradle";
        System.out.println(grep);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.run(grep);
    }

    @Test
    public void checkGrepWithI() throws Exception {
        String grep = "grep -i test ./testGrep.txt";
        System.out.println(grep);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.run(grep);
    }

    @Test
    public void checkGrepWithA() throws Exception {
        String grep = "grep -A 2 group ./build.gradle";
        System.out.println(grep);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.run(grep);
    }

    @Test
    public void checkGrepWithAFaild() throws Exception {
        String grep = "grep -A plugin build.gradle";
        System.out.println(grep);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.run(grep);
    }

    @Test
    public void checkGrepWithASucc() throws Exception {
        String grep = "grep -A 3 plugin build.gradle";
        System.out.println(grep);
        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.run(grep);
    }
}
