package assignment.persistence;

import assignment.models.Program;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by nobel on 14/04/17.
 */
public class ProgramRDBMapper implements IMapper {
    private String url = "jdbc:sqlite:DB/test.db";
    private Connection connection;

    @Override
    public Object get(String id) throws SQLException {
        Program program = new Program();
        String sql = "SELECT * FROM Programs WHERE id = ?;";
        ResultSet programs;

        connection = DriverManager.getConnection(url);
        PreparedStatement query = connection.prepareStatement(sql);

        query.setString(1, id);
        programs = query.executeQuery();

        program.setId(programs.getString(1));
        program.setTitle(programs.getString(2));
        program.setDepartment(programs.getString(3));

        connection.close();

        return program;
    }

    @Override
    public void put(Object object) throws SQLException {
        String sql = "INSERT INTO Programs(id, title, department) \n"
                + "VALUES(?,?,?)";
        Program program = (Program) object;

        connection = DriverManager.getConnection(url);
        PreparedStatement query = connection.prepareStatement(sql);

        query.setString(1, program.getId());
        query.setString(2, program.getTitle());
        query.setString(3, program.getDepartment());

        query.executeUpdate();

        connection.close();
    }
}
