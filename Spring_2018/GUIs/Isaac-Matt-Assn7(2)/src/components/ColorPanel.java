package components;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.Timer;
import java.util.TimerTask;

public class ColorPanel {

    private Rectangle panel = new Rectangle();
    private Color color;
    private int index;
    private Timer timer;
    private MidiChannel midi;
    private int midiNote;

    public ColorPanel(Color col, int i, int note){
        panel.setWidth(200);
        panel.setHeight(200);
        panel.setFill(col);
        color = col;
        index = i;
        midiNote = note;
        try {
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            midi = synth.getChannels()[0];
        }catch(MidiUnavailableException e){
            e.printStackTrace();
        }
        initHandler(panel);
    }

    public Rectangle getPanel() {
        return panel;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color col){
        panel.setFill(col);
    }

    public int getIndex(){
        return index;
    }

    public void initHandler(Rectangle rect) {
        rect.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /*Color currentColor = (Color)rect.getFill();
                rect.setFill(((Color) rect.getFill()).brighter().brighter());*/
                rect.setFill(((Color) rect.getFill()).brighter().brighter());
                midi.noteOn(midiNote, 150);
            }
        });

        rect.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rect.setFill(((Color) rect.getFill()).darker().darker());
                midi.allNotesOff();
            }
        });
    }

//    public void disablePanel(Rectangle rect) {
//        rect.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//
//            }
//        });
//    }

    public void lightUp(){
        panel.setFill(((Color) panel.getFill()).brighter().brighter());
        midi.noteOn(midiNote, 150);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                panel.setFill(((Color) panel.getFill()).darker().darker());
                midi.allNotesOff();
                timer.cancel();
            }
        }, 400, 1000);
//
    }


}
