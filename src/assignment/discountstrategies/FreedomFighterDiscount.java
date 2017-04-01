package assignment.discountstrategies;

import assignment.models.Registration;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class FreedomFighterDiscount implements IDiscountStrategy {

    public FreedomFighterDiscount() {

    }


    @Override
    public int getTotal(Registration reg) {
        int total = (int) reg.getTotal();

        if (reg.getCourseList().size() <= 5 && total >= 20000) {
            total = total - 20000;
        }
        return total;
    }
}
