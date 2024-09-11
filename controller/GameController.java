package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {

    String url = "/view/GameView.fxml";

    @FXML
    private Label nameLbl;

    @FXML
    private Button rollBtn;

    @FXML
    private Label scoreLbl;

    @FXML
    void RollDicesBtnClick(ActionEvent event) {

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

    public void setNameLbl(String string) {
        this.nameLbl.setText(string);
    }
}
