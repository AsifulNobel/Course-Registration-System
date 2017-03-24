package assignment.discountstrategies;

import assignment.models.Registration;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class FreedomFighterDiscount implements IDiscountStrategy {

    private Registration registration;
    private int total;

    public FreedomFighterDiscount(Registration registration) {
        this.registration = registration;
        total = registration.getTotal();
    }


    @Override
    public int getTotal(Registration reg) {
        if (reg.getCourseList().size() <= 5 && total >= 20000) {
            total = total - 20000;
            return total;
        } else {
            return total;
        }
    }
}
