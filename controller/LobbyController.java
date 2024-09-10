package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class LobbyController {

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

    public String getLobbyName() {
        return "";
    }
}
