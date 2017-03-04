package assignment.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by nobel on 03/03/17.
 */
public class Course {
    private SimpleStringProperty id;
    private SimpleStringProperty title;
    private SimpleIntegerProperty credit;
    private SimpleIntegerProperty tuitionPerCredit;

    public Course(String id, String title, int credit, int tuitionPerCredit) {
        this.id = new SimpleStringProperty(id);
        this.title = new SimpleStringProperty(title);
        this.credit = new SimpleIntegerProperty(credit);
        this.tuitionPerCredit = new SimpleIntegerProperty(tuitionPerCredit);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(String.valueOf(id));
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public int getCredit() {
        return credit.get();
    }

    public void setCredit(int credit) {
        this.credit.set(credit);
    }

    public int getTuitionPerCredit() {
        return tuitionPerCredit.get();
    }

    public void setTuitionPerCredit(int tuitionPerCredit) {
        this.tuitionPerCredit.set(tuitionPerCredit);
    }

    public int getSubTotal() {
        return credit.get() * tuitionPerCredit.get();
    }
}
