package assignment.observers;

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

        }
    }
}
