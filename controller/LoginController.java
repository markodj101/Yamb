package controller;
import client.YambApp;
import client.YambPlayer;
import client.YambPlayerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.YambServer;

import java.io.IOException;
import java.net.Socket;

public class LoginController {

    @FXML
    private TextField textField;

    @FXML
    private Button loginBtn;
    private Socket socket;

    private YambServer server = YambServer.getInstance();
    private YambPlayerThread player;
    String url = "/view/LoginView.fxml";


    @FXML
    void LoginBtnClick(ActionEvent event) throws IOException {
        String username = textField.getText();
        System.out.println("Login button clicked!");

        if (username.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Text field warning");
            alert.setContentText("Text field is empty!");
            alert.show();
        }else if(!server.usernameAvailable(username) && server.getPlayers().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Text field warning");
            alert.setContentText("Username is not available!");
            alert.show();
        }else{
            socket = new Socket("localhost", YambServer.PORT);

            player = new YambPlayerThread(socket, server);

            player.handlePlayerConnect(username);
            server.getPlayers().add(player);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FirstPageView.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("YAMB");
                FirstPageController controller = loader.getController();

                controller.setNameLabel(username);
                System.out.println(server.getPlayers().toString());

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                currentStage.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public String getUrl() {
        return url;
    }

    public void setServer(YambServer server) {
        this.server = server;
    }

    public TextField getTextField() {
        return textField;
    }
}
