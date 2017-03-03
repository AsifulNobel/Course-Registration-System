package assignment.models;

import java.util.LinkedList;

/**
 * Created by nobel
 * modified by shawon
 * on 03/03/17.
 */
public class Registration {
    public LinkedList<Course> courseList;

    public Registration() {
         courseList = new LinkedList<>();
    }

    public LinkedList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<Course> courseList) {
        this.courseList = courseList;
    }

    public void addCourse(Course newCourse) {
        courseList.add(newCourse);
    }

    public int getTotal() {
        int total = courseList.stream().mapToInt(Course::getSubTotal).sum();

        return total;
    }
}
