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
            // System.out.println("operation:\n" + message);
            CRUDOperations operation = gson.fromJson(message, CRUDOperations.class);
            String operationType = operation.operation;
            switch (operationType) {
                case "create": {
                    // operation.crudCreate();
                    HibernateUtil.crudCreate(operation.data);
                    System.out.println("operation - create");
                    break;
                }
                case "read": {
                    System.out.println("operation - read");
                    // conn.send(operation.crudRead());
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
            System.out.println(e);
            System.out.println("Not a valid json");
        }

        LOGGER.info("onMessage: {}", message);
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
