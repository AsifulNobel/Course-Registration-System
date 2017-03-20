package assignment.models;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class AboroginDiscount implements IDiscountStrategy {
    @Override
    public int getTotal(Registration reg) {
        int total = reg.getTotal();
        return (int) (total - total * 0.40);
    }
}
