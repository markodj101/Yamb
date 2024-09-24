package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SelectController {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btnCenter;

    @FXML
    private Button btnLeft;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private Label lbl4;

    @FXML
    private Label lbl5;

    @FXML
    private Label lbl6;

    @FXML
    private Label lblDown;

    @FXML
    private Label lblUp;
    private GameController controller;
    private SelectController controller1;

    @FXML
    void LeftBtnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.setLeft(btnLeft.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    void btn1Click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure?");

        alert.showAndWait().ifPresent(response -> {
            // matchuj sa odredjenim lblom na GameControlleru i uradi if petlju sa pitanjem da li je polje empty
            if (response == ButtonType.OK) {
                controller.setUpdown1(btn1.getText());
                btn1.setDisable(true);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    void btn2Click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.setUpdown2(btn2.getText());
                btn2.setDisable(true);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    void btn3Click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.setUpdown3(btn3.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    void btn4Click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.setUpdown4(btn4.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    void btn5Click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.setUpdown5(btn5.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    void btn6Click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.setUpdown6(btn6.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    void centerBtnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Please confirm your action!");
        alert.setContentText("Are you sure?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.setCenter(btnCenter.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    public void setBtn1(String string) {
        this.btn1.setText(string);
    }
    public void setBtn2(String string) {
        this.btn2.setText(string);
    }
    public void setBtn3(String string) {
        this.btn3.setText(string);
    }
    public void setBtn4(String string) {
        this.btn4.setText(string);
    }
    public void setBtn5(String string) {
        this.btn5.setText(string);
    }
    public void setBtn6(String string) {
        this.btn6.setText(string);
    }
    public void setBtnCenter(String string) {
        this.btnCenter.setText(string);
    }
    public void setBtnLeft(String string) {
        this.btnLeft.setText(string);
    }

    public void setLblUp(int i) {
        String nastavak = i + "";
        String strelica = lblDown.getText();
        this.lblUp.setText(strelica + nastavak);;
    }

    public void setLblDown(int i) {
        String nastavak = i + "";
        String strelica = lblDown.getText();
        this.lblDown.setText(strelica + nastavak);
    }

    public void setGameController(GameController controller) {
        this.controller = controller;
    }

    public Button getBtn1() {
        return btn1;
    }

    public Button getBtn2() {
        return btn2;
    }

    public Button getBtn3() {
        return btn3;
    }

    public Button getBtn4() {
        return btn4;
    }

    public Button getBtn5() {
        return btn5;
    }

    public Button getBtn6() {
        return btn6;
    }

    public Button getBtnCenter() {
        return btnCenter;
    }

    public Button getBtnLeft() {
        return btnLeft;
    }
}
