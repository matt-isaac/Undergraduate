package controller;

import components.ColorPanel;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainViewController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private ControlsBarController controlsBarController;

    @FXML
    private GameBoardController gameBoardController;

    @FXML
    private MenuBarController menuBarController;

    @FXML
    private HBox gameBoard;

    @FXML
    private HBox controlsBar;

    @FXML
    private MenuBar menuBar;

    private ArrayList<ColorPanel> sequence = new ArrayList<>();
    private Random randGen = new Random();

    private long timeStamp = 0;
    private long tmp = 0;
    private long diff = 0;

    private AnimationTimer animationTimer;

    // Access controllers
    public ControlsBarController getControlsBarController() {
        return controlsBarController;
    }

    public GameBoardController getGameBoardController() {
        return gameBoardController;
    }

    public MenuBarController getMenuBarController() {
        return menuBarController;
    }

    public void initialize() {
        getGameBoardController().initGameBoard();
        getControlsBarController().initControlsBar();
        getMenuBarController().initMenuBar();
    }
}