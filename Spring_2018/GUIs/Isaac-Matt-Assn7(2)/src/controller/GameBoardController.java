package controller;

import components.ColorPanel;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javax.sound.midi.MidiChannel;

public class GameBoardController {

    @FXML
    private HBox hBox;

    private ColorPanel panel0 = new ColorPanel(Color.DARKGREEN, 0, 54);
    private ColorPanel panel1 = new ColorPanel(Color.DARKRED, 1, 56);
    private ColorPanel panel2 = new ColorPanel(Color.DARKBLUE, 2, 58);
    private ColorPanel panel3 = new ColorPanel(Color.DARKGOLDENROD, 3, 61);

    public HBox gethBox(){
        return hBox;
    }

    public ColorPanel getPanel0() {
        return panel0;
    }

    public ColorPanel getPanel1() {
        return panel1;
    }

    public ColorPanel getPanel2() {
        return panel2;
    }

    public ColorPanel getPanel3() {
        return panel3;
    }

    public void initGameBoard(){
        hBox.getChildren().addAll(panel0.getPanel(), panel1.getPanel(), panel2.getPanel(), panel3.getPanel());
    }

    public void resetPanels(){
        panel0.getPanel().setFill(Color.DARKGREEN);
        panel1.getPanel().setFill(Color.DARKRED);
        panel2.getPanel().setFill(Color.DARKBLUE);
        panel3.getPanel().setFill(Color.DARKGOLDENROD);
    }

//    public void disableGameBoard(){
//        panel0.disablePanel(panel0.getPanel());
//        panel1.disablePanel(panel1.getPanel());
//        panel2.disablePanel(panel2.getPanel());
//        panel3.disablePanel(panel3.getPanel());
//    }

//    public void enableGameBoard(){
//        panel0.initHandler(panel0.getPanel());
//        panel1.initHandler(panel1.getPanel());
//        panel2.initHandler(panel2.getPanel());
//        panel3.initHandler(panel3.getPanel());
//    }



}
