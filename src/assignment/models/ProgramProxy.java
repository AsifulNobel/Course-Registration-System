package assignment.models;

import assignment.persistence.PersistenceFacade;

import java.sql.SQLException;

/**
 * Created by nobel on 15/04/17.
 */
public class ProgramProxy implements IProgram {
    private IProgram realProgram;
    private String id;

    public ProgramProxy(String id) {
        this.id = id;
    }

    public IProgram getRealProgram() {
        if (realProgram == null)
            try {
                realProgram = (IProgram) PersistenceFacade.getInstance().get(id, Program.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return realProgram;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return getRealProgram().getTitle();
    }

    @Override
    public String getDepartment() {
        return getRealProgram().getDepartment();
    }
}
