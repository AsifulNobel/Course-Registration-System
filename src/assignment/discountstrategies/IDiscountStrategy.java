package assignment.discountstrategies;

import assignment.models.Registration;

/**
 * Created by shawonashraf on 3/20/17.
 */
public interface IDiscountStrategy {
    public int getTotal(Registration reg);
}