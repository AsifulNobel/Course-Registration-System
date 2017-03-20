package assignment.models;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class AcademicExcellenceDiscount implements IDiscountStrategy, Observer {

    private Registration registration;
    private int total;

    @Override
    public void update(Observable o, Object arg) {
        if(o == registration) {
        }
    }

    @Override
    public int getTotal(Registration reg) {
        total = reg.getTotal();
        return (int) (total - (total * 0.5));
    }


}
