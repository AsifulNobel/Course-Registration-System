package assignment.models;

import java.util.LinkedList;
import java.util.Observable;

/**
 * Created by nobel
 * modified by shawon
 * on 03/03/17.
 */
public class Registration extends Observable {
    public LinkedList<Course> courseList;
    private IExtraFeeCalculator iefc;
    private int total;
    private IDiscountStrategy discountStrategy;

    public Registration() {
         courseList = new LinkedList<>();
         total = 0;
    }

    public LinkedList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(LinkedList<Course> courseList) {
        this.courseList = courseList;
    }

    public void addCourse(Course newCourse) {
        courseList.add(newCourse);
    }

    public int getTotal() {
        total = courseList.stream().mapToInt(Course::getSubTotal).sum();

        return total;
    }

    public int getTotalWithDiscount() {
        total = courseList.stream().mapToInt(Course::getSubTotal).sum();

        return discountStrategy.getTotal(this);
    }


    public int getExtraFeeAmount() {
        iefc = CourseFactory.getInstance().getExtraFeeCalculator();
        return iefc.getExtraAmount(this.getTotal());
    }

    public IDiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public int getGrandTotal() {
        clearChanged();
        setChanged();
        notifyObservers();

        int grand = 0;

        if(discountStrategy != null) {
            grand =  this.getTotalWithDiscount() + this.getExtraFeeAmount();
        }
        grand =  this.getTotal() + this.getExtraFeeAmount();

        if(grand < 0) return 0;
        return grand;

    }

}
