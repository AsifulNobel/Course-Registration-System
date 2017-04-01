package assignment.discountstrategies;

import assignment.models.Registration;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class AboriginDiscount implements IDiscountStrategy {

    public AboriginDiscount() {

    }


    @Override
    public int getTotal(Registration reg) {
        int total = (int) reg.getTotal();
        return (int) (total - total * 0.60);
    }

}
