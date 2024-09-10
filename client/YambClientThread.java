package client;

import server.YambServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class YambClientThread {
    private final Socket socket;
    private final YambServer server;



    public YambClientThread(Socket socket, YambServer server) throws IOException {
        this.socket=socket;
        this.server= server;
    }
}
