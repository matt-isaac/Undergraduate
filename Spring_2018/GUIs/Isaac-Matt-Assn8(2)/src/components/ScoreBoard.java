package components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScoreBoard {

    private HBox hBox = new HBox();
    private VBox buttonVBox = new VBox();
    private Text minesRemainingLabel = new Text("Mines Remaining");
    private Text mineCountText = new Text("");
    private int mineCount = 100;
    private Text timeTextLabel = new Text("Time");
    private Text timeText = new Text("0");
    private VBox timeVBox = new VBox();
    private VBox mineVBox = new VBox();
    private Button startButton = new Button("Start Sweeping");
    private Button resetButton = new Button("Reset");

    public ScoreBoard(){
        timeVBox.getChildren().addAll(timeTextLabel, timeText);
        timeVBox.setAlignment(Pos.CENTER);

        mineVBox.getChildren().addAll(minesRemainingLabel, mineCountText);
        mineVBox.setAlignment(Pos.CENTER);

        buttonVBox.getChildren().addAll(startButton, resetButton);
        buttonVBox.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(mineVBox, buttonVBox, timeVBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(50);
        hBox.setPadding(new Insets(20));
    }

    public HBox gethBox() {
        return hBox;
    }

    public void setStartButtonAction(EventHandler<ActionEvent> handler){
        startButton.setOnAction(handler);
    }

    public void setResetButtonActioni(EventHandler<ActionEvent> handler){
        resetButton.setOnAction(handler);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Text getMineCountText() {
        return mineCountText;
    }

    public void setMineCountText(int count){
        mineCountText.setText(Integer.toString(count));
    }

    public String getTimeText(){
        return timeText.getText();
    }

    public void setTimeText(int i){
        timeText.setText(Integer.toString(i));
    }
}

