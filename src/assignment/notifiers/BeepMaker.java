package assignment.notifiers;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by nobel on 21/03/17.
 */
public class BeepMaker {

    public void playBeep() {
        Media sound = new Media(new File("resources/beep.wav").toURI().toString());
        MediaPlayer soundPlayer = new MediaPlayer(sound);
        soundPlayer.play();
    }

}
