package assignment.models;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class FreedomFighterDiscount implements IDiscountStrategy, Observer {
    @Override
    public int getTotal(Registration reg) {
        int total = reg.getTotal();
        if (reg.getCourseList().size() <= 5) {
            total = total - 20000;
            return total;
        } else {
            return total;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
