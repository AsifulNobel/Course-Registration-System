package assignment.models;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class BestForStudent extends CompositeDiscount {
    @Override
    public int getTotal(Registration reg) {
        int numberOfStrategies = super.getStrategies().size();

        if(numberOfStrategies >= 2) {
            super.getStrategies().forEach(
                    k -> {
                        int total = k.getTotal(reg);
                        reg.updateGrandTotal(total);
                    }
            );
        }

        return 0;
    }
}
