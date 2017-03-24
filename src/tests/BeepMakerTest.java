package tests;

import org.junit.Assert;
import org.junit.Test;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by shawon on 3/25/17.
 */
public class BeepMakerTest {

    @Test
    public void getAudioFileTest() {
        Path filePath = Paths.get("resources", "beep.wav");
        boolean fileFound = filePath != null;

        String fileName = filePath.toString();

        Assert.assertEquals(true, fileFound);
        Assert.assertEquals("resources/beep.wav", fileName);
    }

    @Test
    public void playAudioFileTest() {
        Path filePath = Paths.get("resources", "beep.wav");
        try(InputStream audioFileInputStream =
                    new FileInputStream(filePath.toString())) {

            AudioStream audioStream = new AudioStream(audioFileInputStream);
            AudioPlayer.player.start(audioStream);

            Assert.assertEquals(true, audioStream != null);

        } catch(Exception e) {}
    }

}
