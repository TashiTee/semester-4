package sample;

import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class QuizPanel extends Application {
    Set<StoreNum> setNum = new LinkedHashSet<StoreNum>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        int num1 = (int) Math.ceil(Math.random() * 10);
        int num2 = (int) Math.ceil(Math.random() * 10);
        QuizPanel quizPanel = new QuizPanel();
        quizPanel.start(primaryStage, num1, num2, setNum);

    }

    public void start(Stage primaryStage, int num1, int num2, Set storeNum) throws Exception {
        this.setNum = storeNum;

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 120, 11.5, 120));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // Place nodes in the pane
        Label lblNumShow = new Label("Two randomly generated numbers are: " + num1 + " and " + num2);
        GridPane.setHalignment(lblNumShow, HPos.CENTER);
        pane.add(lblNumShow, 0, 0);

        Label lblAdd = new Label("What is addition of " + num1 + " and " + num2);
        GridPane.setHalignment(lblAdd, HPos.CENTER);
        pane.add(lblAdd, 0, 1);

        TextField txtAdd = new TextField();
        GridPane.setHalignment(txtAdd, HPos.CENTER);
        txtAdd.setMaxWidth(132);
        txtAdd.setMinWidth(132);
        pane.add(txtAdd, 1, 1);

        Label lblSub = new Label("What is substraction of " + num1 + " and " + num2);
        GridPane.setHalignment(lblSub, HPos.CENTER);
        pane.add(lblSub, 0, 2);

        TextField txtSub = new TextField();
        GridPane.setHalignment(txtSub, HPos.CENTER);
        txtSub.setMaxWidth(132);
        txtSub.setMinWidth(132);
        pane.add(txtSub, 1, 2);

        Label lblMul = new Label("What is multiplication of " + num1 + " and " + num2);
        GridPane.setHalignment(lblMul, HPos.CENTER);
        pane.add(lblMul, 0, 3);

        TextField txtMul = new TextField();
        GridPane.setHalignment(txtMul, HPos.CENTER);
        txtMul.setMaxWidth(132);
        txtMul.setMinWidth(132);
        pane.add(txtMul, 1, 3);

        Label lblDiv = new Label("What is division of " + num1 + " and " + num2 + "(Percision 2)");
        GridPane.setHalignment(lblDiv, HPos.CENTER);
        pane.add(lblDiv, 0, 4);

        TextField txtDiv = new TextField();
        GridPane.setHalignment(txtDiv, HPos.CENTER);
        txtDiv.setMaxWidth(132);
        txtDiv.setMinWidth(132);
        pane.add(txtDiv, 1, 4);

        Label lblCorrectAnsw = new Label();
        GridPane.setHalignment(lblCorrectAnsw, HPos.CENTER);
        pane.add(lblCorrectAnsw, 0, 5);

        Label lblWrngAnsw = new Label();
        GridPane.setHalignment(lblWrngAnsw, HPos.CENTER);
        pane.add(lblWrngAnsw, 0, 6);

        Button btnSubmit = new Button("Check Answer");
        pane.add(btnSubmit, 0, 7);
        GridPane.setHalignment(btnSubmit, HPos.LEFT);

        Button btnReset = new Button("Reset Number");
        pane.add(btnReset, 0, 7);
        GridPane.setHalignment(btnReset, HPos.RIGHT);

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 1, 7);
        GridPane.setHalignment(btnExit, HPos.RIGHT);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Quiz Application"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage


        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isEmpty = txtAdd.getText().isEmpty() || txtSub.getText().isEmpty() || txtMul.getText().isEmpty() || txtDiv.getText().isEmpty();

                if (isEmpty) {
                    showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please fill in the blank");
                } else {
                    try {
                        int correct = 0;
                        int wrong = 0;
                        int add = Integer.parseInt(txtAdd.getText());
                        int sub = Integer.parseInt(txtSub.getText());
                        int mul = Integer.parseInt(txtMul.getText());
                        double div = Double.parseDouble(txtDiv.getText());

                        StoreNum store = new StoreNum(add, sub, mul, div);

                        if (num1 + num2 == add) {
                            correct++;
                        } else {
                            wrong++;
                        }

                        if (num1 - num2 == sub) {
                            correct++;
                        } else {
                            wrong++;
                        }

                        if (num1 * num2 == mul) {
                            correct++;
                        } else {
                            wrong++;
                        }

                        double divAnsr = (double) num1 / (double) num2;

                        if ((int) (divAnsr * 100) == div * 100) {
                            correct++;
                        } else {
                            wrong++;
                        }

                        lblCorrectAnsw.setText("Number of Correct Answers: " + correct);
                        lblWrngAnsw.setText("Number of Wrong Answers: " + wrong);

                        store.setCorrectNum(correct);
                        store.setWrongNum(wrong);

                        setNum.add(store);

                        System.out.println(setNum);
                    } catch (NumberFormatException e) {
                        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please write number in the blank");
                    } catch (Exception e) {
                        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Error!", "Exception occurs!");
                    }
                }
            }
        });

        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            QuizPanel quizPanel = new QuizPanel();

            @Override
            public void handle(ActionEvent event) {
                int num1 = (int) Math.ceil(Math.random() * 10);
                int num2 = (int) Math.ceil(Math.random() * 10);
                try {
                    quizPanel.start(primaryStage, num1, num2, setNum);
                } catch (Exception e) {
                    System.err.println("Exception: " + e);
                }
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
    }


    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}

