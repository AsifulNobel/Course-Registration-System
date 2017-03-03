package tests;

import assignment.models.Course;
import assignment.models.Registration;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by shawon on 3/4/17.
 */
public class RegistrationTest {

    @Test
    public void getTotalTest() {
        Registration registration = new Registration();

        registration.addCourse(new Course("CSE 327", "Soft.Eng.", 3, 5500));
        registration.addCourse(new Course("CSE 440", "Intro. to AI", 3, 5500));
        registration.addCourse(new Course("CSE 373", "Intro.Algo.", 3, 5500));

        int total = registration.getTotal();

        Assert.assertEquals((3 * 3 * 5500), total);
    }

    @Test
    public void addCourseTest() {
        Registration registration = new Registration();

        int numCoursesBefore = registration.getCourseList().size();

        registration.addCourse(new Course("CSE 327", "Soft.Eng.", 3, 5500));
        int numCoursesAfter = registration.getCourseList().size();

        Assert.assertEquals(numCoursesBefore + 1, numCoursesAfter);

    }
}
