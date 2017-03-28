package assignment.notifiers;


import java.time.LocalDateTime;

/**
 * Created by nobel on 21/03/17.
 */
public class BeepMaker {
    public static void playBeep() {
        LocalDateTime c = LocalDateTime.now();
        System.out.println("Grand Total has been updated ... @ " + c);
    }

}
