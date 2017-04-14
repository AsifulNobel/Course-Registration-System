package assignment.controller;

import assignment.models.Course;
import assignment.models.Registration;
import assignment.persistence.PersistenceFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by nobel on 14/04/17.
 */
public class RegistrationViewer implements Initializable {
    @FXML private Label studentId;
    @FXML private Label course_list;
    @FXML private Label credits;
    @FXML private Label discount;
    @FXML private Label total;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Registration registration = (Registration) PersistenceFacade.getInstance().get(Context.getInstance().
                    getStudentId(), new Registration());

            studentId.setText(String.valueOf(registration.getRegId()));
            course_list.setText(String.valueOf(registration.getCourseList().stream().map(Course::getId).
                    collect(Collectors.joining(", "))));
            credits.setText(String.valueOf(registration.getTotalCredits()));
            discount.setText(String.valueOf(registration.getDiscount()));
            total.setText(String.valueOf(registration.getDiscountedGrandTotal()));

            course_list.setMaxWidth(150);
            course_list.setWrapText(true);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FAILURE");
            alert.setHeaderText("ID Not Found!");
            alert.showAndWait();

            e.printStackTrace();
        }
    }


}
