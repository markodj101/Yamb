package client;

import controller.FirstPageController;
import controller.LobbyController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import server.Lobby;
import server.YambServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;



public class YambPlayerThread extends Thread{
    private final Socket socket;
    private final YambServer server;
    private String username;
    private BufferedReader fromUser;
    private PrintWriter toUser;
    private Lobby lobby;
    private boolean ready;
    private boolean inGame;



    public YambPlayerThread(Socket socket, YambServer server) throws IOException {
        this.socket=socket;
        this.server= server;
        try {
            this.fromUser = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.toUser = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error with streams: " + e.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }
    public boolean isReady() {
        return ready;
    }
    public boolean isInGame() {
        return inGame;
    }
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    public void setReady(boolean ready) {
        this.ready = ready;
    }
    @Override
    public String toString() {
        return username;
    }

    @Override
    public void run() {
        try {
            String userInput;
            while (true) {
                userInput = fromUser.readLine();

                if (userInput == null)
                    break;

                handleRequest(userInput);
            }
        } catch (IOException e) {
            //handleDisconnect();
        }
    }
    private void handleRequest(String command) {
        String[] parts = command.split(" ");

        switch (parts[0]) {
            case "CONNECT":
                handlePlayerConnect(parts[1]);
                break;
//            case "READY":
//                handleSetReady(parts[1]);
//                break;
//            case "PRIVATE_LOBBY":
//                lobby.setPrivateLobby(parts[1].equals("true"));
//                break;
//            case "INVITE":
//                handlePlayerInvite(parts[1], parts[2], parts[3]);
//                break;
//            case "ACCEPT":
//                handleAcceptInvite(parts[1]);
//                break;
//            case "LEAVE":
//                handleLeaveLobby();
//                break;
//            case "START":
//                handleStartGame();
//                break;
//            case "PLAY":
//                //lobby.getUno().playMove(parts[1]);
//                break;
//            case "DISCONNECT":
//                //handleDisconnect();
//                break;
        }
    }
    public void sendResponse(String response) {
        toUser.println(response);
    }
    public void handlePlayerConnect(String username) {
        if (!server.usernameAvailable(username)) {
            sendResponse("CONNECT false " + username);
        }
        else {
            server.addPlayer(this);
            System.out.println("Valja");
            this.username = username;
            sendResponse("CONNECT true " + username);

            server.SendToAll(this, username + " joined server!");
            sendResponse("ADD USER " + server.ConnectedPlayers(this));
            System.out.println(server.players);
            server.SendToAll(this, "ADD USER " + username);
            this.username = username;

            System.out.println(server.players);
        }
    }
    private void handleSetReady(String ready) {
        setReady(ready.equals("true"));
        server.SendToGame(this, lobby, username + " is " + (ready.equals("true") ? "ready!" : "not ready!"));

        if (lobby.arePlayersReady())
            lobby.getAdmin().sendResponse("Players are ready! You can start the game!");
    }
    private void handlePlayerInvite(String lobbyName, String sender, String receiver) {
        YambPlayerThread user = server.getPlayerbyUsername(receiver);


        if (lobby.isPlayerInLobby(user))
            sendResponse("ERROR LOBBY " + user.getUsername() + " is already in lobby!");
        else if (user.isInGame())
            sendResponse("ERROR LOBBY " + user.getUsername() + " is currently in game!");
        else
            user.sendResponse("INVITE " + lobbyName + " " + sender);
    }
    private void handleAcceptInvite(String lobbyName) {
        if (this.lobby != null)
            handleLeaveLobby();


        lobby.addPlayer(this);
        this.lobby = lobby;

        server.SendToGame(this, lobby, this.username + " joined lobby!");
        server.SendToGame(this, lobby, "ADD PLAYER " + this.username);

        sendResponse("JOIN " + lobbyName);
        sendResponse("ADD PLAYER " + server.PlayersInLobby(this, lobby));
    }
    private void handleLeaveLobby() {
        lobby.removePlayer(this);
        sendResponse("LEAVE");
        server.SendToGame(this, lobby, username + " left lobby!");
        server.SendToGame(this, lobby, "REMOVE PLAYER " + username);
        setReady(false);

        if (lobby.isEmpty()) {
            sendResponse("REMOVE LOBBY " + lobby.getLobbyName());
            server.SendToAll(this, "REMOVE LOBBY " + lobby.getLobbyName());
            server.SendToAll(this, "Lobby " + lobby.getLobbyName() + " is removed!");
        } else {
            if (lobby.getAdmin().equals(this)) {
                lobby.setNewAdmin();
                lobby.getAdmin().setReady(true);
                lobby.getAdmin().sendResponse("ADMIN " + lobby.getLobbyName());
                lobby.getAdmin().sendResponse("ADD PLAYER " + server.PlayersInLobby(lobby.getAdmin(), lobby));
                server.SendToGame(lobby.getAdmin(), lobby, lobby.getAdmin().getUsername() + " is new admin!");
            }
        }
    }
    private void handleStartGame() {
        if (lobby.notEnoughPlayers())
            sendResponse("ERROR LOBBY ");
        else if (!lobby.arePlayersReady())
            sendResponse("ERROR LOBBY ");
        else {
            //server.SendInGame(lobby, "START");
            //obby.start();
            lobby.setPlayersInGame(true);
        }
    }
    private void close() {
        try {
            socket.close();
            fromUser.close();
            toUser.close();
        } catch (IOException e) {
            System.err.println("Error with closing resources!");
        }
    }
   /* private void handleDisconnect() {
        server.SendToAll(this, username + " has disconnected!");
        server.SendToAll(this, "REMOVE USER " + username);
        server.SendToAll(this, "REMOVE PLAYER " + username);
        close();
        sendResponse("DISCONNECT");

        if (lobby != null) {
            handleLeaveLobby();
            if (isInGame()) {
                lobby.getUno().removePlayer(this);
                lobby.getUno().returnCardsFromDisconnectedPlayer(deck.getCards());

                if (lobby.getUno().getPlayerOnMove().equals(this) && lobby.getUno().getQueue().size() == 1)
                    lobby.getUno().endGame();
                else {
                    lobby.getUno().setPlayerOnMove(lobby.getUno().getNextPlayer());
                    lobby.getUno().continueGame();
                }
            }
        }

        server.removePlayer(this);
    }*/
}
