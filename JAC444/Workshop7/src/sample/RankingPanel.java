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


public class RankingPanel extends Application {
    PopulationRank popRank = new PopulationRank();
    SearchAgain searchAgain = new SearchAgain();

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
        Label lblYear = new Label("Enter the Year:");
        GridPane.setHalignment(lblYear, HPos.CENTER);
        pane.add(lblYear, 0, 0);

        TextField txtYear = new TextField();
        GridPane.setHalignment(txtYear, HPos.CENTER);
        pane.add(txtYear, 0, 1);

        Label lblGender = new Label("Enter the Gender (M/F):");
        GridPane.setHalignment(lblGender, HPos.CENTER);
        pane.add(lblGender, 0, 2);

        TextField txtGender = new TextField();
        txtGender.setMaxWidth(33);
        txtGender.setMinWidth(33);
        GridPane.setHalignment(txtGender, HPos.CENTER);
        pane.add(txtGender, 0, 3);

        Label lblName = new Label("Enter the Name:");
        GridPane.setHalignment(lblName, HPos.CENTER);
        pane.add(lblName, 0, 4);

        TextField txtName = new TextField();
        GridPane.setHalignment(txtName, HPos.CENTER);
        pane.add(txtName, 0, 5);

        Label lblResult = new Label();
        GridPane.setHalignment(lblResult, HPos.CENTER);
        pane.add(lblResult, 0, 6);

        Button btnSubmit = new Button("Submit Query");
        pane.add(btnSubmit, 0, 7);
        GridPane.setHalignment(btnSubmit, HPos.LEFT);

        Button btnExit = new Button("Exit");
        pane.add(btnExit, 0, 7);
        GridPane.setHalignment(btnExit, HPos.RIGHT);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Search Name Ranking Application"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(txtYear.getText().isEmpty());
                System.out.println(txtGender.getText().isEmpty());
                System.out.println(txtName.getText().isEmpty());
                boolean isEmpty = txtYear.getText().isEmpty() || txtGender.getText().isEmpty() || txtName.getText().isEmpty();

                System.out.println(isEmpty);

                if(isEmpty) {
                    showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please fill in the blank");
                }else {
                    String gender = txtGender.getText();
                    String year = txtYear.getText();
                    int yearInt = Integer.parseInt(year);
                    String name = txtName.getText();
                    if(!gender.equals("M") && !gender.equals("F")) {
                        if(yearInt < 2001 || yearInt > 2010) {
                            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please enter M or F in the Name box\n"
                                    + "Please enter between 2001 and 2010 in the Year box");
                        }else {
                            showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please enter M or F in the Name box");
                        }

                    }else if(yearInt < 2001 || yearInt > 2010) {
                        showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please enter between 2001 and 2010 in the Year box");
                    }else {
                        int rank;
                        popRank.initialize();

                        if(gender.equals("M")) {
                            popRank.getFileInfo(year);
                            rank = popRank.findName(gender, name);
                            if(rank != -1) {
                                lblResult.setText("Boy name " + txtName.getText() + " is ranked #" + rank + " in " + txtYear.getText() + " year.");
                            }else {
                                lblResult.setText("Boy name " + txtName.getText() + " in " + year + ": No result");
                            }

                        }else {
                            popRank.getFileInfo(year);
                            rank = popRank.findName(gender, name);
                            if(rank != -1) {
                                lblResult.setText("Girl name " + txtName.getText() + " is ranked #" + rank + " in " + txtYear.getText() + " year.");
                            }else {
                                lblResult.setText("Girl name " + txtName.getText() + " in " + year + ": No result");
                            }
                        }
                    }

                }
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                try {
                    searchAgain.start(primaryStage);
                }catch(Exception e) {
                    System.out.println("Exception" + e);
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