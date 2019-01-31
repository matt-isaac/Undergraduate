package view;

import components.Cell;
import components.OrderedPair;
import components.ScoreBoard;
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;


public class Main extends Application {

    private ScoreBoard scoreBoard = new ScoreBoard();
    private MainController mainController = new MainController();
    private Cell newCell;
    private GridPane gridPane = new GridPane();
    private OrderedPair tempOrderedPair;
    private Random rand = new Random();
    private int nMines = 0;
    private Timer timer;
    private ArrayList<Cell> cells = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/main.fxml"));
        Parent mainPane = loader.load();
        mainController = (MainController)loader.getController();


        primaryStage.setScene(new Scene(mainPane, 720, 632));

//        ArrayList<Integer> mineRow = new ArrayList<Integer>();
//        ArrayList<Integer> mineCol = new ArrayList<Integer>();
//
////        for(int i = 0; i < 20; i++){
////            mineRow.add(i);
////            mineCol.add(i);
////        }
//
//        Collections.shuffle(mineRow);
//        Collections.shuffle(mineCol);

        ArrayList<Integer> mineLocations = new ArrayList<>();
        ArrayList<Integer> possibleLocations = new ArrayList<>();

//        boolean duplicate = true;

//        while(mineLocations.size() < 10) {
//            duplicate = true;
//            int x = rand.nextInt(20);
//            int y = rand.nextInt(20);
//
//            tempOrderedPair = new OrderedPair(x, y);
//            if(mineLocations.size() == 0){
//                mineLocations.add(tempOrderedPair);
//            }
//            for(OrderedPair op : mineLocations){
//                if (!op.equals(tempOrderedPair)){
//                    //mineLocations.add(tempOrderedPair);
//                    duplicate = false;
//                }
//            }
//            if(duplicate == false){
//                mineLocations.add(tempOrderedPair);
//                System.out.println("Added: " + x + "," + y);
//        }
//    }

        for(int i = 1; i < 400; i++){
            possibleLocations.add(i);
        }
        Collections.shuffle(possibleLocations);

        for(int i = 0; i < 100; i++){
            mineLocations.add(possibleLocations.get(i));
        }


        int cellNumber = 0;

        for(int r = 0; r < 20; r++){
            for(int c = 0; c < 20; c++){

                newCell = new Cell(r, c);
                if(mineLocations.contains(cellNumber)){
                    newCell.setMine(true);
                    nMines = nMines + 1;
                }

//                for (OrderedPair op : mineLocations) {
//                    if (op.getX() == newCell.getRowIndex() && op.getY() == newCell.getColumnIndex()) {
//                        newCell.setMine(true);
//                        System.out.println("Mine at: " + r + ", " + c);
//                    }
//                }
                gridPane.add(newCell.getButton(), newCell.getColumnIndex(), newCell.getRowIndex());
                cellNumber = cellNumber + 1;

            }
        }

        mainController.getPane().getChildren().add(gridPane);
        mainController.getBorderPane().setTop(scoreBoard.gethBox());
        scoreBoard.setStartButtonAction(event -> {
            startGame();
        });

        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setTitle("MineSweeper");
        primaryStage.setOnCloseRequest(event -> {
            System.exit(1);
        });
    }

    public void startGame(){


    }


    public static void main(String[] args) {
        launch(args);
    }
}
