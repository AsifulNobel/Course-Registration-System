package tests;

import assignment.models.Course;
import assignment.models.CourseFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by shawon on 3/4/17.
 */
public class CourseFactoryTest {

    @Test
    public void getCourseTest() {
        Course course = new Course("CSE 338", "Networking", 3, 5500, "CSE");
        CourseFactory courseFactory = new CourseFactory();

        Course x = courseFactory.getCourse(course.getId());

        Assert.assertEquals(course.getId(), x.getId());
        Assert.assertEquals(course.getTitle(), x.getTitle());
        Assert.assertEquals(course.getSubTotal(), x.getSubTotal());
    }
}
