package server;

import client.YambPlayerThread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Lobby {
    private final YambServer server;
    private YambPlayerThread admin;
    private final String lobbyName;
    private final Set<YambPlayerThread> players;
    private boolean privateLobby;
    private boolean gameStarted;
    //private Uno uno;


    public Lobby(YambServer server, YambPlayerThread admin, String lobbyName) {
        this.server = server;
        this.admin = admin;
        this.lobbyName = lobbyName;
        this.players = Collections.synchronizedSet(new HashSet<>());
        this.players.add(admin);
    }

//    public void start() {
//        uno = new Uno(server, this, players);
//        players.forEach(player -> player.sendResponse("CARDS CARDS " + player.getDeck()));
//        gameStarted = true;
//        server.SendInGame(this, "BLOCK");
//        uno.getPlayerOnMove().sendResponse(uno.getPlayerOnMove().getDeck().getPlayableCards(uno.getCurrentCard(), uno.getCurrentColor(), false));
//    }


    public String getLobbyName() {
        return lobbyName;
    }


    public Set<YambPlayerThread> getPlayers() {
        return players;
    }

    public YambPlayerThread getAdmin() {
        return admin;
    }
    public boolean isPrivateLobby() {
        return privateLobby;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

//    public Uno getUno() {
//        return uno;
//    }

    public void setPrivateLobby(boolean privateLobby) {
        this.privateLobby = privateLobby;
    }

    public void setNewAdmin() {
        admin = players.iterator().next();
    }

    public boolean isPlayerInLobby(YambPlayerThread player) {
        return players.contains(player);
    }

    public void addPlayer(YambPlayerThread user) {
        players.add(user);
    }

    public void removePlayer(YambPlayerThread user) {
        players.remove(user);
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public boolean notEnoughPlayers() {
        return players.size() == 1;
    }

    public boolean arePlayersReady() {
        return players.stream().allMatch(YambPlayerThread::isReady);
    }

    public void setPlayersInGame(boolean inGame) {
        players.forEach(player -> player.setInGame(inGame));
    }

    @Override
    public String toString() {
        return lobbyName;
    }
}
