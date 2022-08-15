package rpc.rsocket.simple;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class RSocketSimpleTest {

    private static final Logger LOG = LoggerFactory.getLogger(RSocketSimpleTest.class);

    private static SimpleServer server;

    public RSocketSimpleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        server = new SimpleServer();
    }

    @AfterClass
    public static void tearDownClass() {
        server.dispose();
    }

    @Test
    public void sendAndForgetTest() throws InterruptedException {
        SimpleClient client = new SimpleClient();
        client.firAndForget();


        TimeUnit.SECONDS.sleep(5);

        client.dispose();
    }

}
