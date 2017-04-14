package assignment.controller;

import assignment.models.Course;

/**
 * Created by nobel on 14/04/17.
 */
public class Context {
    private final static Context instance = new Context();
    private String studentId;
    private Course course;

    public static Context getInstance() {
        return instance;
    }

    public void setStudentId(String id) {
        this.studentId = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
