package assignment.discountstrategies;

import assignment.models.Registration;

import java.util.LinkedList;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class CompositeDiscount implements IDiscountStrategy {

    private LinkedList<IDiscountStrategy> strategies;
    private int total;


    public CompositeDiscount() {
        strategies = new LinkedList<>();
    }

    @Override
    public int getTotal(Registration reg) {
        // IMPLEMENT

        return total;
    }


    public void add(IDiscountStrategy strategy) {
        strategies.add(strategy);
    }

    public void clear() {
        strategies.clear();
    }

    public LinkedList<IDiscountStrategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(LinkedList<IDiscountStrategy> strategies) {
        this.strategies = strategies;
    }
}
