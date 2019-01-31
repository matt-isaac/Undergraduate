package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private Pane pane;

    @FXML
    private BorderPane borderPane;

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public Pane getPane(){
        return pane;
    }
}
