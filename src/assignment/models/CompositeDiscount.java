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
        int total = 0;

        if(strategies.size() < 2) {
            // best for nsu
            BestForNSU bestForNSU = new BestForNSU();
            bestForNSU.setStrategies(strategies);

            total = bestForNSU.getTotal(reg);
        } else {
            // best for student

            BestForStudent bestForStudent = new BestForStudent();
            bestForStudent.setStrategies(strategies);

            total = bestForStudent.getTotal(reg);
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
}
