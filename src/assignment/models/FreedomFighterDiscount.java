package assignment.models;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class FreedomFighterDiscount implements IDiscountStrategy {
    @Override
    public int getTotal(Registration reg) {
        int total = reg.getTotal();
        if (reg.getCourseList().size() <= 5) {
            return total - 20000;
        } else {
            return total;
        }
    }
}
