package sample;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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

public class AccountATMDeposit extends Application {


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
        Label lblRegister = new Label("Account Deposit");
        GridPane.setHalignment(lblRegister, HPos.CENTER);
        pane.add(lblRegister, 0, 0);

        Label lblDepositNum = new Label("Enter an deposit price: ");
        GridPane.setHalignment(lblDepositNum, HPos.CENTER);
        pane.add(lblDepositNum, 0, 1);

        TextField txtDepositNum = new TextField();
        GridPane.setHalignment(txtDepositNum, HPos.CENTER);
        pane.add(txtDepositNum, 0, 2);

        Button btnSubmit = new Button("Deposit");
        pane.add(btnSubmit, 0, 3);
        GridPane.setHalignment(btnSubmit, HPos.CENTER);

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 0, 4);
        GridPane.setHalignment(btnExit, HPos.CENTER);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("ATM Account Deposit"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            AccountATMDesc accountDes = new AccountATMDesc();
            @Override
            public void handle(ActionEvent event) {

                if(txtDepositNum.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please enter your deposit price");
                    //return;
                }else {
                    account.deposit(Double.parseDouble(txtDepositNum.getText()));
                    showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(), "Deposit Success!", "$" +
                            txtDepositNum.getText() +  " Successfully deposit\nBalance: " + account.getBalance());

                    try {
                        FileOutputStream fos = new FileOutputStream("account.dat");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(allAccount);
                        fos.close();
                        oos.close();
                    }catch(Exception e) {
                        System.out.println("Exception: " + e);
                    }

                    account.print();
                }
            }

        });

        btnExit.setOnAction(new EventHandler<ActionEvent>(){
            AccountATMDesc accountDes = new AccountATMDesc();
            @Override
            public void handle(ActionEvent event) {
                accountDes.start(primaryStage, allAccount, account);
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
