package assignment.discountstrategies;

import assignment.models.Registration;

/**
 * Created by shawonashraf on 3/20/17.
 */
public class AcademicExcellenceDiscount implements IDiscountStrategy {

    private Registration registration;
    private int total;

    public AcademicExcellenceDiscount(Registration registration) {
        this.registration = registration;
        total = registration.getTotal();
    }


    @Override
    public int getTotal(Registration reg) {
        return (int) (total - (total * 0.5));
    }
}
