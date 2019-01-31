package cs2410.videoplayer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.File;

public class VideoPlayer extends Application {
    private BorderPane borderPane = new BorderPane();
    private Slider volumeSlider = new Slider();
    private Slider timeSlider = new Slider();
    private Button playPauseButton = new Button();
    private Button stopButton = new Button();
    private Text volLabel = new Text("Volume: ");
    private Text timeLable = new Text("Time: ");
    private MediaPlayer player;
    private Media media;
    private MediaView mediaView = new MediaView();
    //private String videoFileName = "data/jellyfish-25-mbps-hd-hevc.mp4";
    private Menu fileMenu = new Menu("File");
    private Menu helpMenu = new Menu("Help");
    private MenuItem openMenuItem = new MenuItem("Open");
    private MenuItem closeMenuItem = new MenuItem("Close");
    private MenuItem exitMenuItem = new MenuItem("Exit");
    private MenuItem docMenuItem = new MenuItem("Documentation");
    private MenuItem aboutMenuItem = new MenuItem("About");
    private FileChooser fileChooser = new FileChooser();
    private MenuBar menuBar = new MenuBar();
    private File chosenFile;
    private Duration playerLength;
    private Text playerTime;
    //private Image playIcon = new Image("https://cdn3.iconfinder.com/data/icons/pyconic-icons-2-1/512/play-circle-512.png");
    private Image playIcon = new Image("file:data/play-circle-512.png");
    //private Image pauseIcon = new Image ("https://cdn3.iconfinder.com/data/icons/pyconic-icons-2-1/512/pause-circle-512.png");
    private Image pauseIcon = new Image ("file:data/pause-circle-512.png");
    //private Image stopIcon = new Image("https://cdn3.iconfinder.com/data/icons/pyconic-icons-2-1/512/stop-circle-512.png");
    private Image stopIcon = new Image("file:data/stop-circle-512.png");
    private ImageView playPauseImageView = new ImageView();
    private ImageView stopImageView = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox hBox = new HBox();
        hBox.getChildren().addAll(playPauseButton, stopButton, volLabel, volumeSlider, timeLable, timeSlider);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setBottom(hBox);

        //playPauseButton.setText("Play");
        playPauseButton.setPrefWidth(40);
        //stopButton.setText("Stop");
        stopButton.setPrefWidth(40);
        volumeSlider.setPrefWidth(150);
        timeSlider.setPrefWidth(150);
        volumeSlider.setValue(volumeSlider.getMax()/2.0);

        mediaView.setPreserveRatio(true);
        mediaView.setFitWidth(600);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(mediaView);
        stackPane.setPrefSize(650, 425);

        //ImageView openMenuGraphic = new ImageView("http://ico.ooopic.com/ajax/iconpng/?id=319346.png");
        ImageView openMenuGraphic = new ImageView("file:data/open.png");
        openMenuGraphic.setPreserveRatio(true);
        openMenuGraphic.setFitWidth(20);

        //ImageView closeMenuGraphic = new ImageView("http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-4/256/Open-Folder-Delete-icon.png");
        ImageView closeMenuGraphic = new ImageView("file:data/close.png");
        closeMenuGraphic.setPreserveRatio(true);
        closeMenuGraphic.setFitWidth(20);

        //ImageView exitMenuGraphic = new ImageView("https://cdn1.iconfinder.com/data/icons/toolbar-signs/512/cancel-512.png");
        ImageView exitMenuGraphic = new ImageView("file:data/exit.png");
        exitMenuGraphic.setPreserveRatio(true);
        exitMenuGraphic.setFitWidth(20);

        //ImageView docMenuGraphic = new ImageView("https://cdn0.iconfinder.com/data/icons/simple-seo-and-internet-icons/512/document-512.png");
        ImageView docMenuGraphic = new ImageView("file:data/doc.png");
        docMenuGraphic.setPreserveRatio(true);
        docMenuGraphic.setFitWidth(20);

        //ImageView aboutMenuGraphic = new ImageView("https://icon-icons.com/icons2/37/PNG/512/about_3697.png");
        ImageView aboutMenuGraphic = new ImageView("file:data/about.png");
        aboutMenuGraphic.setPreserveRatio(true);
        aboutMenuGraphic.setFitWidth(20);

        playPauseImageView.setImage(playIcon);
        playPauseImageView.setPreserveRatio(true);
        playPauseImageView.setFitWidth(40);

        stopImageView.setImage(stopIcon);
        stopImageView.setPreserveRatio(true);
        stopImageView.setFitWidth(40);

        playPauseButton.setGraphic(playPauseImageView);
        stopButton.setGraphic(stopImageView);

        openMenuItem.setGraphic(openMenuGraphic);
        closeMenuItem.setGraphic(closeMenuGraphic);
        exitMenuItem.setGraphic(exitMenuGraphic);
        aboutMenuItem.setGraphic(aboutMenuGraphic);
        docMenuItem.setGraphic(docMenuGraphic);


        fileMenu.getItems().addAll(openMenuItem, closeMenuItem, exitMenuItem);
        helpMenu.getItems().addAll(docMenuItem, aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        borderPane.setCenter(stackPane);
        borderPane.setTop(menuBar);

        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.m4v", "*.mp4", "*.m4a"));
        openMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chosenFile = fileChooser.showOpenDialog(primaryStage);
                if(chosenFile != null){
                    media = new Media(chosenFile.toURI().toString());
                    player = new MediaPlayer(media);
                    mediaView.setMediaPlayer(player);
                    player.setOnReady(new Runnable() {
                        @Override
                        public void run() {
                            playerLength = new Duration(media.getDuration().toMillis());
                            player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                                @Override
                                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                                    timeSlider.setValue(newValue.toMillis() / playerLength.toMillis() * timeSlider.getMax());
                                }
                            });

                            initHandlers();
                        }
                    });
                    player.volumeProperty().setValue(0.5);
                    timeSlider.setValue(0);
                }

            }
        });

        closeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (media != null) {
                    player.stop();
                    mediaView.setMediaPlayer(null);
                    playPauseButton.setOnAction(event1 -> {});
                    stopButton.setOnAction(event1 -> {});

                    //playPauseButton.setText("Play");
                    playPauseImageView.setImage(playIcon);
                }
            }
        });

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initHandlers() {
        exitMenuItem.setOnAction(this::exitMenuItemAction);
        playPauseButton.setOnAction(this::playPauseButtonAction);
        stopButton.setOnAction(this::stopButtonAction);

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(volumeSlider.isValueChanging()){
                    player.setVolume(volumeSlider.getValue() / 150);
                }
            }
        });

        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(timeSlider.isValueChanging()) {
                    player.seek(playerLength.multiply(timeSlider.getValue()/timeSlider.getMax()));
                }
            }
        });
    }

    private void exitMenuItemAction(ActionEvent e) {
        System.exit(1);
    }

    private void playPauseButtonAction(ActionEvent e) {
        if(playPauseImageView.getImage() == playIcon) {
            player.play();
            //playPauseButton.setText("Pause");
            playPauseImageView.setImage(pauseIcon);
        }
        else {
            player.pause();
            //playPauseButton.setText("Play");
            playPauseImageView.setImage(playIcon);
        }
    }

    private void stopButtonAction(ActionEvent e) {
        player.stop();
        //playPauseButton.setText("Play");
        playPauseImageView.setImage(playIcon);
    }

}
