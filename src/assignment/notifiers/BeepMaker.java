package assignment.notifiers;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by nobel on 21/03/17.
 */
public class BeepMaker {
    public static void playBeep() {
        String filePath = "resources/beep.wav";
        try (InputStream in = new FileInputStream(filePath)) {
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(in);
            AudioPlayer.player.stop(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
