package assignment.models;

import assignment.discountstrategies.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

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
