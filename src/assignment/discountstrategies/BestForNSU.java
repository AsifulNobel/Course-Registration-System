package assignment.discountstrategies;

import assignment.models.Registration;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class BestForNSU extends CompositeDiscount {

    private int total;

    public BestForNSU() {
        super();
        total = 0;
    }

    @Override
    public int getTotal(Registration reg) {
        int numStrat = getStrategies().size();
        int lowest = Integer.MIN_VALUE;

        for(int i = 0; i < numStrat; i++) {
            total = Math.max(getStrategies().get(i).getTotal(reg), lowest);
        }

        return total;
    }
}
