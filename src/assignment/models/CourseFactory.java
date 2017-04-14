package assignment.models;

import assignment.discountstrategies.*;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.LinkedList;

/**
 * Created by shawon on 3/3/17.
 */
public class CourseFactory {
    private LinkedList<Course> cList;
    private static CourseFactory instance;
    private IExtraFeeCalculator efCalculator;

    public CourseFactory() {

    }

    public LinkedList<Course> getcList() {
        String url = "jdbc:sqlite:DB/test.db";
        Connection connection;
        String sql = "SELECT * FROM Course_List;";
        ResultSet courses;
        cList = new LinkedList<Course>();

        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement query = connection.prepareStatement(sql);

            courses = query.executeQuery();

            while (courses.next()) {
                Course course = new Course(courses.getString(1), courses.getString(2),
                        courses.getInt(3), courses.getInt(4), courses.getString(5));

                cList.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cList;
    }

    public LinkedList<String> getpList() {
        String url = "jdbc:sqlite:DB/test.db";
        Connection connection;
        String sql = "SELECT * FROM Programs;";
        ResultSet programs;
        LinkedList<String> pList = new LinkedList<String>();

        try {
            connection = DriverManager.getConnection(url);
            PreparedStatement query = connection.prepareStatement(sql);

            programs = query.executeQuery();

            while (programs.next()) {
                pList.add(programs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pList;
    }


    public Course getCourse(String id) {
        Course course;

        course = cList.stream().filter(k -> k.getId().equals(id)).findFirst().get();

        return course;
    }

    public static synchronized CourseFactory getInstance() {
        if(instance == null) {
            instance = new CourseFactory();
        }

        return instance;
    }

    public IExtraFeeCalculator getExtraFeeCalculator() {
        if (efCalculator == null) {
            String className = this.getClass().getPackage().getName() + "." +
                    System.getProperty("IExtraFeeCalculator.class.name");
            try {
                efCalculator = (IExtraFeeCalculator) Class.forName(className).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        return efCalculator;
    }

    public IDiscountStrategy createDiscountPolicy(LinkedList<String> atomicStrategies, String compositeStrategy) {

        int listSize = atomicStrategies.size();
        String strategyPackageName = "assignment.discountstrategies.";

        try {
            if (listSize > 1) {
                compositeStrategy = strategyPackageName + compositeStrategy;
                CompositeDiscount tempStrategy = (CompositeDiscount)
                        Class.forName(compositeStrategy).newInstance();

                for (String name : atomicStrategies) {
                    IDiscountStrategy tempAtom = (IDiscountStrategy)
                            Class.forName(strategyPackageName + name).newInstance();

                    tempStrategy.add(tempAtom);
                }

                return tempStrategy;
            }
            else if (listSize == 1) {
                IDiscountStrategy tempStrategy = (IDiscountStrategy) Class.forName(
                        strategyPackageName+atomicStrategies.get(0)).newInstance();

                return tempStrategy;
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
