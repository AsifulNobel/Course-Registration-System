package assignment.models;

/**
 * Created by nobel on 10/03/17.
 */
public class DevelopmentFeeCalculator implements IExtraFeeCalculator {

    @Override
    public int getExtraAmount(int courseTotal) {
        return (int) (courseTotal*0.1);
    }
}
