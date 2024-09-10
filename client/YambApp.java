package client;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class YambApp extends Application {

    private Stage primaryStage;

    private YambPlayer player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        primaryStage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Yamb");
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
