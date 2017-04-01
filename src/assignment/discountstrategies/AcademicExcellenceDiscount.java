package assignment.discountstrategies;

import assignment.models.Registration;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class AcademicExcellenceDiscount implements IDiscountStrategy {

    public AcademicExcellenceDiscount() {
    }


    @Override
    public int getTotal(Registration reg) {
        int total = (int) reg.getTotal();
        return (int) (total - (total * 0.5));
    }
}
