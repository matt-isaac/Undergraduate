package view;

import components.Cell;
import components.ScoreBoard;
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


import java.util.*;

public class Main extends Application {

    private MainController mainController = new MainController();
    private GridPane gridPane = new GridPane();
    private Cell[][] cells = new Cell[20][20];
    private ScoreBoard scoreBoard = new ScoreBoard();
    private ArrayList<Integer> mineLocations;
    private boolean gameInProgress = false;
    private boolean gameOver = false;
    private final int beginningMines = 100;
    private int minesLeft;
    private int nFlagged = 0;
    private Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
    private Alert loserAlert = new Alert(Alert.AlertType.INFORMATION);
    private Boolean won = false;
    private Timer timer;
    //private boolean timerRunning;
    private int seconds = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/main.fxml"));
        Parent mainPane = loader.load();
        mainController = (MainController)loader.getController();

        timer = new Timer();
        primaryStage.setTitle("MineSweeper");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane, 700, 630));
        primaryStage.setOnCloseRequest(event -> {
            System.exit(1);

        });

        scoreBoard.setStartButtonAction(event -> {
                gameInProgress = true;
                setMines();
                beginGame();

        });

        scoreBoard.setResetButtonActioni(event -> {
            resetBoard();
        });


        for(int i = 0; i < 20; ++i) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(5);
            col.setHalignment(HPos.CENTER);
            gridPane.getColumnConstraints().add(col);
        }

        for(int i = 0; i < 20; ++i){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(5);
            row.setValignment(VPos.CENTER);
            gridPane.getRowConstraints().add(row);
        }

        for(int r = 0; r < 20; r++){
            for(int c = 0; c < 20; c++) {
                Cell newCell = new Cell(r,c);
                initCellEventHandler(newCell);
                gridPane.add(newCell, r, c);
                cells[r][c]= newCell;
            }
        }

        gridPane.setGridLinesVisible(true);
        mainController.getBorderPane().setCenter(gridPane);
        mainController.getBorderPane().setTop(scoreBoard.gethBox());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setMines(){
        mineLocations = new ArrayList<>();
        for(int i = 0; i < 400; i++){
            mineLocations.add(i);
        }
        Collections.shuffle(mineLocations);
        List<Integer> mines = mineLocations.subList(0, beginningMines);
        Collections.sort(mines);

        int cellNumber = 0;
        for(int r = 0; r < 20; r++){
            for(int c = 0; c < 20; c++){
                if(mines.contains(cellNumber)){
                    cells[r][c].setMine(true);
                    //System.out.println(cellNumber);
                }
                cellNumber = cellNumber + 1;
            }
        }
    }

    private void beginGame(){
        scoreBoard.getResetButton().setDisable(true);
        gameInProgress = true;
        minesLeft = beginningMines;
        //scoreBoard.setMineCountText(minesLeft);
        scoreBoard.getStartButton().setDisable(true);
        startClock();

        //if(gameOver == true){
        //    resetBoard();
        //}

    }

    public void gameOver() {

        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                if (cells[r][c].isMine()) {
                    cells[r][c].getImageView().setImage(cells[r][c].getMineImage());
                    if(cells[r][c].isFlagged()){
                        //System.out.println("Mine flagged");
                        cells[r][c].setStyle("-fx-background-color: green");
                    }else{
                        cells[r][c].setStyle("-fx-background-color: red");
                    }
                }else if(!cells[r][c].isMine()){
                    if(cells[r][c].isFlagged()){
                        cells[r][c].setStyle("-fx-background-color: yellow");
                    }
                }
            }
        }
        gameInProgress = false;
        gameOver = true;
        //timer.cancel();
        //timerRunning = false;
        stopResetClock();
        if(won){
            showWinnerDialog();
        }
        else{
            showLoserDialog();
        }
        scoreBoard.getResetButton().setDisable(false);

    }

    public void initCellEventHandler(Cell cell) {

        cell.setOnMouseEntered(event -> {
            if(!gameOver){
                if (cell.isRevealed() == false) {
                    cell.setStyle("-fx-background-color: aqua");
                }
            }
        });

        cell.setOnMouseExited(event -> {
            if(!gameOver) {
                if (cell.isRevealed() == false) {
                    cell.setStyle("-fx-background-color: deepskyblue");
                }
            }
        });

        cell.setOnMouseClicked(event -> {
            //System.out.println("Clicked: " + cell.getX() + "," + cell.getY());
            if(gameInProgress) {
                if (cell.isRevealed() == false) {
                    if (event.getButton() == MouseButton.SECONDARY) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            if (cell.getImageView().getImage() == cell.getQuestionImage()) {
                                cell.getImageView().setImage(null);
                                minesLeft = minesLeft + 1;
                                scoreBoard.setMineCountText(minesLeft);
                                cell.setFlagged(false);
                                cell.setQuestionMark(false);
                            } else if (cell.getImageView().getImage() == cell.getFlagImage()) {
                                cell.getImageView().setImage(cell.getQuestionImage());
                                nFlagged = nFlagged - 1;
                                cell.setFlagged(false);
                                cell.setQuestionMark(false);
                            } else if (cell.getImageView().getImage() == null) {
                                cell.getImageView().setImage(cell.getFlagImage());
                                nFlagged = nFlagged + 1;
                                minesLeft = minesLeft - 1;
                                scoreBoard.setMineCountText(minesLeft);
                                cell.setQuestionMark(false);
                                cell.setFlagged(true);
                                if(hasWon()){
                                    won = true;
                                    gameOver();
                                }
                            }
                        }
                    } else {
                        if (cell.isFlagged() == false && cell.isQuestionMark() == false) {
                            if(hasWon()){
                                won = true;
                                gameOver();
                            }
                            if (cell.isMine() == true) {
                                cell.getImageView().setImage(cell.getMineImage());
                                gameOver();
                                gameOver = true;
                            }else{
                                cell.setStyle("-fx-background-color: cornsilk");
                                //try {
                                    lookAround(cell.getX(), cell.getY());
                                //}catch(ArrayIndexOutOfBoundsException e){

                                //}
                            cell.setRevealed(true);
                            }

                        }
                    }

                }
            }
        });
    }

    public void lookAround(int r, int c){
        //try{
            int nMineNeighbors = numberMineNeighbors(r,c);
            if(nMineNeighbors == 0 && cells[r][c].isRevealed() == false){
                cells[r][c].setRevealed(true);
                cells[r][c].setStyle("-fx-background-color: cornsilk");
                try{
                    lookAround(r+1, c);
                } catch(ArrayIndexOutOfBoundsException e){
                }
                try{
                    lookAround(r-1, c);
                }catch(ArrayIndexOutOfBoundsException e){
                }
                try{
                    lookAround(r+1, c+1);
                }catch(ArrayIndexOutOfBoundsException e){
                }
                try{
                    lookAround(r+1, c-1);
                }catch(ArrayIndexOutOfBoundsException e){
                }
                try{
                    lookAround(r, c-1);
                }catch(ArrayIndexOutOfBoundsException e){
                }
                try{
                    lookAround(r, c+1);
                }catch(ArrayIndexOutOfBoundsException e){
                }
                try{
                    lookAround(r-1, c-1);
                }catch(ArrayIndexOutOfBoundsException e){
                }
                try{
                    lookAround(r-1, c+1);
                }catch(ArrayIndexOutOfBoundsException e){
                }
            } else {
                if(!cells[r][c].isMine() && !cells[r][c].isRevealed()){
                    cells[r][c].setRevealed(true);
                    cells[r][c].setStyle("-fx-background-color: cornsilk");
                    cells[r][c].setnSurroundingMines(nMineNeighbors);
                }
                return;
            }

    }

    public int numberMineNeighbors(int r, int c){
        int nMines = 0;
        try {
            if (cells[r + 1][c + 1].isMine()) {
                nMines = nMines + 1;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        try {
            if (cells[r + 1][c].isMine()) {
                nMines = nMines + 1;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        try {
            if (cells[r + 1][c - 1].isMine()) {
                nMines = nMines + 1;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        try {
            if (cells[r][c + 1].isMine()) {
                nMines = nMines + 1;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }


        try {
            if (cells[r][c - 1].isMine()) {
                nMines = nMines + 1;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        try {
            if (cells[r - 1][c + 1].isMine()) {
                nMines = nMines + 1;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        try {
            if (cells[r - 1][c].isMine()) {
                nMines = nMines + 1;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        try {
            if (cells[r - 1][c - 1].isMine()) {
                nMines = nMines + 1;
            }
        }catch(ArrayIndexOutOfBoundsException e){

        }

        return nMines;
    }


    public void resetBoard(){
        scoreBoard.getStartButton().setDisable(false);
        for(int r = 0; r < 20; r++){
            for(int c = 0; c < 20; c++){
                cells[r][c].setStyle("-fx-background-color: deepskyblue");
                cells[r][c].setFlagged(false);
                cells[r][c].setMine(false);
                cells[r][c].setRevealed(false);
                cells[r][c].getImageView().setImage(null);
                cells[r][c].setnSurroundingMinesText("");
            }
        }
        gameOver = false;
        gameInProgress = false;
        minesLeft = beginningMines;
        nFlagged = 0;
        won = false;
        seconds = 0;
        scoreBoard.setTimeText(0);
        scoreBoard.setMineCountText(beginningMines);
        //setMines();
        //beginGame();
    }

    public boolean hasWon() {
        if (nFlagged != beginningMines) {
            return false;
        } else {
            for (int r = 0; r < 20; r++) {
                for (int c = 0; c < 20; c++) {
                    if (cells[r][c].isMine()) {
                        if (!cells[r][c].isFlagged()) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    public void showWinnerDialog(){
        winnerAlert.setTitle("");
        winnerAlert.setHeaderText("Minefield Successfully Cleared");
        winnerAlert.setContentText("Time to completion: " + scoreBoard.getTimeText() + " seconds");
        winnerAlert.showAndWait();
    }

    public void showLoserDialog(){
        loserAlert.setTitle("");
        loserAlert.setHeaderText("Minefield not Successfully Cleared");
        loserAlert.showAndWait();
    }

    public void startClock(){
        timer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                    seconds = seconds + 1;
                    scoreBoard.setTimeText(seconds);

            }}, 0, 1000);
    }

    public void stopResetClock(){
        timer.cancel();
        timer.purge();
        seconds = 0;
        timer = new Timer();
    }

//    public void beginTimer(){
//        if(timerRunning){
//            timer.cancel();
//            timerRunning = false;
//        }
//        timer = new Timer();
//        timerRunning = true;
//        timer.scheduleAtFixedRate(new TimerTask(){
//            @Override
//            public void run() {
//                    seconds = seconds + 1;
//                    scoreBoard.setTimeText(seconds);
//
//            }}, 0, 1000);
//    }


}
