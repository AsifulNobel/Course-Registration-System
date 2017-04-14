package assignment.persistence;

import assignment.models.Course;
import assignment.models.Program;
import assignment.models.Registration;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by nobel on 14/04/17.
 */
public class PersistenceFacade {
    private static PersistenceFacade instance;
    HashMap<Class <?> , IMapper> mappers = new HashMap<Class <?> , IMapper>();

    public  PersistenceFacade() {
        mappers.put(Course.class, new CourseRDBMapper());
        mappers.put(Registration.class, new RegistrationRDBMapper());
        mappers.put(Program.class, new ProgramRDBMapper());
    }

    public void put(Object object) throws SQLException {

        IMapper mapper = mappers.get(object.getClass());
        mapper.put(object);
    }

    public Object get(String id , Object object) throws SQLException {
        System.out.println(id);
        System.out.println(mappers.get(object.getClass()));

        IMapper mapper = mappers.get(object.getClass());

        System.out.println(mapper == null);

        return mapper.get(id);
    }

    public static synchronized PersistenceFacade getInstance() {
        if(instance == null) {
            instance = new PersistenceFacade();
        }

        return instance;
    }
}
