package controller;
import client.YambPlayer;
import client.YambPlayerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import server.YambServer;

public class LoginController {

    @FXML
    private TextField textField;

    private YambServer server;
     YambPlayerThread player;

    
    

    @FXML
    void LoginBtnClick(ActionEvent event) {
        String username = textField.getText();
        System.out.println("Login button clicked!");
        
        if (username.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Text field warning");
            alert.setContentText("Text field is empty!");
            alert.show();
        }else if(!server.usernameAvailable(username)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Text field warning");
            alert.setContentText("Username is not available!");
            alert.show();
        }else{
            player.handlePlayerConnect(username);
        }
    }


}
