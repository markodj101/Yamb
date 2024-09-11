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

                //handleResponse(response);
            }
        } catch (IOException e) {
            close();
        }
    }

    /*private void handleResponse(String response) {
        String[] parts = response.split(" ", 3);

        switch (parts[0]) {
            case "CONNECT":
                yambApp.handleConnect(parts[1], parts[2]);
                break;
            case "ERROR":
                ViewUtil.showErrorAlert(parts[2]);
                break;
            case "ADD":
                yambApp.handleAddItems(parts[1], parts[2]);
                break;
            case "REMOVE":
                yambApp.handleRemoveItem(parts[1], parts[2]);
                break;
            case "CREATE_LOBBY":
                yambApp.handleCreateLobby(parts[1], parts[2]);
                break;
            case "JOIN":
                yambApp.setLobbyScene(parts[1]);
                break;
            case "INVITE":
                yambApp.showInviteAlert(parts[1], parts[2]);
                break;
            case "LEAVE":
                yambApp.setStartScene();
                break;
            case "START":
                yambApp.setGameScene();
                break;
            case "CURRENT":
                yambApp.setCurrentCard(parts[1]);
                break;
            case "GAME_INFO":
                yambApp.showGameInfo(parts[1], parts[2]);
                break;
            case "FINISH":
                yambApp.showFinishAlert(parts[2]);
                break;
            case "DISCONNECT":
                close();
                break;
            default:
                ViewUtil.setTextLabel(yambApp.getLblMessage(), response);
                break;
        }
    }*/

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
