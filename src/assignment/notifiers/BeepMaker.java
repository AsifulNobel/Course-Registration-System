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
public class BeepMaker implements Runnable {

    @Override
    public void run() {
        playBeep();
    }

    public synchronized void playBeep() {
    }

}
