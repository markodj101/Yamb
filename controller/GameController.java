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
import java.util.*;

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
    private List<Label> from1To6indicator = new ArrayList<>();
    private List<Label> upDownList = Arrays.asList(lbl1_3, lbl2_3, lbl3_3, lbl4_3, lbl5_3, lbl6_3);
    @FXML
    public void initialize() {
        from6To1 = Arrays.asList(lbl6_1, lbl5_1, lbl4_1, lbl3_1, lbl2_1, lbl1_1);
        from1To6 = Arrays.asList(lbl1_2, lbl2_2, lbl3_2, lbl4_2, lbl5_2, lbl6_2);
        upDownList = Arrays.asList(lbl1_3, lbl2_3, lbl3_3, lbl4_3, lbl5_3, lbl6_3);
    }

    @FXML
    void BackBtnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to leave game?");
        alert.setContentText("Please confirm your action!");
        alert.show();
    }

    private int dice1, dice2, dice3, dice4, dice5;

    @FXML
    void RollDicesBtnClick(ActionEvent event) {
        Random random = new Random();
        int attempts = Integer.parseInt(attemptsLbl.getText());

        if (attempts > 0) {
            // Ažuriraj kockice samo ako nisu označene
            if (!rb1.isSelected()) {
                dice1 = random.nextInt(6) + 1;
                taDice1.setText(dice1 + "");
            }
            if (!rb2.isSelected()) {
                dice2 = random.nextInt(6) + 1;
                taDice2.setText(dice2 + "");
            }
            if (!rb3.isSelected()) {
                dice3 = random.nextInt(6) + 1;
                taDice3.setText(dice3 + "");
            }
            if (!rb4.isSelected()) {
                dice4 = random.nextInt(6) + 1;
                taDice4.setText(dice4 + "");
            }
            if (!rb5.isSelected()) {
                dice5 = random.nextInt(6) + 1;
                taDice5.setText(dice5 + "");
            }

            // Smanji broj pokušaja
            attempts--;
            attemptsLbl.setText("" + attempts);

        } else {
            System.out.println("No more attempts left!");
            attemptsLbl.setText("3");
            try {
                calculateOptions(dice1, dice2, dice3, dice4, dice5);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        this.attemptsLbl.setText(string);
    }

    public Button getRollBtn() {
        return rollBtn;
    }

    public Label getNameLbl() {
        return nameLbl;
    }

    Label left;
    Label center;

    private void calculateOptions(int a, int b, int c, int d, int e) throws IOException {
        int[] options = {a, b, c, d, e};

        int leftSum = 0;
        int indicator = from6To1.size();
        Iterator<Label> iterator = from6To1.iterator();
        while (iterator.hasNext()) {
            Label l = iterator.next();
            if (l != null) {
                for (int option : options) {
                    if (option == indicator)
                        leftSum += option;
                }
                System.out.println("Sum left: " + leftSum);
                left = l;
                left.setText(leftSum + "");
                iterator.remove();  // Bezbedno uklanjanje elementa
                break;
            }
        }
        int centerSum = 0;
        int indicator2 = from1To6indicator.size() + 1;
        Iterator<Label> iterator2 = from1To6.iterator();
        while (iterator2.hasNext()) {
            Label l = iterator2.next();
            if (l != null) {
                for (int option : options) {
                    if (option == indicator2)
                        centerSum += option;
                }
                System.out.println("Sum center: " + centerSum);
                center = l;
                center.setText(centerSum + "");
                from1To6indicator.add(l);
                break;
            }
        }
        System.out.println(left.getText());
        System.out.println(center.getText());

        int sum1 = 0;
        Label updown1 = upDownList.get(0);
        for(int option: options){
            if (option == 1)
                sum1 += option;
        }
        updown1.setText(sum1 + "");
        int sum2 = 0;
        Label updown2 = upDownList.get(1);
        for(int option: options){
            if (option == 2)
                sum2 += option;
        }
        updown2.setText(sum2 + "");
        int sum3 = 0;
        Label updown3 = upDownList.get(2);
        for(int option: options){
            if (option == 3)
                sum3 += option;
        }
        updown3.setText(sum3 + "");
        int sum4 = 0;
        Label updown4 = upDownList.get(3);
        for(int option: options){
            if (option == 4)
                sum4 += option;
        }
        updown4.setText(sum4 + "");
        int sum5 = 0;
        Label updown5 = upDownList.get(4);
        for(int option: options){
            if (option == 5)
                sum5 += option;
        }
        updown5.setText(sum5 + "");
        int sum6 = 0;
        Label updown6 = upDownList.get(5);
        for(int option: options){
            if (option == 6)
                sum6 += option;
        }
        updown6.setText(sum6 + "");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SelectBoxView.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("SELECT BOX");
        SelectController controller = loader.getController();

        controller.setBtn1(updown1);
        controller.setBtn2(updown2);
        controller.setBtn3(updown3);
        controller.setBtn4(updown4);
        controller.setBtn5(updown5);
        controller.setBtn6(updown6);

        controller.setBtnLeft(left);
        controller.setBtnCenter(center);

        controller.setLblUp(indicator);
        controller.setLblDown(indicator2);



        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
