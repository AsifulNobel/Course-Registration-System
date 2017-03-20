package assignment.models;

import javafx.scene.media.AudioClip;

import java.io.File;
import java.net.URL;
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
            URL filePath = getClass().getResource("resources/beep.wav");
            AudioClip sound;

            try {
                if (filePath != null)
                    System.out.println(filePath.toExternalForm());
                else {
                    System.out.println((new File("")).getAbsolutePath());
                }

                sound = new AudioClip(filePath.toExternalForm());
                sound.play();
            } catch (Exception ex) {
//                ex.printStackTrace();
            }
        }
    }
}
