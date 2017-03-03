package assignment;

/**
 * Created by nobel on 03/03/17.
 */
public class Course {
    private String id;
    private String title;
    private int credit;
    private int tuitionPerCredit;

    public Course(String id, String title, int credit, int tuitionPerCredit) {
        this.id = id;
        this.title = title;
        this.credit = credit;
        this.tuitionPerCredit = tuitionPerCredit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getTuitionPerCredit() {
        return tuitionPerCredit;
    }

    public void setTuitionPerCredit(int tuitionPerCredit) {
        this.tuitionPerCredit = tuitionPerCredit;
    }

    public int getSubTotal() {
        return credit * tuitionPerCredit;
    }
}
