package assignment.controller;

import assignment.models.Course;
import assignment.models.CourseFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by nobel on 04/03/17.
 */
public class RegistrationFxController implements Initializable {
    @FXML private TableView<Course> table;
    @FXML private TableColumn<Course, Integer> serial_no;
    @FXML private TableColumn<Course, String> title;
    @FXML private TableColumn<Course, Integer> slNo;
    @FXML private TableColumn<Course, Integer> credit;
    @FXML private TableColumn<Course, Integer> tuitionPerCredit;
    @FXML private TableColumn<Course, Integer> subTotal;

    @FXML private Button addButton;
    @FXML private Button newReg;

    @FXML private TextField courseField;

    private RegisterCourseController controller;

    public ObservableList<Course> data = FXCollections.observableArrayList(
            new CourseFactory().getcList()
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serial_no.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Integer>(table.getItems().indexOf(column.getValue())+1));
        title.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        credit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));
        tuitionPerCredit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("tuitionPerCredit"));
        subTotal.setCellValueFactory(new PropertyValueFactory<Course, Integer>("subTotal"));

        table.setItems(data);
        slNo.setCellValueFactory(col -> new ReadOnlyObjectWrapper<Integer>(table.getItems().indexOf(col.getValue()) + 1));
        slNo.setSortable(false);

        controller = new RegisterCourseController();
    }

    @FXML
    public void addReg() {
        // set the table empty
        // setting ObersevableList empty clears the table
        data.clear();
        controller.makeNewRegistration();
    }

    @FXML
    public void addCourse() {
        CourseFactory factory = new CourseFactory();
        String courseID = courseField.getText();

        controller.addCourse(factory.getCourse(courseID));
        Course courseToAdd = controller.getCourse(courseID);

        if(courseID != null) {
            data.add(courseToAdd);
        } else {
            System.out.println("Course not found");
        }
    }
}
