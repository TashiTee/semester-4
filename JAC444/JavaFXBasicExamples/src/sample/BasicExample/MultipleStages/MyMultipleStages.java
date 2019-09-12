package sample.BasicExample.MultipleStages;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyMultipleStages extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		//creating a simple scene and placing a button
		Scene scene = new Scene(new Button("OK"),200,250);
		primaryStage.setTitle("First Stage");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Stage stage = new Stage();
		stage.setTitle("Second Stage");
		stage.setScene(new Scene(new Button("Second Stage Button"), 200, 250));
		stage.setResizable(false);
		stage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}


}
