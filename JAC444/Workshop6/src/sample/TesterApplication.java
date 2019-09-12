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

public class TesterApplication extends Application {
    AccountATM accountAtm = new AccountATM();
    AddressBook addressBook = new AddressBook();

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // Place nodes in the pane
        Label lblWelcome = new Label("Welcome Hyunji's Workshop5 Assignment");
        GridPane.setHalignment(lblWelcome, HPos.CENTER);
        pane.add(lblWelcome, 0, 0);

        Button btTask1 = new Button("Task1");
        pane.add(btTask1, 0, 1);
        GridPane.setHalignment(btTask1, HPos.CENTER);

        Button btTask2 = new Button("Task2");
        pane.add(btTask2, 0, 2);
        GridPane.setHalignment(btTask2, HPos.CENTER);

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 0, 4);
        GridPane.setHalignment(btnExit, HPos.CENTER);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Hyunji Lee - Workshop5"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btTask1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    accountAtm.start(primaryStage);
                }catch(Exception e) {
                    System.err.println("Exception: " + e);
                }
            }

        });

        btTask2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addressBook.start(primaryStage);
                }catch(Exception e) {
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

}
