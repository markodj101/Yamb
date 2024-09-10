package server;

import controller.LobbyController;
import client.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class YambServer {
    public static final int PORT = 8080;
    private final Set<YambPlayerThread> players; //ovo je za sad kao ClientThread
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
                new YambPlayerThread(playerSocket,this).start();
            }
        }catch (IOException e){
            System.err.println(e.getMessage()); //msg
        }
    }

    public boolean usernameAvailable(String username) {
        for (YambPlayerThread player : players) {
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


    public void addPlayer(YambPlayerThread player) {
        players.add(player);
    }

    public void removePlayer(YambPlayerThread player) {
        players.remove(player);
    }

    public void removeLobby(LobbyController lobby) {
        lobbies.remove(lobby);
    }

    public void addNewLobby(LobbyController lobby) {
        lobbies.add(lobby);
    }

    public YambPlayerThread getPlayerbyUsername(String username) {
        return players.stream().filter(player -> player.getUsername().equals(username)).findFirst().orElse(null); //promjeniti da bude efikasnije
    }
    public LobbyController getPlayerbyLobbyName(String lobbyName) {
        return lobbies.stream().filter(lobby -> lobby.getLobbyName().equals(lobbyName)).findFirst().orElse(null); //promjeniti da bude efikasnije
    }

    public String ConnectedPlayers(YambPlayerThread player){
        synchronized (player){
            return players.stream().filter(p -> p != player).map(YambPlayerThread::toString).collect(Collectors.joining(" "));
        }
    }

    public String PlayersInLobby(YambPlayerThread player, LobbyController lobby){
        synchronized (player){
            return lobby.getPlayers().stream().filter(p -> p != player).map(YambPlayerThread::toString).collect(Collectors.joining(" "));
        }
    }

    public String getLobbies(){
        synchronized (lobbies){
            return lobbies.stream().map(LobbyController::toString).collect(Collectors.joining(" "));
        }
    }

    public void SendToAll(YambPlayerThread player, String msg){
        synchronized (players){
            players.stream().filter(p->p != player).forEach(u -> u.sendResponse(msg)); //fali funkcija
        }
    }


    public void SendToGame(LobbyController lobby, String msg){
        synchronized (lobby.getPlayers()){
            lobby.getPlayers().forEach(p -> p.sendResponse(msg)); //fali funkcija
        }
    }

    public void SendToSpectators(LobbyController lobby, String msg){
        //ova funkcija mozda ne treba za sad!
    }


}
