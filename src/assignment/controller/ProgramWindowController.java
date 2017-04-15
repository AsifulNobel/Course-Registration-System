package assignment.controller;

import assignment.models.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by nobel on 14/04/17.
 */
public class ProgramWindowController implements Initializable {
    @FXML private Label courseId;
    @FXML private Label courseTitle;
    @FXML private Label credits;
    @FXML private Label tuitionPerCredit;
    @FXML private Label programName;
    @FXML private Label programTitle;
    @FXML private Label department;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Course course = Context.getInstance().getCourse();

        courseId.setText(course.getId());
        courseTitle.setText(course.getTitle());
        credits.setText(String.valueOf(course.getCredit()));
        tuitionPerCredit.setText(String.valueOf(course.getTuitionPerCredit()));
        programName.setText(course.getProgramId());
        programTitle.setText(course.getProgramTitle());
        department.setText(course.getProgramDepartment());
    }
}
