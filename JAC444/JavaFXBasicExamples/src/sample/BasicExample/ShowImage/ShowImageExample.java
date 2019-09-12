package sample.BasicExample.ShowImage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShowImageExample extends Application {
  @Override 
  public void start(Stage primaryStage) {
    // Create a pane to hold the image views
    Pane pane = new HBox(10); //An HBox is a pane that places all nodes horizontally in one row.
    pane.setPadding(new Insets(5, 5, 5, 5));
    Image image = new Image(getClass().getResourceAsStream("image/images.gif")); //you can also replace this with a URL to target and image from there.
    pane.getChildren().add(new ImageView(image));
    
    ImageView imageView2 = new ImageView(image);
    imageView2.setFitHeight(100);
    imageView2.setFitWidth(100);
    pane.getChildren().add(imageView2);   

    ImageView imageView3 = new ImageView(image);
    imageView3.setRotate(90);
    pane.getChildren().add(imageView3);     
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(pane);
    primaryStage.setTitle("ShowImage"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }
  
  public static void main(String[] args) {
    launch(args);
  }
}

