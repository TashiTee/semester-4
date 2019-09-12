package sample;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AddressBook extends Application{

    @Override
    public void start(Stage primaryStage) {
        RememberSeek seekNumStore = new RememberSeek();
        AddressBook addressBook = new AddressBook();
        AddressInfo test = new AddressInfo();
        ArrayList<AddressInfo> addressList = new ArrayList<AddressInfo>();
        try {
            RandomAccessFile raf = new RandomAccessFile("file.txt", "rw");
            addressBook.start(primaryStage, test, raf);
        }catch(Exception e) {
            System.err.println("Exception occurs");
        }
    }

    public void start(Stage primaryStage, AddressInfo test, RandomAccessFile raf) {
        RememberSeek seekNumStore = new RememberSeek();
        GridPane pane1 = new GridPane();
        pane1.setAlignment(Pos.CENTER);
        pane1.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane1.setHgap(5.5);
        pane1.setVgap(5.5);

        // Place nodes in the pane
        Label lblFirstName = new Label("First Name:");
        GridPane.setHalignment(lblFirstName, HPos.CENTER);
        pane1.add(lblFirstName, 0, 0);

        TextField txtFirstName = new TextField();
        GridPane.setHalignment(txtFirstName, HPos.CENTER);
        pane1.add(txtFirstName, 1, 0);

        Label lblLastName = new Label("Last Name:");
        GridPane.setHalignment(lblLastName, HPos.CENTER);
        pane1.add(lblLastName, 0, 1);

        TextField txtLastName = new TextField();
        GridPane.setHalignment(txtLastName, HPos.CENTER);
        pane1.add(txtLastName, 1, 1);

        Label lblCity = new Label("City:");
        GridPane.setHalignment(lblCity, HPos.CENTER);
        pane1.add(lblCity, 0, 2);

        TextField txtCity = new TextField();
        GridPane.setHalignment(txtCity, HPos.CENTER);
        pane1.add(txtCity, 1, 2);

        Label lblProvince = new Label("Province:");
        GridPane.setHalignment(lblProvince, HPos.CENTER);
        pane1.add(lblProvince, 0, 3);

        ChoiceBox cbProvince = new ChoiceBox();
        cbProvince.setItems(FXCollections.observableArrayList(
                "Alberta", "British Columbia", "Manitoba", "New Btunswick", "Newfoundland and Labrador",
                "Nova Scotia", "Ontario", "Prince Edward Island", "Quebec", "Saskatchewan"
        ));
        pane1.add(cbProvince, 1, 3);

        Label lblPostalCode = new Label("Postal Code:");
        GridPane.setHalignment(lblPostalCode, HPos.CENTER);
        pane1.add(lblPostalCode, 0, 4);

        TextField txtPostalCode = new TextField();
        GridPane.setHalignment(txtPostalCode, HPos.CENTER);
        pane1.add(txtPostalCode, 1, 4);

        Button btnAdd = new Button("Add");
        pane1.add(btnAdd, 0, 5);
        GridPane.setHalignment(btnAdd, HPos.CENTER);

        Button btnFirst = new Button("First");
        pane1.add(btnFirst, 1, 5);
        GridPane.setHalignment(btnFirst, HPos.LEFT);

        Button btnNext = new Button("Next");
        pane1.add(btnNext, 0, 6);
        GridPane.setHalignment(btnNext, HPos.CENTER);

        Button btnPrevious = new Button("Previous");
        pane1.add(btnPrevious, 1, 6);
        GridPane.setHalignment(btnPrevious, HPos.LEFT);

        Button btnLast = new Button("Last");
        pane1.add(btnLast, 0, 7);
        GridPane.setHalignment(btnLast, HPos.CENTER);

        Button btnUpdate = new Button("Update");
        pane1.add(btnUpdate, 1, 7);
        GridPane.setHalignment(btnUpdate, HPos.LEFT);

        Button btnExit = new Button("Exit Address Book");
        pane1.add(btnExit, 1, 8);
        GridPane.setHalignment(btnExit, HPos.LEFT);

        Scene scene = new Scene(pane1);
        primaryStage.setTitle("Address Book"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        long seekNum;

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                boolean isEmpty = txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtCity.getText().isEmpty()
                        || cbProvince.getValue().toString().isEmpty() || txtPostalCode.getText().isEmpty();
                if(isEmpty) {
                    showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "Please fill in the blank");
                }else {
                    if(txtPostalCode.getText().length() != 6) {
                        showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "Please write right postal code with no space\n(ex. A1A1A1)");
                    }else {
                        try {
                            if(raf.length() != 0)
                                raf.seek(raf.length() - 1);
                            else
                                raf.seek(raf.length());

                            if(raf.length() != 0) {
                                raf.write(0x0d);
                                raf.write(0x0a);
                            }

                            System.out.println(raf.length());

                            test.setFirstName(txtFirstName.getText());
                            test.setLastName(txtLastName.getText());
                            test.setCity(txtCity.getText());
                            test.setProvince(cbProvince.getValue().toString());
                            test.setPostalCode(txtPostalCode.getText());
                            test.write(raf);
                            seekNumStore.setSeekNum(raf.length() - 180);



                        }catch(IOException e) {
                            System.out.println("IoException occurs: " + e);
                        }catch(Exception e) {
                            System.out.println("Exception occurs: " + e);
                        }

                        showAlert(Alert.AlertType.INFORMATION, pane1.getScene().getWindow(), "Add Complete!", "Successfully add the content");
                    }
                }

            }

        });

        btnFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddressInfo info = new AddressInfo();

                try {
                    seekNumStore.setSeekNum(0);
                    if(raf.length() == 0) {
                        showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "No page in this address books\nPlease add content");
                    }else {
                        raf.seek(0);
                        info.read(raf);

                        txtFirstName.setText(info.getFirstName());
                        txtLastName.setText(info.getLastName());
                        txtCity.setText(info.getCity());
                        txtPostalCode.setText(info.getPostalCode());

                        String provinceN = info.getProvince();
                        Pattern p = Pattern.compile("[a-z]");
                        int count = 0;
                        for(int i = 0; i < provinceN.length(); i++) {
                            Matcher m = p.matcher(provinceN.substring(i));
                            if(m.find()) {
                                count++;
                            }
                        }
                        String provinceName = info.getProvince().substring(0, count);

                        cbProvince.setValue(provinceName);
                    }

                }catch(Exception e) {
                    System.err.println("Exception occurs: " + e);
                }
            }
        });

        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddressInfo info = new AddressInfo();
                long seekNum = seekNumStore.getSeekNum();


                try {
                    long rafLength = raf.length();

                    if(seekNum == raf.length() - 180) {
                        showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "This is last page");
                    }else if(seekNum < 0){
                        showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "You didn't open any page.\nPlease press 'first' or 'last' button");
                    }else {
                        raf.seek(seekNum + 181);
                        seekNumStore.setSeekNum(seekNum + 181);

                        info.read(raf);

                        txtFirstName.setText(info.getFirstName());
                        txtLastName.setText(info.getLastName());
                        txtCity.setText(info.getCity());
                        txtPostalCode.setText(info.getPostalCode());

                        String provinceN = info.getProvince();
                        Pattern p = Pattern.compile("[a-z]");
                        int count = 0;
                        for(int i = 0; i < provinceN.length(); i++) {
                            Matcher m = p.matcher(provinceN.substring(i));
                            if(m.find()) {
                                count++;
                            }
                        }
                        System.out.println(count);
                        String provinceName = info.getProvince().substring(0, count);
                        System.out.println(provinceName);
                        cbProvince.setValue(provinceName);
                        System.out.println(seekNumStore.getSeekNum());
                    }
                }catch(Exception e) {
                    System.err.println("Exception: " + e);
                }
            }
        });

        btnPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddressInfo info = new AddressInfo();
                long seekNum = seekNumStore.getSeekNum();


                try {
                    if(seekNum == 0) {
                        showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "This is first page");
                    }else if(seekNum < 0){
                        showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "You didn't open any page.\nPlease press 'first' or 'last' button");
                    }else {
                        long rafLength = raf.length();

                        raf.seek(seekNum - 181);
                        seekNumStore.setSeekNum(seekNum - 181);

                        info.read(raf);

                        txtFirstName.setText(info.getFirstName());
                        txtLastName.setText(info.getLastName());
                        txtCity.setText(info.getCity());
                        txtPostalCode.setText(info.getPostalCode());

                        String provinceN = info.getProvince();
                        Pattern p = Pattern.compile("[a-z]");
                        int count = 0;
                        for(int i = 0; i < provinceN.length(); i++) {
                            Matcher m = p.matcher(provinceN.substring(i));
                            if(m.find()) {
                                count++;
                            }
                        }
                        System.out.println(count);
                        String provinceName = info.getProvince().substring(0, count);
                        System.out.println(provinceName);
                        cbProvince.setValue(provinceName);
                        System.out.println(seekNumStore.getSeekNum());
                    }


                }catch(Exception e) {
                    System.err.println("Exception: " + e);
                }
            }
        });

        btnLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //182
                AddressInfo info = new AddressInfo();

                try {
                    if(raf.length() == 0) {
                        showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "No page in this address books\nPlease add content");
                    }
                    else {
                        if(raf.length() != 180) {
                            raf.seek(raf.length() - 180);
                            seekNumStore.setSeekNum(raf.length() - 180);
                        }
                        else {
                            raf.seek(0);
                            seekNumStore.setSeekNum(0);
                        }

                        info.read(raf);

                        txtFirstName.setText(info.getFirstName());
                        txtLastName.setText(info.getLastName());
                        txtCity.setText(info.getCity());
                        txtPostalCode.setText(info.getPostalCode());

                        String provinceN = info.getProvince();
                        Pattern p = Pattern.compile("[a-z]");
                        int count = 0;
                        for(int i = 0; i < provinceN.length(); i++) {
                            Matcher m = p.matcher(provinceN.substring(i));
                            if(m.find()) {
                                count++;
                            }
                        }
                        System.out.println(count);
                        String provinceName = info.getProvince().substring(0, count);
                        System.out.println(provinceName);
                        cbProvince.setValue(provinceName);
                        System.out.println(seekNumStore.getSeekNum());
                    }
                }catch(Exception e) {
                    System.err.println("Exception: " + e);
                }
            }
        });

        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddressInfo info = new AddressInfo();

                long seekNum = seekNumStore.getSeekNum();

                if(seekNum < 0){
                    showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "You didn't open any page.\nPlease press 'first' or 'last' button");
                }else {
                    boolean isEmpty = txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtCity.getText().isEmpty()
                            || cbProvince.getValue().toString().isEmpty() || txtPostalCode.getText().isEmpty();
                    if(isEmpty) {
                        showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "Please fill in the blank");
                    }else {
                        if(txtPostalCode.getText().length() != 6) {
                            showAlert(Alert.AlertType.ERROR, pane1.getScene().getWindow(), "Form Error!", "Please write right postal code with no space\n(ex. A1A1A1)");
                        }else {
                            try {
                                raf.seek(seekNum);

                                test.setFirstName(txtFirstName.getText());
                                test.setLastName(txtLastName.getText());
                                test.setCity(txtCity.getText());
                                test.setProvince(cbProvince.getValue().toString());
                                test.setPostalCode(txtPostalCode.getText());
                                test.write(raf);

                                showAlert(Alert.AlertType.INFORMATION, pane1.getScene().getWindow(), "Update Complete", "Successfully updated");

                            }catch(Exception e) {
                                System.err.println("Exception: " + e);
                            }
                        }
                    }
                }
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TesterApplication testerApp = new TesterApplication();
                try {
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
