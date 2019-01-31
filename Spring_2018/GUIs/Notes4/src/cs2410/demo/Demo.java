package cs2410.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Optional;

public class Demo extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
/*        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        dialog.setContentText("This is the content text: lWlW");

        dialog.getDialogPane().getStylesheets().add("resources/custom.css");
        dialog.getDialogPane().setPrefSize();

        dialog.showAndWait();*/

        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");
        btn1.setLayoutX(200); //set position
        btn1.setLayoutY(100);
        btn2.setLayoutX(400);
        btn2.setLayoutY(200);

        btn1.setOnAction(this);

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override // annotation - will run fine w/o it. It helps compiler.
            public void handle(ActionEvent event) {
                System.out.println("you presssed the button 1");
            }
        });

        @Override
                public void handle(Event event){
            if(event.getSource() == btn1) {
                System.out.print("you clicked button 1")
            } else if (event.getSource() == btn2){

            }
            System.out.println("Now print this");
        }

        Pane pane = new Pane();
        pane.getChildren().addAll(btn1, btn2);
        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setScene(scene);

        primaryStage.show();

        //Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds(); //get screen dimensions to calculate centers.
        //primaryScreenBounds.get

    }
}
