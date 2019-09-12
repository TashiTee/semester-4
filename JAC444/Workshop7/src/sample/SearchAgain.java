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


public class SearchAgain extends Application {
    PopulationRank popRank = new PopulationRank();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 120, 11.5, 120));
//	    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        // Place nodes in the pane
        Label lblYN = new Label("Do you want to Search for another Name (Y/N):");
        GridPane.setHalignment(lblYN, HPos.CENTER);
        pane.add(lblYN, 0, 0);

        TextField txtYN = new TextField();
        txtYN.setMaxWidth(33);
        txtYN.setMinWidth(33);
        GridPane.setHalignment(txtYN, HPos.CENTER);
        pane.add(txtYN, 0, 1);

        Button btnSubmit = new Button("Submit");
        pane.add(btnSubmit, 0, 3);
        GridPane.setHalignment(btnSubmit, HPos.LEFT);

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 0, 3);
        GridPane.setHalignment(btnExit, HPos.RIGHT);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Search Name Ranking Application"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isEmpty = txtYN.getText().isEmpty();
                String answer = txtYN.getText();

                if(isEmpty) {
                    showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please fill in the blank");
                }else {
                    if(!answer.equals("Y") && !answer.equals("N")) {
                        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please enter Y or N in the box");
                    }else {
                        if(answer.equals("Y")) {
                            RankingPanel rankPan = new RankingPanel();
                            try {
                                rankPan.start(primaryStage);
                            }catch(Exception e) {
                                System.out.println("Exception: " + e);
                            }

                        }else {
                            showAlert(Alert.AlertType.INFORMATION, pane.getScene().getWindow(), "Bye bye", "Bye bye!");
                            primaryStage.close();
                        }
                    }

                }
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                RankingPanel rankPan = new RankingPanel();
                try {
                    rankPan.start(primaryStage);
                }catch(Exception e) {
                    System.out.println("Exception: " + e);
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