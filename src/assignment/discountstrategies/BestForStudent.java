package assignment.discountstrategies;

import assignment.models.Registration;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class BestForStudent extends CompositeDiscount {

    private int total;

    public BestForStudent() {
        super();
        total = 0;
    }

    @Override
    public int getTotal(Registration reg) {
        int numStrat = getStrategies().size();
        int highest = Integer.MAX_VALUE;

        for (int i = 0; i < numStrat; i++) {
            total = Math.min(getStrategies().get(i).getTotal(reg), highest);
            highest = total;
        }

        return total;
    }
}
