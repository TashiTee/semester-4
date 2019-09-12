package sample.BasicExample.Button;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyJavaFX extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Creating a simple button
		Button btnOK = new Button ("OK");
		
		Scene scene = new Scene(btnOK, 200,250);
		primaryStage.setTitle("MyJavaFx");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	public static void main(String[] args) {
		launch(args);
	}

}
