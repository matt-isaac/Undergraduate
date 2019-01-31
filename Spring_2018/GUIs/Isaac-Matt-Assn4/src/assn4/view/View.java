package assn4.view;

import assn4.model.Model;
import assn4.control.Control;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.util.Optional;

/**
 * View class. GUI components of Image Viewer program
 */
public class View extends Application {

    /**
     * Defines what happens when a button is pressed
     */
    private EventHandler<ActionEvent> handler;
    /**
     * Button to add an image
     */
    private Button add;
    /**
     * Button to delete an image
     */
    private Button del;
    /**
     * Button to advance to next image
     */
    private Button next;
    /**
     * Button to go to previous image
     */
    private Button prev;
    /**
     * Button to save and quit program
     */
    private Button quit;

    private Pane pane1;
    private ImageView view1;
    private Text text;
    private Control control;
    private Image noImage = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png");


    /**
     * Main start Method
     * @param primaryStage primaryStage for GUI
     */
    @Override
    public void start(Stage primaryStage) {
        initHandler();

        control = new Control();

        Scene scene1;
        add = new Button("Add");
        add.setLayoutX(300);
        add.setLayoutY(400);
        add.setPrefWidth(75);
        add.setOnAction(handler);

        del = new Button("Delete");
        del.setLayoutX(400);
        del.setLayoutY(400);
        del.setPrefWidth(75);
        del.setOnAction(handler);

        next = new Button("Next");
        next.setLayoutX(600);
        next.setLayoutY(400);
        next.setPrefWidth(75);
        next.setOnAction(handler);

        prev = new Button("Previous");
        prev.setLayoutX(100);
        prev.setLayoutY(400);
        prev.setPrefWidth(75);
        prev.setOnAction(handler);

        quit = new Button("Save and Quit");
        quit.setLayoutX(350);
        quit.setLayoutY(450);
        quit.setPrefWidth(100);
        quit.setOnAction(handler);



        text = new Text();
        pane1 = new Pane();



        primaryStage.setResizable(false);

        view1 = new ImageView();
        view1.setFitWidth(500);
        view1.setFitHeight(350);
        view1.setPreserveRatio(true);
        view1.setLayoutX(150);
        view1.setLayoutY(25);
        Image currentImage = new Image(control.getModel().getImageList().get(control.getModel().getCurrentImage()).getUrl());
        //Image currentImage = new Image("http://foundtheworld.com/wp-content/uploads/2014/11/Yosemite-National-Park-11.jpg");
        view1.setImage(currentImage);
        text.setText(control.getModel().getImageList().get(control.getModel().getCurrentImage()).getTitle());
        text.setLayoutY(20);
        text.setLayoutX(350);
        text.setFont(Font.font(20));

        //pane1.getChildren().add(add);
        //pane1.getChildren().add(del);
        //pane1.getChildren().add(next);
        //pane1.getChildren().add(prev);

        pane1.getChildren().addAll(view1, text, add, del, prev, next, quit);
        scene1 = new Scene(pane1, 800, 500);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    /**
     * Logic for when buttons are pressed
     */
    private void initHandler() {
        handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() == add) {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("New Image Entry");
                    dialog.setContentText("URL: ");
                    String inputUrl = getTextInput(dialog);
                    dialog.setContentText("Title: ");
                    String inputTitle = getTextInput(dialog);
                    Model.ImageObject newImg = control.addImage(inputTitle, inputUrl);
                    Image tmpImg = new Image(inputUrl);
                    newImg.setImg(tmpImg);
                    view1.setImage(newImg.getImg());
                    text.setText((newImg.getTitle()));
                    next.setDisable(false);
                    prev.setDisable(false);
                    del.setDisable(false);

                } else if (event.getSource() == del) {
                    Model.ImageObject nextImg = control.delImage();
                    if(control.getModel().getImageList().isEmpty()){
                        next.setDisable(true);
                        prev.setDisable(true);
                        del.setDisable(true);
                    }
                    view1.setImage(nextImg.getImg());
                    text.setText(nextImg.getTitle());

                } else if (event.getSource() == next) {
                    Model.ImageObject curImgObj = control.nextImage(control.getModel());
                    if(curImgObj.getImg() == null){
                        Image tmpImg = new Image(curImgObj.getUrl());
                        curImgObj.setImg(tmpImg);
                    }

                    view1.setImage(curImgObj.getImg());
                    text.setText(curImgObj.getTitle());

                } else if (event.getSource() == prev) {
                    Model.ImageObject curImgObj = control.prevImage(control.getModel());
                    if(curImgObj.getImg() == null){
                        Image tmpImg = new Image(curImgObj.getUrl());
                        curImgObj.setImg(tmpImg);
                    }
                    view1.setImage(curImgObj.getImg());
                    text.setText(curImgObj.getTitle());

                } else if (event.getSource() == quit) {
                    control.quit();
                }
            }
        };
    }

    /**
     * Class to retrieve text from text input dialog box
     * @param dialog dialog object
     * @return Input from user
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
}
