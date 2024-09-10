package server;

import controller.LobbyController;
import client.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class YambServer {
    public static final int PORT = 8080;
    private final Set<YambClientThread> players; //ovo je za sad kao ClientThread
    private final Set<LobbyController> lobbies; //ovo je za sad kao Lobby

    public YambServer() {
        this.players = Collections.synchronizedSet(new HashSet<>());
        this.lobbies = Collections.synchronizedSet(new HashSet<>());

    }


    public static void main(String[] args) {
        YambServer server = new YambServer();
        server.execute();
    }

    private void execute() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server sluša na portu: "+ PORT);
            while (true) {
                Socket playerSocket = serverSocket.accept();
                new YambClientThread(playerSocket,this).start();
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public boolean usernameAvailable(String username) {
        for (YambClientThread player : players) {
            if (player.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    public boolean lobbyNameAvailable(String lobbyName) {
        for (LobbyController lobby : lobbies) {
            if(lobby.getLobbyName().equals(lobbyName)){
                return false;
            }
        }
        return true;
    }
}
