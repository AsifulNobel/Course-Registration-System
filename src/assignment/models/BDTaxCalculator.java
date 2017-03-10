package assignment.models;

/**
 * Created by nobel on 10/03/17.
 */
public class BDTaxCalculator {
    public float calculateVATAmount(int total) {
        return (float) (total*0.15);
    }
}
