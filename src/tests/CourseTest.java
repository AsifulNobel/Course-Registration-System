package tests;


import assignment.models.Course;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by shawon on 3/4/17.
 */
public class CourseTest {

    @Test
    public void getSubTotalTest() {
        Course course = new Course("CSE 327", "Soft.Eng.", 3, 5500);
        int subTotal = course.getSubTotal();
        Assert.assertEquals((3 * 5500), subTotal);
    }
}
