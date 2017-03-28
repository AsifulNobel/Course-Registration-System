package assignment.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Created by shawon on 3/3/17.
 */
public class CourseFactory {
    private LinkedList<Course> cList;
    private static CourseFactory instance;
    private IExtraFeeCalculator efCalculator;

    public CourseFactory() {

        cList = new LinkedList<>();

        // generate initial data
        cList.add(new Course("CSE 327", "Soft.Eng.", 3, 5500));
        cList.add(new Course("CSE 440", "Intro. to AI", 3, 5500));
        cList.add(new Course("CSE 472", "Adv.Algo.", 3, 5500));
        cList.add(new Course("CSE 373", "Intro.Algo.", 3, 5500));
        cList.add(new Course("CSE 115", "Prog.Lang.1", 4, 5500));
        cList.add(new Course("CSE 173", "Disc.Math", 3, 5500));
        cList.add(new Course("CSE 215", "Prog.Lang.2", 4, 5500));
        cList.add(new Course("CSE 311", "Database", 3, 5500));
        cList.add(new Course("CSE 338", "Networking", 3, 5500));

        this.LoadProperties();
    }

    public LinkedList<Course> getcList() {
        return cList;
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
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return efCalculator;
    }

    public void LoadProperties() {
        Properties prop = new Properties();

        // minimized try catch block. Java 7 way
        try (FileInputStream propFile = new FileInputStream("resources/CourseRegister.config")){
            prop.load(propFile);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // set the system properties
        System.setProperty("IExtraFeeCalculator.class.name", prop.getProperty("IExtraFeeCalculator.class.name"));
    }
}
