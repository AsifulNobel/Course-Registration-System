package assignment.models;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class AboroginDiscount implements IDiscountStrategy, Observer {
    @Override
    public int getTotal(Registration reg) {
        int total = reg.getTotal();
        return (int) (total - total * 0.40);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
