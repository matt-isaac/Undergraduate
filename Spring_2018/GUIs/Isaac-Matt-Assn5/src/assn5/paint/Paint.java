package assn5.paint;

import assn5.toolpane.ToolPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/**
 * @author Matt Isaac
 * @version 2
 */

public class Paint extends Application{
    // Private member variables
    /**
     * Area designated for drawing
     */
    private Pane drawPane = new Pane();
    /**
     * Area designated for toolbar
     */
    private ToolPane toolPane = new ToolPane();
    /**
     * Area that holds drawPane and toolpane
     */
    private Pane mainPane = new Pane();
    /**
     * Variable to mark starting mouse X position
     */
    private double startX;
    /**
     * Variable to mark starting mouse Y position
     */
    private double startY;

    /**
     * Rectangle shape
     */
    private Rectangle rectangle;
    /**
     * Ellipse shape
     */
    private Ellipse ellipse;
    /**
     * Freehand path shape
     */
    private Path path;


    /**
     * Main start method
     * @param primaryStage Main drawing screen
     */
    @Override
    public void start(Stage primaryStage){
        /**
         * Set layout preferences
         */
        drawPane.setPrefSize(800, 500);
        toolPane.setPrefSize(800, 100);
        mainPane.setPrefSize(800, 600);
        mainPane.getChildren().addAll(toolPane, drawPane);
        Rectangle clip = new Rectangle(0, 50, 800, 500);
        drawPane.setClip(clip);
        Scene scene = new Scene(mainPane, 800, 600);

        drawPaneHandler();

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * Brings shape to the top "layer" of drawing area
     * @param shape shape that triggered event handler
     */
    private void bringToFront(Shape shape){
        drawPane.getChildren().remove(shape);
        drawPane.getChildren().add(shape);
    }

    /**
     * Usesd in setShapeHandler to update toolBar settings when shape is pressed.
     * @param shape shape that triggered event handler
     */
    private void updateToolBar(Shape shape){
        toolPane.setFillPickerValue((Color)shape.getFill());
        toolPane.setStrokePickerValue((Color)shape.getStroke());
        toolPane.setStrokeSizeValue((int)shape.getStrokeWidth());
    }

    /**
     * Event handler for interacting with shape in edit mode.
     * @param shape shape that triggered event handler
     */
    private void setShapeHandler(Shape shape) {

        shape.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolPane.editBtnSelected()) {
                    toolPane.setFillPickerAction(event1 -> {
                        shape.setFill(toolPane.getFillPickerValue());
                    });
                    toolPane.setStrokePickerAction(event2 -> {
                        shape.setStroke(toolPane.getStrokePickerValue());
                    });
                    toolPane.setStrokeSizeAction(event3 -> {
                        shape.setStrokeWidth(toolPane.getStrokeSizeValue());
                    });
                }
            }

        });

        shape.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(toolPane.editBtnSelected()) {
                    startX = event.getX();
                    startY = event.getY();
                    updateToolBar(shape);
                    bringToFront(shape);
                    toolPane.setFillPickerAction(event1 -> {
                        if(toolPane.editBtnSelected()) {
                            shape.setFill(toolPane.getFillPickerValue());
                        }
                    });
                    toolPane.setStrokePickerAction(event2 -> {
                        if(toolPane.editBtnSelected()) {
                            shape.setStroke(toolPane.getStrokePickerValue());
                        }
                    });
                    toolPane.setStrokeSizeAction(event3 -> {
                        if(toolPane.editBtnSelected()) {
                            shape.setStrokeWidth(toolPane.getStrokeSizeValue());
                        }
                    });

                }
            }
        });
        shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolPane.editBtnSelected()) {
                    shape.setTranslateX(shape.getTranslateX() + event.getX() - startX);
                    shape.setTranslateY(shape.getTranslateY() + event.getY() - startY);
                }
            }
        });


    }

    /**
     * Erase method event handler. Removes shape from drawPane
     * @param shape shape that triggered event handler
     */
    private void initErase(Shape shape) {
        shape.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(toolPane.eraseBtnSelected()){
                    drawPane.getChildren().remove(shape);
                }
            }
        });
    }


    /**
     * Handles actions on drawing pane.
     * Logic for different "modes" from toggle buttons.
     */
    private void drawPaneHandler(){
        drawPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toolPane.rectBtnSelected()) {
                    startX = event.getX();
                    startY = event.getY();
                    rectangle = new Rectangle();
                    rectangle.setStrokeWidth(toolPane.getStrokeSizeValue());
                    rectangle.setFill(toolPane.getFillPickerValue());
                    rectangle.setStroke(toolPane.getStrokePickerValue());
                    rectangle.setX(startX);
                    rectangle.setY(startY);
                    drawPane.getChildren().add(rectangle);
                } else if (toolPane.ellBtnSelected()){
                    startX = event.getX();
                    startY = event.getY();
                    ellipse = new Ellipse();
                    ellipse.setStrokeWidth(toolPane.getStrokeSizeValue());
                    ellipse.setFill(toolPane.getFillPickerValue());
                    ellipse.setStroke(toolPane.getStrokePickerValue());
                    ellipse.setCenterX(startX);
                    ellipse.setCenterY(startY);
                    drawPane.getChildren().add(ellipse);
                } else if (toolPane.freeBtnSelected()) {
                    path = new Path();
                    drawPane.getChildren().add(path);
                    path.setStrokeWidth(toolPane.getStrokeSizeValue());
                    path.setStroke(toolPane.getStrokePickerValue());
                    path.getElements().add(new MoveTo(event.getX(), event.getY()));
                }
            }
        });

        drawPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(toolPane.rectBtnSelected()){
                    double endX = event.getX();
                    double endY = event.getY();
                    rectangle.setHeight(Math.abs(event.getY() - startY));
                    rectangle.setWidth(Math.abs(event.getX() - startX));
                    if(startX > endX){
                        rectangle.setX(endX);
                    }
                    if (startY > endY){
                        rectangle.setY(endY);
                    }
                } else if (toolPane.ellBtnSelected()){
                    double endY = event.getY();
                    double endX = event.getX();
                    ellipse.setRadiusY(0.5 * Math.abs((double)(endY - startY)));
                    ellipse.setRadiusX(0.5 * Math.abs((double)(endX - startX)));
                    ellipse.setCenterY((Math.abs((double)startY - endY) / 2.0) + startY);
                    ellipse.setCenterX((Math.abs((double)startX - endX) / 2.0) + startX);
                    if(startX > endX){
                        ellipse.setCenterX(startX - ellipse.getRadiusX());
                    }
                    if(startY > endY){
                        ellipse.setCenterY(startY - ellipse.getRadiusY());
                    }
                } else if (toolPane.freeBtnSelected()){
                    path.getElements().add(new LineTo(event.getX(), event.getY()));
                }
            }
        });

        drawPane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(toolPane.rectBtnSelected()){
                    setShapeHandler(rectangle);
                    initErase(rectangle);
                } else if (toolPane.ellBtnSelected()){
                    setShapeHandler(ellipse);
                    initErase(ellipse);
                } else if (toolPane.freeBtnSelected()){
                    setShapeHandler(path);
                    initErase(path);

                }
            }
        });
    }

}

