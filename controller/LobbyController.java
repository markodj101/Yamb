package controller;
import client.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import server.YambServer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LobbyController {
    private final Set<YambPlayerThread> players;
    private final YambServer server;
    private YambPlayerThread admin;
    private final String lobbyName;
    private boolean privateLobby;
    private boolean gameStarted;
    @FXML
    private ListView<?> LVAvailablePlayers;

    @FXML
    private ListView<?> LVReadyPlayers;

    @FXML
    private Button inviteBtn;

    @FXML
    private Button startGameBtn;

    @FXML
    void InviteBtnClick(ActionEvent event) {

    }

    @FXML
    void StartGameBtnClick(ActionEvent event) {

    }


    public LobbyController(YambServer server, YambPlayerThread admin, String lobbyName){
        this.server=server;
        this.admin=admin;
        this.lobbyName=lobbyName;
        this.players = Collections.synchronizedSet(new HashSet<>());
        this.players.add(admin);

    }

    public String getLobbyName() {
        return "";
    }


    public Set<YambPlayerThread> getPlayers() {
        return players;
    }
}
