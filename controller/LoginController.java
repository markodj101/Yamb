package controller;
import client.YambPlayer;
import client.YambPlayerThread;
import javafx.application.Platform;
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
    private YambPlayer player;
    String url = "/view/LoginView.fxml";


    public void usernameAvaliable(String indicator, String username) {
        Platform.runLater(() -> {
            if (!usernameValidation(textField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Text field warning");
                alert.setContentText("First letter must be uppercase!\nUsername length must be min 3 characters!\nUsername lenght must be max 8 characters\n");
                alert.show();
            } else if (indicator.equals("true")) {
                switchPage();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Text field warning");
                alert.setContentText("Username " + username + " is already used!");
                alert.show();
            }
        });
    }

    private void switchPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FirstPageView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("YAMB");
            FirstPageController controller = loader.getController();
            controller.getNameLabel().setText(textField.getText());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) loginBtn.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void LoginBtnClick(ActionEvent event) throws IOException {
        String username = textField.getText();
        System.out.println(username);
        System.out.println("Login button clicked!");
        player.sendRequest("CONNECT " + username);

    }

    public void setPlayer(YambPlayer player){
        this.player = player;
    }

    public Button getLoginBtn() {
        return loginBtn;
    }

    public String getUrl() {
        return url;
    }

    public TextField getTextField() {
        return textField;
    }

    private boolean usernameValidation(String string){
        String[] letters = string.split("");
        if (letters.length < 3 || letters.length > 8 || Character.isLowerCase(string.charAt(0)))
            return false;

        return true;
    }
}
