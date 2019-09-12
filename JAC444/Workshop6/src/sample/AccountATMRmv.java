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

public class AccountATMRmv extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    }

    public void start(Stage primaryStage, Account[] allAccount) {
        // Create a pane and set its properties
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // Place nodes in the pane
        Label lblRegister = new Label("This scene is for professor. When ATM is full, this is displayed");
        GridPane.setHalignment(lblRegister, HPos.CENTER);
        pane.add(lblRegister, 0, 0);

        Label lblBalance = new Label("Do you want to initialize ATM?");
        GridPane.setHalignment(lblBalance, HPos.CENTER);
        pane.add(lblBalance, 0, 1);

        Button btnYes = new Button("Yes");
        pane.add(btnYes, 0, 4);
        GridPane.setHalignment(btnYes, HPos.CENTER);

        Button btnNo = new Button("No");
        pane.add(btnNo, 0, 5);
        GridPane.setHalignment(btnNo, HPos.CENTER);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("ATM Account Remover"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btnYes.setOnAction(new EventHandler<ActionEvent>() {
            AccountATM accountATM = new AccountATM();
            @Override
            public void handle(ActionEvent event) {

                Account account[] = new Account[10];
                for(int i = 0; i < 10; i++) {
                    account[i] = new Account(0, 100);
                }

                try {
                    FileOutputStream fos = new FileOutputStream("account.dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(account);
                    fos.close();
                    oos.close();
                }catch(Exception e) {
                    System.out.println("Exception: " + e);
                }


                showAlert(Alert.AlertType.INFORMATION, pane.getScene().getWindow(), "Remove Success!", "All accounts are successfully removed!");

                accountATM.start(primaryStage, account);

            }

        });

        btnNo.setOnAction(new EventHandler<ActionEvent>(){
            AccountATM accountATM = new AccountATM();
            @Override
            public void handle(ActionEvent event) {
                accountATM.start(primaryStage, allAccount);
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
