package cs2410.assn3.gui;

import cs2410.assn3.directory.Directory;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Command Directory class uses Directory class (and nested Entry class).
 * Provides user with a way to interact with the directory through a GUI.
 *
 * @author Matt Isaac
 * @version 1
*/
public class GUIDirectory extends Application{
    @Override

    /**
     * Main start method. Calls GUIDirectory constructor.
     * @param args Default arguments
     */
    public void start(Stage primaryStage) throws Exception {
        new GUIDirectory();
    }

    /**
     * This is where most of the "work" happens.
     * Creates instance of Directory.
     */
    public GUIDirectory(){

        Directory movieDirectory = new Directory();

        ArrayList<String> choices = new ArrayList();
        choices.add("List directory contents");
        choices.add("Add movie to directory");
        choices.add("Display average rating");
        choices.add("Quit program");

        ChoiceDialog<String> choiceDialog = new ChoiceDialog<>(null, choices);

        Boolean done = false;

        while(!done){
            Optional<String> res = choiceDialog.showAndWait();

            if (res.get().equals("List directory contents")) {
                listContents();

            } else if (res.get().equals("Add movie to directory")) {
                addMovie();

            } else if (res.get().equals("Display average rating")) {
                displayAverageRating();
            } else {
                System.exit(1);
            }
        }
    }

    /**
     * This method is called when option 2 is selected by the user.
     * A new instance of Entry is created and added to the Directory. User
     * is able to specify movie title, rating, review, etc.
     */
    public void addMovie() {
        String inputTitle;
        String inputYear;
        String inputRating;
        String inputStars;
        String inputDateYear;
        String inputDateMonth;
        String inputDateDay;

        Directory.Entry entry = new Directory.Entry();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setTitle("New Movie Entry");

        dialog.setContentText("Enter the title");
        inputTitle = getTextInput(dialog);

        dialog.setContentText("Enter the release year");
        inputYear = getTextInput(dialog);

        dialog.setContentText("Enter the rating");
        inputRating = getTextInput(dialog);

        dialog.setContentText("Enter the number of stars");
        inputStars = getTextInput(dialog);

        dialog.setContentText("Enter year of date last watched");
        inputDateYear = getTextInput(dialog);

        dialog.setContentText("Enter month of date last watched (1-12)");
        inputDateMonth = getTextInput(dialog);

        dialog.setContentText("Enter day of date last watched (1-31)");
        inputDateDay = getTextInput(dialog);

        entry.setTitle(inputTitle);
        entry.setYear(Integer.parseInt(inputYear));
        entry.setRating(inputRating);
        entry.setStars(Integer.parseInt(inputStars));
        entry.setDateYear(Integer.parseInt(inputDateYear));
        entry.setDateMonth(Integer.parseInt(inputDateMonth));
        entry.setDateDay(Integer.parseInt(inputDateDay));

        Directory.movieList.add(entry);
        entry.writeToFile();
        justAdded(entry);
    }


    /**
     * Message printed when user adds movie to directory.
     * @param e Movie Entry
     */
    private void justAdded(Directory.Entry e){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Just Added");
        dialog.setHeaderText(null);
        dialog.setContentText("The following movie has been added to the directory:" +
                "\n" +  e.getTitle() + " (" + e.getYear() + ") " + e.getRating() +
                "\nStars: " + e.getStars() +
                "\nDate last watched: " + e.getDateMonth() + "/" + e.getDateDay() + "/" + e.getYear());
        dialog.showAndWait();
    }

    /**
     * Method used in addMovie(). Gets text input from user
     * @param dialog
     * @return String entered by user
     */
    private String getTextInput(TextInputDialog dialog){
        dialog.setHeaderText(null);
        boolean done;
        done = false;
        String response = null;
        while(!done) {
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                response = result.get();
                dialog.getEditor().clear();
                done = true;
            }
        }
        return response;
    }

    /**
     * The GUI side of option 3. Calls Directory.averageRating to do the actual calculation.
     */
    public void displayAverageRating(){
        float average = Directory.averageRating();
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        dialog.setTitle("Average Movie Review");
        dialog.setContentText("The average review is " + average + " stars.");
        dialog.showAndWait();
    }

    /**
     * This method is used when user selects option 1. Displays directory contents in window.
     */
    public void listContents(){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Directory Contents");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getStylesheets().add("resources/custom.css");
        dialog.getDialogPane().setPrefSize(400, 200);
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(Directory.fileName));
        } catch(FileNotFoundException error) {
            error.printStackTrace();
        }

        StringBuilder list = new StringBuilder();
        while (fileInput.hasNext()){
            list = list.append(fileInput.nextLine()).append("\n");
        }
        String list_str = list.toString();
        dialog.setContentText(list_str);
        dialog.getDialogPane().setPrefSize(450, 200);
        dialog.showAndWait();
    }
}

