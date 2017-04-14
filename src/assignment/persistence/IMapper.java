package assignment.persistence;

/**
 * Created by nobel on 14/04/17.
 */
public interface IMapper {
    public Object get(String id);
    public void put(Object object);
}
