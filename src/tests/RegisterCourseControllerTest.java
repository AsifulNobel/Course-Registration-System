package tests;

import assignment.controller.RegisterCourseController;
import assignment.models.Course;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by shawon on 3/4/17.
 */
public class RegisterCourseControllerTest {

    @Test
    public void getCourseTest() {
        Course course = new Course("CSE 327", "Soft.Eng.", 3, 5500);
        RegisterCourseController controller = new RegisterCourseController();

        controller.makeNewRegistration();
        controller.addCourse(course);

        Course courseGot = controller.getCourse("CSE 327");

        Assert.assertEquals("CSE 327", courseGot.getId());
    }
}
