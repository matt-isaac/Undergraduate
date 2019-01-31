package cs2410.layoutmanager;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LayoutManager extends Application {
    @Override
     public void start(Stage primaryStage) throws Exception{
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints col0 = new ColumnConstraints();
        //col0.setPercentWidth(50);
        col0.setHgrow(Priority.SOMETIMES);
        gridPane.getColumnConstraints().add(col0);
        ColumnConstraints col1 = new ColumnConstraints();
        //col1.setPercentWidth(25);
        col1.setHgrow(Priority.NEVER);
        ColumnConstraints col2 = new ColumnConstraints();
        //col2.setPercentWidth(25);
        col1.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(col1, col2);

        Button btn1 = new Button("Button 1");
        GridPane.setConstraints(btn1, 2, 2);
        GridPane.setHalignment(btn1, HPos.CENTER);
        GridPane.setValignment(btn1, VPos.TOP);

        Text text1 = new Text("Text 1");
        GridPane.setConstraints(text1, 0, 0);
        GridPane.setHalignment(text1, HPos.CENTER);

        Circle cir1 = new Circle(25, Color.RED);
        GridPane.setConstraints(cir1, 1, 1);
        GridPane.setValignment(cir1, VPos.TOP);

        gridPane.getChildren().addAll(btn1, text1, cir1);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
