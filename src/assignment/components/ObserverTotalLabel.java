package assignment.components;

import assignment.models.Registration;
import javafx.scene.control.Label;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by nobel on 14/04/17.
 */
public class ObserverTotalLabel extends Label implements Observer {
    private Observable o;

    public void setObservable (Observable o) {
        this.o = o;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.o == o) {
            this.setText(Integer.toString(((Registration) arg).getDiscountedGrandTotal()));
        }
    }
}
