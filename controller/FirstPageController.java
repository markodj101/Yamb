package controller;
import client.YambPlayerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import server.YambServer;

import java.io.IOException;
import java.net.Socket;

public class FirstPageController {

    @FXML
    private Button createGameBtn;

    @FXML
    private ListView<?> listView;

    @FXML
    private Label nameLabel;

    @FXML
    private Button playAloneBtn;

    String url = "/view/FirstPageView.fxml";
    private GameController controller;
    private LobbyController controller2;

    @FXML
    void AloneBtnClick(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GameView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("YAMB");
            controller = loader.getController();

            controller.setNameLbl(nameLabel.getText());


            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) playAloneBtn.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CreateGameClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LobbyView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("YAMB");
            controller2 = loader.getController();
            controller2.getLVAvailablePlayers().getItems().add(nameLabel.getText());


            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) playAloneBtn.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel.setText(nameLabel);
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
}

