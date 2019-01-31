package view;

import components.ColorPanel;
import components.ScoreProfile;
import controller.MainViewController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainView extends Application {

    public int cScore;
    public int hScore;

    private boolean cTurn;
    private int seqIndex;

    private long timeStamp = 0;
    private long tmp = 0;
    private long diff = 0;
    private MainViewController mainViewController;
    private ArrayList<ColorPanel> sequence = new ArrayList<ColorPanel>();
    private ArrayList<ColorPanel> userSequence = new ArrayList<ColorPanel>();
    private Timer timer = new Timer();
    private Random random = new Random();
    private boolean wrong;
    private Alert gameOverAlert = new Alert(Alert.AlertType.WARNING);
    private TextInputDialog nameInput = new TextInputDialog();
//    private Thread userTurnThread = new Thread(new userTurn());
//    private Thread computerTurnThread = new Thread(new computerTurn());



//    private AnimationTimer timer = new AnimationTimer() {
//        @Override
//        public void handle(long now) {
//            diff = now - tmp;
//
//            tmp = now;
//            System.out.println(now - tmp);
//            if (now - timeStamp > 1000000000) {
//                timeStamp = now;
//                if(cTurn == true){
//                    lightNext(seqIndex);
//                }
//            }
//        }
//    };


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/mainView.fxml"));
        Parent mainPane = loader.load();
        mainViewController = (MainViewController)loader.getController();

        primaryStage.setTitle("Simon");
        primaryStage.setScene(new Scene(mainPane, 1000, 600));

        mainViewController.getControlsBarController().setStartButtonAction(event -> {
            seqIndex = 0;
            cTurn = true;
            wrong = false;
            startTurn();

        });

        mainViewController.getMenuBarController().getMenuHistory().setOnAction(event -> {
            mainViewController.getControlsBarController().showHistory();
        });

        mainViewController.getMenuBarController().getMenuClearHistory().setOnAction(event -> {
            mainViewController.getControlsBarController().clearHistory();
        });

        mainViewController.getMenuBarController().getMenuHighScoreList().setOnAction(event -> {
            mainViewController.getControlsBarController().showHighScores();
        });

        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = (double) newValue;
                mainViewController.getGameBoardController().getPanel0().getPanel().setHeight(height/3);
                mainViewController.getGameBoardController().getPanel1().getPanel().setHeight(height/3);
                mainViewController.getGameBoardController().getPanel2().getPanel().setHeight(height/3);
                mainViewController.getGameBoardController().getPanel3().getPanel().setHeight(height/3);
                mainViewController.getGameBoardController().gethBox().setMaxHeight(height/3);
            }
        });

        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = (double) newValue;
                mainViewController.getGameBoardController().getPanel0().getPanel().setWidth(width/5);
                mainViewController.getGameBoardController().getPanel1().getPanel().setWidth(width/5);
                mainViewController.getGameBoardController().getPanel2().getPanel().setWidth(width/5);
                mainViewController.getGameBoardController().getPanel3().getPanel().setWidth(width/5);
                mainViewController.getGameBoardController().gethBox().setPrefWidth(width * 0.8);
            }
        });



        gameOverAlert.setTitle("Game Over!");
        gameOverAlert.setHeaderText("Press \'Start Game\' to begin a new game.");

        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            mainViewController.getControlsBarController().writeHistory();
            System.exit(1);

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void startTurn(){
        mainViewController.getControlsBarController().getStartGameButton().setDisable(true);
        mainViewController.getGameBoardController().resetPanels();
        if(cTurn){
            //mainViewController.getGameBoardController().resetPanels();
            //mainViewController.getGameBoardController().disableGameBoard();
            computerTurn();
        } else {
            //mainViewController.getGameBoardController().resetPanels();
            //mainViewController.getGameBoardController().enableGameBoard();
            userTurn();
        }
    }


    public void userTurn(){
//        sequence.add(mainViewController.getGameBoardController().getPanel0());
//        sequence.add(mainViewController.getGameBoardController().getPanel1());
//        sequence.add(mainViewController.getGameBoardController().getPanel2());
//        sequence.add(mainViewController.getGameBoardController().getPanel3());
        initGameBoardHandler(mainViewController.getGameBoardController().getPanel0());
        initGameBoardHandler(mainViewController.getGameBoardController().getPanel1());
        initGameBoardHandler(mainViewController.getGameBoardController().getPanel2());
        initGameBoardHandler(mainViewController.getGameBoardController().getPanel3());

        cTurn = true;
    }


    public void computerTurn(){
        int randVal = random.nextInt(4);
//        sequence.add(mainViewController.getGameBoardController().getPanel0());
//        sequence.add(mainViewController.getGameBoardController().getPanel1());
//        sequence.add(mainViewController.getGameBoardController().getPanel2());
//        sequence.add(mainViewController.getGameBoardController().getPanel3());
        if(randVal == 0){
            sequence.add(mainViewController.getGameBoardController().getPanel0());
        }
        else if(randVal == 1){
            sequence.add(mainViewController.getGameBoardController().getPanel1());
        }
        else if(randVal == 2){
            sequence.add(mainViewController.getGameBoardController().getPanel2());
        }
        else {
            sequence.add(mainViewController.getGameBoardController().getPanel3());
        }

        iterSequence();
        cTurn = false;

        startTurn();
    }

    private void iterSequence() {
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                if (seqIndex == sequence.size()) {
                    seqIndex = 0;
                    this.cancel();
                }
                else {
                    blinkNext();
                }

            }}, 500, 500);
    }

    private void blinkNext(){
        sequence.get(seqIndex).lightUp();
        seqIndex = seqIndex + 1;
    }

    private void initGameBoardHandler(ColorPanel panel){
        //mainViewController.getGameBoardController().gethBox().setOnMouseClicked(new EventHandler<MouseEvent>() {
        panel.getPanel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
                    if (event.getSource() != sequence.get(seqIndex).getPanel()){
                        int currentScore = Integer.parseInt(mainViewController.getControlsBarController().getCurrentScore());
                        if(sequence.size() < 10){
                            gameOverAlert.setContentText("Final Score: " + mainViewController.getControlsBarController().getCurrentScore() + "\nRank: Rookie");
                        }
                        else if(sequence.size() < 15){
                            gameOverAlert.setContentText("Final Score: " + mainViewController.getControlsBarController().getCurrentScore() + "\nRank: Minor League");
                        } else {
                            gameOverAlert.setContentText("Final Score: " + mainViewController.getControlsBarController().getCurrentScore() + "\nRank: Major League");
                        }
                        if(currentScore >= Collections.min(mainViewController.getControlsBarController().getTopTen())){

                            if(currentScore > Collections.max(mainViewController.getControlsBarController().getTopTen())){
                                mainViewController.getControlsBarController().setHighScore(currentScore);
                            }

                            ScoreProfile tmpScoreProfile;
                            nameInput.setTitle("Top Ten High Score!");
                            nameInput.setHeaderText("");
                            nameInput.setContentText("Please enter your name: ");
                            Optional<String> result = nameInput.showAndWait();
                            if(result.isPresent()){
                                tmpScoreProfile = new ScoreProfile(result.get(), currentScore);
                                mainViewController.getControlsBarController().addTopTen(currentScore);
                                mainViewController.getControlsBarController().getHighScoreList().add(tmpScoreProfile);
                            }


                        }
                        mainViewController.getControlsBarController().setTotalGames(mainViewController.getControlsBarController().getTotalGames() + 1);
                        mainViewController.getControlsBarController().calcAverageScore(currentScore);
                        gameOverAlert.showAndWait();
                        sequence.clear();
                        userSequence.clear();
                        seqIndex = 0;
                        wrong = true;
                        //mainViewController.getGameBoardController().resetPanels();
                        mainViewController.getControlsBarController().getStartGameButton().setDisable(false);
                        mainViewController.getControlsBarController().setCurrentScore(0);
                    } else {
                        userSequence.add(sequence.get(seqIndex));
                        seqIndex = seqIndex + 1;
                    }

                    if(userSequence.equals(sequence) && wrong == false){
                        cTurn = true;
                        mainViewController.getControlsBarController().setCurrentScore(sequence.size());
                        userSequence.clear();
                        seqIndex = 0;
                        startTurn();
                    }
                }
                    catch(IndexOutOfBoundsException error){
                        System.out.println("Sequence size: " + sequence.size());
                        System.out.println("Sequence index: " + seqIndex);
                    }
                }



        });
    }

   private boolean iscTurn(){
        return cTurn;
   }

}
