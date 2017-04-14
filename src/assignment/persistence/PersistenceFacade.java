package assignment.persistence;

import assignment.models.Course;
import assignment.models.Registration;

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

    }

    public void put(Object object) {

        IMapper mapper = mappers.get(object.getClass());
        mapper.put(object);
    }

    public Object get(String id , Object object) {

        IMapper mapper = mappers.get(object.getClass());
        return mapper.get(id);
    }

    public static synchronized PersistenceFacade getInstance() {
        if(instance == null) {
            instance = new PersistenceFacade();
        }

        return instance;
    }
}
