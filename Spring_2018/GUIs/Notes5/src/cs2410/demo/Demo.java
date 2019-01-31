package cs2410.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Shape;

public class Demo extends Application{
    private Rectangle tmpRect;
    private double deltaX;
    private double deltaY;
    ColorPicker picker = new ColorPicker();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane mainPane = new Pane();
        Pane topPane = new Pane();
        topPane.setPrefSize(500, 100);
        topPane.setStyle("-fx-backgroud-color: AQUA");
        Pane drawPane = new Pane();
        drawPane.setPrefSize(500, 400);
        drawPane.setLayoutY(100);

        picker.setStyle("-fx-color-label-visible: false");
        picker.getStyleClass().add("button");
        //picker.getStyleClass().add("split-button");
        //picker.getStyleClass().add(null);
        picker.setValue(Color.DARKOLIVEGREEN); // set starting color OR use to chage color picker to color of shape clicked on
        picker.getValue(); // get color value from picker to use when drawing shape.
        // when in edit mode, need an event handler to change shape color when color picker is interacted with
        picker.setOnAction(event -> {
            rect1.setFill(picker.getValue());

        });
        topPane.getChildren().add(picker);


        Circle cir1 = new Circle();
        cir1.setOnMouseClicked((event) -> {
            Circle tmpCir = (Circle)event.getSource();
            Paint tmp = cir1.getFill();
            cir1.setFill(cir1.getStroke());
            cir1.setStroke(tmp);
        });

        private void initDrawPane(Pane pane){
            pane.setOnMousePressed((event -> {
                tmpRect = new Rectangle();
                tmpRect.setX(event.setX());
                tmpRect.setY(event.getY());
            }));

            drawPane.setOnDragDetected(event -> {
                //Add rectangle to pane.
                pane.getChildren.add(tmpRect)
            });
            pane.setOnMouseDragged(event -> {
               //set width and height
            });
            //pane.setOnMouseDragged();
            //pane.setOnMouseReleased();

        }


        // dragging shape - circle specific
        private void initShape(Circle cir){
            cir.setOnMousePressed(event -> {
                deltaX = event.getX() - cir.getCenterX();
                deltaY = event.getY() - cir.getCenterY();
            });
            cir.setOnMouseDragged(event -> {
                cir.setCenterX(event.getX() - deltaX);
                cir.setCenterY(event.getY() - deltaY);
            });
        }

        // dragging shape - generalized
        private void initShape(Shape shape){
            shape.setOnMousePressed(event -> {
                deltaX = event.getX();
                deltaY = event.getY();
                picker.setValue((Color)shape.getFill());
            });
            shape.setOnMouseDragged( event -> {
                shape.setTranslateX(shape.getTranslateX() + event.getX() - deltaX);
                shape.setTranslateY(shape.getTranslateY() + event.getY() - deltaY);
            });
        }



        Rectangle clip = new Rectangle();
        clip.setHeight(50);
        clip.setWidth(50);
        clip.setX(25);
        clip.setY(25);

        drawPane.setClip(clip);

        mainPane.getChildren().addAll(topPane, drawPane);
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();


        /*Circle cir = new Circle();
        cir.setCenterX(150);
        cir.setCenterY(100);
        cir.setRadius(50);
        cir.setFill(Color.DARKSALMON);
        cir.setStroke(Color.CHARTREUSE);
        cir.setStrokeWidth(5);

        Rectangle rect1 = new Rectangle();
        rect1.setX(300);
        rect1.setY(50);
        rect1.setWidth(50);
        rect1.setHeight(100);
        rect1.setFill(Color.CRIMSON);


        Pane pane = new Pane();
        Path path1 = new Path();
        path1.getElements().add(new MoveTo(100, 100));
        path1.getElements().add(new LineTo(50, 200));
        path1.setStroke(Color.STEELBLUE);
        path1.setStrokeWidth(10);
        path1.getElements().add(new LineTo(350, 200));
        path1.setFill(Color.TURQUOISE);

        pane.getChildren().addAll(cir, rect1, path1);

        Scene scene = new Scene(pane, 500, 400);

        primaryStage.setScene(scene);

        primaryStage.show();*/
    }
}

