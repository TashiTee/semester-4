package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

public class AccountATM extends Application {

    AddressBook addressBook = new AddressBook();


    @Override
    public void start(Stage primaryStage) throws Exception {
        AccountATM accountAtm = new AccountATM();

        try {
            File fileExist = new File("account.dat");
            FileInputStream fis;
            Account[] account;
            int empty;


            if(fileExist.exists()) {
                System.out.println("File exists");
                fis = new FileInputStream("account.dat");
                empty = fis.available();

                if(empty == 0) {
                    System.out.println("File empty");

                    account = new Account[10];
                    for(int i = 0; i < 10; i++) {
                        account[i] = new Account(0, 100);
                    }

                }else {
                    System.out.println("File is not empty");
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    account = (Account[])ois.readObject();

                    for(int i = 0; i < 10; i++) {
                        account[i].print();
                    }

                    ois.close();
                }
            }else {
                System.out.println("File not found");
                FileOutputStream fos = new FileOutputStream("account.dat");
                fis = new FileInputStream("account.dat");

                account = new Account[10];
                for(int i = 0; i < 10; i++) {
                    account[i] = new Account(0, 100);
                }

                fos.close();
            }

            fis.close();
            accountAtm.start(primaryStage, account);
        }catch(Exception e) {
            System.out.println("Exception: " + e);
        }


    }

    public void start(Stage primaryStage, Account[] account) {
        // Create a pane and set its properties

        AccountATMDesc accountDesc = new AccountATMDesc();
        AccountATMRegister accountRegister = new AccountATMRegister();
        AccountATMRmv accountRmv = new AccountATMRmv();

        GridPane pane1 = new GridPane();
        pane1.setAlignment(Pos.CENTER);
        pane1.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane1.setHgap(5.5);
        pane1.setVgap(5.5);

        // Place nodes in the pane
        Label lblAccountNum = new Label("Enter your Account number:");
        GridPane.setHalignment(lblAccountNum, HPos.CENTER);
        pane1.add(lblAccountNum, 0, 0);

        TextField txtAccountNum = new TextField();
        GridPane.setHalignment(txtAccountNum, HPos.CENTER);
        pane1.add(txtAccountNum, 1, 0);


        Button btnSubmit = new Button("Submit");
        pane1.add(btnSubmit, 1, 1);
        GridPane.setHalignment(btnSubmit, HPos.RIGHT);

        Button btnExit = new Button("Exit");
        pane1.add(btnExit, 1,2);
        GridPane.setHalignment(btnExit, HPos.RIGHT);

        Scene scene = new Scene(pane1);
        primaryStage.setTitle("ATM"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(txtAccountNum.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "Please enter your account number");
                    return;
                }else {
                    int findAccount = -1;
                    int findInitAccount = -1;
                    int accountNum = Integer.parseInt(txtAccountNum.getText());

                    for(int i = 0; i < 10; i++) {
                        if(account[i].getId() == accountNum) {
                            findAccount = i;
                        }

                        if(findInitAccount == -1 && account[i].getId() == 0) {
                            findInitAccount = i;
                        }
                    }

                    System.out.println(findInitAccount);

                    if(findAccount != -1) {
                        accountDesc.start(primaryStage, account, account[findAccount]);
                    }else if(findInitAccount != -1) {
                        showAlert(Alert.AlertType.INFORMATION, pane1.getScene().getWindow(), "Account Doesn't Exist!", "You don't have account\nPlease register your account number");
                        accountRegister.start(primaryStage, account, account[findInitAccount]);
                    }else {
                        showAlert(Alert.AlertType.INFORMATION, pane1.getScene().getWindow(), "Full account", "Account is already full in this ATM. Please use other ATM machines.");
                        accountRmv.start(primaryStage, account);
                    }
                }

            }

        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TesterApplication testerApp = new TesterApplication();
                try {
                    FileOutputStream fos = new FileOutputStream("account.dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(account);
                    fos.close();
                    oos.close();

                    FileInputStream fis = new FileInputStream("account.dat");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Account[] accountFromSavedFile = (Account[])ois.readObject();

                    for(int i = 0; i < 10; i++) {
                        accountFromSavedFile[i].print();
                    }

                    fis.close();
                    ois.close();

                    testerApp.start(primaryStage);
                }catch(Exception e) {
                    System.err.println("Exception occurs");
                }
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

