package pl.kolaczynskijakub.websocketclient.pl.kolaczynskijakub;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import pl.kolaczynskijakub.websocketclient.pl.kolaczynskijakub.CRUDOperations;

public class Server extends WebSocketServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private ArrayList<WebSocket> connections = new ArrayList<WebSocket>();

    public Server(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public Server(Object p0) {

    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // TODO Auto-generated method stub
        LOGGER.info("onOpen");
        connections.add(conn);

    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // TODO Auto-generated method stub
        LOGGER.info("onClose");
        connections.remove(conn);

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // TODO Auto-generated method stub

        try {
            Gson gson = new Gson();
            // String json = "{\"operation\":\"update\", \"data\":{\"id\": 1,
            // \"name\":\"Stas\", \"surname\":\"Kucharski\",
            // \"email\":\"skucharski@onet.pl\"}}";
            System.out.println("operation:\n" + message);
            CRUDOperations operation = gson.fromJson(message, CRUDOperations.class);
            String operationType = operation.operation;
            System.out.println("operation - " + message + " operationType - " + operationType);
            switch (operationType) {
                case "create": {
                    operation.crudCreate();
                    break;
                }
                case "read": {
                    operation.crudRead();
                    break;
                }
                case "delete": {
                    operation.crudDelete();
                    break;
                }
                case "update": {
                    operation.crudUpdate();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Not a valid json");
        }

        LOGGER.info("onMessage: {}", message);
        // conn.send("Yes, How Can I Serve You?");
        conn.send("Yes, How Can I serve you ?");
        connections.stream().filter(ws -> !conn.equals(ws)).forEach(ws -> {
            ws.send(message);
        });

    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        // TODO Auto-generated method stub
        LOGGER.info("onError");

    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        LOGGER.info("onStart");

    }
}
