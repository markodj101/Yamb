package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameController {

    @FXML
    private Button BtnBack;

    @FXML
    private Label attemptsLbl;

    @FXML
    private Label lbl1_1;

    @FXML
    private Label lbl1_2;

    @FXML
    private Label lbl1_3;

    @FXML
    private Label lbl2_1;

    @FXML
    private Label lbl2_2;

    @FXML
    private Label lbl2_3;

    @FXML
    private Label lbl3_1;

    @FXML
    private Label lbl3_2;

    @FXML
    private Label lbl3_3;

    @FXML
    private Label lbl4_1;

    @FXML
    private Label lbl4_2;

    @FXML
    private Label lbl4_3;

    @FXML
    private Label lbl5_1;

    @FXML
    private Label lbl5_2;

    @FXML
    private Label lbl5_3;

    @FXML
    private Label lbl6_1;

    @FXML
    private Label lbl6_2;

    @FXML
    private Label lbl6_3;

    @FXML
    private Label lblSumDown;

    @FXML
    private Label lblSumSum;

    @FXML
    private Label lblSumUp;

    @FXML
    private Label lblSumUpDown;

    @FXML
    private Label nameLbl;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;

    @FXML
    private RadioButton rb5;

    @FXML
    private Button rollBtn;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label scoreLbl;

    @FXML
    private TextField taDice1;

    @FXML
    private TextField taDice2;

    @FXML
    private TextField taDice3;

    @FXML
    private TextField taDice4;

    @FXML
    private TextField taDice5;
    String url = "/view/GameView.fxml";

    private List<Label> from6To1 = Arrays.asList(lbl6_1, lbl5_1, lbl4_1, lbl3_1, lbl2_1, lbl1_1);
    private List<Label> from1To6 = Arrays.asList(lbl1_2, lbl2_2, lbl3_2, lbl4_2, lbl5_2, lbl6_2);
    private List<Label> upDownList = Arrays.asList(lbl1_3, lbl2_3, lbl3_3, lbl4_3, lbl5_3, lbl6_3);

    @FXML
    void BackBtnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to leave game?");
        alert.setContentText("Please confirm your action!");
        alert.show();
    }

    @FXML
    void RollDicesBtnClick(ActionEvent event) {
        Random random = new Random();
        int attempts = Integer.parseInt(attemptsLbl.getText());
        while (attempts != 0){
            System.out.println(attempts);
            if (!rb1.isSelected()){
                int dice1 = random.nextInt(6) + 1;
                taDice1.setText(dice1 + "");
            }
            if (!rb2.isSelected()){
                int dice2 = random.nextInt(6) + 1;
                taDice2.setText(dice2 + "");
            }
            if (!rb3.isSelected()){
                int dice3 = random.nextInt(6) + 1;
                taDice3.setText(dice3 + "");
            }
            if (!rb4.isSelected()){
                int dice4 = random.nextInt(6) + 1;
                taDice4.setText(dice4 + "");
            }
            if (!rb5.isSelected()){
                int dice5 = random.nextInt(6) + 1;
                taDice5.setText(dice5 + "");
            }
            attempts--;
        }


    }

    public String getUrl() {
        return url;
    }


    public void setNameLbl(String string) {
        this.nameLbl.setText(string);
    }

    public void setScoreLabel(String string) {
        this.scoreLabel.setText(string);
    }

    public void setLblSumSum(String string) {
        this.lblSumSum.setText(string);
    }
    public void setAttemptsLbl(String string) {
        this.lblSumSum.setText(string);
    }

    public Button getRollBtn() {
        return rollBtn;
    }

    public Label getNameLbl() {
        return nameLbl;
    }

    private void calculateOptions(int a, int b, int c, int d, int e){
        int[] options = {a, b, c, d, e};

        int leftSum = 0;
        int indicator = 6;
        for (Label l:
             from6To1) {
            if (l.getText().equalsIgnoreCase("")){
                for(int option: options){
                    if (option == indicator)
                        leftSum += option;
                }
                l.setText(leftSum + "");
                break;
            }
            indicator--;
        }
        int centerSum = 0;
        int indicator2 = 1;
        for (Label l:
                from1To6) {
            if (l.getText().equalsIgnoreCase("")){
                for(int option: options){
                    if (option == indicator2)
                        centerSum += option;
                }
                l.setText(centerSum + "");
                break;
            }
            indicator2++;
        }
        int sum1 = 0;
        Label updown1 = upDownList.get(0);
        for(int option: options){
            if (option == 1)
                sum1 += option;
        }
        int sum2 = 0;
        Label updown2 = upDownList.get(1);
        for(int option: options){
            if (option == 2)
                sum2 += option;
        }
        int sum3 = 0;
        Label updown3 = upDownList.get(2);
        for(int option: options){
            if (option == 3)
                sum3 += option;
        }
        int sum4 = 0;
        Label updown4 = upDownList.get(3);
        for(int option: options){
            if (option == 4)
                sum4 += option;
        }
        int sum5 = 0;
        Label updown5 = upDownList.get(4);
        for(int option: options){
            if (option == 5)
                sum5 += option;
        }
        int sum6 = 0;
        Label updown6 = upDownList.get(5);
        for(int option: options){
            if (option == 6)
                sum6 += option;
        }

    }
}
