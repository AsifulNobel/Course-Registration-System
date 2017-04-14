package assignment.controller;

/**
 * Created by nobel on 14/04/17.
 */
public class Context {
    private final static Context instance = new Context();
    private String studentId;

    public static Context getInstance() {
        return instance;
    }

    public void setStudentId(String id) {
        this.studentId = id;
    }

    public String getStudentId() {
        return studentId;
    }
}
