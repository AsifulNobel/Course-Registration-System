package assignment.controller;

import assignment.discountstrategies.CompositeDiscount;
import assignment.models.Course;
import assignment.models.CourseFactory;
import assignment.notifiers.BeepMaker;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by nobel on 04/03/17.
 */
public class RegistrationFxController implements Initializable, Observer {
    @FXML
    private TableView<Course> table;
    @FXML
    private TableColumn<Course, String> title;
    @FXML
    private TableColumn<Course, Integer> slNo;
    @FXML
    private TableColumn<Course, Integer> credit;
    @FXML
    private TableColumn<Course, Integer> tuitionPerCredit;
    @FXML
    private TableColumn<Course, Integer> subTotal;

    @FXML
    private Button addButton;
    @FXML
    private Button newReg;
    @FXML
    private Button calculateDiscount;

    @FXML
    private ComboBox<String> courseField;
    @FXML
    private ComboBox<String> bestComboSelector;

    @FXML
    private Label total;
    @FXML
    private Label devFee_bdTax;
    @FXML
    private Label grandTotal;
    @FXML
    private Label discount;


    @FXML
    private CheckBox excellenceBox;
    @FXML
    private CheckBox minorityBox;
    @FXML
    private CheckBox freedomBox;


    // helpers

    private RegisterCourseController controller;
    private CourseFactory factory;
    private CompositeDiscount compositeDiscount;


    // notifiers

    BeepMaker beepBeep;

    // observable lists

    public ObservableList<Course> data = FXCollections.observableArrayList();
    public ObservableList<String> options = FXCollections.observableArrayList();
    public ObservableList<String> comboOptions = FXCollections.observableArrayList();
    // populate above list with BestForNSU and BestForStudent text like we did with options

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

        // To maintain single instance of CourseFactory.
        // If new CourseFactory() is used, then one instance will be created here.
        // Again another one will be created when CourseFactory.getInstance method is
        // called in Registration.getExtraFeeAmount method.
        factory = CourseFactory.getInstance();

        options.addAll(factory.getcList().stream().map(Course::getId).collect(Collectors.toList()));

        // Initializes course combo-box
        courseField.setItems(options);
        courseField.setVisibleRowCount(4);
        courseField.setValue(options.get(0));

        // init discount combo box

        // init observable dictionary
        comboOptions.add("Best For NSU");
        comboOptions.add("Best For Student");

        // add to component
        bestComboSelector.getItems().addAll(comboOptions);
        bestComboSelector.setValue(comboOptions.get(0));

        // add observer
        controller.getReg().addObserver(this);
    }

    @FXML
    public void addReg() {
        // set the table empty
        // setting ObersevableList empty clears the table
        data.clear();
        controller.makeNewRegistration();

        // add observer for the new reg object
        controller.getReg().addObserver(this);

        total.setText(Integer.toString(controller.getReg().getTotal()));
        devFee_bdTax.setText(Integer.toString(0));
        grandTotal.setText(Integer.toString(0));
        discount.setText(Integer.toString(0));
    }

    @FXML
    public void addCourse() {
        String courseID = courseField.getValue();

        if (findIfInListAlready(courseID)) {
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
                e.printStackTrace();
            }
        }
    }

    private boolean findIfInListAlready(String id) {
        boolean isInList = data.stream().anyMatch(course -> course.getId().equals(id));
        return isInList;
    }

    @FXML
    private void calculateDiscount() {
        // need implementation
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Grand Total has been updated ... @ " + new Date().getTime());
    }
}
