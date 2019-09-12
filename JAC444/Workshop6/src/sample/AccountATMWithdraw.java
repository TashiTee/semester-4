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

public class AccountATMWithdraw extends Application {


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
        Label lblRegister = new Label("Account Withdraw");
        GridPane.setHalignment(lblRegister, HPos.CENTER);
        pane.add(lblRegister, 0, 0);

        Label lblBalance = new Label("Your balance: " + account.getBalance());
        GridPane.setHalignment(lblBalance, HPos.CENTER);
        pane.add(lblBalance, 0, 1);

        Label lblWithNum = new Label("Enter an withdraw price: ");
        GridPane.setHalignment(lblWithNum, HPos.CENTER);
        pane.add(lblWithNum, 0, 2);

        TextField txtWithNum = new TextField();
        GridPane.setHalignment(txtWithNum, HPos.CENTER);
        pane.add(txtWithNum, 0, 3);

        Button btnSubmit = new Button("Withdraw");
        pane.add(btnSubmit, 0, 4);
        GridPane.setHalignment(btnSubmit, HPos.CENTER);

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 0, 5);
        GridPane.setHalignment(btnExit, HPos.CENTER);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("ATM Account Withdraw"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            AccountATMDesc accountDes = new AccountATMDesc();
            @Override
            public void handle(ActionEvent event) {

                if(txtWithNum.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please enter your deposit price");
                    //return;
                }else {
                    if(Double.parseDouble(txtWithNum.getText()) > account.getBalance()) {
                        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Price is over your balance");
                    }else {
                        account.withdraw(Double.parseDouble(txtWithNum.getText()));
                        showAlert(Alert.AlertType.CONFIRMATION, pane.getScene().getWindow(), "Withdraw Success!", "$" +
                                txtWithNum.getText() +  " Successfully withdraw\nBalance: " + account.getBalance());
                    }

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

                accountDes.start(primaryStage, allAccount, account);
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