package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    private List<Label> from6To1;
    private List<Label> from1To6;
    private List<Label> upDownList;

    private List<Label> from1To6indicator = new ArrayList<>();

    @FXML
    public void initialize() {
         from6To1 = new ArrayList<>(Arrays.asList(lbl6_1, lbl5_1, lbl4_1, lbl3_1, lbl2_1, lbl1_1));
         from1To6 = new ArrayList<>(Arrays.asList(lbl1_2, lbl2_2, lbl3_2, lbl4_2, lbl5_2, lbl6_2));
         upDownList = new ArrayList<>(Arrays.asList(lbl1_3, lbl2_3, lbl3_3, lbl4_3, lbl5_3, lbl6_3));
    }

    @FXML
    void BackBtnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to leave the game?");
        alert.setContentText("Please confirm your action!");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    private int dice1, dice2, dice3, dice4, dice5;

    @FXML
    void RollDicesBtnClick(ActionEvent event) {
        Random random = new Random();
        int attempts = Integer.parseInt(attemptsLbl.getText());

        if (attempts > 1) {

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

            attempts--;
            attemptsLbl.setText("" + attempts);

        } else {
            System.out.println("No more attempts left!");
            attemptsLbl.setText("3");
            rb1.setSelected(false);
            rb2.setSelected(false);
            rb3.setSelected(false);
            rb4.setSelected(false);
            rb5.setSelected(false);
            taDice1.clear();
            taDice2.clear();
            taDice3.clear();
            taDice4.clear();
            taDice5.clear();
            if(!lblSumUp.getText().equals("-") && !lblSumDown.getText().equals("-") && !lblSumUpDown.getText().equals("-")){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("The game is over!");
                alert.setContentText("Your score is " + scoreLabel.getText() + "!");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {;
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                    }
                });
                rollBtn.setDisable(true);
                return;
            }
            try {
                for (Label l:
                     from6To1) {
                    if(!isLblEmpty(l)){
                        from6To1.remove(l);
                        break;
                    }
                }
                for (Label l:
                        from1To6) {
                    if(!isLblEmpty(l)){
                        from1To6.remove(l);
                        from1To6indicator.add(l);
                        System.out.println("From 1 to 6 size after removal: " + from1To6.size());
                        break;
                    }
                }

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
    Label updown1;
    Label updown2;
    Label updown3;
    Label updown4;
    Label updown5;
    Label updown6;

    int sumUpIndicator = 1;
    int sumDownIndicator = 1;
    int sumUpDownIndicator = 1;



    private void calculateOptions(int a, int b, int c, int d, int e) throws IOException {
        int[] options = {a, b, c, d, e};

        int leftSum = 0;
        int indicator = from6To1.size();
        if (indicator != 0){
            Iterator<Label> iterator = from6To1.iterator();
            while (iterator.hasNext()) {
                Label l = iterator.next();
                if (l != null) {
                    for (int option : options) {
                        if (option == indicator)
                            leftSum += option;
                    }
                    left = l;
                    break;
                }
            }
        }else{
            int one = Integer.parseInt(lbl1_1.getText());
            int two = Integer.parseInt(lbl2_1.getText());
            int three = Integer.parseInt(lbl3_1.getText());
            int four = Integer.parseInt(lbl4_1.getText());
            int five = Integer.parseInt(lbl5_1.getText());
            int six = Integer.parseInt(lbl6_1.getText());

            int sumUp = one + two + three + four + five + six;
            lblSumUp.setText(sumUp + "");
        }

        int centerSum = 0;
        int indicator2 = from1To6indicator.size() + 1;
        System.out.println("From 1 to 6 size before removal: " + indicator2);
        if (indicator2 < 7){
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
                    break;
                }
            }
        } else{
            int one = Integer.parseInt(lbl1_2.getText());
            int two = Integer.parseInt(lbl2_2.getText());
            int three = Integer.parseInt(lbl3_2.getText());
            int four = Integer.parseInt(lbl4_2.getText());
            int five = Integer.parseInt(lbl5_2.getText());
            int six = Integer.parseInt(lbl6_2.getText());

            int sumDown = one + two + three + four + five + six;
            lblSumDown.setText(sumDown + "");
        }


        int sum1 = 0;
        for(int option: options){
            if (option == 1)
                sum1 += option;
        }

        int sum2 = 0;
        for(int option: options){
            if (option == 2)
                sum2 += option;
        }

        int sum3 = 0;
        for(int option: options){
            if (option == 3)
                sum3 += option;
        }

        int sum4 = 0;
        for(int option: options){
            if (option == 4)
                sum4 += option;
        }

        int sum5 = 0;
        for(int option: options){
            if (option == 5)
                sum5 += option;
        }

        int sum6 = 0;
        for(int option: options){
            if (option == 6)
                sum6 += option;
        }

        updown1 = upDownList.get(0);
        updown2 = upDownList.get(1);
        updown3 = upDownList.get(2);
        updown4 = upDownList.get(3);
        updown5 = upDownList.get(4);
        updown6 = upDownList.get(5);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SelectBoxView.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();

        stage.setTitle("SELECT BOX");
        SelectController controller = loader.getController();
        controller.setGameController(this);

        controller.setBtn1(sum1 + "");
        if(!isLblEmpty(getLbl1_3())){
            controller.getBtn1().setDisable(true);
        }
        controller.setBtn2(sum2 + "");
        if(!isLblEmpty(getLbl2_3())){
            controller.getBtn2().setDisable(true);
        }
        controller.setBtn3(sum3 + "");
        if(!isLblEmpty(getLbl3_3())){
            controller.getBtn3().setDisable(true);
        }
        controller.setBtn4(sum4 + "");
        if(!isLblEmpty(getLbl4_3())){
            controller.getBtn4().setDisable(true);
        }
        controller.setBtn5(sum5 + "");
        if(!isLblEmpty(getLbl5_3())){
            controller.getBtn5().setDisable(true);
        }
        controller.setBtn6(sum6 + "");
        if(!isLblEmpty(getLbl6_3())){
            controller.getBtn6().setDisable(true);
        }
        if(from6To1.isEmpty()){
            controller.getBtnLeft().setDisable(true);
        }
        if (from1To6indicator.size() == 6){
            controller.getBtnCenter().setDisable(true);
        }
        if(!lblSumUp.getText().equals("-") && sumUpIndicator == 1){
            int sumsum = Integer.parseInt(lblSumSum.getText());
            int sumup = Integer.parseInt(lblSumUp.getText());
            int finalSum = sumsum + sumup;
            lblSumSum.setText(finalSum + "");
            scoreLabel.setText(finalSum + "");
            sumUpIndicator++;
        }
        if(!lblSumDown.getText().equals("-") && sumDownIndicator == 1){
            int sumsum = Integer.parseInt(lblSumSum.getText());
            int sumdown = Integer.parseInt(lblSumDown.getText());
            int finalSum = sumsum + sumdown;
            lblSumSum.setText(finalSum + "");
            scoreLabel.setText(finalSum + "");
            sumDownIndicator++;
        }
        if(!lblSumUpDown.getText().equals("-") && sumUpDownIndicator == 1){
            int sumsum = Integer.parseInt(lblSumSum.getText());
            int sumupdown = Integer.parseInt(lblSumUpDown.getText());
            int finalSum = sumsum + sumupdown;
            lblSumSum.setText(finalSum + "");
            scoreLabel.setText(finalSum + "");
            sumUpDownIndicator++;
        }
        if(!isLblEmpty(getLbl1_3()) && !isLblEmpty(getLbl2_3()) && !isLblEmpty(getLbl3_3()) && !isLblEmpty(getLbl4_3()) && !isLblEmpty(getLbl5_3()) && !isLblEmpty(getLbl6_3())){
            int one = Integer.parseInt(lbl1_3.getText());
            int two = Integer.parseInt(lbl2_3.getText());
            int three = Integer.parseInt(lbl3_3.getText());
            int four = Integer.parseInt(lbl4_3.getText());
            int five = Integer.parseInt(lbl5_3.getText());
            int six = Integer.parseInt(lbl6_3.getText());

            int sumUpdown = one + two + three + four + five + six;
            lblSumUpDown.setText(sumUpdown + "");
        }


        controller.setBtnLeft(leftSum + "");
        controller.setBtnCenter(centerSum + "");

        controller.setLblUp(indicator);
        controller.setLblDown(indicator2);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void setLeft(String s) {
        this.left.setText(s);
    }
    public void setCenter(String s) {
        this.center.setText(s);
    }
    public void setUpdown1(String s) {
        this.updown1.setText(s);
    }
    public void setUpdown2(String s) {
        this.updown2.setText(s);
    }
    public void setUpdown3(String s) {
        this.updown3.setText(s);
    }
    public void setUpdown4(String s) {
        this.updown4.setText(s);
    }
    public void setUpdown5(String s) {
        this.updown5.setText(s);
    }
    public void setUpdown6(String s) {
        this.updown6.setText(s);
    }
    public boolean isLblEmpty(Label label){
        return label.getText().equals("-");
    }
    public Button getBtnBack() {
        return BtnBack;
    }
    public Label getLbl1_3() {
        return lbl1_3;
    }
    public Label getLbl2_3() {
        return lbl2_3;
    }
    public Label getLbl3_3() {
        return lbl3_3;
    }
    public Label getLbl4_3() {
        return lbl4_3;
    }
    public Label getLbl5_3() {
        return lbl5_3;
    }
    public Label getLbl6_3() {
        return lbl6_3;
    }

    public TextField getTaDice1() {
        return taDice1;
    }

    public TextField getTaDice2() {
        return taDice2;
    }

    public TextField getTaDice3() {
        return taDice3;
    }

    public TextField getTaDice4() {
        return taDice4;
    }

    public TextField getTaDice5() {
        return taDice5;
    }
}
