package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    @FXML
    void LeftBtnClick(ActionEvent event) {

    }

    @FXML
    void btn1Click(ActionEvent event) {

    }

    @FXML
    void btn2Click(ActionEvent event) {

    }

    @FXML
    void btn3Click(ActionEvent event) {

    }

    @FXML
    void btn4Click(ActionEvent event) {

    }

    @FXML
    void btn5Click(ActionEvent event) {

    }

    @FXML
    void btn6Click(ActionEvent event) {

    }

    @FXML
    void centerBtnClick(ActionEvent event) {

    }

    public void setBtn1(Label lbl1) {
        this.btn1.setText(lbl1.getText());
    }
    public void setBtn2(Label lbl1) {
        this.btn1.setText(lbl1.getText());
    }
    public void setBtn3(Label lbl1) {
        this.btn1.setText(lbl1.getText());
    }
    public void setBtn4(Label lbl1) {
        this.btn1.setText(lbl1.getText());
    }
    public void setBtn5(Label lbl1) {
        this.btn1.setText(lbl1.getText());
    }
    public void setBtn6(Label lbl1) {
        this.btn1.setText(lbl1.getText());
    }
    public void setBtnCenter(Label lbl1) {
        this.btn1.setText(lbl1.getText());
    }
    public void setBtnLeft(Label lbl1) {
        this.btn1.setText(lbl1.getText());
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
}
