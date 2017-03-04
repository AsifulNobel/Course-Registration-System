package assignment.controller;

import assignment.models.Course;
import assignment.models.CourseFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


import javax.swing.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by nobel on 04/03/17.
 */
public class RegistrationFxController implements Initializable {
    @FXML private TableView<Course> table;
    @FXML private TableColumn<Course, String> title;
    @FXML private TableColumn<Course, Integer> slNo;
    @FXML private TableColumn<Course, Integer> credit;
    @FXML private TableColumn<Course, Integer> tuitionPerCredit;
    @FXML private TableColumn<Course, Integer> subTotal;

    @FXML private Button addButton;
    @FXML private Button newReg;

    @FXML private TextField courseField;

    @FXML private Label grandTotal;

    private RegisterCourseController controller;

    public ObservableList<Course> data = FXCollections.observableArrayList(
            new CourseFactory().getcList()
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        credit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));
        tuitionPerCredit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("tuitionPerCredit"));
        subTotal.setCellValueFactory(new PropertyValueFactory<Course, Integer>("subTotal"));

        table.setItems(data);
        slNo.setCellValueFactory(col -> new ReadOnlyObjectWrapper<Integer>(table.getItems().indexOf(col.getValue()) + 1));
        slNo.setSortable(false);

        controller = new RegisterCourseController();
        grandTotal.setText(Integer.toString(data.stream().mapToInt(Course::getSubTotal).sum()));
    }

    @FXML
    public void addReg() {
        // set the table empty
        // setting ObersevableList empty clears the table
        data.clear();
        controller.makeNewRegistration();
        grandTotal.setText(Integer.toString(controller.getReg().getTotal()));
    }

    @FXML
    public void addCourse() {
        CourseFactory factory = new CourseFactory();
        String courseID = courseField.getText();

        try {
            controller.addCourse(factory.getCourse(courseID));
            Course courseToAdd = controller.getCourse(courseID);

            data.add(courseToAdd);
            grandTotal.setText(Integer.toString(controller.getReg().getTotal()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("!!!Error!!!");
            alert.setHeaderText("Can not add the course");

            alert.showAndWait();
        }


    }
}
