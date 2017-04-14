package assignment.controller;

import assignment.models.Course;
import assignment.models.CourseFactory;
import assignment.models.IProgram;
import assignment.persistence.PersistenceFacade;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
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
    @FXML private TableColumn<Course, IProgram> programInfo;

    public ObservableList<Course> courses = FXCollections.observableArrayList();
    public ObservableList<String> programs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idInfo.setCellValueFactory(new PropertyValueFactory<Course, String>("id"));
        titleInfo.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        creditInfo.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));
        tuitionPerCreditInfo.setCellValueFactory(new PropertyValueFactory<Course, Integer>("tuitionPerCredit"));

        programInfo.setCellValueFactory(cellData -> cellData.getValue().getProgramProperty());
        programInfo.setVisible(false);

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
                            Context.getInstance().setCourse(course);

                            Stage stage = new Stage();
                            Parent root = null;

                            try {
                                root = FXMLLoader.load(getClass().getResource("../view/programWindow.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            stage.setTitle("Course Details");
                            stage.setScene(new Scene(root, 600, 337));
                            stage.setResizable(false);
                            stage.show();
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
                Integer.parseInt(tuitionPerCredit.getText()), programId.getValue());

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
