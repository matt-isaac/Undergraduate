package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GameScreenController {
    @FXML
    private Button startButton;

    @FXML
    private Text highScore;

    @FXML
    private Text currentScore;

    public void initialize() {
        System.out.println("Game Screen Initialized.");
        startButton.setOnAction(event -> {System.out.println("Start Game!");});
    }


}
