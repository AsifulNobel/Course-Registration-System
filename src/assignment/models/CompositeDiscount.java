package assignment.models;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class CompositeDiscount implements IDiscountStrategy {

    private LinkedList<IDiscountStrategy> strategies;
    private static CompositeDiscount instance;

    public CompositeDiscount() {
        strategies = new LinkedList<>();
    }

    @Override
    public int getTotal(Registration reg) {
        int total = reg.getTotal();
        reg.updateGrandTotal(total);
        return total;
    }


    public static CompositeDiscount getInstance() {
        if(instance == null) {
            instance = new CompositeDiscount();
        }

        return instance;
    }

    public void add(IDiscountStrategy strategy) {
        strategies.add(strategy);
    }

    public LinkedList<IDiscountStrategy> getStrategies() {
        return strategies;
    }
}
