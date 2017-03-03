package assignment.controller;

import assignment.models.Course;
import assignment.models.Registration;

public class RegisterCourseController {
    private Registration reg;

    public void makeNewRegistration() {
        reg = new Registration();
    }

    public void addCourse(Course course) {
        reg.addCourse(course);
    }

    public Registration getReg() {
        return reg;
    }

    public Course getCourse(String id) {
        Course course;

        course = reg.getCourseList().stream().filter(k -> k.getId().equals(id)).findFirst().get();

        return course;
    }
}
