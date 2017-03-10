package assignment.controller;

import assignment.models.Course;
import assignment.models.CourseFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    @FXML private ComboBox<String> courseField;

    @FXML private Label total;
    @FXML private Label devFee_bdTax;
    @FXML private Label grandTotal;

    private RegisterCourseController controller;

    public ObservableList<Course> data = FXCollections.observableArrayList();

    public ObservableList<String> options = FXCollections.observableArrayList(
            new CourseFactory().getcList().stream().map(Course::getId).collect(Collectors.toList())
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
        controller.makeNewRegistration();

        // Initializes combo-box
        courseField.setItems(options);
        courseField.setVisibleRowCount(4);
        courseField.setValue(options.get(0));

    }

    @FXML
    public void addReg() {
        // set the table empty
        // setting ObersevableList empty clears the table
        data.clear();
        controller.makeNewRegistration();
        total.setText(Integer.toString(controller.getReg().getTotal()));
        devFee_bdTax.setText(Integer.toString(0));
        grandTotal.setText(Integer.toString(0));
    }

    @FXML
    public void addCourse() {
        CourseFactory factory = new CourseFactory();
        String courseID = courseField.getValue();

        if(findIfInListAlready(courseID)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("!!!Error!!!");
            alert.setHeaderText("Can not add the course");
            alert.setContentText("Course already added");
            alert.showAndWait();
        } else {

            try {
                controller.addCourse(factory.getCourse(courseID));
                Course courseToAdd = controller.getCourse(courseID);

                data.add(courseToAdd);
                total.setText(Integer.toString(controller.getReg().getTotal()));
                devFee_bdTax.setText(Integer.toString(controller.getReg().getExtraFeeAmount()));
                grandTotal.setText(Integer.toString(controller.getReg().getGrandTotal()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("!!!Error!!!");
                alert.setHeaderText("Can not add the course");
                alert.setContentText("Course not found in Course Factory");
                alert.showAndWait();
            }
        }
    }

    private boolean findIfInListAlready(String id) {
        boolean isInList = data.stream().anyMatch(course -> course.getId().equals(id));
        return isInList;
    }
}
