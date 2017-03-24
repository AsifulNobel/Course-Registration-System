package tests;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
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

    }

}
