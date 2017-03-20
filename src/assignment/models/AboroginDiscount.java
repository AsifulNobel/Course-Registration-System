package assignment.models;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class AboroginDiscount implements IDiscountStrategy, Observer {

    private Registration registration;
    private int total;

    public AboroginDiscount(Registration registration) {
        this.registration = registration;
        total = registration.getTotalWithoutDiscount();
    }

    @Override
    public void update(Observable o, Object arg) {
        total = registration.getTotalWithoutDiscount();
    }


    @Override
    public int getTotal(Registration reg) {
        return (int) (total - total * 0.60);
    }

}
