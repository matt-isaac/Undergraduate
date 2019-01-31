package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;

public class MenuBarController {
    @FXML
    private ColorPicker menuChooseColor;

    @FXML
    private MenuItem menuChooseMode;

    @FXML
    private MenuItem menuHighScoreList;

    @FXML
    private MenuItem menuHistory;

    @FXML
    private MenuItem menuClearHistory;

    @FXML
    private MenuItem menuAbout;

    @FXML
    private MenuItem menuRules;

    private Alert rules = new Alert(Alert.AlertType.INFORMATION);

    private Alert about = new Alert(Alert.AlertType.INFORMATION);

    public void initMenuBar(){
        rules.setTitle("Rules of Simon");
        rules.setHeaderText("");
        rules.setContentText("The computer will randomly generate sequences of the " +
                "colored lights. Your job is to repeat the sequence exactly " +
                "as it was given. If you get it correct, the computer will repeat " +
                "the same sequence, adding one more color to the end. This will " +
                "continue until you make a mistake. Your score is the length of " +
                "the final sequence.");
        rules.setHeight(300);

        about.setTitle("About Simon");
        about.setHeaderText("");
        about.setContentText("Simon is an electronic game of memory skill [originally] " +
                " invented by Ralph H. Baer and Howard J. Morrison, with software " +
                " programming by Lenny Cope. The device creates a series of tones " +
                " and lights and requires a user to repeat the sequence." +
                "If the user succeeds, the series becomes progressively longer and " +
                "more complex. Once the user fails, the game is over. The original " +
                "version was manufactured and distributed by Milton Bradley. " +
                "(SOURCE: Wikipedia.com)");
        about.setHeight(400);
        menuChooseColor.setOnAction(event -> {
            menuChooseColor.getValue();
        });
        menuRules.setOnAction(event -> rules.showAndWait());

        menuAbout.setOnAction(event -> about.showAndWait());

    }

    public MenuItem getMenuHistory() {
        return menuHistory;
    }

    public MenuItem getMenuClearHistory() {
        return menuClearHistory;
    }

    public MenuItem getMenuHighScoreList() {
        return menuHighScoreList;
    }
}
