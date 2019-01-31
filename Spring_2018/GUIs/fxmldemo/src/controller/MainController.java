package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    private HBox toolBar;

    @FXML
    private ToolBarController toolBarController;

    @FXML
    private Pane pane;

    public ToolBarController getToolBarController() {
        return toolBarController;
    }
}
