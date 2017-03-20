package assignment.models;

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

        for(int i = 0; i < numStrat; i++) {
            total += getStrategies().get(i).getTotal(reg);
        }

        return total;
    }
}
