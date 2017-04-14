package assignment.persistence;

import assignment.models.Course;
import assignment.models.Registration;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by nobel on 14/04/17.
 */
public class RegistrationRDBMapper implements IMapper {
    private String url = "jdbc:sqlite:DB/test.db";
    private Connection connection;

    @Override
    public Object get(String id) throws SQLException {
        Registration registration = new Registration();
        String sql = "SELECT * FROM Registration WHERE id = ?;";
        ResultSet registrations;

        connection = DriverManager.getConnection(url);
        PreparedStatement query = connection.prepareStatement(sql);

        query.setInt(1, Integer.parseInt(id));
        registrations = query.executeQuery();

        String[] temp = registrations.getString(2).split(", ");
        LinkedList<Course> tempCourses = new LinkedList<Course>();

        for (String title:
                temp) {
            tempCourses.add((Course) PersistenceFacade.getInstance().get(title, Course.class));
        }

        registration.setRegId(registrations.getInt(1));
        registration.setCourseList(tempCourses);
        registration.setTotalCredits(registrations.getInt(3));
        registration.setDiscount(registrations.getInt(4));
        registration.setDiscountedTotal(registrations.getInt(5));

        connection.close();

        return registration;
    }

    @Override
    public void put(Object object) throws SQLException {
        String sql = "INSERT INTO Registration(id, course_list_string, total_credit, \n"
                + "discount, discounted_total) \n"
                + "VALUES(?,?,?,?,?)";
        Registration registration = (Registration) object;

        connection = DriverManager.getConnection(url);
        PreparedStatement query = connection.prepareStatement(sql);

        query.setInt(1, registration.getRegId());
        query.setString(2, registration.getCourseList().stream().map(Course::getId).collect(
                Collectors.joining(", ")));
        query.setInt(3, registration.getTotalCredits());
        query.setInt(4, registration.getDiscount());
        query.setInt(5, registration.getDiscountedGrandTotal());

        query.executeUpdate();

        connection.close();
    }
}
