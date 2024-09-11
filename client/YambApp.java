package client;

import controller.FirstPageController;
import controller.GameController;
import controller.LobbyController;
import controller.LoginController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import server.YambServer;

import java.io.IOException;

public class YambApp extends Application {

    //CLIENT VIEW

    private Stage primaryStage;
    private FirstPageController FPController;
    private GameController gameController;
    private LobbyController lobbyController;
    private LoginController loginController;

    private YambServer server;

    private YambPlayer player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        server = new YambServer();

        primaryStage = stage;
        player = new YambPlayer(this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
        Parent root = loader.load();

        loginController = loader.getController();
        loginController.setServer(server);

        primaryStage.setTitle("YAMB");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> closeAlert());
    }


    private void closeAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Please confirm your action!");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                //player.sendRequest("DISCONNECT");
                primaryStage.close();
            }
        });

    }

}
