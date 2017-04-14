package assignment.models;

/**
 * Created by nobel on 15/04/17.
 */
public class Program implements IProgram {
    private String id;
    private String title;
    private String department;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
