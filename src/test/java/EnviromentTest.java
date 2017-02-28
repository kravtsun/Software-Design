import environment.Environment;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class EnviromentTest {

    @Test
    public void checkEnv() throws IOException {
        Environment environment = new Environment();

        String str = "asdasd  \n " +
                "sadkadskl  \n" +
                "smdkff\n";

        environment.setState(str);

        Assert.assertEquals(str, environment.getEnvString());

        environment.removeEnv();

        str = "1234";

        environment.setState(str);

        Assert.assertEquals(str, environment.getEnvString());
    }
}
