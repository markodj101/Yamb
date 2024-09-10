package client;

import server.YambServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class YambPlayer extends Thread{

    private InetAddress address;
    private int port;
    private Socket socket;
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private String username;
    private YambApp yambApp;

    public YambPlayer(YambApp yambApp) {
        try {
            this.port = YambServer.PORT;
            this.address = InetAddress.getByName("localhost");
            this.socket = new Socket(address, port);
            this.fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.toServer = new PrintWriter(socket.getOutputStream(), true);
            this.yambApp = yambApp;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public void run() {
        try {
            while (true) {
                String response = fromServer.readLine();

                if (response == null) {
                    System.err.println("Connection lost!");
                    break;
                }

               // handleResponse(response);
            }
        } catch (IOException e) {
            close();
        }
    }

    private void close() {
        try {
            socket.close();
            fromServer.close();
            toServer.close();
        } catch (IOException e) {
            System.err.println("Error with closing resources!");
        }
    }


}
