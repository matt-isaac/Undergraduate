/*
package cs2410.demo;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Optional;

public class Demo extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        /* NONE - no buttons
        Alert dialog = new Alert(Alert.AlertType.NONE);
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide()); // when someone clicks the X, close the window.
        */

        // Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        // Alert dialog = new Alert(Alert.AlertType.ERROR);

        /*
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle("This is the Title");
        //dialog.setHeaderText("This is the Header Text");
        dialog.setHeaderText(null);
        dialog.setContentText("This is the Content Text");


        Optional<ButtonType> res = dialog.showAndWait();
        if(res.get() == ButtonType.CANCEL){
            System.out.println("You hit cancel.");
        } else if (res.get() == ButtonType.OK) {
            System.out.println("You hit OK.");
        }
        */

/*
        ArrayList<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(null, list);
        dialog.setHeaderText(null);
        dialog.setTitle("Chooser");
        dialog.setContentText("Make a good choice");

        Optional<String> res = dialog.showAndWait();

        if(res.isPresent()){ // if True, user hit OK. if False, user hit cancel.
            System.out.println("You selected: " + res.get());
        } else {
            System.out.println("You cancelled");
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setTitle("Text Input");
        dialog.setContentText("Enter your name");

        Optional<String> res = dialog.showAndWait();

        if(res.isPresent()) {
            System.out.println("You entered: " + res.get());
        } else {
            System.out.println("You canceled.");
        }


    }

}
*/
package cs2410.demo;

import javafx.application.Application;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Add a description of the class here
 *
 * @author Chad
 * @version XXX
 */
public class Demo extends Application {

    public void start(Stage primaryStage) throws Exception {
        TextInputDialog alert = new TextInputDialog();
        alert.setTitle("Clear Dialog Demo");
        alert.setHeaderText(null);
        alert.setContentText("Leave empty to stop loop");

        boolean done = false;

        while(!done) {
            Optional<String> result = alert.showAndWait();
            if (result.get().isEmpty()) {
                done = true;
            }
        }

        alert.setContentText("Now do it again. (empty to exit)");
        done = false;

        while(!done) {
            alert.getEditor().clear();
            Optional<String> result = alert.showAndWait();
            if (result.get().isEmpty()) {
                done = true;
            }
        }
    }
}