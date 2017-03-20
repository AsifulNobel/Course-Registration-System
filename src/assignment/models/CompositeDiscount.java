package assignment.models;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class CompositeDiscount implements IDiscountStrategy {
    @Override
    public int getTotal(Registration reg) {
        return 0;
    }

    public void add(IDiscountStrategy strategy) {

    }
}
