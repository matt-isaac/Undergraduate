package controller;

import components.ScoreProfile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.*;

public class ControlsBarController {
    @FXML
    private Text currentScore;

    @FXML
    private Text highScore;

    private double averageScore;

    @FXML
    private Button startGameButton;

    private String dataFileName = "history/data";

    private String highscoresFileName = "history/highscores";

    private ArrayList<Integer> topTen = new ArrayList<>();

    private String highScoreScore;

    private String tmp;

    private String highScoreName;

    private Alert showHistoryDialog = new Alert(Alert.AlertType.INFORMATION);

    private Alert highScoreDialog = new Alert(Alert.AlertType.INFORMATION);



    //private ArrayList<String> highScoreNameList = new ArrayList<String>();

    //private ArrayList<Integer> highScoreScoreList = new ArrayList<Integer>();

    private ArrayList<ScoreProfile> highScoreList = new ArrayList<>();

    private int totalGames;

    public String getCurrentScore(){
        return currentScore.getText();
    }

    public String getHighScore(){
        return highScore.getText();
    }

    public double getAverageScore(){
        return averageScore;
    }

    public void setCurrentScore(int score){
        currentScore.setText(Integer.toString(score));
    }

    public void setHighScore(int score){
        highScore.setText(Integer.toString(score));
    }

    public void setAverageScore(int score){
        averageScore = score;
    }

    public void setStartButtonAction(EventHandler<ActionEvent> handler){
        startGameButton.setOnAction(handler);
    }

    public void initControlsBar(){
        startGameButton.setOnAction(event -> {
            System.out.println("Start Game!");

        });
        readHistory();

    }

    private void readHistory(){
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(dataFileName));
        } catch(FileNotFoundException error) {
            error.printStackTrace();
        }

        String hs;
        String as;
        String score;
        List<String> scores = new ArrayList<String>();

        while (fileInput.hasNext()){
            score = fileInput.nextLine();
            scores.add(score);
        }

        hs = scores.get(0);
        as = scores.get(1);
        totalGames = Integer.parseInt(scores.get(2));
        highScore.setText(hs);
        averageScore = Double.parseDouble(as);

        try{
            fileInput = new Scanner(new FileReader(highscoresFileName));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        while(fileInput.hasNext()){
            tmp = new String(fileInput.nextLine());
            String[] nameScore = tmp.split(" ");

            ScoreProfile tmpScoreProfile = new ScoreProfile(nameScore[0], Integer.parseInt(nameScore[1]));
            highScoreList.add(tmpScoreProfile);
            topTen.add(Integer.parseInt(nameScore[1]));
        }
        System.out.println(topTen);

    }

    public void writeHistory(){

//        ArrayList<String> finalHighScoreNames = new ArrayList<>();
//        ArrayList<Integer> finalHighScoreScores = new ArrayList<>();

//        int maxScore;
//        int maxIndex;

        ArrayList<ScoreProfile> finalHighScores = new ArrayList<>();

        Collections.sort(highScoreList, Comparator.comparing(s -> s.getScore(), Comparator.reverseOrder()));

        for(ScoreProfile scoreprof:highScoreList){
            finalHighScores.add(scoreprof);
            if(finalHighScores.size() == 10){
                break;
            }
        }



        PrintWriter fileOutput = null;

        try{
            fileOutput = new PrintWriter(new FileOutputStream(dataFileName, false));

        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }

        //highScore.setText(Collections.max(finalHighScoreScores).toString());
        fileOutput.printf("%s\n%f\n%d", highScore.getText(), averageScore, totalGames);
        fileOutput.close();

        try{
            fileOutput = new PrintWriter(new FileOutputStream(highscoresFileName, false));
        }catch(FileNotFoundException error){
            error.printStackTrace();
        }

        for(ScoreProfile scoreprof : finalHighScores){
            fileOutput.printf("%s %d\n", scoreprof.getName(), scoreprof.getScore());
        }
        fileOutput.close();
    }


    public Button getStartGameButton(){
        return startGameButton;
    }

//    public String getHighScoreName(){
//        return highScoreName;
//    }

//    public ArrayList<String> getHighScoreNameList(){
//        return highScoreNameList;
//    }

//    public ArrayList<Integer> getHighScoreScoreList(){
//        return highScoreScoreList;
//    }

    public ArrayList<ScoreProfile> getHighScoreList(){
        return highScoreList;
    }

    public void setTotalGames(int nGames){
        totalGames = nGames;
    }

    public int getTotalGames(){
        return totalGames;
    }

    public ArrayList<Integer> getTopTen(){
        return topTen;
    }

    public void addTopTen(int score){
        topTen.add(score);
        if(topTen.size() > 10){
            topTen.remove(Collections.min(topTen));
        }
    }

    public void calcAverageScore(int newScore){
        averageScore = (((double) averageScore * ((double) totalGames - 1)) + (double) newScore) /  (double) totalGames;
    }

    public void showHistory(){
        showHistoryDialog.setTitle("History");
        showHistoryDialog.setHeaderText("");
        showHistoryDialog.setContentText("Number of games played: " + totalGames + "\n" +
                "Average Score: " + averageScore);
        showHistoryDialog.showAndWait();
    }

    public void clearHistory()
    {
        currentScore.setText("0");
        averageScore = 0;
        totalGames = 0;
    }

    public void showHighScores(){
        highScoreDialog.setTitle("High Scores");
        highScoreDialog.setHeaderText("");
        highScoreDialog.setContentText("");
        for(ScoreProfile scoreprof : highScoreList){
            highScoreDialog.setContentText(highScoreDialog.getContentText() + "\n" + scoreprof.getName() + ":  " + scoreprof.getScore());
        }
        highScoreDialog.showAndWait();
    }

}
