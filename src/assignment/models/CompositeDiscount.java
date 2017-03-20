package assignment.models;

import java.util.LinkedList;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class CompositeDiscount implements IDiscountStrategy {

    private LinkedList<IDiscountStrategy> strategies;

    public CompositeDiscount() {
        strategies = new LinkedList<>();
    }

    @Override
    public int getTotal(Registration reg) {
       return 0;
    }


    public void add(IDiscountStrategy strategy) {
        strategies.add(strategy);
    }

    public LinkedList<IDiscountStrategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(LinkedList<IDiscountStrategy> strategies) {
        this.strategies = strategies;
    }
}
