package assignment.models;

/**
 * Created by nobel on 10/03/17.
 */
public class BDTaxAdapter implements IExtraFeeCalculator {
    BDTaxCalculator bdTaxCalculator = new BDTaxCalculator();

    @Override
    public int getExtraAmount (int courseTotal) {
        return (int) bdTaxCalculator.calculateVATAmount(courseTotal);
    }
}
