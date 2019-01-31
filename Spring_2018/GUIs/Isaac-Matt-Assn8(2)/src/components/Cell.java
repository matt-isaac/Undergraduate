package components;

import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Cell extends Pane {

    private int x;
    private int y;

    private ImageView imageView;
    private Image flagImage = new Image("file:images/flag_img.png");
    private Image questionImage = new Image("file:images/question_img.png");
    private Image mineImage = new Image("file:images/boom_img.png");
    private Text nSurroundingMines = new Text("");


    boolean flagged;
    boolean questionMark;
    boolean revealed;
    boolean mine;
    boolean exploded;

    Color color;

    public Cell(int x1, int y1) {
        x = x1;
        y = y1;
        nSurroundingMines.setLayoutX(10);
        nSurroundingMines.setLayoutY(20);
        flagged = false;
        questionMark = false;
        revealed = false;
        exploded = false;
        mine = false;
        this.setStyle("-fx-background-color: deepskyblue");
        imageView = new ImageView();
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setLayoutX(2.5);
        imageView.setLayoutY(2.5);
        this.setPadding(new Insets(5.0));
        this.getChildren().add(imageView);
        this.getChildren().add(nSurroundingMines);
        //this.setWidth(10);
        //this.setHeight(10);
    }

//    public void setColor(String color){
//        String fxCommand = "fx-background-color: " + color;
//        this.setStyle(fxCommand);
//    }

    public void setMine(boolean bool){
        mine = bool;
    }

    public boolean isFlagged(){
        return flagged;
    }

    public boolean isExploded() {
        return exploded;
    }

    public boolean isMine() {
        return mine;
    }

    public boolean isQuestionMark() {
        return questionMark;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Image getQuestionImage() {
        return questionImage;
    }

    public Image getFlagImage() {
        return flagImage;
    }

    public Image getMineImage() {
        return mineImage;
    }

    public void setFlagged(boolean bool){
        flagged = bool;
    }

    public void setQuestionMark(boolean bool){
        questionMark = bool;
    }

    public void setRevealed(boolean bool){
        revealed = bool;
    }

    public void setExploded(boolean bool){
        exploded = bool;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setnSurroundingMines(int num){
        nSurroundingMines.setText(Integer.toString(num));
    }

    public void setnSurroundingMinesText(String str){
        nSurroundingMines.setText(str);
    }


}
