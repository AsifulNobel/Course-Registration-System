package assignment.controller;

import assignment.models.Course;
import assignment.models.CourseFactory;
import assignment.persistence.PersistenceFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by nobel on 14/04/17.
 */
public class CourseWindowController implements Initializable {
    @FXML private TextField courseId;
    @FXML private TextField courseTitle;
    @FXML private TextField credit;
    @FXML private TextField tuitionPerCredit;
    @FXML private ComboBox<String> programId;

    @FXML private Button addButton;

    @FXML private TableView<Course> table;
    @FXML private TableColumn<Course, String> idInfo;
    @FXML private TableColumn<Course, String> titleInfo;
    @FXML private TableColumn<Course, Integer> creditInfo;
    @FXML private TableColumn<Course, Integer> tuitionPerCreditInfo;
    @FXML private TableColumn<Course, Button> moreInfo;

    public ObservableList<Course> courses = FXCollections.observableArrayList();
    public ObservableList<String> programs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idInfo.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        titleInfo.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        creditInfo.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));
        tuitionPerCreditInfo.setCellValueFactory(new PropertyValueFactory<Course, Integer>("tuitionPerCredit"));

        moreInfo.setCellValueFactory(new PropertyValueFactory<>("BUTTON"));

        moreInfo.setCellFactory(column -> {
            return new TableCell<Course, Button>() {

                @Override
                public void updateItem(Button item, boolean empty) {
                    Button btn = new Button("VIEW");
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        btn.setOnAction((ActionEvent event) -> {
                            Course course = getTableView().getItems().get(getIndex());
                        } );
                        setGraphic(btn);
                        setText(null);
                    }
                }
            };
        });

        courses.addAll(CourseFactory.getInstance().getcList());
        table.setItems(courses);

        programs.addAll(CourseFactory.getInstance().getpList());
        programId.setItems(programs);
        programId.setVisibleRowCount(3);
        programId.setValue(programs.get(0));
    }


    public void addCourse() {
        Course course = new Course(courseId.getText(),
                courseTitle.getText(), Integer.parseInt(credit.getText()),
                Integer.parseInt(tuitionPerCredit.getText()));

        try {
            PersistenceFacade.getInstance().put(course);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FAILURE");
            alert.setHeaderText("Course Cannot Be Added");
            alert.setContentText("Course Already Exists!");
            alert.showAndWait();
        }

        courses.add(course);
    }
}
