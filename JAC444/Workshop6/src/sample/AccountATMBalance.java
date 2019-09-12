package sample;

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

public class AccountATMBalance extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
    }

    public void start(Stage primaryStage, Account[] allAccount, Account account) {
        // Create a pane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // Place nodes in the pane
        Label lblRegister = new Label("Account Balance");
        GridPane.setHalignment(lblRegister, HPos.CENTER);
        pane.add(lblRegister, 0, 0);

        Label lblDepositNum = new Label("Yout balance is " + account.getBalance());
        GridPane.setHalignment(lblDepositNum, HPos.CENTER);
        pane.add(lblDepositNum, 0, 1);

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 0, 2);
        GridPane.setHalignment(btnExit, HPos.CENTER);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("ATM Account Balance"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            AccountATMDesc accountDes = new AccountATMDesc();
            @Override
            public void handle(ActionEvent event) {

                accountDes.start(primaryStage, allAccount, account);
            }

        });
    }

}