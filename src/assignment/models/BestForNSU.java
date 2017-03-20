package assignment.models;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class BestForNSU extends CompositeDiscount {

    @Override
    public int getTotal(Registration reg) {
        int numberOfStrategies = super.getStrategies().size();
        int total = 0;

        if(numberOfStrategies < 2) {
            for(int i = 0; i < numberOfStrategies; i++) {
                int x = super.getStrategies().get(i).getTotal(reg);
                reg.updateGrandTotal(x);
                total = x;
            }
        }

        return total;
    }
}
