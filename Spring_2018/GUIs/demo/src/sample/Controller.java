package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    private Button btn1;

    @FXML
    private void btn2Press() {
        System.out.println("You pressed Button 2");
    }

    public void initialize() {
        btn1.setOnAction(event -> {System.out.println("You pressed Button 1");});
    }
}
