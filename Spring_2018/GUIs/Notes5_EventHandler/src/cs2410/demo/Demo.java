package cs2410.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Demo extends Application {
    Button btn1 = new Button("Button 1");
    Button btn2 = new Button("Button 2");

    @Override
    public void start(Stage primaryStage) throws Exception {

        ArrayList<Image> imgList = new ArrayList<Image>();

        Image image = new Image("http://parking.usu.edu/ou-images/GameDayPark.jpg");
        Image image2 = new Image("http://www.usu.edu/today/images/large/OldMainPhoto.jpg");
        //Image image3 = new Image("file:data/usu1.png");


        ImageView imageView = new ImageView(image2);
        imageView.setFitWidth(300);
        imageView.setSmooth(true);
        imageView.setPreserveRatio(true);

        // imageView.setImage(<image object>)
        // read in file of url's,
        // model will have title, url string, (and image object?)
        // load next and previous while viewing particular picture.

        Pane pane = new Pane();
        pane.getChildren().addAll(imageView);
        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();


        //btn1.setLayoutX(200);

        //btn1.setOnAction((ActionEvent event) -> {System.out.println("You clicked Button 1");});

/*        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == btn1) {
                    System.out.println("You clicked Button 1");
                } else if (event.getSource() == btn2) {
                    System.out.println("You clicked Button 2");
                }
            }
        };*/
    }
}
