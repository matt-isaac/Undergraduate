package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;



public class ToolBarController {
    @FXML
    private Button btn1;

    @FXML
    RadioButton radio1;

    @FXML
    RadioButton radio2;

    @FXML
    RadioButton radio3;

    public void registerHandler(EventHandler <ActionEvent> handler) {
        btn1.setOnAction(handler);
    }
}
