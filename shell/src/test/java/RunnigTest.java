import org.junit.Assert;
import org.junit.Test;
import ru.spbau.mit.parser.Parser;
import ru.spbau.mit.CommandInvoker;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;

//import

public class RunnigTest {
    private CommandInvoker runCommaner = new CommandInvoker();

    @Test
    public void runPwd() throws Exception {
        String line = "   pwd  ";
        runCommaner.run(line);
    }


    @Test
    public void runCat() {
        String fileName = "./README.md";
        try {

            String line = " cat  " + fileName;
            String res = runCommaner.run(line);

            File file = new File(fileName);
            InputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append((char) inputStream.read());
            }
            String s = sb.toString();
            inputStream.close();

            Assert.assertEquals(s, res);
        } catch (Exception ex) {
            System.out.println("file " + fileName + " not fount in dir " + System.getProperty("user.dir"));
            System.exit(0);
        }
    }

    @Test
    public void runEcho() throws Exception {
        String line = "echo     pam_pam    djjdj    askkasd";
        String res = runCommaner.run(line);

        Assert.assertEquals(Parser.removeDuplicateSpaces(line.substring(5)), Parser.removeSpacesString(res));
    }

    @Test
    public void runWc() throws Exception {
        String fileName = "./build.gradle";
        String line = "wc " + fileName;
        String res = runCommaner.run(line);
    }

    @Test
    public void runCd() throws Exception {
        String currentDir = runCommaner.run("pwd");
        String parentDir = currentDir.substring(0, currentDir.lastIndexOf(File.separatorChar));
        String dirName = "..";
        String line = "cd " + dirName;
        String res = runCommaner.run(line);
        Assert.assertThat(res, Matchers.emptyString());
        String newCurrentDir = runCommaner.run("pwd");
        Assert.assertEquals(parentDir, newCurrentDir);
    }

    @Test
    public void runLs() throws Exception {
        String currentDir = runCommaner.run("pwd");
        String currentDirResult = runCommaner.run("ls " + currentDir);
        String emptyDirResult = runCommaner.run("ls");
        Assert.assertEquals(currentDirResult, emptyDirResult);
        String startDirResult = runCommaner.run("ls *");
        Assert.assertEquals(currentDirResult, startDirResult);

        String srcResult = runCommaner.run("ls src" + File.separatorChar);
        String expectedResult = Stream.of("test", "main")
                .sorted()
                .map((s) -> s + "/")
                .collect(Collectors.joining("\n"));
        Assert.assertEquals(expectedResult, srcResult);
        String srcSlashlessResult = runCommaner.run("ls src");
        Assert.assertEquals(expectedResult, srcSlashlessResult);

        String dummyFile = "123-8as;;sfwesadf";
        String dummyFileResult = runCommaner.run("ls " + dummyFile);
        Assert.assertThat(dummyFileResult, Matchers.emptyString());
    }

}


