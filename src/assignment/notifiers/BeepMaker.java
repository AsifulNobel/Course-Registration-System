package assignment.notifiers;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by nobel on 21/03/17.
 */
public class BeepMaker implements Runnable {

    @Override
    public void run() {
        playBeep();
    }

    private void playBeep() {
        String filePath = "resources/beep.wav";

    }

}
