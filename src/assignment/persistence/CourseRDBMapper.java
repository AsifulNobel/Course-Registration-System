package assignment.persistence;

import assignment.models.Course;

import java.sql.*;

/**
 * Created by nobel on 14/04/17.
 */
public class CourseRDBMapper implements IMapper {
    private String url = "jdbc:sqlite:DB/test.db";
    private Connection connection;

    @Override
    public Object get(String id) {
        Course course = null;
        String sql = "SELECT * FROM Course_List WHERE id = ?;";
        ResultSet courses;

        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement query = connection.prepareStatement(sql);

            query.setString(1, id);
            courses = query.executeQuery();

            course = new Course(courses.getString(1), courses.getString(2),
                    courses.getInt(3), courses.getInt(4), courses.getString(5));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public void put(Object object) {
        String sql = "INSERT INTO Course_List(id, title, credit, tuitionPerCredit,prog_id) \n"
                + "VALUES(?,?,?,?,?)";
        Course course = (Course) object;

        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement query = connection.prepareStatement(sql);

            query.setString(1, course.getId());
            query.setString(2, course.getTitle());
            query.setInt(3, course.getCredit());
            query.setInt(4, course.getTuitionPerCredit());
            query.setString(5, course.getProgramId());

            query.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
