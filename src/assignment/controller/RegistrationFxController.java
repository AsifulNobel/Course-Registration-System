package assignment.controller;

import assignment.configurationUser.ConfigLoader;
import assignment.discountstrategies.AcademicExcellenceDiscount;
import assignment.models.Course;
import assignment.models.CourseFactory;
import assignment.notifiers.BeepMaker;
import com.sun.javafx.collections.MappingChange;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by nobel on 04/03/17.
 */
public class RegistrationFxController implements Initializable, Observer{
    @FXML private TableView<Course> table;
    @FXML private TableColumn<Course, String> title;
    @FXML private TableColumn<Course, Integer> slNo;
    @FXML private TableColumn<Course, Integer> credit;
    @FXML private TableColumn<Course, Integer> tuitionPerCredit;
    @FXML private TableColumn<Course, Integer> subTotal;

    @FXML private Button addButton;
    @FXML private Button newReg;
    @FXML private Button calculateDiscount;

    @FXML private ComboBox<String> courseField;
    @FXML private ComboBox<String> bestComboSelector;

    @FXML private Label total;
    @FXML private Label devFee_bdTax;
    @FXML private Label discount;
    @FXML private Label grandTotal;

    @FXML private CheckBox excellenceBox;
    @FXML private CheckBox minorityBox;
    @FXML private CheckBox freedomBox;

    private RegisterCourseController controller;
    private CourseFactory factory;
    private ConfigLoader propLoader;

    public ObservableList<Course> data = FXCollections.observableArrayList();
    public ObservableList<String> options = FXCollections.observableArrayList();
    public ObservableList<String> comboOptions = FXCollections.observableArrayList();

    private String[] discountOptions = {"AcademicExcellenceDiscount", "AboriginDiscount",
            "FreedomFighterDiscount"};

    private Map<String, String> optionClassMap;
    private LinkedList<String> strategyList;

    public Observable observableObject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        propLoader.getInstance().loadProperties();

        title.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        credit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));
        tuitionPerCredit.setCellValueFactory(new PropertyValueFactory<Course, Integer>("tuitionPerCredit"));
        subTotal.setCellValueFactory(new PropertyValueFactory<Course, Integer>("subTotal"));

        table.setItems(data);
        slNo.setCellValueFactory(col -> new ReadOnlyObjectWrapper<Integer>(table.getItems().indexOf(col.getValue()) + 1));
        slNo.setSortable(false);

        optionClassMap = new HashMap<>();
        optionClassMap.put("Best For NSU", "BestForNSU");
        optionClassMap.put("Best For Student", "BestForStudent");

        controller = new RegisterCourseController();
        controller.makeNewRegistration();

        // To maintain single instance of CourseFactory.
        // If new CourseFactory() is used, then one instance will be created here.
        // Again another one will be created when CourseFactory.getInstance method is
        // called in Registration.getExtraFeeAmount method.
        factory = CourseFactory.getInstance();

        options.addAll(factory.getcList().stream().map(Course::getId).collect(Collectors.toList()));

        // Initializes combo-box
        courseField.setItems(options);
        courseField.setVisibleRowCount(4);
        courseField.setValue(options.get(0));

        comboOptions.add("Best For NSU");
        comboOptions.add("Best For Student");
        bestComboSelector.setItems(comboOptions);
        bestComboSelector.setValue(comboOptions.get(0));

        controller.getReg().addObserver(new BeepMaker(controller.getReg()));
        controller.getReg().addObserver(this);
        observableObject = controller.getReg();
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
                discount.setText("0");
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

    @FXML
    private void calculateDiscount() {
        strategyList = new LinkedList<String>();

        if (excellenceBox.isSelected())
            strategyList.add(discountOptions[0]);
        if (minorityBox.isSelected())
            strategyList.add(discountOptions[1]);
        if (freedomBox.isSelected())
            strategyList.add(discountOptions[2]);
        factory.createDiscountPolicy(strategyList, optionClassMap.get(bestComboSelector.getValue()), controller.getReg());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (observableObject == o) {
            grandTotal.setText(Integer.toString(controller.getReg().getDiscountedGrandTotal()));
            discount.setText(Integer.toString(controller.getReg().getGrandTotal()-controller.getReg().getDiscountedGrandTotal()));
        }
    }
}
