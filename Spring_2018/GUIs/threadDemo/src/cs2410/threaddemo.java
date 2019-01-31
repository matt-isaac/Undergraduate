package cs2410;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class threaddemo extends Application{

/*    private Pane lightPane = new Pane();
    private Button button = new Button("Start");
    private Color[] colorArr = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE};
    private Rectangle rect = new Rectangle();

    public void start(Stage primaryStage) throws Exception {
        BorderPane bpane = new BorderPane();
        bpane.setTop(button);
        bpane.setCenter(rect);
        lightPane.setPrefSize(200, 200);

        button.setOnAction(event -> {blinkColor();});

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void blinkColor(){
        for(int i = 0; i < colorArr.length; i++){
            System.out.println("About to change");
            rect.setFill(colorArr[i%colorArr.length]);

            try{
                Thread.sleep(1000)
            } catch(Interrupted)
                ex.printStackTrace
        }
    }*/

    @Override
        public void Start(Stage primaryStage) throws Exception{
        Thread thread1 = new Thread(new MyRunnable("One"));

        thread1.start();

    }

    private class MyRunnable implements Runnable (String name) {

        @Override
        public void run() {
            System.out.println("New Thread");
        }
    }



}
