package assignment.controller;

import assignment.components.ObserverDiscountLabel;
import assignment.components.ObserverTotalLabel;
import assignment.configurationUser.ConfigLoader;
import assignment.discountstrategies.IDiscountStrategy;
import assignment.models.Course;
import assignment.models.CourseFactory;
import assignment.notifiers.BeepMaker;
import assignment.persistence.PersistenceFacade;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collector;
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
    @FXML private Button calculateDiscount;
    @FXML private Button saveReg;
    @FXML private Button fetchReg;
    @FXML private Button courseInfo;

    @FXML private ComboBox<String> courseField;
    @FXML private ComboBox<String> bestComboSelector;

    @FXML private Label total;
    @FXML private Label devFee_bdTax;
    @FXML private ObserverDiscountLabel discount;
    @FXML private ObserverTotalLabel grandTotal;

    @FXML private CheckBox excellenceBox;
    @FXML private CheckBox minorityBox;
    @FXML private CheckBox freedomBox;

    @FXML private TextField studentId;

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

    private int COURSE_WINDOW_STATE = 0;
    private int REGISTRATION_WINDOW_STATE = 0;

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

        /* Observer-Subject Initialization */
        grandTotal.setObservable(controller.getReg());
        discount.setObservable(controller.getReg());

        controller.getReg().addObserver(new BeepMaker(controller.getReg()));
        controller.getReg().addObserver(grandTotal);
        controller.getReg().addObserver(discount);
    }


    /*
     * Creates new registration and re-initializes value labels to zero.
     * Also reconnects observers to subject
     */
    @FXML
    public void addReg() {
        // set the table empty
        // setting ObersevableList empty clears the table
        data.clear();
        controller.makeNewRegistration();
        total.setText(Integer.toString(controller.getReg().getTotal()));
        devFee_bdTax.setText(Integer.toString(0));
        discount.setText("0");
        grandTotal.setText(Integer.toString(0));

        grandTotal.setObservable(controller.getReg());
        discount.setObservable(controller.getReg());

        controller.getReg().addObserver(new BeepMaker(controller.getReg()));
        controller.getReg().addObserver(grandTotal);
        controller.getReg().addObserver(discount);
    }

    /* *
     * Adds row of course related info to TableView after each addition
     * of course in registration and updates value labels
     * */
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
        IDiscountStrategy temp;

        if (excellenceBox.isSelected())
            strategyList.add(discountOptions[0]);
        if (minorityBox.isSelected())
            strategyList.add(discountOptions[1]);
        if (freedomBox.isSelected())
            strategyList.add(discountOptions[2]);
        temp = factory.createDiscountPolicy(strategyList, bestComboSelector.getValue().replaceAll("\\s+", ""));

        if (temp != null)
            controller.getReg().setDiscountStrategy(temp);
    }


    /* *
     * Opens a new window which contains a table of course info and also
     * has option to add a new course to DB.
     * */
    @FXML
    private void showCourses() {
        try {
            if (this.COURSE_WINDOW_STATE == 0) {
                Stage secondaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../view/courseWindow.fxml"));
                secondaryStage.setTitle("Course Information");
                secondaryStage.setScene(new Scene(root, 600, 337));
                secondaryStage.setResizable(false);
                secondaryStage.show();

                this.COURSE_WINDOW_STATE = 1;

                secondaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    /* *
                     * Re-enables the button after closing of the window
                     * by unsetting COURSE_WINDOW_STATE
                     * */
                    public void handle(WindowEvent we) {
                        COURSE_WINDOW_STATE = 0;
                        options.clear();
                        options.addAll(factory.getcList().stream().map(Course::getId).collect(Collectors.toList()));
                        courseField.setValue(options.get(0));
                    }
                });
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Can not open course window");
                alert.setContentText("Course Window Already Opened");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* *
    * Gets called when registration is saved.
    * */
    public void saveRegistration() {
        controller.getReg().setRegId(Integer.parseInt(studentId.getText()));

        try {
            PersistenceFacade.getInstance().put(controller.getReg());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText("Registration Completed");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FAILURE");
            alert.setHeaderText("Registration Failed");
            alert.setContentText("Duplicate Student ID");
            alert.showAndWait();
        }
    }


    public void fetchRegistration() {
        Context.getInstance().setStudentId(studentId.getText());

        try {
            if (this.REGISTRATION_WINDOW_STATE == 0) {
                Stage secondaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../view/registrationViewerWindow.fxml"));
                secondaryStage.setTitle("Registration Info");
                secondaryStage.setScene(new Scene(root, 309, 242));
                secondaryStage.setResizable(false);
                secondaryStage.show();

                this.REGISTRATION_WINDOW_STATE = 1;

                secondaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    /* *
                     * Re-enables the button after closing of the window
                     * by unsetting REGISTRATION_WINDOW_STATE
                     * */
                    public void handle(WindowEvent we) {
                        REGISTRATION_WINDOW_STATE = 0;
                    }
                });
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Can not open registration window");
                alert.setContentText("Registration Window Already Opened");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
