package assignment.models;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class CompositeDiscount implements IDiscountStrategy, Observer {

    private LinkedList<IDiscountStrategy> strategies;
    private Registration registration;
    private int total;
    private String strategyName;

    public CompositeDiscount() {
    }

    public CompositeDiscount(Registration registration) {
        strategies = new LinkedList<>();
        this.registration = registration;
        total = 0;
    }

    @Override
    public int getTotal(Registration reg) {
        if(strategies.size() < 2) {
            // best for NSU

            strategyName = "Best For NSU";

            BestForNSU bestForNSU = new BestForNSU();
            bestForNSU.setStrategies(this.strategies);
            total = bestForNSU.getTotal(registration);
        } else {
            // best for student

            strategyName = "Best For Student";

            BestForStudent bestForStudent = new BestForStudent();
            bestForStudent.setStrategies(this.strategies);
            total = bestForStudent.getTotal(registration);
        }


       return total;
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

    @Override
    public void update(Observable o, Object arg) {
        total = registration.getGrandTotal();
    }

    public String getStrategyName() {
        return strategyName;
    }
}
