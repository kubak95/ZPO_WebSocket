package pl.kolaczynskijakub.websocketclient;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.gson.Gson;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server extends WebSocketServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private final ArrayList<WebSocket> connections = new ArrayList<>();

    public Server(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public Server(Object p0) {

    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        LOGGER.info("onOpen");
        connections.add(conn);

    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        LOGGER.info("onClose");
        connections.remove(conn);

    }

    @Override
    public void onMessage(WebSocket conn, String message) {

        try {
            Gson gson = new Gson();
            CRUDOperations operation = gson.fromJson(message, CRUDOperations.class);
            String operationType = operation.operation;
            HibernateUtil hibutl = new HibernateUtil();
            switch (operationType) {
                case "create": {
                    hibutl.crudCreate(operation.data);
                    System.out.println("operation - create");
                    break;
                }
                case "read": {
                    System.out.println("operation - read");
                    conn.send(hibutl.crudRead(operation.data));
                    break;
                }
                case "delete": {
                    hibutl.crudDelete(operation.data);
                    break;
                }
                case "update": {
                    hibutl.crudUpdate(operation.data);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            conn.send("Error during processing your request");
        }

        LOGGER.info("onMessage: {}", message);
        conn.send("How Can I serve you ?");
        connections.stream().filter(ws -> !conn.equals(ws)).forEach(ws -> ws.send(message));

    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        LOGGER.info("onError");

    }

    @Override
    public void onStart() {
        LOGGER.info("onStart");

    }
}
