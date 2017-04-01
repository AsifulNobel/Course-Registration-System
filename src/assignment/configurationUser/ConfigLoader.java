package assignment.configurationUser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by nobel on 01/04/17.
 */
public class ConfigLoader {
    private static ConfigLoader instance;

    public ConfigLoader() {

    }

    public static synchronized ConfigLoader getInstance() {
        if(instance == null) {
            instance = new ConfigLoader();
        }

        return instance;
    }


    public void loadProperties() {
        Properties prop = new Properties();

        // minimized try catch block. Java 7 way
        try (FileInputStream propFile = new FileInputStream("resources/CourseRegister.config")){
            prop.load(propFile);
            Enumeration props = prop.propertyNames();

            while (props.hasMoreElements()) {
                String temp = (String) props.nextElement();
                System.setProperty(temp, prop.getProperty(temp));
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
