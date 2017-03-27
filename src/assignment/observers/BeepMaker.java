package assignment.observers;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by nobel on 21/03/17.
 */
public class BeepMaker implements Observer {
    private Observable observableObject;

    public BeepMaker(Observable oob) {
        this.observableObject = oob;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(observableObject != o) {
            observableObject = o;
            System.out.println("GrandTotal  has been updated.");
//            playBeep();
        }
    }

    private static void playBeep() {
        String filePath = "resources/beep.wav";
        try(InputStream in = new FileInputStream(filePath)) {
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(in);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
