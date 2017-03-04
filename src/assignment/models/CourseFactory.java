package assignment.models;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * Created by shawon on 3/3/17.
 */
public class CourseFactory {
    private LinkedList<Course> cList;

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

    }

    public LinkedList<Course> getcList() {
        return cList;
    }


    public Course getCourse(String id) {
        Course course;

        course = cList.stream().filter(k -> k.getId().equals(id)).findFirst().get();

        return course;
    }
}
