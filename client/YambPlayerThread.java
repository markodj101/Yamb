package client;

import server.YambServer;

import java.io.IOException;
import java.net.Socket;



public class YambPlayerThread {
    private final Socket socket;
    private final YambServer server;



    public YambPlayerThread(Socket socket, YambServer server) throws IOException {
        this.socket=socket;
        this.server= server;
    }

    public String getUsername() {
        return "";
    }

    public void start() {

    }
}
