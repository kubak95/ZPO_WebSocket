package pl.kolaczynskijakub.websocketclient;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] input) throws UnknownHostException {
        System.out.println("Works");
        LOGGER.info("Start application");

        Server s = new Server(8887);
        s.start();
    }

}
