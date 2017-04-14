package assignment.persistence;

import java.sql.SQLException;

/**
 * Created by nobel on 14/04/17.
 */
public interface IMapper {
    public Object get(String id) throws SQLException;
    public void put(Object object) throws SQLException;
}
