package components;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Cell {
    private Button button;
    private Color color;
    private ImageView imageView;
    private Image flagImage = new Image("file:images/flag_img.png");
    private Image questionImage = new Image("file:images/question_img.png");
    //private int nclick = 0;
    private int columnIndex;
    private int rowIndex;
    private boolean mine;

    public Cell (int rIndex, int cIndex){

        columnIndex = cIndex;
        rowIndex = rIndex;
        button = new Button();
        button.setPrefWidth(20);
        button.setPrefHeight(20);
        button.setMaxHeight(20);
        button.setMaxHeight(20);
        imageView = new ImageView();
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(false);
        button.setGraphic(imageView);
        mine = false;


        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //nclick = nclick + 1;
                if (event.getButton() == MouseButton.SECONDARY){
                    if(imageView.getImage() == questionImage){
                        imageView.setImage(null);
                    } else if (imageView.getImage() == flagImage){
                        imageView.setImage(questionImage);
                    } else if (imageView.getImage() == null){
                        imageView.setImage(flagImage);
                    }

                }
                else{
                    if (isMine()){
                        System.out.println("Game Over.");
                    } else {
                        button.setDisable(true);
                    }

                }
            }
        });
    }

    public Button getButton() {
        return button;
    }

    public int getColumnIndex(){
        return columnIndex;
    }

    public int getRowIndex(){
        return rowIndex;
    }

    public boolean isMine(){
        return mine;
    }

    public void setMine(boolean tf){
        mine = tf;
    }
}
