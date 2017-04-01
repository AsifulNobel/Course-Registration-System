package assignment.observers;

import javafx.scene.media.AudioClip;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
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
        if(observableObject == o) {
//            observableObject = o;
            System.out.println("GrandTotal has been updated.");
            playBeep();
        }
    }

    private void playBeep() {
        String filePath = "resources/beep.wav";
        try {
            URL path = getClass().getResource(filePath);
            
            AudioClip a = new AudioClip(path.toString());
            a.play();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
