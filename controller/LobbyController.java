package controller;
import client.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import server.YambServer;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LobbyController {
    private  Set<YambPlayerThread> players;
    private  YambServer server;
    private YambPlayerThread admin;
    private  String lobbyName;
    private boolean privateLobby;
    private boolean gameStarted;
    @FXML
    private ListView<String> LVAvailablePlayers;

    @FXML
    private ListView<String> LVReadyPlayers;

    @FXML
    private Button inviteBtn;

    @FXML
    private Button startGameBtn;
    String url = "/view/LobbyView.fxml";
    GameController controller;
    LobbyController lobbyController;
    FirstPageController firstPageController;
    YambPlayer player;

    @FXML
    void InviteBtnClick(ActionEvent event) {
        String username = lobbyController.getLVAvailablePlayers().getSelectionModel().getSelectedItem();
        if (username == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("List View warning");
            alert.setContentText("Select player from ListView!");
            alert.show();
        }
        else
            player.sendRequest("INVITE " + player.getUsername() + " " + username);
    }

    @FXML
    void StartGameBtnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GameView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("YAMB");
            controller = loader.getController();
            controller.setNameLbl(firstPageController.getNameLabel().getText());
            controller.setLblSumSum("0");
            controller.setScoreLabel("0");
            controller.setAttemptsLbl("3");


            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public LobbyController(YambServer server, YambPlayerThread admin, String lobbyName){
//        this.server=server;
//        this.admin=admin;
//        this.lobbyName=lobbyName;
//        this.players = Collections.synchronizedSet(new HashSet<>());
//        this.players.add(admin);
//
//    }

    public String getLobbyName() {
        return "";
    }


    public Set<YambPlayerThread> getPlayers() {
        return players;
    }

    public String getUrl() {
        return url;
    }
    private void openScene() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(getUrl()));
        Parent root = loader.load();

        Stage primaryStage = new Stage();
        primaryStage.setTitle("YAMB");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public ListView<String> getLVAvailablePlayers() {
        return LVAvailablePlayers;
    }

    public Button getStartGameBtn() {
        return startGameBtn;
    }

    public Button getInviteBtn() {
        return inviteBtn;
    }
}
